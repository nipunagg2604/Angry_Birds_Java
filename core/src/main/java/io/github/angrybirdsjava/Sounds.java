package io.github.angrybirdsjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static float musicplay=0f;

    public static boolean isSound=true;
    public static float soundplay;
    public static Music music= Gdx.audio.newMusic(Gdx.files.internal("Music/title_theme.mp3"));
    public static Sound button_click=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/button.wav"));

    public static Music intromusic=Gdx.audio.newMusic(Gdx.files.internal("Music/Angry Birds OST/birds_intro.mp3"));
    public static Music outromusic=Gdx.audio.newMusic(Gdx.files.internal("Music/Angry Birds OST/birds_outro.mp3"));

    public static Sound Sling_Stretch=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/slingshot_streched.wav"));
    public static Sound explosion=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/explosion.wav"));

    public static Sound red_yell=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/redbird_yell01.wav"));
    public static Sound yellow_yell=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/yellowflying.wav"));
    public static Sound black_yell=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/Globe_Bird_Launch_3.wav"));
    public static Sound blue_yell=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/blue slingshot.wav"));

    public static Sound red_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/bird 01 collision a1.wav"));
    public static Sound yellow_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/bird 02 collision a2.wav"));
    public static Sound black_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/bird 04 collision a4.wav"));
    public static Sound blue_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/blue hit a1.wav"));

    public static Sound wood_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/wood collision a1.wav"));
    public static Sound glass_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/ice light collision a2.wav"));
    public static Sound stone_coll=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/rock collision a2.wav"));

    public static Sound wood_damage=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/wood damage a1.wav"));
    public static Sound glass_damage=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/ice light collision a7.wav"));
    public static Sound stone_damage=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/rock collision a4.wav"));

    public static Sound wood_destroy=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/wood destroyed a3.wav"));
    public static Sound glass_destroy=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/glass break.mp3"));
    public static Sound stone_destroy=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/rock damage a3.wav"));

    public static Sound star1=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/star_1.wav"));
    public static Sound star2=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/star_2.mp3"));
    public static Sound star3=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/star_3.wav"));


}
