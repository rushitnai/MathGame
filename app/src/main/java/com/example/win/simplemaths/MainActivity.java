package com.example.win.simplemaths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    ImageButton playbtn,sharebtn,ratebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize views
        t1=(TextView)findViewById(R.id.simplemaths);
        playbtn=(ImageButton)findViewById(R.id.play);
        sharebtn=(ImageButton)findViewById(R.id.share);
        ratebtn=(ImageButton)findViewById(R.id.rate);

        // refer the app on diffrent app
        sharebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Math Games - Fun way to learn Maths. http://www.play.google.com");
                startActivity(intent);
            }
        });
        ratebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"you can rate us on playstore", LENGTH_SHORT).show();;
            }
        });
        playbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Type.class);
                startActivity(i);
            }
        });
    }

}
