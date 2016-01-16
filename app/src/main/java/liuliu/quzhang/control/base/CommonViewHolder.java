package liuliu.quzhang.control.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import net.tsz.afinal.view.banner.ImageModel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import liuliu.quzhang.R;
import me.next.tagview.TagCloudView;

/**
 * Created by liuliu on 2015/11/16   16:29
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class CommonViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private ImageLoader mImageLoader;
    private Context mContext;
    private DisplayImageOptions mDisplayImageOptions;
    private ImageLoadingListenerImpl mImageLoadingListenerImpl;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId,
                             int position) {
        this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(this);
        mImageLoader = ImageLoader.getInstance();
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_default_adimage)
                .showImageForEmptyUri(R.mipmap.ic_default_adimage)
                .showImageOnFail(R.mipmap.ic_default_adimage)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .resetViewBeforeLoading()
                .build();
        mImageLoadingListenerImpl = new ImageLoadingListenerImpl();
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder get(Context context, View convertView,
                                       ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    public CommonViewHolder setHeight(int viewId, int height) {
        LinearLayout view = getView(viewId);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
        return this;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, Object text) {
        TextView view = getView(viewId);
        view.setText(text + "");
        return this;
    }

    public CommonViewHolder setTag(int viewId, List<String> list) {
        TagCloudView view = getView(viewId);
        view.setTags(list);
        return this;
    }

    //获取知道textview的值
    public String getText(int viewId) {
        TextView view = getView(viewId);
        return view.getText().toString().trim();
    }

    /**
     * 设置监听时间
     *
     * @param viewId
     * @param listener
     * @return
     */
    public CommonViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public CommonViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public CommonViewHolder setImageDrawable(int viewId, Drawable bm) {
        ImageView view = getView(viewId);
        view.setImageDrawable(bm);
        return this;
    }

    public CommonViewHolder loadByImage(int viewId, String link) {
        ImageView view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        try {
            //加载图片
            mImageLoader.displayImage(
                    link,
                    view,
                    mDisplayImageOptions,
                    mImageLoadingListenerImpl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CommonViewHolder loadGuanggaoByImage(int viewId, final ImageModel model) {
        ImageView view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        try {
            //加载图片
            mImageLoader.displayImage(
                    model.getImage(),
                    view,
                    mDisplayImageOptions,
                    mImageLoadingListenerImpl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Bitmap getBitmapFromRes(int resId) {
        Resources res = mContext.getResources();
        return BitmapFactory.decodeResource(res, resId);
    }


    private ImageView setImageTag(int viewId, String url) {
        ImageView view = getView(viewId);
        view.setTag(url);
        return view;
    }

    /**
     * 设置控件显示隐藏
     *
     * @param viewId（控件id）
     * @param result(控件显示隐藏)
     */
    public CommonViewHolder setVisible(int viewId, boolean result) {
        View view = getView(viewId);
        if (result) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
        return this;
    }

    public CommonViewHolder setMargin(int viewId, int left, int right, int top, int bottom) {
        LinearLayout ll = getView(viewId);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll.getLayoutParams();
        layoutParams.setMargins(left, right, top, bottom);//4个参数按顺序分别是左上右下
        ll.setLayoutParams(layoutParams);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

    //监听图片异步加载
    public static class ImageLoadingListenerImpl extends SimpleImageLoadingListener {

        public static final List<String> displayedImages =
                Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap bitmap) {
            if (bitmap != null) {
                ImageView imageView = (ImageView) view;
                boolean isFirstDisplay = !displayedImages.contains(imageUri);
                if (isFirstDisplay) {
                    //图片的淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                    System.out.println("===> loading " + imageUri);
                }
            }
        }
    }
}
