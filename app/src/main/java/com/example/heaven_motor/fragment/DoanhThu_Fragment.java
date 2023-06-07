package com.example.heaven_motor.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.OrdersDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThu_Fragment extends Fragment {
Button btntungay,btndenngay,btndoanhthu;
EditText edtungay,eddenngay;
TextView tvdoanhthu;
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
int mYear,mMonth,mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    edtungay = v.findViewById(R.id.edtungay);
    eddenngay = v.findViewById(R.id.eddenngay);
    tvdoanhthu = v.findViewById(R.id.tvdoanhthu);
    btntungay = v.findViewById(R.id.btntungay);
    btndenngay = v.findViewById(R.id.btndenngay);
    btndoanhthu = v.findViewById(R.id.btndoanhthu);
    btntungay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mYear = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDatetungay,mDay,mMonth,mYear);
       d.show();
        }
    });
    btndenngay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mYear = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDatedenngay,mDay,mMonth,mYear);
            d.show();
        }
    });
    btndoanhthu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tungay = edtungay.getText().toString();
            String denngay = eddenngay.getText().toString();
            OrdersDao ordersDao = new OrdersDao(getActivity());
           //tvdoanhthu.setText("Doanh thu: "+ordersDao.getdoanhthu(tungay,denngay)+" VND");
        }
    });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDatetungay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth =monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mDay,mMonth,mYear);
            edtungay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDatedenngay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth =monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mDay,mMonth,mYear);
            eddenngay.setText(sdf.format(c.getTime()));
        }
    };

}