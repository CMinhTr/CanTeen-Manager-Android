package com.example.ql_cantin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonActivity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    EditText edtMaHD,edtSoLuong;
    TextView Ngay,MaNV,txtTong,txtGiaBan;
    Spinner spnBan,spnSP;
    ListView listChiTiet;
    String maHD,maBan,ngay,maNV,maSP;
    Button btnBack,btnLapHD,btnThem;
    ArrayList list,listBan,listSP;
    int soLuong,donGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        edtMaHD = (EditText) findViewById(R.id.edtMaHD);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        MaNV = (TextView) findViewById(R.id.txtMaNV);
        Ngay = (TextView) findViewById(R.id.txtNgay);
        txtTong = (TextView) findViewById(R.id.txtTong);
        txtGiaBan = (TextView) findViewById(R.id.txtGiaBan);
        spnBan = (Spinner) findViewById(R.id.spnBan);
        spnSP = (Spinner) findViewById(R.id.spnSP);
        listChiTiet = (ListView) findViewById(R.id.listChiTiet);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnLapHD = (Button) findViewById(R.id.btnLapHD);
        btnThem = (Button) findViewById(R.id.btnThem);
        Intent call = getIntent();
        Bundle Package = call.getBundleExtra("Mã Nhân Viên");
        maNV = Package.getString("MaNV");
        MaNV.setText(maNV);
        hienThiSpinnerBan();
        hienThiSpinnerSP();
        ngay = String.valueOf(java.time.LocalDate.now());
        Ngay.setText(ngay);
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String maSP = spnSP.getSelectedItem().toString();
//                String sql = "SELECT DONGIA FROM SANPHAM WHERE MASP='" + maSP + "'";
//                database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
//                Cursor tongTien = database.rawQuery(sql,null);
//
//
//                soLuong=Integer.parseInt(edtSoLuong.getText().toString());
//                donGia=Integer.parseInt(txtGiaBan.getText().toString());
//                maHD=edtMaHD.getText().toString();
//                long tongTien = soLuong*donGia;
//                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                txtTong.setText(decimalFormat.format(tongTien) + "Đ");
//
//                String sql = "INSERT INTO CHITIETBANHANG VALUES('"+maHD+"','"+maSP+"','"+soLuong+"','"+donGia+"')";
//                if(doAction(sql)==true)
//                {
//                    Toast.makeText(HoaDonActivity.this, "Thêm Chi Tiết Bán Hàng Thành Công", Toast.LENGTH_SHORT).show();
//                }
//                else{Toast.makeText(HoaDonActivity.this, "Không thể thêm Chi Tiết Bán Hàng ", Toast.LENGTH_SHORT).show();
//                }
//                hienThi();
//            }
//        });
//        listChiTiet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                libChiTietHoaDon chiTiet = (libChiTietHoaDon) list.get(i);
//                edtMaHD.setText(chiTiet.maHD);
//                edtSoLuong.setText(chiTiet.soLuong);
//                txtGiaBan.setText(chiTiet.giaBan);
//
//                return false;
//            }
//        });
        spnSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                libSanPham sanPham = (libSanPham) listSP.get(i);
                maSP=sanPham.maSP;
                donGia= Integer.parseInt(sanPham.donGia);
                String giaBan = String.valueOf(donGia);
                txtGiaBan.setText(giaBan.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(HoaDonActivity.this, "Hãy chọn 1 Sản Phẩm", Toast.LENGTH_SHORT).show();

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soLuong=Integer.parseInt(edtSoLuong.getText().toString());
                donGia=Integer.parseInt(txtGiaBan.getText().toString());
                maHD=edtMaHD.getText().toString();
                String sql = "INSERT INTO CHITIETBANHANG VALUES('"+maHD+"','"+maSP+"','"+soLuong+"','"+donGia+"')";
                if(doAction(sql)==true)
                {
                    Toast.makeText(HoaDonActivity.this, "Thêm Chi Tiết Bán Hàng Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(HoaDonActivity.this, "Không thể thêm Chi Tiết Bán Hàng ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
            }
        });
        btnLapHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoaDonActivity.this,Chi_Tiet_Hoa_Don_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaHD",maHD);
                intent.putExtra("Mã Hóa Đơn",bundle);
                startActivity(intent);
                String maBan = spnBan.getSelectedItem().toString();
                String maNV = MaNV.getText().toString();
            }
        });
        spnBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                libBan ban = (libBan) listBan.get(i);
                maBan=ban.maBan;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(HoaDonActivity.this, "Hãy chọn 1 Bàn", Toast.LENGTH_SHORT).show();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private int thanhToan(String maHD)
    {
//
//            int SoLuong =Integer.parseInt(spnLSP.getSelectedItem().toString());
//            long tongTien = SoLuong * giaCT;
//            list.add(new libChiTietHoaDon(maHD,maSP,soLuong,donGia));
//            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//            txtTong.setText(decimalFormat.format(tongTien) + "Đ");
        String sql = "SELECT * FROM CHITIETBANHANG WHERE MAHD = '"+maHD+"'";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor tongTien = database.rawQuery(sql,null);
        int thanhTien = 0;
        int items = tongTien.getCount();
        for(int i = 0;i<items;i++)
        {
            if(tongTien.moveToPosition(i))
            {
                int giaBan = (int)tongTien.getFloat(3);
                int soLuong = tongTien.getInt(2);
                thanhTien+=(soLuong*giaBan);

            }

        }
        tongTien.close();
        return thanhTien;

    }
    public void hienThi()
    {
        list = new ArrayList();
        String sql = "SELECT * FROM CHITIETBANHANG WHERE MAHD = '"+maHD+"'";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libChiTietHoaDon(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listChiTiet.setAdapter(adapter);
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
    public void hienThiSpinnerBan(){

        listBan =new ArrayList();

        String sql="Select * From BAN Order By TENBAN";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listBan.add(new libBan(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listBan);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnBan.setAdapter(adapter);
    }
    public void hienThiSpinnerSP(){

        listSP=new ArrayList();

        String sql="Select * From SANPHAM Order By TENSP";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listSP.add(new libSanPham(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listSP);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnSP.setAdapter(adapter);
    }


}