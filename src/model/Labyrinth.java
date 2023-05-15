package model;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;



public class Labyrinth {
	public static Tile[][] labyrinth;
	private static Tile extraOld;
	private static Tile extraNew;
	
	private int saveNumber = 0;
	public static int playerTurn=0;
	public static int players;
	static int cardNumbers;
	public static Player[] playerList = new Player[4];
	
	
	private static int[][] playerCard = new int[4][6];
	
	private static Stack<Integer> player0List = new Stack<Integer>();
	private static Stack<Integer> player1List = new Stack<Integer>();
	private static Stack<Integer> player2List = new Stack<Integer>();
	private static Stack<Integer> player3List = new Stack<Integer>();
	

	
	//Constructor
	public Labyrinth(int player, int cardNumber) {
		labyrinth=generation();
		this.players=player;
		this.cardNumbers=cardNumber;
		createPlayer(player, cardNumber);
		reloadHighlight();
	}

	//Alternate constructor.
	public Labyrinth(Tile[][] labyrinth, int playerNum, int cardNumber) {
		labyrinth=getLabyrinth();
		createPlayer (playerNum, cardNumber);
		
	}

	public static Player player0 = new Player("Player 1",0,0, player0List, cardNumbers);
	public static Player player1 = new Player("Player 2",0,6, player1List, cardNumbers);
	public static Player player2 = new Player("Player 3",6,0, player2List, cardNumbers);
	public static Player player3 = new Player("Player 4",6,6, player3List, cardNumbers);
	
	//Creates players and deals them cards.
	public void createPlayer(int player, int cardNumber) {
		Stack<Integer> cardList = new Stack<Integer>();
		for (int i = 1; i < 25; i++){
			cardList.add(i);
		}		
		Collections.shuffle(cardList);
		
		if (player>1) {
			playerList[0]=player0;
			for(int j = 0; j < cardNumber;j++){
				player0List.push(cardList.pop());
			}
			player0.copyTreasure(player0List);
			playerList[1]=player1;
			for(int j = 0; j < cardNumber;j++){
				player1List.push(cardList.pop());
			}
			player1.copyTreasure(player1List);
		}
		if (player>2) {
			playerList[2]=player2;
			for(int j = 0; j < cardNumber;j++){
				player2List.push(cardList.pop());
			}
			player2.copyTreasure(player2List);
		}
		
		
		if (player>3) {
			playerList[3]=player3;
			for(int j = 0; j < cardNumber;j++){
				player3List.push(cardList.pop());
			}
			player3.copyTreasure(player3List);
		}
		playerTurn = 0;
	}
	

	

	
	
	public static Player[] getPlayerList() {
		return playerList;
	}

	public static void setPlayerList(Player[] playerList) {
		Labyrinth.playerList = playerList;
	}

	//Getters and setters
	public static Tile[][] getLabyrinth() {
		return labyrinth;
	}
	
	public void setLabyrinth(Tile[][] newLabyrinth) {
		labyrinth = newLabyrinth;
	}
	
	public int getPlayerTurn() {
		return playerTurn;
	}
	
	//Sets player turn to next player.
	//If player turn is greater than player, which means that the
	//last player's turn has passed, it goes to player 1. 
	public void setPlayerTurn() {
		if (playerTurn == -1 ) {
			playerTurn = 0;
		}else {
			if (playerTurn>=players-1) {
				playerTurn=0;
			}else {
				playerTurn++;
			}
		}
	}
	
	
	//generate the labyrinth randomly.
	public Tile[][] generation() {
		Tile[][] labyrinth = new Tile[7][7];
		Stack<Tile> generator = new Stack<Tile>();
		Stack<Integer> tTreasure = new Stack<Integer>();
		Stack<Integer> lTreasure = new Stack<Integer>();
		Stack<Integer> posX = new Stack<Integer>();
		Stack<Integer> posY = new Stack<Integer>();
		
		tTreasure.add(2);
		tTreasure.add(5);
		tTreasure.add(13);
		tTreasure.add(22);
		tTreasure.add(23);
		tTreasure.add(24);
		Collections.shuffle(tTreasure);
		
		lTreasure.add(9);
		lTreasure.add(10);
		lTreasure.add(14);
		lTreasure.add(17);
		lTreasure.add(18);
		lTreasure.add(21);	
		Collections.shuffle(lTreasure);
		
		for (int i = 0; i<6; i++) {//generates 6 T-tiles with treasures.
			generator.add(new Tile(0,-1,tTreasure.pop()));
		}
		for (int i = 0; i<6;i++) {//generates 5 L-tiles with treasures. //COllin it is 6 not 5!!!!!!!! 
			generator.add(new Tile(1,-1,lTreasure.pop()));
		}
		for (int i = 0; i<=9; i++) {//generates 9 L-tiles without treasures. // Collin I think it is 8
			generator.add(new Tile(1,-1,0));
		}
		for (int i = 0; i<=13;i++) {//generates 13 I-tiles without treasures. 
			generator.add(new Tile(2,-1,0));
		}
		
		//presets
		labyrinth[0][0]=new Tile(1,1,0);//red start
		labyrinth[2][0]=new Tile(0,3,8); 
		labyrinth[4][0]=new Tile(0,3,12);
		labyrinth[6][0]=new Tile(1,0,0);//yellow start
		
		labyrinth[0][2]=new Tile(0,0,7); 
		labyrinth[2][2]=new Tile(0,3,1); 
		labyrinth[4][2]=new Tile(0,2,4); 
		labyrinth[6][2]=new Tile(0,2,15);
		
		labyrinth[0][4]=new Tile(0,0,11);
		labyrinth[2][4]=new Tile(0,0,20);
		labyrinth[4][4]=new Tile(0,1,16);
		labyrinth[6][4]=new Tile(0,2,19);
		
		labyrinth[0][6]=new Tile(1,2,0);//white start
		labyrinth[2][6]=new Tile(0,1,3);
		labyrinth[4][6]=new Tile(0,1,6);
		labyrinth[6][6]=new Tile(1,3,0);//green start
		
		//generating positions
		for (int i = 0; i<3;i++){
			for (int j = 0;j<3;j++) {
				posX.add(2*j+1);
			}
			for (int j = 0; j<7;j++) {
				posX.add(j);
			}
		}
		for (int j = 0;j<3;j++) {
			posX.add(2*j+1);
		}
		
		//yPos
		for (int i = 0; i<3;i++){
			for (int j = 0;j<3;j++) {
				posY.add(2*i);
			}
			for (int j = 0; j<7;j++) {
				posY.add(2*i+1);
			}
		}
		for (int j = 0;j<3;j++) {
			posY.add(6);
		}
		
		Collections.shuffle(generator);
		
		
		for (int i = 0;i<33;i++) {

			labyrinth[posX.pop()][posY.pop()]=generator.pop();
		}
		extraOld = generator.pop();
		return(labyrinth);
	}
	
	//Just in case. Regenerates the labyrinth.
	public void regenerateLabyrinth() {
		labyrinth=generation();
	}
		
	//inserts a tile in target row and column.
	//if the operation is valid, it will return boolean true and insert the tile. 
	//else, it will return boolean false.
	public static void insertExtra(int row, int column) {
		if (column==0) {
			extraNew = labyrinth[row][6]; 
			
			int movedPlayer0 = 5;
			
			for (int i = 0; i<4 ;i++)
			{
				if (playerList[i].getrow() == row && playerList[i].getcolumn() == 6){
					playerList[i].setColumn(0); 
					movedPlayer0 = i;
				}
			}
			
			for (int i = 6; i>0; i--) {
				
				for (int j = 0; j < 4; j ++)
				{
					if (playerList[j].getrow() == row &&  playerList[j].getcolumn() == i - 1 && j!= movedPlayer0)
					{
						playerList[j].playerMove(1);
					}
				}
				
				labyrinth[row][i]=labyrinth[row][i-1];
				
			}
			labyrinth[row][0]=extraOld;
			

					
					
		}
		
		if (column==6) {
	
			extraNew = labyrinth[row][0]; 
			

			int movedPlayer1 = 5;
			
			for (int i = 0; i<4 ;i++)
			{
				if (playerList[i].getrow() == row && playerList[i].getcolumn() == 0){
					playerList[i].setColumn(6);

					movedPlayer1 = i;
				}
			}
			
			for (int i = 0; i < 6; i++) {
				
				for (int j = 0; j < 4; j ++)
				{
					if (playerList[j].getrow() == row &&  playerList[j].getcolumn() == i + 1 && j!= movedPlayer1)
					{
						playerList[j].playerMove(3);
					}
				}
				
				labyrinth[row][i]=labyrinth[row][i+1];
			}
			labyrinth[row][6]=extraOld;
			

			
		}
		
		if (row==0) {
			
			extraNew = labyrinth[6][column]; 
		
			int movedPlayer2 = 5;
			
			for (int i = 0; i<4 ;i++)
			{
				if (playerList[i].getrow() == 6 && playerList[i].getcolumn() == column){
					playerList[i].setRow(0);
					movedPlayer2 = i;
				}
			}
			
			for (int i = 6; i > 0; i--) {
				
				for (int j = 0; j < 4; j ++)
				{
					if (playerList[j].getrow() == i - 1 &&  playerList[j].getcolumn() == column && j != movedPlayer2)
					{
						playerList[j].playerMove(2);
					}
				}
				
				labyrinth[i][column]=labyrinth[i-1][column]; 
			}
			labyrinth[0][column]=extraOld;
			

			
		}
		
		if (row==6) {
			extraNew = labyrinth[0][column]; 
			
			int movedPlayer3 = 5;
			
			for (int i = 0; i<4 ;i++)
			{
				if (playerList[i].getrow() == 0 && playerList[i].getcolumn() == column){
					playerList[i].setRow(6);
					movedPlayer3 = i;
				}
			}
			
			for (int i = 0; i < 6; i++)  {
				
				for (int j = 0; j < 4; j ++)
				{
					if (playerList[j].getrow() == i + 1 &&  playerList[j].getcolumn() == column && j != movedPlayer3)
					{
						playerList[j].playerMove(0);
					}
				}
				
				labyrinth[i][column]=labyrinth[i+1][column];
			}
			labyrinth[6][column]=extraOld;
			
		
			
			
			
		}
		
		extraOld = extraNew;
	
		
		reloadHighlight();
		
		
		}
	
	//determines if the surrounding tiles are valid.
	public static boolean[] validMove(Tile[][] labyrinth, int row, int column) {
		boolean[] result = new boolean [] {false, false, false, false};
		boolean[] openings = new boolean[4];
		boolean[] openings2= new boolean[4];
		openings=labyrinth[row][column].getOpenings();
		if (0<row&&row<7) {//top check
			openings2=labyrinth[row-1][column].getOpenings();
			if (openings[0] == true &&openings2[2] == true) {
			result[0]=true;
			}
		}
		
		if (-1<row&&row<6) {//bottom check
			openings2=labyrinth[row + 1][column].getOpenings();
			if (openings[2] == true  &&openings2[0] == true) {
				result[2]=true;
			}
		}
		
		if (0<column&&column<7) {//left check
			openings2=labyrinth[row][column-1].getOpenings();
			if (openings[3] == true &&openings2[1] == true) {
				result[3]=true;
			}
		}
		if (-1<column&&column<6) {//right check
			openings2=labyrinth[row][column+1].getOpenings();
			if (openings[1] == true &&openings2[3] == true) {
				result[1]=true;
			}
		}
		return result;	
		
	}

	//determines if can move to the tile in direction of selected tile
		public boolean validMove(int row, int column, int direction) {
			if (validMove(labyrinth, row, column)[direction] == true) {
				return true;
			}
			return false;
		}

	
	// Every time player make a move, we just unhighlight all the tile
	// Then we start to highlight from the start.
	public static void unhighlightAll() {
		
		for (int i = 0; i < 7; i ++)
		{
			for (int j = 0; j< 7; j++)
			{
				labyrinth[i][j].setHighlighted(false);
			}
		}
	}
	

	
	//highlight FINISHED!
	public static void highlightPath (int currentRow, int currentColumn)
	{
		// First set the spot as "Highlighted"
		labyrinth[currentRow][currentColumn].setHighlighted(true);
		
		// Import the opening array of the tile
		boolean [] tempMoves = validMove(labyrinth,currentRow,currentColumn);

		
		boolean containPlayer = false;
		
		if (currentRow == playerList[playerTurn].getrow() && currentColumn == playerList[playerTurn].getcolumn())
			containPlayer = true;
		
		//get a counter
		int counter = 0;
		
		//count how many openings are there on the current tile
		for(int i = 0; i < 4; i++)
		{
			if (validMove(labyrinth,currentRow,currentColumn)[i] == true)  {
				counter++;
			}
		}
		
		//Set all the connected tiles to highlighted
		
		if (counter > 1 || containPlayer == true) 
		{
			if (tempMoves[0] == true && currentRow>0 && labyrinth[currentRow-1][currentColumn].isHighlighted()==false)
			{
				highlightPath (currentRow-1, currentColumn);
			}
			
			if (tempMoves[1] == true && currentColumn<6 && labyrinth[currentRow][currentColumn+1].isHighlighted()==false)
			{
				highlightPath (currentRow, currentColumn+1);
			}
			
			if (tempMoves[2] == true && currentRow<6 && labyrinth[currentRow+1][currentColumn].isHighlighted()==false)
			{
				highlightPath (currentRow + 1, currentColumn);
			}
			
			if (tempMoves[3] == true && currentColumn>0 && labyrinth[currentRow][currentColumn-1].isHighlighted()==false)
			{
				highlightPath (currentRow, currentColumn -1);
			}	
		}
		
		
		
		
	
		// Basically if the tile is more than 1 openings, then just recurse the same check 
		// with all of its attached tile 
		// Because if it only have a opening, then it is like the end of the route.
			
			
		//The problem is that if the player starts at the starting spot, that is only 1 openings
		//So when the it is the tile that player sits on, we still do the recursion
		//But when the tile is like no player and only have one path, we can know that is the end of the 
		// route
	
		
		
		
		
	}
	
	public static void reloadHighlight()
	{	
		unhighlightAll();
		highlightPath(playerList[playerTurn].getrow(), playerList[playerTurn].getcolumn());
	}
	
	public void regenerateTileName()
	{
		for (int i = 0; i<7; i++)
		{
			for (int j = 0; j< 7; j++)
			{
				this.labyrinth[i][j].generateName();
				this.labyrinth[i][j].setOpenings();
			}
		}
	}
	
	
		public static Player getPlayer(int player) {
			if (player==0)
				return player0;
			else if (player==1)
				return player1;
			else if (player==2)
				return player2;
			else if (player==3)
				return player3;
			return player0;
		}
		
		
		public static Tile getExtraOld() {
			return extraOld;
		}

		public static void setExtraOld(Tile extraOld) {
			Labyrinth.extraOld = extraOld;
		}

		public static int getPlayers() {
			return players;
		}

		public static int getCardNumbers() {
			return cardNumbers;
		}

		public static void setCardNumbers(int cardNumbers) {
			Labyrinth.cardNumbers = cardNumbers;
		}
		
		
}