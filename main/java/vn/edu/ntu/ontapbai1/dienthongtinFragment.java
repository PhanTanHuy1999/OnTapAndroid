package vn.edu.ntu.ontapbai1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;


public class dienthongtinFragment extends Fragment {

    EditText edtten, edtngaysinh, edtsdt, edtdiachi;
    ImageView imglich;
    RadioButton bttm, btnganhang, btvdt;
    Button btxacnhan;
    Spinner spinner;

    String ten, ngaysinh,  diachi, phuongthuc, dichvu ,sdt;;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dienthongtin, container, false);
        
        addView(view);
        data();
        addevent();
        return view;
    }
    private void addView(View view) {
        edtten = view.findViewById(R.id.edtten);
        edtdiachi = view.findViewById(R.id.edtdiachi);
        edtngaysinh = view.findViewById(R.id.edtngaysinh);
        edtsdt = view.findViewById(R.id.edtsdt);
        imglich = view.findViewById(R.id.imglich);
        btnganhang= view.findViewById(R.id.btnganhang);
        bttm= view.findViewById(R.id.bttienmat);
        btvdt = view.findViewById(R.id.btvdt);
        spinner = view.findViewById(R.id.spiner);
        btxacnhan= view.findViewById(R.id.btxacnhan);

        navController = NavHostFragment.findNavController(dienthongtinFragment.this);
    }
    // Hành Dộng
    private void addevent() {
        btxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = edtten.getText().toString();
                ngaysinh = edtngaysinh.getText().toString();
                 diachi= edtdiachi.getText().toString();
                 sdt = edtsdt.getText().toString();

                 if(bttm.isChecked())
                 {
                     phuongthuc = "Tiền mặt";
                 }
                if(btnganhang.isChecked())
                {
                    phuongthuc = "Ngân hàng";
                }
                if(btvdt.isChecked())
                {
                    phuongthuc = "Ví điện tử";
                }

                dichvu= spinner.getSelectedItem().toString();
                Bundle data = new Bundle();
                data.putString("ten",ten);
                data.putString("ngaysinh",ngaysinh);
                data.putString("sdt",sdt);
                data.putString("diachi",diachi);
                data.putString("dichvu",dichvu);
                data.putString("phuongthuc",phuongthuc);
                navController.navigate(R.id.action_dienthongtinFragment_to_hienthiFragment,data);
            }
        });
    }

    private void data() {
        imglich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(year)
                                .append("-")
                                .append(++month)
                                .append("-")
                                .append(dayOfMonth);
                        edtngaysinh.setText(builder.toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        //tao mang chua gia tri trong spinner
        String[] dichvu = new String[]{"Truyền hình số", "Truyền hình cáp", "FBT"};
//tao adapter cho mang gia tri
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(dienthongtinFragment.this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item,dichvu);
//gan adapter cho spinner
        spinner.setAdapter(arrayAdapter);
    }
}
