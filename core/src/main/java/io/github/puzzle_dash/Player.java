package io.github.puzzle_dash;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Player extends Sprite {
//  Movement Velocity
    private Vector2 velocity;
    private float speed =60*2,gravity=60*1.8f;
    public Player(Sprite sprite)
    {
        super(sprite);
        velocity = new Vector2();
    }
    public void draw(SpriteBatch  batch)
    {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);

    }

    private void update(float delta) {
        //Applying Gravity
        velocity.y-=gravity*delta;
        if(velocity.y>speed)
            velocity.y=speed;
        else if(velocity.y<speed)
            velocity.y= -speed;
        setX(getX()+velocity.x*delta);
        setY(getY()+velocity.y*delta);
    }







}
