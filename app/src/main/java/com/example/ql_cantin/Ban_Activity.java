package com.example.ql_cantin;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import java.util.ArrayList;

public class Ban_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listBan;
    EditText edtMaBan,edtTenBan;
    Spinner spnKhu;
    String maBan,tenBan,maKhu;
    Button btnThem,btnSua,btnXoa;
    ArrayList list,listKhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);
        edtMaBan = (EditText) findViewById(R.id.edtMaBan);
        edtTenBan = (EditText) findViewById(R.id.edtTenBan);
        spnKhu = (Spinner) findViewById(R.id.spnKhu);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        listBan = (ListView) findViewById(R.id.listBan);
        hienThi();
        hienThiSpinner();
        listBan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                libBan ban = (libBan) list.get(i);
                edtMaBan.setText(ban.maBan);
                edtTenBan.setText(ban.tenBan);
                return false;
            }
        });
        spnKhu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                libKhuVuc khuVuc = (libKhuVuc) listKhu.get(i);
                maKhu=khuVuc.maKhu;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Ban_Activity.this, "Hãy chọn 1 Khu", Toast.LENGTH_SHORT).show();

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maBan=edtMaBan.getText().toString();
                tenBan=edtTenBan.getText().toString();

                String sql = "INSERT INTO BAN VALUES('"+maBan+"','"+tenBan+"','"+maKhu+"')";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Ban_Activity.this, "Thêm Bàn Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Ban_Activity.this, "Không thể thêm Bàn ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maBan=edtMaBan.getText().toString();
                tenBan=edtTenBan.getText().toString();
                String sql = "UPDATE BAN SET TENBAN = '"+tenBan+"', MAKV = '"+maKhu+"' WHERE MABAN = '"+maBan+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Ban_Activity.this, "Cập Nhật Bàn Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Ban_Activity.this, "Không thể Cập Nhật Bàn ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maBan=edtMaBan.getText().toString();

                String sql = "DELETE FROM BAN WHERE MABAN = '"+maBan+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Ban_Activity.this, "Xóa Bàn Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Ban_Activity.this, "Không thể Xóa Bàn ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
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
    public void Clear()
    {
        edtMaBan.setText("");
        edtTenBan.setText("");
    }
    public void hienThi()
    {
        list = new ArrayList();
        String sql = "SELECT * FROM BAN";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libBan(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listBan.setAdapter(adapter);
    }
    public void hienThiSpinner(){

        listKhu=new ArrayList();

        String sql="Select * From KHUVUC Order By TENKV";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listKhu.add(new libKhuVuc(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listKhu);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnKhu.setAdapter(adapter);
    }

}