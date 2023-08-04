package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Flag extends ImmovablePiece{
	public Flag(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(position, image,team, board);
	}
}
