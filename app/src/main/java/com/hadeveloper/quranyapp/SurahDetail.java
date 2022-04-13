package com.hadeveloper.quranyapp;

import com.google.gson.annotations.SerializedName;

public class SurahDetail {

    @SerializedName("id")
    private int id;
    @SerializedName("sura")
    private int surah;
    @SerializedName("aya")
    private int aya;
    @SerializedName("arabic_text")
    private String arabic_text;
    @SerializedName("translation")
    private String translation;
    @SerializedName("footnotes")
    private String footnotes;

    public SurahDetail(int id, int surah, int aya, String arabic_text, String translation, String footnotes) {
        this.id = id;
        this.surah = surah;
        this.aya = aya;
        this.arabic_text = arabic_text;
        this.translation = translation;
        this.footnotes = footnotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurah() {
        return surah;
    }

    public void setSurah(int surah) {
        this.surah = surah;
    }

    public int getAya() {
        return aya;
    }

    public void setAya(int aya) {
        this.aya = aya;
    }

    public String getArabic_text() {
        return arabic_text;
    }

    public void setArabic_text(String arabic_text) {
        this.arabic_text = arabic_text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }
}
