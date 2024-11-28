package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.angrybirdsjava.screens.HomeScreen;

import java.io.File;
import java.util.ArrayList;

public class LevelScreen implements Screen{
    private BitmapFont font;
    private Texture background;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button backButton;
    private int height=Gdx.graphics.getHeight();
    private int width=Gdx.graphics.getWidth();
    private float bgX1;
    private float bgX2; // Positions for two background images
    private float bgSpeed = 30;
    private ArrayList<ImageButton> stars1, stars2, stars3;
    public int cnt=1;
    public LevelScreen(final Core game) {
        font=new BitmapFont();
        this.game = game;
        this.bgX1=0;
        this.bgX2=width;
        background = new Texture(Gdx.files.internal("bgg.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);


        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        stars1 = new ArrayList<>();
        stars1.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars0.png")))));
        stars1.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars1.png")))));
        stars1.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars2.png")))));
        stars1.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars3.png")))));

        for(int i=0; i<4; i++) {
            stars1.get(i).setSize(70, 16);
            stars1.get(i).setPosition(55, 388);
        }

        stars2 = new ArrayList<>();
        stars2.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars0.png")))));
        stars2.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars1.png")))));
        stars2.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars2.png")))));
        stars2.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars3.png")))));

        for(int i=0; i<4; i++) {
            stars2.get(i).setSize(70, 16);
            stars2.get(i).setPosition(182, 388);
        }

        stars3 = new ArrayList<>();
        stars3.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars0.png")))));
        stars3.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars1.png")))));
        stars3.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars2.png")))));
        stars3.add(new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Stars/stars3.png")))));

        for(int i=0; i<4; i++) {
            stars3.get(i).setSize(70, 16);
            stars3.get(i).setPosition(310, 388);
        }


        ImageButton imgb = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/1.png"))));
        ImageButton imgb2 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/2.png"))));
        ImageButton imgb3 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/3.png"))));
        ImageButton imgb4 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb5 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb6 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb7 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb8 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb9 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb10 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb11 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb12 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb13 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb14 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb15 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb16 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb17 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb18 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb19 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb20 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));
        ImageButton imgb21 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Level Buttons/locklevel.png"))));

        int SIZE = 70;
        imgb.setSize(80, 80);
        imgb2.setSize(80, 80);
        imgb3.setSize(80, 80);
        imgb4.setSize(SIZE, SIZE);
        imgb5.setSize(SIZE, SIZE);
        imgb6.setSize(SIZE, SIZE);
        imgb7.setSize(SIZE, SIZE);
        imgb8.setSize(SIZE, SIZE);
        imgb9.setSize(SIZE, SIZE);
        imgb10.setSize(SIZE, SIZE);
        imgb11.setSize(SIZE, SIZE);
        imgb12.setSize(SIZE, SIZE);
        imgb13.setSize(SIZE, SIZE);
        imgb14.setSize(SIZE, SIZE);
        imgb15.setSize(SIZE, SIZE);
        imgb16.setSize(SIZE, SIZE);
        imgb17.setSize(SIZE, SIZE);
        imgb18.setSize(SIZE, SIZE);
        imgb19.setSize(SIZE, SIZE);
        imgb20.setSize(SIZE, SIZE);
        imgb21.setSize(SIZE, SIZE);

        int base1 = 180;
        float buttonWidth = imgb7.getWidth(), buttonHeight = imgb7.getHeight();
        int h1 = 860, h2 = 620, h3 = 380;
        int gap = 255;
        imgb.setPosition((base1 + 0*gap - imgb.getWidth()) / 2, (850 - imgb.getHeight()) / 2);
        imgb2.setPosition((base1 + 1*gap - imgb2.getWidth()) / 2, (850 - imgb2.getHeight()) / 2);
        imgb3.setPosition((base1 + 2*gap - imgb3.getWidth()) / 2, (850 - imgb3.getHeight()) / 2);
        imgb4.setPosition((base1 + 3*gap - imgb4.getWidth()) / 2, (h1 - imgb4.getHeight()) / 2);
        imgb5.setPosition((base1 + 4*gap - imgb5.getWidth()) / 2, (h1 - imgb5.getHeight()) / 2);
        imgb6.setPosition((base1 + 5*gap - imgb6.getWidth()) / 2, (h1 - imgb6.getHeight()) / 2);
        imgb7.setPosition((base1 + 6*gap - buttonWidth) / 2, (h1 - buttonHeight) / 2);
        imgb8.setPosition((base1 + 0*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb9.setPosition((base1 + 1*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb10.setPosition((base1 + 2*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb11.setPosition((base1 + 3*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb12.setPosition((base1 + 4*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb13.setPosition((base1 + 5*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb14.setPosition((base1 + 6*gap - buttonWidth) / 2, (h2 - buttonHeight) / 2);
        imgb15.setPosition((base1 + 0*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);
        imgb16.setPosition((base1 + 1*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);
        imgb17.setPosition((base1 + 2*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);
        imgb18.setPosition((base1 + 3*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);
        imgb19.setPosition((base1 + 4*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);
        imgb20.setPosition((base1 + 5*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);
        imgb21.setPosition((base1 + 6*gap - buttonWidth) / 2, (h3 - buttonHeight) / 2);

        imgb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                io.github.angrybirdsjava.Level1Screen obj;
                File file = new File("data.txt");
                if(file.exists() && file.length() > 0) {
                    obj = new io.github.angrybirdsjava.Level1Screen(game, 0, false);
                    obj.loadData();
                }
                else obj = new io.github.angrybirdsjava.Level1Screen(game, 0, true);
                game.setScreen(obj);
                //game.setScreen(new io.github.angrybirdsjava.Level1Screen(game,0, true));
                dispose();
            }
        });

        imgb2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game,0, true));
                dispose();
            }
        });

        imgb3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new io.github.angrybirdsjava.Level1Screen(game,0, true));
                dispose();
            }
        });



        ImageButton imgb22 = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("backbutton.png"))));

        imgb22.setSize(50, 50);
        imgb22.setPosition(4, camera.viewportHeight - imgb22.getWidth()-6);

        imgb22.addListener(new ClickListener() {
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
        stage.addActor(imgb14);
        stage.addActor(imgb15);
        stage.addActor(imgb16);
        stage.addActor(imgb17);
        stage.addActor(imgb18);
        stage.addActor(imgb19);
        stage.addActor(imgb20);
        stage.addActor(imgb21);
        stage.addActor(stars1.get(2));
        stage.addActor(stars2.get(2));
        stage.addActor(stars3.get(2));
        //stage.addActor(imgb22);
    }

    @Override
    public void render(float delta) {
        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        Constants.pig_font.draw(game.batch, "Mouse X: " + (int) mousePosition.x + ", Y: " + (496-(int) mousePosition.y), 10, 20);

        game.batch.draw(background, bgX1, 0,width,height); // Draw first background
        game.batch.draw(background, bgX2, 0,width,height);
//        game.batch.draw(background, 0, 0, width, height);
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