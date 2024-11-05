package io.github.puzzle_dash;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


    public class    SettingsScreen implements Screen {

        SpriteBatch batch;
        Texture settingsTexture;
        Sprite settingsSprite;
        private Stage stage;
        private  Skin skin;


        public SettingsScreen(SpriteBatch batch) {
            this.batch = batch;

            // Load background image
            settingsTexture = new Texture("settings.png");
            settingsSprite = new Sprite(settingsTexture);
            settingsSprite.setPosition(0, 0);
            settingsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            // Initialize TextScreen with the batch to render text

            stage = new Stage(new ScreenViewport());
            Gdx.input.setInputProcessor(stage);



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
            settingsSprite.draw(batch);

            // Draw text on top of the background


            // End drawing
            batch.end();

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
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
            settingsTexture.dispose();
            stage.dispose();

        }
    }

}
