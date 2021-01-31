package com.example.spacewars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Game extends View {
    float rcp, rs, tx, x, y, yce, xce;
    Canvas c;
    Ship player;
    int spawnTime = 10000;
    float speed = 5;
    private Handler spawn = new Handler();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Game(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        y = displaymetrics.heightPixels;
        x = displaymetrics.widthPixels;
        float xc = x / 2;
        float yc = y - y / 5;
        yce = 0;
        rcp = x / 10;
        rs = x / 7;
        player = new Ship(xc, yc, rs, rcp,
                getResources().getColor(R.color.player_ship_color),getResources().getColor(R.color.cockpit_color));
        spawn.removeCallbacks(enemySpawner);
        spawn.postDelayed(enemySpawner, 100);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setSubpixelText(true);
        canvas.drawColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(50);
        p.setColor(Color.WHITE);
        canvas.drawText("Score: " + String.valueOf(player.score), x / 20, y / 30, p);
        player.Draw(canvas);
        player.Move(tx);
        for (Enemy e: enemies) {
            if (!e.killed) {
                e.Draw(canvas);
                e.Move();
            }
            if (e.yc >= y) {
                e.killed = true;
                enemies.remove(e);
                player.score -= 100;
                if (spawnTime > 1000)
                    spawnTime -= 500;
                if (speed < 20)
                    speed += 0.5;
            }
        }
        for (Enemy e: enemies) {
            for (Bullet b: player.bullets) {
                if ((!b.destroyed) && (b.r + e.rs >= Math.sqrt(Math.pow((e.xc - b.x), 2) + Math.pow((e.yc - b.y), 2)))) {
                    e.killed = true;
                    b.destroyed = true;
                    enemies.remove(e);
                    player.score += 100;
                    if (spawnTime > 1000)
                        spawnTime -= 300;
                    if (speed < 20)
                        speed += 0.3;
                }
            }
        }

        invalidate();
    }

    private Runnable enemySpawner = new Runnable() {
        @Override
        public void run() {
            int pos = (int)(Math.random()*2);
            if (pos == 0)
                xce = rs;
            else
                xce = x - rs;
            yce = 2 * rs;
            Enemy enemy = new Enemy(xce, yce, rs, rcp,
                    getResources().getColor(R.color.enemy_ship_color),
                    getResources().getColor(R.color.cockpit_color), speed);
            enemies.add(enemy);
            spawn.postDelayed(this, spawnTime);
        }
    };


}
