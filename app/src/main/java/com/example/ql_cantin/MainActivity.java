package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDangNhap = (Button) findViewById(R.id.button);
        Button btnDangKy = (Button) findViewById(R.id.button2);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDN = new Intent(MainActivity.this,DangNhapActivity.class);
                startActivity(intentDN);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDK = new Intent(MainActivity.this,DangKyActivity.class);
                startActivity(intentDK);
            }
        });

    }
}