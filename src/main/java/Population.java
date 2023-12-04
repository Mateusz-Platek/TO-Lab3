import java.util.ArrayList;
import java.util.Iterator;

public class Population {

    private ArrayList<Person> people;
    private boolean immuneStart;
    private boolean created;

    public Population() {
        this.people = new ArrayList<>();
        this.immuneStart = false;
        this.created = false;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setImmuneStart(boolean immuneStart) {
        this.immuneStart = immuneStart;
    }

    public void createPopulation() {
        if (created) {
            return;
        }
        created = true;

        for (int i = 0; i < 100; i++) {
            double x = Math.random() * 50;
            double y = Math.random() * 50;
            if (immuneStart) {
                if (Math.random() < 0.5) {
                    people.add(new Person(x, y, EnumState.IMMUNE, this));
                } else {
                    people.add(new Person(x, y, EnumState.HEALTHY, this));
                }
            } else {
                people.add(new Person(x, y, EnumState.HEALTHY, this));
            }
        }
    }

    public void move() {
        Iterator<Person> iterator = people.iterator();

        while (iterator.hasNext()) {
            Person person = iterator.next();

            if (Math.random() < 0.25) {
                person.changeDirection();
            }
            if (Math.random() < 0.25) {
                person.changeSpeed();
            }

            person.move();

            if (person.isOutside()) {
                if (Math.random() < 0.5) {
                    person.reverseDirection();
                } else {
                    iterator.remove();
                }
            }
        }
    }

    public void infectOthers() {
        for (Person person: people) {
            person.infect();
        }
    }

    public void addPeople() {
        while (people.size() < 100) {
            double x;
            double y;

            if (Math.random() < 0.5) {
                x = Math.random() * 50;
                y = Math.random() < 0.5 ? 0 : 50;
            } else {
                x = Math.random() < 0.5 ? 0 : 50;
                y = Math.random() * 50;
            }

            if (Math.random() < 0.1) {
                if (Math.random() < 0.5) {
                    people.add(new Person(x, y, EnumState.INFECTED, this));
                } else {
                    people.add(new Person(x, y, EnumState.INFECTEDVISIBLE, this));
                }
            } else {
                if (immuneStart) {
                    if (Math.random() < 0.5) {
                        people.add(new Person(x, y, EnumState.IMMUNE, this));
                    } else {
                        people.add(new Person(x, y, EnumState.HEALTHY, this));
                    }
                } else {
                    people.add(new Person(x, y, EnumState.HEALTHY, this));
                }
            }
        }
    }
}
