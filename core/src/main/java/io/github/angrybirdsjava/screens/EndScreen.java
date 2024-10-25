package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.Core;
import io.github.angrybirdsjava.Level1Screen;

public class EndScreen implements Screen{
    private Texture endMenu;
    private Texture blurBackground;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private ImageButton stars;

    public EndScreen(final Core game) {
        this.game = game;

        blurBackground = new Texture(Gdx.files.internal("pauseBackground.png"));
        endMenu = new Texture(Gdx.files.internal("pauseMenu.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        ImageButton restartButton = new ImageButton(new TextureRegionDrawable(new Texture("restartButton.png")));
        restartButton.setSize(60, 60);
        restartButton.setPosition(500 - restartButton.getWidth(), 235 - restartButton.getWidth());

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level1Screen(game));
                dispose();
            }
        });

        stage.addActor(restartButton);

        ImageButton prevLevel = new ImageButton(new TextureRegionDrawable(new Texture("prevLevel.png")));
        prevLevel.setSize(60, 60);
        prevLevel.setPosition(365 - restartButton.getWidth(), 235 - restartButton.getWidth());

        prevLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level1Screen(game));
                dispose();
            }
        });

        stage.addActor(prevLevel);

        ImageButton nextLevel = new ImageButton(new TextureRegionDrawable(new Texture("nextLevel.png")));
        nextLevel.setSize(60, 60);
        nextLevel.setPosition(635 - restartButton.getWidth(), 235 - restartButton.getWidth());

        nextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level1Screen(game));
                dispose();
            }
        });

        stage.addActor(nextLevel);

        ImageButton stars = new ImageButton(new TextureRegionDrawable(new Texture("stars5.png")));
        stars.setSize(945, 80); // y = 100
        stars.setPosition(0, 235); // y = 220

        stage.addActor(stars);

        ImageButton gameOver = new ImageButton(new TextureRegionDrawable(new Texture("gameOver.png")));
        gameOver.setSize(200, 100);
        gameOver.setPosition(370, 290);

        stage.addActor(gameOver);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(blurBackground, 0, 0, 960, 496);
        game.batch.draw(endMenu, 240, 130, 460, 270);  // Draw the background image (adjust size if needed)
        game.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        endMenu.dispose();
        blurBackground.dispose();
        stage.dispose();
    }
}
