package com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.testretrofitrestapp.R;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.WeatherForDayModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 16.07.2017.
 */

public class WeatherInfoActivityAdapter extends RecyclerView.Adapter<WeatherInfoActivityAdapter.ViewHolder> {

    private String weatherImage;
    private int weather_image;
    private Context context;
    private List<WeatherForDayModel> list;
    private ItemClickListener<WeatherForDayModel> itemListener;

    private long date;

    private double tempDayCels;
    private double tempNightCels;
    private int dayCels;
    private int nightCels;

    public WeatherInfoActivityAdapter(Context context, List<WeatherForDayModel> list, ItemClickListener<WeatherForDayModel> itemListener) {
        this.context = context;
        this.list = list;
        this.itemListener = itemListener;

    }

    @Override
    public WeatherInfoActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_per_day_item, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeatherInfoActivityAdapter.ViewHolder holder, final int position) {

        date = list.get(position).getDt();
        Date time = new Date();
        time.setTime(date * 1000);
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");
        String formattedDate = format.format(time);

        tempDayCels = list.get(position).getTemperatureModel().getDay();
        dayCels = (int) tempDayCels;

        tempNightCels = list.get(position).getTemperatureModel().getNight();
        nightCels = (int) tempNightCels;

        holder.data_card.setText(formattedDate);
        holder.temp_night_card.setText(String.valueOf(nightCels) + "°C");
        holder.temp_day_card.setText(String.valueOf(dayCels) + "°C");
        weatherImage = list.get(position).getWeatherDescriptionModel().get(0).getIcon();
        weather_image = getIcon();
        Picasso.with(context).load(weather_image).into(holder.w_status_card);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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
        RelativeLayout relativeLayout;
        ImageView w_status_card;
        TextView temp_day_card;
        TextView temp_night_card;
        TextView data_card;

        public ViewHolder(View v) {
            super(v);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relative_weather_per_day_item_cube);
            w_status_card = (ImageView) v.findViewById(R.id.weather_status_icon_item_cube);
            temp_day_card = (TextView) v.findViewById(R.id.max_temp_per_day_item);
            temp_night_card = (TextView) v.findViewById(R.id.min_temp_per_day_item);
            data_card = (TextView) v.findViewById(R.id.date_info_item_cube);
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

    public interface ItemClickListener<T> {
        void ItemClick(T v, int position);
    }
}
