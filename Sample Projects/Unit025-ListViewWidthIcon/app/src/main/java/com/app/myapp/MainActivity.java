package com.app.myapp;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String ITEM_TITLE = "Item title";
    private static final String ITEM_ICON = "Item icon";

    private TextView mTxtR;
    private ListView mListViewRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtR = findViewById(R.id.txtR);
        mListViewRegion = findViewById(R.id.listViewRegion);

        String[] regionList = getResources().getStringArray(R.array.region_list);
        TypedArray regionIconList =
                getResources().obtainTypedArray(R.array.region_icon_list);
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < regionList.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put(ITEM_TITLE, regionList[i]);
            item.put(ITEM_ICON, regionIconList.getResourceId(i, 0));
            itemList.add(item);
        }

        SimpleAdapter simAdapListViewRegion = new SimpleAdapter(
                MainActivity.this, itemList,
                R.layout.list_view_item,
                new String[] {ITEM_TITLE, ITEM_ICON},
                new int[] {R.id.txtView, R.id.imgView});
        mListViewRegion.setAdapter(simAdapListViewRegion);

        mListViewRegion.setOnItemClickListener(listViewRegionOnItemClick);
    }

    private AdapterView.OnItemClickListener listViewRegionOnItemClick
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String s = getString(R.string.region_selected);
            TextView txtView = view.findViewById(R.id.txtView);
            mTxtR.setText(s + txtView.getText());
        }
    };
}
