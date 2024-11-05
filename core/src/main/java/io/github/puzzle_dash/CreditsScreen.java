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

public class CreditsScreen implements Screen {

    SpriteBatch batch;
    Texture creditsTexture;
    Sprite creditsSprite;
    TextScreen text;
    private Stage stage;
    private Texture backbuttonTexture;
    public CreditsScreen(SpriteBatch batch) {
        this.batch = batch;

        // Load background image
        creditsTexture = new Texture("credits.png");
        creditsSprite = new Sprite(creditsTexture);
        creditsSprite.setPosition(0, 0);
        creditsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Initialize TextScreen with the batch to render text
        text = new TextScreen(batch);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backbuttonTexture = new Texture("back-button.png"); // Use the correct variable name here
        TextureRegionDrawable backButtonDrawable = new TextureRegionDrawable(backbuttonTexture);
        ImageButton backButton = new ImageButton(backButtonDrawable);

        // Set button position and size
        backButton.setSize(150, 80);
        backButton.setPosition(200, 200); // Adjust the position as needed

        // Add listener for the back button
        backButton.addListener(new ClickListener() {
            @Override    public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to Main Menu Button Clicked!");
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        // Add the back button to the stage
        stage.addActor(backButton);



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
        creditsTexture.dispose();
        text.dispose();
        stage.dispose();
        backbuttonTexture.dispose();
    }
}
