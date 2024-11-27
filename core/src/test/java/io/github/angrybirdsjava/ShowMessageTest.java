package io.github.angrybirdsjava;

import com.badlogic.gdx.math.Vector2;
import io.github.angrybirdsjava.Constants;
import io.github.angrybirdsjava.Level1Screen;
import io.github.angrybirdsjava.screens.ShowMessage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class ShowMessageTest {
    public static boolean get(){
        return false;
    }
    @Test
    public void test() {
        ShowMessage showMessage1 = new ShowMessage("wood","Hello1",200f,200f,2f);
        ShowMessage showMessage2 = new ShowMessage("glass","Hello2",400f,200f,4f);

        boolean result1 = showMessage1.isFinished();
        boolean result2 = showMessage2.isFinished();
        assertEquals(false,result1);
        assertEquals(false,result2);

        showMessage1.update(2f);
        showMessage2.update(3f);
        result1 = showMessage1.isFinished();
        result2 = showMessage2.isFinished();
        assertEquals(false,result1);
        assertEquals(false,result2);

        showMessage1.update(2f);
        showMessage2.update(3f);
        result1 = showMessage1.isFinished();
        result2 = showMessage2.isFinished();
        assertEquals(true,result1);
        assertEquals(false,result2);

        showMessage1.update(2f);
        showMessage2.update(3f);
        result1 = showMessage1.isFinished();
        result2 = showMessage2.isFinished();
        assertEquals(true,result1);
        assertEquals(true,result1);

    }
}

