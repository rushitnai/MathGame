package com.example.win.simplemaths;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends AppCompatActivity {
    TextView time,score,q1,q2,ans,time1,time2;
    ImageButton right,wrong;
    boolean isanscorrect;
    boolean stoptimer=false;
    private int timeleft;
    public int totalscore=0;
    int t=0,f=0,var;
    private int value,Limit,Max;
    Random random=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //initialize  all views
        time =(TextView)findViewById(R.id.timer);
        score=(TextView)findViewById(R.id.score);
        q1 =(TextView)findViewById(R.id.que);
        q2=(TextView)findViewById(R.id.que2);
        ans=(TextView)findViewById(R.id.result);
        right=(ImageButton)findViewById(R.id.btnCorrect);
        wrong=(ImageButton)findViewById(R.id.btnIncorrect);
        this.value=getIntent().getIntExtra("value",0);
        this.Limit=getIntent().getIntExtra("max",0);
        SetDuration();
        timer();
        score.setText(totalscore+"");
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                varifyresult(true);
            }

        });
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                varifyresult(false);
            }

        });
       generateQue();

    }


    // set timelimit according to selected level
    public void SetDuration()
    {
        switch (Limit)
        {
            case 1:
                this.timeleft=60;
                //Max =10;
                break;
            case 2:
                this.timeleft=90;
               // Max =100;
                break;
            case 3:
                this.timeleft=120;
               // Max=1000;
                break;
            case 0:
                this.timeleft=10;
                break;

        }
    }


    public void varifyresult(boolean ans1) {
        if(ans1==isanscorrect)
        {
            t++;
            totalscore=totalscore+5;
            score.setText("" + totalscore);
        }
        else
        {
            f++;
            timeleft = timeleft-5;
            time.setText("  " +timeleft);
            Vibrator vibrator =(Vibrator)this.getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        generateQue();
    }





//generate questions according to math operation type and level
    public void generateQue() {
        isanscorrect=true;

        switch (Limit)
        {
            case 1:
                Max =10;
                break;
            case 2:
                Max =100;
                break;
            case 3:
                Max=1000;
                break;
         }
        switch (value) {
            case 1:
                add();
                break;
            case 2:
                sub();
                break;
            case 3:
                mul();
                break;
            case 4:
                div();
                break;
        }
    }






    private void mul() {
        int n1,n2,answer;
        int Lim;
        if(Max==10)
        {
            Lim=5;
        }
        else if(Max==100)
        {
            Lim=20;

        }
        else
        {
            Lim=40;
        }
        n1=random.nextInt(Lim);
        n2=random.nextInt(Lim);
        answer=n1*n2;
        while(answer==0)
        {
            n1=random.nextInt(Lim);
            n2=random.nextInt(Lim);
            answer=n1*n2;
        }
        while (Lim==20 && n2<9 && n1<9)
        {
            n1=random.nextInt(Lim);
            n2=random.nextInt(Lim);
            answer=n1*n2;
        }
        while(Lim==40 && n1<15&& n2<15)
        {
            n1=random.nextInt(Lim);
            n2=random.nextInt(Lim);
            answer=n1*n2;
        }

        float f=random.nextFloat();
        if(f >0.5f)
        {
            answer = random.nextInt((Lim * Lim));
            if(Lim==20) {
                do {
                    answer = random.nextInt((Lim * Lim));
                } while (answer < 80);
            }else if(Lim==40)
            {
                do {
                    answer = random.nextInt((Lim * Lim));
                } while (answer <180 );
            }
            if(answer==n1*n2)
            {
                isanscorrect=true;
            }
            else {
                isanscorrect = false;
            }
        }
        q1.setText(n1+"*");
        q2.setText(n2+"=");
        ans.setText(String.valueOf(answer));
    }





    private void div() {
       int n1,n2;
        int answer;
        int rem,div;
        do {
            do
            {
                n1 = random.nextInt(Max*2);
                n2 = random.nextInt(Max*2);
            }while (n2==0 || n2 > (n1/2));

             div=n1/n2;
            rem=n1-(div*n2);
            answer = n1 / n2 -(rem);
        }while(rem!=0);

            float f = random.nextFloat();
            if (f > 0.5f) {
                answer = random.nextInt(answer);
                isanscorrect = false;
                if(answer==n1/n2-(rem))
                {
                    isanscorrect=true;
                }
            }


        q1.setText(n1 + "/");
        q2.setText(n2 + "=");
        ans.setText(String.valueOf(answer));
    }





    private void sub() {
        int n1,n2,answer;

        if(Max==10) {
            do {
                n1 = random.nextInt(Max);
                n2 = random.nextInt(Max);
                answer = n1 - n2;
            } while (answer < 0);
        }
        else {
            n1 = random.nextInt(Max);
            n2 = random.nextInt(Max);
            answer = n1 - n2;
        }

       float f=random.nextFloat();

        if(f >0.5f)
        {
            answer =random.nextInt(Max);
            isanscorrect=false;
            if(answer==n1-n2)
            {
                isanscorrect=true;
            }
        }

        q1.setText(n1+"-");
        q2.setText(n2+"=");
        ans.setText(String.valueOf(answer));
    }




    private void add() {
        int n1,n2,answer;
        n1=random.nextInt(Max);
        n2=random.nextInt(Max);
         answer=n1+n2;
        float f=random.nextFloat();

        if(f >0.5f)
        {
            answer =random.nextInt(Max*2);
            isanscorrect=false;
            if(answer==n1+n2)
            {
                isanscorrect=true;
            }
        }

        q1.setText(n1+"+");
        q2.setText(n2+"=");
        ans.setText(String.valueOf(answer));
    }

// handle the timer in seconds
    private void timer() {
        final Handler handler= new Handler();
        handler.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void run() {
                time.setText("  " + timeleft);
                timeleft--;
                if(timeleft<40)
                {
                    time.setTextColor(getColor(R.color.YellowDark));
                }
                if(timeleft<15)
                {
                    time.setTextColor(getColor(R.color.Red));
                }
                if(timeleft<0)
                {
                    stoptimer=true;
                    // pass the values to score activity via intent
                    Intent intent= new Intent(GameActivity.this,Score.class);
                    intent.putExtra("totalscore",totalscore);
                    intent.putExtra("true",t);
                    intent.putExtra("false",f);
                    intent.putExtra("type",value);
                    intent.putExtra("level",Limit);
                    PlayBuzzer();
                    startActivity(intent);

                }
                if(stoptimer==false)
                {
                    handler.postDelayed(this,1000);
                }
            }
        });

    }
    //play buzzer when time is over
    public void PlayBuzzer(){
         MediaPlayer tune= MediaPlayer.create(this ,R.raw.tune);
        tune.start();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        stoptimer=false;
        timer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stoptimer=true;
        if(timeleft==0)
        {
            finish();
        }
    }
}
