package liuliu.quzhang.control.manage.login;

import android.content.Context;

/**
 * Created by Administrator on 2016/1/15.
 */
public class LoginListener {
    private Context mContext;
    private ILoginView mView;

    public LoginListener(ILoginView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    /**
     * 执行登录的相关操作
     */
    public void loading(String userId, String pwd) {
        mView.LoadResult(true);//登录成功
    }
}
