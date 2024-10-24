package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Yellow_Bird {
    private Texture yellow_bird;
    public Yellow_Bird() {
        yellow_bird=new Texture(Gdx.files.internal("birds/yellow.jpg"));
    }
    public Texture getyellowBird() {
        return yellow_bird;
    }
}
