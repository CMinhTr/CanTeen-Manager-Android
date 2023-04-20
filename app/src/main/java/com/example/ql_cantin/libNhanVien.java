package com.example.ql_cantin;

public class libNhanVien {
    String maNV;
    String hoTen;
    String gioiTinh;
    public libNhanVien(String maNV, String hoTen, String gioiTinh){
        this.maNV=maNV;
        this.hoTen=hoTen;
        this.gioiTinh=gioiTinh;
    }

    public String toString()
    {
        String msg ="";
        msg+="Mã Nhân Viên: " + this.maNV+"\n";
        msg+="Họ Tên: "+this.hoTen+" | ";
        msg+="Giới Tính "+this.gioiTinh;
        return msg;

    }
}
