package com.example.caneraydin.androidwithlogin.games;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.caneraydin.androidwithlogin.CheckNetwork;
import com.example.caneraydin.androidwithlogin.CreateTrainingResponse;
import com.example.caneraydin.androidwithlogin.DatabaseHandler;
import com.example.caneraydin.androidwithlogin.MainActivity;
import com.example.caneraydin.androidwithlogin.R;
import com.example.caneraydin.androidwithlogin.domains.ObjectObject;
import com.example.caneraydin.androidwithlogin.domains.TrainingObject;
import com.example.caneraydin.androidwithlogin.domains.TrainingResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by caneraydin on 17.04.2016.
 */
//// TODO: 6/1/2016 leveli aliyoruz zaten artik. yollmasaya gerek yok mainden
public class ChoosingGame extends Activity implements View.OnClickListener, TextToSpeech.OnInitListener {

    String TAG = "Chic";

//// yapıldı T ODO: 5/18/2016 level check

    DatabaseHandler dbHandler;

    int trainingID,objectCount,level,score=0;

    //for demo
    String username, speech,
            KEY_THIS = "Bu,",
            KEY_AN = ", bir ",
            KEY_BEING = " olan, ",
            KEY_OBJECT_IS = " nesnesidir ",
            KEY_OBJECT = " nesnedir ";

    //// TODO: 5/21/2016 merhaba ghosgeldiniz username denebilir belki sesli ya da şarkı
   // KEY_CHOOSE_WHICH_IS = " olani seciniz...";

    String [] KEY_READ = {", şekli ", ", rengi ", ", sayısında "};

    String KEY_CHOOSE_WHICH = ", olanı seçiniz.",
         //   KEY_IN_THE_SCREEN = "Ekrandakilerden ",
            KEY_CHOOSE_THE_OBJECT_WHICH = ", nesnesini seçiniz.";

    List<TrainingObject> trainingObjectList;

    List<ObjectObject> demoObjectObject;

    List<TrainingResponse> trainingResponseList;

    byte[] imgBytes;

    Bitmap bmp;

    ImageView imageDemo, ////  5/20/2016 çünkü burda resme gerek yok answer.tıklanıyor
            imageOne,imageTwo,imageThree,imageFour,imageFive,
    backButtonImg, forwardButtonImg,
    backGrndImg;

    RelativeLayout rLayout;

    ObjectObject objectAnswer,objectResponse;//// yapıldı T ODO: 5/20/2016 ayrı ayrı da olabilirdi ama yapmadım

    TrainingResponse trainingResponse;

    Date currentDate ;

    int counter=-1, aim;

    boolean isNextLevelToGo = false,//cakisma olmasın diye
            isImageClicked = false,//resme tiklatyinca cakisma olmasin diye
    isAnimationStarted = false,
   // mPaused = false,
    isHandlerRunning = false,
           isThreadRunning = false,
    isPaused = false; // oninite ttsnin girince bakmasi icin

    private Thread thread;

    int POSITIVE_SCORE = 2, NEGATIVE_SCORE = 0, HALF_SCORE = 1, LEVEL_SCORE = 0;

    TrainingObject trainingObject;

    TextToSpeech tts;

    ImageView [] correctImage ;//cevabi tutayim diye
    int firstImgId, secondImgId, thirdImgId, fourthImgId, fifthImgId;

    int width,height;

    int colorRead, nameRead, countRead, shapeRead;

    Runnable runnable1, runnable2, runnable3;

    Handler handler;

    //// TODO: 5/31/2016 onpauseta masaustunegelmisse mesela stopa geliyor bekliyor geri gelince en bastanbaslatiyor setupdemoyu
    public void activateProcesses(){
        Log.d(TAG, "choosing game activateproisthreadrun:"+isThreadRunning+"isPaused "+isPaused);

        colorRead = dbHandler.getTrainingColorRead(trainingID);//// TODO: 6/1/2016 test et buraya koydum dogru mu diye
        nameRead = dbHandler.getTrainingNameRead(trainingID);
        shapeRead = dbHandler.getTrainingShapeRead(trainingID);
        countRead = dbHandler.getTrainingCountRead(trainingID);

        if(isHandlerRunning){
            Log.d(TAG, "CHOOSINGGAME activatepro ishandlerrunnigisthreadrun:"+isThreadRunning);
            setupDemo();//bastan basliyr
        }
        else {//handler yoksa thread vardir yani setupgame
            level = dbHandler.getCurrentLevel(trainingID);

/*            if (level != 0){
                isThreadRunning = true; //yoksa sifirden yarim kalmis oyuna basliyor
            }*/

                if (isPaused) {//durdurulysa giriyor
                    Log.d(TAG, "CHOOSINGGAME activateteprolevel=" + level);
                    counter = level - 1;
                    Log.d(TAG, "CHOOSINGGAME activate cntr=" + counter + " lvl" + level+" ispaused"+isPaused);
                    isPaused = false;//
                    setupForGame();//restart.
                }
            Log.d(TAG, "CHOOSINGGAME activate  isthreadrun:"+isThreadRunning);
        }
       /* if (thread != null) {//// TODO: 5/29/2016  burda 5 saniye islemiyor sonsuza kadar kaliyor.diger stop resume dogru mu emin degilim
            synchronized (thread) {
                mPaused = false;
                thread.notifyAll();
                // thread.start();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else{
                    tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        }*/
    }


    public void terminateProcesses(){
        Log.d(TAG, "CHOOSINGGAME terminateproisthreadrun:"+isThreadRunning);

        if(tts.isSpeaking()){
            Log.d(TAG,"CHOOSINGGAME terminatepro tts speak, stoppedisthreadrun:"+isThreadRunning);
            tts.stop();//handler yok etmiyor bunu, benim durdurmam lazim böyle
        }

        if(isHandlerRunning){
            Log.d(TAG,"CHOOSINGGAME terminatepro handlerrunning trueisthreadrun:"+isThreadRunning);
            handler.removeCallbacksAndMessages(null);

        }
        else {//setupgamedadir
            if (isThreadRunning) {
                Log.d(TAG, "CHOOSINGGAME terminatepro handlerrunning falseisthreadrun:"+isThreadRunning);

                if (isAnimationStarted) {
                    Log.d(TAG, "CHOOSINGGAME terminatepro animation, stoppedisthreadrun:"+isThreadRunning);
                    isAnimationStarted = false;
                    ChoosingGame.this.correctImage[0].clearAnimation();
                }

                synchronized (
                        thread) {
                    Log.d(TAG, "CHOOSINGGAME terminatepro syncisthreadrun:"+isThreadRunning);
                    thread.interrupt();

                }

            }
        }

    }

    @Override
    protected void onPause() {
        Log.d(TAG, "CHOOSINGGAME onpause***********isthreadrun:"+isThreadRunning);
        super.onPause();
        isPaused = true;

        terminateProcesses();

        Log.d(TAG, "CHOOSINGGAME onpause ends***********isthreadrun:"+isThreadRunning);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "CHOOSINGGAME ondestryisthreadrun:"+isThreadRunning);
        super.onDestroy();
        tts.shutdown();
       // terminateProcesses();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "CHOOSINGGAME onstopisthreadrun:"+isThreadRunning);
        super.onStop();
       // terminateProcesses();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "CHOOSINGGAME onresume isthreadrun:"+isThreadRunning+"isPaused"+isPaused);
        super.onResume();

        activateProcesses();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "CHOOSINGGAME onrestart");//// TODO: 6/1/2016 mainactivitye mi dönse burda . restart ne diye kontrol et arastir.bozulma ani mi
        super.onRestart();

    /*    level = dbHandler.getCurrentLevel(trainingID)-2;
        Log.d(TAG, "choosing gameonrestart level="+level);

        if(level == 0) {

            setupDemo();//// TODO: 5/21/2016 hata olursa mne olcak sessiz mi, belki yazili olan yapilabilir
            //setupForGame();
        }
        else{
            setupForGame();
        }*/
    }

    public void onBackPressed() {
        Log.d(TAG, "CHOOSINGGAME backbuttpn");

        final Context ctx = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle("Egitiminizi sonlandirmak istediginize emin misiniz?");
       //builder.setMessage("Cikilsin mi?");
        builder.setPositiveButton("Egitimden cik", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // terminateProcesses();
                Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();//// TODO: 6/1/2016 test et finsih mi termiante mi koyuılmalı diye
            }
        });
        builder.setNegativeButton("Egitime devam et", new DialogInterface.OnClickListener() {
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

    public void setupDemo(){

        Log.d(TAG,"CHOOSINGGAME setupdemo************");

        isHandlerRunning = true;
        isThreadRunning = false;

        imageDemo=new ImageView(this);
        forwardButtonImg = new ImageView(this);

        forwardButtonImg.setBackgroundResource(R.drawable.ic_forward);

        RelativeLayout.LayoutParams rLayParamsDirectiveBtnImg = new RelativeLayout.LayoutParams(height/9,height/9);
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak("Önce nesneleri taniyalim...", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else{
            tts.speak("Önce nesneleri taniyalim...", TextToSpeech.QUEUE_FLUSH, null);
        }*/

        rLayout= (RelativeLayout) findViewById(R.id.relative_layout);
        rLayout.setBackgroundColor(Color.MAGENTA);

        rLayParamsDirectiveBtnImg.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        rLayParamsDirectiveBtnImg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        rLayout.addView(forwardButtonImg,rLayParamsDirectiveBtnImg);

        addBackBtnImg();

        demoObjectObject = dbHandler.getDemoObjectObject(trainingID);

        //backButtonImg.setOnClickListener(this);
        forwardButtonImg.setOnClickListener(this);

        final RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        //putting middle to show demo of items in each iteration
        rLayParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        handler = new Handler();

        int i=0;//// TODO: 6/1/2016 i onresume onpauseda durdurulup kaldigi yerden devam edilebilir.

       while (  i <demoObjectObject.size() ) {
           Log.d(TAG,"CHOOSINGGAME setupdemowhile******");
        //    Log.d(TAG, "i yi arttirmak icin for i="+ i);

           speech = "";
           final int finalI = i;

           runnable1 = new Runnable() {

               @Override
               public void run() {
                   ObjectObject demoObject = new ObjectObject();
                   demoObject = demoObjectObject.get(finalI);

                   // Log.d(TAG,"Bu nesnenin şekli "+dbHandler.getShapeName(demoObject.getShapeID())+
                   // " rengi ise"+dbHandler.getColorName(demoObject.getColorID())+"id: "+demoObject.getObjectID());

                   speech = KEY_THIS;

                   if(colorRead == 1){
                       speech = speech + KEY_READ[1]+ dbHandler.getColorName(demoObject.getColorID());
                   }

                   if(shapeRead == 1){
                       speech = speech + KEY_READ[0]+ dbHandler.getShapeName(demoObject.getShapeID());
                   }

                   if(nameRead == 1){
                       speech = speech + KEY_AN+ dbHandler.getObjectName(demoObject.getObjectID())+KEY_OBJECT_IS;
                   }
                   else{
                       speech = speech + KEY_BEING+ KEY_AN+KEY_OBJECT;
                   }

                   Log.d(TAG,"CHOOSINGGAME"+speech);

                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
                   }
                   else{
                       tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
                   }

                   imgBytes = demoObject.getObjectImageBlob();
                   bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                   rLayout.removeView(imageDemo);
                   imageDemo.setImageBitmap(bmp);

                   //// TODO: 5/17/2016 background color degistir ki uyumlu olsun nesne ile karismasin rengi,zittini al
                   rLayout.addView(imageDemo,rLayParams);
               }
           };

           handler.postDelayed(runnable1, 5000 * i);

           i++;
           Log.d(TAG,"CHOOSINGGAME setupdemowhile end******");
                   }//while end

        runnable2 = new Runnable() {

            @Override
            public void run() {
                Log.d(TAG,"CHOOSINGGAME SECond handler");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak("Eğitiminiz şimdi başlıyor...", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else{//// TODO: 5/22/2016 bunlari okuyamiyor süre yetmiyor cunku. bir wait lazim sanki while icinde ya da gerek yok 5 saniyede okur zaten
                    tts.speak("Eğitiminiz şimdi başlıyor...", TextToSpeech.QUEUE_FLUSH, null);
                }
                // setupForGame();
            }
        };

        handler.postDelayed(runnable2, 5000 * demoObjectObject.size());

        runnable3 = new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "CHOOSINGGAME third handler");
                isHandlerRunning = false;
                setupForGame();
            }
        };

        handler.postDelayed(runnable3, 5000 * demoObjectObject.size()+6000);

      //  setupForGame();
        Log.d(TAG, "CHOOSINGGAME setupdemo end************");
//// TODO: 5/20/2016 onresume onpause yap yska karısiyior
    }

    public void addBackBtnImg(){//geri tusunu eklemek icin her iterasyoneda lazim
        RelativeLayout.LayoutParams rLayParamsDirectiveBtnImg = new RelativeLayout.LayoutParams(height/9,height/9);

        rLayParamsDirectiveBtnImg.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        rLayParamsDirectiveBtnImg.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        backButtonImg = new ImageView(this);
        backButtonImg.setBackgroundResource(R.drawable.ic_back);
        rLayout.addView(backButtonImg,rLayParamsDirectiveBtnImg);
        backButtonImg.setOnClickListener(this);
    }

    public void setTwoObjectLayout(){

        Log.d(TAG, "CHOOSINGGAME settwoobjectlayout");

        RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageOne = new ImageView(this);
        correctImage = new ImageView[1];

        imageOne.setImageBitmap(bmp);
        imageOne.setTag(trainingObject.getTrainingobjectOne());
        imageOne.setId(R.id.imgOne);
        rLayout.removeAllViews();
        addBackBtnImg();
        rLayout.addView(imageOne,rLayParams);

        rLayParams = null;
        objectResponse = null;
        bmp = null;
        imgBytes = null;

        /*******************************************************************************************/

        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectTwo());

        rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageTwo = new ImageView(this);

        imageTwo.setImageBitmap(bmp);
        imageTwo.setTag(trainingObject.getTrainingobjectTwo());
        imageTwo.setId(R.id.imgTwo);
        //rLayout.removeAllViews();
        rLayout.addView(imageTwo,rLayParams);

        imageOne.setOnClickListener(this);
        imageTwo.setOnClickListener(this);
    }

    public void setFourObjectLayout(){

        Log.d(TAG, "CHOOSINGGAME setfourobjectlayout");

        RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageOne = new ImageView(this);
        correctImage = new ImageView[1];

        imageOne.setImageBitmap(bmp);
        imageOne.setTag(trainingObject.getTrainingobjectOne());
        imageOne.setId(R.id.imgOne);
        rLayout.removeAllViews();
        addBackBtnImg();
        rLayout.addView(imageOne,rLayParams);

        rLayParams = null;
        objectResponse = null;
        bmp = null;
        imgBytes = null;

        /*******************************************************************************************/

        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectTwo());

        rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageTwo = new ImageView(this);

        imageTwo.setImageBitmap(bmp);
        imageTwo.setTag(trainingObject.getTrainingobjectTwo());
        imageTwo.setId(R.id.imgTwo);
        //rLayout.removeAllViews();
        rLayout.addView(imageTwo,rLayParams);

        rLayParams = null;
        objectResponse = null;
        bmp = null;
        imgBytes = null;

        /*******************************************************************************************/

        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectThree());

        rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageThree = new ImageView(this);

        imageThree.setImageBitmap(bmp);
        imageThree.setTag(trainingObject.getTrainingobjectThree());
        imageThree.setId(R.id.imgThree);
        //rLayout.removeAllViews();
        rLayout.addView(imageThree,rLayParams);

        rLayParams = null;
        objectResponse = null;
        bmp = null;
        imgBytes = null;

        /*******************************************************************************************/

        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectFour());

        rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageFour = new ImageView(this);

        imageFour.setImageBitmap(bmp);
        imageFour.setTag(trainingObject.getTrainingobjectFour());
        imageFour.setId(R.id.imgFour);
        //rLayout.removeAllViews();
        rLayout.addView(imageFour,rLayParams);

        imageOne.setOnClickListener(this);
        imageTwo.setOnClickListener(this);
        imageThree.setOnClickListener(this);
        imageFour.setOnClickListener(this);
    }

//// TODO: 5/21/2016 isspeaking stopa gerek yok hepsinde, zaten 5 saniyeden uzun olamaz ki
    void setupForGame(){
        Log.d(TAG,"CHOOSINGGAME "+counter+" setupForGame");
Log.d(TAG,"false olmali ishadlerrun: "+isHandlerRunning);//// TODO: 5/31/2016 sil

        isThreadRunning = true;

        rLayout.removeView(forwardButtonImg);

        if(tts.isSpeaking()){
            tts.stop();
            Log.d(TAG,"CHOOSINGGAME tts speak, stopped");
        }

        if(isAnimationStarted) {
            isAnimationStarted = false;
            ChoosingGame.this.correctImage[0].clearAnimation();
        }

        isNextLevelToGo=false;
        isImageClicked = false;

        LEVEL_SCORE =0;

        speech = "";

        counter++;
        //get trainingobjects for trainingid
        trainingObjectList = dbHandler.getAllTrainingObject(trainingID);

        trainingResponse = new TrainingResponse();

        trainingObject = new TrainingObject();//localdi, onclickte alabileyim diye global

        trainingObject = null;

        //get trainingobject
        trainingObject = trainingObjectList.get(counter%trainingObjectList.size());//// TODO: 5/22/2016 mod aldım burda çünkü hata veriyor su an icin, ck syida var diye

        trainingResponse.setTrainingID(trainingID);
        trainingResponse.setQuestionObjectID(trainingObject.getTrainingobjectID());
        trainingResponse.setTrainingStarted(1);
        trainingResponse.setStudentUserName(username);
        trainingResponse.setTrainingCompleted(0);
        trainingResponse.setResponseSent(0);
       // trainingResponse.setAnswerObjectID(0);

        objectCount = 1+trainingObject.getTrainingobjectLevel() ;
        Log.d(TAG,"CHOOSINGGAME objcount: "+objectCount);

        // Log.d(TAG,"testicin trainingobjectid: "+trainingObject.getTrainingobjectID());
if(counter==0) {//todo sil
            for (int i = 0; i < trainingObjectList.size(); i++) {
                ObjectObject o = new ObjectObject();
                        o = dbHandler.getObjectObject(trainingObjectList.get(i).getTrainingobjectAnswer());

                Log.d(TAG, dbHandler.getShapeName(o.getShapeID()) +
                        " " + dbHandler.getColorName(o.getColorID())+dbHandler.getObjectName(o.getObjectID()));
            }
            //dbHandler.getAllObjectObject();
        }
        Log.d(TAG, "CHOOSINGGAME traobj: "+  trainingObject.toString());

        /*******************************************************************************************/

        objectAnswer = new ObjectObject();//// yapıldı - T ODO: 5/20/2016  ses için kullanılacak komutu objectanswer
        objectResponse = new ObjectObject();

        objectAnswer = dbHandler.getObjectObject(trainingObject.getTrainingobjectAnswer());
        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectOne());

        if(colorRead == 1){
            speech = speech + KEY_READ[1]+ dbHandler.getColorName(objectAnswer.getColorID());
        }

        if(shapeRead == 1){
            speech = speech + KEY_READ[0]+ dbHandler.getShapeName(objectAnswer.getShapeID());
        }

        if(nameRead == 1){
            speech = speech + dbHandler.getObjectName(objectAnswer.getObjectID())+KEY_CHOOSE_THE_OBJECT_WHICH;
        }
        else{
            speech = speech + KEY_CHOOSE_WHICH;
        }

        Log.d(TAG,"CHOOSINGGAME "+speech);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else{
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

Log.d(TAG,"CHOOSINGGAME "+dbHandler.getColorName(objectAnswer.getColorID())+" - "+dbHandler.getShapeName(objectAnswer.getShapeID()));

        RelativeLayout.LayoutParams rLayParams;

        switch (objectCount){
            case 2://2 ve 3te ortak 2 tane var. o yuzden ortaklar. 3te sonra ekleme yapiliyor

                Log.d(TAG,"CHOOSINGGAME switchcase 2");
                //already defined

                setTwoObjectLayout();
                break;

            case 3:

                Log.d(TAG,"CHOOSINGGAME switchcase 3");

                setTwoObjectLayout();

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectThree());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageThree = new ImageView(this);
                imageThree.setImageBitmap(bmp);
                imageThree.setTag(trainingObject.getTrainingobjectThree());
                imageThree.setId(R.id.imgThree);

                rLayout.addView(imageThree,rLayParams);

                imageOne.setOnClickListener(this);
                imageTwo.setOnClickListener(this);
                imageThree.setOnClickListener(this);

                break;

            case 4:

                Log.d(TAG,"CHOOSINGGAME switchcase 4");

                setFourObjectLayout();
                break;

            default:

                Log.d(TAG,"CHOOSINGGAME switchcase default");
                setFourObjectLayout();

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectFive());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageFive = new ImageView(this);
                imageFive.setImageBitmap(bmp);
                imageFive.setTag(trainingObject.getTrainingobjectFive());
                imageFive.setId(R.id.imgFive);

                rLayout.addView(imageFive,rLayParams);

                imageOne.setOnClickListener(this);
                imageTwo.setOnClickListener(this);
                imageThree.setOnClickListener(this);
                imageFour.setOnClickListener(this);
                imageFive.setOnClickListener(this);

                break;
        }

        //dogru resmi buluyorum ki fade in out yapbileyim
        if(objectAnswer.getObjectID() ==(int)imageOne.getTag()){
            correctImage[0] = imageOne;
        }
        else{
            if(objectAnswer.getObjectID() ==(int)imageTwo.getTag()){
                correctImage[0] = imageTwo;
            }
            else{
                if(objectAnswer.getObjectID() ==(int)imageThree.getTag()){
                    correctImage[0] = imageThree;
                }
                else{
                    if(objectAnswer.getObjectID() ==(int)imageFour.getTag()){
                        correctImage[0] = imageFour;
                    }
                    else{
                        correctImage[0] = imageFive;
                    }
                }
            }
        }

        thread=  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        Log.d(TAG,"CHOOSINGGAME "+counter+" waiting 5 seconds counter:");
                        wait(6000);//normalde 5 saniye ama ses uzun surdugu icin
Log.d(TAG,"objectAnswer.getObjectID():"+objectAnswer.getObjectID()+"imageOne.getTag():"+imageOne.getTag()+"imageTwo.getTag()"+imageTwo.getTag());

                        wait(1000);
                        Log.d(TAG,"CHOOSINGGAME "+counter+" waiting 5 seconds efekt counter:");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Animation fadeIn = new AlphaAnimation(0, 1);
                                fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                                fadeIn.setDuration(2000);
                                Animation fadeOut = new AlphaAnimation(1, 0);
                                fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                                fadeOut.setStartOffset(2000);
                                fadeOut.setDuration(2000);

                                AnimationSet animation = new AnimationSet(false); //change to false
                                animation.addAnimation(fadeIn);
                                animation.addAnimation(fadeOut);
                                isAnimationStarted = true;
                                ChoosingGame.this.correctImage[0].startAnimation(animation);
                            }
                        });

                        wait(5000);
                        if(!isNextLevelToGo) {
                           // Log.d(TAG,counter+" nextlevel  counter:");
                            isNextLevelToGo = true;

                           // counter++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //süre bitti cevaplamadi. negative
                                    currentDate = new Date();

                                    trainingResponse.setTrainingResponseScore(NEGATIVE_SCORE);
                                    trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                                    dbHandler.addTrainingResponse(trainingResponse);
                                    Log.d(TAG,"CHOOSINGGAME trarespo "+  trainingResponse.toString());//// TODO: 5/29/2016 geçicisil
                               //     Log.d(TAG,counter-1+" run ui level  counter:");
                                    if(counter+1<trainingObjectList.size()) {
                                        setupForGame();
                                    }
                                    else{//bitir activity. main activitiye dönülebilir belki.
                                        dbHandler.setTrainingCompletedAll(trainingID);
                                        if (!CheckNetwork.isOnline(ChoosingGame.this)) {
                                            Log.d(TAG, "CHOOSINGGAME no db no wifi, checknetworktrue");
                                            CheckNetwork.showNoConnectionDialog(ChoosingGame.this, false, 2); //display dialog
                                        } else {//// TODO: 5/27/2016 burdan yollayinca gif_mainactivity boolean degistirilemiyor. o yüzden maine yollamak lazim.
                                            isThreadRunning = false;
                                            new CreateTrainingResponse(ChoosingGame.this, false, username).execute(trainingID);
                                            //// TODO: 5/18/2016 async taska sadece trainid yolluyorum,orda cekecek responselari.aynı
                                            ////  5/18/2016 id ile baska olamaz, dogru seyi cekecektir
                                            ////  5/18/2016 ya bizimkin degil de baska bir tablette oyunu bitirdiyse ama bizim localde hala egitim gözküyorsa
                                            Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
                                            intent.putExtra("username", username);

                                       //     startActivity(intent);
                                         //   finish();
                                        }
                                    }
                                }
                            });

                        }//if end

                    }
                }
                catch(InterruptedException ex){
                   //Log.d(TAG,counter+" interrupts");
                    ex.printStackTrace();
                }

                // TODO
/*                synchronized (thread) {
                    while (mPaused) {
                        Log.d(TAG, "choosinggame mpaused");

                        try {
                            thread.wait(1000);
                               Log.d(TAG, "choosinggame mpaused 1000");
                        } catch (InterruptedException e) {
                            Log.d(TAG, "choosinggame mpaused error");
                            e.printStackTrace();
                        }
                    }
                }*/
            }

        };

        thread.start();

/*        if(counter+1 == trainingObjectList.size()) {
            synchronized(
                    thread) {
                if (!CheckNetwork.isOnline(this)) {
                    Log.d(TAG, "choosinggame no db no wifi, checknetworktrue");
                    CheckNetwork.showNoConnectionDialog(this, false); //display dialog
                } else {
                    new CreateTrainingResponse(this).execute(trainingID);
                }
            }//thread end
        }//if end*/

//// yapıldı T ODO: 5/20/2016 onresume filan lazim

    }
    @Override//oncreatede bir sey yok onibitte oluyor her sey.
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "CHOOSINGGAME OnCreate");
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_choosegame);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        Log.d(TAG,"CHOOSINGGAME width:"+width+" height:"+height);//// TODO: 5/27/2016 sil

        tts = new TextToSpeech(this,this);

        dbHandler = new DatabaseHandler(this);
        rLayout = new RelativeLayout(this);
        trainingObjectList = new ArrayList<TrainingObject>();
        demoObjectObject = new ArrayList<ObjectObject>();
        trainingResponseList = new ArrayList<TrainingResponse>();

 /*       trainingObjectList = dbHandler.getAllTrainingObject(trainingID);
        for ( int i = 0; i<trainingObjectList.size() ; i++) {      ObjectObject demoObject = new ObjectObject();
           // demoObject =
            Log.d(TAG,"Bu nesnenin şekli "+dbHandler.getShapeName(trainingObjectList.get(i).getTrainingobjectAnswer())+
                    " rengi ise"+dbHandler.getColorName(trainingObjectList.get(i).getTrainingobjectAnswer()));}*/

        //imageDemo=new ImageView(this);

        rLayout= (RelativeLayout) findViewById(R.id.relative_layout);
        rLayout.setBackgroundColor(Color.MAGENTA);

        trainingID = getIntent().getExtras().getInt("trainingid");
        level = getIntent().getExtras().getInt("level");
        username = getIntent().getExtras().getString("username");
        Log.d(TAG, "CHOOSINGGAME traininfid: "+trainingID+" level: "+level);


        demoObjectObject = dbHandler.getDemoObjectObject(trainingID);

/*
//todo sill
        final RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        //putting middle to show demo of items in each iteration
        rLayParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);
     final Handler handler = new Handler();
     *//*   for ( int i = 0; i<demoObjectObject.size() ; i++) {
            Log.d(TAG, "for i="+ i);
            final int finalI = i;
            final RelativeLayout finalRLayout = rLayout;
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Log.d(TAG, "for finali="+ finalI);
                    ObjectObject demoObject = new ObjectObject();
                    demoObject = demoObjectObject.get(finalI);

                    Log.d(TAG,"Bu nesnenin şekli "+dbHandler.getShapeName(demoObject.getShapeID())+
                            " rengi ise"+dbHandler.getColorName(demoObject.getColorID()));

                    imgBytes = demoObject.getObjectImageBlob();
                    bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                    imageDemo.setImageBitmap(bmp);
                    finalRLayout.removeAllViews();
                   //// TODO: 5/17/2016 background color degistir ki uyumlu olsun nesne ile karismasin rengi,zittini al
                    finalRLayout.addView(imageDemo,rLayParams);
                }
            }, 3000 * i);
            Log.d(TAG, "3000i sonrasi");

        }//for end*//*
        for ( int i = 0; i< 20 ; i++) {
            Log.d(TAG, "for i2="+ i);
            final int finalI = i;
            final RelativeLayout finalRLayout = rLayout;
            final Runnable r=new Runnable() {

                @Override
                public void run() {
                    Log.d(TAG, "for finali2="+ finalI);
                    TrainingObject trainingObject = new TrainingObject();
                    trainingObject = trainingObjectList.get(finalI);
                    objectCount = 2;
//test icin
                    Log.d(TAG,"testicin trainingobjectid: "+trainingObject.getTrainingobjectID());
                    object = dbHandler.getObjectObject(trainingObject.getTrainingobjectAnswer());

                    if(trainingObject.getTrainingobjectThree()!=0) objectCount++;
                    if(trainingObject.getTrainingobjectFour()!=0) objectCount++;
                    if(trainingObject.getTrainingobjectFive()!=0) objectCount++;
                    Log.d(TAG,"matchinggame objcount: "+objectCount);

                    RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                    rLayParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);

                    imgBytes = object.getObjectImageBlob();
                    bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                    imageDemo.setImageBitmap(bmp);
                    imageDemo.setTag(trainingObject.getTrainingobjectAnswer());
                    imageDemo.setId(R.id.imgAnswer);
                    finalRLayout.removeAllViews();
                    finalRLayout.addView(imageDemo,rLayParams);
                }
            };
            Log.d(TAG, "3000i2 sonrasi");

            handler.postDelayed(r, 6000 * i);
            imageDemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == findViewById(R.id.imgAnswer)) {
                        Log.d(TAG, "clicked");handler.removeCallbacks(r); handler.postDelayed(r, 0);
                        //PUT IN CODE HERE TO GET NEXT IMAGE

                    }
                    else{
                        Log.d(TAG, "nonclick");
                        handler.removeCallbacks(r);  handler.postDelayed(r, 6000);
                    }
                }
            });
        }//for end

        trainingObjectList = dbHandler.getAllTrainingObject(trainingID);*/
//// yapıldı T ODO: 02.05.2016 egitim kismi oalcak burda tek tek gosterilecek

   // setupDemo();

/*************************************************************************************
 **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */

//// yapıldı  TO DO: 5/18/2016 response tek tek eklenecek listeye for içinde
        ////  ,zaten hepsi eklenmisse for sonunda

    /*    trainingResponse.setTrainingID(trainingID);
        trainingResponse.setStudentUserName(username);
    //    trainingResponse.setObjectID(Integer.parseInt(trainingObject.getTrainingobjectAnswer()));
        trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
        trainingResponse.setTrainingResponseScore(score);//}else Log.d(TAG,"null");
        trainingResponse.setTrainingStarted(1);
        trainingResponse.setTrainingCompletedAll(0);
        trainingResponse.setResponseSent(0);

        dbHandler.addTrainingResponse(trainingResponse);
     //   trainingResponseList.add(trainingResponse);//to sent server

//// yapıldı T ODO: 5/18/2016 if total questions all sent:büyük ihtimalle gerek yok buna çünkü for sonrası olacak bu, yani tüm hepsi bitmis olacak
dbHandler.setTrainingCompletedAll(trainingID);
        //// yapıldı T ODO: 5/18/2016 internet bagliysa yolla ya da önce maine mi gitse.mainde de kontrol yapilir normal*/

        ////
        Log.d(TAG, "CHOOSINGGAME  OnCreate ends");
    }//oncreate end

    @Override
    public void onClick(View v) {

        Log.d(TAG, "CHOOSINGGAME  onlick");

        if (v.equals(backButtonImg)) {
            Log.d(TAG, "CHOOSINGGAME  backbtnimg clicked");
            final Context ctx = this;
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setCancelable(true);
            builder.setTitle("Egitiminizi sonlandirmak istediginize emin misiniz?");
            //builder.setMessage("Cikilsin mi?");
            builder.setPositiveButton("Egitimden cik", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // terminateProcesses();
                    Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("Egitim devam et", new DialogInterface.OnClickListener() {
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


        } else {
            if (v.equals(forwardButtonImg)) {
                Log.d(TAG, "CHOOSINGGAME  fwdbtnimg clicked");
                if (tts.isSpeaking()) {
                    tts.stop();
                    Log.d(TAG, "CHOOSINGGAME tts speak, stopped");
                }
                    //// yapıldı T ODO: 5/30/2016 burda handler durdurulmali
                handler.removeCallbacksAndMessages(null);
                isHandlerRunning = false;
                setupForGame();
               // if (thread.isAlive()) {
                   // Log.d(TAG, "threadelive stopped");
                   // thread.interrupt();
              //  }

            } else {//imagelarsa

                Log.d(TAG, "CHOOSINGGAME  fonlick else");

                //isImageDragged = true;
                synchronized (
                        thread) {

                    if (v.getTag().equals(trainingObject.getTrainingobjectAnswer())) {
                        Log.d(TAG, counter + " clicked " + v.getTag() + " - " + trainingObject.getTrainingobjectAnswer());

                        if (!isNextLevelToGo && !isImageClicked) {
                            //  Log.d(TAG,counter+" nextlevel  counter:");
                            //dogru cevap ilk seferde
                            thread.interrupt();
                            isNextLevelToGo = true;
                            isImageClicked = true;
                            currentDate = new Date();

                            trainingResponse.setTrainingResponseScore(POSITIVE_SCORE);
                            trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                            trainingResponse.setAnswerObjectID(trainingObject.getTrainingobjectAnswer());
                            trainingResponse.setAnswerTwoObjectID(0);
                            dbHandler.addTrainingResponse(trainingResponse);
                            Log.d(TAG, "CHOOSINGGAME trarespo " + trainingResponse.toString());//// TODO: 5/29/2016 geçicisil
                            // counter++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Log.d(TAG,counter-1+" run ui level  counter:");
                                    if (counter + 1 < trainingObjectList.size()) {
                                        setupForGame();
                                    } else {//bitir activity. main activitiye dönülebilir belki.
                                        dbHandler.setTrainingCompletedAll(trainingID);
                                        if (!CheckNetwork.isOnline(ChoosingGame.this)) {
                                            Log.d(TAG, "CHOOSINGGAME  no db no wifi, checknetworktrue");
                                            CheckNetwork.showNoConnectionDialog(ChoosingGame.this, false, 2); //display dialog
                                        } else {
                                            isThreadRunning = false;
                                            new CreateTrainingResponse(ChoosingGame.this, false, username).execute(trainingID);
                                            Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
                                            intent.putExtra("username", username);
                                          //  startActivity(intent);
                                          //  finish();
                                        }
                                    }
                                }

                            });

                        }//if end
                        else {
                            if (!isNextLevelToGo) {
                                isNextLevelToGo = true;
                                //bu durumda 1 kez tiklanmistir yanlis. //// yapıldı T ODO: 5/20/2016 positive ver sonra reset
                                thread.interrupt();
                                isImageClicked = true;

                                currentDate = new Date();

                                trainingResponse.setAnswerTwoObjectID(trainingObject.getTrainingobjectAnswer());
                                trainingResponse.setTrainingResponseScore(HALF_SCORE);
                                trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                                dbHandler.addTrainingResponse(trainingResponse);
                                Log.d(TAG, "CHOOSINGGAME trarespo " + trainingResponse.toString());//// TODO: 5/29/2016 geçicisil
                                //   counter++;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (counter + 1 < trainingObjectList.size()) {
                                            setupForGame();
                                        } else {//bitir activity. main activitiye dönülebilir belki.
                                            dbHandler.setTrainingCompletedAll(trainingID);
                                            if (!CheckNetwork.isOnline(ChoosingGame.this)) {
                                                Log.d(TAG, "CHOOSINGGAME  no db no wifi, checknetworktrue");
                                                CheckNetwork.showNoConnectionDialog(ChoosingGame.this, false, 2); //display dialog
                                            } else {
                                                isThreadRunning = false;
                                                new CreateTrainingResponse(ChoosingGame.this, false, username).execute(trainingID);
                                                Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
                                                intent.putExtra("username", username);
                                             //   startActivity(intent);
                                               // finish();
                                            }
                                        }
                                    }
                                });
                            }//if ends, else zaten yeni gidilmistir level
                        }//else
                    }  //if dogru degilse
                            else {//dogru cevap degilse

                                Log.d(TAG, "CHOOSINGGAME nonclick" + counter + " -  " + v.getTag() + " - " + trainingObject.getTrainingobjectAnswer());

                                if (!isNextLevelToGo && !isImageClicked) {
                                    //ilk yanlis cevabi
                                    isImageClicked = true;
                                    trainingResponse.setAnswerObjectID((Integer) v.getTag());
                                    //isNextLevelToGo = false;
                                    //  Log.d(TAG,counter+" nextlevel  counter:");
                                    // thread.interrupt();
                                    //yapıldı t odo efekt

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Animation fadeIn = new AlphaAnimation(0, 1);
                                            fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                                            fadeIn.setDuration(2000);
                                            Animation fadeOut = new AlphaAnimation(1, 0);
                                            fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                                            fadeOut.setStartOffset(2000);
                                            fadeOut.setDuration(2000);

                                            AnimationSet animation = new AnimationSet(false); //change to false
                                            animation.addAnimation(fadeIn);
                                            animation.addAnimation(fadeOut);
                                            isAnimationStarted = true;
                                            ChoosingGame.this.correctImage[0].startAnimation(animation);
                                        }
                                    });

                                    // isNextLevelToGo = true;
                                    //  counter++;
                                    //yapıldı t odo isclicked

                                }//if end
                                else {
                                    if (!isNextLevelToGo) {//2. kez yanlis cevap
                                        isNextLevelToGo = true;
                                        //bu durumda 1 kez tiklanmistir yanlis. //// yapıldı T ODO: 5/20/2016 negative ver sonra reset

                                        thread.interrupt();
                                        isNextLevelToGo = true;
                                        //  counter++;

                                        currentDate = new Date();

                                        trainingResponse.setAnswerTwoObjectID((Integer) v.getTag());
                                        trainingResponse.setTrainingResponseScore(NEGATIVE_SCORE);
                                        trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                                        dbHandler.addTrainingResponse(trainingResponse);
                                        Log.d(TAG, "CHOOSINGGAME trarespo " + trainingResponse.toString());//// TODO: 5/29/2016 geçicisil
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Log.d(TAG,counter-1+" run ui level  counter:");
                                                if (counter + 1 < trainingObjectList.size()) {
                                                    setupForGame();
                                                } else {//bitir activity. main activitiye dönülebilir belki.
                                                    dbHandler.setTrainingCompletedAll(trainingID);
                                                    if (!CheckNetwork.isOnline(ChoosingGame.this)) {
                                                        Log.d(TAG, "CHOOSINGGAME no db no wifi, checknetworktrue");
                                                        CheckNetwork.showNoConnectionDialog(ChoosingGame.this, false, 2); //display dialog
                                                    } else {
                                                        isThreadRunning = false;
                                                        new CreateTrainingResponse(ChoosingGame.this, false, username).execute(trainingID);
                                                        Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
                                                        intent.putExtra("username", username);
                                                       // startActivity(intent);
                                                       // finish();
                                                    }
                                                }
                                            }
                                        });
                                    }//if ends, else zaten yeni gidilmistir level
                                }
                            }
                        }//thread sync
            }//if images clicked not buttons
         }//else end
    }//onclick end

    @Override
    public void onInit(int status) {
        Log.d(TAG, "CHOOSINGGAME  oninit");
        Log.d(TAG, "CHOOSINGGAME  oninit isthreadrunning=" + isThreadRunning);//todo sil
        if (status == TextToSpeech.SUCCESS) {
            Log.d(TAG, "CHOOSINGGAME  OnInit - Status suces");
            //  tts.setLanguage(Locale.getDefault());
            // Log.d(TAG, "locale: "+Locale.getDefault());
            // Locale locale = new Locale("tr", "TR");
            tts.setLanguage(Locale.getDefault());
            //  Log.d(TAG, "locale: "+locale.toString());
        } else {
            Log.d(TAG, "CHOOSINGGAME  oninit status else error");
            //// TODO: 6/2/2016 simdilik böyle yaptim
            //sessiz oynamya devam edilsin mi diyaglogu
            Intent intent = new Intent(ChoosingGame.this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }


            if (level == 0) {
                setupDemo();
                //setupForGame();
            } else {
                if (!isPaused) {
                    Log.d(TAG, "CHOOSINGGAME  oninit isPaused false");
                    level = dbHandler.getCurrentLevel(trainingID);
                    counter = level - 1;
                    setupForGame();
                }
                else
                {
                    Log.d(TAG, "CHOOSINGGAME  oninit isPaused true****buraya girmemeli");
                }
            }

    }
}//class end
