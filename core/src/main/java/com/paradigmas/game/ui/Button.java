package com.paradigmas.game.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.paradigmas.game.Main;
import lombok.Getter;

public class Button {
    private final Stage stage;
    @Getter private final TextButton button;
    private final Skin skin;
    private final TextureAtlas buttonAtlas;

    public Button(Main game, String buttonText, float posX, float posY, ButtonAction action) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        buttonAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
        skin.addRegions(buttonAtlas);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.up = skin.getDrawable("default-rect");  // Padrão da skin
        textButtonStyle.down = skin.getDrawable("default-rect");  // Padrão da skin
        textButtonStyle.checked = skin.getDrawable("default-rect");  // Padrão da skin

        button = new TextButton(buttonText, textButtonStyle);

        button.setPosition(posX, posY);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                action.execute();
            }
        });

        stage.addActor(button);
    }

    public void addClickListener(ChangeListener listener) {
        button.addListener(listener);
    }

    public void render() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void dispose() {
        skin.dispose();
        buttonAtlas.dispose();
    }
}
