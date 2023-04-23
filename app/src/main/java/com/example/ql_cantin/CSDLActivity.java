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
        String sql = "CREATE TABLE NHAPHANG(MANH NVARCHAR(10) PRIMARY KEY NOT NULL,NGAYNHAP DATETIME,MANCC NVARCHAR(10),MANV NVARCHAR(10),FOREIGN KEY(MANCC) REFERENCES NHACUNGCAP (MANCC),FOREIGN KEY(MANV)REFERENCES NHANVIEN(MANV))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Nhập Hàng thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblChitietNhapHang()
    {
        String sql = "CREATE TABLE CHITIETNHAPHANG(MANH  NVARCHAR(10) PRIMARY KEY NOT NULL,MASP INT NOT NULL,SOLUONG INT,GIANHAP FLOAT,FOREIGN KEY(MANH) REFERENCES NHAPHANG (MANH),FOREIGN KEY(MASP) REFERENCES SANPHAM(MASP))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Chi Tiết Nhập Hàng thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblHoaDon()
    {
        String sql = "CREATE TABLE HOADONBH(MAHD  NVARCHAR(10) PRIMARY KEY NOT NULL,NGAYBH DATETIME,MABAN NVARCHAR(10),MANV NVARCHAR(10),FOREIGN KEY (MABAN) REFERENCES BAN(MABAN),FOREIGN KEY(MANV)REFERENCES NHANVIEN(MANV))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Hóa Đơn thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblChitietHD()
    {
        String sql = "CREATE TABLE CHITIETBANHANG(MAHD  NVARCHAR(10) NOT NULL,MASP INT NOT NULL,SOLUONG INT,GIABAN FLOAT, PRIMARY KEY (MAHD,MASP),FOREIGN KEY(MAHD) REFERENCES HOADONBH (MAHD),FOREIGN KEY(MASP) REFERENCES SANPHAM(MASP))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Chi Tiết Hóa Đơn thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblLSP()
    {
        String sql = "CREATE TABLE LOAISANPHAM(MALOAI NVARCHAR(10) PRIMARY KEY NOT NULL,TENLOAI NVARCHAR(50) NOT NULL)";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Loại Sản Phẩm thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblKV()
    {
        String sql = "CREATE TABLE KHUVUC(MAKV NVARCHAR(10) PRIMARY KEY NOT NULL,TENKV NVARCHAR(20) NOT NULL)";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Khu Vực thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblBan()
    {
        String sql = "CREATE TABLE BAN(MABAN NVARCHAR(10) PRIMARY KEY NOT NULL,TENBAN NVARCHAR(20) NOT NULL,MAKV NVARCHAR(10),FOREIGN KEY (MAKV) REFERENCES KHUVUC (MAKV) )";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Bàn thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblSP()
    {
        String sql = "CREATE TABLE SANPHAM(MASP  NVARCHAR(10) PRIMARY KEY NOT NULL,TENSP NVARCHAR(20) NOT NULL,DONVITINH NVARCHAR(10),DONGIA INT,MALOAI INT, FOREIGN KEY (MALOAI) REFERENCES LOAISANPHAM(MALOAI))";
        if(doAction(sql)==true){
            Toast.makeText(CSDLActivity.this,"Tạo Table Sản Phẩm thành công!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CSDLActivity.this,"Không thể tạp Table",Toast.LENGTH_SHORT).show();
        }
    }
    public void createTblNCC()
    {
        String sql = "CREATE TABLE NHACUNGCAP(MANCC NVARCHAR(10) PRIMARY KEY NOT NULL,TENNCC NVARCHAR (20) NOT NULL,SDT NVARCHAR(10) )";
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