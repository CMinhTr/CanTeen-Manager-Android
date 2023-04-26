package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CSDLActivity extends AppCompatActivity {

    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdlactivity);

        Button btnDeleteDB = (Button) findViewById(R.id.btnDeleteDB);
        Button btnCreateBD = (Button) findViewById(R.id.btnCreateBD);
        Button btnCreateAllTbl = (Button) findViewById(R.id.btnCreateAllTbl);

        btnCreateBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBD();
            }
        });
        btnDeleteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDB();
            }
        });
        //Tạo tất cả các bảng
        btnCreateAllTbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTblLSP(); //Bảng Loại Sản Phẩm
                createTblSP(); //Bảng Sản Phẩm
                createTblNV(); //Bảng Nhân Viên
                createTblNCC(); //Bảng Nhà Cung Cấp
                createTblKV(); //Bảng Khu Vực
                createTblBan(); //Bảng Bàn
                createTblHoaDon(); //Bảng Hóa Đơn
                createTblChitietHD(); //Bảng Chi Tiết Hóa Đơn
                createTblNhapHang(); //Bảng Nhập Hàng
                createTblChitietNhapHang(); //Bảng Chi Tiết Nhập Hàng
                inSertNhanVien();
            }
        });
        Button btnMain = (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSDLActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
    public void inSertNhanVien()
    {
        String sql = "INSERT INTO NHANVIEN VALUES('Admin','Tran Cong Minh','Nam',11-25-2002,'TP Cà Mau','0931031467','admin','admin')";
        if(doAction(sql)==true)
        {

            Toast.makeText(CSDLActivity.this,"Thêm Nhân Viên thành công",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(CSDLActivity.this,"Không thể thêm Nhân Viên",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblNhapHang()
    {
        String sql = "CREATE TABLE NHAPHANG(MANH TEXT PRIMARY KEY,NGAYNHAP DATETIME,MANCC TEXT,MANV TEXT,FOREIGN KEY(MANCC) REFERENCES NHACUNGCAP (MANCC),FOREIGN KEY(MANV)REFERENCES NHANVIEN(MANV))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Nhập Hàng thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblChitietNhapHang()
    {
        String sql = "CREATE TABLE CHITIETNHAPHANG(MANH  TEXT PRIMARY KEY ,MASP TEXT,SOLUONG INT,GIANHAP FLOAT,FOREIGN KEY(MANH) REFERENCES NHAPHANG (MANH),FOREIGN KEY(MASP) REFERENCES SANPHAM(MASP))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Chi Tiết Nhập Hàng thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblHoaDon()
    {
        String sql = "CREATE TABLE HOADONBH(MAHD  TEXT PRIMARY KEY ,NGAYBH DATETIME,MABAN TEXT,MANV TEXT,FOREIGN KEY (MABAN) REFERENCES BAN(MABAN),FOREIGN KEY(MANV)REFERENCES NHANVIEN(MANV))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Hóa Đơn thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblChitietHD()
    {
        String sql = "CREATE TABLE CHITIETBANHANG(MAHD  TEXT,MASP TEXT,SOLUONG INT,GIABAN FLOAT, PRIMARY KEY (MAHD,MASP),FOREIGN KEY(MAHD) REFERENCES HOADONBH (MAHD),FOREIGN KEY(MASP) REFERENCES SANPHAM(MASP))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Chi Tiết Hóa Đơn thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblLSP()
    {
        String sql = "CREATE TABLE LOAISANPHAM(MALOAI TEXT PRIMARY KEY,TENLOAI TEXT)";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Loại Sản Phẩm thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblKV()
    {
        String sql = "CREATE TABLE KHUVUC(MAKV TEXT PRIMARY KEY,TENKV TEXT )";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Khu Vực thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblBan()
    {
        String sql = "CREATE TABLE BAN(MABAN TEXT PRIMARY KEY,TENBAN TEXT,MAKV TEXT,FOREIGN KEY (MAKV) REFERENCES KHUVUC (MAKV) )";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Bàn thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblSP()
    {
        String sql = "CREATE TABLE SANPHAM(MASP  TEXT PRIMARY KEY  ,TENSP TEXT,DONVITINH TEXT,DONGIA NUMBER,MALOAI NUMBER, FOREIGN KEY (MALOAI) REFERENCES LOAISANPHAM(MALOAI))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Sản Phẩm thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblNCC()
    {
        String sql = "CREATE TABLE NHACUNGCAP(MANCC TEXT PRIMARY KEY ,TENNCC TEXTL,SDT TEXT )";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Nhà Cung Cấp thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblNV()
    {
        String sql = "CREATE TABLE NHANVIEN(MANV TEXT  PRIMARY KEY,HOTENNV TEXT ,GIOITINH TEXT," +
                "NGAYSINH TEXT,DIACHI TEXT,SDT INT,MATKHAU TEXT,PHANQUYEN TEXT)";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Nhân Viên thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createBD()
    {
        try {
            database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
            Toast.makeText(CSDLActivity.this,"Tạo Cơ Sở Dữ Liệu Thành Công!!!",Toast.LENGTH_LONG).show();

        }catch (Exception ex){
            Toast.makeText(CSDLActivity.this,"Không Thể Tạo Cơ Sở Dữ Liệu!!!",Toast.LENGTH_LONG).show();
        }
    }
    public void deleteDB()
    {
        try {
            if(deleteDatabase(nameDB)==true)
            {

                Toast.makeText(CSDLActivity.this,"Xóa Cơ Sở Dữ Liệu Thành Công!!!",Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Không Thể Xóa Cơ Sở Dữ Liệu",Toast.LENGTH_LONG).show();
        }
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

}