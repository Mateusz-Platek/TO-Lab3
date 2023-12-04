import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel {

    private Population population;
    private Simulation simulation;
    private CareTaker careTaker;
    private JComboBox<Integer> secondsComboBox;

    public TopBar(Population population, Simulation simulation, CareTaker careTaker) {
        this.population = population;
        this.simulation = simulation;
        this.careTaker = careTaker;
    }

    public void createButtons() {
        JCheckBox jCheckBox = new JCheckBox("Immune");
        JButton jButtonStart = new JButton("Start");
        JButton jButtonStop = new JButton("Stop");
        secondsComboBox = new JComboBox<>(new Integer[]{});
        JButton jButtonSave = new JButton("Save");
        JButton jButtonLoad = new JButton("Load");

        jButtonStop.setEnabled(false);
        secondsComboBox.setEnabled(false);
        jButtonSave.setEnabled(false);
        jButtonLoad.setEnabled(false);

        jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jCheckBox.isSelected()) {
                    population.setImmuneStart(true);
                } else {
                    population.setImmuneStart(false);
                }
            }
        });

        jButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonStart.setEnabled(false);
                jButtonStop.setEnabled(true);
                jCheckBox.setEnabled(false);
                secondsComboBox.setEnabled(false);
                jButtonSave.setEnabled(false);
                jButtonLoad.setEnabled(false);

                population.createPopulation();
                
                SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        simulation.run();
                        return null;
                    }

                    @Override
                    protected void done() {
                        super.done();
                        jButtonStart.setEnabled(true);
                    }
                };
                swingWorker.execute();
            }
        });

        jButtonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonStop.setEnabled(false);
                jButtonStart.setEnabled(true);
                secondsComboBox.setEnabled(true);
                jButtonSave.setEnabled(true);
                if (!careTaker.getAvailableSeconds().isEmpty()) {
                    jButtonLoad.setEnabled(true);
                }
                simulation.stop();
            }
        });

        jButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonSave.setEnabled(false);
                careTaker.create(simulation.getSecondsElapsed());
            }
        });

        jButtonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                careTaker.restore((Integer) secondsComboBox.getSelectedItem());
                simulation.setSecondsElapsed((Integer) secondsComboBox.getSelectedItem());
            }
        });

        add(jCheckBox);
        add(jButtonStart);
        add(jButtonStop);
        add(secondsComboBox);
        add(jButtonSave);
        add(jButtonLoad);
    }

    public void refreshBar() {
        Integer[] seconds = careTaker.getAvailableSeconds().stream().sorted().toArray(Integer[]::new);
        secondsComboBox.setModel(new DefaultComboBoxModel<>(seconds));
        validate();
    }
}
