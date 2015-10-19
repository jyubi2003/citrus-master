package com.eure.citrus.model.entity;

import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMBObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import android.text.Html;

import static java.lang.String.*;

/**
 * A data item representing a piece of content.
 */
public class Tran extends NCMBObject {

    public String objectId;
    public Date TranDateTime;
    public String TranClass;
    public String CreditAccount;
    public String DebitAccount;
    public String Application;
    public String Customer;
    public BigDecimal Amount;
    public String Unit;
    public String Tax;
    public String Remarks;
    public String message;
    public Date createDate;
    public Date updateDate;
    public String acl;

    /**
     * public Tran()
     * @param classname Path the classname
     * @return Tran
     * description Default Constructor
     */
    public Tran(String classname) {
        super(classname);
    }

    /**
     * public Tran(NCMBObject content)
     * @param aTran Path a Tran object.
     * @return Tran
     * description Copy Constructor
     */
    public Tran(Tran aTran) {
        super(aTran.getClassName());

        add("objectId", aTran.objectId);
        add("TranDateTime", aTran.TranDateTime);
        add("TranClass", aTran.TranClass);
        add("CreditAccount", aTran.CreditAccount);
        add("DebitAccount", aTran.DebitAccount);
        add("Application", aTran.Application);
        add("Customer", aTran.Customer);
        add("Amount" , aTran.Amount);
        add("Unit", aTran.Unit);
        add("Tax", aTran.Tax);
        add("Remarks", aTran.Remarks);
        add("message", aTran.message);
        add("createDate", aTran.createDate);
        add("updateDate", aTran.updateDate);
        add("acl", aTran.acl);
    }

    /**
     * public String toString()
     * @return String
     * description Return the object by String.
     */
    @Override
    public String toString() {
        CharSequence source = toCharSequence();
        StringBuffer outString = new StringBuffer();
        outString.append(source);
        return outString.toString();
    }

    /**
     * public CharSequence toCaarSequence()
     * @return CharSequence
     * description Return the object by HTML String.
     */
    public CharSequence toCharSequence() {
        //
        String html = "<p><font color=#000000>" + DateFormat.getDateInstance().format(TranDateTime) + "<small>JST</small> </font> " +
                "<font color=#000000>" + TranClass + " </font>" +
                "<font color=#000000>" + CreditAccount + " </font>" +
                "<font color=#000000>" + DebitAccount + " </font>" +
                "<font color=#000000>&yen;" + Amount.toString() + "円 </font>" +
                "<font color=#000000>税 &yen;" + Tax + "円 </font><br>" +
                "<font color=#000000>" + Application + " </font>" +
                "<font color=#000000>" + Customer + "様 </font><br></p>";
        // fromHtml() の引数にタグ付き文字列を渡す
        // CharSequence source = Html.fromHtml(html);
        CharSequence source = Html.fromHtml(html);
        return source;
    }

    public void saveInBackground(FindCallback<NCMBObject> findCallback) {

    }
}
