package com.dennou_lab.fragment1.data;

import com.nifty.cloud.mb.FindCallback;
import com.nifty.cloud.mb.NCMB;
import com.nifty.cloud.mb.NCMBException;
import com.nifty.cloud.mb.NCMBObject;
import com.nifty.cloud.mb.NCMBQuery;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TranContent {

    /**
     * An array of TranData items.
     */
    public static List<TranItem> ITEMS = new ArrayList<TranItem>();

    /**
     * A map of TranData items, by ID.
     */
    public static Map<String, TranItem> ITEM_MAP = new HashMap<String, TranItem>();

    /**
     * constructor
     */
    public TranContent(Context context){
    }

    /**
     * Read Content
     */
    public void ReadContent(Context context){

        NCMB.initialize(context, "2d2329a32f2b834527eb0d2a301032f2a27df4dd6814f95cb7e966a6f832020c", "cbf48ac77f3edfff2319c4185d7def9e11aa62d38b6bd5c38570d114bf25afb9");

        NCMBQuery<NCMBObject> query = NCMBQuery.getQuery("TestClass");
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
    }

    /**
     * add item
     */
    private static void addItem(TranItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.objectId, item);
    }

    /**
     * get item
     */
    public TranContent.TranItem getItem(int position) {
        return ITEMS.get(position);
    }

    /**
     * clear item
     */
    private static void clrItem() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    /**
     * post data
     */
    private void PostData(){
        NCMBObject TestClass = new NCMBObject("TestClass");
        TestClass.put("message", "Hello, NCMB!");
        TestClass.saveInBackground();
    }

    /*
    private void dispMessage(NCMBObject message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mParent);

        alertDialogBuilder.setTitle("データ取得");
        alertDialogBuilder.setMessage(message.getString("message"));
        alertDialogBuilder.show();
    }
    */

    /**
     * A data item representing a piece of content.
     */
    public static class TranItem {
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


        public TranItem(NCMBObject content) {
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
}
