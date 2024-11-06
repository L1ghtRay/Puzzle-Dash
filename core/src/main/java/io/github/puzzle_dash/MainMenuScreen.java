package io.github.puzzle_dash;
//    Import Statements
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
//    Class
public class MainMenuScreen implements Screen
{
//    Instance Variables
    final PuzzleDashGame game;
    SpriteBatch batch;

    private final Sprite mainmenusprite;
    private final Texture mainmenuTexture;
//    Stage for the button
    private final Stage stage;
//    Buttons
    private final Texture playbuttonTexture;
    private final Texture levelbuttonTexture;
    private final Texture creditsbuttonTexture;
    private final Texture exitbuttonTexture;
    //    Music
    private Music bg;

//    Constructor
    MainMenuScreen(final PuzzleDashGame game) {
        this.game = game;
//        Main Screen
        mainmenuTexture = new Texture("main-menu.png");
        batch = new SpriteBatch();
        mainmenusprite = new Sprite(mainmenuTexture);
        mainmenusprite.setPosition(0, 0);
        mainmenusprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        Stage
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Play button texture
        playbuttonTexture = new Texture("play-button.png");
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(playbuttonTexture);
        ImageButton button1 = new ImageButton(buttonDrawable1);
        button1.setSize(149, 82);
        button1.setPosition(885, 489);

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Game Button Clicked!");
                bg.stop();
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new Level3(batch));
                dispose();
            }
        });

        // Level button texture
        levelbuttonTexture = new Texture("level-button.png");
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(levelbuttonTexture);
        ImageButton button2 = new ImageButton(buttonDrawable2);
        button2.setSize(209, 84);
        button2.setPosition(858, 393);

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Level Button Clicked!");
                bg.stop();
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new LevelsScreen(batch));
                dispose();
            }
        });

        // Credits button texture
        creditsbuttonTexture = new Texture("credits-button.png");
        TextureRegionDrawable buttonDrawable3 = new TextureRegionDrawable(creditsbuttonTexture);
        ImageButton button3 = new ImageButton(buttonDrawable3);
        button3.setSize(277, 85);
        button3.setPosition(819, 300);

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Open Credits Button Clicked!");
                bg.stop();
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new CreditsScreen(batch));
                dispose();
            }


        });

        // Exit button texture
        exitbuttonTexture = new Texture("exit-button.png");
        TextureRegionDrawable buttonDrawable4 = new TextureRegionDrawable(exitbuttonTexture);
        ImageButton button4 = new ImageButton(buttonDrawable4);
        button4.setSize(181, 82);
        button4.setPosition(865, 209);

        button4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                System.out.println("Exit Game Button Clicked!");
                Gdx.app.exit(); // Exit the application
            }
        });

//        Setting Icon
        Texture setbuttonTexture = new Texture("settings-icon.png");
        TextureRegionDrawable buttonDrawable5 = new TextureRegionDrawable(setbuttonTexture);
        ImageButton button5= new ImageButton(buttonDrawable5);
        button5.setSize(88, 88);
        button5.setPosition(30, 970);

        button5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Settings Button Clicked!");
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new SettingsScreen(batch,1));
                dispose();
            }


        });

        // Add the buttons to the stage
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
        stage.addActor(button5);
    }


    @Override
    public void render(float delta) {

        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            Gdx.app.exit();  // Exit the game
            dispose();
        }

//       Draw the sprite (background)
        batch.begin();
        mainmenusprite.draw(batch);
        batch.end();

//      Draw the stage (buttons)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {
        //Load the music file
        bg = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        bg.setLooping(true); // Set to loop if needed
        bg.setVolume(0.2f); // Set volume (0.0 to 1.0)
        bg.play(); // Start playing the music

    }

    @Override
    public void resize(int width, int height) {
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
//      Dispose of textures and other resources when done
        stage.dispose();
        mainmenuTexture.dispose();
        playbuttonTexture.dispose();
        levelbuttonTexture.dispose();
        creditsbuttonTexture.dispose();
        exitbuttonTexture.dispose();
        bg.dispose();

    }
}

