package com.sn.trainxtrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sn.trainxtrain.model.*;
import com.sn.trainxtrain.view.MainView;

import java.util.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Button mButtonSearch;
    private Button mButtonSearchVersParis;
    private TextView mTextViewResult;
    private LinearLayout mLayout;
    private LinearLayout.LayoutParams params;

    private MainView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTheme(android.R.style.Theme_DeviceDefault_Light);

        view = new MainView();
        //Branchement UI
        mButtonSearch = findViewById(R.id.main_button_search);
        mButtonSearchVersParis = findViewById(R.id.main_button_search2);
        mTextViewResult = findViewById(R.id.text_view_result);
        mLayout = findViewById(R.id.main_layout);



        //Pour faire des Requetes WEB
        retrofit = new Retrofit.Builder()
                .baseUrl("https://ressources.data.sncf.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                //.baseUrl("https://jsonplaceholder.typicode.com/")
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //Click du bouton
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResults("ToLyon");
            }
        });
        mButtonSearchVersParis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResults("ToParis");
            }
        });
    }

    private void getResults(String toWhere){
        String origin = null;
        String dest = null;
        if (toWhere == "ToLyon"){
            origin = "PARIS (intramuros)";
            dest = "LYON (gares intramuros)";
            mTextViewResult.setText("Paris ----> Lyon");
        } else if (toWhere == "ToParis"){
            origin = "LYON (gares intramuros)";
            dest = "PARIS (intramuros)";
            mTextViewResult.setText("Lyon ----> Paris");
        }
        Call<TrainResult> call = jsonPlaceHolderApi.getResult("tgvmax","-date","100","OUI",dest,origin);
        call.enqueue(new Callback<TrainResult>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<TrainResult> call, Response<TrainResult> response) {

                if (!response.isSuccessful()){
                    mTextViewResult.setText("Code: " + response.code());
                    return;
                }
                TrainResult trainResult = response.body();
                Records[] records = trainResult.getRecords();

                if (records.length == 0){
                    mTextViewResult.setText("Pas de train gratuits");
                    return;
                }

                view.showRecords(MainActivity.this,toWhere,records,mLayout);


            }

            @Override
            public void onFailure(Call<TrainResult> call, Throwable t) {
                mTextViewResult.setText(t.getMessage());
            }
        });

    }

}