package io.github.angrybirdsjava.JunitTests;

import com.badlogic.gdx.math.Vector2;
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.Level1Screen;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class BirdCheck {
    public static boolean get(){
        return false;
    }
    @Test
    public void test() {
        //should return false, since no bird has collided yet
        boolean res1= Level1Screen.checkbird(new Vector2(500,400),"red");
        //should return true, since out of play area
        boolean  res2=Level1Screen.checkbird(new Vector2(500,600),"red");
        //should return false, since no bird launched yet
        boolean res3=Level1Screen.checkbird(new Vector2(500,600),"null");

        assertEquals(false,res1);
//        System.out.println(res1);
        assertEquals(true,res2);
//        System.out.println(res2);
        assertEquals(false,res3);
//        System.out.println(res3);
    }
}

