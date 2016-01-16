package liuliu.quzhang.base;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.WindowManager;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import liuliu.quzhang.R;

/**
 * Created by Administrator on 2015/12/29.
 */
public class Utils {

    public static String Preferences_name = "BULKGASOLINE";
    private Context mContext = BaseApplication.getContext();

    public Utils(Context mContext) {
        this.mContext = mContext;
    }

    public interface putListener {
        void put(Intent intent);
    }

    public void IntentPost(Class cla, putListener listener) {
        Intent intent = new Intent();
        intent.setClass(mContext, cla);
        if (listener != null) {
            listener.put(intent);
        }
        mContext.startActivity(intent);
    }

    public void IntentPost(Class cla) {
        IntentPost(cla, null);
    }

    //intent=getIntent();name=标识符
    public String IntentGet(Intent intent, String name) {
        String value = intent.getStringExtra(name);
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }

    public static Object getObject(String modelName, JSONObject jsonObject) {
        Object objectModel = new Object();
        Field[] fields;
        if (jsonObject != null) {
            if (!modelName.equals("")) {
                try {
                    objectModel = getBean(modelName);
                    fields = objectModel.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        setProperty(objectModel, field.getName(), jsonObject.getString(field.getName()), field.getType().getSimpleName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return objectModel;
    }

    public static Bitmap getBitmapFromRes(int resId) {
        Resources res = BaseApplication.getContext().getResources();
        return BitmapFactory.decodeResource(res, resId);
    }

    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //  获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    //创建的model对象，字段名，字段值
    public static void setProperty(Object bean, String propertyName, String propertyValue, String type) throws Exception {
        Class cls = bean.getClass();
        Method[] methos = cls.getMethods();//得到所有的方法
        for (Method m : methos) {
            if (m.getName().equalsIgnoreCase("set" + propertyName)) {
                //找到方法就注入
                if (type.equals("String")) {
                    m.invoke(bean, propertyValue);
                } else if (type.equals("int")) {
                    m.invoke(bean, new Integer(propertyValue.toString()));
                } else if (type.equals("boolean")) {
                    m.invoke(bean, new Boolean(propertyValue.toString()));
                }
                break;
            }
        }
    }

    //包名+model名
    public static Object getBean(String className) throws Exception {
        Class cls;
        try {
            cls = Class.forName("liuliu.demo.list.model." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception("类错误");
        }
        Constructor[] cons;
        try {
            cons = cls.getConstructors();//得到所有构造器
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("构造器错误");
        }
        //如果上面没错，就有构造方法
        Constructor defcon = cons[0];//得到默认构造器，第0个事默认构造器，无参构造方法
        Object obj = defcon.newInstance();//实例化，得到一个对象
        return obj;
    }

    /**
     * 获得当前屏幕宽度
     *
     * @param context 系统变量
     * @return 屏幕的宽度
     */
    public static int getScannerWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    /**
     * 获得当前屏幕高度
     *
     * @param context 系统变量
     * @return 屏幕的宽度
     */
    public static int getScannerHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!extraInfo.isEmpty()) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    public static boolean isEmptyString(String str) {
        return (str == null || str.length() == 0);
    }

    public static String encodeBitmap(Bitmap bitmap) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
                    .trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String URLEncode(String text) {
        if (isEmptyString(text))
            return "";
        return text;
        /*
         * if(Utils.isEmptyString(text)) return "";
		 *
		 * return URLEncoder.encode(text);
		 */
    }

    public static Bitmap getimage(Context contxt, String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为


        Bitmap header = BitmapFactory.decodeResource(contxt.getResources(), R.mipmap.morentupian);

        float hh = header.getHeight();//这里设置高度为800f
        float ww = header.getWidth();//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
    public static Bitmap getBitmapByte(String str) {
//        Bitmap bitmap;
//        byte[] s = Base64.encode(str.getBytes(), Base64.DEFAULT);
//        bitmap = BitmapFactory.decodeByteArray(s, 0, s.length);
//        return bitmap;
        try {
            byte[] buffer = Base64.decode(str.getBytes(), Base64.DEFAULT);
            if (buffer != null && buffer.length > 0) {
                return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String URLEncodeImage(String text) {
        if (Utils.isEmptyString(text))
            return "";

        return URLEncoder.encode(text);
    }
    public static void WriteString(Context context, String key, String value) {
        SharedPreferences sp = getSharedPreferences(context);
        // 存入数据
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String ReadString(Context context,String key) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(key, "");
    }
    public static SharedPreferences getSharedPreferences(Context mContext) {
        return mContext.getSharedPreferences(Preferences_name,
                Context.MODE_PRIVATE);
    }
    public static boolean checkBluetooth(Activity context, int requestCode) {
        /*
         * Intent serverIntent = new Intent(context, DeviceListActivity.class);
		 * context.startActivity(serverIntent); return true;
		 */

        boolean result = true;
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if (null != ba) {
            if (!ba.isEnabled()) {
                result = false;
                Intent intent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                context.startActivityForResult(intent, requestCode);// 或者ba.enable();
                // //同样的关闭WIFi为ba.disable();
            }
        }
        return result;
    }
}
