package com.hadeveloper.quranyapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hadeveloper.quranyapp.model.Surah;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements SurahListener {
    RecyclerView recyclerView;
    SurahAdapter surahAdapter;
    List<Surah> surahList;
    private SurahViewModel surahViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.alerter_dialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations =
                    R.style.Animation_AppCompat_Dialog;
            Button btnTryAgain = dialog.findViewById(R.id.bt_try_again);
            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();

                }
            });
            dialog.show();
        } else {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.alerter_dialog);

            dialog.hide();

        }


        recyclerView = findViewById(R.id.surahRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        surahList = new ArrayList<>();
        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);

        surahViewModel.getSurah().observe(this, surahResponse -> {

            for (int i = 0; i < surahResponse.getList().size(); i++) {

                surahList.add(new Surah(surahResponse.getList().get(i).getNumber(),
                        String.valueOf(surahResponse.getList().get(i).getName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishNameTranslation()),
                        surahResponse.getList().get(i).getNumberOfAyahs(),
                        String.valueOf(surahResponse.getList().get(i).getRevelationType()))
                );
            }
            if (surahList.size()!=0){

                surahAdapter =new SurahAdapter(surahList,this,this);
                recyclerView.setAdapter(surahAdapter);
                surahAdapter.notifyDataSetChanged();
            }

        });


    }


    @Override
    public void onSurahListener(int position) {

        Intent intent =new Intent(getApplicationContext(), SurahDetailActivity.class);
        intent.putExtra(Common.SURAH_NO,surahList.get(position).getNumber());
        intent.putExtra(Common.SURAH_NAME,surahList.get(position).getName());
        intent.putExtra(Common.SURAH_TOTAL_AYA,surahList.get(position).getNumberOfAyahs());
        intent.putExtra(Common.SURAH_TYPE,surahList.get(position).getRevelationType());
        intent.putExtra(Common.SURAH_TRANSLATION,surahList.get(position).getEnglishNameTranslation());
        startActivity(intent);
    }
}