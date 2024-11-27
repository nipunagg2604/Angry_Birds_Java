package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.Constants;

import java.util.ArrayList;

public class Black_Bird {
    private Texture black_bird;
    static private BodyDef bodyDef = new BodyDef();
    static private PolygonShape shape = new PolygonShape();
    static private FixtureDef fixtureDef = new FixtureDef();
    private String category="bird";
    private String type="blackbird";
    public float damage=18f;
    static private Body body;
    private static float ppm= Constants.ppm;
    public float radius;

    public Black_Bird() {
        black_bird =new Texture(Gdx.files.internal("birds/redbird.jpg"));
    }

    public Body createbird(World world, float x, float y, float radius) {
        this.radius = radius;
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x/ppm, y/ppm);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius/ppm);

        fixtureDef.shape = shape;
        fixtureDef.density = 0.3f;
        fixtureDef.restitution = 0.5f;
        Filter filter= new Filter();// Make it bouncy
        filter.categoryBits=Constants.BIT_BIRD;
        filter.maskBits= (short) (Constants.BIT_BLOCKS | Constants.BIT_GROUND | Constants.BIT_PIG);
        Fixture f=body.createFixture(fixtureDef);
        body.setLinearDamping(0f);
        ArrayList a=new ArrayList();
        body.setUserData("black");
        a.add("bird");
        a.add(this);
        a.add(radius);
        f.setUserData(a);
        f.setFilterData(filter);
        shape.dispose();

        return body;
    }

}
