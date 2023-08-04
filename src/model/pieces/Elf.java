package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

public class Elf extends MoveablePiece{
	
	private static final int ElfRank = 4;
	
	public Elf(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board){
		super(ElfRank, position, image, team,board);
	}
	
}
