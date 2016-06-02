package com.example.caneraydin.androidwithlogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caneraydin.androidwithlogin.domains.Login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// yapıldıt odo geri tusunu ayarla

public class LoginActivity extends Activity {

    String TAG = "Chic",
   username,password;

    private Button btn_login;
    private TextView txt_username,txt_password,txt_attempts_left;

    int counter = 3;

    MediaPlayer mMediaPlayer;

    CheckBox chckbxLoginRemember;

    boolean isChecked;//for checkbox

    Login login;

    DatabaseHandler dbHandler;

    boolean wasDbExisted;//maine yollamak lazim cünlü orda da kontrol var, burda exists bakınca yeniyse artik orda exist bakinca yeni olmayacagi icin

    @Override
    protected void onPause() {
        Log.d(TAG, "loginactivityonpause***********");
        super.onPause();

        if(mMediaPlayer.isPlaying()&&mMediaPlayer!=null ){
            Log.d(TAG, "loginactivityonpause mediaisplay not null isplaying");
            mMediaPlayer.pause();
        }

        Log.d(TAG, "loginactivityonpause ends***********");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "loginactivityondestry");
        super.onDestroy();

        if(mMediaPlayer!=null ) {
            Log.d(TAG, "loginactivityondestry mediaisplay not null");
            mMediaPlayer.release();
        }
        else{
            Log.d(TAG, "loginactivityondestry mediaisplay null");//// TODO: 6/2/2016 sil test icin
        }

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "loginactivityonstop");
        super.onStop();

        if(mMediaPlayer!=null ) {
            Log.d(TAG, "loginactivityonstop mediaisplay not null");
            mMediaPlayer.stop();
            //  mMediaPlayer.release();
        }
        else{
            Log.d(TAG, "loginactivityonstop mediaisplayisplaying"+mMediaPlayer.isPlaying());//// TODO: 6/2/2016 sil test icin
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "loginactivityonresume");
        super.onResume();

        mMediaPlayer = MediaPlayer.create(this, R.raw.sound_loginactivity);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();//.release();// STOPSHIP: 6/2/2016
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "loginactivityonrestart");
        super.onRestart();
    }

    public void onBackPressed() {
        Log.d(TAG, "choosing game backbuttpn");

        final Context ctx = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle("Uygulamayi sonlandirmak istediginize emin misiniz?");
        //builder.setMessage("Cikilsin mi?");
        builder.setPositiveButton("Uygulamadan cik", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // terminateProcesses();
                //sonlandiriyor
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());//// TODO: 6/2/2016 böyle mi olsun
               // finish();
            }
        });
        builder.setNegativeButton("Uygulamaya devam et", new DialogInterface.OnClickListener() {
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
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"*****************************");
        Log.d(TAG,"*****************************");
        Log.d(TAG,"*****************************");
        Log.d(TAG,"");
        Log.d(TAG, "login activity OnCreate************");

        super.onCreate(savedInstanceState);
        dbHandler = new DatabaseHandler(this);//islemler icin gerekiyor onceden almak

        //geçici bu sil sonra//todo sil burayi
       // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        // startActivity(intent);
        //  finish();

        wasDbExisted = dbHandler.ifDatabaseExists();
        Log.d(TAG, "login activity wasdbexister="+wasDbExisted);

//isloginremember username döndürüyor
        if (wasDbExisted &&
                dbHandler.isLoginRemember()!=null) {//db varsa ve reember seciliyse maine git.
            Log.d(TAG, "login activity butonlogin on click remembertrue username=" + dbHandler.isLoginRemember()
                    +"dbexists"+dbHandler.ifDatabaseExists());//todo sil hepsini

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("username", dbHandler.isLoginRemember());
            i.putExtra("wasDbExisted", wasDbExisted);

            // intent.putExtra("MESSAGE", "You have been successfully Registered");
            startActivity(i);
            finish();
        }
        else {//db yoksa zaten login de yoktur beni hatırla da. dbvar login seçili değilse de giriyor

            Log.d(TAG, "login activity butonlogin on click rememberfalse username=" + dbHandler.isLoginRemember()
                    +"dbexists"+dbHandler.ifDatabaseExists());//todo sil hepsini


            setContentView(R.layout.activity_login);

        mMediaPlayer = new MediaPlayer();

        btn_login = (Button) findViewById(R.id.button_login);

        login = new Login();

        txt_username = (TextView) findViewById(R.id.editText_username);
        txt_password = (TextView) findViewById(R.id.editText_password);
        txt_attempts_left = (TextView) findViewById(R.id.text_attempts_left);

        chckbxLoginRemember = (CheckBox) findViewById(R.id.chckbox_loginremember);

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "login activity butonlogin on click");

                username = txt_username.getText().toString();
                password = txt_password.getText().toString();
                txt_attempts_left.setBackgroundColor(Color.YELLOW);
                isChecked = chckbxLoginRemember.isChecked();

                if (!isChecked) {
                    login.setLoginRemember(0);
                    Log.d(TAG, "login activity butonlogin on click rememberfalse");//// TODO: 6/2/2016 gecicisil
                } else {
                    login.setLoginRemember(1);
                    Log.d(TAG, "login activity butonlogin on click remembertrue");
                }

                login.setLoginName(username);
                login.setLoginPassword(password);

                switch (dbHandler.isLoginSuccessful(login)) {// 3 seçebek var
                    //2= success
                    //1= wrong password
                    //0= no data go online then add to local if success

                    case 0:
                        if (!CheckNetwork.isOnline(LoginActivity.this)) {

                            CheckNetwork.showNoConnectionDialog(LoginActivity.this, true, 0);
                            Log.d(TAG, "LoginActivity isnotonline while try wait error");

                        } else {
                            counter--;
                            new SigninActivity(LoginActivity.this, login,wasDbExisted).execute();//username postexecuteta lazim
                        }

                        txt_attempts_left.setText(Integer.toString(counter));

                        if (counter == 0) {//todo azaltiyor sonra ekrana yansitiyor kalani. orda azaldiktan sonra async bittigi icin oyle kalyır bir süre
                            btn_login.setEnabled(false);
                        }

                        break;

                    case 1:
                        Log.d(TAG, "login activity case 1 result unsuccess");
                        Toast.makeText(getApplicationContext(), "Giris islemi basarisiz, yanlis sifre, lutfen tekrar deneyiniz...", Toast.LENGTH_LONG).show();

                        counter--;
                        txt_attempts_left.setText(Integer.toString(counter));

                        if (counter == 0) {//todo azaltiyor sonra ekrana yansitiyor kalani. orda azaldiktan sonra async bittigi icin oyle kalyır bir süre
                            btn_login.setEnabled(false);
                        }

                        break;

                    case 2://bourca counterla ugrasmadim
                        Log.d(TAG, "login activity onpostexe result success");
                        Toast.makeText(getApplicationContext(), "Giris islemi basarili!", Toast.LENGTH_LONG).show();
                        login.toString();//// TODO: 6/2/2016 geçici sil

                        if(isChecked){
                            dbHandler.rememberLogin(username);//gerekiyor cünkü sonradan hatirla deyince hatirlamiyor.sadeee asyncde yaratirken oluyort
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("wasDbExisted", wasDbExisted);

                        // intent.putExtra("MESSAGE", "You have been successfully Registered");
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
        Log.d(TAG, "login activity oncreate end*********");
    }
    }

}

class SigninActivity  extends AsyncTask<String,Void,String> {
    ProgressDialog dialog;
    private Context context;
    String TAG = "Chic";
    private String username, password;

    DatabaseHandler dbHandler;

    Login login;

    boolean wasDbExisted;

    int loginRemember;

    public SigninActivity(Context context, Login login, boolean wasDbExisted) {
        Log.d(TAG, "login activity signinactivity consturct");

        this.login = new Login();

        this.context = context;
       // this.username = username;
        this.login = login;
        this.wasDbExisted = wasDbExisted;

        username = this.login.getLoginName();
        password = this.login.getLoginPassword();
        loginRemember = this.login.getLoginRemember();
        this.login.toString();//// TODO: 6/2/2016 geçici sil
    }

    protected void onPreExecute(){
        Log.d(TAG, "login activity signinactivity onpre");
        dialog = ProgressDialog.show(context, "Lutfen bekleyiniz...", null, true, true);
    }

    @Override
    protected String doInBackground(String... arg0) {
        Log.d(TAG, "login activity signinactivity doin");

      //  Log.d(TAG, "login activity signinactivity consturct try");
     //   String username = (String) arg0[0];
       // String password = (String) arg0[0];

        Log.d(TAG, "login activity signinactivity username: " + username + " password: " + password);

        String uri = "http://oep.esy.es/login.php?" + "username=" + username + "&password=" + password;
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader bufferedReader = null;
            URL url = new URL(uri);
            Log.d(TAG, "login activity url " + url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

        bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        //  Log.d(TAG," bufferedReader "+ bufferedReader);
        String json;

            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json + "\n");
     //           Log.d(TAG, "login activity sb " + sb.toString());
                Log.d(TAG, "login activity sb: " + sb);
            }
        } catch (IOException e) {
            Log.d(TAG, "login activity hata doin");
            return new String("Exception: " + e.getMessage());
        }//// TODO: 6/1/2016 internet filan gitmsse exception ile bir seyler yapilmali
        return sb.toString().trim();
    }

        @Override
    protected void onPostExecute(String result){
            dialog.dismiss();
            Log.d(TAG, "login activity onpostexe, result='"+result+"'");

            if(result.equals("success")){
                Log.d(TAG, "login activity onpostexe result success");
                Toast.makeText(context,"Giris islemi basarili!", Toast.LENGTH_LONG).show();

                //sadece successa koyuyorum locale, yanlissa kontrol yok oyle bir username var mi yok mu
                //zaten intenrete baglanmissa localde kayitli degildir. o yüzdfen update yapmyorum add yapiyorum
                dbHandler = new DatabaseHandler(context);
                login.toString();//// TODO: 6/2/2016 geçici sil
                dbHandler.addLogin(login);
dbHandler.getAllLogin();//// TODO: 6/2/2016 geçici sil

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("wasDbExisted", wasDbExisted);
               // intent.putExtra("MESSAGE", "You have been successfully Registered");
                context.startActivity(intent);
                ((Activity) context).finish();
            }
            else{
                Log.d(TAG, "login activity onpostexe result unsuccess");
                Toast.makeText(context,"Giris islemi basarisiz oldu, lutfen tekrar deneyiniz...", Toast.LENGTH_LONG).show();
                //// TODO: 6/2/2016 counter 0sa tekrar denyiniz olmaz da bosvwer.
            }
    }//postexecute end
}//async end