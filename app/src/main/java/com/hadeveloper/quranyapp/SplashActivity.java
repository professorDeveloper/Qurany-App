package com.hadeveloper.quranyapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends Activity {
    // Splash screen timer
    public static final  int SPLASH_TIME_OUT = 4600;
    TextView textView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
//package com.hadeveloper.quranyapp.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.LifecycleOwner;
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.hadeveloper.quranyapp.R;
//import com.hadeveloper.quranyapp.adapter.SurahDetailAdapter;
//import com.hadeveloper.quranyapp.commo.Common;
//import com.hadeveloper.quranyapp.model.SurahDetail;
//import com.hadeveloper.quranyapp.viewmodel.SurahDetailViewModel;
//import com.tapadoo.alerter.Alerter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SurahDetailActivity extends AppCompatActivity {
//
//    private TextView surahName,surahType,surahTranslation;
//    private int no;
//    private RecyclerView recyclerView;
//    private List<SurahDetail> list;
//    private SurahDetailAdapter adapter;
//    private SurahDetailViewModel surahDetailViewModel;
//    private String urdu = "urdu_junagarhi";
//    private String hindi = "hindi_omari";
//    private String english = "english_hilali_khan";
//    String uzbek = "uzbek_mansour";
//    String turkish = "turkish_rwwad";
//    String tajik = "tajik_arifi";
//    String french = "french_montada";
//    String german = "german_bubenheim";
//    String indonesianSabiq = "indonesian_sabiq";
//    String uygur = "uyghur_saleh";
//    String khemer = "khmer_cambodia";
//    int service;
//    private EditText searchView;
//    private ImageButton settingButton;
//
//    private RadioGroup radioGroup,audio_group;
//    private RadioButton translationButton , qariSelectButton;
//    private String lan;
//
//    private String qariAB = "basit";
//    private String qariAW = "afs";
//    private String qr;
//    Handler handler = new Handler();
//    SeekBar seekBar;
//    TextView startTime , totalTime;
//    ImageButton playButton;
//    MediaPlayer mediaPlayer;
//    private String str;
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.surah_detail_layout_item);
//
//       surahName = findViewById(R.id.surah_name);
//        surahTranslation = findViewById(R.id.transition);
//        surahType = findViewById(R.id.type);
//        no = getIntent().getIntExtra(Common.SURAH_NO, 0);
//        surahName.setText(getIntent().getStringExtra(Common.SURAH_NAME));
//        surahType.setText(getIntent().getStringExtra(Common.SURAH_TYPE) + " Nozil Bo`lgan  " +
//                getIntent().getIntExtra(Common.SURAH_TOTAL_AYA, 0) + " Oyat ");
//        surahTranslation.setText(getIntent().getStringExtra(Common.SURAH_TRANSLATION));
//        surah_detail_rv = findViewById(R.id.surah_detail_rv);
//        list = new ArrayList<>();
//        surah_detail_rv.setHasFixedSize(true);
//        setting = findViewById(R.id.setting_button);
//        surahTranslation(uzbek, no);
//
//
//        try {
//            listenAudio(qariAB);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        settingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SurahDetailActivity.this,
//                        R.style.BottomSheetDialogTheme);
//
//                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//                View view = inflater.inflate(R.layout.bottom_sheet_layou,
//                        findViewById(R.id.sheetConteainer));
//
//                view.findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        radioGroup = view.findViewById(R.id.translation_group);
//                        audio_group = view.findViewById(R.id.audio_group);
//
//                        int selectedId = radioGroup.getCheckedRadioButtonId();
//                        translationButton = view.findViewById(selectedId);
//                        translationButton = view.findViewById(selectedId);
//                        if (selectedId == -1) {
//                            Alerter.create(SurahDetailActivity.this)
//                                    .setText("Hech Narsa Tanlanmagan !")
//                                    .setTextAppearance(R.style.ErrorAlerter)
//                                    .setBackgroundColorRes(R.color.design_default_color_error)
//                                    .setIcon(R.drawable.ic_baseline_error_outline_24)
//                                    .setDuration(3000)
//                                    .enableIconPulse(true)
//                                    .enableVibration(true)
//                                    .disableOutsideTouch()
//                                    .enableProgress(true)
//                                    .setProgressColorRes(R.color.white)
//                                    .show()
//                            ;
//
//                        } else {
//                            Alerter.create(SurahDetailActivity.this)
//                                    .setText("Selected !")
//                                    .setTextAppearance(R.style.DoneAlerter)
//                                    .setBackgroundColorRes(R.color.alerter_default_success_background)
//                                    .setIcon(R.drawable.ic_baseline_done_24)
//                                    .setDuration(3000)
//                                    .enableIconPulse(true)
//                                    .enableVibration(true)
//                                    .disableOutsideTouch()
//                                    .enableProgress(true)
//                                    .setProgressColorRes(R.color.white)
//                                    .show()
//                            ;
//                        }
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
//                        surahTranslation(lan,no);
//                        bottomSheetDialog.dismiss();
//
//                        int id = audio_group.getCheckedRadioButtonId();
//                        qariSelectButton = view.findViewById(id);
//                        if(qariSelectButton.getText().toString().trim().toLowerCase().equals("abdul basit murattal")){
//                            qr = qariAB;
//                            service=7;
//                        }else if(qariSelectButton.getText().toString().trim().toLowerCase().equals("al afasee")){
//                            qr = qariAW;
//                            service=8;
//                        }
//                        mediaPlayer.reset();
//                        mediaPlayer.release();
//                        try {
//                            listenAudio(qr);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        bottomSheetDialog.dismiss();
//
//                    }
//                });
//                bottomSheetDialog.setContentView(view);
//                bottomSheetDialog.show();
//            }
//        });
//
//    }
//
//
//    private void init(){
//        surahName = findViewById(R.id.surah_name);
//        surahType = findViewById(R.id.type);
//        surahTranslation = findViewById(R.id.transition);
//        recyclerView = findViewById(R.id.surah_detail_rv);
//        searchView = findViewById(R.id.search_view);
//        settingButton = findViewById(R.id.setting_button);
//
//    }
//
//    private void surahTranslation(String lan, int id) {
//
//        if(list.size()>0){
//            list.clear();
//        }
//
//        surahDetailViewModel = new ViewModelProvider(this).get(SurahDetailViewModel.class);
//        surahDetailViewModel.getSurahDetail(lan,id).observe(this, surahDetailResponse -> {
//
//
//            for (int i=0;i<surahDetailResponse.getList().size();i++){
//                list.add(new SurahDetail(surahDetailResponse.getList().get(i).getId(),
//                        surahDetailResponse.getList().get(i).getSurah(),
//                        surahDetailResponse.getList().get(i).getAya(),
//                        surahDetailResponse.getList().get(i).getArabic_text(),
//                        surahDetailResponse.getList().get(i).getTranslation(),
//                        surahDetailResponse.getList().get(i).getFootnotes()));
//            }
//
//            if(list.size()!=0){
//                adapter = new SurahDetailAdapter(this,list);
//                recyclerView.setAdapter(adapter);
//            }
//
//        });
//    }
//
//    private void filter(String id) {
//        ArrayList<SurahDetail> arrayList = new ArrayList<>();
//        for(SurahDetail detail : list){
//            if(String.valueOf(detail.getId()).contains(id)){
//                arrayList.add(detail);
//            }
//        }
//        adapter.filter(arrayList);
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    private void listenAudio(String qari) throws IOException {
//
//        playButton = findViewById(R.id.play_button);
//        startTime = findViewById(R.id.start_time);
//        totalTime = findViewById(R.id.total_time);
//        seekBar = findViewById(R.id.seekbar);
//
//        mediaPlayer = new MediaPlayer();
//        seekBar.setMax(100);
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mediaPlayer.isPlaying()){
//                    handler.removeCallbacks(updater);
//                    mediaPlayer.pause();
//                    playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
//                }else{
//                    mediaPlayer.start();
//                    playButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
//                    updateSeekBar();
//                }
//            }
//        });
//
//        preparedMediaPlayer(qari);
//
//        seekBar.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                SeekBar seekBar = (SeekBar) v;
//                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
//                mediaPlayer.seekTo(playPosition);
//                startTime.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
//                return false;
//            }
//        });
//
//        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                seekBar.setSecondaryProgress(percent);
//            }
//        });
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                seekBar.setProgress(0);
//                playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
//                startTime.setText("0:00");
//                totalTime.setText("0:00");
//                mediaPlayer.reset();
//                try {
//                    preparedMediaPlayer(qari);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private void preparedMediaPlayer(String qari) throws IOException {
//
//        if(no<10){
//            str = "00"+no;
//        }else if(no<100){
//            str = "0"+no;
//        }else if(no>=100){
//            str = String.valueOf(no);
//        }
//        //https://download.quranicaudio.com/quran/abdul_wadood_haneef_rare/001.mp3
//        mediaPlayer.setDataSource("https://server"+service+".mp3quran.net/"+qari+"/"+str.trim()+".mp3");
//        mediaPlayer.prepare();
//        totalTime.setText(timeToMilliSecond(mediaPlayer.getDuration()));
//    }
//
//
//    private Runnable updater = new Runnable() {
//        @Override
//        public void run() {
//            updateSeekBar();
//            long curentDuration = mediaPlayer.getCurrentPosition();
//            startTime.setText(timeToMilliSecond(curentDuration));
//        }
//    };
//
//    private void updateSeekBar(){
//        if(mediaPlayer.isPlaying()){
//            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) *100));
//            handler.postDelayed(updater,1000);
//        }
//    }
//
//    private String timeToMilliSecond(long milliSecond){
//        String timerString = "";
//        String secondString ;
//
//        int hours = (int) (milliSecond /(1000 * 60 * 60));
//        int minutes = (int) (milliSecond % (1000 * 60 * 60)) / (1000 * 60);
//        int second = (int) ((milliSecond % (1000 * 60 * 60))% (1000 * 60) /1000);
//
//        if(hours>0){
//            timerString = hours + ":";
//        }
//        if(second < 10){
//            secondString = "0" + second;
//        }else{
//            secondString = "" + second;
//        }
//        timerString = timerString + minutes + ":" + secondString;
//        return timerString;
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        if(mediaPlayer.isPlaying()) {
//            handler.removeCallbacks(updater);
//            mediaPlayer.pause();
//            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onStop() {
//        if(mediaPlayer.isPlaying()) {
//            handler.removeCallbacks(updater);
//            mediaPlayer.pause();
//            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
//        }
//        super.onStop();
//    }
//
//    @Override
//    protected void onPause() {
//        if(mediaPlayer.isPlaying()) {
//            handler.removeCallbacks(updater);
//            mediaPlayer.pause();
//            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
//        }
//        super.onPause();
//    }
//}