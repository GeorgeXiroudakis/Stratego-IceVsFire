package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ determineWinner.class, isLegalPositionTest.class, moveToTest.class, calcCapturedPiececesTest.class })
public class AllTests {

}
