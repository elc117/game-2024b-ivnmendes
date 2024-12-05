package com.paradigmas.game.entities;

import lombok.Getter;

@Getter
public class Paradigmer extends Character {
    private int consecutiveHits;
    public Paradigmer(String name, String spritePath) {
        super(name, spritePath);
        consecutiveHits = 0;
        life = 100;

        super.sprite.setSize(7,7);
    }

    @Override
    public void causeDamage() {
        life -= 20;
        if (life < 0) {
            life = 0;
        }
    }

    @Override
    public void causeDamage(int consecutiveHits) {

    }

    public void hit() {
        consecutiveHits++;
    }
}
