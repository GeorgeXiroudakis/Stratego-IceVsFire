package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * ui for when a player wins
 *
 */
public class winUI extends JFrame{
	
	private JLabel WinerAn;
	private JLabel Wper;
	private JLabel numOfTurn;
	private JButton restart;
	private JButton exit;

	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor: creates a winUi and initializes the assets
	 */
	public winUI() {
		this.setTitle("STRATEGO:  ICE VS FIRE --------- RECAP");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(550, 400);
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.setVisible(false);
		
		JPanel top = new JPanel();
		top.setBackground(Color.BLACK);
		top.setOpaque(true);
		top.setSize(300, 300);
		top.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 0);
		
		this.WinerAn = new JLabel("Congratulation x You won." );
		this.WinerAn.setBackground(Color.DARK_GRAY);
		this.WinerAn.setOpaque(true);
		this.WinerAn.setForeground(Color.BLACK);
		this.WinerAn.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
		top.add(this.WinerAn, gbc);
		this.add(top, BorderLayout.NORTH);
		
		
		JPanel statistics = new JPanel();
		statistics.setBackground(Color.lightGray);
		statistics.setOpaque(true);
		statistics.setSize(300, 300);
		statistics.setLayout(new GridBagLayout());
		gbc.insets = new Insets(10, 0, 10, 0);
		
		JLabel statisticsLable = new JLabel("Here are Some Of Your Statistics.");
		statisticsLable.setBackground(Color.DARK_GRAY);
		statisticsLable.setOpaque(true);
		statisticsLable.setForeground(Color.BLACK);
		statisticsLable.setFont(new Font(Font.SERIF, Font.ITALIC, 30));
		gbc.insets = new Insets(10, 0, 40, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		statistics.add(statisticsLable, gbc);
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridwidth = 1;
		
		this.Wper = new JLabel("You Won x% Of The Atacks You Initiated.", SwingConstants.LEFT);
		this.Wper.setBackground(Color.red);
		this.Wper.setOpaque(true);
		this.Wper.setForeground(Color.BLACK);
		this.Wper.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridy = 2;
		statistics.add(this.Wper, gbc);
		
		this.numOfTurn = new JLabel("You Needed x Turns To Obliterate Your Oponent.");
		this.numOfTurn.setBackground(Color.red);
		this.numOfTurn.setOpaque(true);
		this.numOfTurn.setForeground(Color.BLACK);
		this.numOfTurn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		statistics.add(this.numOfTurn, gbc);
		
		JLabel anotherTry = new JLabel("Consider Giving your oponent Another try by Restart, or Exit.");
		anotherTry.setBackground(Color.red);
		anotherTry.setOpaque(true);
		anotherTry.setForeground(Color.BLACK);
		anotherTry.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridy = 3;
		gbc.insets = new Insets(30, 0, 0, 0);
		statistics.add(anotherTry, gbc);
		
		this.restart = new JButton("RESTART");
		this.restart.setBackground(Color.BLACK);
		this.restart.setForeground(Color.RED);
		this.restart.setFocusable(false);
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 0, 10, 0);
		statistics.add(this.restart, gbc);
		
		this.add(statistics, BorderLayout.CENTER);
		
		
		/////////poses fores tha mporoyse na nikhsei
		

		this.exit = new JButton("EXIT");
		this.exit.setBackground(Color.BLACK);
		this.exit.setForeground(Color.RED);
		this.exit.setFocusable(false);
		this.add(this.exit, BorderLayout.SOUTH);
		
		
	}
	
	
	/**
	 * @param s the name to set
	 */
	public void setNameOfWiner(String s) {
		this.WinerAn.setText("Congratulation " + s + " You won.");
	}


	/**
	 * @param turns num of Turn to set
	 */
	public void setNumOfTurn(int turns) {
		this.numOfTurn.setText("You Needed " + turns + " Turns To Obliterate Your Oponent.");
	}
	
	public void setWper(double wper) {
		this.Wper.setText("You Won " + wper + "% of The Attacks You Initiated.");
	}


	public void addRestartAListiner(ActionListener listener) {
		this.restart.addActionListener(listener);
	}
	
	public void addExitAListener(ActionListener listener) {
		this.exit.addActionListener(listener);
	}

}
