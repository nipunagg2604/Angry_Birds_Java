package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Black_Bird {
    private Texture black_bird;
    public Black_Bird() {
        black_bird=new Texture(Gdx.files.internal("birds/black.png"));
    }
    public Texture getblackBird() {
        return black_bird;
    }
}
