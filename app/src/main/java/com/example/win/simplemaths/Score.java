package com.example.win.simplemaths;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Level;

import static com.example.win.simplemaths.R.id.finalscore;
import static com.example.win.simplemaths.R.id.level;
import static com.example.win.simplemaths.R.id.score;
import static com.example.win.simplemaths.R.id.share;

public class Score extends AppCompatActivity {
 ImageButton sharebtn;
 TextView t1,r,w,activitytype,tv_level,tv_highscore;
 int fscore,t,f;
 int type;
 int temp,hs;
 Button play_again;
 MyDbHandler myDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        sharebtn=(ImageButton)findViewById(R.id.share);
        t1=(TextView) findViewById(R.id.finalscore);
        r=(TextView)findViewById(R.id.r1) ;
        w=(TextView)findViewById(R.id.wrong) ;
        tv_level=(TextView)findViewById(R.id.level);
        play_again=(Button)findViewById(R.id.play_again);
        tv_highscore=(TextView)findViewById(R.id.highscore_tv);
        activitytype=(TextView)findViewById(R.id.activity_type);


        myDbHandler= new MyDbHandler(this,"db_highscore",null,1);

        SQLiteDatabase sqLiteDatabase= myDbHandler.getWritableDatabase();


        fscore = getIntent().getIntExtra("totalscore",0);
        t=getIntent().getIntExtra("true",0);
        f=getIntent().getIntExtra("false",0);
        type=getIntent().getIntExtra("type",0);
        int level =getIntent().getIntExtra("level",0);
        myDbHandler.setType(type);
        myDbHandler.setLevel(level);
        r.setText(":"+t);
        w.setText(":"+f);
        t1.setText(" :"+fscore);
        switch (type) {
            case 1:
                activitytype.setText(" Addition");
                break;
            case 2:
                activitytype.setText(" Substraction");
                break;
            case 3:
                activitytype.setText(" Multiplication");
                break;
            case 4:
                activitytype.setText(" Division");
                break;
        }
        switch (level)
        {
            case 1:
                tv_level.setText("Level:1  ");
                break;
            case 2:
                tv_level.setText("Level:2  ");
                break;
            case 3:
                tv_level.setText("Level:3  ");
                break;

        }
        temp = myDbHandler.get_Highscore();
        if(temp>fscore)
        {
             hs=temp;
        }
        else
        {
            hs=fscore;
        }
        myDbHandler.setHighscore(hs);
        tv_highscore.setText("Highest Score : " +String.valueOf(hs));

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Simple Maths - Fun way to learn Maths..my score:"+fscore);
                startActivity(intent);
            }
        });
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Score.this,Type.class);
                startActivity(intent);

            }
        });

    }

}
