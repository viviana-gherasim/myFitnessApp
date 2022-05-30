package com.example.myfitnessapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingActivity extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;

    @BindView(R.id.step_view)  StepView stepView;

    @BindView(R.id.view_pager)  NonSwipeViewPager viewPager;

    @BindView(R.id.btn_previous_step) Button btn_previous_step;

    @BindView(R.id.btn_next_step) Button btn_next_step;

    //Event
    @OnClick(R.id.btn_previous_step)
    void previousStep() {
        if(Common.step == 3 || Common.step > 0) {
            Common.step--;
            viewPager.setCurrentItem(Common.step);
        }
    }

    @OnClick(R.id.btn_next_step)
    void nextClick() {
        if(Common.step < 3 || Common.step == 0 ){
            Common.step++;
            if(Common.step == 1) {  //after choose nutritionist
                BookingStep2Fragment.getInstance().updateUI();
            }
            viewPager.setCurrentItem(Common.step);
        }
    }

    //Broadcast Receiver
    private BroadcastReceiver buttonNextReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Common.currentNutritionist = intent.getParcelableExtra(Common.KEY_NUTRITIONIST);
            btn_next_step.setEnabled(true);
            setColorButton();
        }
    };

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(buttonNextReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(BookingActivity.this);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReceiver, new IntentFilter(Common.KEY_ENABLE_BUTTON_NEXT));

        setupStepView();
        setColorButton();

        //View
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(4);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //show step
                stepView.go(position, true);
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
