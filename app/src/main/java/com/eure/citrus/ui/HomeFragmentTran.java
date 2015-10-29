package com.eure.citrus.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eure.citrus.R;
import com.eure.citrus.Utils;
import com.eure.citrus.listener.OnClickMainFABListener;
import com.eure.citrus.listener.OnMakeSnackbar;
import com.eure.citrus.listener.OnSwipeableRecyclerViewTouchListener;
import com.eure.citrus.model.entity.Task;
import com.eure.citrus.model.entity.Tran;
import com.eure.citrus.model.repository.TaskRepository;
import com.eure.citrus.model.repository.TranRepository;
import com.eure.citrus.ui.adapter.HomeTaskListAdapter;
import com.eure.citrus.ui.adapter.HomeTranListAdapter;
import com.eure.citrus.ui.widget.DividerItemDecoration;
import com.nifty.cloud.mb.NCMBObject;
import com.nifty.cloud.mb.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

import static butterknife.ButterKnife.findById;

/**
 * Created by katsuyagoto on 15/06/18.
 */
public class HomeFragmentTran extends Fragment implements OnClickMainFABListener {

    private static final int REQUEST_CREATE_TASK_ACTIVITY = 1000;

    public HomeFragmentTran() {
    }

    public static HomeFragmentTran newInstance() {
        return new HomeFragmentTran();
    }

    @Bind(R.id.home_task_count_tran)
    AppCompatTextView mHomeTaskCountTextView_tran;

    private HomeTranListAdapter mHomeTranListAdapter;

    // Realm instance for the UI thread
    private Realm mUIThreadRealm;

    // NCMBQuery instance for the UI thread
    private NCMBQuery<NCMBObject> mUIThreadNCMB;

    private OnMakeSnackbar mOnMakeSnackbar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnMakeSnackbar) {
            mOnMakeSnackbar = (OnMakeSnackbar) activity;
        }

        mUIThreadNCMB = NCMBQuery.getQuery("TestClass");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tran, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final List<Tran> allTrans = TranRepository
                .findAllByTranClass(mUIThreadNCMB, "");
        mHomeTaskCountTextView_tran.setText(String.valueOf(allTrans.size()));
        mHomeTranListAdapter = new HomeTranListAdapter(allTrans);

        RecyclerView recyclerView = findById(view, R.id.home_recycle_view_tran);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(Utils.getDrawableResource(getActivity(), R.drawable.line)));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mHomeTranListAdapter);
        OnSwipeableRecyclerViewTouchListener swipeTouchListener =
                new OnSwipeableRecyclerViewTouchListener(recyclerView,
                        new OnSwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView,
                                    int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    final Tran tran = allTrans.get(position);
                                    TranRepository.update(tran);
                                    mHomeTranListAdapter.notifyItemRemoved(position);
                                    showSnackbarWhenDismiss(getString(R.string.complete_task, task.getName()),
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    TranRepository
                                                            .update(tran);
                                                    mHomeTranListAdapter.notifyItemInserted(position);
                                                    updateHomeTranListAdapter();
                                                }
                                            });
                                }
                                updateHomeTranListAdapter();

                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView,
                                    int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    final Tran tran = allTrans.get(position);
                                    TranRepository.update(tran);
                                    mHomeTranListAdapter.notifyItemRemoved(position);
                                    showSnackbarWhenDismiss(getString(R.string.complete_task, task.getName()),
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    TranRepository
                                                            .update(tran);
                                                    mHomeTranListAdapter.notifyItemInserted(position);
                                                    updateHomeTranListAdapter();
                                                }
                                            });
                                }
                                updateHomeTranListAdapter();

                            }
                        });
        recyclerView.addOnItemTouchListener(swipeTouchListener);

        AppCompatTextView homeDayOfWeekTextView = findById(view, R.id.home_dayOfWeek);
        homeDayOfWeekTextView.setText(Utils.getDayOfWeekString());

        AppCompatTextView homeDateTextView = findById(view, R.id.home_date);
        homeDateTextView.setText(Utils.getDateString().toUpperCase());
    }


    private void showSnackbarWhenDismiss(String text, View.OnClickListener listener) {
        if (mOnMakeSnackbar != null) {
            Snackbar snackbar = mOnMakeSnackbar.onMakeSnackbar(text, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.undo, listener);
            snackbar.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CREATE_TASK_ACTIVITY:
                if (resultCode == Activity.RESULT_OK) {
                    final ArrayList<Tran> allTrans = TranRepository
                            .findAllByCompleted(mUIThreadRealm, false);
                    mHomeTranListAdapter.setData(uncompletedTasks);
                    updateHomeTaskListAdapter();
                }
                break;
        }
    }

    /**
     * Should call this method after HomeTaskListAdapter's data are changed.
     */
    private void updateHomeTranListAdapter() {
        mHomeTaskListAdapter.notifyDataSetChanged();
        mHomeTaskCountTextView.setText(String.valueOf(mHomeTaskListAdapter.getItemCount()));
    }

    @Override
    public void onClickMainFAB() {
        Intent intent = new Intent(getActivity(), CreateNewTaskActivity.class);
        startActivityForResult(intent, REQUEST_CREATE_TASK_ACTIVITY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUIThreadRealm.close();
    }

}
