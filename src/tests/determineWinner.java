package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import model.Team.Team;
import model.board.MyPosition;
import model.board.boardCopy;
import model.pieces.Dragon;
import model.pieces.Dwarf;
import model.pieces.Scout;
import model.pieces.Slayer;
import model.pieces.trap;

public class determineWinner {

	@Test
	public void test() {
		boardCopy board = new boardCopy();
		
		Dragon d = new Dragon(new MyPosition(5,5), null, Team.FIRE, board);
		Dragon d2 = new Dragon(new MyPosition(5,5), null, Team.FIRE, board);
		Scout s = new Scout(new MyPosition(5,5), null, Team.FIRE, board);
		Slayer sl = new Slayer(new MyPosition(2,2), null, Team.ICE, board);
		trap t = new trap(new MyPosition(2,2), null, Team.ICE, board);
		Dwarf dw = new Dwarf(new MyPosition(2,2), null, Team.FIRE, board);
		
		assertEquals(d.determineWiner(d, s), d);
		
		assertEquals(d.determineWiner(d, sl), d);
		
		assertEquals(sl.determineWiner(sl, d), sl);
		
		assertEquals(d.determineWiner(d, t), t);
		
		assertEquals(dw.determineWiner(dw, t), dw);
		
		assertEquals(d.determineWiner(d, dw), d);
		
		assertEquals(d.determineWiner(d, d2), null);
		
		
	}

}
