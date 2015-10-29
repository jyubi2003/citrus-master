package com.eure.citrus.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eure.citrus.R;
import com.eure.citrus.model.entity.Task;
import com.eure.citrus.model.entity.Tran;
import com.nifty.cloud.mb.NCMBObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by katsuyagoto on 15/06/18.
 */
public class HomeTranListAdapter extends RecyclerView.Adapter<HomeTranListAdapter.ViewHolder> {

    private List<Tran> mTrans;

    public HomeTranListAdapter(List<Tran> trans) {
        super();
        mTrans = trans;
    }

    public void setData(List<Tran> trans) {
        this.mTrans = trans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_tran_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tran tran = mTrans.get(position);
        holder.tranNameText.setText(tran.mAmount.toString());
        holder.tranGroupText.setText(tran.mApplication);
    }

    @Override
    public int getItemCount() {
        return mTrans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.home_tran_name)
        AppCompatTextView tranNameText;

        @Bind(R.id.home_tran_group)
        AppCompatTextView tranGroupText;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
