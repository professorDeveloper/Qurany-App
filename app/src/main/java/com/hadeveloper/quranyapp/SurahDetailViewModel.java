package com.hadeveloper.quranyapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class    SurahDetailViewModel extends ViewModel {

    public SurahDetailRepo surahDetailRepo;

    public SurahDetailViewModel(){
        surahDetailRepo =new SurahDetailRepo();

    }

    public LiveData<SurahDetailResponse> getSurahDetail(String lan,int id){
        return surahDetailRepo.getDetail(lan,id);


    }

}
