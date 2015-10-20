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

/*    public String objectId;
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
*/
    /**
     * public Tran()
     * @param classname Path the classname
     * description Default Constructor
     */
    public Tran(String classname) {
        super(classname);
    }

    /**
     * public Tran(Tran aTran)
     * @param aTran Path a Tran object.
     * description Copy Constructor
     */
    public Tran(Tran aTran) {
        super(aTran.getClassName());

        add("objectId", aTran.get("objectId"));
        add("TranDateTime", aTran.get("TranDateTime"));
        add("TranClass", aTran.get("TranClass"));
        add("CreditAccount", aTran.get("CreditAccount"));
        add("DebitAccount", aTran.get("DebitAccount"));
        add("Application", aTran.get("Application"));
        add("Customer", aTran.get("Customer"));
        add("Amount" , aTran.get("Amount"));
        add("Unit", aTran.get("Unit"));
        add("Tax", aTran.get("Tax"));
        add("Remarks", aTran.get("Remarks"));
        add("message", aTran.get("message"));
        add("createDate", aTran.get("createDate"));
        add("updateDate", aTran.get("updateDate"));
        add("acl", aTran.get("acl"));
    }

    /**
     * public void setupBy(NCMBObject anObj)
     * @param anObj Path a NCMBObject object.
     * @return void
     * description Copy Constructor
     */
    public Tran(NCMBObject anObj) {
        super(anObj.getClassName());
        add("objectId", anObj.get("objectId"));
        add("TranDateTime", anObj.get("TranDateTime"));
        add("TranClass", anObj.get("TranClass"));
        add("CreditAccount", anObj.get("CreditAccount"));
        add("DebitAccount", anObj.get("DebitAccount"));
        add("Application", anObj.get("Application"));
        add("Customer", anObj.get("Customer"));
        add("Amount", anObj.get("Amount"));
        add("Unit", anObj.get("Unit"));
        add("Tax", anObj.get("Tax"));
        add("Remarks", anObj.get("Remarks"));
        add("message", anObj.get("message"));
        add("createDate", anObj.get("createDate"));
        add("updateDate", anObj.get("updateDate"));
        add("acl", anObj.get("acl"));
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
        String html = "<p><font color=#000000>" + DateFormat.getDateInstance().format(get("TranDateTime") + "<small>JST</small> </font> " +
                "<font color=#000000>" + get("TranClass") + " </font>" +
                "<font color=#000000>" + get("CreditAccount") + " </font>" +
                "<font color=#000000>" + get("DebitAccount") + " </font>" +
                "<font color=#000000>&yen;" + get("Amount").toString() + "円 </font>" +
                "<font color=#000000>税 &yen;" + get("Tax") + "円 </font><br>" +
                "<font color=#000000>" + get("Application") + " </font>" +
                "<font color=#000000>" + get("Customer") + "様 </font><br></p>");
        //CharSequence source = Html.fromHtml(html);
        return Html.fromHtml(html);
    }
}
