package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Red_Bird {
    private Texture red_bird;
    public Red_Bird() {
        red_bird=new Texture(Gdx.files.internal("birds/redbird.jpg"));
    }
    public Texture getRedBird() {
        return red_bird;
    }
}
