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
        font.getData().setScale(1.5f);
    }

    public void render() {
        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);  // Black background

        // Begin drawing
        batch.begin();

        // Set the color (optional)
        font.setColor(1, 1, 1, 1); // White color

        // Draw text

        font.draw(batch, "CREDITS", 800, 1000);
        font.draw(batch, "MUSIC", 800, 900);
        font.draw(batch, "PIXABAY", 800, 800);
        font.draw(batch, "TOOLS", 800, 700);
        font.draw(batch, "LIBGDX", 800, 600);
        font.draw(batch, "TILED", 800, 500);
        font.draw(batch, "PHOTOSHOP", 800, 400);
        font.draw(batch, "IDE", 800, 300);
        font.draw(batch, "INTELJ", 800, 200);


        // End drawing
        batch.end();
    }

    public void dispose() {
        // Dispose resources
        batch.dispose();
        font.dispose();
    }
}
