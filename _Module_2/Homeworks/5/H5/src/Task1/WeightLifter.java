package Task1;

public class WeightLifter extends Athlete {
    private int weightCategory;

    @Override
    public void train() {
        System.out.println("The weight lifter: " + super.getName() + " is training. " +
                "Skill level goes from " + this.getSkillLevel() + " to: " + (this.getSkillLevel() + 1));
        super.setSkillLevel(getSkillLevel() + 1);
    }

    @Override
    public void rest() {
        System.out.println("The weight lifter: " + super.getName() + " is resting. Energy goes up from " +
                this.getEnergy() + " to: " + (this.getEnergy() + 1));
        super.setEnergy(getEnergy() + 1);
    }

    public void compete(String competitorName) {
        System.out.println("The weight lifter: " + super.getName() + " is competing with " + competitorName +
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
                ", weightCategory=" + this.getWeightCategory() +
                '}';
    }

    public WeightLifter(String name, int weight, int skillLevel, int energy, int weightCategory) {
        super(name, weight, skillLevel, energy);
        this.weightCategory = weightCategory;
    }

    public WeightLifter(int weightCategory) {
        this.weightCategory = weightCategory;
    }

    public int getWeightCategory() {
        return weightCategory;
    }

    public void setWeightCategory(int weightCategory) {
        this.weightCategory = weightCategory;
    }
}
