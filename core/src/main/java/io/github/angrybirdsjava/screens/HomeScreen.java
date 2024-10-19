package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.Core;

public class HomeScreen implements Screen {
    final Core game;
    int winwidth=Gdx.graphics.getWidth();
    int winheight=Gdx.graphics.getHeight();
    private OrthographicCamera camera;
    private Texture background;
    private Stage stage;
    ImageButton settingsButton;
    ImageButton playButtton;
    ImageButton text;
    ImageButton image;
    boolean issettingsopen;
    ImageButton help_button;
    ImageButton exit_button;
    ImageButton music_button;
    ImageButton sound_button;

    public HomeScreen(Core core) {
        this.game = core;
        this.camera = new OrthographicCamera();
        this.issettingsopen = false;
        camera.setToOrtho(false, 800, 450);
        this.background=new Texture(Gdx.files.internal("Homescreen/home_screen_bg.jpg"));
        this.stage = new Stage(new ScreenViewport());
        //stage.getViewport().setCamera(camera);
        this.settingsButton=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/setting_button.png"))));
        this.playButtton=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/play_button.png"))));
        this.text=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/text.png"))));
        this.image=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/image.png"))));
        this.help_button=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/help_button.png"))));
        this.music_button=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/music_button.png"))));
        this.sound_button=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/sound_button.png"))));
        this.exit_button=new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Homescreen/exit_button.png"))));
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void show() {

        this.settingsButton.setSize(60,60);
        this.exit_button.setSize(60,60);
        this.help_button.setSize(60,60);
        this.music_button.setSize(60,60);
        this.sound_button.setSize(60,60);
        this.playButtton.setSize(160,100);
        this.text.setSize(450,160);
        this.image.setSize(307,158);
        this.exit_button.setVisible(false);
        this.help_button.setVisible(false);
        this.music_button.setVisible(false);
        this.sound_button.setVisible(false);
        settingsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                issettingsopen=!issettingsopen;
                exit_button.setVisible(issettingsopen);
                sound_button.setVisible(issettingsopen);
                music_button.setVisible(issettingsopen);
                help_button.setVisible(issettingsopen);
//                game.setScreen(new LoadingScreen(game));
//                dispose();
            }
        });
        playButtton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LoadingScreen(game));
                dispose();
            }
        });
        settingsButton.setPosition((75 - settingsButton.getWidth()) / 2,
                (80 - settingsButton.getHeight()) / 2);
        exit_button.setPosition((settingsButton.getX())+80,
                (settingsButton.getY()));
        help_button.setPosition((settingsButton.getX())+160,
                (settingsButton.getY()));
        music_button.setPosition((settingsButton.getX())+240, settingsButton.getY());
        sound_button.setPosition((settingsButton.getX())+320, settingsButton.getY());
        playButtton.setPosition((400- (playButtton.getWidth()) / 2),
                160-(playButtton.getHeight()/ 2));
        text.setPosition((400 - (text.getWidth()) / 2),
                350-(text.getHeight()/ 2));
        image.setPosition((327 - (playButtton.getWidth()) / 2),
                120);
        stage.addActor(settingsButton);
        stage.addActor(image);
        stage.addActor(playButtton);
        stage.addActor(text);
        stage.addActor(help_button);
        stage.addActor(exit_button);
        stage.addActor(music_button);
        stage.addActor(sound_button);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 800, 450);
        game.batch.end();
        stage.draw();
        stage.act(delta); // Update the stage
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}
