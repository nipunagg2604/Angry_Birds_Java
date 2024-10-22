package io.github.angrybirdsjava;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.screens.HomeScreen;

public class PauseScreen implements Screen{
    private Texture pauseMenu;
    private Texture blurBackground;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button button;
    private Button resumeButton;
    private Button restartButton;
    private Button exitButton;
    private ImageButton resume;

    public PauseScreen(final Core game, io.github.angrybirdsjava.GameScreen old) {
        this.game = game;

        blurBackground = new Texture(Gdx.files.internal("pauseBackground.png"));
        pauseMenu = new Texture(Gdx.files.internal("pauseMenu.jpeg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = new Texture(Gdx.files.internal("crossButton.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonDrawable;
        buttonStyle.down = buttonDrawable;


        button = new Button(buttonStyle);
        button.setSize(40, 40);
        button.setPosition(720 - button.getWidth(), 405 - button.getWidth());

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(old.getStage());
                game.setScreen(old);
                dispose();
            }
        });

        stage.addActor(button);


        Texture buttonTexture2 = new Texture(Gdx.files.internal("resumeButton.png"));
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(buttonTexture2);

        Button.ButtonStyle buttonStyle2 = new Button.ButtonStyle();
        buttonStyle2.up = buttonDrawable2;
        buttonStyle2.down = buttonDrawable2;

        resumeButton = new Button(buttonStyle2);
        resumeButton.setSize(40, 40);
        resumeButton.setPosition(335 - resumeButton.getWidth(), 355 - resumeButton.getWidth());

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(old.getStage());
                game.setScreen(old);
                dispose();
            }
        });

        stage.addActor(resumeButton);


        Texture buttonTexture3 = new Texture(Gdx.files.internal("restartButton.png"));
        TextureRegionDrawable buttonDrawable3 = new TextureRegionDrawable(buttonTexture3);

        Button.ButtonStyle buttonStyle3 = new Button.ButtonStyle();
        buttonStyle3.up = buttonDrawable3;
        buttonStyle3.down = buttonDrawable3;

        restartButton = new Button(buttonStyle3);
        restartButton.setSize(40, 40);
        restartButton.setPosition(335 - restartButton.getWidth(), 285 - restartButton.getWidth());

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
                old.dispose();
                dispose();
            }
        });

        stage.addActor(restartButton);


        Texture buttonTexture4 = new Texture(Gdx.files.internal("exitButton.png"));
        TextureRegionDrawable buttonDrawable4 = new TextureRegionDrawable(buttonTexture4);

        Button.ButtonStyle buttonStyle4 = new Button.ButtonStyle();
        buttonStyle4.up = buttonDrawable4;
        buttonStyle4.down = buttonDrawable4;

        exitButton = new Button(buttonStyle4);
        exitButton.setSize(40, 40);
        exitButton.setPosition(335 - exitButton.getWidth(), 220 - exitButton.getWidth());

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
                old.dispose();
                dispose();
            }
        });

        stage.addActor(exitButton);

        ImageButton imgb = new ImageButton(new TextureRegionDrawable(new Texture("resume.png")));
        imgb.setSize(120, 20);
        imgb.setPosition(resumeButton.getX() + 70, resumeButton.getY() + 10);
        stage.addActor(imgb);

        ImageButton imgb2 = new ImageButton(new TextureRegionDrawable(new Texture("restart.png")));
        imgb2.setSize(120, 20);
        imgb2.setPosition(restartButton.getX() + 70, restartButton.getY() + 10);
        stage.addActor(imgb2);

        ImageButton imgb3 = new ImageButton(new TextureRegionDrawable(new Texture("emm.png")));
        imgb3.setSize(150, 30);
        imgb3.setPosition(exitButton.getX() + 95, exitButton.getY() + 5);
        stage.addActor(imgb3);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(blurBackground, 0, 0, 960, 496);
        game.batch.draw(pauseMenu, 250, 130, 460, 270);  // Draw the background image (adjust size if needed)
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
        pauseMenu.dispose();
        blurBackground.dispose();
        stage.dispose();
    }
}