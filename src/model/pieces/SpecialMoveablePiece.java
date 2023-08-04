package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public abstract class SpecialMoveablePiece extends MoveablePiece{

	SpecialMoveablePiece(int rank, model.board.MyPosition position, ImageIcon image, Team team, boardCopy board) {
		super(rank, position, image,team , board);
	}

}
