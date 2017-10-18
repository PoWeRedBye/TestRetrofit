package com.example.maxim_ozarovskiy.testretrofitrestapp.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters.CityCheckDBAdapter;
import com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters.CityCheckListAdapter;
import com.example.maxim_ozarovskiy.testretrofitrestapp.R;
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityModel;
import com.example.maxim_ozarovskiy.testretrofitrestapp.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class CityCheckListActivity extends AppCompatActivity implements CityCheckListAdapter.ItemCityClickListener<CityModel>, CityCheckListAdapter.DeleteCityClickListener<CityModel> {

    private CityCheckDBAdapter dbAdapter;
    private List<CityModel> cityModelList;
    private RecyclerView recyclerView;
    private CityCheckListAdapter cityCheckListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton addCity;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_check_list);
        initUI();
        setSupportActionBar(toolbar);

        dbAdapter = new CityCheckDBAdapter(this);
        dbAdapter.open();
        cityModelList = new ArrayList<CityModel>();
        cityModelList = dbAdapter.getDataFromDB();

        cityCheckListAdapter = new CityCheckListAdapter(this, cityModelList, this, this);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        recyclerView.setItemAnimator(new FadeInLeftAnimator());

        cityCheckListAdapter.setMode(Attributes.Mode.Single);
        recyclerView.setAdapter(cityCheckListAdapter);
        cityCheckListAdapter.notifyDataSetChanged();

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewCityAddActivity.class);
                startActivity(intent);
            }
        });


    }

    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }

    private void initUI() {
        setTitle("My Weather");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.city_check_recycler);
        addCity = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void itemClick(CityModel v, int position) {
        Intent intent = new Intent(getApplicationContext(), WeatherInfoActivity.class);
        intent.putExtra("CityModel", v);
        startActivity(intent);
    }

    @Override
    public void deleteCityClick(final CityModel v, final int position) {
        int id = Integer.parseInt(v.getId());
        dbAdapter.delRec(id);
        cityModelList.remove(position);
        cityCheckListAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), R.string.city_deleted, Toast.LENGTH_SHORT).show();

    }
}
