package Task1;

public class Human {
    private final int votingAge = 18;
    private String name;
    private int age;
    private String address;

    public boolean isAllowedToVote() {
        return this.age >= this.votingAge;
    }

    public void printIsAllowedToVoteMsg() {
        if (this.isAllowedToVote()) {
            System.out.println(this.name + " is " + this.age + " years old and is allowed vote.");
        } else {
            System.out.println(this.name + " is " + this.age + " years old and is not allowed to vote.");
        }
    }

    public Human() {
        this.name = "Ivan";
        this.age = 20;
        this.address = "Sofia";
    }

    public Human(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void increaseAge() {
        this.age += 1;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name=" + name +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", isAllowedToVote=" + isAllowedToVote() +
                '}';
    }
}
