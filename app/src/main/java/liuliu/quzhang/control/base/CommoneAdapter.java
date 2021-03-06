package liuliu.quzhang.control.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommoneAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    public CommoneAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = getViewHolder(position, convertView, parent);
        convert(holder, mDatas.get(position), position);
        return holder.getConvertView();
    }

    private CommonViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return CommonViewHolder.get(mContext, convertView, parent, layoutId, position);
    }

    public abstract void convert(CommonViewHolder holder, T model, int position);
}
