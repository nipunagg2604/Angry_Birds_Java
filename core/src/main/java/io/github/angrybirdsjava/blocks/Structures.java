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
import com.badlogic.gdx.utils.Json;
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.Sounds;
import io.github.angrybirdsjava.pigs.Crown_Pig;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Structures implements Serializable {
    public String block_type;
    private transient TmxMapLoader tileloader;
    private transient TiledMap tileMap;
    private transient World world;
    private transient BodyDef bodyDef = new BodyDef();
    private transient PolygonShape shape = new PolygonShape();
    private transient FixtureDef fixtureDef = new FixtureDef();
    private transient Body body;
    private int index_in_tmx;
    private float strength;
    private float damage;
    public float damage_pig;
    public float intercollsion;
    private static float ppm= Constants.ppm;



    public Structures(String block_type,World world, float Strength, float damage,float damage_pig,float intercollsion, String filename) {
        this.block_type = block_type;
        tileloader = new TmxMapLoader();
        this.world = world;
        this.strength = Strength;
        this.damage = damage;
        this.damage_pig = damage_pig;
        this.intercollsion = intercollsion;
        this.tileMap = tileloader.load(String.valueOf(Gdx.files.internal(filename)));
    }
    public float getStrength() {
        return strength;
    }
    public boolean isDestroyed() {
        return damage >= strength;
    }
    public void applyDamage(float amount,Fixture fixture,int flag) {
        ArrayList<Float> prop=(ArrayList<Float>) (((ArrayList)(fixture.getUserData())).get(3));
        float damage = prop.get(0);
        float strength = prop.get(1);
        damage+= amount;
        if (damage > strength) {
            if (block_type.equals("wooden_vertical")|| block_type.equals("wooden_horizontal")
                    || block_type.equals("wooden_thick_horizontal") || block_type.equals("wooden_square") || block_type.equals("wooden_base")){
                if (Sounds.isSound) Sounds.wood_destroy.play(0.5f);
            }
            else if (block_type.equals("glass_vertical") || block_type.equals("glass_horizontal")) {
                if (Sounds.isSound) Sounds.glass_destroy.play(0.5f);
            }
            else if (block_type.equals("stone_vertical") || block_type.equals("stone_horizontal") || block_type.equals("stone_square")){
                if (Sounds.isSound) Sounds.stone_destroy.play(0.5f);
            }
            damage = strength; // Cap damage to avoid overflow;
            ArrayList a=new ArrayList();
            a=((ArrayList) (fixture.getUserData()));
            a.remove(3);
            prop.clear();
            prop.add(damage);
            prop.add(strength);
            a.add(3,prop);

            a.remove(1);
            a.add(1,"false");

            Filter filter=new Filter();
            filter.categoryBits=Constants.BIT_BLOCKS;
            filter.maskBits=Constants.BIT_GROUND;
            fixture.setFilterData(filter);
        }else{
            if (flag==1 && block_type.equals("wooden_vertical")|| block_type.equals("wooden_horizontal")
                    || block_type.equals("wooden_thick_horizontal") || block_type.equals("wooden_square") || block_type.equals("wooden_base")){
                if (Sounds.isSound) Sounds.wood_damage.play(0.1f);
            }
            else if (flag==1 && block_type.equals("glass_vertical") || block_type.equals("glass_horizontal")) {
                if (Sounds.isSound) Sounds.glass_damage.play(0.1f);
            }
            else if (flag==1 && block_type.equals("stone_vertical") || block_type.equals("stone_horizontal") || block_type.equals("stone_square")){
                if (Sounds.isSound) Sounds.stone_damage.play(0.1f);
            }else if (flag==0 && block_type.equals("wooden_vertical")|| block_type.equals("wooden_horizontal")
                    || block_type.equals("wooden_thick_horizontal") || block_type.equals("wooden_square") || block_type.equals("wooden_base")){
                if (Sounds.isSound) Sounds.wood_coll.play(0.1f);
            }
            else if (flag==0 && block_type.equals("glass_vertical") || block_type.equals("glass_horizontal")) {
                if (Sounds.isSound) Sounds.glass_coll.play(0.1f);
            }
            else if (flag==0 && block_type.equals("stone_vertical") || block_type.equals("stone_horizontal") || block_type.equals("stone_square")){
                if (Sounds.isSound) Sounds.stone_coll.play(0.1f);
            }
            prop.clear();
            prop.add(damage);
            prop.add(strength);
            ArrayList a=(ArrayList) (fixture.getUserData());
            a.remove(3);
            a.add(prop);

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
        else if(block_type.equals("wooden_thick_vertical")) array = tileMap.getLayers().get(18).getObjects().getByType(RectangleMapObject.class);
        else if(block_type.equals("stone_thick_vertical")) array = tileMap.getLayers().get(19).getObjects().getByType(RectangleMapObject.class);
        else if(block_type.equals("stone_thick_horizontal")) array = tileMap.getLayers().get(20).getObjects().getByType(RectangleMapObject.class);

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
            if(block_type.equals("stone_square") || block_type.equals("stone_horizontal") || block_type.equals("stone_vertical") || block_type.equals("stone_thick_vertical") || block_type.equals("stone_thick_horizontal")) fixtureDef.density = 3f;
            if(block_type.equals("glass_horizontal") || block_type.equals("glass_vertical")) fixtureDef.density = 1.2f;
            if(block_type.equals("wooden_horizontal") || block_type.equals("wooden_vertical")  )fixtureDef.density = 1.8f;
            if (block_type.equals("wooden_thick_horizontal") || block_type.equals("wooden_square") || block_type.equals("wooden_thick_vertical")) fixtureDef.density = 2f;
            if(block_type.equals("wooden_base") || block_type.equals("soil") || block_type.equals("trisoil")) fixtureDef.density = 10f;
            body.setLinearDamping(0);
            body.setUserData(a);
            ArrayList b = new ArrayList<>();
            b.add(this);
            b.add("true");
            b.add("flag");
            ArrayList<Float> prop=new ArrayList<>();
            prop.add(damage);
            prop.add(strength);
            b.add(prop);
            Filter filter = new Filter();
            filter.categoryBits = Constants.BIT_BLOCKS;
            filter.maskBits = (short) (Constants.BIT_BLOCKS | Constants.BIT_GROUND | Constants.BIT_BIRD | Constants.BIT_PIG);

            Fixture f = body.createFixture(fixtureDef);
            f.setFilterData(filter);
            f.setUserData(b);
            rectangles.add(body);
        }


        return rectangles;
    }
}
