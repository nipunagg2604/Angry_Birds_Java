package io.github.angrybirdsjava.pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Mucchad_Pig {
    private Texture mucchad_pig;
    public Mucchad_Pig() {
        mucchad_pig=new Texture(Gdx.files.internal("pigs/Mucchad_pig.jpg"));
    }
    public Texture getmucchadpig() {
        return mucchad_pig;
    }
}
