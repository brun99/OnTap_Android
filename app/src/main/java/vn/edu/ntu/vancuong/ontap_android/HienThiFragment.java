package vn.edu.ntu.vancuong.ontap_android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.vancuong.ontap_android.controller.IController;
import vn.edu.ntu.vancuong.ontap_android.model.ThongTin;


public class HienThiFragment extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;
    NavController navController;
    IController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hien_thi, container, false);
        setHasOptionsMenu(true);
        addView(view);
        addEvent();
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
//    Cách thêm sự kiện cho menu toolbar ẩn

//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id)
//        {
//            case R.id.mnuexit: FirstFragment.this.getActivity().finish();
//            case R.id.mnumuahang: showShoppingCart();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //Cách thêm menu cho toolbar bị ẩn
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addView(View view) {
        toolbar = view.findViewById(R.id.tbds);
        recyclerView = view.findViewById(R.id.rcvlist);

        navController = NavHostFragment.findNavController(HienThiFragment.this);
        controller = ((MainActivity)getActivity()).controller;

        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Cho phép xuất hiện nút back
        toolbar.setNavigationIcon(R.drawable.ic_action_back);

        recyclerView.setLayoutManager(new LinearLayoutManager(HienThiFragment.this.getActivity()));
        ThongTinAdapter adapter = new ThongTinAdapter(controller.getAllThongTin());
        recyclerView.setAdapter(adapter);
}

    private void addEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_hienThiFragment_to_themFragment);
            }
        });
    }
    private class ThongTinViewHolder extends RecyclerView.ViewHolder{
        //khai bao cac bien trong 1 thanh phan viewholder(phan tu lap di lap lai) cua adapter

        TextView txthienthi;

        public ThongTinViewHolder(@NonNull View itemView) {
            super(itemView);
            txthienthi = itemView.findViewById(R.id.txthienthi);
            //anh xa cac bien vao itemView (View duoc tao rieng cho viewholder)

        }
        public void bind(ThongTin tin){
            txthienthi.setText(tin.getSoNgay() + "\n" + tin.getMenhGia() + "\n" +
                    tin.getPhuongThuc() + "\n" + "Mua vào:" + tin.getMuaVao() + "bán ra:" + tin.getBanRa());
            //truyen du lieu vao cac thanh phan trong viewholder tu list trong adater--
        }
    }
    private class ThongTinAdapter extends RecyclerView.Adapter<ThongTinViewHolder>{
        List<ThongTin> thongTinList;


        public ThongTinAdapter(List<ThongTin> listInfos) {
            this.thongTinList = listInfos;
        }
        @NonNull
        @Override
        public ThongTinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater  = getLayoutInflater();
            View view = inflater.inflate(R.layout.thongtin,parent,false);
            return new ThongTinViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ThongTinViewHolder holder, int position) {
            holder.bind(thongTinList.get(position));
        }

        @Override
        public int getItemCount() {
            return thongTinList.size();
        }
    }
}