package com.hadeveloper.quranyapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SurahViewModel extends ViewModel {

    private SurahRepo surahRepo;
    public SurahViewModel(){

        surahRepo =new SurahRepo();

    }
    public LiveData<SurahResponse> getSurah(){

        return surahRepo.getSurah();
    }
}
