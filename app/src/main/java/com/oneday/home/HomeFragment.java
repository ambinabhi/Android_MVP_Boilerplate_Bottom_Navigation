package com.oneday.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.oneday.base.DaBaseFragment;
import com.oneday.home.LatestPagerAdapter;
import com.oneday.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abhilash on 30/03/2018.
 */

public class HomeFragment extends DaBaseFragment implements HomeFragmentView {

    @BindView(R.id.vp_latest)
    ViewPager mPagerLatest;

    @BindView(R.id.ll_slider_dots)
    LinearLayout sliderDotspanel;

    @BindView(R.id.rv_home_categories)
    RecyclerView mRecyclerViewCategories;

    LatestPagerAdapter latestPagerAdapter;
    private int dotCount;
    private ImageView[] dots;
    Handler mHandler;
    int page = 1;
    HomeFragmentPresenter homeFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mHandler = new Handler();
        homeFragmentPresenter = new HomeFragmentPresenterImpl(this);
        homeFragmentPresenter.getHomeData(getActivity());
        setHomeSliderAdapter();
        return view;
    }

    private void setHomeSliderAdapter() {
        latestPagerAdapter = new LatestPagerAdapter(getContext());
        mPagerLatest.setAdapter(latestPagerAdapter);
        dotCount = latestPagerAdapter.getCount();
        dots = new ImageView[dotCount];
        for (int i = 0; i < dotCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
        mPagerLatest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               // mHandler.postDelayed(pageRun, 3000);
                for (int i = 0; i < dotCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
     //   mHandler.postDelayed(pageRun, 3000);
    }

    private Runnable pageRun = new Runnable() {
        @Override
        public void run() {
            if (mPagerLatest != null) {
                page = mPagerLatest.getCurrentItem() + 1;

                if (page == mPagerLatest.getAdapter().getCount()) page = 0;
                mPagerLatest.setCurrentItem(page);
            }
        }
    };

    @Override
    public void onHomeItemsSuccess(ArrayList<HomeItem> homeItemArrayList) {
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerViewCategories.setHasFixedSize(true);
        mRecyclerViewCategories.setLayoutManager(gridLayoutManager);
        HomeRecyclerAdapter homeRecyclerAdapter = new HomeRecyclerAdapter(getActivity(), homeItemArrayList);
        mRecyclerViewCategories.setAdapter(homeRecyclerAdapter);
    }
}
