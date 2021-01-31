package com.example.spacewars;

public class Enemy extends Ship {
    boolean killed = false;
    float speed;
    public Enemy(float xc, float yc, float rs, float rcp, int s, int cp, float speed) {
        super(xc, yc, rs, rcp, s, cp);
        this.speed = speed;
    }

    public void Move(){
        yc += speed;
    }
}
