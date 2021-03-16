package hu.nive.ujratervezes.zarovizsga.kennel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Kennel {

    private List<Dog> dogs = new ArrayList<>();

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    public void feedAll() {
        for (Dog dog : dogs) {
            dog.feed();
        }
    }

    public Dog findByName(String name) {
        Optional<Dog> result = dogs.stream()
                .filter(d -> d.getName().equals(name))
                .findAny();
        if(result.isPresent()){
            return result.get();
        }
        throw new IllegalArgumentException("No dog with name: " + name);
    }

    public void playWith(String name, int hours){
        Dog dog = findByName(name);
        dog.play(hours);
    }

    public List<String> getHappyDogNames(int minHappiness){
        return dogs.stream()
                .filter(d->d.getHappiness()>minHappiness)
                .map(Dog::getName)
                .collect(Collectors.toList());
    }

    public List<Dog> getDogs() {
        return new ArrayList<>(dogs);
    }
}
