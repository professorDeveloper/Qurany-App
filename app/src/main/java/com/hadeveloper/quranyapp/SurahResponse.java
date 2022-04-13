package com.hadeveloper.quranyapp;

import com.google.gson.annotations.SerializedName;
import com.hadeveloper.quranyapp.model.Surah;

import java.util.List;

public class SurahResponse {

    @SerializedName("data")
    private List<Surah> list;

    public List<Surah> getList() {
        return list;
    }

    public void setList(List<Surah> list) {
        this.list = list;
    }
}
