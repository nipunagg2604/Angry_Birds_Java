package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import io.github.angrybirdsjava.birds.Black_Bird;
import io.github.angrybirdsjava.birds.Red_Bird;
import io.github.angrybirdsjava.birds.Yellow_Bird;
import io.github.angrybirdsjava.blocks.Structures;
import io.github.angrybirdsjava.pigs.Crown_Pig;
import io.github.angrybirdsjava.screens.EndScreen;

import java.util.ArrayList;
import java.util.Vector;

//import java.util.stream.GathererOp;

public class Level1Screen implements Screen, InputProcessor {
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Core game;
    private Stage stage;
    private Button button;
    private BitmapFont font;
    private Box2DDebugRenderer b2dr;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private World world=new World(new Vector2(0, -10f), false);
    private ArrayList<Body> rectangles_ver=new Structures("wooden_vertical",world).return_array();
    private ArrayList<Body> rectangles_hor=new Structures("wooden_horizontal",world).return_array();;
    private ArrayList<Body> base_objetcs=new Structures("wooden_base",world).return_array();;
    private ArrayList<Body> glass_blocks=new Structures("glass_vertical",world).return_array();;
    private TextureRegion wooden_ver;
    private TextureRegion wooden_hor;
    private TextureRegion glass_block;
    private TextureRegion base;
    private TextureRegion crownpig;

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
    private Body crown_pig;
    public Level1Screen(final Core game) {
        this.game = game;
        background = new Texture("Gamescreen/background.jpg");
        redbird=new Red_Bird();
        blackbird=new Black_Bird();
        yellowbird=new Yellow_Bird();
        crown_pig=Crown_Pig.addpig(world,793,305,15);
        batch = new SpriteBatch();
        wooden_hor=new TextureRegion(new Texture("Blocks/Wooden Blocks/horizontal_wood.png"));
        wooden_ver=new TextureRegion(new Texture("Blocks/Wooden Blocks/vertical_wood.png"));
        base=new TextureRegion(new Texture("Blocks/Wooden Blocks/wooden_base_type_2.png"));
        crownpig=new TextureRegion(new Texture("pigs/crownpig.jpg"));
        glass_block=new TextureRegion(new Texture("Blocks/Glass Blocks/glass_block_type_2.png"));
        sling=new Texture(Gdx.files.internal("Slings/sling2.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);


        InputMultiplexer inputMultiplexer=new InputMultiplexer();
        stage = new Stage(new ScreenViewport(camera));
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);

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
                game.setScreen(new io.github.angrybirdsjava.PauseScreen(game, Level1Screen.this, getStage()));
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
        world.step(1 / 20f, 6, 2);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Mouse X: " + (int) mousePosition.x + ", Y: " + (496-(int) mousePosition.y), 10, 20);
        for (Body rectangle : rectangles_ver) {
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(wooden_ver, v.x - a.get(2), v.y - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        for (Body rectangle : rectangles_hor) {
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(wooden_hor, v.x - a.get(2), v.y - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        for (Body rectangle : base_objetcs) {
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(base, v.x - a.get(2), v.y - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        for (Body rectangle : glass_blocks) {
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(glass_block, v.x - a.get(2), v.y - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        batch.draw(sling,57,128,185,90);

        Vector2 v = (Vector2) crown_pig.getPosition();
        float angle = MathUtils.radiansToDegrees * crown_pig.getAngle();
        batch.draw(crownpig, v.x -15, v.y - 15, 15, 15, 30, 30, 1, 1, angle);
        batch.draw(redbird.getRedBird(),87,130,30,30);
        batch.draw(yellowbird.getyellowBird(),47,130,45,45);
        batch.draw(blackbird.getblackBird(),17,130,35,35);

        //Pigs
//        batch.draw(crown_pig.getcrownpig(),778,280,30,30);

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldcoor =camera.unproject(new Vector3(screenX,screenY,0));
        for (Body i: rectangles_ver) {
            for (Fixture f:i.getFixtureList()) {
                if (f.testPoint(worldcoor.x, worldcoor.y)) {
                    world.destroyBody(i);
                    rectangles_ver.remove(i);
                    return true;
                }
            }
        }
        for (Body i: rectangles_hor) {
            for (Fixture f:i.getFixtureList()) {
                if (f.testPoint(worldcoor.x, worldcoor.y)) {
                    world.destroyBody(i);
                    rectangles_hor.remove(i);
                    return true;
                }
            }
        }
        for (Body i: base_objetcs) {
            for (Fixture f:i.getFixtureList()) {
                if (f.testPoint(worldcoor.x, worldcoor.y)) {
                    world.destroyBody(i);
                    base_objetcs.remove(i);
                    return true;
                }
            }
        }
        for (Body i: glass_blocks) {
            for (Fixture f:i.getFixtureList()) {
                if (f.testPoint(worldcoor.x, worldcoor.y)) {
                    world.destroyBody(i);
                    glass_blocks.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}