package com.mygdx.game.Model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.View.*;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    Texture img;
    private static InputMultiplexer inputMultiplexer;
    public static int gameWidth = 768;
    public static int gameHeight = 1280;

    public static gameStates gameState;

    private static MainScreen mainScreen;

    private static boolean firstTimeLaunched=true;

    @Override
    public void create() {
        // Set the starting game state
        gameState = gameStates.LOADING_GAME;

        // Load all the assets
        //AssetLoader.load();

        // Get screen size and set game dimensions
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        gameHeight = (int) (screenHeight * gameWidth/ screenWidth);

        // Inputs
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);

        // Initialize all the screens
        mainScreen = new MainScreen();

        // Initialize UI
        MenuLayout.init(gameWidth, gameHeight);

        // Initialize background
        MenuLayout.renderBackground(0);

        mainScreen.show();

        // Launch the first screen
        setScreen(mainScreen);
    }

    public static enum gameStates
    {
        MAIN_MENU, GAME, RETRY, LOADING_GAME, JUMPING
    }
    public static enum Screens
    {
        MAIN_MENU, SETTINGS, GAME, GAME_OVER, JUMPING
    }

    public static int getGameWidth()
    {
        return gameWidth;
    }

    public static int getGameHeight()
    {
        return gameHeight;
    }

    // Working with input processors
    public static void addInputProcessor(InputProcessor processor)
    {
        inputMultiplexer.addProcessor(processor);
    }

    public static void changeScreen(Screens whichScreen)
    {
        switch (whichScreen)
        {
            case MAIN_MENU:
                break;
            case SETTINGS:
                break;
            case GAME:
                // Go to game screen
                // But first, does it need refresh
                if (firstTimeLaunched)
                    firstTimeLaunched = false;
                else
                   // gameScreen.refresh();

               // setScreen(gameScreen);

                // Finish
                break;
            case GAME_OVER:
              //  setScreen(gameOverScreen);
                break;
            case JUMPING:
                break;
        }
    }
}
