package Controller;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.swing.*;

public class Monitor extends JFrame implements Runnable
{
	private static final long serialVersionUID = 9121672391459561907L;

	private JTextField red_point = new JTextField("0");
	private JLabel LRD_Cat1 = new JLabel(":Category 1");
	private JLabel LRD_Cat2 = new JLabel(":Category 2");

	private JTextField blue_point = new JTextField("0");
	private JLabel LBU_Cat1 = new JLabel("Category 1:");
	private JLabel LBU_Cat2 = new JLabel("Category 2:");

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

	public JLabel tournamentName = new JLabel("WCK Tournament");
	private String tourName = "WCK Tournament";

	private Board board;

	public int sizeMultiplier = 100;

	public void setSizeMultiplier(int n){
		sizeMultiplier = n;
		create();
	}

	public int getSizeMultiplier(){
		return sizeMultiplier;
	}

	public void setTournamentName(String name){
		this.tourName = name;
		tournamentName.setText(tourName);
		board.msg("Tournament name has been set to: " + tourName);
		create();
	}

	public String getTournamentName(){
		return tourName;
	}

	public Monitor( Board board1 )
	{
		setSize( 1280, 720);
		
		board = board1;

		create();

		setVisible(true);
		setResizable(true);
		
		new Thread(this).start();
		
	}

	public void run()
	{
		try
		{
			while( true )
			{			
				Thread.sleep(100L);
				
				WTime.setText( board.WTime.getText() );
				
				/*current_red.setText( board.txt_current_red );
				next_red.setText( board.txt_next_red );
				current_blue.setText( board.txt_current_blue );
				next_blue.setText( board.txt_next_blue );*/
				
				red_point.setText( board.red_point.getText() );
				cat1_red_show.setText( board.cat1_red_show.getText() );
				cat2_red_show.setText( board.cat2_red_show.getText() );
				
				blue_point.setText( board.blue_point.getText() );
				cat1_blue_show.setText( board.cat1_blue_show.getText() );
				cat2_blue_show.setText( board.cat2_blue_show.getText() );
				
			}
		}
		catch (Exception localException)
		{
		}
	}

	public int getCenter(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = getSize();
		return (int)size.getWidth() / 2;
	}
	
	void create()
	{
		Container cont = getContentPane();
		cont.setLayout(null);
		cont.setBackground(Color.black);
		/*addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){

			}
		});*/
		addComponent(cont, this.tournamentName, (int)(getCenter() - (getTournamentName().length() * (sizeMultiplier / 4.6))), (int)(sizeMultiplier * 5.8), (int)(sizeMultiplier * 8.70), (int)(sizeMultiplier * 0.80)); // 42, 10, 150, 200
		this.tournamentName.setBorder(BorderFactory.createLineBorder(Color.black));
		this.tournamentName.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.70)));
		this.tournamentName.setForeground(Color.gray);

		// ................................................................. \\ 

/*		addComponent(cont, this.blue_score, (int)(sizeMultiplier * 1.4), (int)(sizeMultiplier * 0.4), (int)(sizeMultiplier * 1.75), (int)(sizeMultiplier * 0.5));
		this.blue_score.setBorder(BorderFactory.createLineBorder(Color.black));
		this.blue_score.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.55)));
		this.blue_score.setForeground(Color.blue);
		this.blue_score.setBackground(Color.black);*/

		addComponent(cont, this.blue_point, (int)(sizeMultiplier * 0.15), (int)(sizeMultiplier * 0.5), (int)(sizeMultiplier * 3.10), (int)(sizeMultiplier * 2.50)); // 42, 10, 150, 200
		this.blue_point.setBorder(BorderFactory.createLineBorder(Color.black));
		this.blue_point.setEditable(false);
		this.blue_point.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 2.50)));

/*		addComponent(cont, this.blue_penalty, (int)(sizeMultiplier * 1.15), (int)(sizeMultiplier * 4.05), (int)(sizeMultiplier * 3.00), (int)(sizeMultiplier * 0.60));
		this.blue_penalty.setBorder(BorderFactory.createLineBorder(Color.black));
		this.blue_penalty.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.55)));
		this.blue_penalty.setForeground(Color.blue);
		this.blue_penalty.setBackground(Color.black);*/

		addComponent(cont, this.LBU_Cat1, (int)(sizeMultiplier * 0.2), (int)(sizeMultiplier * 3.60), (int)(sizeMultiplier * 3.00), (int)(sizeMultiplier * 0.60)); // 115, 250, 150, 80
		addComponent(cont, this.cat1_blue_show, (int)(sizeMultiplier * 3.20), (int)(sizeMultiplier * 3.32), (int)(sizeMultiplier * 2.00), (int)(sizeMultiplier * 0.80)); // 230, 250, 200, 80
		this.cat1_blue_show.setEditable(false);
		this.LBU_Cat1.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.50)));
		this.cat1_blue_show.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.80)));


		addComponent(cont, this.LBU_Cat2, (int)(sizeMultiplier * 0.2), (int)(sizeMultiplier * 4.60), (int)(sizeMultiplier * 3.00), (int)(sizeMultiplier * 0.60)); // 115, 360, 150, 80
		addComponent(cont, this.cat2_blue_show, (int)(sizeMultiplier * 3.20), (int)(sizeMultiplier * 4.32), (int)(sizeMultiplier * 2.00), (int)(sizeMultiplier * 0.80)); // 230, 360, 200, 80
		this.cat2_blue_show.setEditable(false);
		this.LBU_Cat2.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.50)));
		this.cat2_blue_show.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.80)));

		/*addComponent(cont, this.red_score, (int)(sizeMultiplier * 12.90), (int)(sizeMultiplier * 4.0), (int)(sizeMultiplier * 1.75), (int)(sizeMultiplier * 5.0));
		this.red_score.setBorder(BorderFactory.createLineBorder(Color.black));
		this.red_score.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 5.5)));
		this.red_score.setForeground(Color.red);
		this.red_score.setBackground(Color.black);*/

		addComponent(cont, this.red_point, (int)(sizeMultiplier * 9.30), (int)(sizeMultiplier * 0.50), (int)(sizeMultiplier * 3.10), (int)(sizeMultiplier * 2.50)); // 800, 10, 150, 200
		this.red_point.setBorder(BorderFactory.createLineBorder(Color.black));
		this.red_point.setEditable(false);
		this.red_point.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 2.50)));

		/*addComponent(cont, this.red_penalty, (int)(sizeMultiplier * 12.10), (int)(sizeMultiplier * 4.05), (int)(sizeMultiplier * 3.00), (int)(sizeMultiplier * 6.0));
		this.red_penalty.setBorder(BorderFactory.createLineBorder(Color.black));
		this.red_penalty.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 5.5)));
		this.red_penalty.setForeground(Color.red);
		this.red_penalty.setBackground(Color.black);*/

		addComponent(cont, this.LRD_Cat1, (int)(sizeMultiplier * 9.80), (int)(sizeMultiplier * 3.60), (int)(sizeMultiplier * 3.00), (int)(sizeMultiplier * 0.60)); // 755, 250, 150, 80
		addComponent(cont, this.cat1_red_show, (int)(sizeMultiplier * 7.50), (int)(sizeMultiplier * 3.32), (int)(sizeMultiplier * 2.00), (int)(sizeMultiplier * 0.80)); // 530, 250, 200, 80
		this.cat1_red_show.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.cat1_red_show.setEditable(false);
		this.LRD_Cat1.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.50)));
		this.cat1_red_show.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.80)));

		addComponent(cont, this.LRD_Cat2, (int)(sizeMultiplier * 9.80), (int)(sizeMultiplier * 4.60), (int)(sizeMultiplier * 3.00), (int)(sizeMultiplier * 0.60)); // 755, 360, 150, 80
		addComponent(cont, this.cat2_red_show, (int)(sizeMultiplier * 7.50), (int)(sizeMultiplier * 4.32), (int)(sizeMultiplier * 2.00), (int)(sizeMultiplier * 0.80)); // 530, 260, 200, 80
		this.cat2_red_show.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.cat2_red_show.setEditable(false);
		this.LRD_Cat2.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.50)));
		this.cat2_red_show.setFont(new Font("Trebuchet MS", 1, (int)(sizeMultiplier * 0.80)));
		
		// Watch \\
		
		addComponent(cont, this.WLabel, (int)(sizeMultiplier * 5.95), (int)(sizeMultiplier * 0.15), (int)(sizeMultiplier * 2.00), (int)(sizeMultiplier * 1.00)); // 370, 15, 180, 40
		this.WLabel.setForeground(Color.gray);
		this.WLabel.setFont(new Font("Verdana", 1, (int)(sizeMultiplier * 0.55)));

		addComponent(cont, this.WTime, (int)(sizeMultiplier * 3.90), (int)(sizeMultiplier * 1.00), (int)(sizeMultiplier * 5.00), (int)(sizeMultiplier * 1.80)); // 370, 45, 250, 87
		this.WTime.setForeground(Color.gray);
		this.WTime.setBackground(Color.black);
		this.WTime.setFont(new Font("Verdana", 1, (int)(sizeMultiplier * 2.00)));
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
		
        
	} // cre

	private void addComponent(Container cont, JComponent component, int left, int top, int width, int height)
	{
		cont.add(component);
		component.setBounds(left, top, width, height);
	}
	
} // cl 
