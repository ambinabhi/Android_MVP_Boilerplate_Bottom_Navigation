package com.oneday.home;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oneday.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abhilash on 30/03/2018.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeRecyclerViewHolder> {

    private Activity mActivity;
    private ArrayList<HomeItem> mHomeItems;

    public HomeRecyclerAdapter(Activity activity, ArrayList<HomeItem> homeItemArrayList) {
        this.mActivity = activity;
        this.mHomeItems = homeItemArrayList;
    }

    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View homeView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cv_home_item, parent, false);
        return new HomeRecyclerViewHolder(homeView);
    }

    @Override
    public void onBindViewHolder(HomeRecyclerViewHolder holder, int position) {
        holder.textViewCategoryName.setText(mHomeItems.get(position).getCategoryName());

        switch (mHomeItems.get(position).getCategoryIcon()){
            case "1":
                holder.imageViewCategory.setBackgroundResource(R.drawable.home_decor);
                break;
            case "2":
                holder.imageViewCategory.setBackgroundResource(R.drawable.painting);
                break;
            case "3":
                holder.imageViewCategory.setBackgroundResource(R.drawable.kids);
                break;
            case "4":
                holder.imageViewCategory.setBackgroundResource(R.drawable.clothing);
                break;
            case "5":
                holder.imageViewCategory.setBackgroundResource(R.drawable.real);
                break;
            case "6":
                holder.imageViewCategory.setBackgroundResource(R.drawable.auto);
                break;
            case "7":
                holder.imageViewCategory.setBackgroundResource(R.drawable.sports);
                break;
            case "8":
                holder.imageViewCategory.setBackgroundResource(R.drawable.pets);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mHomeItems.size();
    }

    public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_category)
        ImageView imageViewCategory;
        @BindView(R.id.tv_category_name)
        TextView textViewCategoryName;

        public HomeRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
