package com.eure.citrus.model.entity;

import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMBACL;
import com.nifty.cloud.mb.NCMBObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import android.text.Html;

import static java.lang.String.*;

/**
 * A data item representing a piece of content.
 */
public class Tran{

    public String mClassName;
    public String mobjectId;
    public Date mTranDateTime;
    public String mTranClass;
    public String mCreditAccount;
    public String mDebitAccount;
    public String mApplication;
    public String mCustomer;
    public BigDecimal mAmount;
    public String mUnit;
    public String mTax;
    public String mRemarks;
    public String mmessage;
    public Date mcreateDate;
    public Date mupdateDate;
    public NCMBACL macl;

    /**
     * public Tran()
     * description Default Constructor
     */
    public Tran() {
        mClassName = "TestClass";
    }

    /**
     * public Tran(Tran aTran)
     * @param aTran Path a Tran object.
     * description Copy Constructor
     */
    public Tran(Tran aTran) {
        mClassName = aTran.mClassName;

        mobjectId = aTran.mobjectId;
        mTranDateTime = aTran.mTranDateTime;
        mTranClass =  aTran.mTranClass;
        mCreditAccount = aTran.mCreditAccount;
        mDebitAccount = aTran.mDebitAccount;
        mApplication = aTran.mApplication;
        mCustomer = aTran.mCustomer;
        mAmount = aTran.mAmount;
        mUnit = aTran.mUnit;
        mTax = aTran.mTax;
        mRemarks = aTran.mRemarks;
        mmessage = aTran.mmessage;
        mcreateDate = aTran.mcreateDate;
        mupdateDate = aTran.mupdateDate;
        macl = aTran.macl;
    }

    /**
     * public void initBy(NCMBObject anObj)
     * @param anObj Path a NCMBObject object.
     * @return void
     * description Copy Constructor
     */
    public void initBy(NCMBObject anObj) {
        if (anObj.getClassName() == "TestClass") {
            mobjectId = anObj.getObjectId();
            mTranDateTime = anObj.getDate("TranDateTime");
            mTranClass = anObj.getString("TranClass");
            mCreditAccount = anObj.getString("CreditAccount");
            mDebitAccount = anObj.getString("DebitAccount");
            mApplication = anObj.getString("Application");
            mCustomer = anObj.getString("Customer");
            mAmount = new BigDecimal(anObj.getString("Amount"));
            mUnit = anObj.getString("Unit");
            mTax = anObj.getString("Tax");
            mRemarks = anObj.getString("Remarks");
            mmessage = anObj.getString("message");
            mcreateDate = anObj.getCreatedAt();
            mupdateDate = anObj.getUpdatedAt();
            macl = anObj.getACL();
        } else {
            // どうやってエラー返そうかな
            ;
        }
    }

    /**
     * public String toString()
     * @return String
     * description Return the object by String.
     */
    @Override
    public String toString() {
        return mApplication;
        /*
        CharSequence source = toCharSequence();
        StringBuffer outString = new StringBuffer();
        outString.append(source);
        return outString.toString();
        */
    }

    /**
     * public CharSequence toCaarSequence()
     * @return CharSequence
     * description Return the object by HTML String.
     */
    public CharSequence toCharSequence() {
        //
        String html = "<p><font color=#000000>" + DateFormat.getDateInstance().format(mTranDateTime + "<small>JST</small> </font> " +
                "<font color=#000000>" + mTranClass + " </font>" +
                "<font color=#000000>" + mCreditAccount + " </font>" +
                "<font color=#000000>" + mDebitAccount + " </font>" +
                "<font color=#000000>&yen;" + mAmount.toString() + "円 </font>" +
                "<font color=#000000>税 &yen;" + mTax + "円 </font><br>" +
                "<font color=#000000>" + mApplication + " </font>" +
                "<font color=#000000>" + mCustomer + "様 </font><br></p>");
        //CharSequence source = Html.fromHtml(html);
        return Html.fromHtml(html);
    }
}
