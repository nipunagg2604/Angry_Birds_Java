package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.physics.box2d.PolygonShape;

//import java.util.stream.GathererOp;

public class GameScreen implements Screen {
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button button;
    private World world;
    private BitmapFont font;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> rectangles_ver;
    private Array<Rectangle> rectangles_hor;
    private Array<Rectangle> base_objetcs;
    private Array<Rectangle> glass_blocks;
    private Texture wooden_ver;
    private Texture wooden_hor;
    private Texture glass_block;
    private Texture base;
    private Texture sling;
    private Texture red_bird;
    private Texture yellow_bird;
    private Texture black_bird;
    private Texture crown_pig;
    ShapeRenderer s=new ShapeRenderer();
    public GameScreen(final Core game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("background.jpg"));
        batch = new SpriteBatch();
        wooden_hor=new Texture(Gdx.files.internal("hor.jpg"));
        wooden_ver=new Texture(Gdx.files.internal("ver.jpg"));
        base=new Texture(Gdx.files.internal("base.png"));
        glass_block=new Texture(Gdx.files.internal("glass_block2.png"));
        sling=new Texture(Gdx.files.internal("sling2.png"));
        red_bird=new Texture(Gdx.files.internal("birds/redbird.png"));
        yellow_bird=new Texture(Gdx.files.internal("birds/yellow.png"));
        black_bird=new Texture(Gdx.files.internal("birds/black.png"));
        crown_pig=new Texture(Gdx.files.internal("pigs/crownpig.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);

        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = new Texture(Gdx.files.internal("pauseButton.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonDrawable;
        buttonStyle.down = buttonDrawable;
        mapLoader=new TmxMapLoader();
        tiledMap = mapLoader.load("level-1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        button = new Button(buttonStyle);
        button.setSize(50, 50);
        button.setPosition(2, 490 - button.getWidth());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button Clicked!");
                game.setScreen(new io.github.angrybirdsjava.PauseScreen(game, GameScreen.this));
            }
        });
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
        rectangles_ver=new Array<>();
        rectangles_hor=new Array<>();
        base_objetcs=new Array<>();
        glass_blocks=new Array<>();
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
        for (MapObject object: tiledMap.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            rectangles_hor.add(rect);
        }
        for (MapObject object: tiledMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            rectangles_ver.add(rect);
        }
        for (MapObject object: tiledMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            base_objetcs.add(rect);
        }
        for (MapObject object: tiledMap.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            glass_blocks.add(rect);
        }

        stage.addActor(button);
}
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        camera.update();
        renderer.setView(camera);
        renderer.render();
        b2dr.render(world,camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        font.draw(game.batch, "Mouse X: " + (int) mousePosition.x + ", Y: " + (496-(int) mousePosition.y), 10, 20);

        for (Rectangle rectangle: rectangles_ver) {
            game.batch.draw(wooden_ver, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        for (Rectangle rectangle: rectangles_hor) {
            game.batch.draw(wooden_hor, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        for (Rectangle rectangle: base_objetcs) {
            game.batch.draw(base, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        for (Rectangle rectangle: glass_blocks) {
            game.batch.draw(glass_block, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        game.batch.draw(sling,57,128,185,90);
        game.batch.draw(red_bird,87,130,30,30);
        game.batch.draw(yellow_bird,47,130,45,45);
        game.batch.draw(black_bird,17,130,35,35);
        game.batch.draw(crown_pig,778,280,30,30);
//        for (MapObject object: tiledMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect=((RectangleMapObject) object).getRectangle();
//            game.batch.draw(base, rect.getX(), rect.getY(),rect.getWidth(),rect.getHeight());
//        }

        game.batch.end();
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
