package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityCheck;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityCheckListAdapter extends RecyclerView.Adapter<CityCheckListAdapter.ViewHolder> {

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, null);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.cityName.setText(cityCheckList.get(position).getCityCheckName());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCityClickListener.DeleteCityClick(cityCheckList.get(position), position);
            }
        });

        holder.cityCheckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCityClickListener.ItemClick(cityCheckList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityCheckList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cityCheckCard;
        TextView cityName;
        ImageButton delete;


        public ViewHolder(View v) {
            super(v);

            cityCheckCard = (CardView) v.findViewById(R.id.city_check_card);
            cityName = (TextView) v.findViewById(R.id.city_check_name_item);
            delete = (ImageButton) v.findViewById(R.id.city_check_delete_item);
        }
    }

    public interface ItemCityClickListener<T> {
        void ItemClick(T v, int position);
    }

    public interface DeleteCityClickListener<D> {
        void DeleteCityClick(D d, int position);

    }
}
