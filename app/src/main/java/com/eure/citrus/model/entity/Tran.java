package com.eure.citrus.model.entity;

import com.nifty.cloud.mb.NCMBObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import android.text.Html;

/**
 * A data item representing a piece of content.
 */
public class Tran {
    public String objectId;
    public Date TranDateTime;
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


    public Tran() {
    }

    public Tran(NCMBObject content) {
        this.objectId = content.getString("objectId");
        this.TranDateTime = content.getDate("TranDateTime");
        this.CreditAccount = content.getString("CreditAccount");
        this.DebitAccount = content.getString("DebitAccount");
        this.Application = content.getString("Application");
        this.Customer = content.getString("Customer");
        this.Amount = new BigDecimal(content.getString("Amount"));
        this.Unit = content.getString("Unit");
        this.Tax = content.getString("Tax");
        this.Remarks = content.getString("Remarks");
        this.message = content.getString("message");
        this.createDate = content.getDate("createDate");
        this.updateDate = content.getDate("updateDate");
        this.acl = content.getString("acl");
    }

    /**
     * public String toString()
     * description インスタンスの内容を文字列で返す
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
     * description HTML タグ付き文字列の作成
     */
    public CharSequence toCharSequence() {
        //
        String html = "<p><font color=#000000>" + DateFormat.getDateInstance().format(TranDateTime) + "<small>JST</small> </font> " +
                "<font color=#000000>" + CreditAccount + " </font>" +
                "<font color=#000000>" + DebitAccount + " </font>" +
                "<font color=#000000>&yen;" + Amount.toString() + "円 </font>" +
                "<font color=#000000>税 &yen;" + Tax.toString() + "円 </font><br>" +
                "<font color=#000000>" + Application + " </font>" +
                "<font color=#000000>" + Customer + "様 </font><br></p>";
        // fromHtml() の引数にタグ付き文字列を渡す
        // CharSequence source = Html.fromHtml(html);
        CharSequence source = Html.fromHtml(html);
        return source;
    }
}
