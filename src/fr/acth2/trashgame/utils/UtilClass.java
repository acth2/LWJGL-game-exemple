package fr.acth2.trashgame.utils;

import static fr.acth2.trashgame.Main.*;

public class UtilClass
{
    public static boolean isGrounded(){

        if(PlayerY == 113){
            return true;
        }else{
            return false;
        }
    }

    public static void jump(){
        for (i = 0; i >= 30; i++){
            PlayerY--;
        }
    }
}
