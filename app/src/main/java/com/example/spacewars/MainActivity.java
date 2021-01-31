package com.example.spacewars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    float downX, downY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        int MIN_DISTANCE = dm.widthPixels / 8;
        game.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Game g = (Game) v;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        downX = event.getX();
                        downY = event.getY();
                        g.player.Fire();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        float upX = event.getX();
                        float upY = event.getY();
                        float deltaX = downX - upX;
                        float deltaY = downY - upY;
                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            if (deltaX < 0) {
                                g.tx = dm.widthPixels - g.rs;
                                g.player.target = true;
                                return true;
                            }
                            if (deltaX > 0) {
                                g.tx = g.rs;
                                g.player.target = true;
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        });
        setContentView(game);
    }
}