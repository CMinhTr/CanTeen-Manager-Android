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

public class San_Pham_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listSP;
    EditText edtMaSP,edtTenSP,edtDVT,edtdonGia;
    Spinner spnLSP;
    String maSP,tenSP,donVT,donGia,maLoai;
    Button btnThem,btnSua,btnXoa;
    ArrayList list,listLSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);

        edtMaSP = (EditText) findViewById(R.id.edtMaSP);
        edtTenSP = (EditText) findViewById(R.id.edtTenSP);
        edtDVT = (EditText) findViewById(R.id.edtDVT);
        edtdonGia = (EditText) findViewById(R.id.edtdonGia);
        spnLSP = (Spinner) findViewById(R.id.spnLSP);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        listSP = (ListView) findViewById(R.id.listSP);
        hienThi();
        hienThiSpinnerMACD();

        listSP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                libSanPham sanPham = (libSanPham) list.get(i);
                edtMaSP.setText(sanPham.maSP);
                edtTenSP.setText(sanPham.tenSP);
                edtDVT.setText(sanPham.dVT);
                edtdonGia.setText(sanPham.donGia);
                return false;
            }
        });
        spnLSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                libLoaiSanPham loaiSP = (libLoaiSanPham) listLSP.get(i);
                maLoai=loaiSP.maLoai;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(San_Pham_Activity.this, "Hãy chọn 1 Loại Sản Phẩm", Toast.LENGTH_SHORT).show();

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSP=edtMaSP.getText().toString();
                tenSP=edtTenSP.getText().toString();
                donVT=edtDVT.getText().toString();
                donGia=edtdonGia.getText().toString();

                String sql = "INSERT INTO SANPHAM VALUES('"+maSP+"','"+tenSP+"','"+donVT+"','"+donGia+"','"+maLoai+"')";
                if(doAction(sql)==true)
                {
                    Toast.makeText(San_Pham_Activity.this, "Thêm Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(San_Pham_Activity.this, "Không thể thêm Sản Phẩm ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSP=edtMaSP.getText().toString();
                tenSP=edtTenSP.getText().toString();
                donVT=edtDVT.getText().toString();
                donGia=edtdonGia.getText().toString();
                String sql = "UPDATE SANPHAM SET TENSP = '"+tenSP+"',DONVITINH = '"+donVT+"', DONGIA = '"+donGia+"', MALOAI = '"+maLoai+"' WHERE MASP = '"+maSP+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(San_Pham_Activity.this, "Cập Nhật Loại Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(San_Pham_Activity.this, "Không thể Cập Nhật Loại Sản Phẩm ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maSP=edtMaSP.getText().toString();

                String sql = "DELETE FROM SANPHAM WHERE MASP = '"+maSP+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(San_Pham_Activity.this, "Xóa Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(San_Pham_Activity.this, "Không thể Xóa Sản Phẩm ", Toast.LENGTH_SHORT).show();
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
        edtMaSP.setText("");
        edtTenSP.setText("");
        edtdonGia.setText("");
        edtDVT.setText("");

    }
    public void hienThi()
    {
        list = new ArrayList();
        String sql = "SELECT * FROM SANPHAM";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libSanPham(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listSP.setAdapter(adapter);
    }
    public void hienThiSpinnerMACD(){

        listLSP=new ArrayList();

        String sql="Select * From LOAISANPHAM Order By TENLOAI";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listLSP.add(new libLoaiSanPham(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listLSP);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnLSP.setAdapter(adapter);
    }

}