package liuliu.quzhang.ui.activity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;
import liuliu.quzhang.base.Utils;
import liuliu.quzhang.model.BlueToothModel;

/**
 * Created by Administrator on 2016/1/16.
 */
public class SettingActivity extends BaseActivity {
    @CodeNote(id = R.id.edit_write_bluetooth)
    EditText SearchBlueTooth;
    private String[] blue_device_scale;
    List<BlueToothModel> listblue;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initEvents() {
        getBluetooth();
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
}
