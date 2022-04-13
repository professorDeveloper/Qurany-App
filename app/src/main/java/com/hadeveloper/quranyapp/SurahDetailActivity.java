package com.hadeveloper.quranyapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.tapadoo.alerter.Alerter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurahDetailActivity extends AppCompatActivity {


    TextView surahName, surahTranslation, surahType;
    int no;
    private String str;

    RecyclerView surah_detail_rv;
    ImageButton setting;
    private RadioGroup radioGroup;
    List<SurahDetail> list;
    private String lan;
    private RadioGroup audio_group;
    private RadioButton translationButton, qariSelectButton;
    int service;

    SurahDetailAdapter adapter;
//    String uzbek = "uzbek_mansour";
//    String english = "english_saheeh";
//    String turkish = "turkish_rwwad";
//    String tajik = "tajik_arifi";
//    String french = "french_montada";
//    String german = "german_bubenheim";
//    String indonesianSabiq = "indonesian_sabiq";
//    String hindi = "hindi_omari";
//    String uygur = "uyghur_saleh";
//    String khemer = "khmer_cambodia";
    SurahDetailViewModel viewModel;
    private String qariAB = "Abd_Al_Baset_Muratal";//
    private String qariAW = "Mishary-Al-Efasy";
    private String qariAS="Abdul-Rahman-Alsudais";
    private String qariMY="Muhammad-Ayoob";
    private String qr;
    Handler handler = new Handler();
    SeekBar seekBar;
    TextView startTime, totalTime;
    ImageButton playButton;
    MediaPlayer mediaPlayer;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_detail_activity);
        surahName = findViewById(R.id.surah_name);
        surahTranslation = findViewById(R.id.transition);
        surahType = findViewById(R.id.type);
        no = getIntent().getIntExtra(Common.SURAH_NO, 0);
        surahName.setText(getIntent().getStringExtra(Common.SURAH_NAME));
        surahType.setText(getIntent().getStringExtra(Common.SURAH_TYPE) + " Nozil Bo`lgan  " +
                getIntent().getIntExtra(Common.SURAH_TOTAL_AYA, 0) + " Oyat ");
        surahTranslation.setText(getIntent().getStringExtra(Common.SURAH_TRANSLATION));
        surah_detail_rv = findViewById(R.id.surah_detail_rv);
        list = new ArrayList<>();
        surah_detail_rv.setHasFixedSize(true);
//        setting = findViewById(R.id.setting_button);
//        surahTranslation(uzbek, no);

        try {
            listenAudio(qariAB);
        } catch (IOException e) {
            e.printStackTrace();
        }


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog bottomSheetDialog = new Dialog(SurahDetailActivity.this, R.style.BottomSheetDialogTheme);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

                View view = inflater.inflate(R.layout.bottom_sheet_layou, findViewById(R.id.sheetConteainer));
                view.findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        radioGroup = view.findViewById(R.id.translation_group);

                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        translationButton = view.findViewById(selectedId);
                        if (selectedId == -1) {
                            Alerter.create(SurahDetailActivity.this)
                                    .setText("Hech Narsa Tanlanmagan !")
                                    .setTextAppearance(R.style.ErrorAlerter)
                                    .setBackgroundColorRes(R.color.design_default_color_error)
                                    .setIcon(R.drawable.ic_baseline_error_outline_24)
                                    .setDuration(3000)
                                    .enableIconPulse(true)
                                    .enableVibration(true)
                                    .disableOutsideTouch()
                                    .enableProgress(true)
                                    .setProgressColorRes(R.color.white)
                                    .show()
                            ;

                        } else {
                            Alerter.create(SurahDetailActivity.this)
                                    .setText("Selected !")
                                    .setTextAppearance(R.style.DoneAlerter)
                                    .setBackgroundColorRes(R.color.alerter_default_success_background)
                                    .setIcon(R.drawable.ic_baseline_done_24)
                                    .setDuration(3000)
                                    .enableIconPulse(true)
                                    .enableVibration(true)
                                    .disableOutsideTouch()
                                    .enableProgress(true)
                                    .setProgressColorRes(R.color.white)
                                    .show()
                            ;
                        }
//
//                        if (translationButton.getText().toString().toLowerCase().trim().equals("uzbek")) {
//
//                            lan = uzbek;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("english")) {
//
//                            lan = english;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("turkish")) {
//
//                            lan = turkish;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("tajik")) {
//
//                            lan = tajik;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("french")) {
//
//                            lan = french;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("german")) {
//
//                            lan = german;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("indonesian")) {
//
//                            lan = indonesianSabiq;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("hindi")) {
//
//                            lan = hindi;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("uygur")) {
//
//                            lan = uygur;
//                        } else if (translationButton.getText().toString().toLowerCase().trim().equals("khemer")) {
//
//                            lan = khemer;
//                        }
                        surahTranslation(lan, no);
                        audio_group = view.findViewById(R.id.audio_group);

                        int id = audio_group.getCheckedRadioButtonId();
                        qariSelectButton = view.findViewById(id);
                        if (qariSelectButton.getText().toString().toLowerCase().trim().equals("abdul basit murattal")) {
                            qr = qariAB;

                        } else if (qariSelectButton.getText().toString().toLowerCase().trim().equals("al afasee")) {
                            qr = qariAW;
                        }
                        else if (qariSelectButton.getText().toString().toLowerCase().trim().equals("as sudais")) {
                            qr = qariAS;
                        }else if (qariSelectButton.getText().toString().toLowerCase().trim().equals("muhammad-ayoob")) {
                            qr = qariMY;
                        }
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        try {
                            listenAudio(qr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        bottomSheetDialog.dismiss();
                        bottomSheetDialog.dismiss();


                    }
                });
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

            }
        });

    }

    private void surahTranslation(String lan, int id) {

        if (list.size() > 0) {
            list.clear();
        }
        viewModel = new ViewModelProvider(this).get(SurahDetailViewModel.class);
        viewModel.getSurahDetail(lan, id).observe(this, surahDetailResponse -> {

            for (int i = 0; i < surahDetailResponse.getList().size(); i++) {

                list.add(new SurahDetail(surahDetailResponse.getList().get(i).getId(),
                        surahDetailResponse.getList().get(i).getSurah(),
                        surahDetailResponse.getList().get(i).getAya(),
                        surahDetailResponse.getList().get(i).getArabic_text(),
                        surahDetailResponse.getList().get(i).getTranslation(),
                        surahDetailResponse.getList().get(i).getFootnotes()
                ));
            }
            if (list.size() != 0) {

                adapter = new SurahDetailAdapter(this, list);
                surah_detail_rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


        });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void listenAudio(String qari) throws IOException {

        playButton = findViewById(R.id.play_button);
        startTime = findViewById(R.id.start_time);
        totalTime = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seekbar);

        mediaPlayer = new MediaPlayer();
        seekBar.setMax(100);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                } else {
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    updateSeekBar();
                }
            }
        });

        preparedMediaPlayer(qari);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                startTime.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekBar.setProgress(0);
                playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                startTime.setText("0:00");
                totalTime.setText("0:00");
                mediaPlayer.reset();
                try {
                    preparedMediaPlayer(qari);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void preparedMediaPlayer(String qari) throws IOException {

        if (no < 10) {
            str = "00" + no;
        } else if (no < 100) {
            str = "0" + no;
        } else if (no >= 100) {
            str = String.valueOf(no);
        }
        //https://download.quranicaudio.com/quran/abdul_wadood_haneef_rare/001.mp3
        mediaPlayer.setDataSource("http://www.all-quran.com/documents/"+qari+"/"+qari+"_files/"+str.trim()+".mp3");
        mediaPlayer.prepare();
        totalTime.setText(timeToMilliSecond(mediaPlayer.getDuration()));
    }


    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long curentDuration = mediaPlayer.getCurrentPosition();
            startTime.setText(timeToMilliSecond(curentDuration));
        }
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private String timeToMilliSecond(long milliSecond) {
        String timerString = "";
        String secondString;

        int hours = (int) (milliSecond / (1000 * 60 * 60));
        int minutes = (int) (milliSecond % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((milliSecond % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = "" + second;
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }


    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onPause();
    }
}