package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoadingScreen implements Screen {
    private Texture background;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private ProgressBar progressBar;
    private float progress = 0f;

    public LoadingScreen(final Core game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("loadingBackground.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin1/biological-attack-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("skin2/neon-ui.json"));
        Skin skin3 = new Skin(Gdx.files.internal("skin3/neon-ui.json"));
        Skin skin4 = new Skin(Gdx.files.internal("skin4/clean-crispy-ui.json"));
        Skin skin5 = new Skin(Gdx.files.internal("skin5/level-plane-ui.json"));

        progressBar = new ProgressBar(0f, 1f, 0.01f, false, skin4);
        progressBar.setSize(820, 40);
        progressBar.setPosition(Gdx.graphics.getWidth() / 2f - 410, Gdx.graphics.getHeight() / 2f - 220);
        progressBar.setColor(Color.CYAN);

        stage.addActor(progressBar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 960, 496);
        game.batch.end();

        if (progress < 1) {
            progress += Gdx.graphics.getDeltaTime() * 0.1f;  // Simulating progress
            progressBar.setValue(progress);
        } else {
            game.setScreen(new GameScreen(game));
        }

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
        stage.dispose();
    }
}
