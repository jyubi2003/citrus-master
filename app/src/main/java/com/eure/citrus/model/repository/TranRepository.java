package com.eure.citrus.model.repository;

import android.support.annotation.NonNull;

import com.eure.citrus.model.entity.Tran;
import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMBException;
import com.nifty.cloud.mb.NCMBObject;
import com.nifty.cloud.mb.NCMBQuery;
import com.nifty.cloud.mb.NCMB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by katsuyagoto on 15/06/25.
 */
public class TranRepository {

    /**
     * An array of TranData items.
     */
    public static List<Tran> ITEMS = new ArrayList<Tran>();

    /**
     * A map of TranData items, by ID.
     */
    public static Map<String, Tran> ITEM_MAP = new HashMap<String, Tran>();

    /**
     *
     * @param query
     * @param name
     * @param groupName
     * @return Tran
     */
    public static Tran create(@NonNull Tran tran) {
        NCMBObject obj = new NCMBObject("TestClass");

        tran.message = name;
        if (groupName != null) {
            tran.Remarks = groupName;
        }
        obj.add("message", tran.message);
        obj.add("Remarks", tran.Remarks);

        try {
            obj.save();
        } catch (Exception e){
            e.printStackTrace();
        }
        return tran;
    }

    /**
     *
     * @param query
     * @param tran
     */
    public static void delete(@NonNull NCMBQuery<NCMBObject> query, Tran tran) {
        List<NCMBObject> result;
        query.whereEqualTo("objectId", tran.objectId);
        try {
            result = query.find();
        } catch(Exception e){
            e.printStackTrace();
        }

        query.task.removeFromRealm();
        //realm.commitTransaction();
    }

    /**
     *
     * @param realm
     * @param task
     * @param completed
     * @return
     */
    public static Task updateByCompleted(@NonNull NCMBQuery<NCMBObject> query, Task task, boolean completed) {
        realm.beginTransaction();
        task.setCompleted(completed);
        realm.commitTransaction();
        return task;
    }

    /**
     *
     * @param realm
     * @return
     */
    public static long count(@NonNull NCMBQuery<NCMBObject> query) {
        return realm.where(Task.class)
                .count();
    }

    /**
     *
     * @param realm
     * @param completed
     * @return
     */
    public static long countByCompleted(@NonNull NCMBQuery<NCMBObject> query, boolean completed) {
        return realm.where(Task.class)
                .equalTo("completed", completed)
                .count();
    }

    /**
     *
     * @param realm
     * @return
     */
    public static RealmResults<Task> findAll(@NonNull NCMBQuery<NCMBObject> query) {
        return realm.where(Task.class)
                .findAll();
    }

    /**
     *
     * @param realm
     * @param completed
     * @return
     */
    public static List<Tran> findAllByCompleted(@NonNull NCMBQuery<NCMBObject> query, boolean completed) {

        //query.whereEqualTo("message", "Hello, NCMB!");
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> result, NCMBException e) {
                if (result != null) {
                    clrItem();
                    if (!result.isEmpty()) {
                        for (int i = 0; i < result.size(); i++) {
                            addItem(new TranItem(result.get(i)));
                        }
                    } else {
                        addItem(new TranItem(new NCMBObject("No Item.")));
                    }
                } else {    /* result == null */
                    e.printStackTrace();
                }
            }
        });






        return realm.where(Task.class)
                .equalTo("completed", completed)
                .findAll();
    }

    /**
     *
     * @param realm
     * @param groupName
     * @return
     */
    public static RealmResults<Task> findAllByGroupName(@NonNull NCMBQuery<NCMBObject> query,
            String groupName) {
        return realm.where(Task.class)
                .equalTo("groupName", groupName)
                .findAll();
    }
}
