package io.github.angrybirdsjava;

import io.github.angrybirdsjava.JunitTests.BirdCheck;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        BirdCheck.class,
        ShowMessageTest.class,

})
public class JunitTestSuite {

}
