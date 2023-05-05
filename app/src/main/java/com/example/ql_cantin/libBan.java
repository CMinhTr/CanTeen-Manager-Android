package com.example.ql_cantin;

public class libBan {
    String maBan;
    String tenBan;
    String maKhu;

    public libBan(String maBan, String tenBan, String maKhu){
        this.maBan=maBan;
        this.tenBan=tenBan;
        this.maKhu=maKhu;
    }

    public String toString()
    {
        String msg ="";
        msg+="Mã Bàn: " + this.maBan+"\n";
        msg+="Tên Bàn: "+this.tenBan+"\n";
        msg+="Mã Khu: "+this.maKhu;
        return msg;
    }
}
