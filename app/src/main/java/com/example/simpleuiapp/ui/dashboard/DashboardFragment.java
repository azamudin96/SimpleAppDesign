package com.example.simpleuiapp.ui.dashboard;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.simpleuiapp.R;
import com.example.simpleuiapp.adapter.Adapter;
import com.example.simpleuiapp.ui.home.HomeViewModel;
import com.example.simpleuiapp.widget.WrapContentHeightViewPager;
import com.example.simpleuiapp.model.Model;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DashboardFragment extends Fragment implements  DatePickerListener , View.OnClickListener {

    private HomeViewModel homeViewModel;
    private HorizontalPicker mPicker;

    WrapContentHeightViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    String str_device;

    CheckBox chk_food , chk_marie , chk_gym , chk_drink;

    TextView tv_food , tv_marie , tv_gym , tv_drink , tv_wish;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.book_menu, "Reading", "156 Sheets" , R.color.dark_purple , 76));
        models.add(new Model(R.drawable.walking, "Walking", "3567 Steps" , R.color.pink , 36));
        models.add(new Model(R.drawable.pencil, "Writing", "2000 Words" , R.color.orange , 88));
        models.add(new Model(R.drawable.ic_baseline_local_drink_24, "Drinking", "8 Glasses" , R.color.light_pink , 28));

        adapter = new Adapter(models, getContext());

        int dpValue = 180; // margin in dips
        float d = getContext().getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * d); // margin in pixels

        viewPager = root.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin(-margin);
        viewPager.setPadding(25, 0, 25, 0);

//        Integer[] colors_temp = {
//                getResources().getColor(R.color.pink),
//                getResources().getColor(R.color.pink),
//                getResources().getColor(R.color.dark_pink),
//                getResources().getColor(R.color.light_pink)
//        };
//
//        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
//                    viewPager.setBackgroundColor(
//
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//
//                else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final TextView textView = root.findViewById(R.id.text_home);
        mPicker = (HorizontalPicker) root.findViewById(R.id.datePicker);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        // initialize it and attach a listener
        mPicker.setListener((DatePickerListener) this)
                .setDateSelectedColor(getResources().getColor(R.color.pink))
                .init();

        mPicker.setDate(new DateTime());

        tv_food = root.findViewById(R.id.tv_food);
        tv_marie = root.findViewById(R.id.tv_marie);
        tv_gym = root.findViewById(R.id.tv_gym);
        tv_drink = root.findViewById(R.id.tv_drink);
        tv_wish = root.findViewById(R.id.tv_wish);

        chk_food = root.findViewById(R.id.chk_food);
        chk_marie = root.findViewById(R.id.chk_marie);
        chk_gym = root.findViewById(R.id.chk_gym);
        chk_drink = root.findViewById(R.id.chk_drink);

        chk_food.setOnClickListener(this);
        chk_marie.setOnClickListener(this);
        chk_gym.setOnClickListener(this);
        chk_drink.setOnClickListener(this);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            tv_wish.setText("Good Morning,");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            tv_wish.setText("Good Afternoon,");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            tv_wish.setText("Good Evening,");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            tv_wish.setText("Good Night,");
        }

        return root;
    }

    @Override
    public void onDateSelected(@NonNull final DateTime dateSelected) {
        // log it for demo
        Log.i("HorizontalPicker", "Selected date is " + dateSelected.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chk_food:
                if (chk_food.isChecked()){
                    strikeThroughText(tv_food);
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
                } else {
                    tv_food.setPaintFlags(tv_food.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    Toast.makeText(getContext(), "Undone", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.chk_marie:
                if (chk_marie.isChecked()){
                    strikeThroughText(tv_marie);
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
                } else {
                    tv_marie.setPaintFlags(tv_marie.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    Toast.makeText(getContext(), "Undone", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.chk_gym:
                if (chk_gym.isChecked()){
                    strikeThroughText(tv_gym);
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
                } else {
                    tv_gym.setPaintFlags(tv_gym.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    Toast.makeText(getContext(), "Undone", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.chk_drink:
                if (chk_drink.isChecked()){
                    strikeThroughText(tv_drink);
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
                } else {
                    tv_drink.setPaintFlags(tv_drink.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    Toast.makeText(getContext(), "Undone", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void strikeThroughText(TextView price){
        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}