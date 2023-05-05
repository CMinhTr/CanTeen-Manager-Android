package com.example.ql_cantin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoiMKActivity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mk);
        Button btnDangKy = (Button) findViewById(R.id.btnDangKy);
        Button btnLKDangNhap = (Button) findViewById(R.id.btnLKDangNhap);
        EditText edtMaNV = (EditText) findViewById(R.id.edtMaNV);
        EditText edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);

        Intent call = getIntent();
        Bundle Package = call.getBundleExtra("Mã Nhân Viên");
        String maNV = Package.getString("MaNV");
        edtMaNV.setText(maNV);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNV;
                String matKhau = "";
                maNV=edtMaNV.getText().toString();
                matKhau=edtMatKhau.getText().toString();

                    String sql1 = "Select * From NHANVIEN WHERE MANV = '"+maNV+"' AND MATKHAU = '"+matKhau+"'";
                    database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
                    Cursor cursor = database.rawQuery(sql1,null);
                    if(cursor.getCount()==0)
                    {
                        String sql2 = "Update NHANVIEN Set MATKHAU = '"+matKhau+"' Where  MANV = '"+maNV+"'";
                        if(doAction(sql2)==true)
                        {
                            Toast.makeText(DoiMKActivity.this, "Đỗi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(DoiMKActivity.this, "Đỗi mật khẩu [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(DoiMKActivity.this, "Đỗi mật khẩu [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                        edtMaNV.findFocus();
                    }
                }

        });
        btnLKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoiMKActivity.this,DangNhapActivity.class);
                startActivity(intent);

            }
        });
    }

    public boolean doAction(String sql) {
        try
        {
            database = openOrCreateDatabase(nameDB, MODE_PRIVATE,null);
            database.execSQL(sql);
            return true;

        } catch(Exception ex){
            return false;
        }
        finally {
            database.close();
        }
    }
}