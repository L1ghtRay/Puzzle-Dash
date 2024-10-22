package io.github.puzzle_dash;

import com.badlogic.gdx.Game;

public class PuzzleDashGame extends Game {
    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }
}
