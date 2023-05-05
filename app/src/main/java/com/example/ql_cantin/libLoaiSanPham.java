package com.example.ql_cantin;

public class libLoaiSanPham {
    String maLoai;
    String tenLoai;

    public libLoaiSanPham(String maLoai,String tenLoai)
    {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public String toString()
    {
        String msg ="";
        msg+="Mã Loại: " + this.maLoai+"\n";
        msg+="Tên Loại: "+this.tenLoai;

        return msg;

    }

}

