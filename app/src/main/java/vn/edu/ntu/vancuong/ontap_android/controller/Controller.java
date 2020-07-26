package vn.edu.ntu.vancuong.ontap_android.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.vancuong.ontap_android.model.ThongTin;

public class Controller extends Application implements IController {
    List<ThongTin> thongTinList;
    public Controller(){//Nhớ khởi tạo Array list
        thongTinList = new ArrayList<>();
    }
    public Controller(List<ThongTin> thongTins) {
        this.thongTinList = thongTins;
    }

    @Override
    public List<ThongTin> getAllThongTin() {
        return thongTinList;
    }

    @Override
    public void add(ThongTin thongTin) {
        thongTinList.add(thongTin);
    }
}
