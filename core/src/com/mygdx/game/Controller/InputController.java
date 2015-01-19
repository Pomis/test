package com.mygdx.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.PauseableThread;
import com.mygdx.game.Model.MyGdxGame;
import com.mygdx.game.Model.UserHero;

import java.util.Random;

/**
 * Created by romanismagilov on 18.01.15.
 */
public class InputController implements InputProcessor {
    private UserHero userHero;

    public InputController(UserHero userHero, int gameWidth) {
        this.userHero = userHero;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Введенные координаты, переведенные в игровую систему координат
        float X = (float) screenX * MyGdxGame.gameWidth / Gdx.graphics.getWidth();
        float Y = (float) screenY * MyGdxGame.gameHeight / Gdx.graphics.getHeight();
        if (MyGdxGame.gameState == MyGdxGame.gameStates.GAME) {
            userHero.moveTo(X - userHero.getWidth() / 2, MyGdxGame.gameHeight - Y - userHero.getHeight() / 2);
        }
        else if (MyGdxGame.gameState == MyGdxGame.gameStates.JUMPING){
            userHero.collision = UserHero.Collision.OFF;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (MyGdxGame.gameState == MyGdxGame.gameStates.GAME) {
            userHero.moveTo(-500,-500);
        }
        return false;
    }

    Random random = new Random();

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float X = (float) screenX * MyGdxGame.gameWidth / Gdx.graphics.getWidth();
        float Y = (float) screenY * MyGdxGame.gameHeight / Gdx.graphics.getHeight();
        if (MyGdxGame.gameState == MyGdxGame.gameStates.GAME) {
            userHero.moveTo(X - userHero.getWidth() / 2, MyGdxGame.gameHeight - Y - userHero.getHeight() / 2);

            // Создаём новые монетки тут например
            if (random.nextDouble() <= 0.05) {
                //Coin coin = new Coin(100+random.nextInt(MyGdxGame.gameWidth-100), 100+random.nextInt(MyGdxGame.gameHeight-100));
                //GameModel.Coins.add(coin);
            }

            CheckIfCoinIsPicked(X,Y);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float X = (float) screenX * MyGdxGame.gameWidth / Gdx.graphics.getWidth();
        float Y = (float) screenY * MyGdxGame.gameHeight / Gdx.graphics.getHeight();
        if (MyGdxGame.gameState == MyGdxGame.gameStates.GAME) {
            userHero.moveTo(-500, -500);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void CheckIfCoinIsPicked(float X, float Y) {
        /*for (int i=0; i<GameModel.Coins.size(); i++) {
            if (GameModel.Coins.get(i).getBoundingCircle().contains(X, MyGdxGame.getGameHeight() - Y)){
                GameModel.Coins.remove(GameModel.Coins.get(i));
            }
        }*/
    }
}

