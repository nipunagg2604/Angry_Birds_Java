package io.github.angrybirdsjava;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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

    public static int lev1_total=0;
    public static int lev2_total=0;
    public static int lev3_total=0;

    static {
        wooden_font = createfont("e29126",28);
        glass_font = createfont("3490b8",28);
        stone_font = createfont("a0a0a0",28);
        pig_font = createfont("6be248",28);
        score_font = createfont("000000",40);
    }
    public static BitmapFont createfont(String hex,int size)  {
        BitmapFont angryfont;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/angrybirds-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        Color mycolor=Color.valueOf(hex);
        // Set font properties

        parameter.size = size; // Font size
        parameter.color = Color.WHITE; // Font color
        parameter.borderWidth = 2; // Border width
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
