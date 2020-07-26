package vn.edu.ntu.vancuong.ontap_android.model;

public class ThongTin {
    String soNgay, menhGia, phuongThuc, muaVao, banRa;

    public ThongTin(String soNgay, String menhGia, String phuongThuc, String muaVao, String banRa) {
        this.soNgay = soNgay;
        this.menhGia = menhGia;
        this.phuongThuc = phuongThuc;
        this.muaVao = muaVao;
        this.banRa = banRa;
    }

    public ThongTin() {
    }

    public String getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(String soNgay) {
        this.soNgay = soNgay;
    }

    public String getMenhGia() {
        return menhGia;
    }

    public void setMenhGia(String menhGia) {
        this.menhGia = menhGia;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public String getMuaVao() {
        return muaVao;
    }

    public void setMuaVao(String muaVao) {
        this.muaVao = muaVao;
    }

    public String getBanRa() {
        return banRa;
    }

    public void setBanRa(String banRa) {
        this.banRa = banRa;
    }
}
