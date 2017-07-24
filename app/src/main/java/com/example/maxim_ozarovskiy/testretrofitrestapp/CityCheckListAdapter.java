package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityCheck;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityCheckListAdapter extends RecyclerSwipeAdapter<CityCheckListAdapter.ViewHolder> {

    private Context context;
    private List<CityCheck> cityCheckList;
    private ItemCityClickListener<CityCheck> itemCityClickListener;
    private CityCheckDBAdapter myDb;
    private DeleteCityClickListener<CityCheck> deleteCityClickListener;


    public CityCheckListAdapter(Context context, List<CityCheck> cityCheckList,
                                ItemCityClickListener<CityCheck> itemCityClickListener,
                                DeleteCityClickListener<CityCheck> deleteCityClickListener) {
        this.context = context;
        this.cityCheckList = cityCheckList;
        this.itemCityClickListener = itemCityClickListener;
        this.deleteCityClickListener = deleteCityClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_2, parent, false);//.inflate(R.layout.city_item, null); standart item view

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });

        holder.cityName.setText(cityCheckList.get(position).getCityCheckName());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCityClickListener.deleteCityClick(cityCheckList.get(position), position);
                mItemManger.removeShownLayouts(holder.swipeLayout);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCityClickListener.itemClick(cityCheckList.get(position), position);
            }
        });

    }




    @Override
    public int getItemCount() {
        return cityCheckList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //CardView cityCheckCard;
        TextView cityName;
        Button delete;
        SwipeLayout swipeLayout;
        ImageView imageView;

        public ViewHolder(View v) {
            super(v);


            cityName = (TextView) v.findViewById(R.id.city_check_name_item);
            delete = (Button) v.findViewById(R.id.delete);
            swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe);
            imageView = (ImageView) v.findViewById(R.id.city_check_image_item);
        }
    }

    public interface ItemCityClickListener<T> {
        void itemClick(T v, int position);
    }

    public interface DeleteCityClickListener<D> {
        void deleteCityClick(D d, int position);
    }
}
