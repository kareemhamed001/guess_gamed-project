package com.example.guesstheanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private  String[] QUESTIONS ;
    private static final boolean[] ANSWERS = {false, true, true, false, true, false, false, false, false, true, true, false, true};
    private  String[] ANSWERS_DETAILS ;
    private TextView textView;

    private String mQusetions,mAnsuerDetails;
    private boolean mAnswer;
    private Random random=new Random() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.QuestoinsView);
        QUESTIONS=getResources().getStringArray(R.array.questions);
        ANSWERS_DETAILS=getResources().getStringArray(R.array.answers);

        textView.setText(QUESTIONS[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuChangeLang){
            showLanguageDialoge();
            return true;
        }else {
        return super.onOptionsItemSelected(item);
        }
    }
    private void showLanguageDialoge(){
        AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle(R.string.menuName).setItems(R.array.languages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String language = "ar";
                switch (which) {
                    case 0:
                        language = "ar";
                        break;
                    case 1:
                        language = "en";
                        break;
                    case 2:
                        language = "fr";
                        break;
                }

                LocaleHelper.setLocale(MainActivity.this, language);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        }).create();
        alertDialog.show();


    }

    public void showQustions(){
        int mQustionsIndex=random.nextInt(QUESTIONS.length);
        mQusetions=QUESTIONS[mQustionsIndex];
        mAnsuerDetails=ANSWERS_DETAILS[mQustionsIndex];
        mAnswer=ANSWERS[mQustionsIndex];
        textView.setText(mQusetions);
    }
    public void changeQuestions(View view){
        showQustions();
    }
    public void onTrueClick(View view){
        if (mAnswer==true){
            Toast.makeText(this,R.string.right_answer,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.falseanswer,Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(MainActivity.this,AnswerActivity.class);
            intent.putExtra("trueAnswer",mAnsuerDetails);
            startActivity(intent);
        }
    }
    public void onfalseClick(View view){
        if (mAnswer==false){
            Toast.makeText(this,R.string.right_answer,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.falseanswer,Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(MainActivity.this,AnswerActivity.class);
            intent.putExtra("trueAnswer",mAnsuerDetails);
            startActivity(intent);
        }
    }
    public void onShare(View view){
        Intent intent=new Intent(MainActivity.this,ShareAnswer.class);
        intent.putExtra("qustion text extra",mQusetions);
        startActivity(intent);
    }
}