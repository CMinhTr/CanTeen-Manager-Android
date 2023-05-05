public class libKhuVuc {

    String maKhu;
    String tenKhu;
    public libKhuVuc(String maKhu,String tenKhu)
    {
        this.maKhu=maKhu;
        this.tenKhu=tenKhu;
    }

    @Override
    public String toString() {
        String msg ="";
        msg+="Mã Khu: " + this.maKhu+"\n";
        msg+="Tên Khu: "+this.tenKhu+"\n";
        return msg;
    }
}
