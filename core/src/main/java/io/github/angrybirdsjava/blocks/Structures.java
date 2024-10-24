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
import java.util.ArrayList;

public class Structures {
    private String block_type;
    private TmxMapLoader tileloader;
    private TiledMap tileMap;
    private World world=new World(new Vector2(0, 0), true);
    private BodyDef bodyDef = new BodyDef();
    private PolygonShape shape = new PolygonShape();
    private FixtureDef fixtureDef = new FixtureDef();
    private Body body;
    private int index_in_tmx;


    public Structures(String block_type) {
        this.block_type = block_type;
        tileloader = new TmxMapLoader();
        this.tileMap = tileloader.load(String.valueOf(Gdx.files.internal("Level_tmx_files/level-1.tmx")));
    }
    public ArrayList<Rectangle> return_array() {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        if (block_type.equals("wooden_vertical")) {
            for (MapObject object: tileMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
                body=world.createBody(bodyDef);

                shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                rectangles.add(rect);
            }
        }
        else if (block_type.equals("wooden_horizontal")) {
            for (MapObject object: tileMap.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
                body=world.createBody(bodyDef);

                shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                rectangles.add(rect);
            }
        }
        else if (block_type.equals("wooden_base")) {
            for (MapObject object: tileMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
                body=world.createBody(bodyDef);

                shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                rectangles.add(rect);
            }
        }
        else if (block_type.equals("glass_vertical")) {
            for (MapObject object: tileMap.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                com.badlogic.gdx.math.Rectangle rect=((RectangleMapObject) object).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
                body=world.createBody(bodyDef);

                shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                rectangles.add(rect);
            }
        }return rectangles;
    }

}
