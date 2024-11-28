package io.github.angrybirdsjava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.angrybirdsjava.screens.EndScreen;
import io.github.angrybirdsjava.screens.HomeScreen;


public class Core extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
//        setScreen(new HomeScreen(this));
        setScreen(new io.github.angrybirdsjava.ThemeScreen(this));
//        setScreen(new io.github.angrybirdsjava.LevelScreen(this));
//        dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
