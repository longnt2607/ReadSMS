package vn.edu.ntu.nguyentruonglong.readsms;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class LuaChonFragment extends Fragment {

    public static final String KEY_FROM_DATE = "tu-ngay";
    public static final String KEY_TO_DATE = "den-ngay";

    EditText edtFromDate, edtToDate;
    ImageView imvFromDate, imvToDate;
    NavController navController;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_luachon, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtFromDate = view.findViewById(R.id.edt_FromDate);
        edtToDate = view.findViewById(R.id.edt_ToDate);
        edtToDate = view.findViewById(R.id.edt_ToDate);
        imvFromDate = view.findViewById(R.id.imvFromDate);
        imvToDate = view.findViewById(R.id.imvToDate);
        imvFromDate.setOnClickListener(this);
        imvToDate.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)getActivity()).navController = this.navController;
    }

    private void thietLap(final int luaChon) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder builder = new StringBuilder();
                builder.append(year)
                        .append("-")
                        .append(++month)
                        .append("-")
                        .append(dayOfMonth);
                if (luaChon == 0) {
                    edtFromDate.setText(builder.toString());
                } else {
                    edtToDate.setText(builder.toString());
                }
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                                                    listener,
                                                                    calendar.get(Calendar.YEAR),
                                                                    calendar.get(Calendar.MONTH),
                                                                    calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void hienThiSMS() {
        if (edtFromDate.getText().toString().length() > 0 && edtToDate.getText().toString().length() > 0) {
            Bundle data = new Bundle();
            data.putCharSequence(KEY_FROM_DATE, edtFromDate.getText().toString());
            data.putCharSequence(KEY_TO_DATE, edtToDate.getText().toString());
            navController.navigate(R.id.action_luaChonFragment_to_danhSachFragment, data);
        }
    }
}
