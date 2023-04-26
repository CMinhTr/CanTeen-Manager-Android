package com.example.ql_cantin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Nhan_Vien_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listNV;
    ArrayList list;
    EditText edtMaNV,edtHoTen,edtNgaySinh,edtDiaChi,edtSDT,edtMatKhau;
    RadioButton rdtGioiTinh,rdtPhanQuyen;
    Button btnThemNV,btnSuaNV,btnXoaNV;
    String MaNV,HoTen,NgaySinh,DiaChi,SDT,MatKhau,GioiTinh,PhanQuyen;
    RadioGroup rdgGioiTinh,rdgPhanQuyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        edtMaNV = (EditText) findViewById(R.id.edtMaNV);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnThemNV = (Button) findViewById(R.id.btnThemNV);
        btnSuaNV = (Button) findViewById(R.id.btnSuaNV);
        btnXoaNV = (Button) findViewById(R.id.btnXoaNV);
        listNV = (ListView) findViewById(R.id.listNV);
        hienthilistNV();
        listNV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                libNhanVien nv = (libNhanVien) list.get(i);
                edtMaNV.setText(nv.maNV);
                edtHoTen.setText(nv.hoTen);
                edtNgaySinh.setText(nv.ngaySinh);
                edtDiaChi.setText(nv.diaChi);
                edtSDT.setText(nv.soDT);
                edtMatKhau.setText(nv.passWord);
                return false;
            }
        });
        btnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themNV();
            }
        });
        btnSuaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaNV();
            }
        });
        btnXoaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoaNV();
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

    public void hienthilistNV()
    {
        list = new ArrayList();
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        String sql = "SELECT * FROM NHANVIEN";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libNhanVien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }while (cursor.moveToNext());


        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listNV.setAdapter(adapter);

    }
    public void themNV()
    {

        MaNV = edtMaNV.getText().toString();
        HoTen = edtHoTen.getText().toString();
        NgaySinh = edtNgaySinh.getText().toString();
        DiaChi = edtDiaChi.getText().toString();
        SDT = edtSDT.getText().toString();
        MatKhau = edtMatKhau.getText().toString();
        rdgGioiTinh =(RadioGroup) findViewById(R.id.rdgGioiTinh);
        rdgPhanQuyen =(RadioGroup) findViewById(R.id.rdgPhanQuyen);
        int idCheckGT = rdgGioiTinh.getCheckedRadioButtonId();
        rdtGioiTinh = findViewById(idCheckGT);
        int idCheckPQ = rdgPhanQuyen.getCheckedRadioButtonId();
        rdtPhanQuyen = findViewById(idCheckPQ);
        GioiTinh = rdtGioiTinh.getText().toString();
        PhanQuyen = rdtPhanQuyen.getText().toString();
        edtMatKhau.setText(GioiTinh);

        String sql = "INSERT INTO NHANVIEN VALUES('"+MaNV+"','"+HoTen+"','"+GioiTinh+"','"+NgaySinh+"','"+DiaChi+"','"+SDT+"','"+MatKhau+"','"+PhanQuyen+"')";
        if(doAction(sql)==true)
        {

            Toast.makeText(Nhan_Vien_Activity.this,"Thêm Nhân Viên thành công",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(Nhan_Vien_Activity.this,"Không thể thêm Nhân Viên",Toast.LENGTH_SHORT).show();
        }
        database.close();
        hienthilistNV();
        Clear();

    }
    public void suaNV()
    {
        MaNV = edtMaNV.getText().toString();
        HoTen = edtHoTen.getText().toString();
        NgaySinh = edtNgaySinh.getText().toString();
        DiaChi = edtDiaChi.getText().toString();
        SDT = edtSDT.getText().toString();
        MatKhau = edtMatKhau.getText().toString();
        rdgGioiTinh =(RadioGroup) findViewById(R.id.rdgGioiTinh);
        rdgPhanQuyen =(RadioGroup) findViewById(R.id.rdgPhanQuyen);
        int idCheckGT = rdgGioiTinh.getCheckedRadioButtonId();
        rdtGioiTinh = findViewById(idCheckGT);
        int idCheckPQ = rdgPhanQuyen.getCheckedRadioButtonId();
        rdtPhanQuyen = findViewById(idCheckPQ);
        GioiTinh = rdtGioiTinh.getText().toString();
        PhanQuyen = rdtPhanQuyen.getText().toString();
        String sql = "UPDATE NHANVIEN SET HOTENNV = '"+HoTen+"',GIOITINH = '"+GioiTinh+"', NGAYSINH = '"+NgaySinh+"',DIACHI = '"+DiaChi+"',SDT = '"+SDT+"',MATKHAU = '"+MatKhau+"', PHANQUYEN = '"+PhanQuyen+"' WHERE MANV = '"+MaNV+"'";
        if(doAction(sql)==true)
        {

            Toast.makeText(Nhan_Vien_Activity.this,"Cập Nhật Nhân Viên thành công",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(Nhan_Vien_Activity.this,"Không thể Cập Nhật  Nhân Viên",Toast.LENGTH_SHORT).show();
        }
        hienthilistNV();
        Clear();
        database.close();
    }
    public void xoaNV()
    {
        MaNV = edtMaNV.getText().toString();
        String sql = "DELETE FROM NHANVIEN WHERE MANV = '"+MaNV+"'";
        if(doAction(sql)==true){
            Toast.makeText(Nhan_Vien_Activity.this,"Xóa Nhân Viên thành công",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Nhan_Vien_Activity.this,"Không thể Xóa Nhân Viên",Toast.LENGTH_SHORT).show();
        }
        database.close();
       hienthilistNV();
        Clear();
    }
    public void Clear()
    {
        edtMaNV.setText("");
        edtHoTen.setText("");
        edtNgaySinh.setText("");
        edtDiaChi.setText("");
        edtSDT.setText("");
        edtMatKhau.setText("");
    }
}
