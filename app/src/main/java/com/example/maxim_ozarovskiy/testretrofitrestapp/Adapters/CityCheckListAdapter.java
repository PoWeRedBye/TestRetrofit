package com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.maxim_ozarovskiy.testretrofitrestapp.R;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityModel;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityCheckListAdapter extends RecyclerSwipeAdapter<CityCheckListAdapter.ViewHolder> {

    private Context context;
    private List<CityModel> cityModelList;
    private ItemCityClickListener<CityModel> itemCityClickListener;
    private CityCheckDBAdapter myDb;
    private DeleteCityClickListener<CityModel> deleteCityClickListener;


    public CityCheckListAdapter(Context context, List<CityModel> cityModelList,
                                ItemCityClickListener<CityModel> itemCityClickListener,
                                DeleteCityClickListener<CityModel> deleteCityClickListener) {
        this.context = context;
        this.cityModelList = cityModelList;
        this.itemCityClickListener = itemCityClickListener;
        this.deleteCityClickListener = deleteCityClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_check_item, parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,  int position) {
       final int pos = holder.getAdapterPosition();
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });

        holder.cityName.setText(cityModelList.get(pos).getCityName());

        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCityClickListener.deleteCityClick(cityModelList.get(pos), pos);
                mItemManger.removeShownLayouts(holder.swipeLayout);
            }
        });

        holder.cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCityClickListener.itemClick(cityModelList.get(pos), pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityModelList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityName;
        SwipeLayout swipeLayout;
        ImageView imageView;
        ImageView trash;

        public ViewHolder(View v) {
            super(v);

            cityName = (TextView) v.findViewById(R.id.city_check_name_item);
            swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe);
            imageView = (ImageView) v.findViewById(R.id.city_check_image_item);
            trash = (ImageView) v.findViewById(R.id.trash);
        }
    }

    public interface ItemCityClickListener<T> {
        void itemClick(T v, int position);
    }

    public interface DeleteCityClickListener<D> {
        void deleteCityClick(D d, int position);
    }
}
