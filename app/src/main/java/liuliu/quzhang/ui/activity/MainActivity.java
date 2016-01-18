package liuliu.quzhang.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.quzhang.R;
import liuliu.quzhang.base.BaseActivity;
import liuliu.quzhang.base.Utils;
import liuliu.quzhang.control.base.CommonViewHolder;
import liuliu.quzhang.control.base.CommoneAdapter;
import liuliu.quzhang.model.PersonModel;
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
    @CodeNote(id = R.id.search_choice_tv)
    TextView search_choice_tv;
    @CodeNote(id = R.id.no_shuju_ll_main)
    LinearLayout no_shuju_ll_main;
    CommoneAdapter mAdapter;
    List<YZXXModel> mList;


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
        mList.add(new YZXXModel("1301020100001", true, "40合同章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2016-01-11 11:26:15.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100002", true, "40X30发票专用章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-11-22 14:27:32.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100003", true, "38财务专用章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-10-29 15:23:41.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100004", true, "20X20法人名章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-09-11 09:28:55.000", "0", "王枫林", "18915673214"));
        mList.add(new YZXXModel("1301020100005", true, "40行政章", "塑料章体", "新刻印章", "河北创维网络科技有限公司", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2015-08-14 11:23:11.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100006", true, "40X30发票专用章", "金属章体", "新刻印章", "河北创维网络科技有限公司", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2015-09-11 08:25:42.000", "0", "王枫林", "18915673214"));
        mList.add(new YZXXModel("1301020100007", true, "40行政章", "金属章体", "新刻印章", "河北创维网络科技有限公司的比较常用的章", "王博", "河北创维网络科技有限公司", "长安区印章刻制中心", "2016-01-12 08:51:22.000", "0", "王枫林", "18915673214"));
        mList.add(new YZXXModel("1301020100008", true, "20X20法人名章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-03-21 16:22:35.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100009", true, "40合同章", "金属章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-03-25 13:27:35.000", "0", "胡海珍", "13145673214"));
        mList.add(new YZXXModel("1301020100010", true, "38财务专用章", "塑料章体", "新刻印章", "石家庄市佳美网络科技有限责任公司的比较常用的章", "郑斌文", "石家庄市佳美网络科技有限责任公司", "长安区印章刻制中心", "2015-04-29 09:27:32.000", "0", "王枫林", "18915673214"));
    }

    @Override
    public void initEvents() {
        try {
            List mResult = mDB.findAll(YZXXModel.class);
            if (mResult.size() == 0) {
                for (YZXXModel model : mList) {
                    mDB.save(model);
                }
            }
        } catch (Exception e) {
            for (YZXXModel model : mList) {
                mDB.save(model);
            }
        }
        reloadList(mList);

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
        String choice = data.getStringExtra("choice").toString().trim();
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
                if (!choice.equals("")) {
                    search_choice_tv.setText("条件：“" + choice + "”");
                    List<YZXXModel> list = mDB.findAllByWhere(YZXXModel.class, "SignetId like '%" + choice + "%' or UserCompanyName like '%" + choice + "%'");
                    loadRefresh(list);
                } else {
                    search_choice_tv.setText("请输入相关查询条件");
                    List<YZXXModel> list = mDB.findAll(YZXXModel.class);
                    loadRefresh(list);
                }
                break;
            case 1:
                List<YZXXModel> list = mDB.findAll(YZXXModel.class);
                loadRefresh(list);
                break;
        }
    }

    private void reloadList(List list) {
        mAdapter = new CommoneAdapter<YZXXModel>(this, list, R.layout.item_main) {
            @Override
            public void convert(CommonViewHolder holder, final YZXXModel model, int position) {
                holder.setText(R.id.yzid_tv, model.getSignetId());
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
                if (model.getQUZhang().equals("0")) {
                    holder.setOnClickListener(R.id.quzhang_btn_main, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mUtils.IntentActivityForResult(WritePersonInfoActivity.class, new Utils.putListener() {
                                @Override
                                public void put(Intent intent) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("YZXXModel", model);
                                    intent.putExtras(bundle);
                                    startActivityForResult(intent, 1);
                                }
                            });
                        }
                    });
                } else {
                    Button btn = holder.getView(R.id.quzhang_btn_main);
                    btn.setText("已取章");
                }

            }
        };
        show_lv.setAdapter(mAdapter);
    }

    private void loadRefresh(List list) {
        if (list.size() == 0) {
            no_shuju_ll_main.setVisibility(View.VISIBLE);
            show_lv.setVisibility(View.GONE);
        } else {
            no_shuju_ll_main.setVisibility(View.GONE);
            show_lv.setVisibility(View.VISIBLE);
        }
        reloadList(list);
        mAdapter.notifyDataSetChanged();
    }
}
