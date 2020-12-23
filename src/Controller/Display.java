package Controller;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JTextField red_point = new JTextField("0");
    private JLabel LRD_Cat1 = new JLabel("C1:");
    private JLabel LRD_Cat2 = new JLabel("C2:");
    private JLabel LBU_Cat1 = new JLabel(":C1");
    private JLabel LBU_Cat2 = new JLabel(":C2");
    private JTextArea cat1_red_show = new JTextArea("");
    private JTextArea cat2_red_show = new JTextArea("");
    private JTextArea cat1_blue_show = new JTextArea("");
    private JTextArea cat2_blue_show = new JTextArea("");
    private JLabel WLabel = new JLabel("Time");
    public JTextField WTime = new JTextField("2:00");
    public JLabel red_score = new JLabel("Score");
    public JLabel red_penalty = new JLabel("Penalties");
    public JLabel blue_score = new JLabel("Score");
    public JLabel blue_penalty = new JLabel("Penalties");

    private JPanel panelMain;
    private JLabel blue_point;

    private Board board;

    public static void main(String[] args) {
        JFrame display = new JFrame("Hello World");

        display.setContentPane(new Display().panelMain);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.pack();
        display.setVisible(true);
    }

/*    private void createUIComponents() {
        Container cont = getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.black);

        // ................................................................. \\

        addComponent(cont, this.blue_score, 140, 40, 175, 50);
        this.blue_score.setBorder(BorderFactory.createLineBorder(Color.black));
        this.blue_score.setFont(new Font("Trebuchet MS", 1, 55));
        this.blue_score.setForeground(Color.blue);
        this.blue_score.setBackground(Color.black);

        addComponent(cont, this.blue_point, 15, 100, 410, 260); // 42, 10, 150, 200
        this.blue_point.setBorder(BorderFactory.createLineBorder(Color.black));
        this.blue_point.setEditable(false);
        this.blue_point.setFont(new Font("Trebuchet MS", 1, 350));

        addComponent(cont, this.blue_penalty, 115, 405, 300, 60);
        this.blue_penalty.setBorder(BorderFactory.createLineBorder(Color.black));
        this.blue_penalty.setFont(new Font("Trebuchet MS", 1, 55));
        this.blue_penalty.setForeground(Color.blue);
        this.blue_penalty.setBackground(Color.black);

        addComponent(cont, this.LBU_Cat1, 115, 460, 400, 120); // 115, 250, 150, 80
        addComponent(cont, this.cat1_blue_show, 270, 464, 300, 150); // 230, 250, 200, 80
        this.cat1_blue_show.setEditable(false);
        this.LBU_Cat1.setFont(new Font("Trebuchet MS", 1, 100));
        this.cat1_blue_show.setFont(new Font("Trebuchet MS", 1, 100));


        addComponent(cont, this.LBU_Cat2, 115, 560, 400, 150); // 115, 360, 150, 80
        addComponent(cont, this.cat2_blue_show, 270, 580, 300, 150); // 230, 360, 200, 80
        this.cat2_blue_show.setEditable(false);
        this.LBU_Cat2.setFont(new Font("Trebuchet MS", 1, 100));
        this.cat2_blue_show.setFont(new Font("Trebuchet MS", 1, 100));

        addComponent(cont, this.red_score, 1290, 40, 175, 50);
        this.red_score.setBorder(BorderFactory.createLineBorder(Color.black));
        this.red_score.setFont(new Font("Trebuchet MS", 1, 55));
        this.red_score.setForeground(Color.red);
        this.red_score.setBackground(Color.black);

        addComponent(cont, this.red_point, 1160, 100, 410, 260); // 800, 10, 150, 200
        this.red_point.setBorder(BorderFactory.createLineBorder(Color.black));
        this.red_point.setEditable(false);
        this.red_point.setFont(new Font("Trebuchet MS", 1, 350));

        addComponent(cont, this.red_penalty, 1210, 405, 300, 60);
        this.red_penalty.setBorder(BorderFactory.createLineBorder(Color.black));
        this.red_penalty.setFont(new Font("Trebuchet MS", 1, 55));
        this.red_penalty.setForeground(Color.red);
        this.red_penalty.setBackground(Color.black);

        addComponent(cont, this.LRD_Cat1, 1300, 460, 400, 120); // 755, 250, 150, 80
        addComponent(cont, this.cat1_red_show, 1000, 460, 300, 150); // 530, 250, 200, 80
        this.cat1_red_show.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.cat1_red_show.setEditable(false);
        this.LRD_Cat1.setFont(new Font("Trebuchet MS", 1, 100));
        this.cat1_red_show.setFont(new Font("Trebuchet MS", 1, 100));

        addComponent(cont, this.LRD_Cat2, 1300, 540, 400, 200); // 755, 360, 150, 80
        addComponent(cont, this.cat2_red_show, 1000, 580, 300, 150); // 530, 260, 200, 80
        this.cat2_red_show.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.cat2_red_show.setEditable(false);
        this.LRD_Cat2.setFont(new Font("Trebuchet MS", 1, 100));
        this.cat2_red_show.setFont(new Font("Trebuchet MS", 1, 100));

        // Watch \\

        addComponent(cont, this.WLabel, 700, 15, 200, 100); // 370, 15, 180, 40
        this.WLabel.setForeground(Color.gray);
        this.WLabel.setFont(new Font("Verdana", 1, 55));

        addComponent(cont, this.WTime, 530, 100, 500, 180); // 370, 45, 250, 87
        this.WTime.setForeground(Color.gray);
        this.WTime.setBackground(Color.black);
        this.WTime.setFont(new Font("Verdana", 1, 200));
        this.WTime.setEditable(false);

        // ................................................................. \\

        this.red_point.setBackground(Color.black);
        this.cat1_red_show.setBackground(Color.black);
        this.cat2_red_show.setBackground(Color.black);
        this.cat1_red_show.setBorder(BorderFactory.createLineBorder(Color.black));
        this.cat2_red_show.setBorder(BorderFactory.createLineBorder(Color.black));

        this.blue_point.setBackground(Color.black);
        this.cat1_blue_show.setBackground(Color.black);
        this.cat2_blue_show.setBackground(Color.black);
        this.cat1_blue_show.setBorder(BorderFactory.createLineBorder(Color.black));
        this.cat2_blue_show.setBorder(BorderFactory.createLineBorder(Color.black));

        this.WTime.setBorder(BorderFactory.createLineBorder(Color.black));

        this.red_point.setForeground(Color.red);
        this.LRD_Cat1.setForeground(Color.red);
        this.LRD_Cat2.setForeground(Color.red);
        this.cat1_red_show.setForeground(Color.red);
        this.cat2_red_show.setForeground(Color.red);
        this.red_point.setHorizontalAlignment(0);

        // ................................................................. \\

        this.blue_point.setForeground(Color.blue);
        this.LBU_Cat1.setForeground(Color.blue);
        this.LBU_Cat2.setForeground(Color.blue);
        this.cat1_blue_show.setForeground(Color.blue);
        this.cat2_blue_show.setForeground(Color.blue);
        this.blue_point.setHorizontalAlignment(0);

    }

    private void addComponent(Container cont, JComponent component, int left, int top, int width, int height)
    {
        cont.add(component);
        component.setBounds(left, top, width, height);
    }*/

}
