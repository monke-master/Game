package com.example.spacewars;

import android.util.DisplayMetrics;

public class Bullet {
    float x, y, r;
    boolean up, destroyed;
    public Bullet(float x, float y, float r, boolean up){
        this.x = x;
        this.y = y;
        this.r = r;
        this.up = up;
        Move();
    }
    public void Move() {
       if (up)
           y -= 30;
       else
           y += 30;
       if (y <= 0)
           destroyed = true;
    }

}
