package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import model.Team.Team;
import model.board.MyPosition;
import model.board.boardCopy;
import model.pieces.Dragon;
import model.pieces.piece;
import model.player.player;

public class calcCapturedPiececesTest {

	@Test
	public void test() {
		
		
		player p = new player("test", 0, Team.FIRE, null);
		ArrayList<piece> pieces = new ArrayList<>();
		boardCopy board = new boardCopy();
		
		for(int i = 0; i < 30;i++) {
			pieces.add(new Dragon(new MyPosition(1, 1), null, Team.FIRE, board));
		}
		p.setPieces(pieces);
		
		Random rand = new Random();
		int randCaptured = rand.nextInt((30 - 1) + 1) + 1;
		
		for(int i = 0; i < randCaptured; i++) {
			p.getPieces().get(i).setCaptured(true);
		}
		assertEquals(p.calcCapturedPieces(), randCaptured);
	}

}
