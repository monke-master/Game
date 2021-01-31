package com.example.spacewars;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


public class Ship {
    float xc, yc, rcp, rs;
    boolean target, fire;
    int cpColor, sColor;
    int score = 0;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public Ship(float xc, float yc, float rs, float rcp, int s, int cp) {
        this.xc = xc;
        this.yc = yc;
        this.rcp = rcp;
        this.rs = rs;
        this.cpColor = cp;
        this.sColor = s;
    }

    public void Draw(Canvas canvas) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(sColor);
        canvas.drawCircle(xc, yc, rs, p);
        p.setColor(cpColor);
        canvas.drawCircle(xc, yc, rcp, p);
        for (Bullet b: bullets) {
            if (!b.destroyed) {
                b.Move();
                p.setColor(Color.WHITE);
                canvas.drawCircle(b.x, b.y, b.r, p);
            }
        }
    }

    public void Move(float tx) {
        if (Math.abs(tx - xc) < 10)
            target = false;
        if (target)
            if (tx > xc)
                xc += 20;
            else
                xc -= 20;
    }
    public void Fire() {
        Bullet bullet = new Bullet(xc, yc, rcp / 10, true);
        bullets.add(bullet);
    }


}
