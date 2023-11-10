package Task2Tests.VolleyballPlayerTests;

import Task2.Homework10.VolleyballPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class canTrainTest {

    private static VolleyballPlayer v1;

    @Before
    public void initializer() {
        v1 = new VolleyballPlayer("Joe", 1);
    }

    @Test
    public void testConditionTwoThenReturnTrue() {
        v1.setCondition(2);
        boolean result = v1.canTrain();
        Assert.assertTrue(result);
    }

    @Test
    public void testConditionOneThenReturnFalse() {
        v1.setCondition(1);
        boolean result = v1.canTrain();
        Assert.assertFalse(result);
    }
}