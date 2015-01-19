package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Controller.Observer;
import com.mygdx.game.Model.JumperGameModel;
import com.mygdx.game.Model.MyGdxGame;

/**
 * Created by romanismagilov on 18.01.15.
 */
public class MenuLayout {
    // Variables
    public static Stage stage;
    private static Skin skin;
    private static OrthographicCamera camera;
    private static Table table;
    private static float colorR, colorG, colorB, colorA;
    private static Array<MovableElement> elements;
    private static MovableElement element;
    private static BitmapFont
            font40,
            font60,
            font90,
            boldFont40,
            boldFont60,
            boldFont90;

    public static boolean tapToJumpPressed = false;

    // Поля надписи  tap to start
    static float scale = 1;
    static float scaley = 1;
    static float opacity = 1f;
    // Инициализация рендерера
    public static void init (int width, int height)
    {
        // Create stage
        stage = new Stage(new ScreenViewport());

        // Set stage's camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        stage.getViewport().setCamera(camera);
        stage.getViewport().setWorldSize(width, height);

        // Make the game react on touches
        MyGdxGame.addInputProcessor(stage);

        // Define skin
        skin = new Skin();

        // Generate fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Digital Sans EF Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        font40 = generator.generateFont(parameter);
        font40.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        parameter.size = 60;
        font60 = generator.generateFont(parameter);
        font60.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        parameter.size = 90;
        font90 = generator.generateFont(parameter);
        font90.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Digital Sans EF Medium.ttf"));
        parameter.size = 40;
        boldFont40 = generator.generateFont(parameter);
        boldFont40.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        parameter.size = 60;
        boldFont60 = generator.generateFont(parameter);
        boldFont60.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        parameter.size = 90;
        boldFont90 = generator.generateFont(parameter);
        boldFont90.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator.dispose();

        skin.add("regular40", font40);
        skin.add("regular60", font60);
        skin.add("regular90", font90);
        skin.add("bold40", boldFont40);
        skin.add("bold60", boldFont60);
        skin.add("bold90", boldFont90);

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Font
        skin.add("regular40", font40);
        skin.add("regular60", font60);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Set the skin to the table
        table.setSkin(skin);
    }


    static Label tapToStart;//=new Label("Tap to start", skin, "regular90", Color.WHITE);
    static Label tapToExit;
    static Label tapToJump;
    static Label separator;
    static Label level1Label;
    public static void setScreen (MyGdxGame.Screens whichScreen)
    {
        tapToJump=new Label("New jumper game", skin, "regular90", Color.WHITE);
        tapToExit=new Label("Exit", skin, "regular90", Color.WHITE);
        separator=new Label("\n\n", skin, "regular90", Color.WHITE);

        switch (whichScreen)
        {
            // Main menu
            case MAIN_MENU:
                // Firstly, clear the UI
                table.clearChildren();
                table.pad(0);
                table.center();

                // Add 'tap to start' label
                //final Label tapToStart = new Label("Tap to start", skin, "regular90", Color.WHITE);


                tapToExit.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.exit();

                        return false;
                    }
                });
                tapToJump.addListener(new InputListener()
                {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                        tapToJumpPressed=true;

                        return false;
                    }
                });
                table.add(tapToJump);
                table.row();
                table.add(separator);
                table.row();
                table.add(tapToExit);
                // Fin
                break;
            // Settings screen
            case SETTINGS:
                break;
            // Game screen
            case GAME:
                // Firstly, clear the UI
                table.clearChildren();
                table.pad(0);
                table.center();

                final Label tempLabel = new Label("Game is active", skin, "regular40", Color.WHITE);

                tempLabel.addListener(new InputListener()
                {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
                    {
                        Gdx.app.log("Clicked", "Game is active");
                        Observer.restartGameClicked();
                        return false;
                    }
                });

                table.add(tempLabel);
                table.padTop(10f);

                table.align(Align.top);

                // Fin
                break;
            // Game over screen
            case GAME_OVER:
                // Firstly, clear the UI
                table.clearChildren();
                table.pad(0);
                table.center();

                final Label tempLabel2 = new Label("Game is over", skin, "regular60", Color.WHITE);

                tempLabel2.addListener(new InputListener()
                {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
                    {
                        Gdx.app.log("Clicked", "Restart");
                        Observer.restartGameClicked();
                        return false;
                    }
                });

                table.add(tempLabel2);

                // Fin
                break;
        }
    }

    public static void displayScore(int score){
        table.clearChildren();
        table.pad(0);
        table.top();
        Label scoreLabel = new Label("Score: "+score, skin, "regular60", Color.WHITE);
        table.add(scoreLabel);
    }

    public static void refreshText()
    {
        if (tapToJumpPressed){
            tapToJump.setFontScale(scale*=1.05, scaley*=1.01);
            table.setColor(1f,1f,1f,opacity*=0.8);
            if (scale>3) {
                tapToJump.clear();
                table.clearChildren();
                table.setColor(1f,1f,1f,1f);
                tapToJumpPressed = false;
                MyGdxGame.gameState = MyGdxGame.gameStates.GAME;
                showLevels();
            }
        }
    }
    public static void showLevels(){
        table.clearChildren();
        level1Label = new Label("Level 1", skin, "regular60", Color.WHITE);
        Label level2Label = new Label("Level 2", skin, "regular60", Color.WHITE);
        Label level3Label = new Label("Level 3", skin, "regular60", Color.WHITE);
        Label level4Label = new Label("Level 4", skin, "regular60", Color.RED);
        table.add(level1Label);
        table.row();
        table.add(level2Label);
        table.row();
        table.add(level3Label);
        table.row();
        table.add(level4Label);
        level1Label.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MyGdxGame.gameState= MyGdxGame.gameStates.JUMPING;
                JumperGameModel.GenerateFirstObstacles();
                table.clearChildren();
                return false;
            }
        });
    }

        // The constructor
    // All backgrounds are defined here
    public static void renderBackground (int type)
    {
        elements = new Array<MovableElement>();

        switch (type)
        {
            // Default
            case 0:
                // Colors
                colorR = 39f/255f;
                colorG = 49f/255f;
                colorB = 52f/255f;
                colorA = 1f;
        }
    }

    // Render only color
    public static void render()
    {
        Gdx.gl.glClearColor(colorR, colorG, colorB, colorA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    // Render with elements
    public static void render (Batch batch)
    {
        // Fill the screen with the color
        Gdx.gl.glClearColor(colorR, colorG, colorB, colorA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw each element
        for (int i = 0; i < elements.size; i++)
        {
            element = elements.get(i);

            batch.begin();
            batch.draw(element.textureRegion, element.getX(), element.getY(), element.getWidth(), element.getHeight());
            batch.end();
        }
    }
    // Render without clearing the screen
    public static void render (float delta)
    {
        stage.act(delta);
        stage.draw();
    }

    // Maybe needed to clear screen
    public static void render (float delta, boolean needClear)
    {
        if (needClear)
        {
            Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        stage.act(delta);
        stage.draw();
    }

    // Update elements
    public static void update (float delta, float yDelta, float yOffset)
    {
        for (int i = 0; i < elements.size; i++)
        {
            element = elements.get(i);
            element.update(delta, yDelta, yOffset);
        }
    }

    // Refresh
    public static void refresh ()
    {
        for (int i = 0; i < elements.size; i++)
        {
            element = elements.get(i);
            element.refresh();
        }
    }

    // Destroy (eg when background's changed)
    public void destroy ()
    {
        elements.clear();
        element = null;
    }

    private abstract class MovableElement
    {
        // Stuff
        protected Vector2 position;
        protected float defX, defY; // Defaults for this element
        protected float
                velocityX,
                parallaxModifer,
                yOffset;
        protected int width, height;
        private TextureRegion textureRegion;

        public MovableElement (float x, float y, float velocityX, float parallaxModifer, TextureRegion textureRegion)
        {
            this.position = new Vector2(x ,y);
            defX = x; defY = y;
            this.width = textureRegion.getRegionWidth();
            this.height = textureRegion.getRegionHeight();
            this.velocityX = velocityX;
            this.parallaxModifer = parallaxModifer;
            this.textureRegion = textureRegion;
        }

        public void update (float delta, float camYDelta, float yOffset)
        {
            position.x += velocityX * delta;
            position.y += -camYDelta * parallaxModifer;

            this.yOffset = yOffset;
        }

        abstract void refresh ();

        public float getX ()
        {
            return position.x;
        }

        public float getY ()
        {
            return position.y + yOffset;
        }

        public int getWidth()
        {
            return width;
        }

        public int getHeight()
        {
            return height;
        }
    }

    // E.g. Kirov
    private class AFlyingThing extends MovableElement
    {
        // If to refresh x position every new refresh (eg new game)
        private boolean refreshX = false;

        // Time for which element will be outside of the screen
        private float invisibleTime = 0f;

        // To flip velocity (not texture) when refreshed?
        private boolean flip = false;

        // Constructor #1
        public AFlyingThing(float x, float y, float velocityX, float parallaxModifer,
                            TextureRegion textureRegion, float invisibleTime, boolean flip)
        {
            super(x, y, velocityX, parallaxModifer, textureRegion);

            this.invisibleTime = invisibleTime;
            this.flip = flip;
        }

        // Constructor #2
        public AFlyingThing(float x, float y, float velocityX, float parallaxModifer,
                            TextureRegion textureRegion, float invisibleTime, boolean flip, boolean refreshX)
        {
            super(x, y, velocityX, parallaxModifer, textureRegion);

            this.refreshX = refreshX;
            this.invisibleTime = invisibleTime;
            this.flip = flip;
        }

        // It can go outside the screen in X, we should prevent it
        public void update (float delta, float deltaY, float yOffset)
        {
            super.update(delta, deltaY, yOffset);

            if (velocityX > 0f)
            {
                if (position.x - width - velocityX * invisibleTime > MyGdxGame.getGameWidth())
                {
                    if (flip)
                        velocityX = -velocityX;
                    else
                        position.x = -width;
                }
            }
            else
            {
                if (position.x + width + velocityX * invisibleTime < 0f)
                {
                    if (flip)
                        velocityX = -velocityX;
                    else
                        position.x = MyGdxGame.getGameWidth() + width;
                }
            }
        }

        public void refresh ()
        {
            if (refreshX)
                position.x = defX;

            position.y = defY;
        }
    }

    // E.g. church
    private class BigThingOnTheGround extends MovableElement
    {
        public BigThingOnTheGround(float x, float y, float velocityX, float parallaxModifer, TextureRegion textureRegion)
        {
            super(x, y, velocityX, parallaxModifer, textureRegion);
        }

        public void refresh ()
        {
            position = new Vector2(defX, defY);
        }
    }
}
