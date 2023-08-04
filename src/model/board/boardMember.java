package model.board;

import model.pieces.*;

/**
 * 
 * struct that contains all the information that its tile of the board must have
 *
 */
public class boardMember {
	private piece piece;
	private boolean canMoveTo;
	private boolean isLegal;
	
	/**
	 * @return the piece
	 */
	public piece getPiece() {
		return piece;
	}
	/**
	 * @param piece the piece to set
	 */
	public void setPiece(piece piece) {
		this.piece = piece;
	}
	/**
	 * @return the canMoveTo
	 */
	public boolean CanMoveTo() {
		return canMoveTo;
	}
	/**
	 * @param canMoveTo the canMoveTo to set
	 */
	public void setCanMoveTo(boolean canMoveTo) {
		this.canMoveTo = canMoveTo;
	}
	/**
	 * @return the isLegal
	 */
	public boolean isLegal() {
		return isLegal;
	}
	/**
	 * @param isLegal the isLegal to set
	 */
	public void setLegal(boolean isLegal) {
		this.isLegal = isLegal;
	}
	
}
