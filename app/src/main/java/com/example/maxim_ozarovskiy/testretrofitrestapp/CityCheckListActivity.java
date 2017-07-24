package com.example.maxim_ozarovskiy.testretrofitrestapp;


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
import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityCheck;
import com.example.maxim_ozarovskiy.testretrofitrestapp.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class CityCheckListActivity extends AppCompatActivity implements CityCheckListAdapter.ItemCityClickListener<CityCheck>, CityCheckListAdapter.DeleteCityClickListener<CityCheck> {

    private CityCheckDBAdapter dbAdapter;
    private List<CityCheck> cityCheckList;
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
        cityCheckList = new ArrayList<CityCheck>();
        cityCheckList = dbAdapter.getDataFromDB();

        cityCheckListAdapter = new CityCheckListAdapter(this, cityCheckList, this, this);

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
                Intent intent = new Intent(getApplicationContext(), CityAdd.class);
                startActivity(intent);
            }
        });


    }

    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }

    private void initUI() {
        setTitle("My Weather App");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.city_check_recycler);
        addCity = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void itemClick(CityCheck v, int position) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("CityCheck", v);
        startActivity(intent);
    }

    @Override
    public void deleteCityClick(final CityCheck v, final int position) {
        int id = Integer.parseInt(v.getId());
        dbAdapter.delRec(id);
        cityCheckList.remove(position);
        cityCheckListAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "City was deleted.", Toast.LENGTH_SHORT).show();

    }
}
