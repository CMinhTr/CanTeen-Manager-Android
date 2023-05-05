package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent call = getIntent();
        Bundle Package = call.getBundleExtra("Mã Nhân Viên");
        String maNV = Package.getString("MaNV");
        TextView txtMaNV = (TextView) findViewById(R.id.txtMaNV);
        txtMaNV.setText(maNV);
        Button btnhanvien = (Button) findViewById(R.id.btnhanvien);
        Button btnloaisanpham = (Button) findViewById(R.id.btnloaisanpham);
        Button btnNCC = (Button) findViewById(R.id.btnNCC);
        Button btnSanPham = (Button) findViewById(R.id.btnSanPham);
        Button btnKhuVuc = (Button) findViewById(R.id.btnKhuVuc);
        Button btnBan = (Button) findViewById(R.id.btnBan);
        Button btnHD = (Button) findViewById(R.id.btnHD);
        Button btnDOIMK = (Button) findViewById(R.id.btnDOIMK);
        btnDOIMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DoiMKActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaNV",maNV);
                intent.putExtra("Mã Nhân Viên",bundle);
                startActivity(intent);
            }
        });


        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,HoaDonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaNV",maNV);
                intent.putExtra("Mã Nhân Viên",bundle);
                startActivity(intent);
            }
        });
        btnBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Ban_Activity.class);
                startActivity(intent);
            }
        });
        btnKhuVuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Khu_Vuc_Activity.class);
                startActivity(intent);
            }
        });
        btnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Nhan_Vien_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("MaNV",maNV);
//                intent.putExtra("Mã Nhân Viên",bundle);
                startActivity(intent);
            }
        });
        btnloaisanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Loai_San_Pham_Activity.class);
                startActivity(intent);
            }
        });
        btnNCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Nha_Cung_Cap_Activity.class);
                startActivity(intent);
            }
        });
        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,San_Pham_Activity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionMenu(Menu menu)
    {
       getMenuInflater().inflate(R.menu.toolbar_menu,menu);
       return  true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.mnuNhanVien:
                intent = new Intent(MainActivity.this,Nhan_Vien_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("MaNV",maNV);
//                intent.putExtra("Mã Nhân Viên",bundle);
                startActivity(intent);
                return true;
            case R.id.mnuNCC:
                intent = new Intent(MainActivity.this,Nha_Cung_Cap_Activity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}