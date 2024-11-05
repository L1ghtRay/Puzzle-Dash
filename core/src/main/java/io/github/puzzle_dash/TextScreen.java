package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TextScreen  {

     SpriteBatch batch;
    BitmapFont font;

    public TextScreen(SpriteBatch batch) {
        // Initialize the SpriteBatch and load the custom BitmapFont
        this.batch=batch;
        font = new BitmapFont(Gdx.files.internal("press.fnt"), Gdx.files.internal("press.png"), false);
    }

    public void render() {
        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);  // Black background

        // Begin drawing
        batch.begin();

        // Set the color (optional)
        font.setColor(1, 1, 1, 1); // White color

        // Draw text
        font.draw(batch, "GAME OVER!", 800, 1000);
        font.draw(batch, "Credits", 1600, 100);
        font.draw(batch, "Music", 800, 700);
        font.draw(batch, "Directed by", 800, 500);// Position (x, y)

        // End drawing
        batch.end();
    }

    public void dispose() {
        // Dispose resources
        batch.dispose();
        font.dispose();
    }
}

