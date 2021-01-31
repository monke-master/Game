package com.example.spacewars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_);
    }

    public void startGame(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}