package Controller;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Fighter.CurrentBlue;
import Fighter.CurrentRed;
import Fighter.NextBlue;
import Fighter.NextRed;
import Fighter.Temp;

public class Board extends JFrame implements Runnable, WindowListener
{
	private static final long serialVersionUID = 4867875935283748663L;

	// Navne til knapper \\
	
	private JButton point1_red = new JButton("+ 1 Point");
	private JButton point2_red = new JButton("+ 2 Point");
	private JButton point3_red = new JButton("+ 3 Point");
	private JButton cat1_red = new JButton("+ C1");
	private JButton cat2_red = new JButton("+ C2");

	// new \\

	public String txt_current_red = "Fighter 1";
	public String txt_next_red = "Fighter 3";

	public String txt_current_blue = "Fighter 2";
	public String txt_next_blue = "Figther 4";
	
	private JButton point1_blue = new JButton("+ 1 Point");
	private JButton point2_blue = new JButton("+ 2 Point");
	private JButton point3_blue = new JButton("+ 3 Point");
	private JButton cat1_blue = new JButton("+ C1");
	private JButton cat2_blue = new JButton("+ C2");

	public JTextField red_point = new JTextField("0");
	private JLabel LRD_Cat1 = new JLabel("C1:");
	private JLabel LRD_Cat2 = new JLabel("C2:");

	JTextField blue_point = new JTextField("0");
	private JLabel LBU_Cat1 = new JLabel(":C1");
	private JLabel LBU_Cat2 = new JLabel(":C2");

	JTextArea cat1_red_show = new JTextArea("");
	JTextArea cat2_red_show = new JTextArea("");

	JTextArea cat1_blue_show = new JTextArea("");
	JTextArea cat2_blue_show = new JTextArea("");
	
	private String showmin = "";

	public int[] redPoint = new int[3];
	public int[] bluePoint = new int[3];
	///////////////////////////////////
	public int[] redTemp = new int[3];
	public int[] blueTemp = new int[3];

	private JButton min1 = new JButton("1:30 min");
	private JButton min2 = new JButton("2 min");
	private JButton min3 = new JButton("3 min");
	private JButton min4 = new JButton("4 min");
	private JButton start = new JButton();
	private JButton reset = new JButton("Reset");
	private JButton ot = new JButton("Custom");
	private JButton size = new JButton("Size");
	private JButton tournamentButton = new JButton("Tournament");

	private Icon icon = new ImageIcon("files//arrow.jpg");
	private JButton back = new JButton("Undo"/*, this.icon*/);

	private JTextField dummy = new JTextField("");
	private String file = "files//beep.wav";	
	
	private JButton beep = new JButton("Beep 1");
	private JButton beep2 = new JButton("Beep 2");

	private boolean overtime = false;

	private int limiten = 1;
	private int limitone = 1;
	
	private boolean finish = false;
	
	private JLabel WLabel = new JLabel("Time");
	public JTextField WTime = new JTextField("2:00");

	public int min = 1;
	public int sec = 60;
	
	private int savedmin = 1;
	private int savedsec = 60;

	private boolean pause = true;	

	private ArrayList<Temp> list = new ArrayList<Temp>();
	
	private Rules rules;
	Monitor moni;
	
	public Board()
	{
		moni = new Monitor( this );
		
		setMinimumSize(new Dimension(560, 520));
		
		this.start.setText("START");

		rules = new Rules(this);
		
		create();
		center();

		setVisible(true);
		setResizable(true);

		new Thread(this).start();
	}
	
	private void Reset()
	{
		this.pause = true;
		
		this.overtime = false;
		
		this.finish = false;		

		this.red_point.setText("0");
		this.cat1_red_show.setText(" ");
		this.cat2_red_show.setText(" ");

		this.blue_point.setText("0");
		this.cat1_blue_show.setText(" ");
		this.cat2_blue_show.setText(" ");

		this.redPoint[0] = 0;
		this.redPoint[1] = 0;
		this.redPoint[2] = 0;

		this.bluePoint[0] = 0;
		this.bluePoint[1] = 0;
		this.bluePoint[2] = 0;

		list.clear();

		if (overtime == false)
		{
			this.WTime.setText(this.savedmin + 1 + ":00");
		}
		if (overtime == true)
		{
			this.WTime.setText(this.showmin);
		}

		min = savedmin; 
		sec = savedsec;
		
		// end \\
		this.limiten = 1;
		this.limitone = 1;		

		this.start.setText("START");

		this.WTime.setForeground(Color.gray);
		moni.WTime.setForeground(Color.gray);

	}	
	
	private void tick()
	{
		try
		{
			Watch( --this.sec );
		}
		catch (Exception localException)
		{
		}
	}

	private void Watch(int sek)
	{
		String toString = "";

		if ((this.min == 0) && (this.sec < 16))
		{
			this.WTime.setForeground(Color.yellow);
			moni.WTime.setForeground(Color.yellow);
		}

		if (sek <= 9)
		{
			toString = "" + sek + "";
			this.WTime.setText(this.min + ":0" + toString);
		}

		if ((sek <= 59) && (sek >= 10))
		{
			toString = "" + sek + "";
			this.WTime.setText(this.min + ":" + toString);
		}

		if ( this.min == 0 && (this.sec <= 15) && (this.limiten > 0))
		{
			this.limiten -= 1;
			Audio();
		}

		if (sek != 0)
			return;

		if ((this.min == 0) && (this.sec == 0))
		{
			this.finish = true;
			this.pause = true;

			this.WTime.setForeground(Color.gray);
			moni.WTime.setForeground(Color.gray);			
			
			this.limiten = 1;
			this.limitone = 1;			
			
			return;
		}

		this.sec = 60;
		this.min -= 1;

	}

	public void run()
	{
		try
		{
			while( true )
			{			
				Thread.sleep(1000L);

				timer();
				
				this.dummy.requestFocusInWindow();
				
				if( (this.min == 0) && (this.sec == 0) && finish == true && limitone > -1 )
				{
					this.limitone -= 1;
					Audio();
				}				
				
			}
		}
		catch (Exception localException)
		{
		}
	}	  

	private void create()
	{
		Container cont = getContentPane();
		cont.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 6));

		panel.add(this.point3_red);
		panel.add(this.point2_red);
		panel.add(this.point1_red);
		panel.add(this.point1_blue);
		panel.add(this.point2_blue);
		panel.add(this.point3_blue);

		/*panel.add(this.current_red);*/
		panel.add(new JLabel(""));
		panel.add(this.cat2_red);
		panel.add(this.cat1_red);
		panel.add(this.cat1_blue);
		panel.add(this.cat2_blue);
		panel.add(new JLabel(""));
		/*panel.add(this.current_blue);*/

		
		/*panel.add(this.next_red);*/
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(this.start);
		panel.add(this.back);
		panel.add(new JLabel(""));
		panel.add(this.beep);
		/*panel.add(this.next_blue);*/


		panel.add(this.tournamentButton);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(this.beep2);

		panel.add(this.reset);
		/*panel.add(new JLabel(""));*/
		panel.add(this.size);
		panel.add(this.ot);
		panel.add(this.min1);
		panel.add(this.min2);
		panel.add(this.min3);
		/*panel.add(new JLabel(""));*/
		/*panel.add(this.min4);*/
		
		back.setToolTipText("previous");

		addComponent(cont, panel, 22, 290, 500, 180);

		addComponent(cont, this.red_point, 30, 10, 115, 140);
		this.red_point.setBorder(BorderFactory.createLineBorder(Color.black));
		this.red_point.setEditable(false);

		addComponent(cont, this.LRD_Cat1, 80, 160, 60, 40);
		addComponent(cont, this.cat1_red_show, 135, 162, 95, 40);
		this.cat1_red_show.setEditable(false);

		addComponent(cont, this.LRD_Cat2, 80, 212, 60, 40);
		addComponent(cont, this.cat2_red_show, 135, 212, 95, 40);
		this.cat2_red_show.setEditable(false);

		addComponent(cont, this.blue_point, 410, 10, 115, 140);
		this.blue_point.setBorder(BorderFactory.createLineBorder(Color.black));
		this.blue_point.setEditable(false);

		addComponent(cont, this.LBU_Cat1, 405, 160, 60, 40);
		addComponent(cont, this.cat1_blue_show, 320, 160, 85, 40);
		this.cat1_blue_show.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.cat1_blue_show.setEditable(false);

		addComponent(cont, this.LBU_Cat2, 405, 210, 60, 40);
		addComponent(cont, this.cat2_blue_show, 320, 210, 85, 40);
		this.cat2_blue_show.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.cat2_blue_show.setEditable(false);

		addComponent(cont, this.WLabel, 210, 10, 100, 60);
		this.WLabel.setForeground(Color.gray);

		addComponent(cont, this.WTime, 210, 45, 160, 50);
		this.WTime.setForeground(Color.gray);
		this.WTime.setBackground(Color.black);
		this.WTime.setFont(new Font("Verdana", 1, 55));
		this.WTime.setEditable(false);

		this.start.setForeground(Color.black);
		this.start.setBackground(Color.black);
		this.reset.setForeground(Color.black);
		this.ot.setForeground(Color.gray);

		cont.setBackground(Color.black);
		panel.setBackground(Color.black);
		this.point1_red.setBackground(Color.black);
		this.point2_red.setBackground(Color.black);
		this.point3_red.setBackground(Color.black);
		this.point1_blue.setBackground(Color.black);
		this.point2_blue.setBackground(Color.black);
		this.point3_blue.setBackground(Color.black);
		this.cat1_red.setBackground(Color.black);
		this.cat2_red.setBackground(Color.black);
		this.cat1_blue.setBackground(Color.black);
		this.cat2_blue.setBackground(Color.black);
		this.reset.setBackground(Color.black);
		this.ot.setBackground(Color.black);
		this.back.setBackground(Color.black);
		/*this.min1.setBackground(Color.black);*/
		this.min2.setBackground(Color.black);
		this.min3.setBackground(Color.black);
		/*this.min4.setBackground(Color.black);*/
		this.beep.setBackground(Color.black);
		this.beep2.setBackground(Color.black);
		
		/*this.current_blue.setBackground(Color.black);
		this.next_blue.setBackground(Color.black);
		this.current_red.setBackground(Color.black);
		this.next_red.setBackground(Color.black);*/

		this.red_point.setBackground(Color.black);
		this.cat1_red_show.setBackground(Color.black);
		this.cat2_red_show.setBackground(Color.black);
		this.cat1_red_show.setBorder(BorderFactory.createLineBorder(Color.black));
		this.cat2_red_show.setBorder(BorderFactory.createLineBorder(Color.black));
		this.dummy.setBackground(Color.black);
		this.dummy.setBorder(BorderFactory.createLineBorder(Color.black));

		this.blue_point.setBackground(Color.black);
		this.cat1_blue_show.setBackground(Color.black);
		this.cat2_blue_show.setBackground(Color.black);
		this.cat1_blue_show.setBorder(BorderFactory.createLineBorder(Color.black));
		this.cat2_blue_show.setBorder(BorderFactory.createLineBorder(Color.black));

		this.WTime.setBorder(BorderFactory.createLineBorder(Color.black));

		this.start.setForeground(Color.black);
		
		/*this.min1.setForeground(Color.lightGray);*/
		this.min2.setForeground(Color.black);
		this.min3.setForeground(Color.black);
		/*this.min4.setForeground(Color.lightGray);*/
		this.beep.setForeground(Color.black);
		this.beep2.setForeground(Color.black);
		this.ot.setForeground(Color.black);
		
		/*this.current_red.setForeground(Color.red);
		this.next_red.setForeground(Color.red);
		
		this.current_blue.setForeground(Color.blue);
		this.next_blue.setForeground(Color.blue);*/
		
		this.point1_red.setForeground(Color.red);
		this.point2_red.setForeground(Color.red);
		this.point3_red.setForeground(Color.red);
		this.cat1_red.setForeground(Color.red);
		this.cat2_red.setForeground(Color.red);
		this.red_point.setForeground(Color.red);
		this.LRD_Cat1.setForeground(Color.red);
		this.LRD_Cat2.setForeground(Color.red);
		this.cat1_red_show.setForeground(Color.red);
		this.cat2_red_show.setForeground(Color.red);
		this.red_point.setHorizontalAlignment(0);

		this.red_point.setFont(new Font("Trebuchet MS", 1, 100));
		this.LRD_Cat1.setFont(new Font("Trebuchet MS", 1, 34));
		this.cat1_red_show.setFont(new Font("Trebuchet MS", 1, 34));
		this.LRD_Cat2.setFont(new Font("Trebuchet MS", 1, 34));
		this.cat2_red_show.setFont(new Font("Trebuchet MS", 1, 34));

		this.blue_point.setFont(new Font("Trebuchet MS", 1, 100));
		this.LBU_Cat1.setFont(new Font("Trebuchet MS", 1, 34));
		this.cat1_blue_show.setFont(new Font("Trebuchet MS", 1, 34));
		this.LBU_Cat2.setFont(new Font("Trebuchet MS", 1, 34));
		this.cat2_blue_show.setFont(new Font("Trebuchet MS", 1, 34));

		this.point1_blue.setForeground(Color.blue);
		this.point2_blue.setForeground(Color.blue);
		this.point3_blue.setForeground(Color.blue);
		this.cat1_blue.setForeground(Color.blue);
		this.cat2_blue.setForeground(Color.blue);
		this.blue_point.setForeground(Color.blue);
		this.LBU_Cat1.setForeground(Color.blue);
		this.LBU_Cat2.setForeground(Color.blue);
		this.cat1_blue_show.setForeground(Color.blue);
		this.cat2_blue_show.setForeground(Color.blue);
		this.blue_point.setHorizontalAlignment(0);

		addComponent(cont, this.dummy, 10, 10, 10, 2);
		this.dummy.requestFocusInWindow();

		addWindowListener(this);

		Listeners();
	}

	private void RestoreNew()
	{
		Temp temp = null;
		
		if(list.size() > 0)
		{
		 	temp = list.get( list.size()-1 );
		}
		
		this.red_point.setText(""+temp.getRedpoint()+"");
		this.cat1_red_show.setText(Cat(temp.getRedcat1()));
		this.cat2_red_show.setText(Cat(temp.getRedcat2()));

		this.blue_point.setText(""+temp.getBlupoint()+"");
		this.cat1_blue_show.setText(Cat(temp.getBlucat1()));
		this.cat2_blue_show.setText(Cat(temp.getBlucat2()));
		
		this.redPoint[0] = temp.getRedpoint();
		this.redPoint[1] = temp.getRedcat1();
		this.redPoint[2] = temp.getRedcat2();

		this.bluePoint[0] = temp.getBlupoint();
		this.bluePoint[1] = temp.getBlucat1();
		this.bluePoint[2] = temp.getBlucat2();
		
		list.remove(list.size()-1);
	}
	
	private void TempNew()
	{
		list.add( new Temp( redPoint[0], redPoint[1], redPoint[2], bluePoint[0], bluePoint[1], bluePoint[2] ) );
	}
	
	/*private void CurrentRed()
	{
		CurrentRed obj = new CurrentRed(this);
	}
	
	private void CurrentBlue()
	{
		CurrentBlue obj = new CurrentBlue(this);
	}
	
	private void NextRed()
	{
		NextRed obj = new NextRed(this);
	}		
	
	private void NextBlue()
	{
		NextBlue obj = new NextBlue(this);
	}	*/
	
	private void Listeners()
	{
		this.start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.Go();
			}
		});
		this.point1_red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.redPoint(1);
			}
		});
		this.point2_red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.redPoint(2);
			}
		});
		this.point3_red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.redPoint(3);
			}
		});
		this.cat1_red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.redCat1();
			}
		});
		this.cat2_red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.redCat2();
			}
		});
		this.point1_blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.bluePoint(1);
			}
		});
		this.point2_blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.bluePoint(2);
			}
		});
		this.point3_blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.bluePoint(3);
			}
		});
		this.cat1_blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.blueCat1();
			}
		});
		this.cat2_blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				TempNew();
				rules.blueCat2();
			}
		});
		this.min1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.overtime = false;
				Board.this.Min123(1, 30);
				Board.this.WTime.setText("1:30");
				Board.this.start.setText("START");
				Board.this.WTime.setForeground(Color.gray);
				moni.WTime.setForeground(Color.gray);
			}
		});
		this.min2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.overtime = false;
				Board.this.Min123(1, 60);
				Board.this.WTime.setText("2:00");
				Board.this.start.setText("START");
				Board.this.WTime.setForeground(Color.gray);
				moni.WTime.setForeground(Color.gray);
			}
		});
		this.min3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.overtime = false;
				Board.this.Min123(2, 60);
				Board.this.WTime.setText("3:00");
				Board.this.start.setText("START");
				Board.this.WTime.setForeground(Color.gray);
				moni.WTime.setForeground(Color.gray);
			}
		});
		this.min4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.overtime = false;
				Board.this.Min123(3, 60);
				Board.this.WTime.setText("4:00");
				Board.this.start.setText("START");
				Board.this.WTime.setForeground(Color.gray);
				moni.WTime.setForeground(Color.gray);
			}
		});
		this.reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.Reset();
			}
		});
		this.back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.RestoreNew();
			}
		});
		this.ot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.OverTime();
			}
		});
		this.ot.setMnemonic(67);

		this.size.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//moni.setSizeMultiplier(20);
				Board.this.customSize();
				// Board.this.msg("Size: " + moni.getSizeMultiplier());
			}
		});

		this.tournamentButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.customName();
				// Board.this.msg("Tournament name has been set to: " + moni.getTournamentName());
			}
		});

		this.beep.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.Sound("files//beep.wav");
			}
		});
		this.beep2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Board.this.Sound("files//beep2.wav");
			}
		});
		
	} // listeners 

	public void msg( String txt )
	{
		JOptionPane.showMessageDialog( this, txt, " ", 2 );
	}

	private void Sound(String file1)
	{
		this.file = file1;
	}	
	
	private void Go()
	{
		this.start.setBackground(Color.black);

		if (!this.pause)
		{
			this.start.setText("START");
			this.pause = true;
			return;
		}
		if (!this.pause)
			return;

		this.start.setText("PAUSE");
		this.pause = false;

		return;
	}

	private void timer()
	{
		if(this.pause == true) 
			return; 

		tick();
	}

	private String Cat(int value)
	{
		if (value == 1) return "X";
		if (value == 2) return "XX";
		if (value == 3) return "XX";
		if (value == 4) return "XXX";
		if (value == 5) return "XXXX";
		if (value == 6) return "XXXXX";

		return "";
	}

	private void Audio()
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch (Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void setOT(String overtime1)
	{
		this.overtime = true;

		String min1 = overtime1.substring(0, 1);
		String sek1 = overtime1.substring(2, 4);

		Integer int_min = Integer.valueOf(Integer.parseInt(min1));
		Integer int_sek = Integer.valueOf(Integer.parseInt(sek1));

		if (int_sek.intValue() > 60)
		{
			msg("Max 60 sek");
			return;
		}

		if ((int_min.intValue() == 0) && (int_sek.intValue() < 16))
		{
			this.WTime.setForeground(Color.yellow);
		}
		
		if (((int_min.intValue() == 0) && (int_sek.intValue() > 15)) || (int_min.intValue() > 0))
		{
			this.WTime.setForeground(Color.gray);
		}
		
		Min123(int_min.intValue(), int_sek.intValue());
	}

	private void Min123(int value, int value2)
	{
		this.min = value;
		this.savedmin = this.min;

		this.sec = value2;
		this.savedsec = value2;

		String temp = "00";

		if (value2 == 0)
		{
			this.min -= 1;

			this.WTime.setText(value + ":" + temp);
			this.showmin = (value + ":" + temp);

			this.savedmin = this.min;
			this.sec = 60;
			this.savedsec = 60;
			return;
		}

		if (value2 < 10)
		{
			this.WTime.setText(this.min + ":0" + value2);
			this.showmin = (this.min + ":0" + value2);
			return;
		}

		this.WTime.setText(this.min + ":" + value2);
		this.showmin = (this.min + ":" + value2);
	}

	private void OverTime()
	{
		Custom obj = new Custom(this);
	}

	private void customSize(){
		CustomSize obj = new CustomSize(this);
	}

	private void customName() {
		CustomTournament obj = new CustomTournament(this);
	}

	public void center()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = getSize();
		screenSize.height /= 2;
		screenSize.width /= 2;
		size.height /= 2;
		size.width /= 2;

		int y = 10;
		int x = screenSize.width - size.width - 45;

		setLocation(x, y);
	}

	private void addComponent(Container cont, JComponent component, int left, int top, int width, int height)
	{
		cont.add(component);
		component.setBounds(left, top, width, height);
	}

	public void windowClosed(WindowEvent event)
	{
		System.exit(0);
	}
	
	public void windowClosing(WindowEvent event)
	{
		System.exit(0);
	}

	public void windowDeiconified(WindowEvent event)
	{
		
	}

	public void windowDeactivated(WindowEvent event)
	{
		
	}

	public void windowActivated(WindowEvent event)
	{
		
	}

	public void windowOpened(WindowEvent event)
	{
		
	}

	public void windowIconified(WindowEvent e)
	{
		
	}
	
} // cl