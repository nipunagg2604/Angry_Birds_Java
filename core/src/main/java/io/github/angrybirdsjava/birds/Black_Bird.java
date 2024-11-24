package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public class Black_Bird {
    private Texture black_bird;
    static private BodyDef bodyDef = new BodyDef();
    static private PolygonShape shape = new PolygonShape();
    static private FixtureDef fixtureDef = new FixtureDef();
    static private Body body;
    static private float ppm=10f;
    public Black_Bird() {
        black_bird =new Texture(Gdx.files.internal("birds/redbird.jpg"));
    }

    public static Body createbird(World world, float x, float y, float radius) {
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x/ppm, y/ppm);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius/ppm);

        fixtureDef.shape = shape;
        fixtureDef.density = 0.3f;
        fixtureDef.restitution = 0.5f; // Make it bouncy
        Fixture f=body.createFixture(fixtureDef);
        body.setLinearDamping(0f);
        f.setUserData("bird");
        shape.dispose();

        return body;
    }
}
