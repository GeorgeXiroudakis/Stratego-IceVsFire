package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *the ui for when a player is reviving
 *
 */
public class reviveUI extends JFrame{
	private URL imageURL;
    private ClassLoader cldr;
	
	private static final long serialVersionUID = 1L;
	private JButton[][] buttons = new JButton[4][5];
	private JButton close;
	
	public JButton[][] getButtons() {return this.buttons;}
	
	/**
	 * constructor: for the revive Ui
	 */
	public reviveUI() {
		cldr = this.getClass().getClassLoader();
		
		this.setTitle("STRATEGO:  ICE VS FIRE --------- REVIVE");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500, 550);
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(false);
		
		imageURL = cldr.getResource("res/other/revive.png");
		ImageIcon logo = new ImageIcon(imageURL);
		this.setIconImage(logo.getImage());
		this.getContentPane().setBackground(Color.BLACK);
		
		JLabel ruleLable = new JLabel("Please Choose a Troop To Revive OR Exit");
		ruleLable.setBackground(Color.DARK_GRAY);
		ruleLable.setOpaque(true);
		ruleLable.setForeground(Color.BLACK);
		ruleLable.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
		this.add(ruleLable, BorderLayout.NORTH);
		
		JPanel butonsPanel = new JPanel();
		butonsPanel.setBackground(Color.DARK_GRAY);
		butonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 4; y++) {
				
				if((x == 3 && y ==4) || x == 3 && y == 3)continue;
				
				this.buttons[x][y] = new JButton();
				this.buttons[x][y].setBackground(Color.BLACK);
				this.buttons[x][y].setForeground(Color.RED);
				
				this.buttons[x][y].setHorizontalTextPosition(JLabel.RIGHT);
				this.buttons[x][y].setVerticalTextPosition(JLabel.BOTTOM);
				this.buttons[x][y].setFont(new Font(Font.SERIF, Font.ITALIC, 20));
				
				gbc.gridx = x;
				gbc.gridy = y;
				butonsPanel.add(this.buttons[x][y], gbc);
			}
		}
		
		this.add(butonsPanel, BorderLayout.CENTER);
		
		this.close = new JButton("Exit");
		this.close.setBackground(Color.BLACK);
		this.close.setForeground(Color.RED);
		this.close.setFocusable(false);
		
		this.add(this.close, BorderLayout.SOUTH);
		
	}
	
	
	
	/**
	 * sets the images on the grid
	 * @param x
	 * @param y
	 * @param imageURL
	 * @param num to add next to the picture
	 */
	public void setImageInButton(int x, int y, URL imageURL, int num) {
		ImageIcon icon = new ImageIcon(imageURL); 
		this.buttons[x][y].setIcon(resizeIcon(icon, 100, 100));
		this.buttons[x][y].setText("" + num);
	}
	
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	    Image img = icon.getImage();  
	    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
	    return new ImageIcon(resizedImage);
	}
	
	/**
	 * adds actionlisteners to the buttons
	 * @param listener the listener to add
	 */
	public void addReviveActionListeners(ActionListener listener) {
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 4; y++) {
				
				if((x == 3 && y ==4) || x == 3 && y == 3)continue;
				
				this.buttons[x][y].addActionListener(listener);
			}
		}
	}
	
	public void addExitActionListeners(ActionListener listener) {
		this.close.addActionListener(listener);
	}

}
