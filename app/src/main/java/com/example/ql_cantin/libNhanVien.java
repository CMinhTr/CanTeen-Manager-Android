package com.example.ql_cantin;

public class libNhanVien {
    String maNV;
    String hoTen;
    String gioiTinh;
    String passWord;

    public libNhanVien(String maNV, String hoTen, String gioiTinh, String passWord){
        this.maNV=maNV;
        this.hoTen=hoTen;
        this.gioiTinh=gioiTinh;
        this.passWord=passWord;
    }

    public String toString()
    {
        String msg ="";
        msg+="Mã Nhân Viên: " + this.maNV+"\n";
        msg+="Họ Tên: "+this.hoTen+" | ";
        msg+="Giới Tính: "+this.gioiTinh;
        msg+="\nMật Khẩu "+this.passWord;
        return msg;

    }
}
