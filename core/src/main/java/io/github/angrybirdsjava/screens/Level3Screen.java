package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import io.github.angrybirdsjava.Core;
import io.github.angrybirdsjava.birds.Black_Bird;
import io.github.angrybirdsjava.birds.Yellow_Bird;
import io.github.angrybirdsjava.blocks.Structures;
import io.github.angrybirdsjava.pigs.Crown_Pig;
import io.github.angrybirdsjava.screens.EndScreen;
import io.github.angrybirdsjava.birds.Red_Bird;

import java.util.ArrayList;

public class Level3Screen implements Screen{
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Core game;
    private Stage stage;
    private Button button;
    private BitmapFont font;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private World world=new World(new Vector2(0, -9.8f), true);
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
//    private ArrayList<Body> rectangles_ver=new Structures("wooden_vertical",world).return_array();
//    private ArrayList<Body> rectangles_hor=new Structures("wooden_horizontal",world).return_array();;
//    private ArrayList<Body> base_objetcs=new Structures("wooden_base",world).return_array();;
//    private ArrayList<Body> glass_blocks=new Structures("glass_vertical",world).return_array();;
    private Texture wooden_ver;
    private Texture wooden_hor;
    private Texture glass_block;
    private Texture base;
    private Texture sling;
    private int width=Gdx.graphics.getWidth();
    private int height=Gdx.graphics.getHeight();
    private BodyDef bodyDef = new BodyDef();
    private PolygonShape shape = new PolygonShape();
    private FixtureDef fixtureDef = new FixtureDef();
    private Body body;
    ShapeRenderer s=new ShapeRenderer();
    private Red_Bird redbird;
    private Black_Bird blackbird;
    private Yellow_Bird yellowbird;
    private Crown_Pig crown_pig;
    public Level3Screen(final Core game) {
        this.game = game;
        background = new Texture("Gamescreen/background.jpg");
        redbird=new Red_Bird();
        blackbird=new Black_Bird();
        yellowbird=new Yellow_Bird();
//        crown_pig=new Crown_Pig();
        batch = new SpriteBatch();
        wooden_hor=new Texture(Gdx.files.internal("Blocks/Wooden Blocks/horiontal_wood.png"));
        wooden_ver=new Texture(Gdx.files.internal("Blocks/Wooden Blocks/vertical_wood.png"));
        base=new Texture(Gdx.files.internal("Blocks/Wooden Blocks/wooden_base_type_2.png"));
        glass_block=new Texture(Gdx.files.internal("Blocks/Glass Blocks/glass_block_type_2.png"));
        sling=new Texture(Gdx.files.internal("Slings/sling2.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = new Texture(Gdx.files.internal("pauseButton.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonDrawable;
        buttonStyle.down = buttonDrawable;
        mapLoader=new TmxMapLoader();
        tiledMap = mapLoader.load("Level_tmx_files/level-1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        button = new Button(buttonStyle);
        button.setSize(50, 50);
        button.setPosition(2, 490 - button.getWidth());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button Clicked!");
                game.setScreen(new io.github.angrybirdsjava.PauseScreen(game, Level3Screen.this, getStage()));
            }
        });

        b2dr = new Box2DDebugRenderer();

        font=new BitmapFont();
        for (MapObject object: tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        stage.addActor(button);

        ImageButton end = new ImageButton(new TextureRegionDrawable(new Texture("nextLevel.png")));
        end.setSize(50, 50);
        end.setPosition(905, 440);

        end.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new EndScreen(game));
                dispose();
            }
        });

        stage.addActor(end);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        camera.update();
        renderer.setView(camera);
        renderer.render();
        b2dr.render(world,camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Mouse X: " + (int) mousePosition.x + ", Y: " + (496-(int) mousePosition.y), 10, 20);

//        for (Rectangle rectangle: rectangles_ver) {
//            batch.draw(wooden_ver, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
//        }
//        for (Rectangle rectangle: rectangles_hor) {
//            batch.draw(wooden_hor, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
//        }
//        for (Rectangle rectangle: base_objetcs) {
//            batch.draw(base, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
//        }
//        for (Rectangle rectangle: glass_blocks) {
//            batch.draw(glass_block, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
//        }
        batch.draw(sling,57,128,185,90);

        //Birds
        batch.draw(redbird.getRedBird(),87,130,30,30);
//        batch.draw(yellowbird.getyellowBird(),47,130,45,45);
//        batch.draw(blackbird.getblackBird(),17,130,35,35);

        //Pigs
        batch.draw(crown_pig.getcrownpig(),778,280,30,30);

//        for (MapObject object: tiledMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect=((RectangleMapObject) object).getRectangle();
//            batch.draw(base, rect.getX(), rect.getY(),rect.getWidth(),rect.getHeight());
//        }

        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public Stage getStage() {
        return stage;
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
        background.dispose();
        batch.dispose();
        stage.dispose();
        tiledMap.dispose();
        world.dispose();
        b2dr.dispose();
    }
}
