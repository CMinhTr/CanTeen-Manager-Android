package com.example.ql_cantin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NhanVienActivity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listNV;
    ArrayList list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        listNV = (ListView) findViewById(R.id.listNV);
        hienthilistNV();
        registerForContextMenu(listNV);

//        Intent call = getIntent();
//        Bundle Package = call.getBundleExtra("Mã Nhân Viên");
//        String maNV = Package.getString("MaNV");
//        TextView txtMaNV = (TextView) findViewById(R.id.txtMaNV);
//        txtMaNV.setText(maNV);


    }
    public boolean doAction(String sql) {
        try
        {
            database = openOrCreateDatabase(nameDB, MODE_PRIVATE,null);
            database.execSQL(sql);
            return true;

        }

        catch(Exception ex){
            return false;
        }
        finally {
            database.close();
        }
    }

    public void hienthilistNV()
    {
        list = new ArrayList();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listNV.setAdapter(adapter);
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        String sql = "SELECT * FROM NHANVIEN";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                    list.add(new libNhanVien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(6)));
            }while (cursor.moveToNext());


        }
        database.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.insert_update_delete_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.them:
                intent = new Intent(NhanVienActivity.this, Insert_NhanVien_Activity.class);
                startActivity(intent);
                return true;
            case R.id.sua:

                libNhanVien nhanVien =(libNhanVien) list.get(0);
                String getMa = nhanVien.maNV;
                intent = new Intent(NhanVienActivity.this, Update_NhanVien_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaNV",getMa);
                intent.putExtra("Mã Nhân Viên",bundle);
                startActivity(intent);
                return true;
            case R.id.xoa:
            default:
                return super.onContextItemSelected(item);
        }
    }
    //    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.insert_update_delete_menu, menu);
//
//        return true;
//    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//            Intent intent;
////        switch (item.getItemId()) {
////            case R.id.them:
////                intent = new Intent(NhanVienActivity.this,Insert_NhanVien_Activity.class);
////                startActivity(intent);
////            return true; case R.id.sua:
////                intent = new Intent(NhanVienActivity.this,Update_NhanVien_Activity.class);
////            startActivity(intent);
////            return true;
////            case R.id.xoa:
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

}