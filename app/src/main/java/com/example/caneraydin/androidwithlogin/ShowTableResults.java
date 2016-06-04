package com.example.caneraydin.androidwithlogin;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.SharedElementCallback;
import android.app.TaskStackBuilder;
import android.app.assist.AssistContent;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.caneraydin.androidwithlogin.DatabaseHandler;
import com.example.caneraydin.androidwithlogin.domains.Color;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowTableResults
        extends Activity implements View.OnClickListener {

    public String TAG;
    public DatabaseHandler dbHandler;
    public RelativeLayout resultsTable;
    public String username;
    ImageView homeButtonImg;

    TableLayout tbl;

   public  int width,height;

    MediaPlayer mMediaPlayer;

    public void onBackPressed() {
        Log.d(TAG, "SHOWTABLERESULTS  backbuttpn");

        final Context ctx = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle("Ana ekrana geri gitmek istediniz...");
        //builder.setMessage("Cikilsin mi?");
        builder.setPositiveButton("Geri git", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // terminateProcesses();
                Intent intent = new Intent(ShowTableResults.this, MainActivity .class);
                intent.putExtra("username", username);
                intent.putExtra("wasDbExisted", true);//true olur hehralde bu//// TODO: 6/4/2016
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Burda kal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                return;
            }
        });
        builder.show();

    }

    @Override
    protected void onPause() {
        Log.d(TAG, "SHOWTABLERESULTS onpause***********");
        super.onPause();

        if(mMediaPlayer.isPlaying()&&mMediaPlayer!=null ){
            Log.d(TAG, "SHOWTABLERESULTS onpause mediaisplay not null isplaying");
            mMediaPlayer.pause();
        }

        Log.d(TAG, "SHOWTABLERESULTS onpause ends***********");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "SHOWTABLERESULTS ondestry");
        super.onDestroy();

        if(mMediaPlayer!=null ) {
            Log.d(TAG, "SHOWTABLERESULTS ondestry mediaisplay not null");
            mMediaPlayer.release();
        }
        else{
            Log.d(TAG, "SHOWTABLERESULTS onondestry mediaisplaynull");//// TODO: 6/2/2016 sil test icin
        }

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "SHOWTABLERESULTS onstop");
        super.onStop();

        if(mMediaPlayer!=null ) {
            Log.d(TAG, "SHOWTABLERESULTS onstop mediaisplay not null");
            mMediaPlayer.stop();
            //  mMediaPlayer.release();
        }
        else{
            Log.d(TAG, "SHOWTABLERESULTS onstop mediaisplayisplaying"+mMediaPlayer.isPlaying());//// TODO: 6/2/2016 sil test icin
        }
    }

    @Override
    protected void onRestart() {//// TODO: 5/31/2016 dogru mu emin degili
        Log.d(TAG, "SHOWTABLERESULTS onrestart");
        super.onRestart();
    }


    public void onCreate(Bundle bundle) {


        Log.d(TAG, "SHOWTABLERESULTS oncreate");
        super.onCreate(bundle);
        this.setContentView(R.layout.dialog_table_results);
        this.username = this.getIntent().getExtras().getString("username");
        this.resultsTable = (RelativeLayout)this.findViewById(R.id.relative_table_results);
        this.dbHandler = new DatabaseHandler((Context)this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        homeButtonImg = (ImageView) findViewById(R.id.home_imgbtn);

        homeButtonImg.setOnClickListener(this);

        mMediaPlayer = new MediaPlayer();
    }

    public void onResume() {

        mMediaPlayer = MediaPlayer.create(this, R.raw.sound_showtableresultsactivity);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();//.release();// STOPSHIP: 6/2/2016

        Log.d((String)this.TAG, (String)"SHOWTABLERESULTS onresume");
        super.onResume();
        new ArrayList();
        List<String> list = this.dbHandler.getAllObjectObjectName(this.username);
        Log.v(this.TAG, ("size :" + list.size()));

      //  View table_layout = this.getLayoutInflater().inflate(R.layout.dialog_table_results, null);
tbl = (TableLayout)findViewById(R.id.dialog_table_results);

        RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height*90/100,height*90/100);

      //  resultsTable.addView(tbl,rLayParams);
     //   resultsTable.setLayoutParams(rLayParams);

        tbl.getLayoutParams().height = height;
        tbl.getLayoutParams().width = width;
        tbl.requestLayout();

        for (int i = 0; i < list.size()*6; ++i) {
            View view = this.getLayoutInflater().inflate(R.layout.table_row, null);
            String objectName = (String)list.get(i%10);//// TODO: 6/4/2016 gecic iburasxi silinecek

            ((TextView)view.findViewById(R.id.object_name)).setText((CharSequence)objectName);

            TextView[] arrtextView = new TextView[9];
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            arrtextView[0] = (TextView)view.findViewById(R.id.true_matching);
            arrtextView[1] = (TextView)view.findViewById(R.id.half_matching);
            arrtextView[2] = (TextView)view.findViewById(R.id.false_matching);

            arrtextView[3] = (TextView)view.findViewById(R.id.true_counting);
            arrtextView[4] = (TextView)view.findViewById(R.id.half_counting);
            arrtextView[5] = (TextView)view.findViewById(R.id.false_counting);

            arrtextView[6] = (TextView)view.findViewById(R.id.true_choosing);
            arrtextView[7] = (TextView)view.findViewById(R.id.half_choosing);
            arrtextView[8] = (TextView)view.findViewById(R.id.false_choosing);

            for (int j = 1; j < 4; ++j) {
                for (int k = 1; k < 4; ++k) {
                    Log.d((String)this.TAG, (String)" -");
                    Log.d((String)this.TAG, (String)("k, , i, j total " + k + " " + j + " " + i + " " + (-1 + (k + 3 * (j - 1)))));
                    arrtextView[-1 + (k + 3 * (j - 1))].setText((CharSequence)String.valueOf((int)this.dbHandler.getCountForTable(j, (k + k) % 3, objectName, this.username)));
                }
            }
            tbl.addView(view);

        }

        View table_row = this.getLayoutInflater().inflate(R.layout.table_row, null);

        ((TextView)table_row.findViewById(R.id.object_name)).setText("TOPLAM");
        ((TextView)table_row.findViewById(R.id.object_name)).setBackgroundColor(android.graphics.Color.WHITE);
        ((TextView)table_row.findViewById(R.id.object_name)).setTextColor(android.graphics.Color.MAGENTA);
        ((TextView)table_row.findViewById(R.id.object_name)).setTextSize(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ((TextView)table_row.findViewById(R.id.object_name)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        int trueMatching = this.dbHandler.getTotalCountForTable(1, 2, this.username);
        int halfMatching = this.dbHandler.getTotalCountForTable(1, 1, this.username);
        int falseMatching = this.dbHandler.getTotalCountForTable(1, 0, this.username);

       int totalMatching = falseMatching + (trueMatching + falseMatching);

        int trueCounting = this.dbHandler.getTotalCountForTable(2, 2, this.username);
        int halfCounting = this.dbHandler.getTotalCountForTable(2, 1, this.username);
        int falseCounting = this.dbHandler.getTotalCountForTable(2, 0, this.username);

       int totalCounting = falseCounting + (trueCounting + halfCounting);

        int trueChoosing = this.dbHandler.getTotalCountForTable(3, 2, this.username);
        int halfChoosing = this.dbHandler.getTotalCountForTable(3, 1, this.username);
        int falseChoosing = this.dbHandler.getTotalCountForTable(3, 0, this.username);

        int totalChoosing = falseChoosing + (trueChoosing + halfChoosing);


        ((TextView)table_row.findViewById(R.id.true_matching)).setText((CharSequence)String.valueOf((int)trueMatching));
        ((TextView)table_row.findViewById(R.id.true_matching)). setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.true_matching)) .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.true_matching)).setTextSize(20);

        ((TextView)table_row.findViewById(R.id.half_matching)).setText((CharSequence)String.valueOf((int)halfMatching));
        ((TextView)table_row.findViewById(R.id.half_matching)).     setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.half_matching))  .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.half_matching))  .setTextSize(20);

        ((TextView)table_row.findViewById(R.id.false_matching)).setText((CharSequence)String.valueOf((int)falseMatching));
        ((TextView)table_row.findViewById(R.id.false_matching)).   setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.false_matching)) .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.false_matching)) .setTextSize(20);

        ((TextView)table_row.findViewById(R.id.true_counting)).setText((CharSequence)String.valueOf((int)trueCounting));
        ((TextView)table_row.findViewById(R.id.true_counting)).  setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.true_counting))   .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.true_counting)).setTextSize(20);

        ((TextView)table_row.findViewById(R.id.half_counting)).setText((CharSequence)String.valueOf((int)halfCounting));
        ((TextView)table_row.findViewById(R.id.half_counting)).setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.half_counting))  .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.half_counting)) .setTextSize(20);

        ((TextView)table_row.findViewById(R.id.false_counting)).setText((CharSequence)String.valueOf((int)falseCounting));
        ((TextView)table_row.findViewById(R.id.false_counting)). setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.false_counting))  .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.false_counting))  .setTextSize(20);

        ((TextView)table_row.findViewById(R.id.true_choosing)).setText((CharSequence)String.valueOf((int)trueChoosing));
        ((TextView)table_row.findViewById(R.id.true_choosing)). setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.true_choosing))  .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.true_choosing))  .setTextSize(20);

        ((TextView)table_row.findViewById(R.id.half_choosing)).setText((CharSequence)String.valueOf((int)halfChoosing));
        ((TextView)table_row.findViewById(R.id.half_choosing)).   setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.half_choosing))  .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.half_choosing)) .setTextSize(20);

        ((TextView)table_row.findViewById(R.id.false_choosing)).setText((CharSequence)String.valueOf((int)falseChoosing));
        ((TextView)table_row.findViewById(R.id.false_choosing)). setBackgroundColor(android.graphics.Color.RED);
        ((TextView)table_row.findViewById(R.id.false_choosing))  .setTextColor(android.graphics.Color.GREEN);
        ((TextView)table_row.findViewById(R.id.false_choosing))  .setTextSize(20);

        tbl.addView(table_row);

        View view2 = this.getLayoutInflater().inflate(R.layout.table_last_row, null);

        int percentChoosing = 0;
        if (totalChoosing != 0) {
            percentChoosing = 100 * (trueChoosing + halfChoosing / 2) / totalChoosing;
        }

        int percentMatching = 0;
        if (totalMatching != 0) {
            percentMatching = 100 * (trueMatching + halfMatching / 2) / totalMatching;
        }

        int percentCounting = 0;
        if (totalCounting != 0) {
            percentCounting = 100 * (trueCounting + halfCounting / 2) / totalCounting;
        }


        ((TextView)view2.findViewById(R.id.last_matching)).setText("%"+String.valueOf(percentMatching));
        ((TextView)view2.findViewById(R.id.last_counting)).setText("%"+String.valueOf(percentCounting));
        ((TextView)view2.findViewById(R.id.last_choosing)).setText("%"+String.valueOf(percentChoosing));

        tbl.addView(view2);

      //  RelativeLayout.LayoutParams rLayParamsDirectiveBtnImg = new RelativeLayout.LayoutParams(999,999);

      //  rLayParamsDirectiveBtnImg.addRule(RelativeLayout.ALIGN_PARENT_TOP);
       // rLayParamsDirectiveBtnImg.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

      //  homeButtonImg = new ImageView(this);
      //  homeButtonImg.setBackgroundResource(R.drawable.ic_back);
       // resultsTable.addView(homeButtonImg,rLayParamsDirectiveBtnImg);
       // homeButtonImg.setOnClickListener(this);


       // this.dbHandler.getCounttForTable();
       // this.dbHandler.getAllTrainingResponse();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "SHOWTABLERESULTS  onlick");

        if (v.equals(homeButtonImg)) {
            Log.d(TAG, "SHOWTABLERESULTS  homeButtonImg clicked");
            final Context ctx = this;
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setCancelable(true);
            builder.setTitle("Ana ekrana geri gitmek istediniz...");
            //builder.setMessage("Cikilsin mi?");
            builder.setPositiveButton("Geri git", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // terminateProcesses();
                    Intent intent = new Intent(ShowTableResults.this, MainActivity .class);
                    intent.putExtra("username", username);
                    intent.putExtra("wasDbExisted", true);//true olur hehralde bu//// TODO: 6/4/2016
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("Burda kal", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    return;
                }
            });
            builder.show();


        }



    }
}