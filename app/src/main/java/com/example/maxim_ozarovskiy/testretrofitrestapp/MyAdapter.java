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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String weatherImage;
    private int weather_image;
    private Context context;
    private List<ListModel> list;
    private ItemClickListener<ListModel> itemListener;

    private long date;

    private double tempDayCels;
    private double tempNightCels;
    private Double dayKelvin;
    private Double nightKelvin;
    private int dayCels;
    private int nightCels;

    public MyAdapter(Context context, List<ListModel> list, ItemClickListener<ListModel> itemListener) {
        this.context = context;
        this.list = list;
        this.itemListener = itemListener;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, null);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        date = list.get(position).getDt();
        Date time = new Date();
        time.setTime(date*1000);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        String formattedDate = format.format(time);


        dayKelvin = list.get(position).getTemp().getDay();
        tempDayCels = dayKelvin - 273;
        dayCels = (int) tempDayCels;

        nightKelvin = list.get(position).getTemp().getNight();
        tempNightCels = nightKelvin - 273;
        nightCels =(int) tempNightCels;

        holder.data_card.setText(formattedDate);
        holder.temp_night_card.setText(String.valueOf(nightCels)+ " °C");
        holder.temp_day_card.setText(String.valueOf(dayCels)+ " °C");
        weatherImage = list.get(position).getWeather().get(0).getIcon();
        weather_image = getIcon();
        Picasso.with(context).load(weather_image).into(holder.w_status_card);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.ItemClick(list.get(position), position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView w_status_card;
        TextView temp_day_card;
        TextView temp_night_card;
        TextView data_card;


        public ViewHolder(View v) {
            super(v);

            cardView = (CardView) v.findViewById(R.id.card_1);
            w_status_card = (ImageView) v.findViewById(R.id.weather_status_card_1);
            temp_day_card = (TextView) v.findViewById(R.id.text_temp_day_card_1);
            temp_night_card = (TextView) v.findViewById(R.id.text_temp_niht_card_1);
            data_card = (TextView) v.findViewById(R.id.date_card_1);


        }
    }

    private void getDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);


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
    public interface ItemClickListener<T> {
        void ItemClick(T v, int position);
    }
}
