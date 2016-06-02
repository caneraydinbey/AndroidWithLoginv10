package com.example.caneraydin.androidwithlogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Chico on 5/19/2016.
 */
public class CheckNetwork {

    private static String TAG = "Chic";
    private static boolean connectDialogOpen= false;
    int msg;
    //boolean isDialogLoopNeeded;

    public static boolean isOnline(Context context){
        Log.d(TAG,"checknetwork Isonline");
        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            Log.d(TAG,"checknetwork wifi on");//// değisti sistem buna gerek yok T ODO: 5/17/2016 internete bahlanmamissa text yaz internet gerekli egitiminiz yok yerelde
            return true;
        }
        else {
            Log.d(TAG,"checknetwork wifi off");
            return false;
        }
    }

    public static void showNoConnectionDialog(final Context ctx, final boolean isDialogLoopNeeded, final int msg) {//we need wifi so loop asking for wifi

        Log.d(TAG, "checknetwork showNoConnectionDialog connectDialogOpen:" + connectDialogOpen);

        String message = "";

        switch (msg) {
            case 0://login
                message = "Hesabiniza giris yapabilmeniz icin internet gerekmekte...";
                break;

            case 1://getall
                message = "Egitimleri alabilmeniz icin internet baglantisi gerekmekte...";
                break;

            case 2://sendresponse
                message = "Interneti atkiflestirip cevaplarinizi sisteme yollamaniz tavsiye edilir...";
                break;

            case 4://hictraining kalmadi
                message = "Hic egitiminiz kalmadigi icin interneti aktiflestirip egitimleri kontrol etmeniz gerekmekte...";
                break;

            case 5://checkfortraining
                message = "Yeni egitimleri almak icin internet kontrolu yapmaniz önerilir...";
                break;
        }


        if (!isOnline(ctx)){
            if (!connectDialogOpen) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setCancelable(!isDialogLoopNeeded);
                builder.setTitle(message);
                builder.setMessage("Simdi interneti aktiflestirmek ister misiniz?");
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        connectDialogOpen = false;
                        ctx.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

                    }
                });
                builder.setNegativeButton("Hayir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        connectDialogOpen = false;
                        if (isDialogLoopNeeded) {
                            showNoConnectionDialog(ctx, isDialogLoopNeeded, msg);
                        }
                        return;
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        connectDialogOpen = false;
                        if (isDialogLoopNeeded) {
                            showNoConnectionDialog(ctx, isDialogLoopNeeded, msg);
                            Log.d(TAG, "HATA checknetwork showNoConnectionDialog girmemeliydi buraya:" + connectDialogOpen);//// TODO: 6/1/2016 sil burayi buraya girmiyor test et önce
                            // cunku true ise zaten giriyrr ife gerek yok. ama trueysa dialogloop,cancelable false olur.o zaman setcancele girmemesi lazim

                        }
                        return;
                    }
                });
                connectDialogOpen = true;
                builder.show();
            }
    }
    }
}