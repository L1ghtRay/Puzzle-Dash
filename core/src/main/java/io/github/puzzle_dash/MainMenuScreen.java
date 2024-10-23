package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    final PuzzleDashGame game;

    SpriteBatch batch;
    // Sprites
    Sprite mainmenusprite;
    Sprite playbuttonsprite;
    Sprite levelbuttonsprite;
    Sprite creditsbuttonsprite;
    Sprite exitbuttonsprite;
    Texture mainmenuTexture;
    Stage stage; // Stage for the button
    // Buttons
    Texture playbuttonTexture;
    ImageButton button1;
    Texture levelbuttonTexture;
    ImageButton button2;
    Texture creditsbuttonTexture;
    ImageButton button3;
    Texture exitbuttonTexture;
    ImageButton button4;

    MainMenuScreen(final PuzzleDashGame game) {
        this.game = game;

        mainmenuTexture = new Texture("main-menu.png");
        batch = new SpriteBatch();

        mainmenusprite = new Sprite(mainmenuTexture);
        mainmenusprite.setPosition(0, 0);
        mainmenusprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set up the stage and input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage); // Direct input events to the stage

        // Play button texture
        playbuttonTexture = new Texture("play-button.png");
        playbuttonsprite = new Sprite(playbuttonTexture);
        TextureRegion buttonRegion1 = new TextureRegion(playbuttonTexture);
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(buttonRegion1);
        button1 = new ImageButton(buttonDrawable1);
        button1.setSize(180, 81);
        button1.setPosition(708, 404);

        // Add click listener to the play button
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.log("Button", "Start Game Button Clicked!");
                System.out.println("Start Game Button Clicked!");
                // Add code to start the game or transition to another screen
            }
        });

        // Level button texture
        levelbuttonTexture = new Texture("level-button.png");
        levelbuttonsprite = new Sprite(levelbuttonTexture);
        TextureRegion buttonRegion2 = new TextureRegion(levelbuttonTexture);
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(buttonRegion2);
        button2 = new ImageButton(buttonDrawable2);
        button2.setSize(209, 84);
        button2.setPosition(698, 313);

        // Add click listener to the level button
        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.log("Button", "Start Level Button Clicked!");
                System.out.println("Start Level Button Clicked!");
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new LevelsScreen(batch));
                dispose();
            }
        });

        // Credits button texture
        creditsbuttonTexture = new Texture("credits-button.png");
        creditsbuttonsprite = new Sprite(creditsbuttonTexture);
        TextureRegion buttonRegion3 = new TextureRegion(creditsbuttonTexture);
        TextureRegionDrawable buttonDrawable3 = new TextureRegionDrawable(buttonRegion3);
        button3 = new ImageButton(buttonDrawable3);
        button3.setSize(277, 85);
        button3.setPosition(659, 224);

        // Add click listener to the credits button
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.log("Button", "Open Credits Button Clicked!");
                System.out.println("Open Credits Button Clicked!");
                // Add code to start the game or transition to another screen
            }
        });

        // Exit button texture
        exitbuttonTexture = new Texture("exit-button.png");
        exitbuttonsprite = new Sprite(exitbuttonTexture);
        TextureRegion buttonRegion4 = new TextureRegion(exitbuttonTexture);
        TextureRegionDrawable buttonDrawable4 = new TextureRegionDrawable(buttonRegion4);
        button4 = new ImageButton(buttonDrawable4);
        button4.setSize(181, 82);
        button4.setPosition(708, 138);

        // Add click listener to the exit button
        button4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Button", "Exit Game Button Clicked!");
                Gdx.app.exit(); // Exit the application
            }
        });

        // Add the buttons to the stage
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
    }


    @Override
    public void render(float delta) {

        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Draw the sprite (background)
        batch.begin();
        mainmenusprite.draw(batch);

        batch.end();

        // Draw the stage (buttons)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
//        batch.dispose();
        stage.dispose();
        mainmenuTexture.dispose();
        playbuttonTexture.dispose();
        levelbuttonTexture.dispose();
        creditsbuttonTexture.dispose();
        exitbuttonTexture.dispose();
    }
}
