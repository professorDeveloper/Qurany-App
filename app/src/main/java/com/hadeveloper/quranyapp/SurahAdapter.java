package com.hadeveloper.quranyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadeveloper.quranyapp.model.Surah;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {

    private List<Surah> list;
    private Context context;
    private SurahListener surahListener;

    public SurahAdapter(List<Surah> list, Context context, SurahListener surahListener) {
        this.list = list;
        this.context = context;
        this.surahListener = surahListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.
                getContext()).inflate(R.layout.surah_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.surahNo.setText(String.valueOf(list.get(position).getNumber()));
        holder.surahEnglishName.setText((list.get(position).getEnglishName()));
        holder.arabicName.setText((list.get(position).getName()));
        holder.totalAya.setText(String.valueOf(list.get(position).getNumberOfAyahs()+"\" aya\""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView surahEnglishName, totalAya, surahNo, arabicName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            surahEnglishName = itemView.findViewById(R.id.english_name);
            arabicName = itemView.findViewById(R.id.arabic_name);
            totalAya = itemView.findViewById(R.id.total_aya);
            surahNo = itemView.findViewById(R.id.number_ayah);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surahListener.onSurahListener(getAdapterPosition());
            }
        });
        }

    }
}
