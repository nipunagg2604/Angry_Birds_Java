package io.github.angrybirdsjava.levels;

import com.badlogic.gdx.graphics.Texture;
import io.github.angrybirdsjava.birds.Black_Bird;
import io.github.angrybirdsjava.birds.Red_Bird;
import io.github.angrybirdsjava.birds.Yellow_Bird;
import io.github.angrybirdsjava.pigs.Crown_Pig;

public class Level1 {
    private Texture background;
    private Red_Bird redbird;
    private Black_Bird blackbird;
    private Yellow_Bird yellowbird;
    private Crown_Pig crown_pig;

    public Level1() {
        background = new Texture("Gamescreen/background.jpg");
        redbird=new Red_Bird();
        blackbird=new Black_Bird();
        yellowbird=new Yellow_Bird();
        crown_pig=new Crown_Pig();
    }

    public Texture getBackground() {return background;}

    public Red_Bird getRedbird() {
        return redbird;
    }

    public Yellow_Bird getYellowbird() {
        return yellowbird;
    }

    public Black_Bird getBlackbird() {
        return blackbird;
    }

    public Crown_Pig getCrown_pig() {
        return crown_pig;
    }
}
