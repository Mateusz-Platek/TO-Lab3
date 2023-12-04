import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CareTaker {

    private Population population;
    private HashMap<Integer, ArrayList<PersonMemento>> mementos;

    public CareTaker(Population population) {
        this.population = population;
        this.mementos = new HashMap<>();
    }

    public Set<Integer> getAvailableSeconds() {
        return mementos.keySet();
    }

    public void create(int secondsElapsed) {
        ArrayList<PersonMemento> personMementos = new ArrayList<>();
        for (Person person: population.getPeople()) {
            personMementos.add(person.save());
        }
        mementos.put(secondsElapsed, personMementos);
    }

    public void restore(int secondsElapsed) {
        ArrayList<Person> people = population.getPeople();
        people.clear();

        for (PersonMemento memento: mementos.get(secondsElapsed)) {
            people.add(new Person(memento, population));
        }
    }
}
