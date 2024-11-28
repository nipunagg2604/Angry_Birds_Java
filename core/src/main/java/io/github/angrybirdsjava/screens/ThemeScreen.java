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
    private float lastTouchX=0;
    private float start=199;
    private float end=300;

    private float gap=230;

    private float buttonwidth=400;
    private float buttonheight=280;

    ImageButton imgb;
    ImageButton imgb2;
    ImageButton imgb3;
    ImageButton imgb4;
    ImageButton imgb5;
    ImageButton imgb6;
    ImageButton imgb7;
    ImageButton imgb8;
    ImageButton imgb9;
    ImageButton imgb10;
    ImageButton imgb11;
    ImageButton imgb12;

    public ThemeScreen(final Core game) {
        this.game = game;

        bgX1=0;
        bgX2=width;
        background = new Texture("bgg.jpg");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        imgb = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-1.png"))));
        imgb2 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-2.png"))));
        imgb3 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-3.png"))));
        imgb4 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-4.png"))));
        imgb5 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-5.png"))));
        imgb6 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-6.png"))));
        imgb7 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-7.png"))));
        imgb8 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-8.png"))));
        imgb9 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-9.png"))));
        imgb10 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-10.png"))));
        imgb11 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-11.png"))));
        imgb12 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Themes/Theme-12.png"))));


        imgb.setSize(buttonwidth,buttonheight);
        imgb2.setSize(buttonwidth,buttonheight);
        imgb3.setSize(buttonwidth,buttonheight);
        imgb4.setSize(buttonwidth,buttonheight);
        imgb5.setSize(buttonwidth,buttonheight);
        imgb6.setSize(buttonwidth,buttonheight);
        imgb7.setSize(buttonwidth,buttonheight);
        imgb8.setSize(buttonwidth,buttonheight);
        imgb9.setSize(buttonwidth,buttonheight);
        imgb10.setSize(buttonwidth,buttonheight);
        imgb11.setSize(buttonwidth,buttonheight);
        imgb12.setSize(buttonwidth,buttonheight);

        imgb.setPosition((start - imgb.getWidth()) / 2, (570 - buttonheight) / 2);
        imgb2.setPosition((start+gap - imgb2.getWidth()) / 2, (570 - buttonheight) / 2);
        imgb3.setPosition((start+2*gap - imgb3.getWidth()) / 2, (570 - buttonheight)/2);
        imgb4.setPosition((start+3*gap - imgb4.getWidth()) / 2, (570 - buttonheight) / 2);
        imgb5.setPosition((start+4*gap - imgb5.getWidth()) / 2, (570 - imgb5.getHeight()) / 2);
        imgb6.setPosition((start+5*gap - imgb6.getWidth()) / 2, (570 - imgb6.getHeight()) / 2);
        imgb7.setPosition((start+6*gap - imgb7.getWidth()) / 2, (570 - imgb7.getHeight()) / 2);
        imgb8.setPosition((start+7*gap - imgb8.getWidth()) / 2, (570 - imgb8.getHeight()) / 2);
        imgb9.setPosition((start+8*gap - imgb9.getWidth()) / 2, (570 - imgb9.getHeight()) / 2);
        imgb10.setPosition((start+9*gap - imgb10.getWidth()) / 2, (570 - imgb10.getHeight()) / 2);
        imgb11.setPosition((start+10*gap - imgb11.getWidth()) / 2, (570 - imgb11.getHeight()) / 2);
        imgb12.setPosition((start+11*gap - imgb12.getWidth()) / 2, (570 - imgb12.getHeight()) / 2);


//        imgb.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
//                dispose();
//            }
//        });
//
//        imgb2.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
//                dispose();
//            }
//        });
//
//        imgb3.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
//                dispose();
//            }
//        });
//
//        imgb4.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
//                dispose();
//            }
//        });
//
//        imgb5.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game));
//                dispose();
//            }
//        });


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
        stage.addActor(imgb6);
        stage.addActor(imgb7);
        stage.addActor(imgb8);
        stage.addActor(imgb9);
        stage.addActor(imgb10);
        stage.addActor(imgb11);
        stage.addActor(imgb12);
        stage.addActor(imgb13);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();

            if (lastTouchX != 0) {
                float d = (touchX - lastTouchX)*1.5f;

                // Scroll objects based on drag direction
                start+=d;
                if (start>=199) start=199;
                if (start<=-780) start=-780;
                imgb.setPosition((start - imgb.getWidth()) / 2, (570 - imgb.getHeight()) / 2);
                imgb2.setPosition((start+gap - imgb2.getWidth()) / 2, (570 - imgb2.getHeight()) / 2);
                imgb3.setPosition((start+2*gap - imgb3.getWidth()) / 2, (570 - imgb3.getHeight()) / 2);
                imgb4.setPosition((start+3*gap - imgb4.getWidth()) / 2, (570 - imgb4.getHeight()) / 2);
                imgb5.setPosition((start+4*gap - imgb5.getWidth()) / 2, (570 - imgb5.getHeight()) / 2);
                imgb6.setPosition((start+5*gap - imgb6.getWidth()) / 2, (570 - imgb6.getHeight()) / 2);
                imgb7.setPosition((start+6*gap - imgb7.getWidth()) / 2, (570 - imgb7.getHeight()) / 2);
                imgb8.setPosition((start+7*gap - imgb8.getWidth()) / 2, (570 - imgb8.getHeight()) / 2);
                imgb9.setPosition((start+8*gap - imgb9.getWidth()) / 2, (570 - imgb9.getHeight()) / 2);
                imgb10.setPosition((start+9*gap - imgb10.getWidth()) / 2, (570 - imgb10.getHeight()) / 2);
                imgb11.setPosition((start+10*gap - imgb11.getWidth()) / 2, (570 - imgb11.getHeight()) / 2);
                imgb12.setPosition((start+11*gap - imgb12.getWidth()) / 2, (570 - imgb12.getHeight()) / 2);


            }

            lastTouchX = touchX; // Update last touch position
        } else {
            lastTouchX = 0; // Reset when touch ends
        }

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
