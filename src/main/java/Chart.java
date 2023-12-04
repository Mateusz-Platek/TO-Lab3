import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chart extends JPanel {

    private ArrayList<Person> people;

    public Chart(ArrayList<Person> people) {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.BLACK);
        this.people = people;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = 10;
        for (var person : people) {
            g.setColor(person.getColor());
            g.fillOval((int) (person.getX() * 16 - size / 2), (int) (person.getY() * 16 - size / 2), size, size);
        }
    }
}
