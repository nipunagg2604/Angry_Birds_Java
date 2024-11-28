package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.screens.HomeScreen;

public class ThemeScreen implements Screen{
    private Texture background;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button backButton;
    private int height=Gdx.graphics.getHeight();
    private int width=Gdx.graphics.getWidth();
    private float bgX1;
    private float bgX2; // Positions for two background images
    private float bgSpeed = 50;

    public ThemeScreen(final Core game) {
        this.game = game;

        bgX1=0;
        bgX2=width;
        background = new Texture("Gamescreen/background.jpg");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        ImageButton imgb = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-1.png"))));
        ImageButton imgb2 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-2.png"))));
        ImageButton imgb3 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-3.png"))));
        ImageButton imgb4 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-4.png"))));
        ImageButton imgb5 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-5.png"))));

        imgb.setSize(400, 340);
        imgb2.setSize(400, 340);
        imgb3.setSize(400, 340);
        imgb4.setSize(400, 340);
        imgb5.setSize(400, 340);

        imgb.setPosition((300 - imgb.getWidth()) / 2, (590 - imgb.getHeight()) / 2);
        imgb2.setPosition((630 - imgb2.getWidth()) / 2, (590 - imgb2.getHeight()) / 2);
        imgb3.setPosition((960 - imgb3.getWidth()) / 2, (590 - imgb3.getHeight()) / 2);
        imgb4.setPosition((1290 - imgb4.getWidth()) / 2, (590 - imgb4.getHeight()) / 2);
        imgb5.setPosition((1620 - imgb5.getWidth()) / 2, (590 - imgb5.getHeight()) / 2);

        imgb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
                dispose();
            }
        });

        imgb2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
                dispose();
            }
        });

        imgb3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
                dispose();
            }
        });

        imgb4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
                dispose();
            }
        });

        imgb5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
                dispose();
            }
        });


        ImageButton imgb13 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("backbutton.png"))));

        imgb13.setSize(50, 50);
        imgb13.setPosition(4, camera.viewportHeight - imgb13.getWidth()-6);

        imgb13.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
                dispose();
            }
        });

        stage.addActor(imgb);
        stage.addActor(imgb2);
        stage.addActor(imgb3);
        stage.addActor(imgb4);
        stage.addActor(imgb5);
        stage.addActor(imgb13);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        bgX1 -= bgSpeed * delta;
        bgX2 -= bgSpeed * delta;

        if (bgX1 + width <= 0) {
            bgX1 = bgX2 + width;
        }
        if (bgX2 + width <= 0) {
            bgX2 = bgX1 +width;
        }

        game.batch.draw(background, bgX1, 0,width,height); // Draw first background
        game.batch.draw(background, bgX2, 0,width,height);

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
        background.dispose();
    }
}
