package io.github.angrybirdsjava.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.*;
import java.lang.reflect.Array;
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

    private static float ppm=10f;

    public Structures(String block_type,World world) {
        this.block_type = block_type;
        tileloader = new TmxMapLoader();
        this.world = world;
        this.tileMap = tileloader.load(String.valueOf(Gdx.files.internal("Level_tmx_files/level-1.tmx")));
    }
    public ArrayList<Body> return_array() {
        ArrayList<Body> rectangles = new ArrayList<>();
        if (block_type.equals("wooden_vertical")) {
            for (MapObject object: tileMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((rect.getX()+rect.getWidth()/2)/ppm, (rect.getY()+rect.getHeight()/2)/ppm);
                body=world.createBody(bodyDef);
                ArrayList<Float> a=new ArrayList();
                a.add(rect.getX());
                a.add(rect.getY());
                a.add(rect.getWidth()/2);
                a.add(rect.getHeight()/2);
                shape.setAsBox((rect.getWidth()/2)/ppm, (rect.getHeight()/2)/ppm);
                fixtureDef.shape = shape;
                fixtureDef.friction = 0.5f;  // Moderate friction
                fixtureDef.restitution = 0.3f;
                fixtureDef.density = 1f;
                body.setLinearDamping(0);
                body.setUserData(a);
                body.createFixture(fixtureDef);
                rectangles.add(body);
            }
        }
        else if (block_type.equals("wooden_horizontal")) {
            for (MapObject object: tileMap.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((rect.getX()+rect.getWidth()/2)/ppm, (rect.getY()+rect.getHeight()/2)/ppm);
                body=world.createBody(bodyDef);

                ArrayList<Float> a=new ArrayList();
                a.add(rect.getX());
                a.add(rect.getY());
                a.add(rect.getWidth()/2);
                a.add(rect.getHeight()/2);
                shape.setAsBox((rect.getWidth()/2)/ppm, (rect.getHeight()/2)/ppm);
                fixtureDef.shape = shape;
                fixtureDef.friction = 0.5f;  // Moderate friction
                fixtureDef.restitution = 0.3f;
                fixtureDef.density = 1f;
                body.setLinearDamping(0);
                body.setUserData(a);
                body.createFixture(fixtureDef);
                rectangles.add(body);
            }
        }
        else if (block_type.equals("wooden_base")) {
            for (MapObject object: tileMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((rect.getX()+rect.getWidth()/2)/ppm, (rect.getY()+rect.getHeight()/2)/ppm);
                body=world.createBody(bodyDef);
                ArrayList<Float> a=new ArrayList();
                a.add(rect.getX());
                a.add(rect.getY());
                a.add(rect.getWidth()/2);
                a.add(rect.getHeight()/2);
                shape.setAsBox((rect.getWidth()/2)/ppm, (rect.getHeight()/2)/ppm);
                fixtureDef.shape = shape;
                fixtureDef.friction = 0.5f;  // Moderate friction
                fixtureDef.restitution = 0.3f;
                fixtureDef.density = 1f;
                body.setLinearDamping(0);
                body.setUserData(a);
                body.createFixture(fixtureDef);
                rectangles.add(body);
            }
        }
        else if (block_type.equals("glass_vertical")) {
            for (MapObject object: tileMap.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((rect.getX()+rect.getWidth()/2)/ppm, (rect.getY()+rect.getHeight()/2)/ppm);
                body=world.createBody(bodyDef);
                ArrayList<Float> a=new ArrayList();
                a.add(rect.getX());
                a.add(rect.getY());
                a.add(rect.getWidth()/2);
                a.add(rect.getHeight()/2);
                shape.setAsBox((rect.getWidth()/2)/ppm, (rect.getHeight()/2)/ppm);
                fixtureDef.shape = shape;
                fixtureDef.friction = 0.5f;  // Moderate friction
                fixtureDef.restitution = 0.3f;
                fixtureDef.density = 1f;
                body.setLinearDamping(0);
                body.setUserData(a);
                body.createFixture(fixtureDef);
                rectangles.add(body);
            }
        }
        return rectangles;
    }

}
