import java.awt.*;

public class Healthy implements PersonState {

    private Person person;
    protected Color color;

    public Healthy(Person person) {
        this.person = person;
        this.color = Color.GREEN;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void infect() {}

    @Override
    public void changeState() {
        PersonState state = Math.random() < 0.5 ? new Infected(person) : new InfectedVisible(person);
        person.setState(state);
    }

    @Override
    public void move() {}
}
