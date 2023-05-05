package com.example.ql_cantin;

public class libSanPham {
    String maSP;
    String tenSP;
    String dVT;
    String donGia;
    String maLoai;

    public libSanPham(String maSP, String tenSP, String dVT,String donGia,String maLoai){
        this.maSP=maSP;
        this.tenSP=tenSP;
        this.dVT=dVT;
        this.donGia=donGia;
        this.maLoai=maLoai;
    }


    public String toString()
    {
        String msg ="";
        msg+="Mã Sản Phẩm: " + this.maSP+"\n";
        msg+="Tên: "+this.tenSP+"\n";
        msg+="Giá: "+this.donGia;
        return msg;
    }


}
