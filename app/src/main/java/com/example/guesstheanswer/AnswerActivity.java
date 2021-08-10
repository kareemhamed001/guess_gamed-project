package com.example.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private String trueAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        textView=findViewById(R.id.answer);
        button=findViewById(R.id.button);
        trueAnswer=getIntent().getStringExtra("trueAnswer");
        if (trueAnswer!=null){
            textView.setText(trueAnswer);
        }
    }
    public void backbt(View view){
        finish();
    }
}