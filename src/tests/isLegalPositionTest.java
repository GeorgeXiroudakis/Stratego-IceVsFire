package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import model.board.MyPosition;

public class isLegalPositionTest {

	@Test
	/**
	 * test the islegal function of the model.position
	 */
	public void test() {
		boolean v1, v2;
		
		MyPosition valid = new MyPosition(5,5);
		MyPosition invalid = new MyPosition(3,4);
		
		v1 = valid.isLegalPosition();
		v2 = invalid.isLegalPosition();
		
		assertEquals(true, v1);
		assertEquals(false, v2);
	}

}
