package com.oneday.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.oneday.R;
import com.oneday.event.SearchEvent;

/**
 * Created by Abhilash on 29/03/2018.
 */

public class DaBaseActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    protected Context mContext;
    public MenuItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = DaBaseActivity.this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DaBaseApplication.getEventBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        DaBaseApplication.getEventBus().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        item = menu.findItem(R.id.action_search);
        /*final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setBackgroundColor(ContextCompat.getColor(this, R.color.light_grey));
        searchEditText.setTextColor(ContextCompat.getColor(this, R.color.grey));
        searchEditText.setHintTextColor(ContextCompat.getColor(this, R.color.grey));
        searchEditText.setHint("Search");*/
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    /*    int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.action_sync) {
            if (!ConnectionUtils.isConnectedToNetwork(this)) {
                DtToast.showLong(this, Utils.fetchLocalisationString(this, "internet_connection", NO_CONNECTION));
            } else {
                DBAdapter db = new DBAdapter(this);
                db.open();
                SyncData syncData = new SyncData();
                syncData.fetchAllData(this, true, null);
            }
        } else if (id == R.id.action_add_category) {
            final ArrayList<String> stringArrayList = new ArrayList<>();
            String addCategory = Utils.fetchLocalisationString(this, "add_category", "Add Category");
            String deleteCategory = Utils.fetchLocalisationString(this, "delete_category", "Delete Category");
            String category = Utils.fetchLocalisationString(this, "category_title", "Category");
            stringArrayList.add(addCategory);
            stringArrayList.add(deleteCategory);
            showCategoryListDialog(stringArrayList, category);
        } else if (id == R.id.action_language) {
            final ArrayList<String> stringArrayLanguagesList = availableLanguages(this);
            String selectLanguage = Utils.fetchLocalisationString(this, "language_select", "Select Language");
            showListDialog(stringArrayLanguagesList, selectLanguage);
        } else if (id == R.id.action_scan_barcode) {
            DtBaseApplication.getEventBus().post(new ScanBarcodeEvent(new EmptyResponse()));
        } else if (id == R.id.action_announcements) {
            DtBaseApplication.getEventBus().post(new AnnouncementEvent(new EmptyResponse()));
        }*/
        return super.onOptionsItemSelected(item);
    }

    public void collapseSearchView() {
        MenuItemCompat.collapseActionView(item);
    }


    public void setBackgroundImage(View view, int i) {
        view.setBackgroundResource(i);
    }

    public void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public void setActionBarColor() {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        }
    }

    public void showBackButton() {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showTile(String title) {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null && !TextUtils.isEmpty(title)) {
            mActionBar.setTitle(title);
        }
    }

    public void showLogo() {
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
           // mActionBar.setLogo(R.drawable.toolbar_logo);
            mActionBar.setDisplayShowTitleEnabled(false);
        }
    }




    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String text) {
        DaBaseApplication.getEventBus().post(new SearchEvent(text));
        return true;
    }

}
