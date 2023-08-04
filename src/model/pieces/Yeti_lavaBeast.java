package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.MyPosition;
import model.board.boardCopy;

public class Yeti_lavaBeast extends MoveablePiece{
	
	private static final int Yeti_lavaBeastRank = 5;
	
	public Yeti_lavaBeast(MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(Yeti_lavaBeastRank, position, image,team, board);
	}

}
