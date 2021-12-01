package com.example.doanchuyennganh_nhom5.model;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.doanchuyennganh_nhom5.R;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, ngay, thang, nam);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        EditText ngaysinh =(EditText) getActivity().findViewById(R.id.edt_ngaythangnamsinh_cn);
        String sNgaySinh = i2 + "/" + (i1+1) + "/" + (i+100);
        ngaysinh.setText(sNgaySinh);
    }

}
