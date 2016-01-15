package liuliu.quzhang.base;

import android.os.Bundle;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;

/**
 * BaseActivity声明相关通用方法
 * <p/>
 * Created by LiuWeiJie on 2015/7/22 0022.
 * Email:1031066280@qq.com
 */
public abstract class BaseActivity extends FinalActivity {
    public FinalDb mDB;
    public Utils mUtils;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        mDB = FinalDb.create(this);
        mUtils = new Utils(this);
        initEvents();
    }


    public abstract void initViews();

    public abstract void initEvents();
}
