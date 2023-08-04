package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Sorceress extends MoveablePiece{
	private static final int SorceressRank = 6;
	
	public Sorceress(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(SorceressRank, position, image,team , board);
	}

}
