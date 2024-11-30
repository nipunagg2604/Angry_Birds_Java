package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.screens.HomeScreen;

public class PauseScreen implements Screen{
    private Texture blurBackground;
    private OrthographicCamera camera;
    private final Core game;
    public Stage stage;
    private Button button;
    private Button resumeButton;
    private Button restartButton;
    private Button exitButton;
    private Button saveButton;
    private ImageButton resume;

    public PauseScreen(final Core game, Screen old, InputMultiplexer inp, String filename) {
        this.game = game;

        blurBackground = new Texture(Gdx.files.internal("pauseBackground.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Image pauseMenu = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("pauseMenu.jpeg"))));
        pauseMenu.setSize(460, 270);
        pauseMenu.setPosition(250, 130);
        stage.addActor(pauseMenu);

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
                io.github.angrybirdsjava.Level1Screen obj = ((io.github.angrybirdsjava.Level1Screen) old);
                obj.saveData();
                obj.world.dispose();
                obj.dispose();
                Gdx.input.setInputProcessor(inp);
                game.setScreen(new io.github.angrybirdsjava.LevelScreen(game, 0));
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
        resumeButton.setSize(33, 33);
        resumeButton.setPosition(328 - resumeButton.getWidth(), 366 - resumeButton.getWidth());

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(inp);
                io.github.angrybirdsjava.Level1Screen obj = null;
                io.github.angrybirdsjava.Level2Screen obj2 = null;
                io.github.angrybirdsjava.Level3Screen obj3 = null;
                if(old instanceof io.github.angrybirdsjava.Level1Screen) {
                    obj = ((io.github.angrybirdsjava.Level1Screen) old);
                    obj.ispause=false;
                }
                else if(old instanceof io.github.angrybirdsjava.Level2Screen) {
                    obj2 = ((io.github.angrybirdsjava.Level2Screen) old);
                    obj2.ispause=false;
                }
                else {
                    obj3 = ((io.github.angrybirdsjava.Level3Screen) old);
                    obj3.ispause=false;
                }
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
        restartButton.setSize(33, 33);
        restartButton.setPosition(328 - restartButton.getWidth(), 310 - restartButton.getWidth());

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(old instanceof io.github.angrybirdsjava.Level1Screen) game.setScreen(new io.github.angrybirdsjava.Level1Screen(game,0, true, filename));
                else if(old instanceof io.github.angrybirdsjava.Level2Screen) game.setScreen(new io.github.angrybirdsjava.Level2Screen(game,0, true, filename));
                else game.setScreen(new io.github.angrybirdsjava.Level3Screen(game,0, true, filename));
                old.dispose();
                dispose();
            }
        });

        stage.addActor(restartButton);


        Texture buttonTexture4 = new Texture(Gdx.files.internal("Buttons/mainmenu.png"));
        TextureRegionDrawable buttonDrawable4 = new TextureRegionDrawable(buttonTexture4);

        Button.ButtonStyle buttonStyle4 = new Button.ButtonStyle();
        buttonStyle4.up = buttonDrawable4;
        buttonStyle4.down = buttonDrawable4;

        exitButton = new Button(buttonStyle4);
        exitButton.setSize(33, 33);
        exitButton.setPosition(328 - exitButton.getWidth(), 254 - exitButton.getWidth());

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.ThemeScreen(game, 0));
                old.dispose();
                dispose();
            }
        });

        stage.addActor(exitButton);

        Texture buttonTexture5 = new Texture(Gdx.files.internal("Buttons/save.png"));
        TextureRegionDrawable buttonDrawable5 = new TextureRegionDrawable(buttonTexture5);

        Button.ButtonStyle buttonStyle5 = new Button.ButtonStyle();
        buttonStyle5.up = buttonDrawable5;
        buttonStyle5.down = buttonDrawable5;

        saveButton = new Button(buttonStyle5);
        saveButton.setSize(33, 33);
        saveButton.setPosition(328 - saveButton.getWidth(), 200 - saveButton.getWidth());

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                io.github.angrybirdsjava.Level1Screen obj = null;
                io.github.angrybirdsjava.Level2Screen obj2 = null;
                io.github.angrybirdsjava.Level3Screen obj3 = null;
                if(old instanceof io.github.angrybirdsjava.Level1Screen) {
                    obj = ((io.github.angrybirdsjava.Level1Screen) old);
                    obj.saveData();
                    obj.dispose();
                }
                else if(old instanceof io.github.angrybirdsjava.Level2Screen) {
                    obj2 = ((io.github.angrybirdsjava.Level2Screen) old);
                    obj2.saveData();
                    obj2.dispose();
                }
                else {
                    obj3 = ((io.github.angrybirdsjava.Level3Screen) old);
                    obj3.saveData();
                    obj3.dispose();
                }
                Gdx.input.setInputProcessor(inp);
                game.setScreen(new io.github.angrybirdsjava.ThemeScreen(game, 0));
                dispose();
            }
        });
        stage.addActor(saveButton);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Constants.pause_font;
        Label resume=new Label("RESUME",labelStyle);
        resume.setPosition(resumeButton.getX() + 70, resumeButton.getY() + 7);
        stage.addActor(resume);

        Label restart=new Label("RESTART",labelStyle);
        restart.setPosition(restartButton.getX() + 70, restartButton.getY() + 7);
        stage.addActor(restart);

        Label emm=new Label("EXIT TO MAIN MENU",labelStyle);
        emm.setPosition(exitButton.getX() + 70, exitButton.getY() + 7);
        stage.addActor(emm);

        Label save=new Label("SAVE GAME",labelStyle);
        save.setPosition(saveButton.getX() + 70, saveButton.getY() + 7);
        stage.addActor(save);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(blurBackground, 0, 0, 960, 496);
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
        //pauseMenu.dispose();
        blurBackground.dispose();
        stage.dispose();
    }
}
