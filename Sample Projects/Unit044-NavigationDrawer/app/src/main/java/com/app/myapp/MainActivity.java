package com.app.myapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        // 取得DrawerLayout元件，才能呼叫它的closeDrawers()關閉選單
        mDrawerLayout = findViewById(R.id.drawerLayout);

        // 取得NavigationView元件，然後設定Listener處理按下選單項目的操作
        NavigationView navView = findViewById(R.id.navigationView);
        navView.setNavigationItemSelectedListener(navViewOnItemSelected);

//        ActionBar actBar = getSupportActionBar();
//        actBar.setDisplayHomeAsUpEnabled(true);
//        actBar.setHomeButtonEnabled(true);

        // 取得Toolbar元件，把它設為Action Bar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // 建立ActionBarDrawerToggle，傳入Toolbar元件，並且讓它和DrawerLayout元件偕同運作
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 要先把選單的項目傳給 ActionBarDrawerToggle 處理。
        // 如果它回傳 true，表示處理完成，不需要再繼續往下處理。
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private NavigationView.OnNavigationItemSelectedListener navViewOnItemSelected = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menuItem1:
                    Toast.makeText(MainActivity.this, "選項一", Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.menuItem2:
                    Toast.makeText(MainActivity.this, "選項二", Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.menuItem3:
                    Toast.makeText(MainActivity.this, "選項三", Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawers();
                    break;
            }

            return false;
        }
    };
}
