package com.sn.trainxtrain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.sn.trainxtrain.model.*;

public class MainActivityOld extends AppCompatActivity {

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Button mButtonSearch;

    private TextView mTextViewResult;
    private LinearLayout mLayout;
    private LinearLayout.LayoutParams params;
    private String[] days = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};

    final private int marges = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTheme(android.R.style.Theme_DeviceDefault_Light);
        //Branchement UI
        mButtonSearch = findViewById(R.id.main_button_search);

        mTextViewResult = findViewById(R.id.text_view_result);
        mLayout = findViewById(R.id.main_layout);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);

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
                //mTextViewResult.setText("");

            }
        });

    }

    private void getPosts(){

        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order","desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    mTextViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "user ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    mTextViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mTextViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    mTextViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Comment> comments = response.body();

                for (Comment comment: comments){
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getId() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    mTextViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                mTextViewResult.setText(t.getMessage());
            }
        });
    }
}
