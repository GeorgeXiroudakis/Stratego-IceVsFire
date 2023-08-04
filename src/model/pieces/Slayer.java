package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Slayer extends SpecialMoveablePiece{
	
	private static final int SlayerRank = 1;
	
	public Slayer(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(SlayerRank, position, image,team, board);
	}

}
