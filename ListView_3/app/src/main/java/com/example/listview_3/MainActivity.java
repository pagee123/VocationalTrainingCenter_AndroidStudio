package com.example.listview_3;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listViewData;
    private String[] foodName;
    private int[] foodPrice;
    private TypedArray foodPic;
    private List<Map<String,Object>> listData;
    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listViewData = (ListView)findViewById(R.id.listView_id);
        foodName = getResources().getStringArray(R.array.food_name);
        foodPrice = getResources().getIntArray(R.array.food_price);
        foodPic = getResources().obtainTypedArray(R.array.food_pic);

        listData = new ArrayList<>();
        for(int i = 0;i<foodName.length;i++){
            Map<String,Object> data = new HashMap<>();
            data.put("name",foodName[i]);
            data.put("price",foodPrice[i]);
            data.put("pic",foodPic.getResourceId(i,0));
            listData.add(data);
        }

        Log.d("main","listdata="+listData);

        adapter = new SimpleAdapter(MainActivity.this,listData,R.layout.item_layout,new String[]{"name","price","pic"},
                new int[]{R.id.textView_itemName,R.id.textView_itemPrice,R.id.imageView_itemPic});

        listViewData.setAdapter(adapter);

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                setTitle(item.get("name").toString()+" - $"+item.get("price"));
            }
        });
    }
}