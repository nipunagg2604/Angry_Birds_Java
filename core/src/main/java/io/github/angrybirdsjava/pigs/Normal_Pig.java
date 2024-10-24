package io.github.angrybirdsjava.pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Normal_Pig {
    private Texture normal_pig;
    public Normal_Pig() {
        normal_pig=new Texture(Gdx.files.internal("pigs/normal_pig.jpg"));
    }
    public Texture getnormalpigpig() {
        return normal_pig;
    }
}
