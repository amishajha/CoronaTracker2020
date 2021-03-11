package com.example.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
 TextView tvcases,tvrecovered,tvcritical,tvactive,tvtodaycases,tvtotaldeaths,tvtodaydeaths,tvaffectedcountries;
 SimpleArcLoader simpleArcLoader;
 ScrollView scrollView;
 PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcases=   (TextView)findViewById(R.id.tvcases);
        tvrecovered=(TextView) findViewById(R.id.tvrecovered);
        tvcritical=(TextView)findViewById(R.id.tvcritical);
        tvactive=(TextView)findViewById(R.id.tvActive);
        tvtodaycases=(TextView)findViewById(R.id.tvtodaycases);
        tvtotaldeaths=(TextView)findViewById(R.id.tvtodaydeaths);
        tvtodaydeaths=(TextView)findViewById(R.id.tvtotaldeaths);
        tvaffectedcountries=(TextView)findViewById(R.id.tvAffectedcountries);
        simpleArcLoader=(SimpleArcLoader)findViewById(R.id.loader);
        scrollView=(ScrollView)findViewById(R.id.scrollStats);
        pieChart=(PieChart)findViewById(R.id.piechart);
        fetchData();



    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvcases.setText(jsonObject.getString("cases"));
                    tvrecovered.setText(jsonObject.getString("recovered"));
                    tvcritical.setText(jsonObject.getString("critical"));
                    tvactive.setText(jsonObject.getString("active"));
                    tvtodaydeaths.setText(jsonObject.getString("todayDeaths"));
                    tvtodaycases.setText(jsonObject.getString("todayCases"));
                    tvtotaldeaths.setText(jsonObject.getString("todayDeaths"));
                    tvaffectedcountries.setText(jsonObject.getString("affectedCountries"));

              pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#FFA726")));
              pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvrecovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvtotaldeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart.startAnimation();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void trackcountries(View view) {
        startActivity(new Intent(getApplicationContext(),AffectedActivity.class));

    }
}