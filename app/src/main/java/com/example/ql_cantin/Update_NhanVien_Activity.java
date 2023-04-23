package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Update_NhanVien_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    Button btnSuaNV;
    RadioButton rdtGioiTinh,rdtPhanQuyen;

    EditText edtMaNV,edtHoTen,edtNgaySinh,edtDiaChi,edtSDT,edtMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nhan_vien);
        edtMaNV = (EditText) findViewById(R.id.edtMaNV);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnSuaNV = (Button) findViewById(R.id.btnSuaNV);
        Intent call = getIntent();
        Bundle Package = call.getBundleExtra("Mã Nhân Viên");
        String maNV = Package.getString("MaNV");
        edtMaNV = (EditText) findViewById(R.id.edtMaNV);
        edtMaNV.setText(maNV);

       btnSuaNV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               suaNV();
           }
       });

    }

    public void suaNV()
    {
        String MaNV = edtMaNV.getText().toString();
        String HoTen = edtHoTen.getText().toString();
        String NgaySinh = edtNgaySinh.getText().toString();
        String DiaChi = edtDiaChi.getText().toString();
        String SDT = edtSDT.getText().toString();
        String MatKhau = edtMatKhau.getText().toString();
        RadioGroup rdgGioiTinh =(RadioGroup) findViewById(R.id.rdgGioiTinh);
        RadioGroup rdgPhanQuyen =(RadioGroup) findViewById(R.id.rdgPhanQuyen);

        int idCheckGT = rdgGioiTinh.getCheckedRadioButtonId();
        rdtGioiTinh = findViewById(idCheckGT);
        int idCheckPQ = rdgPhanQuyen.getCheckedRadioButtonId();
        rdtPhanQuyen = findViewById(idCheckPQ);
        String GioiTinh = rdtGioiTinh.getText().toString();
        String PhanQuyen = rdtPhanQuyen.getText().toString();


        String sql = "UPDATE NHANVIEN SET HOTENNV = '"+HoTen+"',GIOITINH = '"+GioiTinh+"',NGAYSINH = '"+NgaySinh+"',DIACHI = '"+DiaChi+"',SDT = '"+SDT+"',MATKHAU = '"+MatKhau+"',PHANQUYEN = '"+PhanQuyen+"' WHERE MANV = '"+MaNV+"'";
        if(doAction(sql)==true)
        {

            Toast.makeText(Update_NhanVien_Activity.this,"Cập Nhật Nhân Viên thành công",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(Update_NhanVien_Activity.this,"Không thể Cập Nhật Nhân Viên",Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(Update_NhanVien_Activity.this,NhanVienActivity.class);
        startActivity(intent);
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