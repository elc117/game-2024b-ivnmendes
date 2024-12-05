package com.paradigmas.game.screens;

import com.paradigmas.game.Main;
import com.paradigmas.game.utils.ScreenType;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class ScreenManager {
    private final Main game;
    private final HashMap<ScreenType, SuperScreen> screens;

    public ScreenManager(Main game) {
        this.game = game;
        screens = new HashMap<>();
    }

    public void addScreen(ScreenType screenType, SuperScreen screen) {
        screens.put(screenType, screen);
    }

    public void deleteScreen(ScreenType screenType) {
        screens.remove(screenType);
    }

    public void showScreen(ScreenType screenToShow) {
        SuperScreen screen = screens.get(screenToShow);
        game.setScreen(screen);
    }
}