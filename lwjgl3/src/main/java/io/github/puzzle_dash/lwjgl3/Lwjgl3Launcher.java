package io.github.puzzle_dash.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.puzzle_dash.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Puzzle Dash");
        config.setWindowedMode(1600, 900);
        new Lwjgl3Application(new Main(), config);
        config.useVsync(true);
        config.setWindowIcon("puzzle_dash128.png", "puzzle_dash64.png", "puzzle_dash32.png", "puzzle_dash16.png");

        //if (StartupHelper.startNewJvmIfRequired()) return;
        //createApplication();
    }

    /* private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Puzzle Dash");




        config.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);

        config.setWindowedMode(640, 480);

        config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return config;
    }*/
}

