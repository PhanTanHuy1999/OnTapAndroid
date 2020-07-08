package vn.edu.ntu.ontapbai1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


public class hienthiFragment extends Fragment {
    TextView hienthi;
    Button btnthoat;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hienthi, container, false);
        addview(view);
        data();
        addevent();
        return view ;
    }

    private void addevent() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_dienthongtinFragment_to_hienthiFragment);
            }
        });
    }

    private void addview(View view) {
        hienthi = view.findViewById(R.id.hienthi);
        btnthoat = view.findViewById(R.id.btnthoat);
        navController = NavHostFragment.findNavController(hienthiFragment.this);
    }

    private void data() {
        Bundle data = getArguments();
        String str = "Chúc mừng khách hàng : "+data.getString("ten")+"\nNgày sinh"
                +data.getString("ngaysinh :")+ "\nĐã đăng ký thành công dịch vụ: "+ data.getString("dichvu")
                +"\nPhương thức thanh toán của bạn là:"+ data.getString("phuongthuc") +"\nChúng tôi sẽ liên lạc với bạn qua số điện thoại:"
                + data.getString("sdt");
        hienthi.setText(str);
    }
}
