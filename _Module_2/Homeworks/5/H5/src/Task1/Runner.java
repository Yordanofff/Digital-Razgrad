package Task1;

public class Runner extends Athlete {
    private int distance;

    @Override
    public void train() {
        System.out.println("The runner: " + super.getName() + " is training. " +
                "Skill level goes from " + this.getSkillLevel() + " to: " + (this.getSkillLevel() + 1));
        super.setSkillLevel(getSkillLevel() + 1);
    }

    @Override
    public void rest() {
        System.out.println("The runner: " + super.getName() + " is resting. Energy goes up from " +
                this.getEnergy() + " to: " + (this.getEnergy() + 1));
        super.setEnergy(getEnergy() + 1);
    }

    public void compete(String competitorName) {
        System.out.println("The runner: " + super.getName() + " is competing with " + competitorName +
                ". Energy level goes down from " + super.getEnergy() + " to " + (super.getEnergy() - 1));
        super.setEnergy(getEnergy() - 1);
    }

    @Override
    public String toString() {
        return "Task1.Swimmer{" +
                "name='" + super.getName() + '\'' +
                ", weight=" + super.getWeight() +
                ", skillLevel=" + super.getSkillLevel() +
                ", energy=" + super.getEnergy() +
                ", distance=" + this.getDistance() +
                '}';
    }

    public Runner(String name, int weight, int skillLevel, int energy, int distance) {
        super(name, weight, skillLevel, energy);
        this.distance = distance;
    }

    public Runner(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
