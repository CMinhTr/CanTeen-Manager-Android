package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Insert_Loai_San_Pham_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    EditText edtMaLoai, edtTenLoai;
    Button btnThemLSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_loai_san_pham);
        edtMaLoai = (EditText) findViewById(R.id.edtMaLoai);
        edtTenLoai = (EditText) findViewById(R.id.edtTenLoai);
        btnThemLSP = (Button) findViewById(R.id.btnThemLSP);
        btnThemLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themLSP();
            }
        });







    }


    public void themLSP()
    {
        String MaLoai = edtMaLoai.getText().toString();
        String TenLoai = edtTenLoai.getText().toString();

        String sql = "INSERT INTO LOAISANPHAM  VALUES('"+MaLoai+"','"+TenLoai+"')";
        if(doAction(sql)==true)
        {

            Toast.makeText(Insert_Loai_San_Pham_Activity.this,"Thêm Loại Sản Phẩm thành công",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(Insert_Loai_San_Pham_Activity.this,"Không thể thêm Loại Sản Phẩm",Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(Insert_Loai_San_Pham_Activity.this,LoaiSanPhamActivity.class);
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