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
        font = new BitmapFont(Gdx.files.internal("custom_font.fnt"), Gdx.files.internal("custom_font.png"), false);
    }

    public void render() {
        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);  // Black background

        // Begin drawing
        batch.begin();

        // Set the color (optional)
        font.setColor(1, 1, 1, 1); // White color

        // Draw text
        font.draw(batch, "Welcome to Puzzle Dash!", 600, 800);
        font.draw(batch, "Welcome to Puzzle Dash!", 700, 600);
        font.draw(batch, "Welcome to Puzzle Dash!", 800, 400);
        font.draw(batch, "Welcome to Puzzle Dash!", 600, 200);// Position (x, y)

        // End drawing
        batch.end();
    }

    public void dispose() {
        // Dispose resources
        batch.dispose();
        font.dispose();
    }
}

