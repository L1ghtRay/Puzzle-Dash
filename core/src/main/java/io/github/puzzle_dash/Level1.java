package io.github.puzzle_dash;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

class Hud implements Disposable {
    public Stage stage;
    Viewport viewport;

    Integer worldTimer;
    float timeCount;
    int score;

    float scoreIncrementTime; // Timer for score increment
    private static final float SCORE_INCREMENT_INTERVAL = 1.0f; // Score increases every second

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

        scoreIncrementTime = 0; // Initialize the timer

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
        scoreIncrementTime += dt; // Increment the timer

        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }

        // Increment score every SCORE_INCREMENT_INTERVAL seconds
//        if (scoreIncrementTime >= SCORE_INCREMENT_INTERVAL) {
//            score += 10; // Increase the score by 10 (or any amount you prefer)
//            scoreLabel.setText(String.format("%06d", score)); // Update the score label
//            scoreIncrementTime = 0; // Reset the timer
//        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

//        Class
public class Level1 implements Screen {

    private Texture[] playerTextures;
    private int currentTextureIndex;
    private float animationTimer;
    private static final float FRAME_DURATION = 0.1f; // Time per frame in seconds
    private Music bg;
    Sprite whiteButtonSprite;
    Rectangle whiteButtonBounds;
    Sprite redButtonSprite;
    Rectangle redButtonBounds;
    Sprite yellowButtonSprite;
    Rectangle yellowButtonBounds;
    Sprite blueButtonSprite;
    Rectangle blueButtonBounds;
    Sprite redLightSprite;
    Sprite blueLightSprite;
    Sprite yellowLightSprite;
    OrthographicCamera camera;
    Viewport gamePort;
    Hud hud;
    SpriteBatch batch;
    TmxMapLoader mapLoader;
    TiledMap map;
    Player player;
    OrthogonalTiledMapRenderer renderer;
    boolean isPaused;
    boolean isOnWhite;
    boolean isOnWhite2 = false;
    boolean isOnRed;
    boolean isOnRed2 = false;
    boolean isOnYellow;
    boolean isOnYellow2 = false;
    boolean isOnBlue;
    boolean isOnBlue2 = false;
    private float savedPlayerX, savedPlayerY;
    private int savedTextureIndex;
    private float savedAnimationTimer;
    private static final float PLAYER_SPEED = 100; // Regular speed
    private static final float DASH_SPEED = 170; // Dash speed

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
        // Handle animation only if moving
        if (isMoving) {
            animationTimer += dt;
            if (animationTimer >= FRAME_DURATION) {
                animationTimer = 0;
                currentTextureIndex = (currentTextureIndex + 1) % playerTextures.length;
                if (currentTextureIndex != 0)
                    player.setRegion(playerTextures[currentTextureIndex]);
            }
        }
        else {
            player.setRegion(playerTextures[0]);
        }

    }


    public void update(float dt) {
        handleInput(dt);
        hud.update(dt);

        isOnWhite = player.getBoundingRectangle().overlaps(whiteButtonBounds);
        if (isOnWhite && !isOnWhite2) {
            System.out.println("White Button Pressed");
            isOnWhite2 = isOnWhite;
        } else if (!isOnWhite && isOnWhite2) {
            System.out.println("White Button Released");
            isOnWhite2 = isOnWhite;
        }

        isOnRed = player.getBoundingRectangle().overlaps(redButtonBounds);
        if (isOnRed && !isOnRed2) {
            System.out.println("Red Button Pressed");
            isOnRed2 = isOnRed;
        } else if (!isOnRed && isOnRed2) {
            System.out.println("Red Button Released");
            isOnRed2 = isOnRed;
        }

        isOnYellow = player.getBoundingRectangle().overlaps(yellowButtonBounds);
        if (isOnYellow && !isOnYellow2) {
            System.out.println("Yellow Button Pressed");
            bg.stop();
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new Level2(batch));
            isOnYellow2 = isOnYellow;
        } else if (!isOnYellow && isOnYellow2) {
            System.out.println("Yellow Button Released");
            isOnYellow2 = isOnYellow;
        }

        isOnBlue = player.getBoundingRectangle().overlaps(blueButtonBounds);
        if (isOnBlue && !isOnBlue2) {
            System.out.println("Blue Button Pressed");
            isOnBlue2 = isOnBlue;
        } else if (!isOnBlue && isOnBlue2) {
            System.out.println("Blue Button Released");
            isOnBlue2 = isOnBlue;
        }

        camera.position.x = player.getX(); // Center the camera on the player
        camera.update();
        renderer.setView(camera);

    }



    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            bg.stop();
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new PauseMenu(batch, Level1.this));

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            bg.stop();
            PuzzleDashGame game = (PuzzleDashGame) Gdx.app.getApplicationListener();
            game.setScreen(new Level1(batch));
        }

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        renderer.getBatch().begin();
        whiteButtonSprite.draw(renderer.getBatch());
        redButtonSprite.draw(renderer.getBatch());
        yellowButtonSprite.draw(renderer.getBatch());
        blueButtonSprite.draw(renderer.getBatch());
        redLightSprite.draw(renderer.getBatch());
        blueLightSprite.draw(renderer.getBatch());
        yellowLightSprite.draw(renderer.getBatch());
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

        bg = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        bg.setLooping(true); // Set to loop if needed
        bg.setVolume(0.2f); // Set volume (0.0 to 1.0)
        bg.play(); // Start playing the music

        // Initialize player with the first texture
        player = new Player(new Sprite(playerTextures[0]),bg,1);
        player.setScale(2.0f, 2.0f);

        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("layer3");
        player.setPosition(450, 380);
        player.setCollisionLayer(collisionLayer);

        Texture whiteButtonTexture = new Texture("simon-says-button-4-1.png");  // Add your button texture
        whiteButtonSprite = new Sprite(whiteButtonTexture);
        whiteButtonSprite.setPosition(2900, 320);  // Position it within the map
        whiteButtonBounds = new Rectangle(
            (int)whiteButtonSprite.getX(),
            (int)whiteButtonSprite.getY(),
            (int)whiteButtonSprite.getWidth(),
            (int)whiteButtonSprite.getHeight()
        );

        Texture redButtonTexture = new Texture("simon-says-button-1-1.png");  // Add your button texture
        redButtonSprite = new Sprite(redButtonTexture);
        redButtonSprite.setPosition(3000, 320);  // Position it within the map
        redButtonBounds = new Rectangle(
            (int)redButtonSprite.getX(),
            (int)redButtonSprite.getY(),
            (int)redButtonSprite.getWidth(),
            (int)redButtonSprite.getHeight()
        );

        Texture blueButtonTexture = new Texture("simon-says-button-2-1.png");  // Add your button texture
        blueButtonSprite = new Sprite(blueButtonTexture);
        blueButtonSprite.setPosition(3050, 320);  // Position it within the map
        blueButtonBounds = new Rectangle(
            (int)blueButtonSprite.getX(),
            (int)blueButtonSprite.getY(),
            (int)blueButtonSprite.getWidth(),
            (int)blueButtonSprite.getHeight()
        );

        Texture yellowButtonTexture = new Texture("simon-says-button-3-1.png");  // Add your button texture
        yellowButtonSprite = new Sprite(yellowButtonTexture);
        yellowButtonSprite.setPosition(3100, 320);  // Position it within the map
        yellowButtonBounds = new Rectangle(
            (int)yellowButtonSprite.getX(),
            (int)yellowButtonSprite.getY(),
            (int)yellowButtonSprite.getWidth(),
            (int)yellowButtonSprite.getHeight()
        );

        Texture redLightTexture = new Texture("simon-says-light-1-1.png");  // Add your button texture
        redLightSprite = new Sprite(redLightTexture);
        redLightSprite.setPosition(3000, 470);  // Position it within the map

        Texture blueLightTexture = new Texture("simon-says-light-2-1.png");  // Add your button texture
        blueLightSprite = new Sprite(blueLightTexture);
        blueLightSprite.setPosition(3050, 470);  // Position it within the map

        Texture yellowLightTexture = new Texture("simon-says-light-3-1.png");  // Add your button texture
        yellowLightSprite = new Sprite(yellowLightTexture);
        yellowLightSprite.setPosition(3100, 470);  // Position it within the map

//        addNewElementsToEndOfMap();
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
        savedPlayerX = player.getX();
        savedPlayerY = player.getY();
        savedTextureIndex = currentTextureIndex;
        savedAnimationTimer = animationTimer;

        isPaused = false;
    }
    public void pausedGame()
    {
        player.setPosition(savedPlayerX, savedPlayerY);
        currentTextureIndex = savedTextureIndex;
        animationTimer = savedAnimationTimer;
        isPaused=true;
    }

}
