package com.oneday.home;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Abhilash on 30/03/2018.
 */

public class HomeFragmentPresenterImpl implements HomeFragmentPresenter {

    private HomeFragmentView mHomeFragmentView;

    public HomeFragmentPresenterImpl(HomeFragmentView homeFragmentView) {
        this.mHomeFragmentView = homeFragmentView;
    }

    @Override
    public void getHomeData(Activity activity) {
        ArrayList<HomeItem> homeItemArrayList = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(readJSONFromAsset(activity));

            // Getting JSON Array node
            JSONArray categories = jsonObj.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                JSONObject categoriesJSONObject = categories.getJSONObject(i);
                HomeItem homeItem = new HomeItem();
                homeItem.setCategoryId(categoriesJSONObject.getInt("category_id"));
                homeItem.setCategoryName(categoriesJSONObject.getString("category_name"));
                homeItem.setCategoryIcon(categoriesJSONObject.getString("category_icon"));
                homeItem.setTrending(categoriesJSONObject.getBoolean("trending"));
                homeItemArrayList.add(homeItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHomeFragmentView.onHomeItemsSuccess(homeItemArrayList);
    }

    public String readJSONFromAsset(Activity activity) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("home_categories.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
