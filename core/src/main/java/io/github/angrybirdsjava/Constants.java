package io.github.angrybirdsjava;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static float ppm=13f;

    public static short BIT_BIRD=1;
    public static short BIT_GROUND=2;
    public static short BIT_BLOCKS=4;
    public static short BIT_SLING=8;
    public static short BIT_PIG=16;


    public static BitmapFont wooden_font;
    public static BitmapFont glass_font;
    public static BitmapFont stone_font;
    public static BitmapFont pig_font;
    public static BitmapFont score_font;
    public static BitmapFont score_black_font;
    public static BitmapFont pause_font;

    public static int lev1_total=9600;
    public static int lev2_total=23000;
    public static int lev3_total=26000;

    public static boolean isLoaded = false;

    public static ArrayList<Map<Integer,Integer> > star_map=new ArrayList<>();
    public static ArrayList<Map<Integer,Integer> > score_map=new ArrayList<>();

    public static ArrayList<Body> bodiestodestroy=new ArrayList<>();
    public static ArrayList<Body> bodiestoadd=new ArrayList<>();

    static {
        wooden_font = createfont(Color.WHITE,"e29126",28);
        glass_font = createfont(Color.WHITE,"3490b8",28);
        stone_font = createfont(Color.WHITE,"a0a0a0",28);
        pig_font = createfont(Color.WHITE,"6be248",28);
        score_font = createfont(Color.WHITE,"000000",40);
        score_black_font = createfont(Color.valueOf("25211C"),"fff3e5",50);
        pause_font = createfont(Color.valueOf("25211C"),"fff3e5",18);
        star_map.add(new HashMap<>());
        star_map.add(new HashMap<>());
        star_map.add(new HashMap<>());
        score_map.add(new HashMap<>());
        score_map.add(new HashMap<>());
        score_map.add(new HashMap<>());

        star_map.get(0).put(1,0);
        star_map.get(0).put(2,-1);
        star_map.get(0).put(3,-1);
        star_map.get(1).put(1,0);
        star_map.get(1).put(2,-1);
        star_map.get(1).put(3,-1);
        star_map.get(2).put(1,0);
        star_map.get(2).put(2,-1);
        star_map.get(2).put(3,-1);

        score_map.get(0).put(1,0);
        score_map.get(0).put(2,0);
        score_map.get(0).put(3,0);
        score_map.get(1).put(1,0);
        score_map.get(1).put(2,0);
        score_map.get(1).put(3,0);
        score_map.get(2).put(1,0);
        score_map.get(2).put(2,0);
        score_map.get(2).put(3,0);



    }
    public static BitmapFont createfont(Color color,String hex,int size)  {
        BitmapFont angryfont;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/angrybirds-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        Color mycolor=Color.valueOf(hex);
        // Set font properties

        parameter.size = size; // Font size
        parameter.color = color; // Font color
        parameter.borderWidth = 1; // Border width
        parameter.borderColor = mycolor; // Border color
        parameter.shadowOffsetX = 2; // Shadow X offset
        parameter.shadowOffsetY = 2; // Shadow Y offset
        parameter.shadowColor = new Color(0, 0, 0, 0.75f); // Shadow color

        angryfont = generator.generateFont(parameter); // Generate the BitmapFont
        generator.dispose();
        return angryfont;
    }
    public static boolean get(){
        return false;
    }

}
