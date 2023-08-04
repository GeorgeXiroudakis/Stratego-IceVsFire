package model.pieces;

import javax.swing.ImageIcon;

import model.Team.*;

import model.board.MyPosition;
import model.board.boardCopy;
import model.board.boardMember;

public abstract class piece {
	private MyPosition position;
	private ImageIcon frontImage;
	private Team team;
	private boolean isCaptured;
	
	/**
	 * 
	 * @param position
	 * @param image
	 * @param team
	 * @param name
	 */
	piece(MyPosition position, ImageIcon image, Team team, boardCopy board){
		this.setPosition(position);
		this.setImage(image);
		this.setTeam(team);
		this.setCaptured(false);
		
		boardMember tile = board.getMembers()[position.getX()][position.getY()];
		tile.setPiece(this);
		tile.setCanMoveTo(false);
		
	}

	public MyPosition getPosition() {
		return position;
	}

	public void setPosition(MyPosition position) {
		this.position = position;
	}

	public ImageIcon getImage() {
		return frontImage;
	}

	public void setImage(ImageIcon image) {
		this.frontImage = image;
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
	 * @return the isCaptured
	 */
	public boolean isCaptured() {
		return isCaptured;
	}

	/**
	 * @param isCaptured the isCaptured to set
	 */
	public void setCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}
	
	
	
}
