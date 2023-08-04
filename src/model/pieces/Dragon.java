/**
 * 
 */
package model.pieces;

import javax.swing.ImageIcon;

import model.Team.Team;
import model.board.boardCopy;

/**
 * @author George
 *
 */
public class Dragon extends MoveablePiece{
	private static final int DragonsRank = 10;

	public Dragon(model.board.MyPosition position, ImageIcon image, Team team, boardCopy board) {
		super(DragonsRank, position, image, team, board);
	}
}
