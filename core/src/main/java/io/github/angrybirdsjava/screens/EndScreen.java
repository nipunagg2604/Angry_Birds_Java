package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.Core;
import io.github.angrybirdsjava.Level1Screen;
import io.github.angrybirdsjava.ThemeScreen;

public class EndScreen implements Screen{
    private Texture endMenu;
    private Texture blurBackground;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private int cnt=1;
    private int starstotal;
    public EndScreen(final Core game,int total_score,int starscount,int level) {
        this.game = game;
        this.starstotal=starscount;
        blurBackground = new Texture(Gdx.files.internal("pauseBackground.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
        Image endmenu=new Image(new Texture(Gdx.files.internal("Endscreen/endscreen.jpg")));
        endmenu.setSize(500,350);
        endmenu.setPosition(220,80);
        stage.addActor(endmenu);

        ImageButton restartButton = new ImageButton(new TextureRegionDrawable(new Texture("Buttons/restartButton.png")));
        restartButton.setSize(60, 60);
        restartButton.setPosition(500 - restartButton.getWidth(), 180 - restartButton.getWidth());




        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level1Screen(game,0, true));
                dispose();
            }
        });

        stage.addActor(restartButton);

        ImageButton mainmenu = new ImageButton(new TextureRegionDrawable(new Texture("Buttons/mainmenu.png")));
        mainmenu.setSize(60, 60);
        mainmenu.setPosition(365 - restartButton.getWidth(), 180 - restartButton.getWidth());

        mainmenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ThemeScreen(game));
                dispose();
            }
        });

        stage.addActor(mainmenu);

        ImageButton nextLevel = new ImageButton(new TextureRegionDrawable(new Texture("nextLevel.png")));
        nextLevel.setSize(60, 60);
        nextLevel.setPosition(635 - restartButton.getWidth(), 180 - restartButton.getWidth());
        nextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (level==1){
                    game.setScreen(new Level1Screen(game,0, true));
                }else if (level==2){
                    game.setScreen(new Level1Screen(game,0, true));
                }
                dispose();
            }
        });
        stage.addActor(nextLevel);
        Image leftempty=new Image(new TextureRegionDrawable(new Texture("EndScreen/emtpyleft.png")));
        leftempty.setSize(80, 80);
        leftempty.setPosition(310,210);
        stage.addActor(leftempty);
        Image middleempty=new Image(new TextureRegionDrawable(new Texture("EndScreen/emptymiddle.png")));
        middleempty.setSize(80, 80);
        middleempty.setPosition(430,230);
        stage.addActor(middleempty);
        Image rightempty=new Image(new TextureRegionDrawable(new Texture("EndScreen/emptyright.png")));
        rightempty.setSize(80, 80);
        rightempty.setPosition(550,210);
        stage.addActor(rightempty);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Constants.score_black_font;
        Label Gameover=new Label("GAME OVER",labelStyle);
        Gameover.setPosition(350, 335);
        stage.addActor(Gameover);

    }
    public void showstars(int starstotal){
        Image leftstar=new Image(new TextureRegionDrawable(new Texture("EndScreen/leftstar.png")));
        Image middlestar=new Image(new TextureRegionDrawable(new Texture("EndScreen/middlestar.png")));
        Image rightstar=new Image(new TextureRegionDrawable(new Texture("EndScreen/rightstar.png")));


        stage.addAction(Actions.sequence(
                Actions.delay(2f),
                Actions.scaleBy(0.1f,0.1f,2f)

        ));
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
        if (cnt==5){
            showstars(starstotal);
            System.out.println("hellow");
            cnt++;
        }
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        cnt++;
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
        blurBackground.dispose();
        stage.dispose();
    }
}
