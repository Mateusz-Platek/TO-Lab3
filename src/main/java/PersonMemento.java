
public class PersonMemento {

    private double velocity;
    private Vector2D vector2D;
    private double x;
    private double y;
    private PersonState state;
    private int infectedTime;

    public PersonMemento(double velocity, Vector2D vector2D, double x, double y, PersonState state, int infectedTime) {
        this.velocity = velocity;
        this.vector2D = vector2D;
        this.x = x;
        this.y = y;
        this.state = state;
        this.infectedTime = infectedTime;
    }

    public double getVelocity() {
        return velocity;
    }

    public Vector2D getVector2D() {
        return vector2D;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public PersonState getState() {
        return state;
    }

    public int getInfectedTime() {
        return infectedTime;
    }
}
