package com.app.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 把項目清單準備好，放在一個List物件裏頭
        List<String> listStr = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            listStr.add(new String("第" + String.valueOf(i+1) + "項"));

        // 取得介面佈局檔中的RecyclerView元件
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 設定RecyclerView使用的LayoutManager，
        // LayoutManager決定項目的排列方式。
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//      recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
      recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                 StaggeredGridLayoutManager.VERTICAL));

        // 建立RecyclerView的Adapter物件，傳入包含項目清單的List物件
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listStr);

        // 把Adapter物件傳給RecyclerView
        recyclerView.setAdapter(adapter);
    }
}
