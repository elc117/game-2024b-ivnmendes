package com.paradigmas.game.entities;

public class Enemy extends Character{
    public Enemy(String name, String spritePath) {
        super(name, spritePath);
        life = 200;
    }

    @Override
    public void causeDamage() {

    }

    @Override
    public void causeDamage(int consecutiveHits) {
        life -= consecutiveHits * 20;
        if (life < 0) {
            life = 0;
        }
    }
}
