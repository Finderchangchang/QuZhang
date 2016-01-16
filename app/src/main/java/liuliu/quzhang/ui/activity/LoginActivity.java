package liuliu.quzhang.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;
import liuliu.quzhang.base.Utils;
import liuliu.quzhang.control.manage.login.ILoginView;
import liuliu.quzhang.control.manage.login.LoginListener;

/**
 * 用户登录
 * Created by Administrator on 2016/1/15.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    @CodeNote(id = R.id.userid_login_et)
    EditText userId_et;
    @CodeNote(id = R.id.pwd_login_et)
    EditText pwd_et;
    @CodeNote(id = R.id.login_btn, click = "onClick")
    Button login_btn;
    @CodeNote(id = R.id.setting_iv_login, click = "onClick")
    ImageView setting_iv_login;
    LoginListener mLogin;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_login);
        mLogin = new LoginListener(this, this);
    }

    @Override
    public void initEvents() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn://用户登录
                String userId = userId_et.getText().toString();
                String pwd = pwd_et.getText().toString();
                if (!userId.isEmpty() || !pwd.isEmpty()) {
                    mLogin.loading(userId, pwd);
                } else {
                    //账号密码不能为空
                    Toast.makeText(this, "账号或密码不能为空！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.setting_iv_login:
                mUtils.IntentPost(SettingActivity.class);
                break;
        }
    }

    @Override
    public void LoadResult(boolean result) {
        if (result) {//登录成功
            mUtils.IntentPost(MainActivity.class);
            this.finish();//关闭登录页面
        } else {
            //账号，密码不正确
        }
    }
}
