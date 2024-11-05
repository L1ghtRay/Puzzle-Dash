package io.github.puzzle_dash;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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


    private Texture[] playerTextures;
    private int currentTextureIndex;
    private float animationTimer;
    private static final float FRAME_DURATION = 0.1f; // Time per frame in seconds
    private Music bg;
    PuzzleDashGame game;
    Texture PauseTexture;
    OrthographicCamera camera;
    Viewport gamePort;
    Hud hud;
    SpriteBatch batch;
    TmxMapLoader mapLoader;
    TiledMap map;
    Player player;
    OrthogonalTiledMapRenderer renderer;
    Stage stage;
    boolean isPaused;
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
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//        PauseTexture = new Texture("play-button.png");
////        playbuttonsprite = new Sprite(playbuttonTexture);
////        TextureRegion buttonRegion1 = new TextureRegion(playbuttonTexture);
//        TextureRegionDrawable buttonDrawable1 = new TextureRegionDrawable(PauseTexture);
//        ImageButton button1 = new ImageButton(buttonDrawable1);
//        button1.setSize(149, 82);
//        button1.setPosition(885, 489);
//        stage.addActor(button1);
    }

    public void handleInput(float dt) {
        boolean isMoving = false;
        float speed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? DASH_SPEED : PLAYER_SPEED;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setPosition(player.getX() + speed * 4 * dt, player.getY());
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setPosition(player.getX() - speed * 4 * dt, player.getY());
            isMoving = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.jump(); // Initiate jump
        }
        isPaused=false;
        // Handle animation only if moving
        if (isMoving) {
            animationTimer += dt;
            if (animationTimer >= FRAME_DURATION) {
                animationTimer = 0;
                currentTextureIndex = (currentTextureIndex + 1) % playerTextures.length;
                if (currentTextureIndex != 0)
                    player.setRegion(playerTextures[currentTextureIndex]);
            }
        } else {
            player.setRegion(playerTextures[0]);
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
            bg.stop();
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new PauseMenu(batch,Level1.this));

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
            new Texture("guy1.png"),
            new Texture("guy2.png"),
            new Texture("guy3.png"),
            new Texture("guy4.png"),
            new Texture("guy5.png"),
            new Texture("guy6.png"),
            new Texture("guy7.png")
        };

        // Initialize player with the first texture
        player = new Player(new Sprite(playerTextures[0]));
        player.setScale(2.0f, 2.0f);

        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("layer3");
        player.setPosition(450, 380);
        player.setCollisionLayer(collisionLayer);
        bg = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        bg.setLooping(true); // Set to loop if needed
        bg.setVolume(0.2f); // Set volume (0.0 to 1.0)
        bg.play(); // Start playing the music


        currentTextureIndex = 0;
        animationTimer = 0;
        System.out.println("Player initialized with animation");
    }

        // Debugging



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
        bg.dispose();
    }
    public void resumeGame()
    {
        isPaused = false;
    }
    public void pausedGame()
    {
        isPaused=true;
    }

}
