package com.example.caneraydin.androidwithlogin.games;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.caneraydin.androidwithlogin.DatabaseHandler;
import com.example.caneraydin.androidwithlogin.R;
import com.example.caneraydin.androidwithlogin.domains.ObjectObject;
import com.example.caneraydin.androidwithlogin.domains.TrainingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caneraydin on 17.04.2016.
 */
public class CountingGame extends AppCompatActivity implements View.OnDragListener {
    String TAG = "Chic";

    RelativeLayout rLayout;

    DatabaseHandler dbHandler;

    int trainingID,objectCount;

    List<TrainingObject> trainingObjectList;

    List<ObjectObject> demoObjectObject;

    byte[] imgBytes;

    Bitmap bmp;

    ImageView imageAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "counting ggame OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegame);

        dbHandler = new DatabaseHandler(this);
        rLayout = new RelativeLayout(this);
        trainingObjectList = new ArrayList<TrainingObject>();
        demoObjectObject = new ArrayList<ObjectObject>();
        imageAnswer=new ImageView(this);

        rLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        rLayout.setBackgroundColor(Color.MAGENTA);

        trainingID = getIntent().getExtras().getInt("trainingid");
        Log.d(TAG, "traininfid: "+trainingID);

        demoObjectObject = dbHandler.getDemoObjectObject(trainingID);

        RelativeLayout.LayoutParams rLayParams = new RelativeLayout.LayoutParams(140,140);

        rLayParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rLayParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        for (int i = 0; i<demoObjectObject.size() ;i++) {
            Log.d(TAG, "for i="+i);
            ObjectObject demoObject = new ObjectObject();
            demoObject = demoObjectObject.get(i);

            Log.d(TAG,"Bu nesnenin şekli "+dbHandler.getShapeName(demoObject.getShapeID())+
                    " rengi ise"+dbHandler.getColorName(demoObject.getColorID()));

            imgBytes = demoObject.getObjectImageBlob();
            bmp = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

            imageAnswer.setImageBitmap(bmp);
            rLayout.removeAllViews();
            rLayout.addView(imageAnswer,rLayParams);
        }



        trainingObjectList = dbHandler.getAllTrainingObject(trainingID);
//// TODO: 02.05.2016 egitim kismi oalcak burda tek tek gosterilecek




        Log.d(TAG, "counting ggame OnCreate ends");
    }//oncreate end

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }
}//class end
