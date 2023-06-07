package com.example.heaven_motor.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.LoaiXeSpinnerAdapter;
import com.example.heaven_motor.adapter.VehicleAdapter;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Vehicle;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class QLyXe_Fragment extends Fragment {
    Button fab;
    Dialog dialog;
    EditText edMaXe, edTenXe, edBKS, edGiaThue, edTinhTrang;
    Button save, cancle;
    ImageView imgXe;
    Spinner spinnerLoaiXe, spinnerDungTich, spinnerHangXe, spinnerNam;
    int yc = 123;
    FrameLayout frameLayout;

    ListView listView;
    VehicleAdapter vehicleAdapter;
    Vehicle obj;
    VehicleDAO dao;
    int maLoaiXe;
    String hangXe;
    int nam;
    int viTriLoai, viTriHang, viTriNam, viTriDungTich;

    ArrayList<Vehicle> list;

    ArrayList<Categoris> listCategorises;
    CategorisDao categorisDao;
    LoaiXeSpinnerAdapter loaiXeSpinnerAdapter;
    int dungTich, position;

    public QLyXe_Fragment() {
        // Required empty public constructor
    }

    public static QLyXe_Fragment newInstance() {
        QLyXe_Fragment fragment = new QLyXe_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_ly_xe, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frameLayout = view.findViewById(R.id.fragmentXe);
        Animation animation1= AnimationUtils.loadAnimation(getContext(),R.anim.downtoup);
        frameLayout.startAnimation(animation1);
        fab = view.findViewById(R.id.fab);
        listView = view.findViewById(R.id.fragXe_recy);
        dao = new VehicleDAO(getContext());
        capNhatRCV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opDialogAddXe(getContext(), 0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                obj = list.get(position);
                opDialogAddXe(getContext(), 1);
                return false;
            }
        });

    }

    void capNhatRCV() {
        list = (ArrayList<Vehicle>) dao.getAll();
        vehicleAdapter = new VehicleAdapter(getContext(), this, list);
        listView.setAdapter(vehicleAdapter);

    }

    void opDialogAddXe(Context context, int type) {
        dialog = new Dialog(context);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        dialog.setContentView(R.layout.dialog_them_xe);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        window.setAttributes(windowAttributes);
        //ánh xạ
        imgXe = dialog.findViewById(R.id.imgXe);
        edMaXe = dialog.findViewById(R.id.edMaXe);
        edTenXe = dialog.findViewById(R.id.edTenXe);
        edTinhTrang = dialog.findViewById(R.id.edTinhTrang);
        edBKS = dialog.findViewById(R.id.edBKS);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        cancle = dialog.findViewById(R.id.btnCancel);
        save = dialog.findViewById(R.id.btnSave);
        spinnerLoaiXe = dialog.findViewById(R.id.spinnerLoaiXe);
        spinnerDungTich = dialog.findViewById(R.id.spinnerDungTich);
        spinnerHangXe = dialog.findViewById(R.id.spinnerHangXe);
        spinnerNam = dialog.findViewById(R.id.spinnerNam);
        imgXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImg();
            }
        });

        //spinner loại xe
        listCategorises = new ArrayList<Categoris>();
        categorisDao = new CategorisDao(context);
        listCategorises = (ArrayList<Categoris>) categorisDao.getAll();
        loaiXeSpinnerAdapter = new LoaiXeSpinnerAdapter(context, listCategorises);
        spinnerLoaiXe.setAdapter(loaiXeSpinnerAdapter);
        spinnerLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiXe = listCategorises.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner dung tích
        ArrayList<Integer> listDungTich = new ArrayList<>();
        listDungTich.add(50);
        listDungTich.add(100);
        listDungTich.add(110);
        listDungTich.add(125);
        listDungTich.add(150);
        listDungTich.add(250);
        listDungTich.add(500);
        listDungTich.add(1000);

        ArrayAdapter arrayAdapterDungTich = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listDungTich);
        spinnerDungTich.setAdapter(arrayAdapterDungTich);
        spinnerDungTich.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dungTich = Integer.parseInt(arrayAdapterDungTich.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner hãng xe
        ArrayList<String> listHangXe = new ArrayList<>();
        listHangXe.add("HONDA");
        listHangXe.add("YAMAHA");
        listHangXe.add("KAWASAKI");
        listHangXe.add("DUCATI");
        listHangXe.add("SUZUKI");
        listHangXe.add("BMW");
        listHangXe.add("SYM");
        listHangXe.add("BENELLI");
        listHangXe.add("VINFAST");
        ArrayAdapter arrayAdapterHangXe = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listHangXe);
        spinnerHangXe.setAdapter(arrayAdapterHangXe);
        spinnerHangXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hangXe = arrayAdapterHangXe.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner năm
        ArrayList<Integer> listNam = new ArrayList<>();
        for (int i = 2010; i <= 2022; i++) {
            listNam.add(i);
        }
        ArrayAdapter arrayAdapterNam = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listNam);
        spinnerNam.setAdapter(arrayAdapterNam);
        spinnerNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nam = Integer.parseInt(arrayAdapterNam.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validate() > 0) {
                    obj = new Vehicle();
                    obj.setId(edMaXe.getText().toString());
                    obj.setName(edTenXe.getText().toString());
                    obj.setPrice(Integer.parseInt(edGiaThue.getText().toString()));
                    obj.setBKS(edBKS.getText().toString());
                    obj.setStatus(Integer.parseInt(edTinhTrang.getText().toString()));
                    obj.setCategorie_id(maLoaiXe);
                    obj.setBrand(hangXe);
                    obj.setTrangThai(0);
                    obj.setCapacity(dungTich);
                    obj.setYear(nam);
                    obj.setImg(imgViewToByte(imgXe));
                    if (type == 0) {
                        if (dao.insert(obj) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        obj.setId(edMaXe.getText().toString());
                        if (dao.Update(obj) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                    capNhatRCV();
                    dialog.dismiss();
                }

            }
        });
        if (type != 0) {
            edMaXe.setText(String.valueOf(obj.getId()));
            edMaXe.setEnabled(false);
            edTenXe.setText(obj.getName());
            edBKS.setText(obj.getBKS());
            edGiaThue.setText(String.valueOf(obj.getPrice()));
            edTinhTrang.setText(String.valueOf(obj.getStatus()));
            byte[] xeImg = obj.getImg();
            Bitmap bitmap = BitmapFactory.decodeByteArray(xeImg, 0, xeImg.length);
            imgXe.setImageBitmap(bitmap);
            for (int i = 0; i < listCategorises.size(); i++) {
                if (obj.getCategorie_id() == (listCategorises.get(i).getId())) {
                    viTriLoai = i;
                }
            }
            spinnerLoaiXe.setSelection(viTriLoai);
            for (int j = 0; j < listHangXe.size(); j++) {
                if (obj.getBrand().equalsIgnoreCase((String) arrayAdapterHangXe.getItem(j))) {
                    viTriHang = j;
                }
            }
            spinnerHangXe.setSelection(viTriHang);
            for (int a = 0; a < listNam.size(); a++) {
                if (obj.getYear() == (Integer) arrayAdapterNam.getItem(a)) {
                    nam = a;
                }
            }
            spinnerNam.setSelection(viTriNam);
            for (int b = 0; b < listDungTich.size(); b++) {
                if (obj.getCapacity() == (Integer) arrayAdapterDungTich.getItem(b)) {
                    viTriDungTich = b;
                }
            }
            spinnerDungTich.setSelection(viTriDungTich);
        }
        dialog.show();
    }


    private void openImg() {

    }

    public int Validate() {
        int check = 1;
        if (edMaXe.getText().length() == 0 || edBKS.getText().length() == 0 || edGiaThue.getText().length() == 0 || edTinhTrang.getText().length() == 0 || edTenXe.getText().length() == 0) {
            check = -1;
            Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }else if (Integer.parseInt(edTinhTrang.getText().toString())<50||Integer.parseInt(edTinhTrang.getText().toString())>100){
            Toast.makeText(getContext(), "Tình trạng xe trong khoảng 50%->100%", Toast.LENGTH_SHORT).show();
            return -1;

        }
        return check;
    }

    public void delete(String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có muốn xóa sách mã " + Id + " không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                capNhatRCV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        builder.show();
    }

    @Override
    public void onStart() {
        capNhatRCV();
        super.onStart();

    }

    @Override
    public void onResume() {
        onStart();
        super.onResume();
    }

    public void setImg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, yc);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == yc && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgXe.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] imgViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}