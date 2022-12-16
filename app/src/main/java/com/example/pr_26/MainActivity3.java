package com.example.pr_26;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    int amount = 0;
    int coffee = 1;
    int coffeeCost = 250;
    int combo = 1;
    int comboCost = 250;
    int cost = 250;
    TextView tv_totalCost;
    TextView tv_count;
    TextView tv_count2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);
        tv_totalCost = (TextView) findViewById(R.id.tv_totalCost);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_count2 = (TextView) findViewById(R.id.tv_count2);
        SQLiteDatabase database = openOrCreateDatabase("DatabaseName", Context.MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM BASKET ORDER BY id DESC", null);
        cursor.moveToFirst();
        ((TextView) findViewById(R.id.tv_name)).setText(cursor.getString(4));
        ((TextView) findViewById(R.id.tv_phone)).setText(cursor.getString(5));
        if(cursor.getInt(1) == 1){
            ((TextView) findViewById(R.id.tv_sugar)).setVisibility(View.VISIBLE);
        }else{
            ((TextView) findViewById(R.id.tv_sugar)).setVisibility(View.INVISIBLE);
        }

        if(cursor.getInt(2) == 1){
            ((TextView) findViewById(R.id.tv_cinnamon)).setVisibility(View.VISIBLE);
        }else{
            ((TextView) findViewById(R.id.tv_cinnamon)).setVisibility(View.INVISIBLE);
        }


        coffeeCost = coffee * cost;
        comboCost = combo * cost;
        amount = coffeeCost + comboCost;
        tv_totalCost.setText(String.valueOf(amount)+" рублей");
    }

    public void OnPlusClick(View view){
        switch(view.getId()){
            case R.id.btn_plus:
                coffee++;
                break;
            case R.id.btn_minus:
                if(coffee > 0)
                    coffee--;
                break;
            case R.id.btn_plus2:
                combo++;
                break;
            case R.id.btn_minus2:
                if(combo > 0)
                    combo--;
                break;
        }
        coffeeCost = coffee * cost;
        comboCost = combo * cost;
        amount = coffeeCost + comboCost;
        tv_count.setText(String.valueOf(coffee));
        tv_count2.setText(String.valueOf(combo));
        tv_totalCost.setText(String.valueOf(amount)+ " рублей");
    }
}