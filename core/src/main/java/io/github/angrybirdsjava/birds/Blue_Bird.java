package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Blue_Bird {
    private Texture blue_bird;
    public Blue_Bird() {
        blue_bird=new Texture(Gdx.files.internal("birds/bluebird.jpg"));
    }
    public Texture getblueBird() {
        return blue_bird;
    }
}
