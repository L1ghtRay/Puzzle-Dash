package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {

    SpriteBatch batch;
    private final Texture settingsTexture;
    private final Sprite settingsSprite;
    private final Stage stage;
    private final Slider volumeSlider;
    private final BitmapFont font;
    private final BitmapFont customFont;
    private final int[] leaderboardScores = {100, 80, 60, 40}; // Default leaderboard scores

    // Constructor with score parameter
    public SettingsScreen(SpriteBatch batch, int score) {
        this.batch = batch;
        // To store the current score from Level1

        // Background image
        settingsTexture = new Texture("credits.png");
        settingsSprite = new Sprite(settingsTexture);
        settingsSprite.setPosition(0, 0);
        settingsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Initialize the stage for UI elements
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Set up the volume slider
        volumeSlider = new Slider(0f, 1f, 0.1f, false, new Slider.SliderStyle());
        volumeSlider.setValue(0.5f);  // Default volume level
        volumeSlider.setPosition(100, 800);
        volumeSlider.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                float volume = volumeSlider.getValue();
                System.out.println("Volume set to: " + volume);
                // Set game volume here
            }
        });
        stage.addActor(volumeSlider);

        // Set up the font for the leaderboard
        font = new BitmapFont();
        customFont = new BitmapFont(Gdx.files.internal("press.fnt"));
        customFont.getData().setScale(1.5f); // Load custom font here

        // Update leaderboard scores
        updateLeaderboardScores(score);
    }

    private void updateLeaderboardScores(int score) {
        // Example logic: replace the lowest score if the new score is higher
        for (int i = 0; i < leaderboardScores.length; i++) {
            if (score > leaderboardScores[i]) {
                leaderboardScores[i] = score; // Update the leaderboard score
                break; // Exit loop after updating
            }
        }
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Check for escape key to go back to main menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new MainMenuScreen(game));
        }

        // Begin drawing
        batch.begin();
        settingsSprite.draw(batch);

        // Display leaderboard
        customFont.draw(batch, "LEADERBOARD", 800, 800);
        for (int i = 0; i < leaderboardScores.length; i++) {
            customFont.draw(batch, (i + 1) + ". " + leaderboardScores[i], 900, 650 - i * 100);
        }

        // Draw text on top of the background
        batch.end();

        // Update and draw the stage
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
        font.dispose();
        customFont.dispose();
    }
}
