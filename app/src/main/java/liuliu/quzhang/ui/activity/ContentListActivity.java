package liuliu.quzhang.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.quzhang.R;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ContentListActivity extends Activity{
    @CodeNote(id=R.id.show_lv_content)ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);
    }
}
