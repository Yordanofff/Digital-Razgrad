package Task2Tests.VolleyballPlayerTests;

import Task2.Homework10.VolleyballPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class trainTest {

    private static VolleyballPlayer v1;

    @Before
    public void initializer() {
        v1 = new VolleyballPlayer("Joe", 1);
    }

    @Test
    public void testTrainSkillsThenReturnTrue() {
        v1.setCondition(2);
        v1.setSkills(1);
        v1.train();
        int expectedSkills = 2;

        Assert.assertEquals(expectedSkills, v1.getSkills());
    }

    @Test
    public void testTrainConditionThenReturnTrue() {
        v1.setCondition(2);
        v1.setSkills(1);
        v1.train();
        int expectedCondition = 1;

        Assert.assertEquals(expectedCondition, v1.getCondition());
    }

}

