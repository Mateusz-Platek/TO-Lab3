import java.awt.*;

public class Immune extends Healthy {

    public Immune(Person person) {
        super(person);
        this.color = Color.BLUE;
    }

    @Override
    public void changeState() {}
}