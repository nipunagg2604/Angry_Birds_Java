package io.github.angrybirdsjava;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import io.github.angrybirdsjava.screens.ContactDetect;
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
    private World world=new World(new Vector2(0, -10f), true);
    private ArrayList<Body> rectangles_ver=new Structures("wooden_vertical",world,6,0).return_array();
    private ArrayList<Body> rectangles_hor=new Structures("wooden_horizontal",world,6,0).return_array();;
    private ArrayList<Body> base_objetcs=new Structures("wooden_base",world,10,0).return_array();;
    private ArrayList<Body> glass_blocks=new Structures("glass_vertical",world,2,0).return_array();;
    private Texture pathpoint=new Texture("lightGrayDot.png");
    private Texture blackpoint=new Texture("trail.png");
    private ArrayList<Vector2> trajectory=new ArrayList();
    private ArrayList<Vector2> actualtrajectory=new ArrayList();
    private ArrayList<Vector2> actualvelocity=new ArrayList();
    private TextureRegion wooden_ver;
    private TextureRegion wooden_hor;
    private TextureRegion glass_block;
    private TextureRegion base;
    private TextureRegion crownpig;
    private TextureRegion redbird;
    private TextureRegion yellowbird;
    private TextureRegion blackbird;
    private int cnt2=0;
    private ArrayList<Vector2> array=new ArrayList();
    private boolean birdinactive=false;
    private Texture sling;
    private Texture slinghalf1;
    private Texture slinghalf2;
    private TextureRegion slingrubber;
    private int width=Gdx.graphics.getWidth();
    private int height=Gdx.graphics.getHeight();
    private BodyDef bodyDef = new BodyDef();
    private PolygonShape shape = new PolygonShape();
    private FixtureDef fixtureDef = new FixtureDef();
    private Body body;
    ShapeRenderer s=new ShapeRenderer();
//    private Red_Bird redbird;
//    private Black_Bird blackbird;
//    private Yellow_Bird yellowbird;
    private Body crown_pig;
    private Vector2 slinghalf1pos=new Vector2(182,210);
    private Vector2 slinghalf2pos=new Vector2(147,210);

    private static float ppm=Constants.ppm;
    private Body slingbody;
    private Body redbirdbody;
    private Body yellowirdbody;
    private Body blackbirdbody;
    private String currentbird="nul";
    private ContactDetect detect=new ContactDetect();
    private ArrayList<Body> birds=new ArrayList<>();
    private ArrayList<Boolean> inactive=new ArrayList<>();
    private Vector2 slingOrigin=new Vector2(114,203);
    private boolean isDragging;
    private boolean isLaunched;
    int cnt=1;
    private ArrayList<Vector2> calvel=new ArrayList<>();
    private Vector2 dragPositionglobal=new Vector2(103,190);

    public Level1Screen(final Core game) {
        this.game = game;
        background = new Texture("Gamescreen/background.jpg");
        crown_pig=Crown_Pig.addpig(world,793,305,15);
        batch = new SpriteBatch();
        wooden_hor=new TextureRegion(new Texture("Blocks/Wooden Blocks/horizontal_wood.png"));
        wooden_ver=new TextureRegion(new Texture("Blocks/Wooden Blocks/vertical_wood.png"));
        base=new TextureRegion(new Texture("Blocks/Wooden Blocks/wooden_base_type_2.png"));
        crownpig=new TextureRegion(new Texture("pigs/crownpig.jpg"));
        redbird=new TextureRegion(new Texture("birds/redbird.jpg"));
        yellowbird=new TextureRegion(new Texture("birds/yellow.jpg"));
        blackbird=new TextureRegion(new Texture("birds/black.png"));
        glass_block=new TextureRegion(new Texture("Blocks/Glass Blocks/glass_block_type_2.png"));
        sling=new Texture(Gdx.files.internal("Slings/slingshot2.png"));
        slinghalf1=new Texture(Gdx.files.internal("Slings/slinghalf1.png"));
        slinghalf2=new Texture(Gdx.files.internal("Slings/slinghalf2.png"));
        slingrubber=new TextureRegion(new Texture("Slings/rect.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        redbirdbody=(new Red_Bird()).createbird(world,114,203,15);
        blackbirdbody=(new Black_Bird()).createbird(world,60,160,17.5f);
        yellowirdbody=(new Yellow_Bird()).createbird(world,89,152,15f);


        birds.add(redbirdbody);
        birds.add(yellowirdbody);
        birds.add(blackbirdbody);
        inactive.add(false);
        inactive.add(false);
        world.setContactListener(detect);
        InputMultiplexer inputMultiplexer=new InputMultiplexer();
        stage = new Stage(new ScreenViewport(camera));
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new InputAdapter(){
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (!isLaunched) {
                    Body bird=birds.get(0);
                    isDragging = true;
                    Vector3 touchPos = new Vector3(screenX, screenY, 0);
                    camera.unproject(touchPos);

//                    // Limit the drag distance (optional)
                    Vector2 dragPosition = new Vector2(touchPos.x, touchPos.y);

                    if (dragPosition.dst(slingOrigin) >20) {
                        dragPosition.sub(slingOrigin).nor().scl(20).add(slingOrigin);
                    }

                    Vector2 dis=new Vector2(slingOrigin.cpy()).sub(dragPosition).scl(40);
                    bird.setType(BodyDef.BodyType.DynamicBody);
                    Vector2 velocity=dis.cpy().scl(1/bird.getMass());

                    bird.setType(BodyDef.BodyType.KinematicBody);
                    System.out.println("drag : "+dragPosition);
                    trajectory=calculateTrajectory(dragPosition,velocity,0.1f,100);
                    bird.setTransform((dragPosition.x)/ppm,(dragPosition.y)/ppm, 0);
                    float radius=((float)((ArrayList)(bird.getFixtureList().get(0).getUserData())).get(2));
                    dragPositionglobal=bird.getPosition().scl(ppm).cpy().sub(radius/1.5f,radius/1.5f);
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (isDragging && !isLaunched) {
                    isDragging = false;
                    isLaunched = true;

                    // Calculate launch force
                    Body bird=birds.get(0);
                    currentbird=bird.getFixtureList().get(0).getUserData().toString();
//                    System.out.println(currentbird);
                    Vector2 birdPosition = bird.getPosition();
                    birdPosition.x=(birdPosition.x)*ppm;
                    birdPosition.y=(birdPosition.y)*ppm;
//                    System.out.println("before : "+slingOrigin);
                    Vector2 v=slingOrigin.cpy();
//                    System.out.println("after : "+slingOrigin);

                    Vector2 launchForce = v.sub(birdPosition).scl(40).scl(1/ppm);
                    bird.setType(BodyDef.BodyType.DynamicBody);
                    bird.applyLinearImpulse(launchForce, bird.getWorldCenter(), true);
                }
                return true;
            }
        });

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
            bodyDef.position.set((rect.getX()+rect.getWidth()/2)/ppm, (rect.getY()+rect.getHeight()/2)/ppm);
            body=world.createBody(bodyDef);

            shape.setAsBox((rect.getWidth()/2)/ppm, (rect.getHeight()/2)/ppm);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits=Constants.BIT_GROUND;
            fixtureDef.filter.maskBits= (short) (Constants.BIT_BLOCKS | Constants.BIT_BIRD | Constants.BIT_SLING);
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
    public void checkbird(){
        if (birds.size()<1) {
            isLaunched=true;
            return;
        }
        Body bird=birds.get(0);
        Vector2 birdPosition = bird.getPosition();
        birdPosition.x=(birdPosition.x)*ppm;
        birdPosition.y=(birdPosition.y)*ppm;
        if (currentbird=="null"){
            return;
        }
        boolean isOffScreen = birdPosition.x < 0 || birdPosition.x > Gdx.graphics.getWidth()
                || birdPosition.y < 0 || birdPosition.y > Gdx.graphics.getHeight();

        if (!birdinactive){
            if (detect.hasBirdCollided() || isOffScreen) {
                bird=birds.get(0);
                ArrayList a=(ArrayList) bird.getFixtureList().get(0).getUserData();
                a.remove(0);
                a.add(0,"null");
                birds.remove(0);
                if (birds.size()!=0){
                    bird=birds.get(0);
                    bird.setTransform(114/ppm,203/ppm,0);
                }detect.birdCollided=false;
                currentbird="null";
                isLaunched=false;
                array.clear();
                actualtrajectory.clear();
                isDragging=false;
            }
        }

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Vector3 mousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        camera.update();
        renderer.setView(camera);
        renderer.render();
        world.step(1/50f, 6, 2);
        b2dr.render(world,camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Mouse X: " + (int) mousePosition.x + ", Y: " + (496-(int) mousePosition.y), 10, 20);
        for (Body rectangle : rectangles_ver) {
            String s=(String) (((ArrayList)(rectangle.getFixtureList().get(0).getUserData())).get(1));
            if (s.equals("false")) {continue;}
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(wooden_ver, (v.x)*ppm - a.get(2), (v.y)*ppm - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        for (Body rectangle : rectangles_hor) {
            String s=(String) (((ArrayList)(rectangle.getFixtureList().get(0).getUserData())).get(1));
            if (s.equals("false")) {continue;}
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(wooden_hor, (v.x)*ppm - a.get(2), (v.y)*ppm - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        for (Body rectangle : base_objetcs) {
            String s=(String) (((ArrayList)(rectangle.getFixtureList().get(0).getUserData())).get(1));
            if (s.equals("false")) {continue;}
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(base, (v.x)*ppm - a.get(2), (v.y)*ppm - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }
        for (Body rectangle : glass_blocks) {
            String s=(String) (((ArrayList)(rectangle.getFixtureList().get(0).getUserData())).get(1));
            if (s.equals("false")) {continue;}
            ArrayList<Float> a = new ArrayList();
            float angle = MathUtils.radiansToDegrees * rectangle.getAngle();
            a = (ArrayList<Float>) rectangle.getUserData();
            Vector2 v = new Vector2();
            v = (Vector2) rectangle.getPosition();
            batch.draw(glass_block, (v.x)*ppm - a.get(2), (v.y)*ppm - a.get(3), a.get(2), a.get(3), 2 * a.get(2), 2 * a.get(3), 1, 1, angle);
        }

//        batch.draw(sling,57,128,185,90);
        batch.draw(slinghalf1,157,128,35,110);


        slingbody=createsling(151,171,20,45);
        Vector2 v = (Vector2) crown_pig.getPosition();
        float angle = MathUtils.radiansToDegrees * crown_pig.getAngle();
        batch.draw(crownpig, (v.x)*ppm -15, (v.y)*ppm - 15, 15, 15, 30, 30, 1, 1, angle);
        float length = slinghalf1pos.cpy().dst(dragPositionglobal);
        float angleprint = MathUtils.atan2(dragPositionglobal.y - slinghalf1pos.cpy().y, dragPositionglobal.x - slinghalf1pos.cpy().x) * MathUtils.radiansToDegrees;;
//        if (angleprint>0){
//            angleprint = MathUtils.atan2(slinghalf1pos.cpy().y-dragPositionglobal.y ,  slinghalf1pos.cpy().x-dragPositionglobal.x ) * MathUtils.radiansToDegrees;
//        }
//        System.out.println(angleprint);
        if (dragPositionglobal.y > slinghalf1pos.y) {
            batch.draw(
                    slingrubber,
                    slinghalf1pos.x,
                    slinghalf1pos.y,
                    0,
                    slingrubber.getTexture().getHeight() / 2,
                    length,
                    slingrubber.getTexture().getHeight()/2,
                    1,
                    -1, // Flip vertically
                    angleprint
            );
        }else{
            batch.draw(
                    slingrubber,                 // The texture for the sling
                    slinghalf1pos.x,                 // X-coordinate of the anchor
                    slinghalf1pos.y,                 // Y-coordinate of the anchor
                    0,                            // Origin X (anchor point is the left edge)
                    slingrubber.getTexture().getHeight() / 2, // Origin Y (center vertically)
                    length,                       // Width (stretch the texture horizontally)
                    slingrubber.getTexture().getHeight()/2,     // Height (constant vertical size)
                    1,                            // Scale X
                    1,                            // Scale Y
                    angleprint                         // Rotation angle in degrees
            );
        }

        v=(Vector2) redbirdbody.getPosition();
        angle=MathUtils.radiansToDegrees * redbirdbody.getAngle();
        batch.draw(redbird, (v.x)*ppm-15 , (v.y)*ppm-15 , 15, 15, 30, 30, 1, 1, angle);
        if (isLaunched){
            ArrayList a=(ArrayList) redbirdbody.getFixtureList().get(0).getUserData();
            if (a.get(0)!="null") {
                actualtrajectory.add(v.cpy().scl(ppm));
                actualvelocity.add(redbirdbody.getLinearVelocity().cpy().scl(ppm));
            }
        }
//        if (isLaunched==false && currentbird.equals("null") && cnt==1) {
//            System.out.println("points : "+actualtrajectory);
//            System.out.println("calculated : "+trajectory);
//            System.out.println("actual velocity : "+actualvelocity);
//            System.out.println("cal velocity : "+calvel);
//            cnt++;
//        }
        for (Vector2 q:array){
            batch.draw(blackpoint,q.x,q.y,6f,6f);
        }
        for (Vector2 p: actualtrajectory) {
            cnt2++;

            if (cnt2%3!=0){

                continue;
            }array.add(p.cpy());

        }


        v=(Vector2) yellowirdbody.getPosition();
        angle=MathUtils.radiansToDegrees * yellowirdbody.getAngle();
        batch.draw(yellowbird, (v.x)*ppm -22.5f, (v.y)*ppm-22.5f , 15f, 15f, 45, 45, 1, 1, angle);

        if (isLaunched){
            ArrayList a=(ArrayList) yellowirdbody.getFixtureList().get(0).getUserData();
            if (a.get(0)!="null") {
                actualtrajectory.add(v.cpy().scl(ppm));
                actualvelocity.add(yellowirdbody.getLinearVelocity().cpy().scl(ppm));
            }
        }
        v=(Vector2) blackbirdbody.getPosition();
        angle=MathUtils.radiansToDegrees * blackbirdbody.getAngle();
        batch.draw(blackbird, (v.x)*ppm -25, (v.y)*ppm - 30, 17.5f, 17.5f, 35, 35, 1, 1, angle);

        if (isLaunched){
            ArrayList a=(ArrayList) blackbirdbody.getFixtureList().get(0).getUserData();
            if (a.get(0)!="null") {
                actualtrajectory.add(v.cpy().scl(ppm));
                actualvelocity.add(blackbirdbody.getLinearVelocity().cpy().scl(ppm));
            }
        }length = slinghalf2pos.cpy().dst(dragPositionglobal);
        angleprint = MathUtils.atan2(dragPositionglobal.y - slinghalf2pos.cpy().y, dragPositionglobal.x - slinghalf2pos.cpy().x) * MathUtils.radiansToDegrees;;
//        if (angleprint>0){
//            angleprint = MathUtils.atan2(slinghalf1pos.cpy().y-dragPositionglobal.y ,  slinghalf1pos.cpy().x-dragPositionglobal.x ) * MathUtils.radiansToDegrees;
//        }
        batch.draw(slinghalf2,137,174,30,65);
//        System.out.println(angleprint);
        if (dragPositionglobal.y > slinghalf2pos.y) {
            batch.draw(
                    slingrubber,
                    slinghalf2pos.x,
                    slinghalf2pos.y,
                    0,
                    slingrubber.getTexture().getHeight() / 2,
                    length,
                    slingrubber.getTexture().getHeight()/2,
                    1,
                    -1, // Flip vertically
                    angleprint
            );
        }else{
            batch.draw(
                    slingrubber,                 // The texture for the sling
                    slinghalf2pos.x,                 // X-coordinate of the anchor
                    slinghalf2pos.y,                 // Y-coordinate of the anchor
                    0,                            // Origin X (anchor point is the left edge)
                    slingrubber.getTexture().getHeight() / 2, // Origin Y (center vertically)
                    length,                       // Width (stretch the texture horizontally)
                    slingrubber.getTexture().getHeight()/2,     // Height (constant vertical size)
                    1,                            // Scale X
                    1,                            // Scale Y
                    angleprint                         // Rotation angle in degrees
            );
        }


        if (isDragging==true){

            for (Vector2 point : trajectory) {
                batch.draw(pathpoint, point.x, point.y, 6f, 6f); // Center and scale the dots
            }
        }
        checkbird();


//        for (MapObject object: tiledMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect=((RectangleMapObject) object).getRectangle();
//            batch.draw(base, rect.getX(), rect.getY(),rect.getWidth(),rect.getHeight());
//        }

        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    public Body createsling (int x,int y,float width,float height) {
        BodyDef baseDef = new BodyDef();
        baseDef.type = BodyDef.BodyType.StaticBody;
        baseDef.position.set(x/ppm, y/ppm); // Adjust position as needed
        Body base = world.createBody(baseDef);

        PolygonShape baseShape = new PolygonShape();
        baseShape.setAsBox(width/ppm, height/ppm); // Size of the base

        FixtureDef baseFixture = new FixtureDef();
        baseFixture.filter.maskBits = 0;
        baseFixture.shape = baseShape;
        baseFixture.density = 1f;
        baseFixture.friction = 0.5f;

        base.createFixture(baseFixture);
        baseShape.dispose();
        return base;
    }
    public ArrayList<Vector2> calculateTrajectory(Vector2 startPosition, Vector2 initialVelocity, float timeStep, int steps) {

        ArrayList<Vector2> trajectoryPoints = new ArrayList<>();
        calvel.clear();
        System.out.println("mm"+initialVelocity);
        float gravity = -10f * ppm; // Gravity in Box2D units
//        Vector2 copy=initialVelocity.cpy().scl(ppm);
//        System.out.println("copy"+copy);
        for (int i = 0; i < steps; i++) {
            float t = i * timeStep;

            // Calculate positions
            float x = startPosition.x + initialVelocity.x * t;
            float y = startPosition.y + ((initialVelocity.y * t) + (0.5f * gravity * t * t));

            float vx=initialVelocity.x;
            float vy=initialVelocity.y+gravity*t;

            calvel.add(new Vector2(vx,vy));
//            System.out.println("vel : "+new Vector2(vx,vy));
            trajectoryPoints.add(new Vector2(x, y));
        }



        return trajectoryPoints;
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