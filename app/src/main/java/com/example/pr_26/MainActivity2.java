package com.example.pr_26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    int delivery = 1;
    int sugar_1 = 0;
    int cinnamon_1 = 0;
    RadioButton rb_delivery;
    RadioButton rb_pickup;
    int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        Intent intent = getIntent();
        rb_delivery = (RadioButton) findViewById(R.id.rb_delivery);
        rb_pickup = (RadioButton) findViewById(R.id.rb_pickup);
    }

    public void radioClicked(View view){
        int senderID = view.getId();
        switch(senderID){
            case R.id.rb_delivery:
                rb_pickup.setChecked(false);
                delivery = 1;
                break;

            case R.id.rb_pickup:
                rb_delivery.setChecked(false);
                delivery = 0;
                break;
        }
    }

    public void onBasketClick(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void Send(View view){
        Switch sugar = (Switch) findViewById(R.id.sugar);
        Switch cinnamon = (Switch) findViewById(R.id.cinnamon);
        if(cinnamon.isChecked()){
            cinnamon_1 = 1;
        }else{
            cinnamon_1 = 0;
        }
        if(sugar.isChecked()){
            sugar_1 = 1;
        }else{
            sugar_1 = 0;
        }
        String name = String.valueOf(((EditText) findViewById(R.id.editTextTextPersonName)).getText());
        String phone = String.valueOf(((EditText) findViewById(R.id.editTextPhone)).getText());
        SQLiteDatabase database = openOrCreateDatabase("DatabaseName", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS BASKET (id INTEGER PRIMARY KEY AUTOINCREMENT, sugar INTEGER, cinnamon INTEGER, delivery INTEGER, name TEXT, phone TEXT)");
        database.execSQL("INSERT OR IGNORE INTO BASKET (sugar, cinnamon, delivery, name, phone) VALUES (" + sugar_1 + ", " + cinnamon_1 + ", " + delivery + ", '" + name + "','" + phone + "')");
    }

}