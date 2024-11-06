package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//        Class
public class DeathScreen implements Screen {
    SpriteBatch batch;
    Texture deathtexture;
    Texture diedtexture;
    Texture restarttexture;
    Texture quittexture;
    Sprite deathSprite;
    Music bg;
    Stage stage;
    int i;
//    Constructor
    DeathScreen(SpriteBatch batch,int i){

        this.batch=batch;
        this.i=i;
        deathtexture = new Texture("death-overlay.png");
        deathSprite = new Sprite(deathtexture);
        deathSprite.setPosition(0,0);
        deathSprite.setSize(1920,1080);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
//        Death Texture
        diedtexture = new Texture("you-died-text.png");
        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(diedtexture);
        ImageButton button1 = new ImageButton(buttonDrawable1);
        button1.setSize(714,115);
        button1.setPosition(620,560);

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("You Died!");
            }
        });

//        Restart button
        restarttexture = new Texture("restart-button.png");
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(restarttexture);
        ImageButton button2 = new ImageButton(buttonDrawable2);
        button2.setSize(213, 83);
        button2.setPosition(850, 370);

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Button Clicked!");
                bg.stop();
                if(i==1) {
                    bg.stop();
                    PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                    game.setScreen(new Level1(batch));
                    dispose();
                } else if (i==2) {
                    bg.stop();
                    PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                    game.setScreen(new Level2(batch));
                    dispose();
                } else {
                    bg.stop();
                    PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                    game.setScreen(new Level3(batch));
                    dispose();

                }
            }
        });

//        Quit Button
        quittexture = new Texture("quit-button.png");
        TextureRegionDrawable buttonDrawable3 = new TextureRegionDrawable(quittexture);
        ImageButton button3 = new ImageButton(buttonDrawable3);
        button3.setSize(139, 82);
        button3.setPosition(886, 200);

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Open Credits Button Clicked!");
                bg.stop();
                PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }


        });

        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);



    }
    @Override
    public void show() {

        bg = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        bg.setLooping(true); // Set to loop if needed
        bg.setVolume(0.2f); // Set volume (0.0 to 1.0)
        bg.play();

    }

    @Override
    public void render(float v) {
        batch.begin();
            deathSprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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
        deathtexture.dispose();
        diedtexture.dispose();
        stage.dispose();
    }
}
