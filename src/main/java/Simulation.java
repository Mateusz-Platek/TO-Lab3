import javax.swing.*;
import java.awt.*;

public class Simulation {

    private Population population;
    private JFrame window;
    private Chart chart;
    private TopBar topBar;
    private CareTaker careTaker;
    private boolean stop;
    private int secondsElapsed;

    public Simulation() {
        this.population = new Population();
        this.window = new JFrame();
        this.chart = new Chart(population.getPeople());
        this.careTaker = new CareTaker(population);
        this.topBar = new TopBar(population, this, careTaker);
        this.secondsElapsed = 0;
        this.stop = false;
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public void setSecondsElapsed(int secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public void init() {
        window.setTitle("Simulation");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(chart, BorderLayout.CENTER);
        window.add(topBar, BorderLayout.PAGE_START);
        topBar.createButtons();
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }

    private void sleep() {
        try {
            Thread.sleep(1000 / 25);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < 25; i++) {
                sleep();
                population.move();
                population.addPeople();
                population.infectOthers();
                chart.repaint();
            }

            topBar.refreshBar();
            secondsElapsed++;

            if (stop) {
                stop = false;
                break;
            }
        }
    }
}
