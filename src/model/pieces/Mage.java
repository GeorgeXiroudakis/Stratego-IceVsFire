package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Mage extends MoveablePiece{
	private static final int MageRank = 9;
	
	public Mage(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board) {
		super(MageRank, position, image,team, board);
	}

}
