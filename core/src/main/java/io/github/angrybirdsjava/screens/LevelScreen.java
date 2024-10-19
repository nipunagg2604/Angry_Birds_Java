package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelScreen implements Screen{
    private Texture background;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button backButton;

    public LevelScreen(final Core game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("levelBackground.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 450);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        ImageButton imgb = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level1.png"))));
        imgb.setSize(60, 60);
        imgb.setPosition(100, 380);

        stage.addActor(imgb);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 800, 450);
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
        stage.dispose();
    }
}
