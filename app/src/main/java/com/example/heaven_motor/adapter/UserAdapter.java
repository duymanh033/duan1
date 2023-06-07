package com.example.heaven_motor.adapter;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heaven_motor.Login_MainActivity2;
import com.example.heaven_motor.MainActivity;
import com.example.heaven_motor.R;
import com.example.heaven_motor.Sign_Up.Sign_up_MainActivity2;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.fragment.QLyNguoi_Dung_Fragment;
import com.example.heaven_motor.model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    QLyNguoi_Dung_Fragment fragment;
    ArrayList<Users> list;
    UserDAO userDAO;

    public UserAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
        userDAO = new UserDAO(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_user, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users pl = list.get(position); // lay ra 1 item trong list dua vao position
        holder.item_userTV_id.setText("ID : " + pl.getId());
        holder.item_userTV_name.setText("TÊN : " + pl.getName());
        holder.item_userTV_date.setText("TUỔI : " + pl.getDate());
        holder.item_userTV_cccd.setText("CĂN CƯỚC : " + pl.getCCCD());
        holder.item_userTV_address.setText("ĐỊA CHỈ : " + pl.getAddress());
        holder.item_userTV_passwork.setText("MẬT KHẨU : " + pl.getPasswork());
        holder.item_userTV_phone.setText("SĐT : " + pl.getPhone());


        holder.cvCHITIET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (holder.item_userTV_name.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.item_userTV_name.setVisibility(v);

                int v2 = (holder.item_userTV_date.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.item_userTV_date.setVisibility(v2);

                int v3 = (holder.item_userTV_cccd.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.item_userTV_cccd.setVisibility(v3);

                int v4 = (holder.item_userTV_address.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.item_userTV_address.setVisibility(v4);

                int v5 = (holder.item_userTV_passwork.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.item_userTV_passwork.setVisibility(v5);

                int v6 = (holder.item_userTV_phone.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.item_userTV_phone.setVisibility(v6);

                int v7 = (holder.updateIMG.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.updateIMG.setVisibility(v7);

                int v8 = (holder.delIMG.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(holder.lnl, new AutoTransition());
                holder.delIMG.setVisibility(v8);
            }
        });

        holder.updateIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogUPDATE(pl);
            }
        });
        holder.delIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("XÓA NGƯỜI DÙNG");
                builder.setMessage("xác nhận để xóa");
                builder.setCancelable(true);

                builder.setPositiveButton("Xóa",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (userDAO.delete_(pl.getId())){
                                    list.clear();
                                    list.addAll(userDAO.getAll());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Xóa Thất bại", Toast.LENGTH_SHORT).show();
                                }

                                dialog.cancel();
                            }
                        });
                builder.setNegativeButton("Hủy",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView item_userTV_id, item_userTV_name, item_userTV_date, item_userTV_cccd, item_userTV_address, item_userTV_passwork, item_userTV_phone;
        CardView cvCHITIET;
        LinearLayout lnl;
        ImageView updateIMG, delIMG;

        public UserViewHolder(View v) {
            super(v);
            updateIMG = v.findViewById(R.id.ivEditUser);
            delIMG = v.findViewById(R.id.ivDelUser);

            cvCHITIET = v.findViewById(R.id.cvCHITIET);
            lnl = v.findViewById(R.id.lnl);
            item_userTV_id = v.findViewById(R.id.item_userTV_id);
            item_userTV_name = v.findViewById(R.id.item_userTV_name);
            item_userTV_date = v.findViewById(R.id.item_userTV_date);
            item_userTV_cccd = v.findViewById(R.id.item_userTV_cccd);
            item_userTV_address = v.findViewById(R.id.item_userTV_address);
            item_userTV_passwork = v.findViewById(R.id.item_userTV_passwork);
            item_userTV_phone = v.findViewById(R.id.item_userTV_phone);


        }
    }

    private void opendialogUPDATE(Users user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_user1, null);
        builder.setView(view);

        Dialog dialog = builder.create();
        dialog.show();


        EditText id = view.findViewById(R.id.Dialog_Sign_up_edId_update);
        EditText passwork = view.findViewById(R.id.Dialog_Sign_up_edPassword_update);
        EditText ten = view.findViewById(R.id.Dialog_Sign_up_edName_update);
        EditText tuoi = view.findViewById(R.id.Dialog_Sign_up_edDate_update);
        EditText cccd = view.findViewById(R.id.Dialog_Sign_up_edCCCD_update);
        EditText phone = view.findViewById(R.id.Dialog_Sign_up_edPhone_update);
        EditText diachi = view.findViewById(R.id.Dialog_Sign_up_edAddress_update);

        Button btnsua = view.findViewById(R.id.Dialog_btnUpdateUser);
        Button btnHuy = view.findViewById(R.id.Dialog_btnHuyUpdateUser);

        // gán giá trị của phân loại lên view
        id.setText(user.getId());
        passwork.setText(user.getPasswork());
        ten.setText(user.getName());
        tuoi.setText(user.getDate() + "");
        cccd.setText(user.getCCCD());
        phone.setText(user.getPhone());
        diachi.setText(user.getAddress());

        //end gán

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setId(id.getText().toString());
                user.setPasswork(passwork.getText().toString());
                user.setName(ten.getText().toString());
                user.setDate(Integer.parseInt(tuoi.getText().toString()));
                user.setCCCD(cccd.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setAddress(diachi.getText().toString());

                if (userDAO.update(user)>0) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    // reset du lieu lên rcv
                    list.clear();
                    list.addAll(userDAO.getAll());
                    notifyDataSetChanged();
                    //
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}


//            item_userTV_id = v.findViewById(R.id.item_userTV_id);
//            item_userTV_id.setText("ID: " + item.getId());
//
//            item_userTV_name = v.findViewById(R.id.item_userTV_name);
//            item_userTV_name.setText("TÊN: " + item.getName());
//
//            item_userTV_date = v.findViewById(R.id.item_userTV_date);
//            item_userTV_date.setText("TUỔI: " + item.getDate());
//
//            item_userTV_address = v.findViewById(R.id.item_userTV_address);
//            item_userTV_address.setText("ĐỊA CHỈ: " + item.getAddress());
//
//            item_userTV_cccd = v.findViewById(R.id.item_userTV_cccd);
//            item_userTV_cccd.setText("CĂN CƯỚC: " + item.getCCCD());
//
//            item_userTV_passwork = v.findViewById(R.id.item_userTV_passwork);
//            item_userTV_passwork.setText("MẬT KHẨU: " + item.getPasswork());


