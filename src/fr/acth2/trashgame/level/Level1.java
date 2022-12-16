package fr.acth2.trashgame.level;

import static org.lwjgl.opengl.GL11.*;
import static fr.acth2.trashgame.Main.*;
public class Level1
{
    public static int Obstacle1X = 160;
    public static int Obstacle1X2 = 185;
    public static int Obstacle1Y = 9;
    public static int Obstacle1Y2 = 100;
    public static int Obstacle1Size = 16;

    public static void load(){
        loadCube();
        loadCubeCollision();
        //firstObstacle();
    }

    public static void firstObstacle(){
        glBegin(GL_QUADS);
        glVertex2f(Obstacle1X2 , Obstacle1Y);
        glVertex2f(Obstacle1X + Obstacle1Size , Obstacle1Y);
        glVertex2f(Obstacle1X + Obstacle1Size, Obstacle1Y2 + Obstacle1Size);
        glVertex2f(Obstacle1X2 , Obstacle1Y2 + Obstacle1Size);
        glEnd();

        glBegin(GL_QUADS);
        glVertex2f(PlayerX , PlayerY);
        glVertex2f(PlayerX + PlayerSize , PlayerY);
        glVertex2f(PlayerX + PlayerSize, PlayerY + PlayerSize);
        glVertex2f(PlayerX , PlayerY + PlayerSize);
        glEnd();
    }
    public static void loadCube(){
        glBegin(GL_QUADS);
        glVertex2f(0 , 127);
        glVertex2f(300 + 16 , 127);
        glVertex2f(300 + 16, 150 + 16);
        glVertex2f(0 , 150 + 16);
        glEnd();
    }

    public static void loadCubeCollision(){
        if(PlayerY == 113){
            PlayerY = 112;
        }
    }

    public static void unloadCube(){
        Obstacle1X = -10000;
        Obstacle1X2 = -10000;
        Obstacle1Y = -10000;
        Obstacle1Y2 = -10000;
        Obstacle1Size = 0;
    }
}
