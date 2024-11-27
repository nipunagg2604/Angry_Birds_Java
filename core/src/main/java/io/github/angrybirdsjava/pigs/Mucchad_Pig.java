package io.github.angrybirdsjava.pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.blocks.Structures;

import java.util.ArrayList;

public class Mucchad_Pig {
    private static Texture crown_pig;
    private static BodyDef bodyDef = new BodyDef();
    private static CircleShape shape = new CircleShape();
    private static FixtureDef fixtureDef = new FixtureDef();
    private static Body body;
    private static float ppm= Constants.ppm;
    private float strength;
    private float damage;
    private transient World world;
    public Mucchad_Pig(World world, float strength, float damage) {
        this.world = world;
        this.strength = strength;
        this.damage = damage;
        crown_pig=new Texture(Gdx.files.internal("pigs/crownpig.png"));
    }
    public void applyDamage(Fixture pig,Structures s) {
        float dodamage = s.damage_pig;
        damage+= dodamage;
        if (damage > strength) {
            damage = strength; // Cap damage to avoid overflow;
            ArrayList a=(ArrayList) (pig.getUserData());
            a.remove(1);
            a.add(1,"false");
        }
    }public void applyDamage(Fixture pig,float dodamage) {
        damage+= dodamage;
        if (damage > strength) {
            damage = strength; // Cap damage to avoid overflow;
            ArrayList a=(ArrayList) (pig.getUserData());
            a.remove(1);
            a.add(1,"false");
        }
    }
    public Body addpig(World world, float x, float y, float radius) {
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Make it dynamic
        bodyDef.position.set(x/ppm, y/ppm);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius/ppm);

        fixtureDef.shape = shape;
        fixtureDef.density = 2.0f;  // Adjust density for mass
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
        Fixture f= body.createFixture(fixtureDef);
        f.setFilterData(filter);
        f.setUserData(a);
        return body;
    }
    public Texture getmucchadpig() {
        return crown_pig;
    }
}
