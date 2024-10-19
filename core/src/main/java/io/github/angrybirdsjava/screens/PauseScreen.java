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
        camera.setToOrtho(false, 800, 450);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = new Texture(Gdx.files.internal("crossButton.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonDrawable;
        buttonStyle.down = buttonDrawable;


        button = new Button(buttonStyle);
        button.setSize(40, 40);
        button.setPosition(612 - button.getWidth(), 360 - button.getWidth());

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(old.getStage());
                game.setScreen(old);
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
        resumeButton.setPosition(280 - resumeButton.getWidth(), 310 - resumeButton.getWidth());

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(old.getStage());
                game.setScreen(old);
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
        restartButton.setPosition(280 - restartButton.getWidth(), 247.5f - restartButton.getWidth());

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.GameScreen(game));
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
        exitButton.setPosition(280 - exitButton.getWidth(), 185 - exitButton.getWidth());

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(old.getStage());
                game.setScreen(old);
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
        game.batch.draw(blurBackground, 0, 0, 800, 450);
        game.batch.draw(pauseMenu, 200, 100, 400, 250);  // Draw the background image (adjust size if needed)
        //game.font.draw(game.batch, "Resume", 305, 296);
        //game.font.draw(game.batch, "Restart", 305, 234.5f);
        //game.font.draw(game.batch, "Exit to Main Menu", 305, 172);
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
        pauseMenu.dispose(); // Dispose pause menu texture
        blurBackground.dispose(); // Dispose blur background texture
        stage.dispose();
    }
}
