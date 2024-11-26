package io.github.angrybirdsjava;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;

public class Constants {
    public static float ppm=13f;

    public static short BIT_BIRD=1;
    public static short BIT_GROUND=2;
    public static short BIT_BLOCKS=4;
    public static short BIT_SLING=8;
    public static short BIT_PIG=16;

    public static float musicplay=0f;

    public static boolean isSound=true;
    public static float soundplay;
    public static Music music=Gdx.audio.newMusic(Gdx.files.internal("Music/title_theme.mp3"));
    public static Sound button_click=Gdx.audio.newSound(Gdx.files.internal("Music/sfx/button.wav"));

    public static Music intromusic=Gdx.audio.newMusic(Gdx.files.internal("Music/Angry Birds OST/birds_intro.mp3"));
    public static Music outromusic=Gdx.audio.newMusic(Gdx.files.internal("Music/Angry Birds OST/birds_outro.mp3"));
}
