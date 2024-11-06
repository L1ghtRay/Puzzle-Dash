package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//        Class
public class LevelsScreen implements Screen {
    SpriteBatch batch1;
    private Music bg;
    Sprite levelmenusprite;
    Texture levelmenuTexture;
    Stage stage;
    private final Texture lvl_editbuttonTexture;
    private final Texture backbuttonTexture;
//        Constructor
    LevelsScreen(SpriteBatch batch) {
        batch1 = batch;
//        LevelMenu Screen
        levelmenuTexture=new Texture("level-menu.png");
        levelmenusprite = new Sprite(levelmenuTexture);
        levelmenusprite.setPosition(0, 0);
        levelmenusprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        Stage
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
//        Level 1 preview
        Texture lvl_1Texture = new Texture("level-preview-1.png");
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(lvl_1Texture);
        ImageButton button1 = new ImageButton(buttonDrawable1);
        button1.setSize(424, 308);
        button1.setPosition(194, 433);

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level1 Clicked!");
                bg.stop();
                PuzzleDashGame game1 = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game1.setScreen(new Level1(batch1));
                dispose();

            }
        });
//        Level 2
        Texture lvl_2Texture = new Texture("level-preview-2.png");
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(lvl_2Texture);
        ImageButton button2 = new ImageButton(buttonDrawable2);
        button2.setSize(424, 308);
        button2.setPosition(750, 433);

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level2 Clicked!");
                bg.stop();
                PuzzleDashGame game1 = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game1.setScreen(new Level2(batch1));
                dispose();

            }
        });
//        Level 3
        Texture lvl_3Texture = new Texture("level-preview-3.png");
        TextureRegionDrawable buttonDrawable3 = new TextureRegionDrawable(lvl_3Texture);
        ImageButton button3 = new ImageButton(buttonDrawable3);
        button3.setSize(424, 308);
        button3.setPosition(1312, 433);

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level2 Clicked!");
                bg.stop();
                PuzzleDashGame game1 = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game1.setScreen(new Level3(batch1));
                dispose();

            }
        });

//        Level Editor Button
        lvl_editbuttonTexture = new Texture("level-editor-button.png");
        TextureRegionDrawable buttonDrawable5 = new TextureRegionDrawable(lvl_editbuttonTexture);
        ImageButton button5 = new ImageButton(buttonDrawable5);
        button5.setSize(330, 83);
        button5.setPosition(795, 222);

        button5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level Editor Button Clicked!");
                bg.stop();
                PuzzleDashGame game1 = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game1.setScreen(new LevelEditor(batch1));
                dispose();

            }
        });

//        Back Button
        backbuttonTexture = new Texture("back-button.png");
        TextureRegionDrawable buttonDrawable6 = new TextureRegionDrawable(backbuttonTexture);
        ImageButton button6 = new ImageButton(buttonDrawable6);
        button6.setSize(139, 83);
        button6.setPosition(890, 116);

        button6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(" Back Button Clicked!");
                bg.stop();
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button5);
        stage.addActor(button6);
    }

    @Override
    public void show() {

//        Music
        bg = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        bg.setLooping(true); // Set to loop if needed
        bg.setVolume(0.2f); // Set volume (0.0 to 1.0)
        bg.play(); // Start playing the music


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        TO Go Back
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new MainMenuScreen(game));

        }

        batch1.begin();
        levelmenusprite.draw(batch1);
        batch1.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        levelmenuTexture.dispose();
        backbuttonTexture.dispose();
        lvl_editbuttonTexture.dispose();
        bg.dispose();
    }
}
