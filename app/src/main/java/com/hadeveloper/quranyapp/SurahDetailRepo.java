package com.hadeveloper.quranyapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hadeveloper.quranyapp.network.Api;
import com.hadeveloper.quranyapp.network.JsonPlaceHolderApi;
import com.hadeveloper.quranyapp.response.SurahDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahDetailRepo {

    JsonPlaceHolderApi jsonPlaceHolderApi;

    public SurahDetailRepo() {

        jsonPlaceHolderApi = Api.getInstance().create(JsonPlaceHolderApi.class);

    }

    public LiveData<SurahDetailResponse> getDetail(String lan, int id) {

        MutableLiveData<SurahDetailResponse> data = new MutableLiveData<>();

        jsonPlaceHolderApi.getSurahDetail(lan, id).enqueue(new Callback<SurahDetailResponse>() {
            @Override
            public void onResponse(Call<SurahDetailResponse> call, Response<SurahDetailResponse> response) {

                if (response.isSuccessful()) {
                    data.setValue(response.body());
                    Log.e("AAA","OnFailure"+response.code());

                }
            }

            @Override
            public void onFailure(Call<SurahDetailResponse> call, Throwable t) {
                Log.e("AAA","OnFailure"+t.getMessage());

                data.setValue(null);
            }
        });

        return data;
    }
}
