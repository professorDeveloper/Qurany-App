package com.hadeveloper.quranyapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahRepo {
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public  SurahRepo(){

        jsonPlaceHolderApi = Api.getRetrofit().create(JsonPlaceHolderApi.class);
    }

    public LiveData<SurahResponse> getSurah(){


        MutableLiveData<SurahResponse> data =new MutableLiveData<>();

        jsonPlaceHolderApi.getSurah().enqueue(new Callback<SurahResponse>() {
            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                if (response.isSuccessful()){
                    data.setValue(response.body());
                    Log.e("AAA","OnFailure"+response.code());
                }
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                Log.e("AAA","OnFailure"+t.getMessage());


            }
        });

        return data;

    }





}
