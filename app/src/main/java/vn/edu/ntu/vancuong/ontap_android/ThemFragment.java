package vn.edu.ntu.vancuong.ontap_android;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;


import java.util.Calendar;

import vn.edu.ntu.vancuong.ontap_android.controller.IController;
import vn.edu.ntu.vancuong.ontap_android.model.ThongTin;


public class ThemFragment extends Fragment {

    Toolbar toolbar; //Nhớ chọn ...appcompat.widget
    //Nếu toolbar bị đè xuống thì vào res -> values -> styles.xml rồi sửa từ ...LightActionBar thành NoActionBar
    EditText edtngay,edtmua,edtban;
    RadioButton rbusd, rbvnd, rbyen;
    Spinner spinner;
    ImageView imglich;
    Button btnthem, btnxem;

    //Nhớ thêm đủ các controller
    NavController navController;
    IController controller; // Nhớ thêm Icontroller bên main activity

    //Các biến có thể có để lấy dữ liệu
    ThongTin thongTin; //khai báo 1 phần tử trong recycleview để xử lý các hàm thêm và sửa
    String menhgia; //Lấy từ radiogroup

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them, container, false);
        addView(view);
        addEvent();
        return view;
    }
    private void addView(View view) {
        toolbar = view.findViewById(R.id.tbthem);
        toolbar.inflateMenu(R.menu.my_menu);

        edtngay = view.findViewById(R.id.edtngay);
        edtmua = view.findViewById(R.id.edtmua);
        edtban = view.findViewById(R.id.edtban);
        spinner = view.findViewById(R.id.spinner);
        rbusd = view.findViewById(R.id.rbusd);
        rbvnd = view.findViewById(R.id.rbvnd);
        rbyen = view.findViewById(R.id.rbyen);
        imglich = view.findViewById(R.id.imglich);
        btnthem = view.findViewById(R.id.btnthem);
        btnxem = view.findViewById(R.id.btnxem);

        navController = NavHostFragment.findNavController(ThemFragment.this);
        controller = ((MainActivity)getActivity()).controller;
        //Xử lý spinner
        String[] phuongthuc = new String[]{"Tiền mặt","Ngân hàng","Ví điện tử"}; // Khai báo 1 mảng dữ liệu cho spinner

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ThemFragment.this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item,phuongthuc);
        //Tạo ra một adapter cho spinner theo chiều dọc đi xuống (support_simple_spinner_dropdown_item)

        spinner.setAdapter(arrayAdapter);// gắn adapter cho spinner
    }

    private void addEvent() {
        imglich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(year).append("/")
                                .append(month+1).append("/")
                                .append(dayOfMonth);
                        edtngay.setText(builder.toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbusd.isChecked())
                    menhgia = "USD";
                if (rbvnd.isChecked())
                    menhgia = "VND";
                if (rbyen.isChecked())
                    menhgia = "YEN";

                thongTin = new ThongTin(edtngay.getText().toString(), menhgia, spinner.getSelectedItem().toString() //Lấy giá trị từ spinner
                        ,edtmua.getText().toString(),
                        edtban.getText().toString()); //Gán dữ liệu cho biến cần thêm/sửa đã tạo ở trên vào controller
                controller.add(thongTin);
                edtngay.setText("");
                edtban.setText("");
                edtmua.setText("");
            }
        });
        btnxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_themFragment_to_hienThiFragment);
            }
        });
    }
}