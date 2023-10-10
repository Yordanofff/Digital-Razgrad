package Task1;

public class Swimmer extends Athlete {
    private String style;

    @Override
    public void train() {
        System.out.println("The swimmer: " + super.getName() + " is training. " +
                "Skill level goes from " + this.getSkillLevel() + " to: " + (this.getSkillLevel() + 1));
        super.setSkillLevel(getSkillLevel() + 1);
    }

    @Override
    public void rest() {
        System.out.println("The swimmer: " + super.getName() + " is resting. Energy goes up from " +
                this.getEnergy() + " to: " + (this.getEnergy() + 1));
        super.setEnergy(getEnergy() + 1);
    }

    public void compete(String competitorName) {
        if (super.hasEnergy()) {
            System.out.println("The swimmer: " + super.getName() + " is competing with " + competitorName +
                ". Energy level goes down from " + super.getEnergy() + " to " + (super.getEnergy() - 1));
            setEnergy(super.getEnergy() - 1);
        } else {
            System.out.println(super.getName() + " doesn't have enough energy to continue. Needs to rest.");
        }
    }

    @Override
    public String toString() {
        return "Task1.Swimmer{" +
                "name='" + super.getName() + '\'' +
                ", weight=" + super.getWeight() +
                ", skillLevel=" + super.getSkillLevel() +
                ", energy=" + super.getEnergy() +
                ", style='" + this.getStyle() + '\'' +
                '}';
    }

    public Swimmer(String name, int weight, int skillLevel, int energy, String style) {
        super(name, weight, skillLevel, energy);
        this.style = style;
    }

    public Swimmer() {
        this.style = "free style";
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
