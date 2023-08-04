package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.sound.sampled.*;

import model.Exceptions.WinnerException;
import model.Team.Team;
import model.Turn.*;
import model.board.MyPosition;
import model.board.boardCopy;
import model.board.boardMember;
import model.player.player;
import view.mainGraphicUI;
import view.reviveUI;
import view.winUI;
import model.pieces.*;

public class controller {
	private URL imageURL;
    private ClassLoader cldr;
	
	private mainGraphicUI ui;
	private reviveUI rui;
	private winUI wui;
	
	private player playerIce, playerFire;
	private boardCopy board;
	private Turn turn;
	
	boolean halfArmy;
	
	Clip soundTrack;	
	
	private static final int maxX = 10;
	private static final int maxY = 8;
	
	private static final String bluHidder = "res/bluePieces/blueHidden.png";
	private static final String redHidder = "res/redPieces/redHidden.png";
	private static final String blueFlag = "res/bluePieces/flagB.png";
	private static final String redFlag = "res/redPieces/flagR.png";
	private static final String blueTrap = "res/bluePieces/trapB.png";
	private static final String redTrap = "res/redPieces/trapR.png";
	private static final String blueSlayer = "res/bluePieces/slayerB.png";
	private static final String redSlayer = "res/redPieces/slayerR.png";
	private static final String blueScout = "res/bluePieces/scoutB.png";
	private static final String redScout = "res/redPieces/scoutR.png";
	private static final String blueDwarf = "res/bluePieces/dwarfB.png";
	private static final String redSDwarf = "res/redPieces/dwarfR.png";
	private static final String blueElf = "res/bluePieces/elfB.png";
	private static final String redElf = "res/redPieces/elfR.png";
	private static final String yeti = "res/bluePieces/yeti.png";
	private static final String lavaBeast = "res/redPieces/lavaBeast.png";
	private static final String blueSorceress = "res/bluePieces/sorceressB.png";
	private static final String redSorceress = "res/redPieces/sorceressR.png";
	private static final String blueBeastRider = "res/bluePieces/beastRiderB.png";
	private static final String redBeastRider = "res/redPieces/beastRiderR.png";
	private static final String blueKnight = "res/bluePieces/knightB.png";
	private static final String redKnight = "res/redPieces/knightR.png";
	private static final String blueMage = "res/bluePieces/mageB.png";
	private static final String redMage = "res/redPieces/mageR.png";
	private static final String blueDragon = "res/bluePieces/dragonB.png";
	private static final String redDragon = "res/redPieces/dragonR.png";
	
	private static final String IceTexture = "res/bluePieces/iceTexture.png";
	private static final String FireTexture = "res/redPieces/FireTexture.png";
	private static final String MidleTextrure = "res/other/MidleBackground.png";
	
	private static int numOfDragons = 1;
	private static int numOfMages = 1;
	private static int numOfKnites = 2;
	private static int numOfBeastRiders = 3;
	private static int numOfSorceress = 2;
	private static int numOfYeti_lavaBeast = 2;
	private static int numOfElfs = 2;
	private static int numOfDwarfs = 5;
	private static int numOfScouts = 4;
	private static int numOfSlayers = 1;
	private static int numOfTraps = 6;
	private static int numOfflags = 1;
	
	private static final int maxXForSpawnR = 10;
	private static final int minXForSpawnR = 1;
	private static final int maxYForSpawnR = 3;
	private static final int minYForSpawnR = 1;
	
	private static final int maxXForSpawnB = 10;
	private static final int minXForSpawnB = 1;
	private static final int maxYForSpawnB = 8;
	private static final int minYForSpawnB = 6;
	
	
	/**
	 * constructor: creates a new controller and inits all the members
	 * if the halfArmy rule is enable it allocates half the troops to the players
	 */
	public controller(){
		cldr = this.getClass().getClassLoader();
		
		this.soundTrack = playAndSaveSoudClip("sountTrack", -40);
		
		this.board = new boardCopy();
		
		this.ui = new mainGraphicUI();
		this.rui = new reviveUI();
		this.wui = new winUI();
		
		getUi().addButtonActionlListeners(new ListeningForButon());
		ui.addExitActionListener(new exitWListener());
		rui.addReviveActionListeners(new listenForReviveChose());
		rui.addExitActionListeners(new exitWindowListener());
		wui.addExitAListener(new exitWListener());
		
		setTileImages();
		
		{
			int reply = JOptionPane.showConfirmDialog(null,
					"Would you like to enable the no half army rule?\n(if enabled each player will have half the troops.)?",
					"half army rule", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				numOfKnites = 1;
				numOfBeastRiders = 1;
				numOfSorceress = 1;
				numOfYeti_lavaBeast = 1;
				numOfElfs = 1;
				numOfDwarfs = 2;
				numOfScouts = 2;
				numOfTraps = 3;
			}
			getUi().gethalfArmy().setSelected(reply == JOptionPane.YES_OPTION);
			getUi().gethalfArmy().setEnabled(false);
		}
		

		this.playerFire = new player((JOptionPane.showInputDialog("Player one, you will represent Fire.\nWhats your name?", "Nickname")),1, Team.FIRE,null);
		playerFire.setPieces( intiPieces(playerFire, this.board, halfArmy) );
		
		this.playerIce = new player((JOptionPane.showInputDialog("Player two, you will represent Ice.\nWhats your name?", "Nickname")),1, Team.ICE,null);
		playerIce.setPieces( intiPieces(playerIce, this.board, halfArmy) );
		
		this.turn = new Turn(playerFire);
		getUi().setTurn(playerFire.getName());
		getUi().setWper(0);
		getUi().setnumOfTurn(Turn.getCount());
		getUi().setNumOfCaptured(playerIce.calcCapturedPieces());
		uptadeCaptured(playerFire);
		
		//hide cards of blue
		for(int i = 0; i < playerIce.getPieces().size(); i++) {
			MyPosition toHide = playerIce.getPieces().get(i).getPosition();
			imageURL = cldr.getResource(bluHidder);
			getUi().setImageInButton(toHide.getX(), toHide.getY(), imageURL);
		}
		
		setTileImages();
		
	}
	
	/**
	 * @return the ui
	 */
	public mainGraphicUI getUi() {return ui;}
	
	/**
	 * @return the Rui
	 */
	public reviveUI getRui() {return rui;}
	
	/**
	 * @return the Wui
	 */
	public winUI getWui() {return wui;}
	

	/**
	 * inits a players pieces
	 * 
	 * Postcointitions: returns a new piece[] according to the 
	 * team of the player and the half army rule
	 * 
	 * @param player the player to initialize 
	 * @param halfArmy true if the rule in enables, false if not
	 * @return a piece array 
	 */
	ArrayList<piece> intiPieces(player player, boardCopy board, Boolean halfArmy) {
		
		playSoudClip("dealing", 0);
		
		ArrayList<piece> pieces = new ArrayList<>();
		Random rand = new Random();
		
		if(player.getTeam() == Team.FIRE) {
			
			//flags
			for(int i = 0; i < controller.numOfflags; i++) {
				
				MyPosition spwonPosition;
				//generate a valid location to spawn the piece
				do {											//flags to spawn in the deepest row to make it more interesting
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , 1);
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				//spawn it on the board and pass it to the piece array
				imageURL = cldr.getResource(redFlag);
				Flag F = new Flag(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(F);
			}
				
			//dragons
			for(int i = 0; i < controller.numOfDragons; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redDragon);
				Dragon D = new Dragon(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(D);
			}
			
			//mage
			for(int i = 0; i < controller.numOfMages; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redMage);
				Mage M = new Mage(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(M);
			}
			
			//knight
			for(int i = 0; i < controller.numOfKnites; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redKnight);
				Knight K = new Knight(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(K);
			}
			
			//beast riders
			for(int i = 0; i < controller.numOfBeastRiders; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redBeastRider);
				BeastRider B = new BeastRider(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(B);
			}
			
			//Sorceress
			for(int i = 0; i < controller.numOfSorceress; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redSorceress);
				Sorceress S = new Sorceress(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(S);
			}
			
			//lava beast
			for(int i = 0; i < controller.numOfYeti_lavaBeast; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(lavaBeast);
				Yeti_lavaBeast L = new Yeti_lavaBeast(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(L);
			}
			
			//Elf
			for(int i = 0; i < controller.numOfElfs; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redElf);
				Elf E = new Elf(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(E);
			}
			
			//Dwarfs
			for(int i = 0; i < controller.numOfDwarfs; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redSDwarf);
				Dwarf D = new Dwarf(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(D);
			}
			
			//Scouts
			for(int i = 0; i < controller.numOfScouts; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redScout);
				Scout S = new Scout(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(S);
			}
			
			//Slayers
			for(int i = 0; i < controller.numOfSlayers; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redSlayer);
				Slayer S = new Slayer(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(S);
			}
			
			//traps
			for(int i = 0; i < controller.numOfTraps; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(redTrap);
				trap T = new trap(spwonPosition, new ImageIcon(imageURL) , Team.FIRE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(T);
			}	
	
		}else {
			//ice
			
			//flags
			for(int i = 0; i < controller.numOfflags; i++) {
				
				MyPosition spwonPosition;
				do {											//spawn flag in the deepest row to be more intersting
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , 8);
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueFlag);
				Flag F = new Flag(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(F);
			}
			
			//dragons
			for(int i = 0; i < controller.numOfDragons; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueDragon);
				Dragon D = new Dragon(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(D);
			}
			
			//mage
			for(int i = 0; i < controller.numOfMages; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueMage);
				Mage M = new Mage(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(M);
			}
			
			//knight
			for(int i = 0; i < controller.numOfKnites; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueKnight);
				Knight K = new Knight(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(K);
			}
			
			//beast riders
			for(int i = 0; i < controller.numOfBeastRiders; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueBeastRider);
				BeastRider B = new BeastRider(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL	);
				pieces.add(B);
			}
			
			//Sorceress
			for(int i = 0; i < controller.numOfSorceress; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueSorceress);
				Sorceress S = new Sorceress(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(S);
			}
			
			//lava beast
			for(int i = 0; i < controller.numOfYeti_lavaBeast; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(yeti);
				Yeti_lavaBeast L = new Yeti_lavaBeast(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(L);
			}
			
			//Elf
			for(int i = 0; i < controller.numOfElfs; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueElf);
				Elf E = new Elf(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(E);
			}
			
			//Dwarfs
			for(int i = 0; i < controller.numOfDwarfs; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueDwarf);
				Dwarf D = new Dwarf(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(D);
			}
			
			//Scouts
			for(int i = 0; i < controller.numOfScouts; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueScout);
				Scout S = new Scout(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(S);
			}
			
			//Slayers
			for(int i = 0; i < controller.numOfSlayers; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueSlayer);
				Slayer S = new Slayer(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(S);
			}
			
			//traps
			for(int i = 0; i < controller.numOfTraps; i++) {
				
				MyPosition spwonPosition;
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
				
				imageURL = cldr.getResource(blueTrap);
				trap T = new trap(spwonPosition, new ImageIcon(imageURL) , Team.ICE, board);
				getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), imageURL);
				pieces.add(T);
			}

		}
		return pieces;
	}

	/**
	 * creates a new turn its used in the beggining of each turn
	 * the new turn has the player who wasnt playing at the previous turn<p>
	 * 
	 * postcondition: creates a new turn with the player who wasnt playing at the previous turn 
	 */
	void newTurn() {
		
		System.out.println();
		
		setTileImages();
		
		if(getUi().getpause().isSelected()) waitForPlayerToStopLooking(getUi().getPauseVal().getValue() * 1000); 
		
		//hide cards
		String backOfcard = null;
		if(this.turn.getPlayer().getTeam() == Team.FIRE)backOfcard = redHidder; else backOfcard = bluHidder;
		
		for(int i = 0; i < this.turn.getPlayer().getPieces().size(); i++) {
			if(this.turn.getPlayer().getPieces().get(i).isCaptured())continue;
			MyPosition toHide = this.turn.getPlayer().getPieces().get(i).getPosition();
			
			imageURL = cldr.getResource(backOfcard);
			getUi().setImageInButton(toHide.getX(), toHide.getY(), imageURL);
		}
		
		player p;
		if(playerFire.isHis_turn()) {
			p = playerIce; 
			playerFire.setHis_turn(false);
		} else {
			p = playerFire; 
			playerIce.setHis_turn(false);
		}
		
		player prevPlayerReviving = turn.getPlayerReviving();
		MoveablePiece prevPieceReviving = turn.getPieceReviving();
		
		this.turn = new Turn(p);
		
		turn.setPlayerReviving(prevPlayerReviving);
		turn.setPieceReviving(prevPieceReviving);
		
		//unhide cards of player who is now playing
		for(int i = 0; i < this.turn.getPlayer().getPieces().size(); i++) {
			if(this.turn.getPlayer().getPieces().get(i).isCaptured())continue;
			MyPosition toHide = this.turn.getPlayer().getPieces().get(i).getPosition();
			getUi().setImageInButton(toHide.getX(), toHide.getY(), this.turn.getPlayer().getPieces().get(i).getImage());
		}
		
		if( !(checkIfPlayerHasMoves(turn.getPlayer())) ) {
			getUi().displayErrorMessage(turn.getPlayer().getName() + " you dont have any moves left.");
		}
		
		getUi().setTurn(turn.getPlayer().getName());
		getUi().setWper(turn.getPlayer().calcWper());
		getUi().setnumOfTurn(Turn.getCount());
		
		if(playerFire.isHis_turn()) {
			p = playerIce; 
		} else {
			p = playerFire; 
		}
		getUi().setNumOfCaptured(p.calcCapturedPieces());
		getUi().setRevive(turn.getPlayer().getRevives());
		
		uptadeCaptured(turn.getPlayer());
	}
	
	/**
	 * sets the background images where its necessary (referent in each area)
	 */
	private void setTileImages() {
		for(int x = 1; x <= 10; x++) {
			for(int y = 1; y <= 3; y++) {
				if(board.getMembers()[x][y].getPiece() == null) {
					imageURL = cldr.getResource(FireTexture);
					getUi().setImageInButton(x, y, imageURL);
				}
			}
		}
		
		for(int x = 1; x <= 10; x++) {
			if(x == 3 || x == 4 || x == 7 || x == 8)continue;
			for(int y = 4; y <= 5; y++) {
				if(board.getMembers()[x][y].getPiece() == null) {
					imageURL = cldr.getResource(MidleTextrure);
					getUi().setImageInButton(x, y, imageURL);
				}
			}
		}
		
		for(int x = 1; x <= 10; x++) {
			for(int y = 6; y <= 8; y++) {
				if(board.getMembers()[x][y].getPiece() == null) {
					imageURL = cldr.getResource(IceTexture);
					getUi().setImageInButton(x, y, imageURL);
				}
			}
		}
		
		//to resize if the screen size has changed
		this.ui.colorIlegals();
	}
	
	/**
	 * checks if a player has any moves left or he has lost<p>
	 * postcondition:will check if a player has lost from lack of moves
	 * 
	 * @param player player to check
	 * @return true if he still has possible moves
	 */
	private boolean checkIfPlayerHasMoves(player player) {
		if(player.haslostFromMoves(board, getUi().getnoReteat().isSelected()))return false;
		return true;
	}
	
	/**
	 * after its play you can set a waiting time when both players stop looking so they don't see each others cards<p>
	 * 
	 * postcondition: The thread will sleep for the given milliseconds
	 * 
	 * @param milliseconds for how much to pause
	 */
	private void waitForPlayerToStopLooking(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
	
	/**
	 * updates the captured images and values in the menu
	 * 
	 * @param player to update his captured 
	 */
	private void uptadeCaptured(player player) {
		player oponent;
		if(playerFire.isHis_turn()) {
			oponent = playerIce; 
		} else {
			oponent = playerFire; 
		}
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 4; y++) {
				imageURL = cldr.getResource(captureImage(x, y, player.getTeam()));
				getUi().setCaptured(x, y, imageURL, captureNum(x, y, oponent));
			}
		}
		
	}
	
	/**
	 * finds the correct picture to piece in the captured grid of the menu<P>
	 * 
	 * @param x the horizontal position 
	 * @param y the vertical position
	 * @param team the team of the opponent 
	 * @return a string of the path of the correct image
	 */
	String captureImage(int x, int y, Team team) {
		String image = null;
		
		if(team == Team.ICE) {
			if(x == 1 && y == 1)image = redDragon;
			else if(x == 1 && y == 2)image = redMage;
			else if(x == 1 && y == 3)image = redKnight;
			else if(x == 1 && y == 4)image = redBeastRider;
			else if(x == 2 && y == 1)image = redSorceress;
			else if(x == 2 && y == 2)image = lavaBeast;
			else if(x == 2 && y == 3)image = redElf;
			else if(x == 2 && y == 4)image = redSDwarf;
			else if(x == 3 && y == 1)image = redScout;
			else if(x == 3 && y == 2)image = redSlayer;
			else if(x == 3 && y == 3)image = redTrap;
			else if(x == 3 && y == 4)image = redFlag;
		}else {
			if(x == 1 && y == 1)image = blueDragon;
			else if(x == 1 && y == 2)image = blueMage;
			else if(x == 1 && y == 3)image = blueKnight;
			else if(x == 1 && y == 4)image = blueBeastRider;
			else if(x == 2 && y == 1)image = blueSorceress;
			else if(x == 2 && y == 2)image = yeti;
			else if(x == 2 && y == 3)image = blueElf;
			else if(x == 2 && y == 4)image = blueDwarf;
			else if(x == 3 && y == 1)image = blueScout;
			else if(x == 3 && y == 2)image = blueSlayer;
			else if(x == 3 && y == 3)image = blueTrap;
			else if(x == 3 && y == 4)image = blueFlag;
		}
		return image;
	}
	
	/**
	 * 	
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @param player the  player
	 * @return
	 */
	int captureNum(int x, int y, player player) {
		int num = 0;
		
		if(x == 1 && y == 1)num = player.calcCapturedDragons();
		if(x == 1 && y == 2)num = player.calcCapturedMage();
		if(x == 1 && y == 3)num = player.calcCapturedKnight();
		if(x == 1 && y == 4)num = player.calcCapturedBeastRider();
		if(x == 2 && y == 1)num = player.calcCapturedSorceress();
		if(x == 2 && y == 2)num = player.calcCapturedLavaBeastYeti();
		if(x == 2 && y == 3)num = player.calcCapturedElf();
		if(x == 2 && y == 4)num = player.calcCapturedDwarf();
		if(x == 3 && y == 1)num = player.calcCapturedScout();
		if(x == 3 && y == 2)num = player.calcCapturedSlayer();
		if(x == 3 && y == 3)num = player.calcCapturedTraps();
		if(x == 3 && y == 4)num = player.calcCapturedFlags();
		
		return num;
	}
	
	String reviveImage(int x, int y, Team team) {
		String image = null;
		
		if(team == Team.FIRE) {
			if(x == 1 && y == 1)image = redDragon;
			else if(x == 1 && y == 2)image = redMage;
			else if(x == 1 && y == 3)image = redKnight;
			else if(x == 1 && y == 4)image = redBeastRider;
			else if(x == 2 && y == 1)image = redSorceress;
			else if(x == 2 && y == 2)image = lavaBeast;
			else if(x == 2 && y == 3)image = redElf;
			else if(x == 2 && y == 4)image = redSDwarf;
			else if(x == 3 && y == 1)image = redScout;
			else if(x == 3 && y == 2)image = redSlayer;
			else if(x == 3 && y == 3)image = redTrap;
			else if(x == 3 && y == 4)image = redFlag;
		}else {
			if(x == 1 && y == 1)image = blueDragon;
			else if(x == 1 && y == 2)image = blueMage;
			else if(x == 1 && y == 3)image = blueKnight;
			else if(x == 1 && y == 4)image = blueBeastRider;
			else if(x == 2 && y == 1)image = blueSorceress;
			else if(x == 2 && y == 2)image = yeti;
			else if(x == 2 && y == 3)image = blueElf;
			else if(x == 2 && y == 4)image = blueDwarf;
			else if(x == 3 && y == 1)image = blueScout;
			else if(x == 3 && y == 2)image = blueSlayer;
			else if(x == 3 && y == 3)image = blueTrap;
			else if(x == 3 && y == 4)image = blueFlag;
		}
		return image;
	}
	
	/**
	 * called when a revive is graned and handles what needs to be done
	 * 
	 * postcondition: opens the revive frames and sets the correct images and values(the actual revive is handled by the listener on the buttons)
	 * 
	 * @param player who was granded the revive
	 * @param piece that gained the revive
	 */
	private void Revive(player player, MoveablePiece piece) {
		
		if(player.calcCapturedPieces() == 0){System.out.println("revive granded but no captured pieces to revive."); return;}
		
		System.out.println("revive granded");
		
		turn.setPlayerReviving(player);
		turn.setPieceReviving(piece);
		this.rui.setVisible(true);
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 4; y++) {
				
				if((x == 3 && y ==4) || x == 3 && y == 3)continue;
				
				imageURL = cldr.getResource(reviveImage(x, y, player.getTeam()));
				rui.setImageInButton(x, y, imageURL, captureNum(x, y, player));
				
				if( (x == 1 && y == 1) && player.getCapturedDragon() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 1 && y == 2) && player.getCapturedMage() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 1 && y == 3) && player.getCapturedKnight() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 1 && y == 4) && player.getCapturedBeastRider() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 2 && y == 1) && player.getCapturedSorceress() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 2 && y == 2) && player.getCapturedLavaBeastYeti() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 2 && y == 3) && player.getCapturedElf() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 2 && y == 4) && player.getCapturedDwarf() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 3 && y == 1) && player.getCapturedScout() == null)rui.getButtons()[x][y].setEnabled(false);
				if( (x == 3 && y == 2) && player.getCapturedSlayer() == null)rui.getButtons()[x][y].setEnabled(false);
			}
		}	
	}
	
	/**
	 * playes the cound clip given 
	 * 
	 * postcondition: if the file if found will start a thread and play the sound
	 * @param name of the clip(without the path)
	 * @param boost boost value
	 */
	private void playSoudClip(String name, float boost) {
		imageURL =  cldr.getResource("res/sound/" + name + ".wav");
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(imageURL);
			if (audioInputStream == null)return;
		} catch (UnsupportedAudioFileException | IOException e3) {
			e3.printStackTrace();
		}catch(NullPointerException e){
			return;
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip( );
		} catch (LineUnavailableException e3) {
			e3.printStackTrace();
		}catch(NullPointerException e){
			return;
		}
	    try {
			clip.open(audioInputStream);
			FloatControl gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(boost);
		} catch (LineUnavailableException | IOException e3) {
			e3.printStackTrace();
		}catch(NullPointerException e){
			return;
		}
	    clip.start( );
	}
	
	/**
	 * playes the cound clip given  and retruns the clip thread
	 * 
	 * postcondition: if the file if found will start a thread and play the sound
	 * @param name of the clip(without the path)
	 * @param boost boost value
	 */
	private Clip playAndSaveSoudClip(String name, float boost) {
		imageURL =  cldr.getResource("res/sound/" + name + ".wav");
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(imageURL);
			if (audioInputStream == null)return null;
		} catch (UnsupportedAudioFileException | IOException e3) {
			e3.printStackTrace();
		}catch(NullPointerException e){
			return null;
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip( );
		} catch (LineUnavailableException e3) {
			e3.printStackTrace();
		}catch(NullPointerException e){
			return null;
		}
	    try {
			clip.open(audioInputStream);
			FloatControl gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(boost);
		} catch (LineUnavailableException | IOException e3) {
			e3.printStackTrace();
		}catch(NullPointerException e){
			return null;
		}
	    clip.start( );
	    
	    return clip;
	}
	
	/**
	 * stops the soundtrack thread(used when restarting)
	 */
	public void stopSoundTrack() {this.soundTrack.stop();}
	
	/**
	 * 
	 * class for the action listeners of the buttons of the board
	 * saves its time it is called in the same turn data on the turn instance and acconding the stage of the turn and the data the correct actions are performed
	 *
	 */
	class ListeningForButon implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton[][] buttons = getUi().getButtons();
			int XofButtonPressed = 0;
			int YofButtonPressed = 0;
			
			boolean flag = false;
			for(int x = 1; x <= controller.maxX; x++) {
				if(flag)break;
				for(int y = 1; y <= controller.maxY; y++) {
					if( e.getSource() == buttons[y][x]) {
						XofButtonPressed = x;
						YofButtonPressed = y;
						flag = true;
						break;
					}
				}			
			}
			
			if(turn.hasSelectedCard() && board.getMembers()[XofButtonPressed][YofButtonPressed].getPiece() != null && 
					board.getMembers()[XofButtonPressed][YofButtonPressed].getPiece().getTeam() == turn.getPlayer().getTeam()) {
				turn.setChoosingAgain(true);
			}
			
			
			//if he selected to play the card or playing again
			if(!turn.hasSelectedCard() || turn.ischoosingAgain()) {
				
				if(turn.ischoosingAgain()) {
					System.out.println("Changing selection");
					
					//remove green outline
					for(int i = 0; i < turn.getPossiblePositions().size(); i++) {
						getUi().StopshowPossibleMove(turn.getPossiblePositions().get(i).getX(), turn.getPossiblePositions().get(i).getY());
					}
					
					turn.setChoosingAgain(false);
				}
				
				piece selectedPiece = board.getMembers()[XofButtonPressed][YofButtonPressed].getPiece();
				if(selectedPiece == null)return;
				System.out.print("Button(" + XofButtonPressed + ":" + YofButtonPressed + ") Selected (");
				System.out.println(selectedPiece + ")");
				
				
				if(selectedPiece.getTeam() != turn.getPlayer().getTeam()) {
					System.out.println("Ilegan turn selection rejected");
					getUi().displayErrorMessage("Someone is in a hurry.\nLet " + turn.getPlayer().getName() + " play first");
					return;
				} 
				
				if( (selectedPiece instanceof ImmovablePiece) ) {
					System.out.println("ImmovablePiece selection rejected");
					getUi().displayErrorMessage("Remember "+ turn.getPlayer().getName() +" the flag and trap pieces can not be moved.\nTry again.");
					return;
				}
				
				
				turn.setPieceSelected(selectedPiece);
				
				turn.setPossiblePositions(((MoveablePiece)selectedPiece).possibleMoves(board, getUi().getnoReteat().isSelected()));
				
				//with noretrit you can still atack back
				if(getUi().getnoReteat().isSelected()) {
					MyPosition backforAtack;
					if(turn.getPlayer().getTeam() == Team.FIRE) {
						backforAtack = new MyPosition(selectedPiece.getPosition().getX(), selectedPiece.getPosition().getY() - 1);
					}else {
						backforAtack = new MyPosition(selectedPiece.getPosition().getX(), selectedPiece.getPosition().getY() + 1);
					}
					
					if(backforAtack.isLegalPosition() && board.getMembers()[backforAtack.getX()][backforAtack.getY()].getPiece() != null && 
							board.getMembers()[backforAtack.getX()][backforAtack.getY()].getPiece().getTeam() != turn.getPlayer().getTeam()) {
						
						turn.setAttackOnlyPossition(backforAtack);
						getUi().showPossibleMoveAttack(backforAtack.getX(), backforAtack.getY());
					}
				}
				
				if(turn.getPossiblePositions().size() == 0) {
					getUi().displayErrorMessage(turn.getPlayer().getName() + " this unit has nowhere to go.\nTry another one.");
					System.out.println("No possible moves selection rejected");
					return;
				}
				//showing possible moves
				turn.setSelectedCard(true);
				for(int i = 0; i < turn.getPossiblePositions().size(); i++) {
					
					//if the possition has no piece is just a move
					if( board.getMembers()[turn.getPossiblePositions().get(i).getX()][turn.getPossiblePositions().get(i).getY()].getPiece() == null )
						
						getUi().showPossibleMove(turn.getPossiblePositions().get(i).getX(), turn.getPossiblePositions().get(i).getY());
					
					//if it has then its surly an enemy piece so its an attack
					else getUi().showPossibleMoveAttack(turn.getPossiblePositions().get(i).getX(), turn.getPossiblePositions().get(i).getY());
					
					
					
				}
				
				return;
				
			}
			
			if(turn.hasSelectedCard() && !(turn.getPlayDone())) {
				
				//detemin if he is attaking
				if(board.getMembers()[XofButtonPressed][YofButtonPressed].getPiece() != null)turn.setAttacing(true);
				
				//moving
				if(turn.isAttacing() == false) {
					
					//check if the move is valid
					for(int i = 0; i < turn.getPossiblePositions().size(); i++) {
						if(XofButtonPressed == turn.getPossiblePositions().get(i).getX() && 
						   YofButtonPressed == turn.getPossiblePositions().get(i).getY())break;
						
						if(i == turn.getPossiblePositions().size() - 1 ) {
							System.out.println("not valid move");
							getUi().displayErrorMessage("That move isn't possible.\nTry again");
							turn.setSelectedCard(false);
							for(int i1 = 0; i1 < turn.getPossiblePositions().size(); i1++) {
								getUi().StopshowPossibleMove(turn.getPossiblePositions().get(i1).getX(), turn.getPossiblePositions().get(i1).getY());
							}
							
							return;
						}
					}
					
					playSoudClip("move", 0);
					
					System.out.println("Moving to " + XofButtonPressed + ":" + YofButtonPressed);
					
					//move piece in model and view
					piece pieceToMove = turn.getPieceSelected();
					getUi().setImageInButtonToDefult(pieceToMove.getPosition().getX(), pieceToMove.getPosition().getY());
					((MoveablePiece)pieceToMove).moveTo(new MyPosition(XofButtonPressed, YofButtonPressed), board);		
					getUi().setImageInButton(XofButtonPressed, YofButtonPressed, pieceToMove.getImage());
					
					//check if he can revive
					if( 	(turn.getPlayer().getRevives() < 2)&&
							!(pieceToMove instanceof Scout) &&
							!(((MoveablePiece)pieceToMove).hasRevived()) &&
							( (pieceToMove.getTeam() == Team.FIRE && pieceToMove.getPosition().getY() == 8) || (pieceToMove.getTeam() == Team.ICE && pieceToMove.getPosition().getY() == 1) ) )
					Revive(turn.getPlayer(), (MoveablePiece) turn.getPieceSelected());
					
					//remove green outline
					for(int i = 0; i < turn.getPossiblePositions().size(); i++) {
						getUi().StopshowPossibleMove(turn.getPossiblePositions().get(i).getX(), turn.getPossiblePositions().get(i).getY());
					}
					
					if(turn.getAttackOnlyPossition() != null)getUi().StopshowPossibleMove(turn.getAttackOnlyPossition().getX(), turn.getAttackOnlyPossition().getY());
					
					turn.setPlayDone(true);
					
					newTurn();
				}else {
					
					//check if the move is valid
					for(int i = 0; i < turn.getPossiblePositions().size(); i++) {
						if(XofButtonPressed == turn.getPossiblePositions().get(i).getX() && 
						   YofButtonPressed == turn.getPossiblePositions().get(i).getY())break;
						
						//if is the back atack i can do
						if(turn.getAttackOnlyPossition() != null && XofButtonPressed == turn.getAttackOnlyPossition().getX() &&
								YofButtonPressed == turn.getAttackOnlyPossition().getY())break;
						
						if(i == turn.getPossiblePositions().size() - 1 ) {
							System.out.println("not valid attacking");
							getUi().displayErrorMessage("That attack isn't possible.\nTry again");
							turn.setAttacing(false);
							return;
						}
					}
					
					turn.getPlayer().addBattlePlayed();
	
					piece peiceToAttack = board.getMembers()[XofButtonPressed][YofButtonPressed].getPiece();
					System.out.println("Attacking : (" + peiceToAttack +") At " + XofButtonPressed + ":" + YofButtonPressed);
					
					if(peiceToAttack instanceof Flag) {
						turn.getPlayer().setWonGame(true);
					}
					
					//if defender won
					if( ((MoveablePiece)turn.getPieceSelected()).determineWiner((MoveablePiece) turn.getPieceSelected(), peiceToAttack) == peiceToAttack ) {
						
						playSoudClip("lost", -30);
					    
						System.out.println("winner : " +  peiceToAttack );
						getUi().setImageInButtonToDefult(turn.getPieceSelected().getPosition().getX(), turn.getPieceSelected().getPosition().getY());
						try {
							((MoveablePiece)turn.getPieceSelected()).attack(peiceToAttack, board);
						} catch (WinnerException e1) {
							return;
						}
					}else if( ((MoveablePiece)turn.getPieceSelected()).determineWiner((MoveablePiece) turn.getPieceSelected(), peiceToAttack) == turn.getPieceSelected() ){
						//attack won
						
						playSoudClip("won", 0);
					    
						System.out.println("winner : " +  turn.getPieceSelected() );
						turn.getPlayer().addBattlewon();
						getUi().setImageInButtonToDefult(turn.getPieceSelected().getPosition().getX(), turn.getPieceSelected().getPosition().getY());
						getUi().setImageInButton(peiceToAttack.getPosition().getX(), peiceToAttack.getPosition().getY(), turn.getPieceSelected().getImage());
						try {
							((MoveablePiece)turn.getPieceSelected()).attack(peiceToAttack, board);
						} catch (WinnerException e1) {
							//not possible
							return;
						}
						
						//check if he can revive
						if( 	(turn.getPlayer().getRevives() < 2)&&
								!(turn.getPieceSelected() instanceof Scout) &&
								!(((MoveablePiece)turn.getPieceSelected()).hasRevived()) &&
								( (turn.getPieceSelected().getTeam() == Team.FIRE && turn.getPieceSelected().getPosition().getY() == 8) || (turn.getPieceSelected().getTeam() == Team.ICE && turn.getPieceSelected().getPosition().getY() == 1) ) )
						Revive(turn.getPlayer(), (MoveablePiece) turn.getPieceSelected());
						
					}else {
						//draw
						System.out.println("Draw");
						
						playSoudClip("draw",  0);
						getUi().setImageInButtonToDefult(turn.getPieceSelected().getPosition().getX(), turn.getPieceSelected().getPosition().getY());
						getUi().setImageInButtonToDefult(peiceToAttack.getPosition().getX(), peiceToAttack.getPosition().getY());
						try {
							((MoveablePiece)turn.getPieceSelected()).attack(peiceToAttack, board);
						} catch (WinnerException e1) {
							//not possible
							return;
						}
					}
					
					//remove green outline
					for(int i = 0; i < turn.getPossiblePositions().size(); i++) {
						getUi().StopshowPossibleMove(turn.getPossiblePositions().get(i).getX(), turn.getPossiblePositions().get(i).getY());
					}
					
					if(turn.getAttackOnlyPossition() != null)getUi().StopshowPossibleMove(turn.getAttackOnlyPossition().getX(), turn.getAttackOnlyPossition().getY());
					
					turn.setPlayDone(true);
					
					//if someone won the game
					if(turn.getPlayer().isWonGame()) {
						
						playSoudClip("success", 0);
						
						player p;
						if(turn.getPlayer() == playerFire)p = playerIce;
						else p = playerFire;
						
						//unhide cards of player who is now playing
						for(int i = 0; i < p.getPieces().size(); i++) {
							if(p.getPieces().get(i).isCaptured())continue;
							MyPosition toHide = p.getPieces().get(i).getPosition();
							getUi().setImageInButton(toHide.getX(), toHide.getY(), p.getPieces().get(i).getImage());
						}
						
						wui.setNameOfWiner(turn.getPlayer().getName());
						wui.setNumOfTurn(Turn.getCount());
						wui.setWper(turn.getPlayer().calcWper());
						wui.setVisible(true);
						
						return;
						
					}
								
					newTurn();
				}
			}
		}	
	}
	
	/**
	 * this action listener is attached to the buttons on the revive panel
	 * 
	 * poscondition: if called it revives the chosen piece 
	 */
	class listenForReviveChose implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			playSoudClip("revive", -20);
			
			JButton[][] buttons = rui.getButtons();
			int XofButtonPressed = 0;
			int YofButtonPressed = 0;
			
			boolean flag = false;
			for(int x = 1; x <= 3; x++) {
				if(flag)break;
				for(int y = 1; y <= 4; y++) {
					if( e.getSource() == buttons[x][y]) {
						XofButtonPressed = x;
						YofButtonPressed = y;
						flag = true;
						break;
					}
				}
			}
			
			//at this point the turn has changed so we find the other player so the one chossing is the one whos turn isnt now
			player p;
			if(playerFire.isHis_turn()) {
				p = playerIce; 
			} else {
				p = playerFire; 
			}
			
			MoveablePiece pieceToRevive = null;
			
			if( (XofButtonPressed == 1 && YofButtonPressed == 1) ) pieceToRevive = p.getCapturedDragon();
			else if( (XofButtonPressed == 1 && YofButtonPressed == 2) ) pieceToRevive = p.getCapturedMage();
			else if( (XofButtonPressed == 1 && YofButtonPressed == 3) ) pieceToRevive = p.getCapturedKnight(); 
			else if( (XofButtonPressed == 1 && YofButtonPressed == 4) ) pieceToRevive = p.getCapturedBeastRider(); 
			else if( (XofButtonPressed == 2 && YofButtonPressed == 1) ) pieceToRevive = p.getCapturedSorceress(); 
			else if( (XofButtonPressed == 2 && YofButtonPressed == 2) ) pieceToRevive = p.getCapturedLavaBeastYeti();
			else if( (XofButtonPressed == 2 && YofButtonPressed == 3) ) pieceToRevive = p.getCapturedElf();
			else if( (XofButtonPressed == 2 && YofButtonPressed == 4) ) pieceToRevive = p.getCapturedDwarf();
			else if( (XofButtonPressed == 3 && YofButtonPressed == 1) ) pieceToRevive = p.getCapturedScout();
			else if( (XofButtonPressed == 3 && YofButtonPressed == 2) ) pieceToRevive = p.getCapturedSlayer();				
			MyPosition spwonPosition;
			Random rand = new Random();
			if(p.getTeam() == Team.FIRE) {
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnR - minXForSpawnR) + 1) + minXForSpawnR , rand.nextInt( (maxYForSpawnR - minYForSpawnR) + 1) + minYForSpawnR );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
			}else {
				do {
					spwonPosition = new MyPosition(rand.nextInt((maxXForSpawnB - minXForSpawnB) + 1) + minXForSpawnB , rand.nextInt( (maxYForSpawnB - minYForSpawnB) + 1) + minYForSpawnB );
				}while(board.getMembers()[spwonPosition.getX()][spwonPosition.getY()].getPiece() != null);
			}
			
			System.out.println("reviving " + pieceToRevive + " at " + spwonPosition.getX() + ":" + spwonPosition.getY() );
			
			pieceToRevive.setCaptured(false);
			pieceToRevive.setPosition(spwonPosition);
			boardMember tile = board.getMembers()[spwonPosition.getX()][spwonPosition.getY()];
			tile.setPiece(pieceToRevive);
			tile.setCanMoveTo(false);
			getUi().setImageInButton(spwonPosition.getX(), spwonPosition.getY(), pieceToRevive.getImage());
			rui.setVisible(false);
			
			turn.getPlayerReviving().addRevive();
			turn.setPlayerReviving(null);
			turn.getPieceReviving().setHasRevived(true);
			turn.setPieceReviving(null);
			
		}
		
	}
	
	/**
	 * this action listener is attached to the butons that close the revive window if the player doesnt want to revive
	 * postcondition:will set the window visibility to false
	 */
	class exitWindowListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("revive denied");
			rui.setVisible(false);	
		}
	}
	
	/**
	 * atached to the buttons that terminate the game
	 * 
	 * postcondition: will dispose of all the windows
	 *
	 */
	class exitWListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("\nBYEEEEEEE!!!");
			ui.dispose();
			rui.dispose();
			wui.dispose();
			System.exit(0);
		}
	}
}
