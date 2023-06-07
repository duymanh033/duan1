package com.example.heaven_motor.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.Splash_screen_MainActivity2;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Users;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ToiFragment extends Fragment {
    ImageView img,edImg,chup,folder;
    TextView tvTen,tvMa,tvTuoi,tvDiaChi,tvSDT,tvCCCD;
    Button capNhat,update,huy,Dondadat,phanhoi,dangxuat;
    Users obj;
    UserDAO userDAO;
    EditText edTen,edTuoi,edDiaChi,edSDT, edCCCD;
    CardView cardView;
    LinearLayout linearLayout;
    int yc = 123;



    public ToiFragment() {
        // Required empty public constructor
    }
    public static ToiFragment newInstance() {
        ToiFragment fragment = new ToiFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_toi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardView =view.findViewById(R.id.card);
        linearLayout = view.findViewById(R.id.lear);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.lefttoright);
        cardView.startAnimation(animation);
        Animation animation1= AnimationUtils.loadAnimation(getContext(),R.anim.info);
        linearLayout.startAnimation(animation1);
        Dondadat  =view.findViewById(R.id.btnDonDaDat);
        phanhoi = view.findViewById(R.id.btnPhanHoi);
        dangxuat = view.findViewById(R.id.btnDangXuat);

        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có muốn xóa sách mã đăng xuất không ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), Splash_screen_MainActivity2.class);
                        startActivity(intent);
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
        });


        img = view.findViewById(R.id.imgUser);
        tvTen = view.findViewById(R.id.tvTenUser);
        tvMa = view.findViewById(R.id.tvMaUser);
        tvTuoi = view.findViewById(R.id.tvTuoi);
        tvCCCD = view.findViewById(R.id.tvCCCD);
        tvSDT = view.findViewById(R.id.tvSDT);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        capNhat = view.findViewById(R.id.btnCapNhatUser);
        Intent intent = getActivity().getIntent();
        if (getActivity().getIntent().hasExtra("user")){
            String maUser = intent.getStringExtra("user");
            userDAO = new UserDAO(getContext());
            obj=userDAO.getID(maUser);
            tvTen.setText(obj.getName());
            tvMa.setText("Mã tài khoản : "+obj.getId());
            tvTuoi.setText("Tuổi : "+String.valueOf(obj.getDate()));
            tvCCCD.setText("CCCD : "+obj.getCCCD());
            tvSDT.setText("Số điện thoại : "+obj.getPhone());
            tvDiaChi.setText("Địa chỉ : "+obj.getAddress());
        }
        if (obj.getImg()==null){
            obj.setImg(intToByteArray(R.drawable.ic_baseline_person_24));
            userDAO.update(obj);
            img.setImageResource(R.drawable.ic_baseline_person_24);
        }


           byte[] imguser = obj.getImg();
           Bitmap bitmap = BitmapFactory.decodeByteArray(imguser, 0, imguser.length);
           img.setImageBitmap(bitmap);
        phanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), PhanHoiActivity.class);
                intent1.putExtra("user", obj.getName());
                startActivity(intent1);
            }
        });

           capNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                window.setAttributes(windowAttributes);
                dialog.setContentView(R.layout.dialog_update_user);
                edImg = dialog.findViewById(R.id.imgUser);
                chup =dialog.findViewById(R.id.imgChup);
                folder=dialog.findViewById(R.id.imgFolder);
                update = dialog.findViewById(R.id.btnCapNhatUser);
                huy = dialog.findViewById(R.id.btnHuy);
                edTen = dialog.findViewById(R.id.edTenUser);
                edTuoi = dialog.findViewById(R.id.edTuoiUser);
                edCCCD = dialog.findViewById(R.id.edCCCD);
                edDiaChi = dialog.findViewById(R.id.edDiaChi);
                edSDT = dialog.findViewById(R.id.edSDT);

                edTen.setText(obj.getName());
                edTuoi.setText(String.valueOf(obj.getDate()));
                edCCCD.setText(obj.getCCCD());
                edSDT.setText(obj.getPhone());
                edDiaChi.setText(obj.getAddress());

                if (obj.getImg()==null){
                    edImg.setImageResource(R.drawable.place);
                }else {
                    edImg.setImageBitmap(bitmap);
                }



                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Validate()>0){
                            obj.setName(edTen.getText().toString());
                            obj.setPhone(edSDT.getText().toString());
                            obj.setAddress(edDiaChi.getText().toString());
                            obj.setDate(Integer.parseInt(edTuoi.getText().toString()));
                            obj.setCCCD(edCCCD.getText().toString());
                            obj.setImg(imgViewToByte(edImg));
                            if (userDAO.update(obj)>0){
                                Toast.makeText(getContext(), "Sửa thành công, vui lòng tải lại trang", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }


                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                chup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setImg1();
                    }
                });
                folder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setImg();
                    }
                });
                dialog.show();
            }
        });




    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private int Validate(){
        int check = 1;
        if (edTen.getText().length()==0||edTuoi.getText().length()==0||edCCCD.getText().length()==0||edDiaChi.getText().length()==0||edSDT.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }else if (Integer.parseInt(edTuoi.getText().toString())<18){
            Toast.makeText(getActivity(), "Tuổi phải từ 18 trở lên", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
    public void setImg() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, yc);
    }
    public void setImg1() {
        Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON);
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
                edImg.setImageBitmap(bitmap);
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
    public static byte[] intToByteArray(int a)
    {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
}