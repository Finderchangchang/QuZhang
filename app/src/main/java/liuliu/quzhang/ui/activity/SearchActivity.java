package liuliu.quzhang.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;

/**
 * Created by Administrator on 2016/1/16.
 */
public class SearchActivity extends BaseActivity {
    @CodeNote(id = R.id.back_iv_search, click = "onClick")
    ImageView back_iv_search;
    @CodeNote(id = R.id.search_ll, click = "onClick")
    LinearLayout search_ll;
    @CodeNote(id = R.id.choice_et_search)
    EditText choice_et_search;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void initEvents() {

    }

    Intent mIntent;

    public void onClick(View view) {
        mIntent = new Intent();
        switch (view.getId()) {
            case R.id.back_iv_search:
                mIntent.putExtra("choice", "");
                break;
            case R.id.search_ll://根据条件进行查询
                String choice = choice_et_search.getText().toString().trim();
                if (choice != "") {
                    mIntent.putExtra("choice", choice);
                } else {
                    mIntent.putExtra("choice", "");
                }
                break;
        }
        this.setResult(0, mIntent);
        this.finish();
    }
}
