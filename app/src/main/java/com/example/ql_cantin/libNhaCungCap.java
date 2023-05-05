package com.example.ql_cantin;

public class libNhaCungCap {
    String maNCC;
    String tenNCC;
    String soDT;
    public libNhaCungCap(String maNCC,String tenNCC,String soDT)
    {
        this.maNCC=maNCC;
        this.tenNCC=tenNCC;
        this.soDT=soDT;
    }
    public String toString()
    {
        String msg ="";
        msg+="Mã Nhà Cung Cấp: " + this.maNCC+"\n";
        msg+="Tên Nhà Cung Cấp: "+this.tenNCC+"\n";
        msg+="Số Điện Thoại: "+this.soDT+"\n";

        return msg;

    }

}
