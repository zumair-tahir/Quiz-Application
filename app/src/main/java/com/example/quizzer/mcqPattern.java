package com.example.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class mcqPattern extends AppCompatActivity {

    String[] mcq;
    Button next,cancel;
    RadioGroup rg;
    RadioButton rA,rB,rC,rD;
    TextView question,timer;
    int Ans;
    int mcqNo;
    int score;
    int counter;
    CountDownTimer CDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_pattern);

        //bundle work
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mcqNo = bundle.getInt("mcqNO");
            score = bundle.getInt("score");
        }

        mcq=getResources().getStringArray(R.array.mcq);
        question=(TextView)findViewById(R.id.textView2);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        rA=(RadioButton)findViewById(R.id.radioButton);
        rB=(RadioButton)findViewById(R.id.radioButton2);
        rC=(RadioButton)findViewById(R.id.radioButton3);
        rD=(RadioButton)findViewById(R.id.radioButton4);
        next=(Button)findViewById(R.id.button4);
        cancel=(Button)findViewById(R.id.button5);
        int i=mcqNo;
        question.setText(mcq[i]);
        rA.setText(mcq[i+1]);
        rB.setText(mcq[i+2]);
        rC.setText(mcq[i+3]);
        rD.setText(mcq[i+4]);
        if(mcq[i+5].equals("A"))
            Ans=rA.getId();
        if(mcq[i+5].equals("B"))
            Ans=rB.getId();
        if(mcq[i+5].equals("C"))
            Ans=rC.getId();
        if(mcq[i+5].equals("D"))
            Ans=rD.getId();


        timer=(TextView)findViewById(R.id.textView4);
        CDT = new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                timer.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                next.performClick();
            }
        }.start();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CDT.cancel();
                int checked = rg.getCheckedRadioButtonId();
                if(Ans==checked)
                {
                    score=score+1;
                }
                if(mcq.length>(mcqNo+6))
                {
                    Bundle bundle = new Bundle();
                    bundle.putInt("mcqNO",mcqNo+6);
                    bundle.putInt("score",score);
                    Intent intent = new Intent(mcqPattern.this, mcqPattern.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    cancel.performClick();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CDT.cancel();
                Bundle bundle = new Bundle();
                bundle.putInt("score",score);
                Intent intent = new Intent(mcqPattern.this, Result.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
}