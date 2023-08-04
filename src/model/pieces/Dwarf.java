package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Dwarf extends SpecialMoveablePiece{
	
	private static final int DwarfRank = 3;
	
	public Dwarf(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(DwarfRank, position, image,team, board);
	}

}
