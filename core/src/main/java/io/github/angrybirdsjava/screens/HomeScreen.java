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
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.Core;
import io.github.angrybirdsjava.LevelScreen;

public class HomeScreen implements Screen {
    final Core game;
    int width=Gdx.graphics.getWidth();
    int height=Gdx.graphics.getHeight();
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
    private float bgX1;
    private float bgX2; // Positions for two background images
    private float bgSpeed = 50;

    private Texture crosstexture=new Texture(Gdx.files.internal("cross.png"));


    public HomeScreen(Core core) {
        this.game = core;
        this.camera = new OrthographicCamera();
        this.issettingsopen = false;
        camera.setToOrtho(false, width, height);
        this.background=new Texture(Gdx.files.internal("Homescreen/home_screen_bg.jpg"));
        this.stage = new Stage(new ScreenViewport());
        bgX1=0;
        bgX2=width;
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

        Constants.music.play();
        Constants.music.setVolume(0.6f);



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

        music_button.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Constants.button_click.play(1f);
                if (!Constants.music.isPlaying()){
                    Constants.music.play();
                    Constants.music.setPosition(Constants.musicplay);
                }else{
                    Constants.musicplay =Constants.music.getPosition();
                    Constants.music.stop();
                }

            }
        });
        sound_button.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Constants.button_click.play(1f);
                Constants.isSound=!Constants.isSound;

            }
        });
        settingsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Constants.button_click.play(1f);
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
                Constants.button_click.play(1f);
                game.setScreen(new LevelScreen(game));
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
        playButtton.setPosition((472- (playButtton.getWidth()) / 2),
                180-(playButtton.getHeight()/ 2));
        text.setPosition((470 - (text.getWidth()) / 2),
                370-(text.getHeight()/ 2));
        image.setPosition((400 - (playButtton.getWidth()) / 2),
                140);
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
        bgX1 -= bgSpeed * delta;
        bgX2 -= bgSpeed * delta;

        if (bgX1 + width <= 0) {
            bgX1 = bgX2 + width;
        }
        if (bgX2 + width <= 0) {
            bgX2 = bgX1 +width;
        }
        game.batch.begin();

        game.batch.draw(background, bgX1, 0,width,height); // Draw first background
        game.batch.draw(background, bgX2, 0,width,height);

        game.batch.end();
        stage.draw();
        stage.act(delta);
        game.batch.begin();
        if (!Constants.music.isPlaying() && issettingsopen){
            game.batch.draw(crosstexture,settingsButton.getX()+240,settingsButton.getY(),60,60);
        }
        if (!Constants.isSound && issettingsopen){
            game.batch.draw(crosstexture,settingsButton.getX()+320,settingsButton.getY(),60,60);
        }
        game.batch.end();
    }

    public Stage getStage() {
        return stage;
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
