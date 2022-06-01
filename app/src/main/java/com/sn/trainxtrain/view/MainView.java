package com.sn.trainxtrain.view;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sn.trainxtrain.MainActivity;
import com.sn.trainxtrain.R;
import com.sn.trainxtrain.model.Fields;
import com.sn.trainxtrain.model.Records;

import java.util.Calendar;

public class MainView {

    private int marges = 40;
    private LinearLayout.LayoutParams params;
    private String[] days = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    private int prevDay;
    private int colSwitch;
    private int[] rBtoLyon = {R.drawable.rounded_corner,R.drawable.rounded_corner2};
    private int[] rBtoParis = {R.drawable.rounded_corner_toparis,R.drawable.rounded_corner_toparis2};
    private Records[] resultsRec;

    public MainView(){
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        colSwitch = 1;
        prevDay = 0;
    }

    public void showRecords(MainActivity mainActivity,String toWhere, Records[] records, LinearLayout mLayout){
        mLayout.removeAllViews();
        resultsRec = records;

        int[] listInt = new int[records.length];
        for(int i = 0; i < records.length - 1; i++){
            String[] dateList = records[i].getFields().getDate().split("-");
            String[] hourList = records[i].getFields().getHeure_depart().split(":");

            String sVal = dateList[1]
                    + dateList[2]
                    + hourList[0]
                    + hourList[1];
            listInt[i] = Integer.parseInt(sVal);
        }
        bubbleSort(listInt, records.length);

        for(Records record : resultsRec){
            TextView textView = new TextView(mainActivity);
            textView.setPadding(marges,marges,marges,marges);

            textView.setLayoutParams(params);
            textView.setLineSpacing(0, 1.3f);
            textView.setTextSize(20);



            Fields f = record.getFields();
            int day = getDayWeek(f.getDate())-1;
            String jourDep = days[day];

            String content =  "";
            content +=  jourDep+"  "+f.getDate()  +"\n";
            content += "  " + f.getHeure_depart() +" => " + f.getHeure_arrivee()+  "\n";

            content += "N° " + f.getTrain_no();
            textView.setText(content);

            if(prevDay != day){
                colSwitch = (colSwitch+1)%2;
                prevDay = day;
            }

            if (toWhere == "ToParis") {
                textView.setBackgroundResource(rBtoParis[colSwitch]);
                textView.setTextColor(Color.parseColor("#053003"));
            }else if(toWhere == "ToLyon"){
                textView.setBackgroundResource(rBtoLyon[colSwitch]);
                textView.setTextColor(Color.parseColor("#072C30"));
            }
            mLayout.addView(textView);
        }
    }
    private int getDayWeek(String dateString){
        Calendar c = Calendar.getInstance();
        String[] dateList = dateString.split("-");
        c.set(Calendar.YEAR,Integer.parseInt(dateList[0]));
        c.set(Calendar.MONTH,Integer.parseInt(dateList[1])-1);
        c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dateList[2]));
        System.out.println(c.getTime().toString());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }


    private void bubbleSort(int arr[], int n) {
        if (n == 1) {
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                Records tempRec = resultsRec[i];
                arr[i] = arr[i + 1];
                resultsRec[i] = resultsRec[i+1];
                arr[i + 1] = temp;
                resultsRec[i+1] = tempRec;
            }
        }
        bubbleSort(arr, n - 1);
    }
}
