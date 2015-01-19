package com.mygdx.game.Model;

import com.mygdx.game.Controller.InputController;
import com.mygdx.game.View.MenuLayout;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by romanismagilov on 19.01.15.
 */
public class JumperGameModel {

    public static UserHero userHero = new UserHero(0, 0);
    public static ArrayList<Obstacle> Obstacles = new ArrayList();
    public static ArrayList<Particle> Particles = new ArrayList();

    public static float speed = 250;
    public static float heroSpeed = 0;
    public static float heroAcceleration = 0;

    static int score=0;

    static Random random = new Random();

    public static void GenerateFirstObstacles() {
        for (int i = 0; i < 15; i++) {
            int space = 200 + random.nextInt(300);
            Obstacle obstacle = new Obstacle(0, space);
            if (Obstacles.size() != 0) {
                obstacle.position.add(0, Obstacles.get(Obstacles.size() - 1).getY());
            } else
                obstacle.width = MyGdxGame.gameWidth;
            Obstacles.add(obstacle);
        }
    }
    // Генератор частиц. Ограничен 300 частицами. Дальше берутся уже созданные
    static int particleCounter=0;
    static void createParticles(int amount){
        for (int i=0; i<amount; i++) {
            if (particleCounter>=100) {
                Particle particle=Particles.get(particleCounter%100);
                particle.moveTo(MyGdxGame.gameWidth / 2 + userHero.getWidth() / 2f, 140);
            }
            else {
                Particle particle = new Particle(MyGdxGame.gameWidth / 2 + userHero.getWidth() / 2f, 140);
                Particles.add(particle);
            }
            particleCounter++;
        }
    }


    public static void MoveAll(float delta) {
        speed += 0.1;
        // Перемещение препятствий
        for (int i = 0; i < Obstacles.size() - 1; i++) {
            Obstacles.get(i).position.add(0, -delta * speed);
            if (Obstacles.get(i).getY() < -10) {
                MenuLayout.displayScore(++score);
                Obstacles.get(i).position.add(0, speed + random.nextInt(300) + Obstacles.get(Obstacles.size() - 1).getY());
            }

        }
        // Перемещение частиц
        for (int i=0; i<Particles.size() - 1; i++){
            Particles.get(i).position.sub(5 - random.nextInt(10), 3 + random.nextInt(14));
        }
        if (userHero.collision == UserHero.Collision.OFF) {



            heroSpeed -= heroAcceleration;
            if (heroSpeed>150) {
                userHero.height += 1;
                if (userHero.height%3==0) createParticles(3);
            }
            else if (heroSpeed<=150) {
                userHero.height -= 1;
                if (userHero.height%3==0) createParticles(1);
            }
            for (int i = 0; i < Obstacles.size() - 1; i++) {
                Obstacles.get(i).position.add(0, -delta * heroSpeed);
                if (Obstacles.get(i).getY() < -10) {
                    Obstacles.get(i).position.add(0, heroSpeed + random.nextInt(300) + Obstacles.get(Obstacles.size() - 1).getY());
                }
                if (heroSpeed <= 0) {
                    userHero.collision = UserHero.Collision.ON;
                    heroSpeed = 300;
                    heroAcceleration = 6;
                    userHero.height=40;
                }
            }
        }


    }
}