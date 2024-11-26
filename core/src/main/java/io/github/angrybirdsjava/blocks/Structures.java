package io.github.angrybirdsjava.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.awt.*;
import java.util.ArrayList;

public class Structures {
    private String block_type;
    private TmxMapLoader tileloader;
    private TiledMap tileMap;
    private World world;
    private BodyDef bodyDef = new BodyDef();
    private PolygonShape shape = new PolygonShape();
    private FixtureDef fixtureDef = new FixtureDef();
    private Body body;
    private int index_in_tmx;
    private float strength;
    private float damage;
    private static float ppm= Constants.ppm;


    public Structures(String block_type,World world, int Strength, int damage, String filename) {
        this.block_type = block_type;
        tileloader = new TmxMapLoader();
        this.world = world;
        this.strength = Strength;
        this.damage = damage;
        this.tileMap = tileloader.load(String.valueOf(Gdx.files.internal(filename)));
    }
    public float getStrength() {
        return strength;
    }
    public boolean isDestroyed() {
        return damage >= strength;
    }
    public void applyDamage(float amount,Fixture fixture) {
        damage += amount;
        if (damage > strength) {
            damage = strength; // Cap damage to avoid overflow;
            if (block_type.equals("glass_vertical") || block_type.equals("glass_horizontal")) {
                ArrayList a=new ArrayList();
                a=((ArrayList) (fixture.getUserData()));
                a.remove(1);
                a.add(1,"false");

                Filter filter=new Filter();
                filter.categoryBits=Constants.BIT_BLOCKS;
                filter.maskBits=Constants.BIT_GROUND;
                fixture.setFilterData(filter);
            }else if( block_type.equals("wooden_vertical") || block_type.equals("wooden_horizontal")) {
                ArrayList a=new ArrayList();
                a=((ArrayList) (fixture.getUserData()));
                a.remove(1);
                a.add(1,"false");
                Filter filter=new Filter();
                filter.categoryBits=Constants.BIT_BLOCKS;
                filter.maskBits=Constants.BIT_GROUND;
                fixture.setFilterData(filter);
            }
        }
    }
    public World returnworld(){
        return world;
    }
    public float getDamage() {
        return damage;
    }
    public ArrayList<Body> return_array() {
        ArrayList<Body> rectangles = new ArrayList<>();
        Array<RectangleMapObject> array = new Array<RectangleMapObject>();
        if (block_type.equals("wooden_vertical")) array = tileMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("wooden_horizontal")) array = tileMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("wooden_base")) array = tileMap.getLayers().get(6).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("glass_vertical")) array = tileMap.getLayers().get(10).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("glass_horizontal")) array = tileMap.getLayers().get(11).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("stone_vertical")) array = tileMap.getLayers().get(12).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("stone_horizontal")) array = tileMap.getLayers().get(13).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("soil")) array = tileMap.getLayers().get(14).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("slingshot")) array = tileMap.getLayers().get(16).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("stone_square")) array = tileMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("wooden_square")) array = tileMap.getLayers().get(8).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("wooden_thick_horizontal")) array = tileMap.getLayers().get(9).getObjects().getByType(RectangleMapObject.class);
        else if (block_type.equals("trisoil")) array = tileMap.getLayers().get(17).getObjects().getByType(RectangleMapObject.class);

        for(MapObject object: array) {
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / ppm, (rect.getY() + rect.getHeight() / 2) / ppm);
            body = world.createBody(bodyDef);
            ArrayList<Float> a = new ArrayList();
            a.add(rect.getX());
            a.add(rect.getY());
            a.add(rect.getWidth() / 2);
            a.add(rect.getHeight() / 2);
            shape.setAsBox((rect.getWidth() / 2) / ppm, (rect.getHeight() / 2) / ppm);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.5f;  // Moderate friction
            fixtureDef.restitution = 0.3f;
            fixtureDef.density = 1f;
            body.setLinearDamping(0);
            body.setUserData(a);
            ArrayList b = new ArrayList<>();
            b.add(this);
            b.add("true");
            if(block_type.equals("glass_horizontal") || block_type.equals("glass_vertical")) b.add("glass");
            Filter filter = new Filter();
            filter.categoryBits = Constants.BIT_BLOCKS;
            filter.maskBits = (short) (Constants.BIT_BLOCKS | Constants.BIT_GROUND | Constants.BIT_BIRD);

            Fixture f = body.createFixture(fixtureDef);
            f.setFilterData(filter);
            f.setUserData(b);
            rectangles.add(body);
        }


        return rectangles;
    }

}
