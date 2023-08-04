package view;


import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * class for the main ui of the game
 *
 */
public class mainGraphicUI extends JFrame{
	private URL imageURL;
    private ClassLoader cldr;

	private static final long serialVersionUID = 2L;
	
	private static final int maxX = 10;
	private static final int maxY = 8;
	
	private static final int boarderThickes = 3;
	
	private JPanel board;
	private JPanel menue;
	private JButton[][] buttons;
	private JCheckBox noRetreat;
	private JCheckBox halfArmy;
	private JCheckBox pause;
	private JSlider pauseVal;
	private JLabel nameOfTurn;
	private JLabel Wper;
	private JLabel revive;
	private JLabel numOfTurn;
	private JLabel[][] CapturedImages = new JLabel[4][5]; 
	private JLabel numOfCaptured;
	private JButton restart;
	private JButton exit;
	
	
	public JButton[][] getButtons(){return this.buttons;}
	
	public JCheckBox getnoReteat() {return this.noRetreat;}
	
	public JCheckBox gethalfArmy() {return this.halfArmy;}
	
	public JCheckBox getpause() {return this.pause;}
	
	public JSlider getPauseVal() {return this.pauseVal;}
	
	public void setTurn(String s) {
		this.nameOfTurn.setText(s + "'s turn.");
	}
	
	public void setWper(double val) {
		final DecimalFormat df = new DecimalFormat("0.00");
		
		Wper.setText("Win persentage: " + df.format(val) + "%.");
	}
	
	public void setRevive(int val) {
		
		revive.setText("Revives: " + val);
	}
	
	public void setnumOfTurn(int val) {
		
		numOfTurn.setText("Round: " + val);
	}

	/**
	 * @return the capturedImages
	 */
	public JLabel[][] getCapturedImages() {
		return CapturedImages;
	}

	/**
	 * @param capturedImages the capturedImages to set
	 */
	public void setCapturedImages(JLabel[][] capturedImages) {
		CapturedImages = capturedImages;
	}

	/**
	 * @return the numOfCaptured
	 */
	public JLabel getNumOfCaptured() {
		return numOfCaptured;
	}

	/**
	 * @param numOfCaptured the numOfCaptured to set
	 */
	public void setNumOfCaptured(int numOfCaptured) {
		this.numOfCaptured.setText("Captures: " + numOfCaptured);
	}

	/**
	 * <b>constructor</b>: Creates a new gui and intits the basic putons and panels<p>
	 * Postcondition: creates new basic gui
	 */
	public mainGraphicUI() {
		cldr = this.getClass().getClassLoader();
		
		createJFrame();
		createBoardJpanel();
		createMenuJpanel();
	}
	
	/**
	 * creates the jrame of the mainui
	 */
	public void createJFrame(){
		
		this.setTitle("STRATEGO:  ICE VS FIRE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 850);
		this.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		
		imageURL = cldr.getResource("res/other/logo.png");
		ImageIcon logo = new ImageIcon(imageURL);
		this.setIconImage(logo.getImage());
		this.getContentPane().setBackground(Color.BLACK);
		
		
	}
	
	/**
	 * creates the board part of the main ui
	 */
	public void createBoardJpanel() {
		this.board = new JPanel();
		board.setBackground((Color.yellow ));
		board.setBounds(0,0, 800, 800);
		board.setLayout(new GridLayout(8, 10, 1, 1));
		board.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		this.buttons = new JButton[mainGraphicUI.maxY + 1][mainGraphicUI.maxX + 1]; 
		
		for(int x = mainGraphicUI.maxY; x >= 1; x--) {
			for(int y = 1; y <= mainGraphicUI.maxX; y++) {
				this.buttons[x][y] = new JButton(y + ":" + x);
				this.buttons[x][y].setBackground(Color.BLACK);
				this.buttons[x][y].setBounds(200,200,90,90);
				board.add(this.buttons[x][y]);
			}
		}
		
		colorIlegals();
			
		this.add(board, BorderLayout.CENTER);		
	}
	
	/**
	 * creates the menu part of the main ui
	 */
	public void createMenuJpanel() {
		this.menue = new JPanel();
		menue.setLayout(new GridLayout(3,1));
		menue.setBackground((Color.gray ));
		menue.setBounds(800, 0, 390, 800);

		JPanel rules = new JPanel();
		rules.setBackground(Color.BLACK);
		rules.setOpaque(true);
		rules.setSize(300, 300);
		rules.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 0, 10, 0);
		
		JLabel ruleLable = new JLabel("Rule Panel.");
		ruleLable.setBackground(Color.DARK_GRAY);
		ruleLable.setOpaque(true);
		ruleLable.setForeground(Color.BLACK);
		ruleLable.setFont(new Font(Font.SERIF, Font.ITALIC, 30));
		gbc.insets = new Insets(10, 0, 40, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rules.add(ruleLable, gbc);
		gbc.insets = new Insets(10, 0, 10, 0);
		
		this.noRetreat = new JCheckBox("NO RETREAT");
		noRetreat.setBackground(Color.RED);
		noRetreat.setForeground(Color.BLACK);
		noRetreat.setOpaque(true);
		noRetreat.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 3;
		rules.add(noRetreat, gbc);
		
		this.halfArmy = new JCheckBox("HALF ARMY");
		halfArmy.setBackground(Color.GREEN);
		halfArmy.setForeground(Color.BLACK);
		halfArmy.setOpaque(true);
		halfArmy.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 2;
		rules.add(halfArmy, gbc);
		
		this.pause = new JCheckBox("Pause Bewean Plays");
		pause.setBackground(Color.YELLOW);
		pause.setForeground(Color.BLACK);
		pause.setOpaque(true);
		pause.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 4;
		rules.add(pause, gbc);
		
		this.pauseVal = new JSlider(1, 5, 2);
		pauseVal.setBackground(Color.YELLOW);
		pauseVal.setForeground(Color.BLACK);
		pauseVal.setPaintTrack(true);
		pauseVal.setMajorTickSpacing(1);
		pauseVal.setPaintLabels(true);
		pauseVal.setOpaque(true);
		pauseVal.setFocusable(false);
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 5;
		rules.add(pauseVal, gbc);
		gbc.insets = new Insets(10, 0, 10, 0);
		
		menue.add(rules);
		
		
		JPanel statistics = new JPanel();
		statistics.setBackground(Color.blue);
		statistics.setOpaque(true);
		statistics.setSize(300, 300);
		statistics.setLayout(new GridBagLayout());
		gbc.insets = new Insets(10, 0, 10, 0);
		
		JLabel statisticsLable = new JLabel("Game Statistics");
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
		
		this.nameOfTurn = new JLabel("player x turn", SwingConstants.CENTER);
		this.nameOfTurn.setBackground(Color.pink);
		this.nameOfTurn.setOpaque(true);
		this.nameOfTurn.setForeground(Color.BLACK);
		this.nameOfTurn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridy = 1;
		statistics.add(this.nameOfTurn, gbc);
		
		this.Wper = new JLabel("Win persentage : 0.00%", SwingConstants.LEFT);
		this.Wper.setBackground(Color.orange);
		this.Wper.setOpaque(true);
		this.Wper.setForeground(Color.BLACK);
		this.Wper.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridy = 2;
		statistics.add(this.Wper, gbc);
		
		this.revive = new JLabel("Revives: 0");
		this.revive.setBackground(Color.cyan);
		this.revive.setOpaque(true);
		this.revive.setForeground(Color.BLACK);
		this.revive.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		statistics.add(this.revive, gbc);
		
		this.numOfTurn = new JLabel("Round: 0");
		this.numOfTurn.setBackground(Color.green);
		this.numOfTurn.setOpaque(true);
		this.numOfTurn.setForeground(Color.BLACK);
		this.numOfTurn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		gbc.gridx = 1;
		statistics.add(this.numOfTurn, gbc);
		
		menue.add(statistics);
		
		
		JPanel Captured = new JPanel();
		Captured.setBackground(Color.DARK_GRAY);
		Captured.setOpaque(true);
		Captured.setSize(300, 300);
		Captured.setLayout(new GridBagLayout());

		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridwidth = 1;
		
		this.numOfCaptured = new JLabel("Captures: 0");
		this.numOfCaptured.setBackground(Color.DARK_GRAY);
		this.numOfCaptured.setOpaque(true);
		this.numOfCaptured.setForeground(Color.BLACK);
		this.numOfCaptured.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Captured.add(this.numOfCaptured, gbc);
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 4; y++) {
				this.CapturedImages[x][y] = new JLabel("x");
				this.CapturedImages[x][y].setIcon(resizeIcon(new ImageIcon("res/redPieces/redHidden.png"), 50 , 50) );
				this.CapturedImages[x][y].setHorizontalTextPosition(JLabel.RIGHT);
				this.CapturedImages[x][y].setVerticalTextPosition(JLabel.BOTTOM);
				this.CapturedImages[x][y].setForeground(Color.RED);
				gbc.gridx = x;
				gbc.gridy = y;
				Captured.add(this.CapturedImages[x][y], gbc);
			}
		}
		
		gbc.insets = new Insets(0, 0, 0, 0);
		
		this.restart = new JButton("RESTART");
		this.restart.setBackground(Color.BLACK);
		this.restart.setForeground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 3;
		Captured.add(this.restart, gbc);
		
		this.exit = new JButton("EXIT");
		this.exit.setBackground(Color.BLACK);
		this.exit.setForeground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 4;
		Captured.add(this.exit, gbc);
		
		
		
		menue.add(Captured);
		
		this.add(menue, BorderLayout.EAST);
		
	}
	
	/**
	 * sets the color for the illegal squares on the board 
	 */
	public void colorIlegals() {
		imageURL = cldr.getResource("res/other/yellow.jpg");
		this.setImageInButton(3, 4, imageURL);
		this.setImageInButton(3, 5, imageURL);
		this.setImageInButton(4, 4, imageURL);
		this.setImageInButton(4, 5, imageURL);
		
		this.setImageInButton(7, 4, imageURL);
		this.setImageInButton(7, 5, imageURL);
		this.setImageInButton(8, 4, imageURL);
		this.setImageInButton(8, 5, imageURL);
		
	}
	
	/**
	 * sets the image in a button of the main board
	 * 
	 * postcondition: will resize and update the image on the specified button
	 * @param x the horizontal position of the button
	 * @param y the vertical position of the button
	 * @param imageURL the url of the image to place
	 */
	public void setImageInButton(int x, int y, URL imageURL) {
		//using x,y inverted
		ImageIcon icon = new ImageIcon(imageURL); 
		this.buttons[y][x].setIcon(resizeIcon(icon, this.buttons[y][x].getWidth(), this.buttons[y][x].getHeight()));
	}
	
	public void setImageInButton(int x, int y, ImageIcon pathOfImage) {
		//using x,y inverted
		ImageIcon icon = pathOfImage; 
		this.buttons[y][x].setIcon(resizeIcon(icon, this.buttons[y][x].getWidth(), this.buttons[y][x].getHeight()));
	}
	
	/**
	 * resets the image of a button
	 * @param x the horizontal position of the button
	 * @param y the vertical position of the button
	 */
	public void setImageInButtonToDefult(int x, int y) {
		//using x,y inverted
		
		this.buttons[y][x].setIcon(null);
	}
	
	/**
	 * resize the image to fit the button
	 * 
	 * @param icon the icon to resize
	 * @param resizedWidth the with of the button
	 * @param resizedHeight the height of the button
	 * @return the resized image
	 */
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	    Image img = icon.getImage();  
	    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
	    return new ImageIcon(resizedImage);
	}
	
	/**
	 * sets the images int the capture section of the menu
	 * @param x the horizontal position of the image in the grid
	 * @param y y the vertical position of the image in the grid
	 * @param imageURL the image to add
	 * @param num the num of the captured pieces to display next to the image
	 */
	public void setCaptured(int x, int y, URL imageURL, int num) {
		this.CapturedImages[x][y].setIcon(resizeIcon(new ImageIcon(imageURL), 50 , 50) );
		this.CapturedImages[x][y].setText("" + num);
	}
	
	/**
	 * show the possible moves that a player can make to a piece
	 * by outlining the squares it can move too<p>
	 * 
	 * Postcondition:oulines the squars the piece an go to
	 * @param x horizontal possition of the possible move
	 * @param y vertical possition of the possible move
	 */
	public void showPossibleMove(int x, int y) {
		this.getButtons()[y][x].setBorder(BorderFactory.createLineBorder(Color.GREEN, boarderThickes));
	}
	
	
	/**
	 * show the possible moves that a player can make to a piece
	 * by outlining the squares it can attack with red<p>
	 * 
	 * Postcondition:oulines the squars the piece can attack
	 * @param x horizontal possition of the possible move
	 * @param y vertical possition of the possible move
	 */
	public void showPossibleMoveAttack(int x, int y) {
		this.getButtons()[y][x].setBorder(BorderFactory.createLineBorder(Color.RED, boarderThickes));
	}
	
	/**
	 * removes the privious set hightlight of a possible move or attack<p>
	 * 
	 * Postcondition:revoves the ouline of the  squar
	 * 
	 * @param x horizontal possition of the possible move
	 * @param y vertical possition of the possible move
	 */
	public void StopshowPossibleMove(int x, int y) {
		this.getButtons()[y][x].setBorder(null);
	}
	
	/**
	 * this function is called when a player has won
	 * 
	 * postcondition: displays congratulatory graphic to the winner
	 * @param winner player who won
	 */
	public void congratsWinner(String winner) {
		this.displayErrorMessage(winner + " You captured the flag");
	}
	
	/**
	 * there will be moltiple add action listener functions that will add the action listeres of the components in the view
	 * those action listeners will be handled by the condroler.
	 * !!!!!!!!this is just an example of those functions
	 * @param listeningForButon class impimenting actionlisterer created by controler
	 */
	public void addButtonActionlListeners(ActionListener listeningForButon) {
		for(int i = 1; i <= mainGraphicUI.maxX; i++) {
			for(int j = 1; j <= mainGraphicUI.maxY; j++) {
				buttons[j][i].addActionListener(listeningForButon);
			}
		}
	}
	
	public void addExitActionListener(ActionListener listener) {
		this.exit.addActionListener(listener);
	}
	
	public void setRestartAListiner(ActionListener listener) {
		this.restart.addActionListener(listener);
	}
	
	/**
	 * this function is used to desplay error messages to the players
	 * postcondtition: will desplay the error mage giver in a dialog window
	 * @param errorMessage the message to display
	 */
	public void displayErrorMessage(String errorMessage) {
			
			JOptionPane.showMessageDialog(this, errorMessage);
	}
	
}
