package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Khu_Vuc_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listKV;
    EditText edtMaKhu,edtTenKhu;
    String maKhu,tenKhu;
    Button btnThem,btnSua,btnXoa;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khu_vuc);
        edtMaKhu = (EditText) findViewById(R.id.edtMaKhu);
        edtTenKhu = (EditText) findViewById(R.id.edtTenKhu);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        listKV = (ListView) findViewById(R.id.listKV);
        hienThi();
        listKV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                libKhuVuc khuVuc = (libKhuVuc) list.get(i);
                edtMaKhu.setText(khuVuc.maKhu);
                edtTenKhu.setText(khuVuc.tenKhu);
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhu=edtMaKhu.getText().toString();
                tenKhu=edtTenKhu.getText().toString();
                String sql = "INSERT INTO KHUVUC VALUES('"+maKhu+"','"+tenKhu+"')";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Khu_Vuc_Activity.this, "Thêm Khu Vực Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Khu_Vuc_Activity.this, "Không thể thêm Khu Vực ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhu=edtMaKhu.getText().toString();
                tenKhu=edtTenKhu.getText().toString();
                String sql = "UPDATE KHUVUC SET TENKV = '"+tenKhu+"'WHERE MAKV = '"+maKhu+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Khu_Vuc_Activity.this, "Cập Nhật Khu Vực Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Khu_Vuc_Activity.this, "Không thể Cập Nhật Khu Vực ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhu=edtMaKhu.getText().toString();

                String sql = "DELETE FROM KHUVUC WHERE MAKV = '"+maKhu+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Khu_Vuc_Activity.this, "Xóa Khu Vực Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Khu_Vuc_Activity.this, "Không thể Xóa Khu Vực ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });

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
    public void Clear()
    {
        edtMaKhu.setText("");
        edtTenKhu.setText("");
    }
    public void hienThi()
    {
        list = new ArrayList();
        String sql = "SELECT * FROM KHUVUC";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libKhuVuc(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listKV.setAdapter(adapter);
    }


}