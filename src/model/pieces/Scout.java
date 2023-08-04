package model.pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.MyPosition;
import model.board.boardCopy;

public class Scout extends SpecialMoveablePiece{
	
	private static final int ScoutRank = 2;
	
	public Scout(MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(ScoutRank, position, image,team, board);
	}
	
	@Override
	/**
	 * overiting because scout can move more than one steps
	 */
	public ArrayList<MyPosition> possibleMoves(boardCopy board, boolean noRetritRule) {
		ArrayList<MyPosition> res = new ArrayList<MyPosition>();
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		MyPosition maybe;
		
		if( !(noRetritRule && this.getTeam() == Team.ICE) ) {
			
			for(int i = y + 1; i <= 8; i++) {
				maybe = new MyPosition(x, i);
				if( maybe.canMoveTo(this, board) ) res.add(maybe); else break;
				if( (board.getMembers()[maybe.getX()][maybe.getY()].getPiece() != null) && 
				   (board.getMembers()[maybe.getX()][maybe.getY()].getPiece().getTeam() !=
				   this.getTeam())) break;
			}
			
		}
		
		if( !(noRetritRule && this.getTeam() == Team.FIRE) ) {
			
			for(int i = y - 1; i >= 1; i-- ) {
				maybe = new MyPosition(x, i);
				if(maybe.canMoveTo(this, board) ) res.add(maybe); else break;
				if( (board.getMembers()[maybe.getX()][maybe.getY()].getPiece() != null) && 
						   (board.getMembers()[maybe.getX()][maybe.getY()].getPiece().getTeam() !=
						   this.getTeam())) break;
			}
		}
		
		for(int i = x - 1; i >= 1; i--) {
			maybe = new MyPosition(i, y);
			if(maybe.canMoveTo(this, board) ) res.add(maybe); else break;
			if( (board.getMembers()[maybe.getX()][maybe.getY()].getPiece() != null) && 
					   (board.getMembers()[maybe.getX()][maybe.getY()].getPiece().getTeam() !=
					   this.getTeam())) break;
		}
		
		for(int i = x + 1; i <= 10; i++) {
			maybe = new MyPosition(i, y);
			if(maybe.canMoveTo(this, board) ) res.add(maybe); else break;
			if( (board.getMembers()[maybe.getX()][maybe.getY()].getPiece() != null) && 
					   (board.getMembers()[maybe.getX()][maybe.getY()].getPiece().getTeam() !=
					   this.getTeam())) break;
		}
		
		
		
		return res;
	}
	
	
}
