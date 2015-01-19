package com.mygdx.game.View;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Controller.InputController;
import com.mygdx.game.Model.*;

/**
 * Created by romanismagilov on 18.01.15.
 */
public class MainScreen implements Screen {
    public MainScreen(){};

    private JumperGameModel gameModel;
    private GameView gameView;
    private InputController inputController;

    @Override
    public void show()
    {
        gameModel = new JumperGameModel();
        gameView = new GameView(gameModel);
        inputController = new InputController(gameModel.userHero,MyGdxGame.getGameWidth());
        MenuLayout.refresh();
        MyGdxGame.addInputProcessor(inputController);
        MyGdxGame.gameState=(MyGdxGame.gameStates.MAIN_MENU);
        MenuLayout.setScreen(MyGdxGame.Screens.MAIN_MENU);
        render(0.1f);
    }

    // Тот самый метод обновления содержимого экрана
    @Override
    public void render(float delta)
    {
        // Firstly - background
        MenuLayout.update(delta, 0f, 0f);
        MenuLayout.render(MenuLayout.stage.getBatch());
        gameView.render(false);
        gameModel.MoveAll(delta);
        // Then - UI
        MenuLayout.render(delta, false);

        MenuLayout.refreshText();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }
}
