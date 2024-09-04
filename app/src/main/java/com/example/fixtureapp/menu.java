package com.example.fixtureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {
    CardView cardone,cardtwo,cardthree,cardfour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardone =findViewById(R.id.card1);
        cardthree =findViewById(R.id.card3);
        cardtwo=findViewById(R.id.card2);
        cardone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this,fixtureschedule.class));
            }
        });

        cardtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this, stadium.class));
            }
        });



    }
}