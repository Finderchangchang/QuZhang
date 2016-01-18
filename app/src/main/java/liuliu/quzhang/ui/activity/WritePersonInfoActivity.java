package liuliu.quzhang.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ivsign.android.IDCReader.CopyFile;
import com.ivsign.android.IDCReader.Cvr100bMYTask;
import com.ivsign.android.IDCReader.Cvr100bTask;

import net.tsz.afinal.annotation.view.CodeNote;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;
import liuliu.quzhang.base.Utils;
import liuliu.quzhang.model.BlueToothModel;
import liuliu.quzhang.model.PersonModel;
import liuliu.quzhang.model.YZXXModel;

/**
 * Created by Administrator on 2016/1/15.
 */
public class WritePersonInfoActivity extends BaseActivity {
    @CodeNote(id = R.id.info_read_card)
    Button readCard;
    @CodeNote(id = R.id.btn_yan)
    Button btnYan;
    @CodeNote(id = R.id.et_write_quname)
    EditText quanme;
    @CodeNote(id = R.id.et_write_qutel)
    EditText qutel;
    @CodeNote(id = R.id.et_write_card)
    EditText card;
    @CodeNote(id = R.id.et_write_address)
    EditText address;
    @CodeNote(id = R.id.et_write_jiguan)
    EditText jiguan;
    @CodeNote(id = R.id.et_write_sex)
    EditText sex;
    @CodeNote(id = R.id.et_write_nation)
    EditText nation;
    @CodeNote(id = R.id.et_write_time)
    EditText time;
    @CodeNote(id = R.id.et_wrte_shenname)
    EditText shenname;
    @CodeNote(id = R.id.et_write_shentel)
    EditText shentel;
    @CodeNote(id = R.id.et_yanzheng)
    EditText yanzheng;
    @CodeNote(id = R.id.text_count)
    TextView tCount;
    @CodeNote(id = R.id.reg_person_image)
    ImageView pimg;
    @CodeNote(id = R.id.text_count_mes)
    TextView countmes;

    StringBuffer path = new StringBuffer();
    @CodeNote(id = R.id.img_photo_image)
    ImageView img;
    Bitmap bm = null;
    @CodeNote(id = R.id.write_save)
    Button btnSave;
    @CodeNote(id = R.id.back_iv_search, click = "onClick")
    ImageView back_iv_search;
    int count = 0;
    Timer TTimer;
    TimerTask TTtimerTask;
    YZXXModel yzxxModel;
    @CodeNote(id = R.id.back_iv_search)
    ImageView backImg;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_write_info);
        yzxxModel = (YZXXModel) getIntent().getSerializableExtra("YZXXModel");
        shenname.setText(yzxxModel.getSQRName());
        shentel.setText(yzxxModel.getSQRTelNumber());
    }

    @Override
    public void initEvents() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                setResult(1, null);
            }
        });
        new CopyFile().CopyWltlib(WritePersonInfoActivity.this);
        readCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.ReadString(WritePersonInfoActivity.this, "BLUETOOTH").equals("")) {
                    Toast.makeText(WritePersonInfoActivity.this, "请选择蓝牙设备！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WritePersonInfoActivity.this, SettingActivity.class);
                    startActivity(intent);
                } else {
                    onReadCardCvr();
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera(11);
            }
        });

        btnYan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMobileNum(shentel.getText().toString().trim())) {
                    Toast.makeText(WritePersonInfoActivity.this, "发送成功！", Toast.LENGTH_SHORT).show();
                    count = 30;
                    tCount.setVisibility(View.VISIBLE);
                    countmes.setVisibility(View.VISIBLE);
                    btnYan.setEnabled(false);
                    TTimer = new Timer();
                    TTtimerTask = new TimerTask() {
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            handler.sendMessage(msg);
                        }
                    };
                    TTimer.schedule(TTtimerTask, 0, 1000);

                } else {
                    Toast.makeText(WritePersonInfoActivity.this, "手机号输入错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanme.getText().toString().trim().equals("")) {
                    Toast.makeText(WritePersonInfoActivity.this, "请填写取章人姓名！", Toast.LENGTH_SHORT).show();
                } else if (card.getText().toString().trim().equals("")) {
                    Toast.makeText(WritePersonInfoActivity.this, "请填写取章人身份证号码！", Toast.LENGTH_SHORT).show();
                } else if (!yanzheng.getText().toString().trim().equals("5650")) {
                    Toast.makeText(WritePersonInfoActivity.this, "验证码错误！", Toast.LENGTH_SHORT).show();
                } else if (bm == null) {
                    Toast.makeText(WritePersonInfoActivity.this, "请为印模预留表拍照！", Toast.LENGTH_SHORT).show();
                } else {
                    Timer timer = new Timer();
                    TimerTask tast = new TimerTask() {
                        @Override
                        public void run() {
                            Looper.prepare();
                            YZXXModel model = new YZXXModel();
                            // model=mDB.findAllByWhere(YZXXModel.class," ")

                            List<YZXXModel> list = mDB.findAllByWhere(YZXXModel.class, "SignetId='" + yzxxModel.getSignetId() + "'");
                            if (list.size() > 0) {
                                yzxxModel = list.get(0);
                                yzxxModel.setQUZhang("1");
                                mDB.update(yzxxModel);
                            }
                            Message msg = new Message();
                            msg.what = 0;
                            handlerclear.sendMessage(msg);
                            Looper.loop();
                        }
                    };
                    timer.schedule(tast, 2000);
                }
            }
        });
    }

    Handler handlerclear = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            clearText();
            Toast.makeText(WritePersonInfoActivity.this, "信息上传成功！", Toast.LENGTH_SHORT).show();
        }
    };

    private void clearText() {
        setResult(1, null);
        finish();
//        quanme.setText("");
//        qutel.setText("");
//        card.setText("");
//        address.setText("");
//        shentel.setText("");
//        shenname.setText("");
//        yanzheng.setText("");
//
//        pimg.setImageResource(R.mipmap.person_header);
//        img.setImageResource(R.mipmap.morentupian);
//        btnYan.setEnabled(true);
//        TTimer = null;
//        TTtimerTask = null;
//        tCount.setVisibility(View.GONE);
//        countmes.setVisibility(View.GONE);
//        tCount.setText("00");
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count != 0) {
                count--;
                if (count < 10) {
                    tCount.setText("0" + count);
                } else {
                    tCount.setText(count + "");
                }
            } else {
                tCount.setVisibility(View.GONE);
                countmes.setVisibility(View.GONE);
                btnYan.setEnabled(true);
                if (TTimer != null) {
                    TTimer.cancel();
                    TTimer = null;
                }
                if (TTtimerTask != null) {
                    TTtimerTask = null;
                }
            }
        }
    };

    //验证手机号是否正确
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }


    //cvr
    private void onReadCardCvr() {
        if (Utils.checkBluetooth(WritePersonInfoActivity.this, 2)) {
            final ProgressDialog progressDialog = ProgressDialog.show(WritePersonInfoActivity.this, "", "正在读取身份证信息...", true, true);
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Bundle bundle = msg.getData();
                    boolean find = false;
                    progressDialog.dismiss();
                    if (bundle != null) {
                        boolean result = bundle.getBoolean("result");
                        PersonModel person = (PersonModel) bundle.getSerializable("person_info");
                        if (result) {
                            find = true;
                            setPerson(person);
                        }
                    }
                    if (!find) {
                        Toast.makeText(WritePersonInfoActivity.this, "证件读取失败！（cvr）", Toast.LENGTH_SHORT).show();
                    }
                }
            };

            new Cvr100bMYTask().startCvr100bTask(WritePersonInfoActivity.this, new Cvr100bTask.Cvr100bListener() {
                @Override
                public BlueToothModel reauestBlueDevice() {
                    BlueToothModel blue = new BlueToothModel();
                    blue.setDeviceAddress(Utils.ReadString(WritePersonInfoActivity.this, "BLUETOOTH"));
                    return blue;
                }

                @Override
                public void onResult(boolean result, PersonModel person) {
                    Message msg = handler.obtainMessage(1);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("result", result);
                    bundle.putSerializable("person_info", person);
                    msg.setData(bundle);
                    msg.sendToTarget();
                }
            });
        } else {
            Toast.makeText(WritePersonInfoActivity.this, "请检查蓝牙读卡设备设置！", Toast.LENGTH_SHORT).show();
        }
    }

    private void setPerson(PersonModel person) {
        quanme.setText(person.getPersonName());
        card.setText(person.getPersonCardId());
        if (!person.getPersonCardImage().equals("")) {
            pimg.setImageBitmap(Utils.getBitmapByte(person.getPersonCardImage()));
        }
        jiguan.setText(person.getPersonNation());
        if (person.getPersonSex().equals("1")) {
            sex.setText("男");
        } else {
            sex.setText("女");
        }
        nation.setText(person.getPersonNation());
        time.setText(person.getPersonBirthday());
        address.setText(person.getPersonAddress());
    }

    //开启拍照
    public void startCamera(int type) {
        // 利用系统自带的相机应用:拍照
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        path = new StringBuffer();
        path.append(WritePersonInfoActivity.this.getExternalFilesDir(null)).append("/IMG_CASH.jpg");
        File file = new File(path.toString());
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TTimer = null;
        TTtimerTask = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 11) {
            bm = Utils.getimage(this, path.toString());
            if (bm != null) {
                img.setImageBitmap(bm);
                img.setTag(bm);
            } else {
                img.setImageResource(R.mipmap.morentupian);
                img.setTag(bm);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv_search:
                this.finish();
                break;
        }
    }
}
