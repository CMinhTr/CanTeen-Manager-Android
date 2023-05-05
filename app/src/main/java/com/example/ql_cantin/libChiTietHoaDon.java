package com.example.ql_cantin;

public class libChiTietHoaDon {
    String maHD;
    String maSP;
    int soLuong;
    int giaBan;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public libChiTietHoaDon(String maHD, String maSP, int soLuong, int giaBan)
    {
        this.maHD=maHD;
        this.maSP=maSP;
        this.soLuong=soLuong;
        this.giaBan=giaBan;
    }
    public String toString()
    {
        String msg ="";
        msg+="Sản Phẩm: " + this.maSP+"\n";
        msg+="Số Lượng: " + this.soLuong+"\n";
        msg+="Giá: "+this.giaBan;
        return msg;
    }

}
