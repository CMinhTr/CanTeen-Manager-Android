package com.example.ql_cantin;

public class libHoaDon {
    String maHD;
    String maBan;
    String Ngay;
    String maNV;

    public libHoaDon(String maHD,String maBan, String Ngay, String maNV)
    {
        this.maHD=maHD;
        this.maBan=maBan;
        this.Ngay=Ngay;
        this.maNV=maNV;
    }


    public String toString()
    {
        String msg ="";
        msg+="Mã Hóa Đơn: " + this.maHD+"\n";
        msg+="Mã Nhân Viên: "+this.maNV;
        return msg;
    }

}
