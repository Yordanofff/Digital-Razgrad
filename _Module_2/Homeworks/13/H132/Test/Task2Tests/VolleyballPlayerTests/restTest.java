package Task2Tests.VolleyballPlayerTests;

import Task2.Homework10.VolleyballPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class restTest {

    private static VolleyballPlayer v1;

    @Before
    public void initializer() {
        v1 = new VolleyballPlayer("Joe", 1);
    }

    @Test
    public void testConditionOneThenReturnTrue() {
        v1.setCondition(1);
        v1.rest();
        int expected = 2;
        Assert.assertEquals(expected, v1.getCondition());
    }

    @Test
    public void testConditionTwoThenReturnTrue() {
        int maxPossibleCondition = VolleyballPlayer.MAX_CONDITION_SCORE;
        v1.setCondition(maxPossibleCondition);
        v1.rest();
        int expected = maxPossibleCondition;
        Assert.assertEquals(expected, v1.getCondition());
    }

}
