package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Turn.Turn;

/**
 * 
 * this class is used to create a instances of the game.
 * itis maily needed for the restart function
 *
 */
public class GameInstance implements ActionListener{
	
	controller controller;
	
	/**
	 * constructor of the game instance
	 * 
	 * postcondition:creates a new constroler and adds the restart Action listerer to the sestart buttons
	 */
	public GameInstance() {
		controller = new controller();
		this.controller.getUi().setRestartAListiner(this);
		this.controller.getWui().addRestartAListiner(this);
	}
	
	/**
	 * action performed when a resart button is pressed
	 * 
	 * postcondition: the privious uies are disposed, the turn count is restarted and 
	 * a new constoler is created with added action listeners to the restart buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.stopSoundTrack();
		controller.getUi().dispose();
		controller.getRui().dispose();
		controller.getWui().dispose();
		Turn.resetCount();
		controller = new controller();
		controller.getUi().setRestartAListiner(this);
		controller.getWui().addRestartAListiner(this);
	}
}
