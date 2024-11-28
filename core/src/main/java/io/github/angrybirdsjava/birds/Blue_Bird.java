package io.github.angrybirdsjava.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class Blue_Bird implements Serializable {

    private transient Texture blue_bird;
    static private BodyDef bodyDef = new BodyDef();
    static private PolygonShape shape = new PolygonShape();
    static private FixtureDef fixtureDef = new FixtureDef();
    private String category="bird";
    private String type="bluebird";
    public float damage=0.5f;
    static private Body body;
    private static float ppm= Constants.ppm;;
    public float radius;
    public Blue_Bird() {
        blue_bird=new Texture(Gdx.files.internal("birds/redbird.jpg"));
    }
    public Body createbird(World world, float x, float y, float radius) {
        this.radius=radius;
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x/ppm, y/ppm);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius/ppm);

        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.5f; // Make it bouncy
        Filter filter = new Filter();
        filter.categoryBits=Constants.BIT_BIRD;
        filter.maskBits= (short) (Constants.BIT_BLOCKS | Constants.BIT_GROUND | Constants.BIT_PIG);
        Fixture f=body.createFixture(fixtureDef);
        body.setLinearDamping(0f);
        ArrayList a=new ArrayList();
        body.setUserData("blue");
        a.add("bird");
        a.add(this);
        a.add(radius);
        f.setUserData(a);
        f.setFilterData(filter);
        shape.dispose();

        return body;
    }

    public Body addBird(World world, float posX, float posY, float velX, float velY, float ang, float angle, int type, float grvScl, float radius, float density, float friction, float restitution, short categoryBits, short maskBits, short groupIndex, boolean isAwake, boolean isSlAl, boolean isFxdRotation, boolean isBullet, Object userData, Object userData2) {
        BodyDef bodyDef = new BodyDef();
        CircleShape shape = new CircleShape();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.position.set(posX, posY);

        bodyDef.angle = angle;

        if(type == 0) bodyDef.type = BodyDef.BodyType.StaticBody;
        else if(type == 1) bodyDef.type = BodyDef.BodyType.DynamicBody;
        else bodyDef.type = BodyDef.BodyType.KinematicBody;
        Body b = world.createBody(bodyDef);

        b.setGravityScale(grvScl);
        shape.setRadius(radius);

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        Filter filter = new Filter();
        filter.categoryBits = categoryBits;
        filter.maskBits = maskBits;
        filter.groupIndex = groupIndex;


        b.setAwake(isAwake);
        b.setSleepingAllowed(isSlAl);
        b.setFixedRotation(isFxdRotation);
        b.setBullet(isBullet);
        b.setUserData(userData);

        fixtureDef.shape = shape;

        b.setLinearDamping(0);
        Fixture f = b.createFixture(fixtureDef);
        f.setFilterData(filter);
        ArrayList a= (ArrayList) userData2;
        a.remove(1);
        a.add(1, this);
        f.setUserData(a);

        b.setLinearVelocity(new Vector2(velX, velY));
        b.setAngularVelocity(ang);

        shape.dispose();

        return b;
    }
}
