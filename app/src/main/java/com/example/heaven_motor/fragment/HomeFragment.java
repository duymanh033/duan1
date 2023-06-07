package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.DatHangAdapter;
import com.example.heaven_motor.adapter.DatHang_homeAdapter;
import com.example.heaven_motor.adapter.SlideShowAdapter;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Slideshow;
import com.example.heaven_motor.model.Users;
import com.example.heaven_motor.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {

    //đặt hàng
    GridView gridView;
    List<Vehicle> list;
    VehicleDAO dao;
    DatHang_homeAdapter adapter;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //===
    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<Slideshow> mListPhoto;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if (currentPosition == mListPhoto.size() - 1) {
                mViewPager2.setCurrentItem(0);
            } else {
                mViewPager2.setCurrentItem(currentPosition + 1);

            }
        }
    };

    TextView nameUser;
    UserDAO userDAO;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dao  = new VehicleDAO(getContext());
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        gridView =v.findViewById(R.id.frag_datHang_grip_home);


        loadData();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager2 = view.findViewById(R.id.view_pager_slideshow);
        mCircleIndicator3 = view.findViewById(R.id.cirle_slideshow);
        nameUser = view.findViewById(R.id.home_name_user);
        Intent i = getActivity().getIntent();


        // setting viewpager 2
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager2.setClipToPadding(false);
        mViewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        mViewPager2.setPageTransformer(compositePageTransformer);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }
        });
        //============

        // set ten user
        String user2 = i.getStringExtra("user");
        userDAO = new UserDAO(getContext());
        Users users = userDAO.getID(user2);
        String name = users.getName();
        nameUser.setText(name+" !");
        //=====

        mListPhoto = getListPhoto();
        SlideShowAdapter photoAdapter = new SlideShowAdapter(mListPhoto);
        mViewPager2.setAdapter(photoAdapter);
        mCircleIndicator3.setViewPager(mViewPager2);


        // sản phẩm


        //=======================



    }

    public void loadData(){
        list = dao.getAll();
        adapter = new DatHang_homeAdapter(getContext(),this,list);
        gridView.setAdapter(adapter);

    }

    private List<Slideshow> getListPhoto() {
        List<Slideshow> list = new ArrayList<>();

        list.add(new Slideshow(R.drawable.slide1));
        list.add(new Slideshow(R.drawable.slide2));
        list.add(new Slideshow(R.drawable.slide3));
        list.add(new Slideshow(R.drawable.slide4));

        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }
}