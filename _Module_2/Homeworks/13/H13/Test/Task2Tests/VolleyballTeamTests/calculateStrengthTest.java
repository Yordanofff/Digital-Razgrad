package Task2Tests.VolleyballTeamTests;

import Task2.Homework10.VolleyballITeam;
import Task2.Homework10.VolleyballPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class calculateStrengthTest {
    VolleyballPlayer v1;
    VolleyballPlayer v2;
    VolleyballPlayer v3;
    VolleyballPlayer v4;
    VolleyballPlayer v5;
    VolleyballPlayer v6;
    VolleyballPlayer[] testPlayersList = new VolleyballPlayer[VolleyballITeam.NUMBER_OF_PLAYERS];
    VolleyballITeam testTeam = new VolleyballITeam();


    @Before
    public void initializer() {
        v1 = new VolleyballPlayer("A", 1);
        v2 = new VolleyballPlayer("A", 1);
        v3 = new VolleyballPlayer("A", 1);
        v4 = new VolleyballPlayer("A", 1);
        v5 = new VolleyballPlayer("A", 1);
        v6 = new VolleyballPlayer("A", 1);

        testPlayersList[0] = v1;
        testPlayersList[1] = v2;
        testPlayersList[2] = v3;
        testPlayersList[3] = v4;
        testPlayersList[4] = v5;
        testPlayersList[5] = v6;

        testTeam.setPlayers(testPlayersList);
    }

    @Test
    public void testSomeNumbersThenReturnTrue() {
        v1.setSkills(1);
        v2.setSkills(2);
        v3.setSkills(3);
        v4.setSkills(1);
        v5.setSkills(2);
        v6.setSkills(3);

        double expected = 2.0;
        double result = testTeam.calculateStrength();

        Assert.assertEquals(expected, result, 0.001);

    }

    @Test
    public void testSomeOtherNumbersThenReturnTrue() {
        v1.setSkills(1);
        v2.setSkills(2);
        v3.setSkills(3);
        v4.setSkills(4);
        v5.setSkills(6);
        v6.setSkills(2);

        double expected = 3.0;
        double result = testTeam.calculateStrength();

        Assert.assertEquals(expected, result, 0.001);

    }
}
