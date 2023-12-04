import java.awt.*;
import java.util.HashMap;

public class Person {

    private double velocity;
    private Vector2D vector2D;
    private double x;
    private double y;
    private PersonState state;
    private HashMap<Person, Integer> around;
    private int infectedTime;
    private Population population;

    public Person(double x, double y, EnumState state, Population population) {
        this.velocity = Math.random() * 0.1;
        this.vector2D = new Vector2D();
        this.x = x;
        this.y = y;
        switch (state) {
            case IMMUNE:
                this.state = new Immune(this);
                break;
            case HEALTHY:
                this.state = new Healthy(this);
                break;
            case INFECTED:
                this.state = new Infected(this);
                break;
            case INFECTEDVISIBLE:
                this.state = new InfectedVisible(this);
                break;
        }
        this.around = new HashMap<>();
        this.infectedTime = 0;
        this.population = population;
    }

    public Person(PersonMemento memento, Population population) {
        this.velocity = memento.getVelocity();
        this.vector2D = memento.getVector2D();
        this.x = memento.getX();
        this.y = memento.getY();
        if (memento.getState() instanceof Immune) {
            this.state = new Immune(this);
        } else if (memento.getState() instanceof Healthy) {
            this.state = new Healthy(this);
        } else if (memento.getState() instanceof InfectedVisible) {
            this.state = new InfectedVisible(this);
        } else if (memento.getState() instanceof Infected) {
            this.state = new Infected(this);
        }
        this.around = new HashMap<>();
        this.infectedTime = memento.getInfectedTime();
        this.population = population;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return state.getColor();
    }

    public HashMap<Person, Integer> getAround() {
        return around;
    }

    public int getInfectedTime() {
        return infectedTime;
    }

    public Population getPopulation() {
        return population;
    }

    public void setState(PersonState state) {
        this.state = state;
    }

    public void setInfectedTime(int infectedTime) {
        this.infectedTime = infectedTime;
    }

    public void changeDirection() {
        vector2D.changeDirection();
    }

    public void changeSpeed() {
        velocity = Math.random() * 0.1;
    }

    public boolean isOutside() {
        return x > 50 || x < 0 || y > 50 || y < 0;
    }

    public void reverseDirection() {
        vector2D.reverseDirection();
    }

    public void move() {
        state.move();

        double x = vector2D.getX() * velocity / vector2D.abs();
        double y = vector2D.getY() * velocity / vector2D.abs();
        this.x += x;
        this.y += y;
    }

    public void infect() {
        state.infect();
    }

    public void changeState() {
        state.changeState();
    }

    public PersonMemento save() {
        Vector2D vector = new Vector2D(vector2D.getX(), vector2D.getY());
        PersonState personState = null;
        if (state instanceof Immune) {
            personState = new Immune(this);
        } else if (state instanceof Healthy) {
            personState = new Healthy(this);
        } else if (state instanceof InfectedVisible) {
            personState = new InfectedVisible(this);
        } else if (state instanceof Infected){
            personState = new Infected(this);
        }

        return new PersonMemento(velocity, vector, x, y, personState, infectedTime);
    }
}
