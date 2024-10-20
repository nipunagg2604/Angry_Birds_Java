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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.screens.HomeScreen;

public class LevelScreen implements Screen{
    private Texture background;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button backButton;

    public LevelScreen(final Core game, HomeScreen home) {
        this.game = game;

        background = new Texture(Gdx.files.internal("levelBackground.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        root.defaults().spaceBottom(50);
        root.defaults().spaceTop(50);
        root.defaults().spaceRight(120);
        root.defaults().spaceLeft(120);
        stage.addActor(root);

        ImageButton imgb = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level1.png"))));
        imgb.setSize(100, 100);
        imgb.setPosition(90, 280);

        imgb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb);

        ImageButton imgb2 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level2.png"))));
        imgb2.setSize(100, 100);
        imgb2.setPosition(270, 280);

        imgb2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb2);

        ImageButton imgb3 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level3.png"))));
        imgb3.setSize(100, 100);
        imgb3.setPosition(450, 280);

        imgb3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb3);

        ImageButton imgb4 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level4.png"))));
        imgb4.setSize(100, 100);
        imgb4.setPosition(450, 280);

        imgb4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb4);

        root.row();
        ImageButton imgb5 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level5.png"))));
        imgb5.setSize(100, 100);
        imgb5.setPosition(90, 280);

        imgb5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb5);

        ImageButton imgb6 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level6.png"))));
        imgb6.setSize(100, 100);
        imgb6.setPosition(270, 280);

        imgb6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb6);

        ImageButton imgb7 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level7.png"))));
        imgb7.setSize(100, 100);
        imgb7.setPosition(450, 280);

        imgb7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb7);

        ImageButton imgb8 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level8.png"))));
        imgb8.setSize(100, 100);
        imgb8.setPosition(450, 280);

        imgb8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb8);

        root.row();
        ImageButton imgb9 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("level9.png"))));
        imgb9.setSize(100, 100);
        imgb9.setPosition(90, 280);

        imgb9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                dispose();
            }
        });

        root.add(imgb9);

        ImageButton imgb10 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("levelLocked.png"))));
        imgb10.setSize(100, 100);
        imgb10.setPosition(270, 280);

        root.add(imgb10);

        ImageButton imgb11 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("levelLocked.png"))));
        imgb11.setSize(100, 100);
        imgb11.setPosition(450, 280);

        root.add(imgb11);

        ImageButton imgb12 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("levelLocked.png"))));
        imgb12.setSize(100, 100);
        imgb12.setPosition(450, 280);

        root.add(imgb12);

        ImageButton imgb13 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("restartButton.png"))));

        imgb13.setSize(50, 50);
        imgb13.setPosition(2, camera.viewportHeight - imgb13.getWidth());

        imgb13.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
                dispose();
            }
        });

        stage.addActor(imgb13);
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
