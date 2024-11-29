package io.github.angrybirdsjava.pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.blocks.Structures;
import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;

public class Mucchad_Pig implements Serializable {
    private static Texture crown_pig;
    private static BodyDef bodyDef = new BodyDef();
    private static CircleShape shape = new CircleShape();
    private static FixtureDef fixtureDef = new FixtureDef();
    private static Body body;
    private static float ppm= Constants.ppm;
    public float strength;
    public float damage;
    private transient World world;
    public Mucchad_Pig(World world,float strength,float damage) {
        this.world = world;
        this.strength = strength;
        this.damage = damage;
        crown_pig=new Texture(Gdx.files.internal("pigs/crownpig.png"));
    }
    public void applyDamage(Fixture pig, io.github.angrybirdsjava.blocks.Structures s) {
        float dodamage = s.damage_pig;
        damage+= dodamage;
        if (damage > strength) {
            damage = strength; // Cap damage to avoid overflow;
            ArrayList a=(ArrayList) (pig.getUserData());
            a.remove(1);
            a.add(1,"false");
            Filter filter=new Filter();
            filter.categoryBits=Constants.BIT_PIG;
            filter.maskBits=Constants.BIT_GROUND;
            pig.setFilterData(filter);
        }
    }
    public void applyDamage(Fixture pig,float dodamage) {
        damage+= dodamage;
        if (damage > strength) {
            damage = strength; // Cap damage to avoid overflow;
            ArrayList a=(ArrayList) (pig.getUserData());
            a.remove(1);
            a.add(1,"false");
            Filter filter=new Filter();
            filter.categoryBits=Constants.BIT_PIG;
            filter.maskBits=Constants.BIT_GROUND;
            pig.setFilterData(filter);
        }
    }
    public Body addpig(World world, float x, float y, float radius) {
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Make it dynamic
        bodyDef.position.set(x/ppm, y/ppm);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius/ppm);

        fixtureDef.shape = shape;
        fixtureDef.density = 3.0f;  // Adjust density for mass
        fixtureDef.friction = 0.5f; // Adjust friction as needed
        fixtureDef.restitution = 0.2f; // Bounciness
        body.setLinearDamping(0);
        Filter filter=new Filter();
        filter.categoryBits=Constants.BIT_PIG;
        filter.maskBits=(short) (Constants.BIT_PIG | Constants.BIT_GROUND | Constants.BIT_BIRD | Constants.BIT_BLOCKS);
        // Create the fixture on the body
        ArrayList a=new ArrayList();
        a.add(this);
        a.add("true");
        a.add("pig");
        a.add("flag");
        Fixture f= body.createFixture(fixtureDef);
        f.setFilterData(filter);
        f.setUserData(a);
        return body;
    }
    public Texture getcrownpig() {
        return crown_pig;
    }

    public Body addPig(World world, float posX, float posY, float velX, float velY, float ang, float angle, int type, float grvScl, float radius, float density, float friction, float restitution, short categoryBits, short maskBits, short groupIndex, boolean isAwake, boolean isSlAl, boolean isFxdRotation, boolean isBullet, Object userData2) {
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

        fixtureDef.shape = shape;

        b.setLinearDamping(0);
        Fixture f = b.createFixture(fixtureDef);
        f.setFilterData(filter);
        ArrayList a= (ArrayList) userData2;
        a.remove(0);
        a.add(0, this);
        f.setUserData(a);

        b.setLinearVelocity(new Vector2(velX, velY));
        b.setAngularVelocity(ang);

        shape.dispose();

        return b;
    }
}
