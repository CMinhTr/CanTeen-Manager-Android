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
import android.widget.Toast;

import java.util.ArrayList;

public class Nha_Cung_Cap_Activity extends AppCompatActivity {
    String nameDB = "QL_CANTIN.db";
    SQLiteDatabase database;
    ListView listNCC;
    EditText edtMaNCC,edtTenNCC,edtsoDT;
    String maNCC,tenNCC,soDT;
    Button btnThem,btnSua,btnXoa;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_cung_cap);
        edtMaNCC = (EditText) findViewById(R.id.edtMaNCC);
        edtTenNCC = (EditText) findViewById(R.id.edtTenNCC);
        edtsoDT = (EditText) findViewById(R.id.edtsoDT);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        listNCC = (ListView) findViewById(R.id.listNCC);

        hienThi();
        listNCC.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                libNhaCungCap nCC = (libNhaCungCap) list.get(i);
                edtMaNCC.setText(nCC.maNCC);
                edtTenNCC.setText(nCC.tenNCC);
                edtsoDT.setText(nCC.soDT);
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maNCC=edtMaNCC.getText().toString();
                tenNCC=edtTenNCC.getText().toString();
                soDT=edtsoDT.getText().toString();
                String sql = "INSERT INTO NHACUNGCAP VALUES('"+maNCC+"','"+tenNCC+"','"+soDT+"')";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Nha_Cung_Cap_Activity.this, "Thêm Nhà Cung Cấp Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Nha_Cung_Cap_Activity.this, "Không thể thêm Nhà Cung Cấp ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maNCC=edtMaNCC.getText().toString();
                tenNCC=edtTenNCC.getText().toString();
                soDT=edtsoDT.getText().toString();
                String sql = "UPDATE NHACUNGCAP SET TENNCC = '"+tenNCC+"', SDT = '"+soDT+"' WHERE MANCC = '"+maNCC+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Nha_Cung_Cap_Activity.this, "Cập Nhật Nhà Cung Cấp Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Nha_Cung_Cap_Activity.this, "Không thể Cập Nhật Nhà Cung Cấp ", Toast.LENGTH_SHORT).show();
                }
                hienThi();
                Clear();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maNCC=edtMaNCC.getText().toString();

                String sql = "DELETE FROM NHACUNGCAP WHERE MANCC = '"+maNCC+"'";
                if(doAction(sql)==true)
                {
                    Toast.makeText(Nha_Cung_Cap_Activity.this, "Xóa Nhà Cung Cấp Thành Công", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(Nha_Cung_Cap_Activity.this, "Không thể Xóa Nhà Cung Cấp ", Toast.LENGTH_SHORT).show();
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
        edtMaNCC.setText("");
        edtTenNCC.setText("");
        edtsoDT.setText("");
    }
    public void hienThi()
    {
        list = new ArrayList();
        String sql = "SELECT * FROM NHACUNGCAP";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                list.add(new libNhaCungCap(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listNCC.setAdapter(adapter);
    }


}