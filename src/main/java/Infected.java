import java.awt.*;

public class Infected implements PersonState {

    private Person person;
    protected Color color;

    public Infected(Person person) {
        this.person = person;
        this.color = Color.YELLOW;
    }

    @Override
    public Color getColor() {
        return color;
    }

    private void getHealthy() {
        if (person.getInfectedTime() >= 2250) {
            person.setState(new Immune(person));
        } else if (person.getInfectedTime() >= 1500) {
            if (Math.random() < 0.25) {
                person.setState(new Immune(person));
            }
        }
    }

    private double calculateDistance(Person otherPerson) {
        return Math.sqrt((person.getX() - otherPerson.getX()) * (person.getX() - otherPerson.getX())
                + (person.getY() - otherPerson.getY()) * (person.getY() - otherPerson.getY()));
    }

    public void inRange(Person otherPerson) {
        if (calculateDistance(otherPerson) <= 3.0) {
            if (person.getAround().containsKey(otherPerson)) {
                person.getAround().put(otherPerson, person.getAround().get(otherPerson) + 1);
            } else {
                person.getAround().put(otherPerson, 1);
            }
        } else {
            person.getAround().remove(otherPerson);
        }
    }

    @Override
    public void infect() {
        for (Person personInPopulation: person.getPopulation().getPeople()) {
            if (person.equals(personInPopulation)) {
                continue;
            }
            inRange(personInPopulation);
        }

        for (Person personAround: person.getAround().keySet()) {
            if (person.getAround().get(personAround) >= 75) {
                personAround.changeState();
            }
        }
    }

    @Override
    public void changeState() {
        if (person.getInfectedTime() >= 1500) {
            person.setState(new Immune(person));
        }
    }

    @Override
    public void move() {
        person.setInfectedTime(person.getInfectedTime() + 1);

        if (person.getInfectedTime() >= 1500) {
            getHealthy();
        }
    }
}
