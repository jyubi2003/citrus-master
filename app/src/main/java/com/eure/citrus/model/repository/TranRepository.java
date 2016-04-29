package com.eure.citrus.model.repository;

import android.support.annotation.NonNull;

import com.eure.citrus.model.entity.Tran;
import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMBACL;
import com.nifty.cloud.mb.NCMBException;
import com.nifty.cloud.mb.NCMBObject;
import com.nifty.cloud.mb.NCMBQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eure.citrus.model.entity.Tran;
import com.nifty.cloud.mb.SaveCallback;

import io.realm.RealmResults;

/**
 * Created by hiroshiyoshida on 15/10/19.
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
     * constructor
     */
    public  TranRepository() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    /**
     * create()
     * @return Tran
     */
    public static Tran create() {
        NCMBObject tmpObj = new NCMBObject("TestClass");
        Tran tran = new Tran();
        try {
            tmpObj.save();
        } catch (Exception e){
            e.printStackTrace();
        }
        tran.initBy(tmpObj);
        return tran;
    }

    /**
     * create()
     * @param aTran Tran object to save.
     * @return Tran saved
     */
    public static Tran create(@NonNull Tran aTran) {
        NCMBObject tmpObj = new NCMBObject("TranClass");

        tmpObj.add("objectId", aTran.mobjectId);
        tmpObj.add("TranDateTime", aTran.mTranDateTime);
        tmpObj.add("TranClass", aTran.mTranClass);
        tmpObj.add("CreditAccount", aTran.mCreditAccount);
        tmpObj.add("DebitAccount", aTran.mDebitAccount);
        tmpObj.add("Application", aTran.mApplication);
        tmpObj.add("Customer", aTran.mCustomer);
        tmpObj.add("Amount", aTran.mAmount);
        tmpObj.add("Unit", aTran.mUnit);
        tmpObj.add("Tax", aTran.mTax);
        tmpObj.add("Remarks", aTran.mRemarks);
        tmpObj.add("message", aTran.mmessage);
        tmpObj.add("createDate", aTran.mcreateDate);
        tmpObj.add("updateDate", aTran.mupdateDate);
        tmpObj.add("acl", aTran.macl);

        try {
            tmpObj.save();
        } catch (Exception e){
            e.printStackTrace();
        }
        return aTran;
    }

    /**
     * delete()
     * @param aTran Tran object to delete.
     */
    public static void delete(Tran aTran) {
        NCMBObject tmpObj = new NCMBObject("TeestClass");
        tmpObj.add("objectId", aTran.mobjectId);
        try {
            tmpObj.delete();
            // 成功したSnackBarとか出したいです。;
        } catch(Exception e){
            // 失敗したSnackBarとか出したいです。;
            e.printStackTrace();
        }
    }

    /**
     * update()
     * @param aTran Tran to update.
     */
    public static void update(@NonNull Tran aTran) {
        NCMBObject tmpObj = new NCMBObject("TestClass");

        tmpObj.setObjectId(aTran.mobjectId);
        tmpObj.add("TranDateTime", aTran.mTranDateTime);
        tmpObj.add("TranClass", aTran.mTranClass);
        tmpObj.add("CreditAccount", aTran.mCreditAccount);
        tmpObj.add("DebitAccount", aTran.mDebitAccount);
        tmpObj.add("Application", aTran.mApplication);
        tmpObj.add("Customer", aTran.mCustomer);
        tmpObj.add("Amount", aTran.mAmount.toString());
        tmpObj.add("Unit", aTran.mUnit);
        tmpObj.add("Tax", aTran.mTax);
        tmpObj.add("Remarks", aTran.mRemarks);
        tmpObj.add("message", aTran.mmessage);
//        tmpObj.add("createDate", aTran.mcreateDate);
//        tmpObj.add("updateDate", aTran.mupdateDate);
        tmpObj.setACL(aTran.macl);

        tmpObj.saveInBackground(new SaveCallback() {
            @Override
            public void done(NCMBException e) {
                if (e == null) {
                    // 成功したSnackBarとか出したいです。
                } else {
                    // 失敗したSnackBarとか出したいです。
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * count()
     * @param query Query for count number of items.
     * @return count
     */
    public static int count(@NonNull NCMBQuery<NCMBObject> query) {
        int count = 0;
        try {
            count = query.count();
            // ここはSnackBar要らない。
        } catch(Exception e){
            // 失敗したSnackBarとか出したいです。
            e.printStackTrace();
        }
        return count;
    }

    /**
     * countByTranClass()
     * @param query Query for count of items.
     * @param aTranClass Transaction class
     * @return count
     */
    public static long countByTranClass(@NonNull NCMBQuery<NCMBObject> query, String aTranClass) {
        int count = 0;
        query.whereEqualTo("TranClass", aTranClass);
        try {
            count = query.count();
            // ここはSnackBar要らない。
        } catch(Exception e){
            // 失敗したSnackBarとか出したいです。
            e.printStackTrace();
        }
        return count;
    }

    /**
     *
     * @param query Query for count of all items.
     * @return ITEMS
     */
    public static List<Tran> findAll(@NonNull NCMBQuery<NCMBObject> query) {
        List<NCMBObject> tmpList;
        ITEMS.clear();
        try {
            tmpList = query.find();
            for (NCMBObject obj: tmpList) {
                Tran tmpTran = new Tran();
                tmpTran.initBy(obj);
                ITEMS.add(tmpTran);
            };
        } catch(Exception e){
            // 失敗したSnackBarとか出したいです。
            e.printStackTrace();
        }
//        return ITEMS;

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> result, NCMBException e) {
                if (result != null) {
                    ITEMS.clear();
                    if (!result.isEmpty()) {
                        for (NCMBObject obj: result) {
                            // ITEMS.add(new Tran((Tran)obj));
                            Tran tmpTran = new Tran();
                            tmpTran.initBy(obj);
                            ITEMS.add(tmpTran);
                        };
                    } else {
                        // このクラスのデータがないSnackBarとか出したいです。
                        ;
                    }
                } else {    /* result == null */
                    // 失敗したSnackBarとか出したいです。
                    e.printStackTrace();
                }
            }
        });

        return ITEMS;
    }

    /**
     *
     * @param query Query for find.
     * @param aTranClass Transaction Class for Query.
     * @return ITEMS
     */
    public static List<Tran> findAllByTranClass(@NonNull NCMBQuery<NCMBObject> query, String aTranClass) {
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> result, NCMBException e) {
                if (result != null) {
                    ITEMS.clear();
                    if (!result.isEmpty()) {
                        for (NCMBObject obj: result) {
                            // ITEMS.add(new Tran((Tran)obj));
                            Tran tmpTran = new Tran();
                            tmpTran.initBy(obj);
                            ITEMS.add(tmpTran);
                        };
                    } else {
                        // このクラスのデータがないSnackBarとか出したいです。
                        ;
                    }
                } else {    /* result == null */
                    // 失敗したSnackBarとか出したいです。
                    e.printStackTrace();
                }
            }
        });

        return ITEMS;
    }

}
