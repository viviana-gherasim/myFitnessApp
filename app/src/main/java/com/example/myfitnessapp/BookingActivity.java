package com.example.myfitnessapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.step_view)  StepView stepView;

    @BindView(R.id.view_pager)  ViewPager viewPager;

    @BindView(R.id.btn_previous_step) Button btn_previous_step;

    @BindView(R.id.btn_next_step) Button btn_next_step;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(BookingActivity.this);

        setupStepView();
        setColorButton();

        //View
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if( position == 0) {
                    btn_previous_step.setEnabled(false);
                }
                else
                    btn_previous_step.setEnabled(true);

                setColorButton();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupStepView() {
        List<String> stepList = new ArrayList<>();
        stepList.add("City");
        stepList.add("Nutritionist");
        stepList.add("Time");
        stepList.add("Confirm");
        stepView.setSteps(stepList);
    }

    private void setColorButton() {
        if(btn_next_step.isEnabled()) {
            btn_next_step.setBackgroundResource(R.color.colorPrimary);
        }
        else{
            btn_next_step.setBackgroundResource(R.color.browser_actions_bg_grey);
        }

        if(btn_previous_step.isEnabled()) {
            btn_previous_step.setBackgroundResource(R.color.colorPrimary);
        }
        else{
            btn_previous_step.setBackgroundResource(R.color.browser_actions_bg_grey);
        }
    }
}
