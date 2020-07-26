package vn.edu.ntu.vancuong.ontap_android.controller;

import java.util.List;

import vn.edu.ntu.vancuong.ontap_android.model.ThongTin;

public interface IController {
    public List<ThongTin> getAllThongTin();
    public void add(ThongTin thongTin);
}
