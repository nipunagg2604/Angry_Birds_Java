package io.github.angrybirdsjava.pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Crown_Pig {
    private Texture crown_pig;
    public Crown_Pig() {
        crown_pig=new Texture(Gdx.files.internal("pigs/crownpig.png"));
    }
    public Texture getcrownpig() {
        return crown_pig;
    }
}
