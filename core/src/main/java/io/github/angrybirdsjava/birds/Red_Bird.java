package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

//import static io.github.angrybirdsjava.pigs.Crown_Pig.world;

public class Red_Bird {
    private Texture red_bird;
    static private BodyDef bodyDef = new BodyDef();
    static private PolygonShape shape = new PolygonShape();
    static private FixtureDef fixtureDef = new FixtureDef();
    static private Body body;
    static private float ppm=13f;
    public Red_Bird() {
        red_bird=new Texture(Gdx.files.internal("birds/redbird.jpg"));
    }
    public Texture getRedBird() {
        return red_bird;
    }
    public static Body createbird(World world,float x, float y, float radius) {
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x/ppm, y/ppm);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius/ppm);

        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.5f; // Make it bouncy
        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }
}
