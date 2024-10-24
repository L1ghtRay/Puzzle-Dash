package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class CreditsScreen implements Screen {

    SpriteBatch batch;
    Texture creditsTexture;
    Sprite creditsSprite;
    public CreditsScreen(SpriteBatch batch)
    {
       this.batch=batch;
       creditsTexture =new Texture("credits.png");
       creditsSprite =new Sprite(creditsTexture);
       creditsSprite.setPosition(0,0);
       creditsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    @Override
    public void render(float delta) {

        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Draw the sprite (background)
        batch.begin();
        creditsSprite.draw(batch);

        batch.end();

        // Draw the stage (buttons)

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
//        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        // Dispose of textures and other resources when done
        creditsTexture.dispose();
    }
}
