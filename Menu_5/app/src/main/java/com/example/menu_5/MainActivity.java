package com.example.menu_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewData;
    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView naviView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.drawer_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        textViewData = (TextView) findViewById(R.id.textView_data);
        textViewData.setText("");
        myDrawerLayout = (DrawerLayout)findViewById((R.id.drawerLayout_id));

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,myDrawerLayout,R.string.open,R.string.close);
        myDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        naviView = (NavigationView) findViewById(R.id.navigation_id);
        naviView.setItemIconTintList(null);
        naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.item_1:
                        textViewData.setText("Item 1");
                        break;
                    case R.id.item_2:
                        textViewData.setText("Item 2");
                        break;
                    case R.id.item_3:
                        textViewData.setText("Item 3");
                        break;
                }
                myDrawerLayout.close();
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}