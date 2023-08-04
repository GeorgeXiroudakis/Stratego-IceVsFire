package model.player;

import java.util.ArrayList;

import model.Team.*;
import model.board.boardCopy;
import model.pieces.*;

/**
 * 
 * class to represent each player
 *
 */
public class player {
	private String nickame;
	private int id;
	private Team team;
	private ArrayList<piece> pieces;
	private int battlesWon;
	private int battlesPlayed;
	private boolean his_turn;
	private boolean wonGame;
	private int revives;
	
	
	
	/**
	 * <b>constructor</b>: constructs new player with the giver parameters<br />
	 * <b>Precontions</b>: name must not be null and id must be > 0<br>
	 * <b>Postcontions</b>: initialises the new player with the giver values 
	 * 
	 * @param name name of the player
	 * @param id of player
	 * @param team team of the player(enum: ICE or FIRE)
	 */
	public player(String name, int id, Team team, ArrayList<piece> pieces) {
		if(name == null)throw new IllegalArgumentException();
		this.setName(name);
		
		if(id<0)throw new IllegalArgumentException();
		this.setId(id);
		
		this.setTeam(team);
		this.setPieces(pieces);	
		if(team == Team.FIRE)this.setHis_turn(true); else this.setHis_turn(false);
		
		this.setPieces(null);
		this.battlesWon = 0;
		this.battlesPlayed = 0;
		this.wonGame = false;
		this.revives = 0;
		
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return nickame;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.nickame = name;
	} 
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	/**
	 * @return the pieces
	 */
	public ArrayList<piece> getPieces() {
		return pieces;
	}
	/**
	 * @param pieces the pieces to set
	 */
	public void setPieces(ArrayList<piece> pieces) {
		this.pieces = pieces;
	}
	/**
	 * @return the his_turn
	 */
	public boolean isHis_turn() {
		return his_turn;
	}
	/**
	 * @param his_turn the his_turn to set
	 */
	public void setHis_turn(boolean his_turn) {
		this.his_turn = his_turn;
	}
	
	
	/**
	 * @return the wonGame
	 */
	public boolean isWonGame() {
		return wonGame;
	}

	/**
	 * @param wonGame the wonGame to set
	 */
	public void setWonGame(boolean wonGame) {
		this.wonGame = wonGame;
	}
	
	public int getRevives() {return this.revives;}
	
	public int getBattelsplayed() {return this.battlesPlayed;}
	
	public int getBattlesWon() {return this.battlesWon;}
	
	public void addBattlewon() {this.battlesWon += 1;;}
	
	public void addBattlePlayed() {this.battlesPlayed += 1;}
	
	public void addRevive() {this.revives += 1;}

	
	public double calcWper() {
		if(this.getBattelsplayed() == 0)return 0;
		 return (this.getBattlesWon()/(double)this.getBattelsplayed()) * 100 ;
	}
	
	/**
	 * calculates how many of the players pieces are captured 
	 * by traversing the array of his pieced and checking the iscaptured bool<p>
	 * Postcondition:calculates how many pieces of a player are captured
	 * 
	 * @return # of captured pieces
	 */
	public int calcCapturedPieces() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured()) sam++;
		return sam;
	}
	
	public int calcCapturedDragons() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Dragon) sam++;
		return sam;
	}
	
	public int calcCapturedMage() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Mage) sam++;
		return sam;
	}
	
	public int calcCapturedKnight() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Knight) sam++;
		return sam;
	}
	
	public int calcCapturedBeastRider() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof BeastRider) sam++;
		return sam;
	}
	
	public int calcCapturedSorceress() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Sorceress) sam++;
		return sam;
	}
	
	public int calcCapturedLavaBeastYeti() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Yeti_lavaBeast) sam++;
		return sam;
	}
	
	public int calcCapturedElf() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Elf) sam++;
		return sam;
	}
	
	public int calcCapturedDwarf() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Dwarf) sam++;
		return sam;
	}
	
	public int calcCapturedScout() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Scout) sam++;
		return sam;
	}
	
	public int calcCapturedSlayer() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Slayer) sam++;
		return sam;
	}
	
	public int calcCapturedTraps() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof trap) sam++;
		return sam;
	}
	
	public int calcCapturedFlags() {
		int sam = 0;
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Flag) sam++;
		return sam;
	}
	
	public MoveablePiece getCapturedDragon() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Dragon)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedMage() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Mage)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedKnight() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Knight)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedBeastRider() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof BeastRider)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedSorceress() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Sorceress)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedLavaBeastYeti() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Yeti_lavaBeast)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedElf() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Elf)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedDwarf() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Dwarf)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedScout() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Scout)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
	public MoveablePiece getCapturedSlayer() {
		for (int i = 0; i < this.pieces.size(); i++) if(this.pieces.get(i).isCaptured() && this.pieces.get(i) instanceof Slayer)return (MoveablePiece) this.pieces.get(i);
		
		return null;
	}
	
		
	
	/**
	 * <b>Observer</b>:observes if a player has lost fron not having any valid moves
	 * <b>Postcontions</b>: will determine if a player has no moves(he lost)
	 * @return true if he has lost, false if he has not lost.
	 */
	public boolean haslostFromMoves(boardCopy board, boolean NoRetriteRule) {
		boolean hasLost = true;
		for (int i = 0; i < this.pieces.size(); i++) {
			if( (this.pieces.get(i) instanceof MoveablePiece) && 
				( !(this.pieces.get(i).isCaptured() ) ) &&
				( ((MoveablePiece)this.pieces.get(i)).possibleMoves(board, NoRetriteRule).size() != 0 ) ) {
				hasLost = false;
				break;
			}
		}
		return hasLost;
	}

	
	
}
