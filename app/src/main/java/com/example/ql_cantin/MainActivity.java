package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnhanvien = (Button) findViewById(R.id.btnhanvien);
        Intent call = getIntent();
        Bundle Package = call.getBundleExtra("Mã Nhân Viên");
        String maNV = Package.getString("MaNV");
        TextView txtMaNV = (TextView) findViewById(R.id.txtMaNV);
        txtMaNV.setText(maNV);
        btnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,NhanVienActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaNV",maNV);
                intent.putExtra("Mã Nhân Viên",bundle);
                startActivity(intent);
            }
        });

    }
}