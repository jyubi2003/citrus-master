package com.eure.citrus.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eure.citrus.R;
import com.eure.citrus.Utils;
import com.eure.citrus.listener.OnRecyclerItemClickListener;
import com.eure.citrus.model.entity.Task;
import com.eure.citrus.model.entity.Tran;
import com.eure.citrus.model.repository.TaskRepository;
import com.eure.citrus.model.repository.TranRepository;
import com.eure.citrus.ui.adapter.ListsTaskListAdapter;
import com.eure.citrus.ui.adapter.ListsTranListAdapter;
import com.eure.citrus.ui.widget.DividerItemDecoration;
import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMBException;
import com.nifty.cloud.mb.NCMBObject;
import com.nifty.cloud.mb.NCMBQuery;

import java.util.List;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

import static butterknife.ButterKnife.findById;

/**
 * Created by katsuyagoto on 15/06/19.
 */
public class TranListsFragment extends Fragment implements OnRecyclerItemClickListener {

    private static final String KEY_GROUP_NAME = "key_group_name";

    public TranListsFragment() {
    }

    public static TranListsFragment newInstance() {
        return new TranListsFragment();
    }

    public static TranListsFragment newInstance(String groupName) {
        TranListsFragment tranListsFragment = new TranListsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_GROUP_NAME, groupName);
        tranListsFragment.setArguments(args);
        return tranListsFragment;
    }

    private ListsTranListAdapter mListsTranListAdapter;

    // NCMBQuery instance for the UI thread
    private NCMBQuery<NCMBObject> mUIThreadNCMB;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mUIThreadNCMB =  NCMBQuery.getQuery("TestClass");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Tran> trans = null;
        boolean showGroupName = false;

        if (getArguments() != null) {
            String groupName = getArguments().getString(KEY_GROUP_NAME);
            trans = TranRepository.findAll(mUIThreadNCMB);
        } else {
            trans = TranRepository.findAll(mUIThreadNCMB);
            showGroupName = true;
        }

        mListsTranListAdapter = new ListsTranListAdapter(getActivity(), trans, this, showGroupName);

        RecyclerView recyclerView = findById(view, R.id.lists_recycler_view);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(Utils.getDrawableResource(getActivity(), R.drawable.line)));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mListsTranListAdapter);
    }

    @Override
    public void onClickRecyclerItem(View v, int position) {
        Tran tran = mListsTranListAdapter.getItem(position);
        AppCompatCheckedTextView tranNameText = (AppCompatCheckedTextView) v.findViewById(R.id.lists_tran_name);
        mListsTranListAdapter.changeTranNameState(v, tranNameText, "収入", getResources());

        TranRepository.update(tran);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListsTranListAdapter.release();
        mUIThreadNCMB.cancel();
    }

}
