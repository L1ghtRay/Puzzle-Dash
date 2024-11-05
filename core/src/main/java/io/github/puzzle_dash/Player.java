package io.github.puzzle_dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
     Vector2 velocity = new Vector2();
     float speed = 60 * 2;
     float gravity = 60 * 1.8f;
     float jumpSpeed = 300; // Set jump speed
     Rectangle boundingBox;
    private boolean isOnGround = false; // Track if player is on ground
    private TiledMapTileLayer collisionLayer;

    public Player(Sprite sprite) {
        super(sprite);
        boundingBox = new Rectangle(getX(), getY(), getWidth() - 229, getHeight() - 229);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        boundingBox.setPosition(getX(), getY()); // Update the bounding box position
        super.draw(batch);
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public void update(float delta) {
        // Apply gravity if player is not on the ground
        if (!isOnGround) {
            velocity.y -= gravity * delta;
        } else {
            velocity.y = 0; // Reset vertical velocity if on ground
        }

        // Move on X (horizontal movement)
        setX(getX() + velocity.x * delta);

        // Move on Y (vertical movement)
        setY(getY() + velocity.y * delta);

        // Update bounding box position
        boundingBox.setPosition(getX(), getY());

        // Check for collisions after updating the bounding box
        if (collisionLayer != null) {
            checkCollisions(delta);
        }

        // Continuously update isOnGround based on the collision layer
        isOnGround = isStandingOnTile();
    }

    // New method to check if the player is standing on a tile
    private boolean isStandingOnTile() {
        // Check the tile directly beneath the player
        int tileX = (int) ((getX() + boundingBox.width / 2) / collisionLayer.getTileWidth());
        int tileY = (int) (getY() / collisionLayer.getTileHeight());

        TiledMapTileLayer.Cell cellBelow = collisionLayer.getCell(tileX, tileY);

        return cellBelow != null; // Player is on ground if there's a tile below
    }

    public void checkCollisions(float delta) {
        for (int x = 0; x < collisionLayer.getWidth(); x++) {
            for (int y = 0; y < collisionLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
                if (cell != null) {
                    // Calculate tile bounds
                    Rectangle tileBounds = new Rectangle(
                        x * collisionLayer.getTileWidth(),
                        y * collisionLayer.getTileHeight(),
                        collisionLayer.getTileWidth(),
                        collisionLayer.getTileHeight()
                    );

                    // Check for overlap with the tile
                    if (boundingBox.overlaps(tileBounds)) {
                        handleCollision(tileBounds, delta);
                    }
                }
            }
        }
    }

    void handleCollision(Rectangle tileBounds, float delta) {
        float playerRight = boundingBox.x + boundingBox.width;
        float playerTop = boundingBox.y + boundingBox.height;

        // Handle collision on top of the tile (player falling onto the tile)
        if (boundingBox.y + velocity.y <= tileBounds.y + tileBounds.height && velocity.y < 0) {
            setY(tileBounds.y + tileBounds.height); // Place the player on top of the tile
            velocity.y = 0; // Stop vertical movement
            isOnGround = true;
        }

        // Handle collision on bottom of the tile (player is pushing into the bottom of the tile)
        else if (playerTop + velocity.y >= tileBounds.y && velocity.y > 0) {
            setY(tileBounds.y - boundingBox.height); // Place the player below the tile
            velocity.y = 0; // Stop vertical movement
        }

        // Handle collision on the left of the tile
        if (boundingBox.x + velocity.x < tileBounds.x + tileBounds.width && boundingBox.x + boundingBox.width > tileBounds.x && velocity.x < 0) {
            setX(tileBounds.x + tileBounds.width); // Prevent moving left into the tile
        }

        // Handle collision on the right of the tile
        if (boundingBox.x + velocity.x + boundingBox.width > tileBounds.x && boundingBox.x < tileBounds.x + tileBounds.width && velocity.x > 0) {
            setX(tileBounds.x - boundingBox.width); // Prevent moving right into the tile
        }
    }

    public void jump() {
        if (isOnGround) {
            velocity.y = jumpSpeed - 100;
            isOnGround = false; // Player is now in the air after jumping
        }
    }
}
