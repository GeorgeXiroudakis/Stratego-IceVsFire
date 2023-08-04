package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class trap extends ImmovablePiece{
	public trap(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(position, image,team, board);
	}
}
