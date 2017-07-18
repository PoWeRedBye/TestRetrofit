package com.example.maxim_ozarovskiy.testretrofitrestapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.ListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private String weatherImage;
    private int weather_image;
    private Context context;
    private List<ListModel> list;

    public MyAdapter(Context context, List<ListModel> list){
        this.context = context;
        this.list = list;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        holder.data_card.setText(list.get(position).getDt().toString());
        holder.temp_night_card.setText(list.get(position).getTemp().getNight().toString());
        holder.temp_day_card.setText(list.get(position).getTemp().getDay().toString());
        weatherImage = list.get(position).getWeather().get(position).getIcon();
        weather_image = getIcon();
        Picasso.with(context).load(weather_image).into(holder.w_status_card);



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView w_status_card;
        TextView temp_day_card;
        TextView temp_night_card;
        TextView data_card;


        public ViewHolder(View v) {
            super(v);

            cardView = (CardView) v.findViewById(R.id.card);
            w_status_card = (ImageView) v.findViewById(R.id.weather_status_card);
            temp_day_card = (TextView) v.findViewById(R.id.text_temp_day_card);
            temp_night_card = (TextView) v.findViewById(R.id.text_temp_niht_card);
            data_card = (TextView) v.findViewById(R.id.date_card);

        }
    }
    public int getIcon() {
        switch (weatherImage) {
            case "01d":
                return R.drawable.one_day;
            case "01n":
                return R.drawable.one_night;
            case "02d":
                return R.drawable.two_day;
            case "02n":
                return R.drawable.two_night;
            case "03d":
                return R.drawable.three_day;
            case "03n":
                return R.drawable.three_night;
            case "04d":
                return R.drawable.four;
            case "04n":
                return R.drawable.four;
            case "09d":
                return R.drawable.five_day_night;
            case "09n":
                return R.drawable.five_day_night;
            case "10d":
                return R.drawable.six_day;
            case "10n":
                return R.drawable.six_night;
            case "11d":
                return R.drawable.seven_day_night;
            case "11n":
                return R.drawable.seven_day_night;
            case "13d":
                return R.drawable.eleven_day;
            case "13n":
                return R.drawable.eleven_night;
            case "50d":
                return R.drawable.fog_day;
            case "50n":
                return R.drawable.fog_night;
        }
        return weather_image;
    }
}
