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

public class DangNhapActivity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        Button btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        EditText edtMaNV = (EditText) findViewById(R.id.edtMaNV);
        EditText edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);



        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNV,matKhau;
                maNV=edtMaNV.getText().toString();
                matKhau=edtMatKhau.getText().toString();
                database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
                String sql = "SELECT * FROM NHANVIEN";
                Cursor cursor = database.rawQuery(sql,null);
                if(cursor.getCount()==0)
                {
                    Toast.makeText(DangNhapActivity.this, "Không tồn tại ", Toast.LENGTH_SHORT).show();
                }
                else if (kiemtra(cursor,maNV,matKhau))
                {
                    Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MaNV",maNV);
                    intent.putExtra("Mã Nhân Viên",bundle);
                    edtMaNV.setText("");
                    edtMatKhau.setText("");
                    startActivity(intent);
                }
                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(DangNhapActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Vui lòng kiểm tra lại");
                    builder.show();
                }
            }
        });


    }
    public boolean kiemtra(Cursor cursor,String maNV,String matKhau)
    {

        if(cursor.moveToFirst()){
            do {
                if(cursor.getString(0).equals(maNV))
                {
                    if (cursor.getString(6).equals(matKhau))
                    {
                        return true;
                    }
                    return false;
                }      }while (cursor.moveToNext());

        }
        database.close();



        return false;
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