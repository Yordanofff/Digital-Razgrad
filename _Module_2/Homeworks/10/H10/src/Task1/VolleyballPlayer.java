package Task1;

public class VolleyballPlayer implements IPlayer {
    private String name;
    private int age;
    private int playerPosition;
    private int skills;  // 1-10
    private int condition; // 1-5
    public final static int MAX_SKILLS_SCORE = 10;
    public final static int MAX_CONDITION_SCORE = 5;

    private String get_this_player_msg() {
        return "Player [" + this.getName() + "]";
    }

    public void train() {
        // default - print msg enabled.
        train(true);
    }

    public void train(boolean printMsg) {
        if (!this.canTrain()) {
            if (printMsg) {
                System.out.println(this.get_this_player_msg() + " is in bad condition(1) and can't train.");
            }
        } else {
            if (printMsg) {
                System.out.println(this.get_this_player_msg() + " is training.");
            }
            setSkills(getSkills() + 1);
            setCondition(getCondition() - 1);
        }
    }

    public boolean canTrain() {
        return this.condition > 1;
    }

    public void rest() {
        rest(true);
    }

    public void rest(boolean printMsg) {
        if (printMsg) {
            System.out.println(this.get_this_player_msg() + " is resting.");
        }
        setCondition(getCondition() + 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) {
        this.skills = setMaxValue(skills, MAX_SKILLS_SCORE);
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = setMaxValue(condition, MAX_CONDITION_SCORE);
    }

    private int setMaxValue(int value, int maxValue) {
        if (value > maxValue) {
            return maxValue;
        }
        return value;
    }

    @Override
    public String toString() {
        return "VolleyballPlayer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", playerPosition=" + playerPosition +
                ", skills=" + skills +
                ", condition=" + condition +
                '}';
    }

    public VolleyballPlayer(String name, int age, int playerPosition, int skills, int condition) {
        this.name = name;
        this.age = age;
        this.playerPosition = playerPosition;
        this.skills = skills;
        this.condition = condition;
    }

    public VolleyballPlayer(String name, int age, int playerPosition) {
        // full of energy new player
        this.name = name;
        this.age = age;
        this.playerPosition = playerPosition;
        this.skills = 1;
        this.condition = MAX_CONDITION_SCORE;
    }

    public VolleyballPlayer(String name, int playerPosition) {
        // full of energy new player
        this.name = name;
        this.playerPosition = playerPosition;
        this.skills = 1;
        this.condition = MAX_CONDITION_SCORE;
    }

}
