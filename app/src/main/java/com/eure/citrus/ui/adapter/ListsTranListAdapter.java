package com.eure.citrus.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eure.citrus.R;
import com.eure.citrus.listener.OnRecyclerItemClickListener;
import com.eure.citrus.model.entity.Task;
import com.eure.citrus.model.entity.Tran;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by katsuyagoto on 15/06/18.
 */
public class ListsTranListAdapter extends RecyclerView.Adapter<ListsTranListAdapter.ViewHolder> {

    private Context mContext;

    private RealmResults<Task> mTasks;
    private List<Tran> mTrans;

    private static boolean sShowGroupName = true;

    private static OnRecyclerItemClickListener sOnRecyclerItemClickListener;

    public ListsTranListAdapter(Context context, RealmResults<Task> tasks,
                                OnRecyclerItemClickListener onRecyclerItemClickListener, boolean showGroupName) {
        super();
        mContext = context;
        mTasks = tasks;
        sOnRecyclerItemClickListener = onRecyclerItemClickListener;
        sShowGroupName = showGroupName;
    }

    public ListsTranListAdapter(Context context, List<Tran> trans,
                                OnRecyclerItemClickListener onRecyclerItemClickListener, boolean showGroupName) {
        super();
        mContext = context;
        mTrans = trans;
        sOnRecyclerItemClickListener = onRecyclerItemClickListener;
        sShowGroupName = showGroupName;
    }

    public void setData(RealmResults<Task> tasks) {
        this.mTasks = tasks;
    }

    public void setData(List<Tran> trans) {

        this.mTrans = trans;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_lists_tran_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tran tran = mTrans.get(position);
        holder.tranNameText.setChecked(true);
        holder.tranNameText.setText(tran.mAmount.toString());
        changeTranNameState(holder.itemView, holder.tranNameText, tran.mTranClass, mContext.getResources());
        holder.tranGroupText.setText(tran.mApplication);
    }

    public Tran getItem(int position) { return mTrans.get(position); }

    @Override
    public int getItemCount() {
        return mTrans.size();
    }

    /**
     * Change background color, text color
     */
    public void changeTranNameState(View view, AppCompatCheckedTextView tranNameText, String aTranClass,
            Resources resources) {
        tranNameText.setChecked(aTranClass == "支払い" ? true:false);
        if (aTranClass == "支払い" ? true : false) {
            tranNameText.setTextColor(resources.getColor(android.R.color.holo_red_light));
            view.setBackgroundColor(resources.getColor(R.color.mt_gray1));
        } else if (aTranClass == "収入" ? true : false){
            tranNameText.setTextColor(resources.getColor(android.R.color.holo_blue_light));
            view.setBackgroundColor(resources.getColor(R.color.mt_gray1));
        } else {
            tranNameText.setTextColor(resources.getColor(android.R.color.holo_orange_light));
            view.setBackgroundColor(resources.getColor(R.color.mt_gray1));
        }
    }

    public void release() {
        sOnRecyclerItemClickListener = null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.lists_tran_name)
        AppCompatCheckedTextView tranNameText;

        @Bind(R.id.lists_tran_group)
        AppCompatTextView tranGroupText;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
            if (!sShowGroupName) {
                tranGroupText.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                sOnRecyclerItemClickListener.onClickRecyclerItem(view, position);
            }
        }
    }
}
