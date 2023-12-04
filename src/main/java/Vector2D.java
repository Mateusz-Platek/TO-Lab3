
public class Vector2D {

    private double x;
    private double y;

    private double generateDouble() {
        return Math.random() * 2 - 1;
    }

    public Vector2D() {
        this.x = generateDouble();
        this.y = generateDouble();
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    public void changeDirection() {
        x = generateDouble();
        y = generateDouble();
    }

    public void reverseDirection() {
        this.x = this.x * -1;
        this.y = this.y * -1;
    }
}
