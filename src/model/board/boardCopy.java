package model.board;



public class boardCopy {
	final int maxX = 10 + 1;
	final int maxY = 8 + 1;
	private boardMember[][] members;
	
	/**
	 * constructor: makes a new boarcopy and initialize the yellow squise
	 */
	public boardCopy(){
		
		this.members = new boardMember[maxX][maxY];
		for(int x = 1; x < maxX; x++) {
			for(int y = 1; y < maxY; y++) {
				members[x][y] = new boardMember();
				members[x][y].setCanMoveTo(true);
				members[x][y].setLegal(true);
				members[x][y].setPiece(null);
			}
		}
		
		for(int x = 3; x <= 4; x++) {
			for(int y = 4; y <= 5; y++) {
				members[x][y].setCanMoveTo(false);
				members[x][y].setLegal(false);
				members[x][y].setPiece(null);
			}
		}
		
		for(int x = 7; x <= 8; x++) {
			for(int y = 4; y <= 5; y++) {
				members[x][y].setCanMoveTo(false);
				members[x][y].setLegal(false);
				members[x][y].setPiece(null);
			}
		}
	}
	
	public boardMember[][] getMembers(){return this.members;}
	
	public void setMebers(boardMember[][] members) {this.members = members;}
	
	
	
}
