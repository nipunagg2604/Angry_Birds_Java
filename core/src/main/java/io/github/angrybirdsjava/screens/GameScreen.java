package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GameScreen implements Screen {
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private final Core game;
    private Stage stage;
    private Button button;
    private World world;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    public GameScreen(final Core game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("background.jpg"));
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 496);


//        for (MapObject object: tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect=((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
//            body=world.createBody(bodyDef);
//
//            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//        }



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
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

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
}
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        renderer.setView(camera);
        renderer.render();
//        b2dr.render(world,camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        //game.batch.draw(background, 0, 0, 800, 450);
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
