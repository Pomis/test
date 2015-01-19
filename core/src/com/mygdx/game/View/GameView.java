package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Model.JumperGameModel;
import com.mygdx.game.Model.MyGdxGame;
import com.mygdx.game.Model.Obstacle;
import com.mygdx.game.Model.Particle;
import com.mygdx.game.Model.UserHero;

/**
 * Created by romanismagilov on 18.01.15.
 */
public class GameView {
    private final static float CAMERA_SMOOTH = 1f;
    private final static boolean SHOW_COLLIDERS = false;

    // private ScrollHandler scrollHandler;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    // Game objects
    private UserHero userHero;

    // Variables
    private static float previousYDelta = 0f, yOffset = 0f;

    public GameView(JumperGameModel gameModel) {
        // Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.getGameWidth(), MyGdxGame.getGameHeight());

        // Initialize batch
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        // Initialize shape renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Reference game objects
        userHero = gameModel.userHero;
        //scrollHandler = gameModel.getScrollHandler();
    }

    public void render(boolean clearScreen) {
        // Clear the screen
        if (clearScreen) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        // Draw the stuff
        batch.begin();
        drawUserHero();
        drawObstacles();
        drawParticles();
        // End
        batch.end();
    }

    private void drawUserHero() {
        batch.enableBlending();
        batch.draw(userHero.getTextureRegion(), MyGdxGame.gameWidth/2f, 140,
                userHero.getWidth(), userHero.getHeight());
        batch.setColor(1f, 1f, 1f, 1f);
    }

    private void drawObstacles() {
        batch.enableBlending();
        for (Obstacle obstacle: JumperGameModel.Obstacles) {
            batch.draw(obstacle.getTextureRegion(), obstacle.getX(), obstacle.getY(),
                    obstacle.getWidth(), obstacle.getHeight());
        }
    }

    private void drawParticles() {
        batch.enableBlending();
        for (Particle particle: JumperGameModel.Particles) {
            batch.draw(particle.getTextureRegion(), particle.getX(), particle.getY(),
                    particle.getWidth(), particle.getHeight());
        }
    }

//region Shit

    //private void drawPipes ()
    //{
    //    batch.enableBlending();

    //    for (int i = 0; i < ScrollHandler.getMaxPipes(); i++)
    //    {
    //        Pipes pipes = scrollHandler.getPipes().get(i);

    //        batch.draw(ScrollHandler.getPipeLeft(),
    //                pipes.getX(),
    //                pipes.getY(),
    //                pipes.getPipeWidth(),
    //                pipes.getPipeHeight());
    //       batch.draw(ScrollHandler.getPipeRight(),
    //                pipes.getX() + pipes.getPipeWidth() + ScrollHandler.getHorizontalGap(),
    //                pipes.getY(),
    //                pipes.getPipeWidth(),
    //                pipes.getPipeHeight());
    //    }
    //}

    //private void drawGems ()
    //{
    //    batch.enableBlending();

    //    for (int i = 0; i < GemsHandler.getActiveGems().size; i++)
    //   {
    //        Gem gem = GemsHandler.getActiveGems().get(i);
    //
    //        batch.draw(gem.textureRegion, gem.getX(), gem.getY());
    //     }
    // }

    /*private void drawBonusesDebug ()
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.PINK);

        for (int i = 0; i < GemsHandler.getActiveGems().size; i++)
        {
            Gem gem = GemsHandler.getActiveGems().get(i);

            shapeRenderer.circle(gem.getBoundingCircle().x, gem.getBoundingCircle().y, gem.getBoundingCircle().radius);
        }

        shapeRenderer.end();
    }*/

/*    private void drawJumperDebug ()
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);

        shapeRenderer.circle(jumper.getBoundingCircle().x, jumper.getBoundingCircle().y,
                jumper.getBoundingCircle().radius);

        shapeRenderer.end();
    }*/

   /* private void drawPipesDebug ()
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);

        for (int i = 0; i < ScrollHandler.getMaxPipes(); i++)
        {
            Pipes pipes = scrollHandler.getPipes().get(i);

            shapeRenderer.rect(pipes.getLeftBoundingRect().x, pipes.getLeftBoundingRect().y,
                    pipes.getLeftBoundingRect().width, pipes.getLeftBoundingRect().height);
            shapeRenderer.rect(pipes.getRightBoundingRect().x, pipes.getRightBoundingRect().y,
                    pipes.getRightBoundingRect().width, pipes.getRightBoundingRect().height);
        }

        shapeRenderer.end();
    }*/
//endregion
    public void refresh ()
    {
        camera.translate(0f, -yOffset);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        yOffset = 0f;
        previousYDelta = 0f;
    }

    public void dispose ()
    {
        batch.dispose();
        shapeRenderer.dispose();
    }

    // Getters
    public static float getYOffset()
    {
//        Gdx.app.log("Y offset", yOffset+"");
        return yOffset;
    }

    public static float getPreviousYDelta()
    {
        return previousYDelta;
    }

    public SpriteBatch getBatch()
    {
        return batch;
    }
}
