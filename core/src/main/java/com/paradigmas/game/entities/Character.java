package com.paradigmas.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import com.paradigmas.game.utils.LoadAssets;

import lombok.Getter;

@Getter
public abstract class Character {
    protected int life;
    protected String name;
    protected Sprite sprite;

    public Character(String name, String spritePath) {
        this.name = name;
        sprite = LoadAssets.loadSprite(spritePath, 3, 3);
    }

    public abstract void causeDamage();

    public abstract void causeDamage(int consecutiveHits);

}
