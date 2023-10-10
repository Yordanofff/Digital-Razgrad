package Task1;

public class Athlete {

    private String name;
    private int weight;
    private int skillLevel;
    private int energy;

    public void train() {
        System.out.println("The athlete: " + this.name + " is training. Skill level goes from " + this.skillLevel + " to: " + (this.skillLevel + 1));
        this.skillLevel += 1;
    }

    public void compete() {
        if (this.hasEnergy()) {
            System.out.println("The athlete: " + this.name + " is competing. Energy goes down from " + this.energy + " to " + (this.energy - 1));
            setEnergy(this.energy - 1);
        } else {
            System.out.println(this.name + " doesn't have enough energy to continue. Needs to rest.");
        }
    }

    public void rest() {
        System.out.println("The athlete: " + this.name + " is resting. Energy goes up from " + this.energy + " to " + (this.energy + 1));
        this.energy += 1;
    }

    @Override
    public String toString() {
        return "Task1.Athlete{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", skillLevel=" + skillLevel +
                ", energy=" + energy +
                '}';
    }

    public boolean hasEnergy() {
        return this.energy > 0;
    }

    public Athlete(String name, int weight, int skillLevel, int energy) {
        this.name = name;
        this.weight = weight;
        this.skillLevel = skillLevel;
        this.energy = energy;
    }

    public Athlete() {
        this.name = "Random athlete";
        this.weight = 80;
        this.skillLevel = 1;
        this.energy = 5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

}
