package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public abstract class ImmovablePiece extends piece{
	
	public ImmovablePiece(model.board.MyPosition position, ImageIcon image, Team team,  boardCopy board) {
		super(position, image,team, board);
		
	}
}
