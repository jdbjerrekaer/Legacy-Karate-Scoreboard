package Controller;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class CustomSize extends JDialog
        implements WindowListener
{
    private static final long serialVersionUID = 3405825746157476552L;
    private JTextArea value = new JTextArea("100");

    private JButton enter = new JButton("Set");
    private Board parent;

    public CustomSize(Board parent1)
    {
        setTitle("Enter Size");
        this.parent = parent1;

        setSize(255, 120);

        create();
        center();

        setVisible(true);
        setResizable(true);
    }

    public void Ok()
    {
        String number = this.value.getText();

        int result = Integer.parseInt(number);

        this.parent.moni.setSizeMultiplier(result);

        dispose();
    }

    private void create()
    {
        Container cont = getContentPane();
        cont.setLayout(null);

        addComponent(cont, this.value, 20, 20, 87, 40);
        addComponent(cont, this.enter, 130, 20, 75, 40);

        cont.setBackground(Color.black);
        this.enter.setBackground(Color.black);

        this.value.setBackground(Color.lightGray);
        this.value.setFont(new Font("Trebuchet MS", 1, 40));
        this.value.requestFocusInWindow();
        this.value.setCaretPosition(this.value.getText().length());

        this.enter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CustomSize.this.Ok();
            }
        });
        this.enter.setMnemonic(10);
    }

    public void center()
    {
        Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(127, 11);
    }

    public void windowClosed(WindowEvent event)
    {
    }

    public void windowClosing(WindowEvent event)
    {
    }

    public void windowActivated(WindowEvent event)
    {
    }

    public void windowDeactivated(WindowEvent event)
    {
    }

    public void windowOpened(WindowEvent event)
    {
    }

    public void windowIconified(WindowEvent event)
    {
    }

    public void windowDeiconified(WindowEvent event)
    {
    }

    private void addComponent(Container cont, JComponent component, int left, int top, int width, int height)
    {
        cont.add(component);
        component.setBounds(left, top, width, height);
    }

}