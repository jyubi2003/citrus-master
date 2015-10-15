package com.dennou_lab.fragment1.data;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dennou_lab.fragment1.MainActivity;
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

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TranListData extends ArrayAdapter<TranContent.TranItem> {

    /**
     * A DataSource TranContent.
     */
    public static TranContent tranContent;

    /**
     * LayoutInflater Object to Create View..
     */
    private LayoutInflater layoutInflater_;

    /**
     * constructor
     */
    public TranListData(Context context, int textViewResourceId, List<TranContent.TranItem> objects){
        super(context, textViewResourceId, objects);

        // Adapter へのデータクラスの登録（でもArrayAdapter向けにはList<TranContent.TranItem> objectsが渡っているんですけどね）
        MainActivity mainActivity = (MainActivity)context;
        tranContent = mainActivity.getTranContent();

        // convertView用に使うインフレーターを生成しておく
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 取引データの読み込みを指示
     */
    public void SetupContent(){
        tranContent.ReadContent(getContext());
    }

    /**
     * 画面へのデータの登録（なんでgetViewなのかね）
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        TranContent.TranItem item = (TranContent.TranItem)tranContent.getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(android.R.layout.simple_list_item_1, null);
        }

        // TranContentのデータをViewのWidgetにセットする
        /*
        ImageView imageView;
        imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getImageData());
        */

        TextView textView;
        textView = (TextView)convertView.findViewById(android.R.id.text1);
        textView.setText(item.toCharSequence());

        return convertView;
    }

}
