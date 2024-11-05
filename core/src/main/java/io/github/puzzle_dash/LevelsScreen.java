package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelsScreen implements Screen {
    SpriteBatch batch1;




    Sprite levelmenusprite;
    Texture levelmenuTexture;
    Stage stage;
    //button sprite
//    Sprite lvl_editbuttonsprite;
//    Sprite backbuttonsprite;
    //buttons
    private final Texture lvl_editbuttonTexture;
    private final Texture backbuttonTexture;

    LevelsScreen(SpriteBatch batch) {
        batch1 = batch;

        levelmenuTexture=new Texture("level-menu.png");
        levelmenusprite = new Sprite(levelmenuTexture);
        levelmenusprite.setPosition(0, 0);
        levelmenusprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        lvl_editbuttonTexture = new Texture("level-editor-button.png");
//        lvl_editbuttonsprite = new Sprite(lvl_editbuttonTexture);
//        TextureRegion buttonRegion5 = new TextureRegion(lvl_editbuttonTexture);
        TextureRegionDrawable buttonDrawable5 = new TextureRegionDrawable(lvl_editbuttonTexture);
        ImageButton button5 = new ImageButton(buttonDrawable5);
        button5.setSize(330, 83);
        button5.setPosition(795, 222);
        button5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Gdx.app.log("Button", "Level Editor Button Clicked!");
                System.out.println("Level Editor Button Clicked!");
                PuzzleDashGame game1 = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game1.setScreen(new LevelEditor(batch1));
                dispose();
                // Add code to start the game or transition to another screen
            }
        });
        backbuttonTexture = new Texture("back-button.png");
//      backbuttonsprite = new Sprite(backbuttonTexture);
//        TextureRegion buttonRegion6 = new TextureRegion(backbuttonTexture);
        TextureRegionDrawable buttonDrawable6 = new TextureRegionDrawable(backbuttonTexture);
        ImageButton button6 = new ImageButton(buttonDrawable6);
        button6.setSize(139, 83);
        button6.setPosition(890, 116);
        button6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Gdx.app.log("Button", "Level Editor Button Clicked!");
                System.out.println(" Back Button Clicked!");
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new MainMenuScreen(game));


                // Add code to start the game or transition to another screen
            }
        });



        stage.addActor(button5);
        stage.addActor(button6);

    }

    @Override
    public void show() {
        // Initialize level resources if needed
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
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
    }
}
