package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import model.Team.Team;
import model.board.MyPosition;
import model.board.boardCopy;
import model.pieces.*;

public class moveToTest {

	@Test
	public void test() {
		boardCopy board = new boardCopy();
		
		assertEquals(null, board.getMembers()[1][1].getPiece());
		assertEquals(true, board.getMembers()[1][1].CanMoveTo());
		
		Dragon d1 = new Dragon(new MyPosition(1, 1), null, Team.FIRE, board);
		
		assertEquals(d1, board.getMembers()[1][1].getPiece());
		assertEquals(false, board.getMembers()[1][1].CanMoveTo());
		
		d1.moveTo(new MyPosition(1, 2), board);
		
		assertEquals(null, board.getMembers()[1][1].getPiece());
		assertEquals(d1, board.getMembers()[1][2].getPiece());
		assertEquals(true, board.getMembers()[1][1].CanMoveTo());
		assertEquals(false, board.getMembers()[1][2].CanMoveTo());
		
	}

}
