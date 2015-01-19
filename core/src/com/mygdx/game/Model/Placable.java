package com.mygdx.game.Model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Controller.*;
/**
 * Created by romanismagilov on 18.01.15.
 */
public abstract class Placable
{
    // Variables
    protected Vector2 position, temp;
    protected int width, height;
    protected boolean resetNeeded;

    // Constructor
    protected Placable (float x, float y)
    {
        position = new Vector2(x, y);
        resetNeeded = false;
    }

    protected Placable() {
    }

    // Update
    public void update (float x, float y)
    {
        position.add(temp = new Vector2(x, y));
    }

    // Getters
    public boolean getResetNeeded ()
    {
        return resetNeeded;
    }

    public float getX ()
    {
        return position.x;
    }

    public float getY ()
    {
        return position.y;
    }
}