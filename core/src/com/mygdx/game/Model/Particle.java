package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by romanismagilov on 19.01.15.
 */
public class Particle extends Placable {
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
    public Particle(float x, float y) {
        // Default
        textureRegion = TextureLoader.randomParticle();

        width = 5;
        height = 5;

        boundingCircle = new Circle(x + width / 2f, y + width / 2f, width / 2f);

        // Set vectors
        position = new Vector2(0, 0);
        position = new Vector2(x, y);
        resetNeeded = false;
    }


    public enum Collision{ON, OFF}
    public Collision collision=Collision.ON;

    public void moveTo(float x, float y) {
        position = new Vector2(x,y);
    }


    // The update method
    public void update (float delta)
    {
    }

    public void onClick (boolean direction)
    {
    }


    public void bounce ()
    {
    }

    // Refresh position and velocity
    public void refresh ()
    {
        maxYReached = 0f;

        //position = new Vector2(defX, defY);
        //velocity = new Vector2(0, 0);
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
