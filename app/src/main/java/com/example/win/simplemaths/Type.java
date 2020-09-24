package com.example.win.simplemaths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Type extends AppCompatActivity {


    Button tadd,tsub,tmul,tdiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        tadd = (Button) findViewById(R.id.tadd);
        tsub = (Button)findViewById(R.id.tsub);
        tmul = (Button)findViewById(R.id.tmul);
        tdiv = (Button)findViewById(R.id.tdiv);
        final  Intent i = new Intent(Type.this,Set_level.class);
        tadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i.putExtra("value",1);
                startActivity(i);
            }
        });
        tsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i.putExtra("value",2);
                startActivity(i);
            }
        });
        tmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("value",3);
                startActivity(i);
            }
        });
        tdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("value",4);
                startActivity(i);
            }
        });

    }
}
