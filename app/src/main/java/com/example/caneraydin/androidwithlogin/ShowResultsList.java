package com.example.caneraydin.androidwithlogin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

/**
 * Created by Chico on 6/4/2016.
 */
public class ShowResultsList extends Activity{

    String TAG = "Chic",
            username;

    DatabaseHandler dbHandler;

   // TableLayout resultsTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "SHOWTABLERESULTS oncreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getExtras().getString("username");

       // resultsTable = (TableLayout) findViewById(R.id.dialog_table_results);

        dbHandler = new DatabaseHandler(this);

    }
















}
