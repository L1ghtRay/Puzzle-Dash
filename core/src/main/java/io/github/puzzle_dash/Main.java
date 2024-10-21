package io.github.puzzle_dash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Main extends ApplicationAdapter {
    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite sprite;
    Stage stage;   // Add a stage for the button
    Texture buttonTexture; // Texture for the button
    ImageButton button; // Use ImageButton instead of TextButton

    @Override
    public void create() {
        // Create camera and sprite as before
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("main-menu.png"));
        sprite.setPosition(0, 0);
        sprite.setSize(640, 480);

        // Set up the stage and input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // Direct input events to the stage

        // Load the button texture
        buttonTexture = new Texture("play-button.png"); // Replace with your button image file
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);
        button = new ImageButton(buttonDrawable);
        button.setSize(500, 300);
        button.setPosition(55, 140);  // Position at bottom center

        // Add click listener to the button
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Button", "Start Game Button Clicked!");
                // Here you can add code to start the game or transition to another screen
            }
        });

        // Add the button to the stage
        stage.addActor(button);
    }

    @Override
    public void render() {
        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Draw the sprite (background)
        batch.begin();
        sprite.draw(batch);
        batch.end();

        // Draw the stage (button)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        buttonTexture.dispose();  // Dispose of the button texture when done
    }
}
