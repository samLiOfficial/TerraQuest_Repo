package model; 

import java.util.Stack; 

public class Player{
	private String name; 
	private int column; 
	private int row; 
	private Stack<Integer> treasure = new Stack<Integer>(); 
	private int treasureObtained=0; 
	private int[] originalList; 
	
	//constructor.
	public Player(String name, int column, int row, Stack<Integer> treasure, int cardNumber) {
		setName(name); 
		setColumn(column); 
		setRow(row); 
		setTreasure(treasure); 
		copyTreasure(this.treasure); 
		originalList = new int[6];
	}

	//setters and getters. 
	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name; 
	}

	public int getcolumn() {
		return column; 
	}

	public void setColumn(int column) {
		this.column = column; 
	}

	public int getrow() {
		return row; 
	}

	public void setRow(int row) {
		this.row = row; 
	}

	
	//returns a stack containing treasures the player must acquire.
	public Stack<Integer> getTreasure() {
		return treasure; 
	}
	
	//sets stack with integers. 
	@SuppressWarnings("unchecked")
	public void setTreasure(Stack<Integer> treasure) {
		this.treasure = treasure; 
	}

	
	//adds treasure number to stack. 
	public void addTreasure(int treasureNumber) {
		this.treasure.add(treasureNumber); 
	}

	public int getTreasureObtained() {
		return treasureObtained; 
	}

	public void setTreasureObtained(int treasureObtained) {
		this.treasureObtained = treasureObtained; 
	}

	//creates array of treasure numbers that is the same as the stack
	public void copyTreasure(Stack<Integer> treasure) {
		for (int i = 0; i<treasure.size(); i++) {
		this.originalList[i] = (int)treasure.get(i); 
		}
	}
	
	//Given a treasure number, will return integer that is index of treasure
	//in original treasure list. If treasure not in list, will return -1.
	public int findOriginalIndex(int treasureNumber) {
		for (int i = 0; i<this.originalList.length; i++) {
			if (treasureNumber == this.originalList[i]) {
				return i; 
			}
		}
		return -1; 
	}
	
	//player movement. 
	//0 is up, 1 is right, 2 is down, 3 is left.
	//automatically "wraps around."
	public void playerMove(int direction) {
		switch(direction) {
		case 0: 
			if(row!=0) {  
				this.row = this.row -1 ; 
			} break; 
			
		case 1: 
			if(column!=6) {  
				column = column + 1 ; 
			}  break; 
			
		case 2: 
			if(row!=6) {  
				row = row + 1; 
			}break; 
			
		case 3: 
			if(column!=0) {  
				column = column -1;
			}break; 
			
		}
		
	}
	
	//Determines if player has moved to a tile that contains a treasure they want.
	//If they have, removes the treasure from their list. 
	//Called by playerMove.
	//TODO Need to come back and check why not a arraylist
	public void cardCheck() {
		int currentTreasure = Labyrinth.getLabyrinth()[row][column].getTreasureNumber(); 
		
		if (treasure.contains(currentTreasure)){
			setTreasureObtained(treasure.indexOf(currentTreasure)); 
			
			int index = treasure.indexOf(currentTreasure);
			
			treasure.remove(index); 
		}
		
	}

	//if player has no more treasures to acquire, returns boolean true.
	//else, returns boolean false.
	public boolean playerWin() {
		if (treasure.isEmpty()) {
			return true; 
		}
		return false; 
	}

	
	//Automatically generated.
	@Override
	public String toString() {
		return "Player [name=" + name + ", row=" + column + ", column=" + row + ", treasure=" + treasure + "]"; 
	}
	
	public String toSaveString() {
		return String.format("%s\n%s\n%s\n%s", getName(), getrow(), getcolumn(), this.treasure.toString()); 
	}

	public int[] getOriginalList() {
		return originalList; 
	}

	public void setOriginalList(int[] originalList) {
		this.originalList = originalList.clone(); 
	}
	
	
}