package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created by romanismagilov on 18.01.15.
 */
public class TextureLoader {
    static Random random = new Random();

    private static Texture userHeroTexture;
    public static TextureRegion userHero;

    private static Texture wallTexture;
    public static TextureRegion wall;
    public static void load()
    {
        userHeroTexture = new Texture(Gdx.files.internal("images/userHero.png"));
        userHeroTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        userHero = new TextureRegion(userHeroTexture);

        wallTexture = new Texture(Gdx.files.internal("images/wall1.png"));
        wallTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        wall = new TextureRegion(wallTexture);
    }

    public static TextureRegion randomParticle(){
        String randomParticlePath = "images/particle"+(1+random.nextInt(3))+".png";
        Texture particleTexture = new Texture(Gdx.files.internal(randomParticlePath));
        return new TextureRegion(particleTexture);
    }
}
