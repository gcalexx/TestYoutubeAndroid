package com.example.testyoutube.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testyoutube.R;
import com.example.testyoutube.services.SearchService;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String youTubeKey;
    private SearchService searchService;
    private Retrofit retrofit;
    public static final String Base_URL = "https://www.googleapis.com/youtube/v3/";
    private static final int tryCountMax = 3;
    private static YouTube youTube;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubeKey = "AIzaSyBkXHLxk-kCajYObs1sZOBDQVu2gabge9Y";

        requestRetrofit();

    }

    public void requestRetrofit(){

        retrofitClient();

        searchService = retrofit.create(SearchService.class);

        Call<SearchListResponse> call = searchService.search("id,snippet", "eminem", "video", "2", youTubeKey);

        call.enqueue(new Callback<SearchListResponse>() {

            @Override
            public void onResponse(Call<SearchListResponse> call, Response<SearchListResponse> response) {
                System.out.println("Response: "+response);

                if(response.isSuccessful()){
                    System.out.println("Size response => "+response.body().getItems().size() );
                }else{
                    System.out.println("Error: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SearchListResponse> call, Throwable t) {
                System.out.println("Mensaje => "+t.getMessage());
            }

        });

    }

    public void retrofitClient() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        okhttp3.Response response = chain.proceed(request);

                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < tryCountMax) {
                            tryCount++;
                            response = chain.proceed(request);
                        }

                        return response;
                    }
                })
                .build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
