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
import android.widget.Toast;

import java.util.ArrayList;

public class Loai_San_Pham_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listLSP;
    EditText edtMaLoai,edtTenLoai;
    String maLoai,tenLoai;
    Button btnThem,btnSua,btnXoa;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);
        edtMaLoai = (EditText) findViewById(R.id.edtMaLoai);
        edtTenLoai = (EditText) findViewById(R.id.edtTenLoai);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        listLSP = (ListView) findViewById(R.id.listLSP);
        hienThi();
        listLSP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                libLoaiSanPham loaiSP = (libLoaiSanPham) list.get(i);
                edtMaLoai.setText(loaiSP.maLoai);
                edtTenLoai.setText(loaiSP.tenLoai);
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maLoai=edtMaLoai.getText().toString();
                tenLoai=edtTenLoai.getText().toString();
                    String sql = "INSERT INTO LOAISANPHAM VALUES('"+maLoai+"','"+tenLoai+"')";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Loai_San_Pham_Activity.this, "Thêm Loại Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Loai_San_Pham_Activity.this, "Không thể thêm Loại Sản Phẩm ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maLoai=edtMaLoai.getText().toString();
                tenLoai=edtTenLoai.getText().toString();
                String sql = "UPDATE LOAISANPHAM SET TENLOAI = '"+tenLoai+"'WHERE MALOAI = '"+maLoai+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Loai_San_Pham_Activity.this, "Cập Nhật Loại Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Loai_San_Pham_Activity.this, "Không thể Cập Nhật Loại Sản Phẩm ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maLoai=edtMaLoai.getText().toString();

                String sql = "DELETE FROM LOAISANPHAM WHERE MALOAI = '"+maLoai+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Loai_San_Pham_Activity.this, "Xóa Loại Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Loai_San_Pham_Activity.this, "Không thể Xóa Loại Sản Phẩm ", Toast.LENGTH_SHORT).show();
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
        edtMaLoai.setText("");
        edtTenLoai.setText("");
    }
    public void hienThi()
    {
        list = new ArrayList();
        String sql = "SELECT * FROM LOAISANPHAM";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libLoaiSanPham(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listLSP.setAdapter(adapter);
    }


}