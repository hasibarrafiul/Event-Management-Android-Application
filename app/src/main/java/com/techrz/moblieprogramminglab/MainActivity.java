package com.techrz.moblieprogramminglab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("@mainActivity OnCreate");

        setContentView(R.layout.activity_main);

        Button btnCreateNew = findViewById(R.id.createNew);
        btnCreateNew.setOnClickListener(v->createNew());

        Button history = findViewById(R.id.history);
        history.setOnClickListener(v->history());

        findViewById(R.id.exit).setOnClickListener(v->finish());

    }
    void createNew(){
        Intent i = new Intent(this, EventInformation.class);
        startActivity(i);
    }
    void history(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("@mainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("@mainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("@mainActivity onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("@mainActivity onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("@mainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("@mainActivity onDestroy");
    }
}