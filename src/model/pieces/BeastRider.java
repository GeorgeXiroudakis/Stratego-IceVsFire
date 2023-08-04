package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class BeastRider extends MoveablePiece{
	
	private static final int BeastRiderRank = 7;
	
	public BeastRider(model.board.MyPosition position, ImageIcon image,Team team, boardCopy board){
		super(BeastRiderRank, position, image,team ,board);
	}

}
