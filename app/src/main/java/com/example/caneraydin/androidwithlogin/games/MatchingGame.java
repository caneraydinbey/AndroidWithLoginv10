package com.example.caneraydin.androidwithlogin.games;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
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

import android.view.View.OnTouchListener;


/**
 * Created by caneraydin on 17.04.2016.
 */
public class MatchingGame extends Activity implements TextToSpeech.OnInitListener, View.OnDragListener {

    String TAG = "Chic";

    DatabaseHandler dbHandler;

    int trainingID, objectCount, level, score = 0;

    //for demo
    String username, speech,
            KEY_THIS = "Bu,",
            KEY_AN = ", bir ",
            KEY_BEING = " olan, ",
            KEY_OBJECT_IS = " nesnesidir ",
            KEY_OBJECT = " nesnedir ";

    //// TODO: 5/21/2016 merhaba ghosgeldiniz username denebilir belki
    // KEY_MATCH_WHICH_IS = " olani seciniz...";

    String [] KEY_READ = {", şekli ", ", rengi ", ", sayısında "};

    String KEY_MATCH_WHICH = ", olanları eşleştiriniz.",
    //   KEY_IN_THE_SCREEN = "Ekrandakilerden ",
    KEY_MATCH_THE_OBJECT_WHICH = ", nesnelerini eşleştiriniz.";

    List<TrainingObject> trainingObjectList;

    List<ObjectObject> demoObjectObject;

    List<TrainingResponse> trainingResponseList;

    byte[] imgBytes;

    Bitmap bmp;

    ImageView imageDemo, imageAnswer,
            imageOne, imageTwo, imageThree, imageFour, imageFive;

    RelativeLayout rLayout;

    ObjectObject //objectAnswer,
            objectResponse;//// TODO: 5/22/2016 objectanswer gerek yok gibi

    TrainingResponse trainingResponse;

    Date currentDate;

    int counter = -1, aim,
    width, height;

    int colorRead, nameRead, countRead, shapeRead;

    boolean isNextLevelToGo = false,//cakisma olmasın diye saniye ve sürükleme
            isImageDragged = false,//resme tiklatyinca cakisma olmasin diye
            isAnimationStarted = false;

    private Thread thread;

    int POSITIVE_SCORE = 2, NEGATIVE_SCORE = 0, HALF_SCORE = 1, LEVEL_SCORE = 0;

    TrainingObject trainingObject;

    TextToSpeech tts;

    ImageView [] correctImage ;//cevabi tutayim diye
    // int firstImgId, secondImgId, thirdImgId, fourthImgId, fifthImgId;

    public void setupDemo() {

        Log.d(TAG, "matching game setupdemo");

        imageDemo = new ImageView(this);
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak("Önce nesneleri taniyalim...", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else{
            tts.speak("Önce nesneleri taniyalim...", TextToSpeech.QUEUE_FLUSH, null);
        }*/

        rLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        rLayout.setBackgroundColor(Color.GRAY);

        demoObjectObject = dbHandler.getDemoObjectObject(trainingID);

        final RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3, height/3);

        //putting middle to show demo of items in each iteration
        rLayParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        final Handler handler = new Handler();

        for (int i = 0; i < demoObjectObject.size(); i++) {
            // Log.d(TAG, "for i="+ i);

            speech = null;
            final int finalI = i;
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    ObjectObject demoObject = new ObjectObject();
                    demoObject = demoObjectObject.get(finalI);

                    //  Log.d(TAG,"Bu nesnenin şekli "+dbHandler.getShapeName(demoObject.getShapeID())+
                    // " rengi ise"+dbHandler.getColorName(demoObject.getColorID())+"id: "+demoObject.getObjectID());


                    speech = KEY_THIS + ", " + dbHandler.getColorName(demoObject.getColorID()) + KEY_AN + " " + dbHandler.getShapeName(demoObject.getShapeID());
                    Log.d(TAG, speech);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else {
                        tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
                    }

                    imgBytes = demoObject.getObjectImageBlob();
                    bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                    imageDemo.setImageBitmap(bmp);
                    rLayout.removeAllViews();
                    //// TODO: 5/17/2016 background color degistir ki uyumlu olsun nesne ile karismasin rengi,zittini al
                    rLayout.addView(imageDemo, rLayParams);
                }
            }, 5000 * i);

            // Log.d(TAG, "3000i sonrasi");
/*         Thread  thread=  new Thread(){
               @Override
               public void run(){
                   try {
                           Log.d(TAG, "matching game inside thread");
                           wait(demoObjectObject.size() * 5000);
                       setupForGame();
                   }
                       catch(InterruptedException e){
                           e.printStackTrace();
                       }
                   }
           thread.start();*/
        }//for end

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "SECond handler");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak("Eğitiminiz şimdi başlıyor...", TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    tts.speak("Eğitiminiz şimdi başlıyor...", TextToSpeech.QUEUE_FLUSH, null);
                }//// TODO: 5/22/2016 bunlari okuyamiyor süre yetmiyor cunku. bir wait lazim sanki

                //setupForGame();
            }
        }, 5000 * demoObjectObject.size());
        // setupForGame();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "third handler");
                setupForGame();
            }
        }, 5000 * demoObjectObject.size()+6000);

        Log.d(TAG, "setupdemo end");
    }//setupdemoend

/*    protected void onPause(){
        super.onPause(); //
        if(thread.isAlive()){
            threadPaused = true;
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    protected void onResume(){
        super.onResume();
        if(threadPause){
            thread.
        }
    }*/

    public void setTwoObjectLayout() {

        Log.d(TAG, "matching game settwoobjectlayout");

        RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectOne());

        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        imageOne = new ImageView(this);

        imageOne.setImageBitmap(bmp);
        imageOne.setTag(trainingObject.getTrainingobjectOne());
        imageOne.setId(R.id.imgOne);
        rLayout.addView(imageOne,rLayParams);
        imageOne.setOnDragListener(this);

        rLayParams = null;
        objectResponse = null;
        bmp = null;
        imgBytes = null;

        /*******************************************************************************************/

        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectTwo());
        Log.d(TAG,"objrsponse: "+objectResponse.toString());//// TODO: 5/22/2016 geçici sil
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
        imageTwo.setOnDragListener(this);

    }

    void setupForGame(){
        Log.d(TAG, "matching game setupforgame");
        Log.d(TAG,counter+" setupForGame");

        if(tts.isSpeaking()){
            tts.stop();
            Log.d(TAG,"tts speak, stopped");
        }

        if(isAnimationStarted) {
            isAnimationStarted = false;
            MatchingGame.this.imageAnswer.clearAnimation();
            MatchingGame.this.correctImage[0].clearAnimation();
        }

        isNextLevelToGo=false;
        isImageDragged = false;

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
        Log.d(TAG,"objcount: "+objectCount);

        if(counter==0) {//todo sil
            for (int i = 0; i < trainingObjectList.size(); i++) {
                Log.d(TAG, "Bu nesnenin şekli " + dbHandler.getShapeName(trainingObjectList.get(i).getTrainingobjectAnswer()) +
                        " rengi ise" + dbHandler.getColorName(trainingObjectList.get(i).getTrainingobjectAnswer()));
            }
            dbHandler.getAllObjectObject();
        }

        //those are standarst for each level so defining before switchcase

        /*******************************************************************************************/

        correctImage = new ImageView[1];

        objectResponse = new ObjectObject();
        objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectAnswer());

        colorRead = dbHandler.getTrainingColorRead(trainingID);//// TODO: 5/29/2016 gecici bu dordu sil sonra.setupgame direk basliyr diye yaptim. choosingten bak onresume iyi mi
        nameRead = dbHandler.getTrainingNameRead(trainingID);
        shapeRead = dbHandler.getTrainingShapeRead(trainingID);
        countRead = dbHandler.getTrainingCountRead(trainingID);

        RelativeLayout.LayoutParams rLayParams;

        rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
        rLayParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//ortaya gelecek

        if(colorRead == 1){
            speech = speech + KEY_READ[1]+ dbHandler.getColorName(objectResponse.getColorID());
        }

        if(shapeRead == 1){
            speech = speech + KEY_READ[0]+ dbHandler.getShapeName(objectResponse.getShapeID());
        }

        if(nameRead == 1){
            speech = speech + dbHandler.getObjectName(objectResponse.getObjectID())+KEY_MATCH_THE_OBJECT_WHICH;
        }
        else{
            speech = speech + KEY_MATCH_WHICH;
        }




        Log.d(TAG,"speech: "+speech);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else{
            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        imgBytes = objectResponse.getObjectImageBlob();
        bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

        imageAnswer = new ImageView(this);
        imageAnswer.setImageBitmap(bmp);
        imageAnswer.setTag(trainingObject.getTrainingobjectAnswer());
        imageAnswer.findViewById(R.id.imgAnswer);

/*        int idThatCanBeUsedLater = R.id.imgAnswer;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {  Log.d(TAG,"if");
            idThatCanBeUsedLater = View.generateViewId();
            imageAnswer.setId(idThatCanBeUsedLater);}*/


        rLayout.removeAllViews();
        rLayout.addView(imageAnswer,rLayParams);


        imageAnswer.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                imageAnswer.getParent().requestDisallowInterceptTouchEvent(true);
                Log.d(TAG,"imageanswer setontouchlistener");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG,"imageanswer setontouchlistener actiondown");
                    Log.d(TAG,"parent "+ String.valueOf(imageAnswer.getParent()));
                    Log.d(TAG, "view "+String.valueOf(v));
                    ClipData data = ClipData.newPlainText("", "");
                    //  Log.d(TAG,"data: "+data.toString());
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.INVISIBLE);
                    return true;
                } else {

                    Log.d(TAG, "ontouch else");
                    Log.d(TAG, String.valueOf(imageAnswer.getParent()));
                    Log.d(TAG,"imageanswer setontouchlistener else: "+event.toString());

                    Log.d(TAG,"view "+ String.valueOf(v));
                    if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        return true;}
                    return false;
                }
            }
        });

        objectResponse = null;

        imageAnswer.setOnDragListener(this);

        switch (objectCount) {
            case 2:
                //2 ve 3te ortak 2 tane var. o yuzden ortaklar. 3te sonra ekleme yapiliyor

                Log.d(TAG,"switchcase 2");
                //already defined

                setTwoObjectLayout();
                break;

            case 3:
                Log.d(TAG,"switchcase 3");

                setTwoObjectLayout();

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectThree());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
                rLayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageThree = new ImageView(this);
                imageThree.setImageBitmap(bmp);
                imageThree.setTag(trainingObject.getTrainingobjectThree());
                imageThree.setId(R.id.imgThree);

                rLayout.addView(imageThree,rLayParams);
                imageThree.setOnDragListener(this);


                break;

            case 4:
                Log.d(TAG,"switchcase 4");

                setTwoObjectLayout();

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectThree());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
                rLayParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageThree = new ImageView(this);
                imageThree.setImageBitmap(bmp);
                imageThree.setTag(trainingObject.getTrainingobjectThree());
                imageThree.setId(R.id.imgThree);

                rLayout.addView(imageThree,rLayParams);
                imageThree.setOnDragListener(this);
                /***********/

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectFour());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
                rLayParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageFour = new ImageView(this);
                imageFour.setImageBitmap(bmp);
                imageFour.setTag(trainingObject.getTrainingobjectFour());
                imageFour.setId(R.id.imgFour);

                rLayout.addView(imageFour,rLayParams);
                imageFour.setOnDragListener(this);



                break;

            default:

                Log.d(TAG,"switchcase default");
                setTwoObjectLayout();

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectThree());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
                rLayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageThree = new ImageView(this);
                imageThree.setImageBitmap(bmp);
                imageThree.setTag(trainingObject.getTrainingobjectThree());
                imageThree.setId(R.id.imgThree);

                rLayout.addView(imageThree,rLayParams);
                imageThree.setOnDragListener(this);
                /***************/

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectFour());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
                rLayParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageFour = new ImageView(this);
                imageFour.setImageBitmap(bmp);
                imageFour.setTag(trainingObject.getTrainingobjectFour());
                imageFour.setId(R.id.imgFour);

                rLayout.addView(imageFour,rLayParams);
                imageFour.setOnDragListener(this);
                /**************/

                rLayParams = null;
                objectResponse = null;
                bmp = null;
                imgBytes = null;

                objectResponse = dbHandler.getObjectObject(trainingObject.getTrainingobjectFive());

                rLayParams = new RelativeLayout.LayoutParams(height/3,height/3);

                rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);//ortaya gelecek
                rLayParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                imgBytes = objectResponse.getObjectImageBlob();
                bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                imageFive = new ImageView(this);
                imageFive.setImageBitmap(bmp);
                imageFive.setTag(trainingObject.getTrainingobjectFive());
                imageFive.setId(R.id.imgFive);

                rLayout.addView(imageFive,rLayParams);
                imageFive.setOnDragListener(this);


                break;

        }//switch end

//dogru resmi buluyorum ki fade in out yapbileyim
        if(imageAnswer.getTag() .equals( imageOne.getTag())){
            correctImage[0] = imageOne;
        }
        else{
            if(imageAnswer.getTag().equals( imageTwo.getTag())){
                correctImage[0] = imageTwo;
            }
            else{
                if(imageAnswer.getTag() .equals( imageThree.getTag())){
                    correctImage[0] = imageThree;
                }
                else{
                    if(imageAnswer.getTag() .equals( imageFour.getTag())){
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
                        Log.d(TAG,counter+" waiting 5 seconds counter:");
                        wait(4000);
                        Log.d(TAG,"objectAnswer.getObjectID():"+objectResponse.getObjectID()+"imageOne.getTag():"+imageOne.getTag()+"imageTwo.getTag()"+imageTwo.getTag());

                        wait(1000);
                        Log.d(TAG,counter+" waiting 5 seconds efekt counter:");

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
                                MatchingGame.this.correctImage[0].startAnimation(animation);
                                MatchingGame.this.imageAnswer.startAnimation(animation);
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
                                    //     Log.d(TAG,counter-1+" run ui level  counter:");
                                    if(counter+1<trainingObjectList.size()) {
                                        setupForGame();
                                    }
                                    else{//bitir activity. main activitiye dönülebilir belki.
                                        dbHandler.setTrainingCompletedAll(trainingID);
                                        if (!CheckNetwork.isOnline(MatchingGame.this)) {
                                            Log.d(TAG, "Matchgame no db no wifi, checknetworktrue");
                                            CheckNetwork.showNoConnectionDialog(MatchingGame.this, false, 2); //display dialog
                                        } else {
                                            new CreateTrainingResponse(MatchingGame.this, false, username).execute(trainingID);
                                            Intent intent = new Intent(MatchingGame.this, MainActivity.class);
                                            intent.putExtra("username", username);
                                              //startActivity(intent);
                                             //finish();
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
            }
        };

        thread.start();





    }//setupfor game ned


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "matching ggame OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchgame);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        Log.d(TAG,"width:"+width+" height:"+height);//// TODO: 5/27/2016 sil
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
        rLayout.setBackgroundColor(Color.YELLOW);

        trainingID = getIntent().getExtras().getInt("trainingid");
        level = getIntent().getExtras().getInt("level");
        username = getIntent().getExtras().getString("username");
        Log.d(TAG, "traininfid: "+trainingID+" level: "+level);

        counter = counter + level;

        demoObjectObject = dbHandler.getDemoObjectObject(trainingID);

        Log.d(TAG, "matching game OnCreate ends");
    }//oncreate end


    @Override
    public void onInit(int status) {
        Log.d(TAG, "matching game oninit");

        if (status == TextToSpeech.SUCCESS) {
            Log.d(TAG, "matching game OnInit - Status suces");
            //  tts.setLanguage(Locale.getDefault());
            // Log.d(TAG, "locale: "+Locale.getDefault());
            // Locale locale = new Locale("tr", "TR");
            tts.setLanguage(Locale.getDefault());
            //  Log.d(TAG, "locale: "+locale.toString());
        }
        else{
            Log.d(TAG, "matching game oninit status else");
        }

        if(level == 0) {
            //setupDemo();//// TODO: 5/21/2016 hata olursa mne olcak sessiz mi, belki yazili olan yapilabilir
            setupForGame();//// TODO: 5/22/2016 geçici sil bunu antremaniçin
        }
        else{
            setupForGame();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d(TAG, "ontouch method");
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
        Log.d(TAG, "ondrag start");

        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "drag action started");

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    Log.d(TAG, "Can accept this data");

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    Log.d(TAG, "Can not accept this data");

                }
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                //  Log.d(TAG, "drag action entered");
                //                the drag point has entered the bounding box
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                //   Log.d(TAG, "drag action location");
            /*triggered after ACTION_DRAG_ENTERED
                stops after ACTION_DRAG_EXITED*/
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "drag action exited");
                //                the drag shadow has left the bounding box
                return true;

            case DragEvent.ACTION_DROP:
                Log.d(TAG, "drag action droop");

                ImageView dropOntoImg = (ImageView) receivingLayoutView;
                View draggedImageView = (View) dragEvent.getLocalState();
                // ImageView draggedImg = (ImageView) draggedImageView;

                Log.d(TAG,"droponto: "+receivingLayoutView.getTag()+" dropped: "+draggedImageView.getTag());

                Log.d(TAG,  "swtich önesi "+ dropOntoImg.getId());

                Log.d(TAG,  "swtich önesii "+dropOntoImg.getTag().toString());

                if(dropOntoImg.getTag().equals(draggedImageView.getTag())){
                    Log.d(TAG,"true**** "+dropOntoImg.getTag()+" droppeged true with dragged"+ draggedImageView.getTag() + "");

                    if(!isNextLevelToGo && !isImageDragged){
                        //  Log.d(TAG,counter+" nextlevel  counter:");
                        thread.interrupt();
                        isNextLevelToGo = true;
                        isImageDragged = true ;
                        currentDate = new Date();

                        trainingResponse.setTrainingResponseScore(POSITIVE_SCORE);
                        trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                        trainingResponse.setAnswerObjectID(trainingObject.getTrainingobjectAnswer());
                        trainingResponse.setAnswerTwoObjectID(0);
                        dbHandler.addTrainingResponse(trainingResponse);
                        Log.d(TAG, "trarespo " + trainingResponse.toString());//// TODO: 5/29/2016 geçicisil
                        // counter++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Log.d(TAG,counter-1+" run ui level  counter:");
                                if(counter+1<trainingObjectList.size()) {
                                    setupForGame();
                                }
                                else{//bitir activity. main activitiye dönülebilir belki.
                                    dbHandler.setTrainingCompletedAll(trainingID);
                                    if (!CheckNetwork.isOnline(MatchingGame.this)) {
                                        Log.d(TAG, "MatchingGame no db no wifi, checknetworktrue");
                                        CheckNetwork.showNoConnectionDialog(MatchingGame.this, false, 2); //display dialog
                                    } else {
                                        new CreateTrainingResponse(MatchingGame.this, false, username).execute(trainingID);
                                        Intent intent = new Intent(MatchingGame.this, MainActivity.class);
                                        intent.putExtra("username", username);
                                        //startActivity(intent);
                                        //finish();
                                    }
                                }
                            }

                        });

                    }//if end
                    else{
                        if(!isNextLevelToGo){
                            isNextLevelToGo = true;
                            //bu durumda 1 kez tiklanmistir yanlis.
                            thread.interrupt();
                            isImageDragged = true;

                            currentDate = new Date();

                            trainingResponse.setAnswerTwoObjectID(trainingObject.getTrainingobjectAnswer());
                            trainingResponse.setTrainingResponseScore(HALF_SCORE);
                            trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                            dbHandler.addTrainingResponse(trainingResponse);

                            //   counter++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(counter+1<trainingObjectList.size()) {
                                        setupForGame();
                                    }
                                    else{//bitir activity. main activitiye dönülebilir belki.
                                        dbHandler.setTrainingCompletedAll(trainingID);
                                        if (!CheckNetwork.isOnline(MatchingGame.this)) {
                                            Log.d(TAG, "MatchingGame no db no wifi, checknetworktrue");
                                            CheckNetwork.showNoConnectionDialog(MatchingGame.this, false, 2); //display dialog
                                        } else {
                                            new CreateTrainingResponse(MatchingGame.this, false, username).execute(trainingID);
                                            Intent intent = new Intent(MatchingGame.this, MainActivity.class);
                                            intent.putExtra("username", username);
                                            //startActivity(intent);
                                            //finish();
                                        }
                                    }
                                }
                            });
                        }//if ends, else zaten yeni gidilmistir level
                    }
                    return true;
                }
                else{
                    Log.d(TAG,"false*** "+dropOntoImg.getTag()+" droppeged true with dragged"+ draggedImageView.getTag() + "");

                    if(!isNextLevelToGo && !isImageDragged){
                        //ilk yanlis cevabi
                        isImageDragged = true ;
                        trainingResponse.setAnswerObjectID((Integer) dropOntoImg.getTag());
                        //isNextLevelToGo = false;
                        //  Log.d(TAG,counter+" nextlevel  counter:");
                        // thread.interrupt();


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
                                MatchingGame.this.correctImage[0].startAnimation(animation);
                                MatchingGame.this.imageAnswer.startAnimation(animation);
                            }
                        });

                        // isNextLevelToGo = true;
                        //  counter++;


                    }//if end
                    else{
                        if(!isNextLevelToGo){//2. kez yanlis cevap
                            isNextLevelToGo = true;
                            //bu durumda 1 kez tiklanmistir yanlis.

                            thread.interrupt();
                            isNextLevelToGo = true;
                            //  counter++;

                            currentDate = new Date();

                            trainingResponse.setAnswerTwoObjectID((Integer) dropOntoImg.getTag());
                            trainingResponse.setTrainingResponseScore(NEGATIVE_SCORE);
                            trainingResponse.setTrainingResponseFinishTime(String.valueOf(currentDate.getTime() / 1000));
                            dbHandler.addTrainingResponse(trainingResponse);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Log.d(TAG,counter-1+" run ui level  counter:");
                                    if(counter+1<trainingObjectList.size()) {
                                        setupForGame();
                                    }
                                    else{//bitir activity. main activitiye dönülebilir belki.
                                        dbHandler.setTrainingCompletedAll(trainingID);
                                        if (!CheckNetwork.isOnline(MatchingGame.this)) {
                                            Log.d(TAG, "MatchingGame no db no wifi, checknetworktrue");
                                            CheckNetwork.showNoConnectionDialog(MatchingGame.this, false, 2); //display dialog
                                        } else {
                                            new CreateTrainingResponse(MatchingGame.this, false, username).execute(trainingID);
                                            Intent intent = new Intent(MatchingGame.this, MainActivity.class);
                                            intent.putExtra("username", username);
                                            //startActivity(intent);
                                            //finish();
                                        }
                                    }
                                }
                            });
                        }//if ends, else zaten yeni gidilmistir level
                    }

                    return false;
                }

                //dogdru sürukleyince obur levele geciyor. yanlissa  görünr yapiyor burda
            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "drag action ended");
                Log.d(TAG, "getResult: " + dragEvent.getResult());

                if (!dragEvent.getResult()) {
                    Log.d(TAG, "false, setting visible");

                    final View droppedView = (View) dragEvent.getLocalState();
                    droppedView.post(new Runnable() {
                        @Override
                        public void run() {
                            droppedView.setVisibility(View.VISIBLE);
                        }
                    });
                }

                return true;

            // An unknown action type was received.
            default:
                Log.d(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;

    }




//// TODO: 5/31/2016 onpause vs yok 




}//class end