package liuliu.quzhang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class RyAdapter<T> extends RecyclerView.Adapter<VViewHolder> {
    private int mLayoutId;
    private List<T> mData;
    private Context mContext;

    public RyAdapter(Context context, List<T> messages, int layoutId) {
        mLayoutId = layoutId;
        mData = messages;
        mContext = context;
    }

    /**
     * 组件绑定
     *
     * @param parent
     * @return
     */
    @Override
    public VViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(mContext)
                .inflate(mLayoutId, parent, false);
        return new VViewHolder(v);
    }

    /**
     * 数据与组件绑定
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(VViewHolder viewHolder, int position) {
        convert(viewHolder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void convert(VViewHolder holder, T t, int position);
}
