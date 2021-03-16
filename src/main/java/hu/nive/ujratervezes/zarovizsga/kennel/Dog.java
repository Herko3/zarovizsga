package hu.nive.ujratervezes.zarovizsga.kennel;

public abstract class Dog {

    private String name;
    protected int happiness = 0;

    public Dog(String name) {
        this.name = name;
    }

    public abstract void feed();
    public abstract void play(int hours);

    public String getName() {
        return name;
    }

    public int getHappiness() {
        return happiness;
    }
}
