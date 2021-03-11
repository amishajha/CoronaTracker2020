package com.example.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
private int positionCountry;
TextView tvcountry,tvcases,tvRecovered,tvCritical,tvActive,tvTodaycase,tvTotalcases,tvTotalDeaths,tvTodayDeaths;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Details of"+AffectedActivity.countrymodelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvcountry = findViewById(R.id.tvCountry);
        tvcases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodaycase = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvcountry.setText(AffectedActivity.countrymodelList.get(positionCountry).getCountry());
        tvcases.setText(AffectedActivity.countrymodelList.get(positionCountry).getCases());
        tvRecovered.setText(AffectedActivity.countrymodelList.get(positionCountry).getRecovered());
        tvCritical.setText(AffectedActivity.countrymodelList.get(positionCountry).getCritical());
        tvActive.setText(AffectedActivity.countrymodelList.get(positionCountry).getActive());
        tvTodaycase.setText(AffectedActivity.countrymodelList.get(positionCountry).getTodaycases());
        tvTotalDeaths.setText(AffectedActivity.countrymodelList.get(positionCountry).getTodayDeaths());
        tvTodayDeaths.setText(AffectedActivity.countrymodelList.get(positionCountry).getTodayDeaths());

    }
}