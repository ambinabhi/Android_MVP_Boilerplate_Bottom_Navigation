package com.oneday.home;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.oneday.account.AccountFragment;
import com.oneday.AddItemFragment;
import com.oneday.base.DaBaseActivity;
import com.oneday.R;
import com.oneday.splash.SearchFragment;
import com.oneday.WishListFragment;
import com.oneday.navigation.BottomNavigationViewNew;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends DaBaseActivity {
    @BindView(R.id.vp_home)
    ViewPager mViewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationViewNew mBottomNavigationView;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    AddItemFragment addItemFragment;
    WishListFragment wishListFragment;
    AccountFragment accountFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mBottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        showTile("Bangalore, India");
    }

    private void setupViewPager(ViewPager mViewPager) {
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        addItemFragment = new AddItemFragment();
        wishListFragment = new WishListFragment();
        accountFragment = new AccountFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(searchFragment);
        adapter.addFragment(addItemFragment);
        adapter.addFragment(wishListFragment);
        adapter.addFragment(accountFragment);
        mViewPager.setAdapter(adapter);
    }


    private BottomNavigationViewNew.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewNew.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.nav_search:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.nav_add:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.nav_fav:
                    mViewPager.setCurrentItem(3);
                    return true;
                case R.id.nav_account:
                    mViewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }

    };
}
