package liuliu.quzhang.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;
import liuliu.quzhang.base.Utils;
import liuliu.quzhang.control.base.CommonViewHolder;
import liuliu.quzhang.control.base.CommoneAdapter;
import liuliu.quzhang.model.YZXXModel;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {
    @CodeNote(id = R.id.title_user_name_main)
    TextView title_user_name;
    @CodeNote(id = R.id.search_ll_main, click = "onClick")
    LinearLayout search_ll;
    @CodeNote(id = R.id.show_lv_main)
    ListView show_lv;
    CommoneAdapter mAdapter;
    List mList;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        mList = new ArrayList();
        initDatas();
    }

    /**
     * 加载数据到数据库
     */
    private void initDatas() {
        mList.add(new YZXXModel("1301020100001", true, "40合同章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2016-01-11 11:26:15.000"));
        mList.add(new YZXXModel("1301020100002", true, "40X30发票专用章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-11-22 14:27:32.000"));
        mList.add(new YZXXModel("1301020100003", true, "38财务专用章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-10-29 15:23:41.000"));
        mList.add(new YZXXModel("1301020100004", true, "20X20法人名章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-9-11 09:28:55.000"));
        mList.add(new YZXXModel("1301020100005", true, "40行政章", "塑料章体", "新刻印章", "河北创维网络科技有限公司", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2015-08-14 11:23:11.000"));
        mList.add(new YZXXModel("1301020100006", true, "40X30发票专用章", "金属章体", "新刻印章", "河北创维网络科技有限公司", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2015-09-11 08:25:42.000"));
        mList.add(new YZXXModel("1301020100007", true, "40行政章", "金属章体", "新刻印章", "河北创维网络科技有限公司的比较常用的章", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2016-01-12 08:51:22.000"));
        mList.add(new YZXXModel("1301020100008", true, "20X20法人名章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-03-21 16:22:35.000"));
        mList.add(new YZXXModel("1301020100009", true, "40合同章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-03-25 13:27:35.000"));
        mList.add(new YZXXModel("1301020100010", true, "38财务专用章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-04-29 09:27:32.000"));
    }

    @Override
    public void initEvents() {
        mAdapter = new CommoneAdapter<YZXXModel>(this, mList, R.layout.item_main) {
            @Override
            public void convert(CommonViewHolder holder, YZXXModel model, int position) {
                holder.setText(R.id.company_name_tv, model.getUserCompanyName());
                List list = new ArrayList();
                list.add(model.getYZType());
                holder.setTag(R.id.yz_type_tcv, list);
                list = new ArrayList();
                list.add(model.getGGId());//印章规格
                list.add(model.getZTCL());//章体材料
                if (model.isSPState()) {
                    list.add("已审批");
                } else {
                    list.add("未审批");
                }
                holder.setTag(R.id.yz_types_tcv, list);
                holder.setText(R.id.yz_content_tv, model.getYZContent());
                holder.setText(R.id.jb_name_tv, model.getJBName());
                holder.setText(R.id.kz_unit_tv, model.getKZUnit());
            }
        };
        show_lv.setAdapter(mAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_ll_main:
                mUtils.IntentActivityForResult(SearchActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        startActivityForResult(intent, 0);
                    }
                });
                break;
        }
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String choice = data.getStringExtra("choice");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
                Log.i("buggg", choice);
                break;
        }
    }
}
