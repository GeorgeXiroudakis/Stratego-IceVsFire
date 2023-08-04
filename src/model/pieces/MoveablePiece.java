package model.pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.board.*;
import model.Exceptions.WinnerException;
import model.Team.Team;

public abstract class MoveablePiece extends piece{
	private final int rank;
	private boolean hasRevived;
	
	public MoveablePiece(int rank, MyPosition position, ImageIcon image,Team team , boardCopy board){
		super(position, image,team , board);
		this.rank = rank;
		this.hasRevived = false;
	}
	
	/**
	 * geter for rank
	 * @return the rank
	 */
	public int getRank() {return this.rank;}
	
	/**
	 * @return the hasRevived
	 */
	public boolean hasRevived() {
		return hasRevived;
	}

	/**
	 * @param hasRevived the hasRevived to set
	 */
	public void setHasRevived(boolean hasRevived) {
		this.hasRevived = hasRevived;
	}

	/**
	 * This function calculates all the legal moves a MovablePiece can make.<p>
	 * this method is not abstract because all but one movable piece
	 * can move the same so that piece an overwrite it.
	 * 
	 * @param board the boardcopy currently in play
	 * @return a array of Positions the piece can legally move to
	 * using this.position of each piece 
	 */
	public ArrayList<MyPosition> possibleMoves(boardCopy board, boolean noRetritRule) {
		ArrayList<MyPosition> res = new ArrayList<MyPosition>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		MyPosition maybe;
		
		//Check the front back right and left tile to determine if we can move to it
		
		if( !(noRetritRule && this.getTeam() == Team.ICE) && (y + 1 <= 8) ) {
			maybe = new MyPosition(x, y + 1);
			if(maybe.canMoveTo(this, board) ) res.add(maybe);
		}
		
		if( !(noRetritRule && this.getTeam() == Team.FIRE) && (y - 1 >= 1)  ) {
			maybe = new MyPosition(x, y - 1);
			if(maybe.canMoveTo(this, board) ) res.add(maybe);
		}
		
		if(x - 1 >= 1) {
			maybe = new MyPosition(x - 1, y);
			if(maybe.canMoveTo(this, board) ) res.add(maybe);
		}
		
		if(x + 1 <= 10) {
			maybe = new MyPosition(x + 1, y);
			if(maybe.canMoveTo(this, board) ) res.add(maybe);
		}
		
		
		
		return res;
	}
	
	
	/**
	 * determines if a player is attacking  <p>
	 * precondition: the move must be legal and the piece must be able to move there, 
	 * its beter to give possition generated from the possiblemoves function that checks everything
	 * @param board boardcopy used
	 * @param position that the player wants to move to
	 */
	public boolean determineIfAttacking(boardCopy board, MyPosition position) {
		piece p = board.getMembers()[position.getX()][position.getY()].getPiece();
		
		if(p == null)return false;
		return true;
	}
	
	 
	
	/**
	 * This function moves the movable piece to a different position.<p>
	 * 
	 * @param position: The position to move the piece
	 * Precondition: The position must be legal.<p>
	 * Postcondition:Changes the position of the piece
	 * 
	 */
	public void moveTo(MyPosition position, boardCopy board) {

		boardMember tile = board.getMembers()[this.getPosition().getX()][this.getPosition().getY()];

		tile.setCanMoveTo(true);
		tile.setPiece(null);
		
		this.setPosition(new MyPosition(position.getX(), position.getY()));
		
		boardMember newTile = board.getMembers()[position.getX()][position.getY()];
		newTile.setCanMoveTo(false);
		newTile.setPiece(this);
	}
	
	/**
	 * determine the winner and make other actions 
	 * ex update the board the camtures,....
	 * @param opponent
	 * @param board 
	 * @throws WinnerException if a the attack results in a win
	 */
	public void attack(piece opponent, boardCopy board) throws WinnerException {
		
		piece battleWiner = determineWiner(this, opponent);
		
		if(battleWiner instanceof Flag)throw new WinnerException();
		
		
		if(battleWiner == this) {
			
			opponent.setCaptured(true);
			boardMember winersTile = board.getMembers()[this.getPosition().getX()][this.getPosition().getY()];
			winersTile.setPiece(null);
			winersTile.setCanMoveTo(true);
			this.setPosition(opponent.getPosition());
			
			boardMember losersTile = board.getMembers()[opponent.getPosition().getX()][opponent.getPosition().getY()];
			losersTile.setCanMoveTo(false);
			losersTile.setPiece(this);
			opponent.setPosition(null);
			
		}else if(battleWiner == opponent) {
			
			this.setCaptured(true);
			boardMember losersTile = board.getMembers()[this.getPosition().getX()][this.getPosition().getY()];
			losersTile.setCanMoveTo(true);
			losersTile.setPiece(null);
			this.setPosition(null);
			
		}else {
			//draw
			this.setCaptured(true);
			boardMember attackersTile = board.getMembers()[this.getPosition().getX()][this.getPosition().getY()];
			attackersTile.setPiece(null);
			attackersTile.setCanMoveTo(true);
			this.setPosition(null);
			
			opponent.setCaptured(true);
			boardMember defentensTile = board.getMembers()[opponent.getPosition().getX()][opponent.getPosition().getY()];
			defentensTile.setPiece(null);
			defentensTile.setCanMoveTo(true);
			opponent.setPosition(null);
		}
		
	}
	
	
	/**
	 * Determines the winer of a attack takes account of special pieces!!!!!!!
	 * 
	 * @param me
	 * @param opponent
	 * @return the winner or null if they are the same rank
	 */
	public piece determineWiner(MoveablePiece me, piece opponent) {
		
		//special situations 
		if(opponent instanceof Flag)return me;
		if(me instanceof Dwarf && opponent instanceof trap)return me;
		if(me instanceof Slayer && opponent instanceof Dragon)return me;
		if(opponent instanceof trap)return opponent;
		
		//default situation
		
		if(me.getRank() == ((MoveablePiece)opponent).rank)return null;
		if(me.getRank() > ((MoveablePiece)opponent).rank)return me;
		return opponent;
				
	}
}
