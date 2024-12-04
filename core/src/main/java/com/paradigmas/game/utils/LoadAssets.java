package com.paradigmas.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.Color;

import java.awt.*;

public abstract class LoadAssets {
    public static Texture loadTexture(String path) {
        try {
            return new Texture(path);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar textura: " + path, e);
        }
    }

    public static Music loadMusic(String path) {
        try {
            Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
            music.setLooping(true);
            music.setVolume(0.5F);
            return music;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar música: " + path, e);
        }
    }

    public static Skin loadSkin(String path) {
        try {
            return new Skin(Gdx.files.internal(path));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar skin: " + path, e);
        }
    }

    public static TextureAtlas loadAtlas(String path) {
        try {
            return new TextureAtlas(Gdx.files.internal(path));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar atlas: " + path, e);
        }
    }

    public static BitmapFont loadFont(String path, int fontSize){
        try {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

            // Defina o tamanho da fonte
            parameter.size = fontSize;

            BitmapFont font = generator.generateFont(parameter);
            generator.dispose();

            return font;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar fonte: " + path, e);
        }
    }
}