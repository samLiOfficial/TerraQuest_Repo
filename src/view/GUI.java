package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import model.Tile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import model.Labyrinth;
import model.Layer;
import model.Tile;
import model.Player;

import java.util.Scanner;

public class GUI {

	Labyrinth currGame = new Labyrinth(4, 6);
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					window.showInstructions(); // Call showInstructions() on the GUI instance
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//Set the frame
		frame = new JFrame();
		frame.setTitle("TerraQuest: The Map Adventure"); 
		frame.setBounds(1000, 1000, 1957, 1262);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null);

		
		//Set the panel
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(6, 6, 1916, 1228);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		

		
		//Set game Board image
		JLabel gameBoard = new JLabel("New label");
		gameBoard.setIcon(new ImageIcon("NewGameBoard.png"));
		gameBoard.setBounds(120, 93, 669, 678);
		panel.add(gameBoard, Integer.valueOf(0));
	
		//Set extra tile image
		JLabel extraTile = new JLabel("New label");
		extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName()+".png"));
		extraTile.setBounds(1417, 863, 97, 98);
		panel.add(extraTile, Integer.valueOf(0));
		
		//Player cards text labels	
		JLabel player1 = new JLabel("Player RED card");
		player1.setBounds(988, 93, 169, 16);
		panel.add(player1);
		
		JLabel player2 = new JLabel("Player YELLOW card");
		player2.setBounds(988, 295, 169, 16);
		panel.add(player2);
		
		JLabel player3 = new JLabel("Player WHITE card");
		player3.setBounds(988, 491, 169, 16);
		panel.add(player3);
		
		JLabel player4 = new JLabel("Player GREEN card");
		player4.setBounds(988, 657, 169, 16);
		panel.add(player4);
		
		//Invisible label to display congraduation when game end
		JLabel Congrad = new JLabel("");
		Congrad.setBounds(367, 263, 1000, 300);
		Congrad.setFont(new Font("Serif", Font.PLAIN, 55));
		panel.add(Congrad,Integer.valueOf(4));
		
		JLabel[][] cardArray = new JLabel[4][6];
		
		//Generate card image labels using loop
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				cardArray[i][j] = new JLabel("");
				cardArray[i][j].setBounds(j*117 + 1169, i*188 + 40, 97, 148);
				cardArray[i][j].setIcon(new ImageIcon(""));
				panel.add(cardArray[i][j], Integer.valueOf(1));
				cardArray[i][j].setVisible(true);

			}
		}
		
		
		// Set the image of all the cards
		for (int i = 0; i< Labyrinth.player0.getTreasure().size(); i++)
		{
			cardArray[0][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player0.getTreasure().get(i)) + ".png"));
		}
		for (int i = 0; i< Labyrinth.player1.getTreasure().size(); i++)
		{
			cardArray[1][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player1.getTreasure().get(i)) + ".png"));
		}
		for (int i = 0; i< Labyrinth.player2.getTreasure().size(); i++)
		{
			cardArray[2][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player2.getTreasure().get(i)) + ".png"));
		}
		for (int i = 0; i< Labyrinth.player3.getTreasure().size(); i++)
		{
			cardArray[3][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player3.getTreasure().get(i)) + ".png"));
		}
		
		
		
		//Layer1 generation (Tile JLabel layer generation)
		Layer layer1 = new Layer();
		
		for (int i = 0; i<7; i++)
		{
			for (int j = 0; j< 7; j++)
			{
				layer1.getGrid()[i][j] = new JLabel("");
				layer1.getGrid()[i][j].setBounds(j*96 + 120, i*97 + 93, 96, 96);
				panel.add(layer1.getGrid()[i][j], Integer.valueOf(1));
				layer1.getGrid()[i][j].setVisible(true);
			}
		}
		
		layer1.reloadboard(currGame);


		
		//Layer 2 loop (Player JLabel layer generation)
		Layer layer2 = new Layer();
		
		for (int i = 0; i<7; i++)
		{
			for (int j = 0; j< 7; j++)
			{
				layer2.getGrid()[i][j] = new JLabel("");
				//layer2.getGrid()[i][j].setIcon(new ImageIcon("Ghost0.png"));
				layer2.getGrid()[i][j].setBounds(j*96 + 120, i*97 + 91, 96, 96);
				panel.add(layer2.getGrid()[i][j], Integer.valueOf(2));
				layer2.getGrid()[i][j].setVisible(true);
				layer2.getGrid()[i][j].setAlignmentX(48);
				layer2.getGrid()[i][j].setAlignmentY(48);
			}
		}
		
		//Initialize the game
		layer2.reloadPlayer(currGame);
		
		
		//Layer 3 loop (highlight JLabel layer generation)
		Layer layer3 = new Layer();
		
		for (int i = 0; i<7; i++)
		{
			for (int j = 0; j< 7; j++)
			{
				layer3.getGrid()[i][j] = new JLabel("");
				layer3.getGrid()[i][j].setBounds(j*96 + 120, i*97 + 91, 96, 96);
				panel.add(layer3.getGrid()[i][j], Integer.valueOf(3));
				layer3.getGrid()[i][j].setVisible(true);
			}
		}
		
		currGame.reloadHighlight();
		layer3.clearGrid();
		layer3.reloadHighLight(currGame);
		

		
		//Button to turn tile to left
		JButton turnLeft = new JButton("");
		turnLeft.setIcon(new ImageIcon("rotateLeft.png"));
		turnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currGame.getExtraOld().rotateLeft();
				extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			}
		});
		turnLeft.setBounds(1267, 903, 126, 126);
		panel.add(turnLeft, Integer.valueOf(0));
		
		//Button to turn tile to right
		JButton turnRight = new JButton("");
		turnRight.setIcon(new ImageIcon("rotateRight.png"));
		turnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currGame.getExtraOld().rotateRight();   
				extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			}
		});
		
		turnRight.setBounds(1536, 903, 126, 126);
		panel.add(turnRight);
		

		

		//Top insertion buttons
		JButton insertUpRight = new JButton("");
		insertUpRight.setIcon(new ImageIcon("downNew.png"));
		insertUpRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(0, 5);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertUpRight.setBounds(595, 0, 96, 98);
		panel.add(insertUpRight);
		
		JButton insertUpMid = new JButton("");
		insertUpMid.setIcon(new ImageIcon("downNew.png"));
		insertUpMid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(0, 3);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertUpMid.setBounds(400, 0, 96, 98);
		panel.add(insertUpMid);
		
		
		JButton insertUpLeft = new JButton("");
		insertUpLeft.setIcon(new ImageIcon("downNew.png"));
		insertUpLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(0, 1);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertUpLeft.setBounds(212, 0, 96, 98);
		panel.add(insertUpLeft);
		
		//Right insertion buttons
		JButton insertRightTop = new JButton("");
		insertRightTop.setIcon(new ImageIcon("leftNew.png"));
		insertRightTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(1, 6);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertRightTop.setBounds(789, 188, 96, 98);
		panel.add(insertRightTop);
		
		
		JButton insertRightMid = new JButton("");
		insertRightMid.setIcon(new ImageIcon("leftNew.png"));
		insertRightMid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(3, 6);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertRightMid.setBounds(789, 385, 96, 98);
		panel.add(insertRightMid);
		
		
		JButton insertRightBot = new JButton("");
		insertRightBot.setIcon(new ImageIcon("leftNew.png"));
		insertRightBot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(5, 6);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertRightBot.setBounds(789, 575, 96, 98);
		panel.add(insertRightBot);
		
		
		//Left insertion button
		JButton insertLeftTop = new JButton("");
		insertLeftTop.setIcon(new ImageIcon("rightNew.png"));
		insertLeftTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(1, 0);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertLeftTop.setBounds(16, 188, 96, 98);
		panel.add(insertLeftTop);
		
		JButton insertLeftMid = new JButton("");
		insertLeftMid.setIcon(new ImageIcon("rightNew.png"));
		insertLeftMid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(3, 0);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertLeftMid.setBounds(16, 385, 96, 98);
		panel.add(insertLeftMid);
		
		JButton insertLeftBot = new JButton("");
		insertLeftBot.setIcon(new ImageIcon("rightNew.png"));
		insertLeftBot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(5, 0);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertLeftBot.setBounds(16, 575, 96, 98);
		panel.add(insertLeftBot);
		
		
		//Button insertion button
		JButton insertDownLeft = new JButton("");
		insertDownLeft.setIcon(new ImageIcon("upNew.png"));
		insertDownLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(6, 1);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertDownLeft.setBounds(212, 771, 96, 98);
		panel.add(insertDownLeft);
		
		JButton insertDownMid = new JButton("");
		insertDownMid.setIcon(new ImageIcon("upNew.png"));
		insertDownMid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(6, 3);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.clearGrid();
			layer3.reloadHighLight(currGame);
			}
		});
		insertDownMid.setBounds(400, 771, 96, 98);
		panel.add(insertDownMid);
		
		JButton insertDownRight = new JButton("");
		insertDownRight.setIcon(new ImageIcon("upNew.png"));
		insertDownRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currGame.insertExtra(6, 5);
			currGame.regenerateTileName();
			extraTile.setIcon(new ImageIcon(currGame.getExtraOld().getName() + ".png"));
			layer1.reloadboard(currGame);
			layer2.clearGrid();
			layer2.reloadPlayer(currGame);
			currGame.reloadHighlight();
			layer3.reloadHighLight(currGame);
		
			}
		});
		insertDownRight.setBounds(595, 771, 96, 98);
		panel.add(insertDownRight);
		
		//Controller buttons
		JButton controlUp = new JButton("");
		controlUp.setIcon(new ImageIcon("up.png"));
		controlUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currGame.regenerateTileName();
				
				if(currGame.validMove(currGame.getLabyrinth(), currGame.getPlayerList()[currGame.getPlayerTurn()].getrow(), currGame.getPlayerList()[currGame.getPlayerTurn()].getcolumn())[0] == true)
				{
					currGame.getPlayerList()[currGame.getPlayerTurn()].playerMove(0);
				}
				layer2.clearGrid();
				layer2.reloadPlayer(currGame);
			

				
				
			}
		});
		controlUp.setBounds(947, 771, 104, 98);
		panel.add(controlUp);
		
		JButton controlRight = new JButton("");
		controlRight.setIcon(new ImageIcon("right.png"));
		controlRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currGame.regenerateTileName();
				

				if(currGame.validMove(currGame.getLabyrinth(), currGame.getPlayerList()[currGame.getPlayerTurn()].getrow(), currGame.getPlayerList()[currGame.getPlayerTurn()].getcolumn())[1] == true)
				{
					currGame.getPlayerList()[currGame.getPlayerTurn()].playerMove(1);
				}
				layer2.clearGrid();
				layer2.reloadPlayer(currGame);

			}
		});
		controlRight.setBounds(1046, 863, 104, 98);
		panel.add(controlRight);
		
		
		JButton controlDown = new JButton("");
		controlDown.setIcon(new ImageIcon("down.png"));
		controlDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				currGame.regenerateTileName();
				

				if(currGame.validMove(currGame.getLabyrinth(), currGame.getPlayerList()[currGame.getPlayerTurn()].getrow(), currGame.getPlayerList()[currGame.getPlayerTurn()].getcolumn())[2] == true)
				{
					currGame.getPlayerList()[currGame.getPlayerTurn()].playerMove(2);
				}
				layer2.clearGrid();
				layer2.reloadPlayer(currGame);

			}
		});
		controlDown.setBounds(947, 953, 104, 98);
		panel.add(controlDown);
		
		
		JButton controlLeft = new JButton("");
		controlLeft.setIcon(new ImageIcon("left.png"));
		controlLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currGame.regenerateTileName();
				
				if(currGame.validMove(currGame.getLabyrinth(), currGame.getPlayerList()[currGame.getPlayerTurn()].getrow(), currGame.getPlayerList()[currGame.getPlayerTurn()].getcolumn())[3] == true)
				{
					currGame.getPlayerList()[currGame.getPlayerTurn()].playerMove(3);
				}
				layer2.clearGrid();
				layer2.reloadPlayer(currGame);

			}
		});
		controlLeft.setBounds(850, 863, 104, 98);
		panel.add(controlLeft);
		
		//Player turn label
		JLabel playerTurn = new JLabel("        Red player turn");
		playerTurn.setFont(new Font("Serif", Font.PLAIN, 20));
		playerTurn.setBounds(346, 922, 200, 75);
		playerTurn.setBackground(Color.white);
		playerTurn.setOpaque(true);
		panel.add(playerTurn);
		
		//End turn button
		JButton endTurn = new JButton("");
		endTurn.setIcon(new ImageIcon("end.png"));
		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currGame.getPlayerList()[currGame.getPlayerTurn()].cardCheck();
				
				//Empty card image slots
				for (int i = 0; i< 6; i++)
				{

					cardArray[0][i].setIcon(new ImageIcon(""));
				}
				for (int i = 0; i< 6; i++)
				{
					cardArray[1][i].setIcon(new ImageIcon(""));
				}
				for (int i = 0; i< 6; i++)
				{
					cardArray[2][i].setIcon(new ImageIcon(""));
				}
				for (int i = 0; i< 6; i++)
				{
					cardArray[3][i].setIcon(new ImageIcon(""));
				}
				
				
				//Reload card image slots
				for (int i = 0; i< Labyrinth.player0.getTreasure().size(); i++)
				{

					cardArray[0][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player0.getTreasure().get(i)) + ".png"));
				}
				for (int i = 0; i< Labyrinth.player1.getTreasure().size(); i++)
				{
					cardArray[1][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player1.getTreasure().get(i)) + ".png"));
				}
				for (int i = 0; i< Labyrinth.player2.getTreasure().size(); i++)
				{
					cardArray[2][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player2.getTreasure().get(i)) + ".png"));
				}
				for (int i = 0; i< Labyrinth.player3.getTreasure().size(); i++)
				{
					cardArray[3][i].setIcon(new ImageIcon(translationTreasure(Labyrinth.player3.getTreasure().get(i)) + ".png"));
				}
				
				//Check if the player won
				if(currGame.getPlayerList()[currGame.getPlayerTurn()].playerWin() == true)
				{
					int realPlayerNum = currGame.getPlayerTurn()+1;
					System.out.println("  Player "+ realPlayerNum + " win!!!!! Now exit the game");
					Congrad.setBackground(Color.white);
					Congrad.setOpaque(true);
					Congrad.setText("     Player "+ realPlayerNum + " win!");
					Congrad.setIcon(new ImageIcon ("gameOver.png"));
				}
				
				currGame.setPlayerTurn();
				
				//Change the turn of the player on the label
				if (currGame.getPlayerTurn() == 0)
				{
					playerTurn.setText("        Red player turn");
				}
				
				else if (currGame.getPlayerTurn() == 1)
				{
					playerTurn.setText("     Yellow player turn");
				}
				
				else if (currGame.getPlayerTurn() == 2)
				{
					playerTurn.setText("     White player turn");
				}
				
				else if (currGame.getPlayerTurn() == 3)
				{
					playerTurn.setText("     Green player turn");
				}
				
				currGame.reloadHighlight();
				layer3.clearGrid();
				layer3.reloadHighLight(currGame);
				
			}
		});
		endTurn.setBounds(633, 903, 125, 125);
		panel.add(endTurn);
		
		
		// Button to view game instructions
		JButton instructionButton = new JButton("Game Instruction");
		instructionButton.setFont(instructionButton.getFont().deriveFont(Font.BOLD));
		instructionButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        showInstructions();
		    }
		});
		instructionButton.setBounds(10, 10, 150, 60);  // Increased button height to 40
		panel.add(instructionButton);
	
		
	}
	
	
	

		
	
	//Translate treasure from number to words to let them be the same with the name of the PNG images.
	public String translationTreasure(int TreaNum)
	{
		if (TreaNum == 0)
		{
			return "" ;
		}
		if (TreaNum == 1)
		{
			return "CardBat" ;
		}
		
		if (TreaNum == 2)
		{
			return "CardBeetle" ;
		}
		if (TreaNum == 3)
		{
			return "CardBook" ;
		}
		if (TreaNum == 4)
		{
			return "CardCandle" ;
		}
		if (TreaNum == 5)
		{
			return "CardDiamond" ;
		}
		if (TreaNum == 6)
		{
			return "CardDragon" ;
		}
		if (TreaNum == 7)
		{
			return "CardEmerald" ;
		}
		if (TreaNum == 8)
		{
			return "CardGenie" ;
		}
		if (TreaNum == 9)
		{
			return "CardGhost" ;
		}
		if (TreaNum == 10)
		{
			return "CardHorse" ;
		}
		if (TreaNum == 11)
		{
			return "CardKeys" ;
		}
		if (TreaNum == 12)
		{
			return "CardKnight" ;
		}
		if (TreaNum == 13)
		{
			return "CardLizard" ;
		}
		if (TreaNum == 14)
		{
			return "CardMap" ;
		}
		if (TreaNum == 15)
		{
			return "CardMoney" ;
		}
		if (TreaNum == 16)
		{
			return "CardMoth" ;
		}
		if (TreaNum == 17)
		{
			return "CardMouse" ;
		}
		if (TreaNum == 18)
		{
			return "CardOwl" ;
		}
		if (TreaNum == 19)
		{
			return "CardPig" ;
		}
		if (TreaNum == 20)
		{
			return "CardRing" ;
		}
		if (TreaNum == 21)
		{
			return "CardSkeleton" ;
		}
		if (TreaNum == 22)
		{
			return "CardSorceress" ;
		}
		if (TreaNum == 23)
		{
			return "CardSpider" ;
		}
		else
		{
			return "CardTreasureChest" ;
		}
	
	}

	private void showInstructions() {
	    String instructions = "TerraQuest: The Map Adventure Game Instructions:\n\n" +
	            "1. Objective: Be the first player to collect all your treasure cards and return to your starting position.\n\n" +
	            "2. Gameplay:\n" +
	            "   a. Each player takes turns to move their game piece through the labyrinth and collect treasure cards.\n" +
	            "   b. On your turn, you can shift rows or columns of the labyrinth to create new paths and reach your goal.\n" +
	            "   c. The extra tile, represented by an image, can be inserted into the labyrinth to change its configuration.\n" +
	            "   d. Use the control buttons to move your game piece in the desired direction (up, down, left, or right).\n\n" +
	            "3. Collecting Treasure Cards:\n" +
	            "   a. Your game piece can collect a treasure card by reaching a tile with the corresponding treasure symbol.\n" +
	            "   b. Each player has their own set of treasure cards, displayed on the game board.\n" +
	            "   c. The collected cards are displayed on the respective player's card slots.\n\n" +
	            "4. Ending a Turn:\n" +
	            "   a. After each turn, players can check if they have collected all their treasure cards.\n" +
	            "   b. If a player collects all their cards, they win the game.\n" +
	            "   c. Press the 'End Turn' button to conclude your turn and proceed to the next player.\n\n" +
	            "5. Winning the Game:\n" +
	            "   The first player to collect all their treasure cards and return to their starting position wins the game.\n\n" +
	            "Enjoy playing TerraQuest and may the best strategist win!";

	    // Use a custom icon with a transparent image
	    ImageIcon icon = new ImageIcon("logoIns.png");

	    JOptionPane.showMessageDialog(frame, instructions, "Game Instructions", JOptionPane.INFORMATION_MESSAGE, icon); 
	   
	}
	
}
	
	