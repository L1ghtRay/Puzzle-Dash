package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class CreditsScreen implements Screen {

    SpriteBatch batch;
    Texture creditsTexture;
    Sprite creditsSprite;
    TextScreen text;

    public CreditsScreen(SpriteBatch batch) {
        this.batch = batch;

        // Load background image
        creditsTexture = new Texture("credits-bg.png");
        creditsSprite = new Sprite(creditsTexture);
        creditsSprite.setPosition(0, 0);
        creditsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Initialize TextScreen with the batch to render text
        text = new TextScreen(batch);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new MainMenuScreen(game));

        }
        // Begin drawing
        batch.begin();

        // Draw the background sprite first
        creditsSprite.draw(batch);

        // Draw text on top of the background


        // End drawing
        batch.end();
        text.render();
    }

    @Override
    public void show() { }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        creditsTexture.dispose();
        text.dispose();
    }
}
