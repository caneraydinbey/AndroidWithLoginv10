package com.example.caneraydin.androidwithlogin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chico on 6/3/2016.
 */
public class ResultsTableAlertDialog extends DialogFragment {
    String TAG = "Chic";
    DatabaseHandler dbHandler;
    View rootView;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_table_results, container, false);
        getDialog().setTitle("Simple Dialog");
        dbHandler = new DatabaseHandler(getContext());
        return rootView;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Simple Dialog");

        //List<String> objectNameList = new ArrayList<String>();

      //  objectNameList = dbHandler.getAllObjectObjectName("t");

//objectname


            //TableRow table_row = new TableRow(getActivity());
            View table_row = (TableRow) rootView.findViewById(R.id.table_row);


            String objectName ="objectName";

            ((TextView) table_row.findViewById(R.id.object_name)).setText(objectName);

            TextView[] textViewArray = new TextView[9];

            TextView t1 = new TextView(getActivity());
            TextView t2 = new TextView(getActivity());
            TextView t3 = new TextView(getActivity());
            TextView t4 = new TextView(getActivity());
            TextView t5 = new TextView(getActivity());
            TextView t6 = new TextView(getActivity());
            TextView t7 = new TextView(getActivity());
            TextView t8 = new TextView(getActivity());
            TextView t9 = new TextView(getActivity());

            t1 = (TextView) table_row.findViewById(R.id.true_matching);
            t2 = (TextView) table_row.findViewById(R.id.half_matching);
            t3 = (TextView) table_row.findViewById(R.id.false_matching);

            t4 = (TextView) table_row.findViewById(R.id.true_counting);
            t5 = (TextView) table_row.findViewById(R.id.half_counting);
            t6 = (TextView) table_row.findViewById(R.id.false_counting);

            t7 = (TextView) table_row.findViewById(R.id.true_choosing);
            t8 = (TextView) table_row.findViewById(R.id.half_choosing);
            t9 = (TextView) table_row.findViewById(R.id.false_choosing);

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
            for (int i = 1; i < 4; i++) {


                //response
                for (int k = 1; k < 4; k++) {

                    textViewArray[i * 3 + k - 3].setText(dbHandler.getCountForTable(i, k, objectName, "t"));

                }


            }
            // resultsTable.addView(table_row);
            //  resultsTable.requestLayout();

        return builder.create();
    }}












