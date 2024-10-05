package com.app.myapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String DB_FILE = "friends.db",
                                DB_TABLE = "friends";

    private FriendDbOpenHelper mFriendDbOpenHelper;

    private EditText mEdtName,
                    mEdtSex,
                    mEdtAddr;

    private TextView mTxtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFriendDbOpenHelper =
                new FriendDbOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        SQLiteDatabase friendDb = mFriendDbOpenHelper.getWritableDatabase();

        // 檢查資料表是否已經存在
        Cursor cursor = friendDb.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                        DB_TABLE + "'", null);

        if(cursor != null) {
            if(cursor.getCount() == 0)	// 沒有資料表，需要建立一個資料表
                friendDb.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "sex TEXT," +
                        "address TEXT);");

            cursor.close();
        }

        friendDb.close();

        mEdtName = findViewById(R.id.edtName);
        mEdtSex = findViewById(R.id.edtSex);
        mEdtAddr = findViewById(R.id.edtAddr);
        mTxtList = findViewById(R.id.txtList);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnQuery = findViewById(R.id.btnQuery);
        Button btnList = findViewById(R.id.btnList);

        btnAdd.setOnClickListener(btnAddOnClick);
        btnQuery.setOnClickListener(btnQueryOnClick);
        btnList.setOnClickListener(btnListOnClick);
    }

    private View.OnClickListener btnAddOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase friendDb = mFriendDbOpenHelper.getWritableDatabase();

            ContentValues newRow = new ContentValues();
            newRow.put("name", mEdtName.getText().toString());
            newRow.put("sex", mEdtSex.getText().toString());
            newRow.put("address", mEdtAddr.getText().toString());
            friendDb.insert(DB_TABLE, null, newRow);

            friendDb.close();
        }
    };

    private View.OnClickListener btnQueryOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase friendDb = mFriendDbOpenHelper.getWritableDatabase();

            Cursor c = null;

            if (!mEdtName.getText().toString().equals("")) {
                c = friendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "name=" + "\"" + mEdtName.getText().toString()
                        + "\"", null, null, null, null, null);
            } else if (!mEdtSex.getText().toString().equals("")) {
                c = friendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "sex=" + "\"" + mEdtSex.getText().toString()
                        + "\"", null, null, null, null, null);
            } else if (!mEdtAddr.getText().toString().equals("")) {
                c = friendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, "address=" + "\"" + mEdtAddr.getText().toString()
                        + "\"", null, null, null, null, null);
            }

            if (c == null)
                return;

            if (c.getCount() == 0) {
                mTxtList.setText("");
                Toast.makeText(MainActivity.this, "沒有這筆資料", Toast.LENGTH_LONG)
                        .show();
            } else {
                c.moveToFirst();
                mTxtList.setText(c.getString(0) + c.getString(1)  + c.getString(2));

                while (c.moveToNext())
                    mTxtList.append("\n" + c.getString(0) + c.getString(1) +
                            c.getString(2));
            }

            friendDb.close();
        }
    };

    private View.OnClickListener btnListOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase friendDb = mFriendDbOpenHelper.getWritableDatabase();

            Cursor c = friendDb.query(true, DB_TABLE, new String[]{"name", "sex",
                    "address"}, 	null, null, null, null, null, null);

            if (c == null)
                return;

            if (c.getCount() == 0) {
                mTxtList.setText("");
                Toast.makeText(MainActivity.this, "沒有資料", Toast.LENGTH_LONG)
                        .show();
            }
            else {
                c.moveToFirst();
                mTxtList.setText(c.getString(0) + c.getString(1)  + c.getString(2));

                while (c.moveToNext())
                    mTxtList.append("\n" + c.getString(0) + c.getString(1)  +
                            c.getString(2));
            }

            friendDb.close();
        }
    };
}
