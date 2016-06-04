package com.example.caneraydin.androidwithlogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.caneraydin.androidwithlogin.domains.ObjectObject;
import com.example.caneraydin.androidwithlogin.domains.Training;
import com.example.caneraydin.androidwithlogin.domains.TrainingResponse;
import com.example.caneraydin.androidwithlogin.games.ChoosingGame;
import com.example.caneraydin.androidwithlogin.games.CountingGame;
import com.example.caneraydin.androidwithlogin.games.MatchingGame;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Todo geri tusuna basarsa login olmussa bu sayfaya geri gelecek.

/**
 * Created by caneraydin on 17.04.2016.
 */
public class MainActivity extends Activity {
    String TAG = "Chic";
    String username;//="t";//// TODO: 30.04.2016 kaldir degeri
    String imageURL = "http://oep.esy.es/images/object/";
   // int lastUpdate=1000000000;//// TODO: 28.04.2016 kaldir degeri

    boolean wasDbExisted;

    TextView textMain, textHalfCompletedGames, textUnCompletedGames, textMatch;

   // ImageView imageChoose, imageCount, imageMatch;

    ImageView backgroundImg,
    settingsMenuPopup;

    DatabaseHandler dbHandler;

    RadioGroup rdioGrpUncompleted,rdioGrpHalfCompleted;

    List<Training> trainingList,trainingHalfList;

    MediaPlayer mMediaPlayer;

    PopupMenu popup;

    TableLayout resultsTable;

    private boolean getAllDBAsyncExecuted =false,//not twice execute
            checkDBUpdateAsyncExecuted = false,//not twice
            createTrainingResponseAsyncExecuted = false;//not twice

    public void setCreateTrainingResponseAsyncExecuted(boolean createTrainingResponseAsyncExecuted) {
        this.createTrainingResponseAsyncExecuted = createTrainingResponseAsyncExecuted;
    }

    public void setCheckDBUpdateAsyncExecuted(boolean checkDBUpdateAsyncExecuted) {
        this.checkDBUpdateAsyncExecuted = checkDBUpdateAsyncExecuted;
    }

    public void setGetAllDBAsyncExecuted(boolean getAllDBAsyncExecuted) {
        this.getAllDBAsyncExecuted = getAllDBAsyncExecuted;
    }

    public void onBackPressed() {
        Log.d(TAG, "sound_mainactivity backbuttpn");

        final Context ctx = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle("Uygulamayi sonlandirmak istediginize emin misiniz?");
        //builder.setMessage("Cikilsin mi?");
        builder.setPositiveButton("Uygulamayi kapat", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // terminateProcesses();
                //sonlandiriyor
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());//// TODO: 6/2/2016 böylemi olsun gecici simdilik
                //finish();
            }
        });
        builder.setNegativeButton("Uygulamada kal", new DialogInterface.OnClickListener() {
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
        Log.d(TAG, "mainactivityonpause***********");
        super.onPause();

        if(mMediaPlayer.isPlaying()&&mMediaPlayer!=null ){
            Log.d(TAG, "mainactivityonpause mediaisplay not null isplaying");
            mMediaPlayer.pause();
        }

        Log.d(TAG, "mainactivityonpause ends***********");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "mainactivityondestry");
        super.onDestroy();

        if(mMediaPlayer!=null ) {
            Log.d(TAG, "mainactivityondestry mediaisplay not null");
            mMediaPlayer.release();
        }
        else{
            Log.d(TAG, "mainactivityonondestry mediaisplaynull");//// TODO: 6/2/2016 sil test icin
        }

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "mainactivityonstop");
        super.onStop();

        if(mMediaPlayer!=null ) {
            Log.d(TAG, "mainactivityonstop mediaisplay not null");
            mMediaPlayer.stop();
          //  mMediaPlayer.release();
        }
        else{
            Log.d(TAG, "mainactivityonstop mediaisplayisplaying"+mMediaPlayer.isPlaying());//// TODO: 6/2/2016 sil test icin
        }
    }

    @Override
    protected void onRestart() {//// TODO: 5/31/2016 dogru mu emin degili
        Log.d(TAG, "mainactivityonrestart");
        super.onRestart();


    }

    @Override
    public void onResume() {
        Log.d(TAG,"mainonresume*****************");
        super.onResume();

        mMediaPlayer = MediaPlayer.create(this, R.raw.sound_mainactivity);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();//.release();// STOPSHIP: 6/2/2016

        DatabaseHandler dbHandler =  new DatabaseHandler(MainActivity.this);
        trainingList = dbHandler.getAllTraining(username);//todo getalltraning yerine kontrol yeni alanlari alsa mi
        trainingHalfList = dbHandler.getHalfTraining(username);//2 kere cagiriyoruz bu 2sini hem burda hem showtrainnigguide cunku gui cagriliyor getalldansonrafilan

        //dbHandler.deleteAllTrainingResponses();//// TODO: 5/22/2016 gecici sil
        //dbHandler.getAllTrainingResponse();
        //showTrainingsUI();
        //// TODO: 5/25/2016 toast mesjlar koy

        if (!wasDbExisted) {
            Log.d(TAG, "mainonresume dbhandler dbexists false,getalldb will vbeexecuted");
            if ( CheckNetwork.isOnline(this)) {
                if(!getAllDBAsyncExecuted) {
                    Log.d(TAG, "mainonresume dbhandler dbexists false,getalldb will vbeexecuted async false");
                    getAllDBAsyncExecuted = true;//// TODO: 5/22/2016 bu hicnir yerde falseo olmuyor sanirim cünkü birkere aliniyor
                    new GetAllDatabase(MainActivity.this).execute(username);
                    //orda kendisi  showTrainingsUI(); yapiyor
                }
            } else {
                Log.d(TAG, "mainonresume dbhandler dbexists false,getalldb will vbeexecuted async true");

                CheckNetwork.showNoConnectionDialog(this,true, 1);
                //no need  showTrainingsUI(); because no db
            }
        }
        //database exists but if there are new updates. burda cagiracak.
        else {
            Log.d(TAG, "mainonresume dbhandler dbexists true,");
            if (CheckNetwork.isOnline(this)) {
                Log.d(TAG, "mainonresume dbhandler dbexists true, ispnline");
                //sending unsend responses
                List<Integer> unsendResponseTrainingIDs = new ArrayList<>();
                unsendResponseTrainingIDs = dbHandler.getUnsendResponseTrainingIDs();

                if(unsendResponseTrainingIDs.size()>0)//// TODO: 5/19/2016 burda username atsak mi veritbanında baskasınızki var mıdır ki
                {
                    Log.d(TAG, "mainonresume dbhandler dbexists true, unsendsize>0:"+unsendResponseTrainingIDs.size());
                    for(int i=0 ; i<unsendResponseTrainingIDs.size() ; i++){
                        Log.d(TAG, "mainonresume dbhandler dbexists true, size>0 inside for executingfor traid: "+unsendResponseTrainingIDs.get(i));
                      //  dbHandler.setTrainingSent(unsendResponseTrainingIDs.get(i));
//// TODO: 5/22/2016 burda bunuyazmisim oysa önce internetten yollamam lazim göndermem lazim.orda zaten dogruysa databaselocal yaziyor.buna gerek yok.
                        if(!createTrainingResponseAsyncExecuted) {
                            Log.d(TAG, "mainonresume dbhandler dbexists true, size>0 inside for executingfor traid getAllDBAsyncExecuted false");
                            //her seferinde bir tane yollasin diye, birden fazla olmasin diye.
                            createTrainingResponseAsyncExecuted = true;
                        new CreateTrainingResponse(MainActivity.this, true, username).execute(unsendResponseTrainingIDs.get(i));
                        }//// TODO: 5/25/2016 ayni anda calissin diye bir sey var asynctasklar ama yapmiyorum.sirayla calisacaklar. zaten 2 tanesi cakisabiliyor
                    }//for end
                }
                else{
                    Log.d(TAG, "mainonresume dbhandler dbexists true size<0,");//// TODO: 5/22/2016 gecici bu else, sil
                }
                //  if(){
                // }
                if(!createTrainingResponseAsyncExecuted) {//öbürü yapilmamissa yap.ayni anda olmasşn dşye
                    if(!checkDBUpdateAsyncExecuted) {
                        checkDBUpdateAsyncExecuted = true;
                        new CheckDatabaseUpdate(MainActivity.this).execute(username, dbHandler.getLastTrainingSetUpdate());
                    }
                }
            }
            else {
                if(trainingList.size()==0&&trainingHalfList.size()==0){//hic ttraining yok o yüzdwen zorunlu check yapilmali
                    Log.d(TAG,"mainonresume trainings size: "+trainingList.size()+" "+trainingHalfList.size());
                    CheckNetwork.showNoConnectionDialog(this,true, 4);//// TODO: 5/29/2016 hic trainingi yoksa yazi yazilmali ekran
                }
                else{
                    Log.d(TAG,"mainonresume trainings size: "+trainingList.size()+" "+trainingHalfList.size());
                    CheckNetwork.showNoConnectionDialog(this,false, 5);
                }

                Log.d(TAG, "mainonresume dbexist, no wifi,need new updates check");
            }
            showTrainingsUI();
        }
        Log.d(TAG,"mainonresume end******************");
    }
    TrainingResponse tr;
    //// TODO: 6/1/2016 database tam alindi mi kontrolu yapilabilir.getalldatabase yaparken, kapanırsa mesela yarıda kalir ama dbexists,
    // oldugu icin dogru gozukur

    //// TODO: 6/1/2016 silme yok hic

//// TODO: 25.04.2016 username name n si buyut 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "MAINACTIVITY oncreate");
//// TODO: database baglanacak 3 tablo icin cekecek verileri yeni olmayanlari.degistirme yok simdilik.jsonArray dönüştürücü de lazım. internet var mı diye sormali
        //sqlite database baglanip tarihe gore sıralayip onla karsılastricak. inner yerine baska class daha iyi. aysntask icin
        //önce createtimea bakacak yeni eklenen var mi diye.
//burdan asynctask cagirilacak parametre olrak username ve lastupdate gömderilecek sqliteden cekilen.eger newtraining yazısı gelirse databasea kaydecek direk.
        //   List<ObjectObject> objectList = new ArrayList<ObjectObject>();
//        objectList=    dbHandler.getAllObjectObject();
        // for(int i=0;i<objectList.size();i++){
        //  object = objectList.get(i);
        // Picasso.with(getApplicationContext()).load(imageURL+object.getObjectImage()).into(target);
        // Log.d(TAG, "picasso sonrası"+" objimg.: "+object.getObjectImage());
        // }

//// TODO: 25.04.2016 lastupdate alinmasi lazim SQLitedan. güncellemek için

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = getIntent().getExtras().getString("username");
        wasDbExisted = getIntent().getExtras().getBoolean("wasDbExisted");

        //sqlitedakiler guncel mi diye bakacak
        // new CheckDatabaseUpdate(MainActivity.this, lastUpdate).execute(username);
        //// TODO: 28.04.2016 once database bosmu var mi kontrolu olmali. ilk kez mi aciyor


        //ll = (LinearLayout) findViewById(R.id.linearlayoutleft);
        // sv = (ScrollView) findViewById(R.id.scrollviewleft);
        //   new GetAllDatabase(MainActivity.this,ll).execute(username);
//// TODO: 5/15/2016 sil linearlayout yollamayi ve üsttek slash

        mMediaPlayer = new MediaPlayer();

        textMain = (TextView) findViewById(R.id.text_main);
        textHalfCompletedGames = (TextView) findViewById(R.id.text_half_completed_trainings);
        textUnCompletedGames = (TextView) findViewById(R.id.text_uncompleted_trainings);
        //  textMatch = (TextView) findViewById(R.id.text_match);
        dbHandler = new DatabaseHandler(MainActivity.this);

        trainingList = new ArrayList<Training>();
        trainingHalfList = new ArrayList<Training>();

        rdioGrpUncompleted = new RadioGroup(this);
        rdioGrpHalfCompleted = new RadioGroup(this);

//dbHandler.getAllTrainingResponse();//// TODO: 5/25/2016 sil
//dbHandler.getAllTraining("t");//// TODO: 5/25/2016 sil
        //  dbHandler.getAllObjectObject();     //// TODO: 5/25/2016 sil
        // dbHandler.getAllTrainingObject();     //// TODO: 5/25/2016 sil
        // Log.d(TAG,"LEVEL:"+ dbHandler.getCurrentLevel(86));

        textMain.setText("Hoşgeldin " + username);


        backgroundImg = new ImageView(this);
        backgroundImg = (ImageView) findViewById(R.id.background_img);

        //  GlideDrawableImageViewTarget ivTarget = new GlideDrawableImageViewTarget(ivImg);
        Glide.with(this)
                .load(R.raw.gif_mainactivity) // The image you want to load
                .crossFade()
                .placeholder(R.drawable.img_mainactivity_background_placeholderimg) // The placeholder GIF.
                .into(backgroundImg);

        resultsTable = (TableLayout) findViewById(R.id.dialog_table_results);

        settingsMenuPopup = new ImageView(this);
        settingsMenuPopup = (ImageView) findViewById(R.id.settings_imgbtn);


        settingsMenuPopup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                popup = new PopupMenu(MainActivity.this, settingsMenuPopup);

                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popupmenu_main, popup.getMenu());

                //ikonlar gözüksün diye
                try {
                    Field[] fields = popup.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popup);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                    .getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod(
                                    "setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Log.d(TAG, "you clciked " + item.getTitle() + " id=" + item.getItemId()+"   item.getGroupId():"+  item.getGroupId());//// TODO: 6/2/2016 sil buralari
                        Log.d(TAG, "you clciked " + item.getTitle() + " id=" + item.getItemId() + " item:" + item.toString());

                        if (item.getTitle().toString().contains("onuc")) {//sonuclarinizi gorunse
                            Log.d(TAG, "you clciked sonuc gör");

                            showPopupDialog();

                        } else {
                            Log.d(TAG, "you clciked logout");


                            final Context ctx = MainActivity.this;
                            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                            builder.setCancelable(true);
                            builder.setTitle("Oturumunuzu sonlandirmak istediginize emin misiniz?");
                            //builder.setMessage("Cikilsin mi?");
                            builder.setPositiveButton("Oturumu kapat", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    dbHandler.forgetLogin(username);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("Iptal", new DialogInterface.OnClickListener() {
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
                        return true;
                    }
                });
                popup.show(); //showing popup menu


            }
        }); //closing the setOnClickListener method
    }

//// TODO: 30.04.2016 once database ayarla sonra layout duzelt
        //// TODO: 30.04.2016 if sqlite=nullsa alttakini cagir. ama ya baska kullanıcı girdiyse dolduduysa db ne olacak. bu ihtimali göz ardı ediyorum
        //// TODO: 5/27/2016 burayi block comment yaptim cünkü zaten resumede da aynisi var
                    // dbHandler.getUnsendResponseTrainingIDs();//// TODO: 5/19/2016 burda username atsak mi veritbanında baskasınızki var mıdır ki
                    // TODO: 5/19/2016 burda username atsak mi veritbanında baskasınızki var mıdır ki
                       //todo acaba checkdb baslsmis olabilri mi, nasil olur ki öyle
                               //// TODO: 5/22/2016 gecici bu else, sil
                   // TODO: 5/25/2016 hic egitim yoksa kontrol lazim sanki
//// TODO: 02.05.2016 halfcompelted yap-yapildi

    private void showPopupDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        FragmentManager fm = getFragmentManager();
        ResultsTableAlertDialog dialogFragment = new ResultsTableAlertDialog ();
      //  dialogFragment.show(fm, "Sample Fragment");
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout


/*
        HashMap<String,Integer> CH = new HashMap<String, Integer>();
        HashMap<String,Integer> CT = new HashMap<String, Integer>();
        HashMap<String,Integer> CF = new HashMap<String, Integer>();
        HashMap<String,Integer> SH = new HashMap<String, Integer>();
        HashMap<String,Integer> SF = new HashMap<String, Integer>();
        HashMap<String,Integer> ST = new HashMap<String, Integer>();

        Log.d("")
        for(int i=0 ; i<6 ; i++){

        }
        dbHandler.getCHResults(username);
        dbHandler.getCTResults(username);
        dbHandler.getCFResults(username);
        dbHandler.getSFResults(username);
        dbHandler.getSHResults(username);
        dbHandler.getSTResults(username);
*/



/*
        //1 match,3 secme, 2 count

        List<String> objectNameList = new ArrayList<String>();

        objectNameList = dbHandler.getAllObjectObjectName(username);

//objectname
for(int j=0; j<objectNameList.size() ; j++) {

    TableRow table_row = new TableRow(this);
    table_row = (TableRow) findViewById(R.id.table_row);


    String objectName = objectNameList.get(j);

    ((TextView)table_row.findViewById(R.id.object_name)).setText(objectName);

    TextView [] textViewArray = new TextView[9];

    TextView t1 = new TextView(this);
    TextView t2 = new TextView(this);
    TextView t3 = new TextView(this);
    TextView t4 = new TextView(this);
    TextView t5 = new TextView(this);
    TextView t6 = new TextView(this);
    TextView t7 = new TextView(this);
    TextView t8 = new TextView(this);
    TextView t9 = new TextView(this);

    t1 = (TextView) findViewById(R.id.true_matching);
    t2 = (TextView) findViewById(R.id.half_matching);
    t3 = (TextView) findViewById(R.id.false_matching);

    t4 = (TextView) findViewById(R.id.true_counting);
    t5 = (TextView) findViewById(R.id.half_counting);
    t6 = (TextView) findViewById(R.id.false_counting);

    t7 = (TextView) findViewById(R.id.true_choosing);
    t8 = (TextView) findViewById(R.id.half_choosing);
    t9 = (TextView) findViewById(R.id.false_choosing);

    textViewArray[1] = t1;
            textViewArray[2] = t2;
    textViewArray[3] = t3;
            textViewArray[4] = t4;
    textViewArray[5] = t5;
            textViewArray[6] = t6;
    textViewArray[7] = t7;
            textViewArray[8] = t8;
    textViewArray[9] = t9;


    //behavor
    for (int i = 1 ; i < 4; i++) {


        //response
        for(int k=1;k<4;k++){

           textViewArray[i*3+k-3].setText(dbHandler.getCountForTable(i,k,objectName,username));

        }



    }
    resultsTable.addView(table_row);
    resultsTable.requestLayout();
}


        builder.setView(inflater.inflate(R.layout.dialog_table_results, null));
          builder.create();
        //builder.show();
        */


        Intent intent = new Intent(MainActivity.this, ShowTableResults.class);
        intent.putExtra("username", username);
        startActivity(intent);//finish yok cunku geri gelecek//// TODO: 6/4/2016 olmali mi
}







    public void showTrainingsUI(){
        Log.d(TAG,"MAINACTIVITY showtrainingui");

      /*  tr = new TrainingResponse();
        for(int i=0;i<1;i++){//todo sil geicic
            tr.setTrainingCompletedAll(0);
            tr.setTrainingStarted(1);
            tr.setStudentUserName("t");
            tr.setTrainingResponseScore(551);
            tr.setTrainingResponseFinishTime("44444");
            tr.setTrainingID(86);
            tr.setObjectID(666+i);
            tr.setResponseSent(0);
            dbHandler.addTrainingResponse(tr);
        }*/
        //dbHandler.getAllTrainingResponse();


        trainingList = dbHandler.getAllTraining(username);//todo getalltraning yerine kontrol yeni alanlari alsa mi
        trainingHalfList = dbHandler.getHalfTraining(username);

//dbHandler.getHalfTraining(username);
        // dbHandler.getAllTraining();

        rdioGrpUncompleted.removeAllViews();
        rdioGrpHalfCompleted.removeAllViews();

        //new trainings being taken
        for (int i = 0; i < trainingList.size(); i++) {
            Log.d(TAG,"MAINACTIVITY traininglist adding for");
            Training training = trainingList.get(i);

            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText( training.getTrainingAim()+" - egitim "+training.getTrainingID());
            rdioGrpUncompleted.addView(radioButton);
        }
//dbHandler.getDemoObjectObject(88);test icindi bu

        (( ViewGroup) findViewById(R.id.radio_uncompleted_trainings)).removeAllViews();
        (( ViewGroup) findViewById(R.id.radio_uncompleted_trainings)).addView(rdioGrpUncompleted);

        rdioGrpUncompleted.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG,"MAINACTIVITY activitythis radio selected: "+checkedId);

                Training training = trainingList.get(checkedId);

                if(training.getBehaviorID()==3){
                    Intent intent = new Intent(MainActivity.this, ChoosingGame.class);
                    intent.putExtra("trainingid", training.getTrainingID());
                    intent.putExtra("level",0);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    finish();
                }//// TODO: 5/20/2016 intentleri finish yapmaya öldürmeye gerek var mi geri tusuyal gelisnsin bence
                else{
                    if(training.getBehaviorID()==1){
                        Intent intent = new Intent(MainActivity.this, MatchingGame.class);
                        intent.putExtra("trainingid", training.getTrainingID());
                        intent.putExtra("level",0);
                        intent.putExtra("username",username);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, CountingGame.class);
                        intent.putExtra("trainingid", training.getTrainingID());
                        intent.putExtra("level",0);
                        intent.putExtra("username",username);
                        startActivity(intent);
                        finish();
                    }
                }
            }//oncheckecchanged end
        });//listener end

        //half trainings being taken
        for (int i = 0; i < trainingHalfList.size(); i++) {

            Training training = trainingHalfList.get(i);

            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText( training.getTrainingAim()+" - egitim "+training.getTrainingID());
            rdioGrpHalfCompleted.addView(radioButton);
        }

        (( ViewGroup) findViewById(R.id.radio_half_completed_trainings)).removeAllViews();
        (( ViewGroup) findViewById(R.id.radio_half_completed_trainings)).addView(rdioGrpHalfCompleted);

        rdioGrpHalfCompleted.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG,"MAINACTIVITY activitythis radio selected: "+checkedId);

                Training training = trainingHalfList.get(checkedId);

                if(training.getBehaviorID()==3){
                    Intent intent = new Intent(MainActivity.this, ChoosingGame.class);
                    intent.putExtra("trainingid", training.getTrainingID());
                    intent.putExtra("level",dbHandler.getCurrentLevel(training.getTrainingID()));
                    intent.putExtra("username",username);
                    startActivity(intent);
                    finish();
                }
                else{
                    if(training.getBehaviorID()==1){
                        Intent intent = new Intent(MainActivity.this, MatchingGame.class);
                        intent.putExtra("trainingid", training.getTrainingID());
                        intent.putExtra("level",dbHandler.getCurrentLevel(training.getTrainingID()));
                        intent.putExtra("username",username);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, CountingGame.class);
                        intent.putExtra("trainingid", training.getTrainingID());
                        intent.putExtra("level",dbHandler.getCurrentLevel(training.getTrainingID()));
                        intent.putExtra("username",username);
                        startActivity(intent);
                        finish();
                    }
                }
            }//oncheckecchanged end
        });//listener end

        Log.d(TAG,"MAINACTIVITY showtrainingui end");
    }//showtraining end



}//end of class