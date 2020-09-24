package com.example.win.simplemaths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Set_level extends AppCompatActivity {

    Button btn_easy,btn_hard,btn_normal;
    int value1,Max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_level);
        btn_easy=(Button) findViewById(R.id.bt1);
        btn_normal=(Button) findViewById(R.id.bt2);
        btn_hard=(Button) findViewById(R.id.bt3);
        this.value1=getIntent().getIntExtra("value",0);
        final Intent lvltogame = new Intent(Set_level.this,GameActivity.class);

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Max=1;
                lvltogame.putExtra("value",value1);
                lvltogame.putExtra("max",Max);
                startActivity(lvltogame);
            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Max=2;
                lvltogame.putExtra("value",value1);
                lvltogame.putExtra("max",Max);
                startActivity(lvltogame);
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Max=3;
                lvltogame.putExtra("value",value1);
                lvltogame.putExtra("max",Max);
                startActivity(lvltogame);
            }
        });

    }
}
