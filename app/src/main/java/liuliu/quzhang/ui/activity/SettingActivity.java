package liuliu.quzhang.ui.activity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;
import liuliu.quzhang.base.Utils;
import liuliu.quzhang.model.BlueToothModel;
import liuliu.quzhang.model.YZXXModel;

/**
 * Created by Administrator on 2016/1/16.
 */
public class SettingActivity extends BaseActivity {
    @CodeNote(id = R.id.edit_write_bluetooth)
    EditText SearchBlueTooth;
    @CodeNote(id = R.id.back_iv_search, click = "onClick")
    ImageView back_iv_search;
    @CodeNote(id = R.id.clear_btn_setting, click = "onClick")
    Button clear_btn_setting;
    private String[] blue_device_scale;
    List<BlueToothModel> listblue;
    List<YZXXModel> mList;
    String ad = "";

    @Override
    public void initViews() {
        setContentView(R.layout.activity_setting);
        mList = new ArrayList();
        ad = Utils.ReadString(SettingActivity.this, "BLUETOOTH");
    }

    @Override
    public void initEvents() {
        getBluetooth();
        for (int i = 0; i < listblue.size(); i++) {
            if (listblue.get(i).getDeviceAddress().equals(ad)){
                SearchBlueTooth.setText(listblue.get(i).getDeviceName());
                return;
            }
        }
        SearchBlueTooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(blue_device_scale);
            }
        });
    }

    //选择弹出框
    private void showDialog(final String[] value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this, Window.FEATURE_NO_TITLE);
        builder.setItems(value, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SearchBlueTooth.setText(listblue.get(which).getDeviceName());
                Utils.WriteString(SettingActivity.this, "BLUETOOTH", listblue.get(which).getDeviceAddress());
            }
        });
        builder.show();
    }

    private void getBluetooth() {
        BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(SettingActivity.this, "不支持蓝牙设备！", Toast.LENGTH_SHORT).show();
        }
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() == 0) {
            Toast.makeText(SettingActivity.this, "没有配对蓝牙设备！", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
        }
        blue_device_scale = new String[pairedDevices.size()];
        int count = 0;
        listblue = new ArrayList<BlueToothModel>();
        for (BluetoothDevice device : pairedDevices) {
            BlueToothModel blue = new BlueToothModel();
            blue.setDeviceName(device.getName());
            blue.setDeviceAddress(device.getAddress());
            listblue.add(blue);
            blue_device_scale[count++] = device.getName();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv_search:
                this.finish();
                break;
            case R.id.clear_btn_setting:
                initDatas();
                try {
                    List mResult = mDB.findAll(YZXXModel.class);
                    if (mResult.size() == 0) {
                        for (YZXXModel model : mList) {
                            mDB.save(model);
                        }
                    } else {
                        mDB.deleteAll(YZXXModel.class);
                        for (YZXXModel model : mList) {
                            mDB.save(model);
                        }
                    }
                } catch (Exception e) {
                    for (YZXXModel model : mList) {
                        mDB.save(model);
                    }
                }
                break;
        }
    }

    /**
     * 加载数据到数据库
     */
    private void initDatas() {
        mList.add(new YZXXModel("1301020100001", true, "40合同章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2016-01-11 11:26:15.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100002", true, "40X30发票专用章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-11-22 14:27:32.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100003", true, "38财务专用章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-10-29 15:23:41.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100004", true, "20X20法人名章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-09-11 09:28:55.000", "0", "王枫林", "18915673214"));
        mList.add(new YZXXModel("1301020100005", true, "40行政章", "塑料章体", "新刻印章", "河北创维网络科技有限公司", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2015-08-14 11:23:11.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100006", true, "40X30发票专用章", "金属章体", "新刻印章", "河北创维网络科技有限公司", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2015-09-11 08:25:42.000", "0", "王枫林", "18915673214"));
        mList.add(new YZXXModel("1301020100007", true, "40行政章", "金属章体", "新刻印章", "河北创维网络科技有限公司的比较常用的章", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2016-01-12 08:51:22.000", "0", "王枫林", "18915673214"));
        mList.add(new YZXXModel("1301020100008", true, "20X20法人名章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-03-21 16:22:35.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100009", true, "40合同章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-03-25 13:27:35.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100010", true, "38财务专用章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-04-29 09:27:32.000", "0s", "王枫林", "18915673214"));
    }
}
