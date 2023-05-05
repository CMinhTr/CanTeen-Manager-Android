package com.example.ql_cantin;

public class libKhuVuc {

    String maKhu;
    String tenKhu;

    public libKhuVuc(String maKhu,String tenKhu)
    {
        this.maKhu = maKhu;
        this.tenKhu = tenKhu;
    }

    public String toString()
    {
        String msg ="";
        msg+="Mã Khu: " + this.maKhu+"\n";
        msg+="Tên Khu: "+this.tenKhu;

        return msg;

    }
}
