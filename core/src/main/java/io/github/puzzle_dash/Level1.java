package io.github.puzzle_dash;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class Hud implements Disposable {
    public Stage stage;
    Viewport viewport;

    Integer worldTimer;
    float timeCount;
    Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new StretchViewport(PuzzleDashGame.V_WIDTH, PuzzleDashGame.V_HEIGHT, new OrthographicCamera());
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


    private Texture[] playerTextures;
    private int currentTextureIndex;
    private float animationTimer;
    private static final float FRAME_DURATION = 0.1f; // Time per frame in seconds

    PuzzleDashGame game;

    OrthographicCamera camera;
    Viewport gamePort;
    Hud hud;
    SpriteBatch batch;
    TmxMapLoader mapLoader;
    TiledMap map;
    Player player;
    OrthogonalTiledMapRenderer renderer;

    private static final float PLAYER_SPEED = 100; // Regular speed
    private static final float DASH_SPEED = 300; // Dash speed

    public Level1(SpriteBatch batch) {
        this.batch = batch;
        camera = new OrthographicCamera();
        gamePort = new StretchViewport(PuzzleDashGame.V_WIDTH, PuzzleDashGame.V_HEIGHT, camera);
        hud = new Hud(this.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tutorial-room.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    public void handleInput(float dt) {
        boolean isMoving = false;
        float speed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? DASH_SPEED : PLAYER_SPEED;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setPosition(player.getX() + speed * dt, player.getY());
            isMoving = true;
            System.out.println("Moving Right"); // Debugging
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setPosition(player.getX() - speed * dt, player.getY());
            isMoving = true;
            System.out.println("Moving Left"); // Debugging
        }

        // Handle animation only if moving
        if (isMoving) {
            animationTimer += dt;
            if (animationTimer >= FRAME_DURATION) {
                animationTimer = 0;
                currentTextureIndex = (currentTextureIndex + 1) % playerTextures.length;
                player.setTexture(playerTextures[currentTextureIndex]);
            }
        } else {
            // Reset to first texture if not moving
            player.setTexture(playerTextures[0]);
        }
    }


    public void update(float dt) {
        handleInput(dt);
        hud.update(dt);
        camera.position.x = player.getX(); // Center the camera on the player
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new MainMenuScreen(game));
        }

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        renderer.getBatch().begin();
        player.draw(renderer.getBatch()); // Directly draw the player
        renderer.getBatch().end();

        hud.stage.draw(); // Draw the HUD
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }


    @Override
    public void show() {
        playerTextures = new Texture[]{
            new Texture("p2-removebg-preview.png"),
            new Texture("p3-removebg-preview.png"),
            new Texture("p4-removebg-preview.png"),
            new Texture("p5-removebg-preview.png"),
            new Texture("p6-removebg-preview.png"),
            new Texture("p7-removebg-preview.png")



        };

        // Initialize player with the first texture
        player = new Player(new Sprite(playerTextures[0]));
        player.setScale(0.4f, 0.4f);
        player.setPosition(250, 140);


        currentTextureIndex = 0;
        animationTimer = 0;
        System.out.println("Player initialized with animation"); // Debugging
    }


    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        hud.dispose();
        player.getTexture().dispose();
    }
}



