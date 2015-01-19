package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by romanismagilov on 19.01.15.
 */
public class Obstacle extends Placable {
    private TextureRegion
            textureRegion;

    // Debug shape
    private Circle boundingCircle;
    private Rectangle boundingRectangle;

    // Variables
    public Vector2
            position;
    public int
            width,
            height;
    private static Vector2 temp;
    private float maxYReached = 0f;

    // Save start variables
    private float defX, defY;

    // The constructor
    public Obstacle(float x, float y) {
        // Default
        TextureLoader.load();
        textureRegion = TextureLoader.wall;

        width = MyGdxGame.gameWidth;
        height = 15;

        boundingCircle = new Circle(x + width / 2f, y + width / 2f, width / 2f);

        // Set vectors
        position = new Vector2(0, 0);
        position = new Vector2(x, y);
        resetNeeded = false;
    }

    public void moveTo(float x, float y) {
        position = new Vector2(x,y);
    }


    // The update method
    public void update (float delta)
    {
        position.add(0,-delta);
    }

    public void refresh ()
    {
        maxYReached = 0f;
    }

    // Destroys (eg when changing)
    public void destroy ()
    {
        boundingCircle = null;
        boundingRectangle = null;
    }

    // Getters
    public Circle getBoundingCircle()
    {
        return boundingCircle;
    }

    public Rectangle getBoundingRectangle()
    {
        return boundingRectangle;
    }

    public float getX ()
    {
        return position.x;
    }

    public float getY ()
    {
        return position.y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public float getMaxYReached()
    {
        return maxYReached;
    }

    public TextureRegion getTextureRegion()
    {
        return textureRegion;
    }
}
