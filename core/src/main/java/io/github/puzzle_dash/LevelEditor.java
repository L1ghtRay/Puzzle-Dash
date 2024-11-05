package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelEditor implements Screen {
    SpriteBatch batch;
    private Texture lvl_editortexture;
    private Sprite lvl_editorSprite;
    LevelEditor(SpriteBatch batch) {
        this.batch=batch;
        lvl_editortexture = new Texture("credits.png");
        lvl_editorSprite = new Sprite(lvl_editortexture);
        lvl_editorSprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new LevelsScreen(batch));


        }
        batch.begin();
        lvl_editorSprite.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int i, int i1) {

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

    @Override
    public void dispose() {
        lvl_editortexture.dispose();

    }
}
