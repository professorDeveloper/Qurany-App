package com.hadeveloper.quranyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadeveloper.quranyapp.model.SurahDetail;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailAdapter extends RecyclerView.Adapter<SurahDetailAdapter.ViewHolder> {
    private Context context;
    List<SurahDetail> detailList;

    public SurahDetailAdapter(Context context, List<SurahDetail> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_detail_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ayaNo.setText(String.valueOf(detailList.get(position).getAya()));
        holder.arabic_text.setText(detailList.get(position).getArabic_text());
        holder.translation.setText(detailList.get(position).getTranslation());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
    public void filter(ArrayList<SurahDetail> details){
        detailList = details;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ayaNo, arabic_text, translation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ayaNo = itemView.findViewById(R.id.aya_no);
            arabic_text = itemView.findViewById(R.id.arabic_text);
            translation = itemView.findViewById(R.id.transition_detail);
        }
    }
}
