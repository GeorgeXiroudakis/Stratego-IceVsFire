package model.board;

import model.pieces.piece;

/**
 * This class represents a position on the board<p>
 * 
 * 
 *
 */
public class MyPosition {
	final int maxX = 10;
	final int maxY = 8;
	
	private int x;
	private int y;
	
	
	/**
	 * Constactor of position<p>
	 * 
	 * @param x The horizontal position  
	 * @param y The vertical position
	 * 
	 * Preconditions: The x and y must be in the bounds of the board,
	 * those bounds are set as a final veritable.<p>
	 * 
	 * Postconditions: Creates a position with the given horizontal
	 * and vertical values
	 * 
	 * @throws IllegalArgumentException if the preconditions are not met
	 */
	public MyPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	/**
	 * geter for horizontal parameter.
	 * @return
	 */
	public int getX() {return this.x;}
	
	/**
	 * geter for vertical parameter.
	 * @return
	 */
	public int getY() {return this.y;}
	
	/**
	 * sets the Position from another position
	 * @param position
	 */
	public void setPosition(MyPosition position){
		
		if ( !( ( (position.getX() >= 1) && (x <= maxX) )  && 
				( (position.getY() >= 1) && (y <= maxY) ) 
				) ) 
		{
			throw new IllegalArgumentException();
		}
		
		this.x = position.getX();
		this.y = position.getY();
	}
	
	/**
	 * sets the Position from a horizontal and a vertical value
	 * @param x horizontal possition
	 * @param y vertical possition
	 */
	public void setPosition(int x, int y){
		
		if ( !( ( (x >= 1) && (x <= maxX) )  && 
				( (y >= 1) && (y <= maxY) ) 
				) ) 
		{
			throw new IllegalArgumentException();
		}
		
		this.x = x;
		this.y = y;
	}
	
	
	
	/**
	 * This function determins if a x,y compination is legal
	 * (does not fall int the yellow squars in the midle of the board or out of bounds) 
	 * @return true if its legal, false if its illegal.
	 */
	public boolean isLegalPosition() {
		if ( !( ( (this.x >= 1) && (this.x <= maxX) )  && 
				( (this.y >= 1) && (this.y <= maxY) ) 
				) ) 
		{
			return false;
		}
		
		if( ( (this.x == 3 || this.x == 4) && (this.y == 4 || this.y == 5) ) || ( (this.x == 7 || this.x == 8) && (this.y == 4 || this.y == 5 ) ) ) {
			return false;
		}
		
		return true;
	}
	
	/**
	  * Checks if the movable piece can move to a position.<p>
	  * @param board the boardcopy that is curently on play
	  * @param piece the piece we try to move
	  *  
	  * @return
	  */
	 public boolean canMoveTo(piece piece, boardCopy board) {
		 if(! this.isLegalPosition()) return false;
		 
		 //if its we want to move were there is a piece of the same team
		if( board.getMembers()[this.getX()][this.getY()].getPiece() != null &&
			board.getMembers()[this.getX()][this.getY()].getPiece().getTeam() == piece.getTeam()) return false;
		
		return true;
		
	}
	
	
}
