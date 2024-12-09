package com.paradigmas.game.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.GwtGraphics;
import com.badlogic.gdx.graphics.g2d.freetype.gwt.inject.OnCompletion;
import com.google.gwt.core.client.ScriptInjector;
import com.badlogic.gdx.graphics.g2d.freetype.gwt.*;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.paradigmas.game.Main;

import static com.ibm.icu.impl.number.AffixPatternProvider.Flags.PADDING;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
            // Resizable application, uses available space in browser with no padding:
//            GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
//            cfg.padVertical = 0;
//            cfg.padHorizontal = 0;
//            cfg.fullscreenOrientation = GwtGraphics.OrientationLockType.LANDSCAPE;
//            return cfg;
            // If you want a fixed size application, comment out the above resizable section,
            // and uncomment below:
            return new GwtApplicationConfiguration(1024, 760);
        }

        @Override
        public ApplicationListener createApplicationListener () {
            return new Main();
        }

        @Override
        public void onModuleLoad () {
            FreetypeInjector.inject(new OnCompletion() {
                public void run () {
                    GwtLauncher.super.onModuleLoad();
                }
            });
        }

    class ResizeListener implements ResizeHandler {
        @Override
        public void onResize(ResizeEvent event) {
            if (Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            } else {
                int width = event.getWidth() - PADDING;
                int height = event.getHeight() - PADDING;
                getRootPanel().setWidth("" + width + "px");
                getRootPanel().setHeight("" + height + "px");
                getApplicationListener().resize(width, height);
                Gdx.graphics.setWindowedMode(width, height);
            }
        }
    }

}
