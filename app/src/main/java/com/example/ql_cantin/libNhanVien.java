package com.example.ql_cantin;

public class libNhanVien {
    String maNV;
    String hoTen;
    String gioiTinh;
    String passWord;
    String ngaySinh;
    String diaChi;
    String soDT;





    public libNhanVien(String maNV, String hoTen, String gioiTinh, String passWord,String ngaySinh,String diaChi,String soDT){
        this.maNV=maNV;
        this.hoTen=hoTen;
        this.gioiTinh=gioiTinh;
        this.ngaySinh=passWord;
        this.diaChi=ngaySinh;
        this.soDT=diaChi;
        this.passWord=soDT;
    }

    public String toString()
    {
        String msg ="";
        msg+="Mã Nhân Viên: " + this.maNV+"\n";
        msg+="Họ Tên: "+this.hoTen+" | ";
        msg+="Giới Tính: "+this.gioiTinh;
//        msg+="Ngày Sinh: "+this.ngaySinh;
//        msg+="Địa Chỉ: "+this.diaChi;
//        msg+="Số Điện Thoại: "+this.soDT;
        msg+="\nMật Khẩu "+this.passWord;
        return msg;

    }
}
