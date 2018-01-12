package com.phoenix.appdetailactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    /**
     * Tab RadioButton 介绍 评论 推荐
     */
    private RadioButton oneBtn;
    private RadioButton twoBtn;

    private Fragment oneFragment, twoFragment;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        //设置屏幕外保存的view个数
        viewPager.setOffscreenPageLimit(2);

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScorllView);
        nestedScrollView.setFillViewport(true);

        oneBtn = findViewById(R.id.button1);
        oneBtn.setOnClickListener(this);
        twoBtn = findViewById(R.id.button2);
        twoBtn.setOnClickListener(this);

        initFragment();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        oneFragment = OneFragment.newInstance();
        twoFragment = TwoFragment.newInstance();
        fragmentList = new ArrayList<>();
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        oneBtn.setChecked(true);
                        break;
                    case 1:
                        twoBtn.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.button2:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
}
