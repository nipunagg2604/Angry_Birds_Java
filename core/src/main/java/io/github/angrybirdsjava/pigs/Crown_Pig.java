package io.github.angrybirdsjava.pigs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.Constants;

public abstract class Crown_Pig {
    private static Texture crown_pig;
    private static World world;
    private static BodyDef bodyDef = new BodyDef();
    private static CircleShape shape = new CircleShape();
    private static FixtureDef fixtureDef = new FixtureDef();
    private static Body body;
    private static float ppm= Constants.ppm;;
    public Crown_Pig(World world) {
        this.world=world;
        crown_pig=new Texture(Gdx.files.internal("pigs/crownpig.png"));
    }
    public static Body addpig(World world, float x, float y, float radius) {
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
        Fixture f= body.createFixture(fixtureDef);
        f.setFilterData(filter);

        return body;
    }
    public Texture getcrownpig() {
        return crown_pig;
    }
}
