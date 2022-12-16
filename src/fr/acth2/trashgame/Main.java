package fr.acth2.trashgame;

import fr.acth2.trashgame.level.Level1;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import java.io.File;
import static fr.acth2.trashgame.utils.UtilClass.*;

import static org.lwjgl.opengl.GL11.*;

public class Main
{

    public boolean running = false;
    public static int scale = 3;
    public static int width = 720 / scale;
    public static int height = 480 / scale;

    public static int PlayerX = 16;// + time / speed;
    public static int PlayerY = 35;// + time / speed;

    public static String title = "Trash game - V01 - AcTh2";

    public int time = 0;

    public static int PlayerSpeed = 8;
    public static int PlayerSize = 16;




    DisplayMode mode = new DisplayMode(width * scale, height * scale);

    static {
        String os_name = System.getProperties().getProperty("os.name").split(" ")[0].toLowerCase();
        if(os_name.equalsIgnoreCase("mac")) os_name += "osx";
        System.setProperty("org.lwjgl.librarypath", new File("native/"+os_name).getAbsolutePath());

    }
    public Main() {

        try{
            Display.setDisplayMode(mode);
            Display.setResizable(false);
            Display.setFullscreen(false);
            Display.setTitle(title);
            Display.create();

            initGL();
        }catch(LWJGLException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void initGL(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluOrtho2D(0, width, height, 0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public void start() throws InterruptedException {
        running = !running;
        loop();
    }

    public void exit(){
        Display.destroy();
        System.exit(0);
    }

    public void loop() throws InterruptedException {
        while(running && !Display.isCloseRequested()){
            Display.update();

            update();
            playerRender();
        }

        running = !running;
        exit();
    }

    public void update(){
        time++;
    }

    public void playerMouvement() throws InterruptedException {

        if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
            PlayerY++;

            if(PlayerY == 0){
                PlayerY = 1;
            }

            System.out.println(PlayerY);

        }

        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            Thread.sleep(PlayerSpeed);
            PlayerX++;


            if(PlayerX == 223){
                PlayerX = 222;
            }

            System.out.println(PlayerX);

        }

        if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            Thread.sleep(PlayerSpeed);
            PlayerX--;


            if(PlayerX == 0){
                PlayerX = 1;
            }

            System.out.println(PlayerX);
        }


        if(PlayerY <= 0){
            PlayerY = 1;
        }

        Thread.sleep(PlayerSpeed);
        if(PlayerY != 113){
            PlayerY++;
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && isGrounded()){
                jump();
            }
        }
    }

    public void cubeRender(){
        glBegin(GL_QUADS);
        glVertex2f(PlayerX , PlayerY);
        glVertex2f(PlayerX + PlayerSize , PlayerY);
        glVertex2f(PlayerX + PlayerSize, PlayerY + PlayerSize);
        glVertex2f(PlayerX , PlayerY + PlayerSize);
        glEnd();
    }
    public void playerRender() throws InterruptedException {
        glClear(GL_COLOR_BUFFER_BIT);
        playerMouvement();
        cubeRender();
        Level1.load();
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();

        main.start();
    }
}
