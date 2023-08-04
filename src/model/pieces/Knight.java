package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Knight extends MoveablePiece{
	
	private static final int KnightRank = 8;
	
	public Knight(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board) {
		super(KnightRank, position, image,team, board);
	}

}
