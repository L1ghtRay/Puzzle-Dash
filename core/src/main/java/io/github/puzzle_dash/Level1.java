package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.puzzle_dash.Hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

 class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(PuzzleDashGame.V_WIDTH, PuzzleDashGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        worldLabel = new Label("PUZZLE", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        marioLabel = new Label("DASH", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


public class Level1 implements Screen {
    private PuzzleDashGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;



    public Level1(PuzzleDashGame game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(PuzzleDashGame.V_WIDTH, PuzzleDashGame.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tutorial-room.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            gamecam.position.x += 100 * dt;
        }
    }

    public void update(float dt) {
        handleInput(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void show() {
        // This method is called when the screen is set
    }

    @Override
    public void hide() {
        // Called when the screen is no longer set
    }

    @Override
    public void pause() {
        // Called when the game is paused
    }

    @Override
    public void resume() {
        // Called when the game is resumed
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        hud.dispose();
    }

}
