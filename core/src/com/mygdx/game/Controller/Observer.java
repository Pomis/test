package com.mygdx.game.Controller;

import com.mygdx.game.Model.MyGdxGame;

/**
 * Created by romanismagilov on 18.01.15.
 */
public class Observer
{
    public static void tapToStartClicked ()
    {
        MyGdxGame.changeScreen(MyGdxGame.Screens.GAME);
    }

    public static void restartGameClicked ()
    {
        MyGdxGame.changeScreen(MyGdxGame.Screens.GAME);
    }
}
