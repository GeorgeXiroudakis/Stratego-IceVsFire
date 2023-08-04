package model.Turn;

import java.util.ArrayList;

import model.board.MyPosition;
import model.pieces.MoveablePiece;
import model.pieces.piece;
import model.player.*;

/**
 * in each turn a new instance of class in created 
 * and it contains all the  of each turn.<p>
 * 
 * the static count keeps count of the turn created add is incremented only by the constructor
 */
public class Turn {
	private static int count = 0;
	private player player;
	private boolean selectedCard;
	private boolean choosingAgain;
	private piece PieceSelected;
	private boolean Attacing;
	private ArrayList<MyPosition> possiblePositions;
	private MyPosition attackOnlyPossition;
	private player playerReviving;
	private MoveablePiece pieceReviving;
	private boolean playDone;
	
	/**
	 * constructor of turn : creates a new turn with with the player given and setes the boolean vlauers to false<P>
	 * 
	 * Postcondion: creates a new turn with the parameters, ads one to the count.
	 * 
	 */
	public Turn(player player) {
		
		this.setPlayer(player);
		player.setHis_turn(true);
		
		this.setSelectedCard(false);
		this.setChoosingAgain(false);
		this.setPieceSelected(null);
		this.setAttacing(false);
		this.setPossiblePositions(null);
		this.setAttackOnlyPossition(null);
		this.setPlayDone(false);
		this.setPlayerReviving(null);
		this.setPieceReviving(null);
		
		
		count++;//add a turn(only way to increment count is through that constructor
	}

	/**
	 * @return the player
	 */
	public player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(player player) {
		this.player = player;
	}

	/**
	 * @return the selectedCard
	 */
	public boolean hasSelectedCard() {
		return selectedCard;
	}

	/**
	 * @param selectedCard the selectedCard to set
	 */
	public void setSelectedCard(boolean selectedCard) {
		this.selectedCard = selectedCard;
	}

	/**
	 * @return the possiblePositions
	 */
	public ArrayList<MyPosition> getPossiblePositions() {
		return possiblePositions;
	}

	/**
	 * @param possiblePositions the possiblePositions to set
	 */
	public void setPossiblePositions(ArrayList<MyPosition> possiblePositions) {
		this.possiblePositions = possiblePositions;
	}

	/**
	 * @return the pieceSelected
	 */
	public piece getPieceSelected() {
		return PieceSelected;
	}

	/**
	 * @param pieceSelected the pieceSelected to set
	 */
	public void setPieceSelected(piece pieceSelected) {
		PieceSelected = pieceSelected;
	}

	/**
	 * @return the choosingAgain
	 */
	public boolean ischoosingAgain() {
		return choosingAgain;
	}

	/**
	 * @param choosingAgain the choosingAgain to set
	 */
	public void setChoosingAgain(boolean choosingAgain) {
		this.choosingAgain = choosingAgain;
	}

	/**
	 * @return the attacing
	 */
	public boolean isAttacing() {
		return Attacing;
	}

	/**
	 * @param attacing the attacing to set
	 */
	public void setAttacing(boolean attacing) {
		Attacing = attacing;
	}

	/**
	 * @return the attackOnlyPossition
	 */
	public MyPosition getAttackOnlyPossition() {
		return attackOnlyPossition;
	}

	/**
	 * @param attackOnlyPossition the attackOnlyPossition to set
	 */
	public void setAttackOnlyPossition(MyPosition attackOnlyPossition) {
		this.attackOnlyPossition = attackOnlyPossition;
	}

	/**
	 * @return the playerReviving
	 */
	public player getPlayerReviving() {
		return playerReviving;
	}

	/**
	 * @param playerReviving the playerReviving to set
	 */
	public void setPlayerReviving(player playerReviving) {
		this.playerReviving = playerReviving;
	}

	/**
	 * @return the pieceReviving
	 */
	public MoveablePiece getPieceReviving() {
		return pieceReviving;
	}

	/**
	 * @param pieceReviving the pieceReviving to set
	 */
	public void setPieceReviving(MoveablePiece pieceReviving) {
		this.pieceReviving = pieceReviving;
	}

	/**
	 * @return the playDone
	 */
	public boolean getPlayDone() {
		return playDone;
	}

	/**
	 * @param playDone the playDone to set
	 */
	public void setPlayDone(boolean playDone) {
		this.playDone = playDone;
	}
	
	/**
	 * static function that returns the count of the turns created<p>
	 * postcondtition: returns the count of the turns created
	 * 
	 * @return count of the turns
	 */
	public static int getCount() {
		return count;
	}
	
	/**
	 * resets the static variable turn count (used when restarting the game)<p>
	 * postcondition: the static variable is reseted to 0;
	 */
	public static void resetCount() {count = 0;}
	
}
