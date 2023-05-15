package model;

public class Tile {
	
	private int type; //type 0 is T shape, type 1 is bent shape, type 2 is straight shape.
	private int direction; //0 is up, 1 is right, 2 is down, 3 is left 
	private int treasureNumber; //treasure 0 is no treasure
	private boolean[] openings;
	private String name;

	private boolean highlighted = false;
	
	//Constructor
	public Tile(int type, int direction, int treasureNumber) {
		setType(type);
		setDirection(direction);
		setTreasureNumber(treasureNumber);
		setHighlighted(false);
		generateName();
		setOpenings();//from the instant type and direction was set, openings was as well set.
	}

	//setters and getters. 
	public int getType() {
		return type;
	}
	
	//overloaded method: returns type of tile.
	public int getType(Tile tile) {
		return tile.getType();
	}
	
	//Getters and setters
	public void setType(int type) {
		this.type = type;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if (-1<direction&&direction<4) {
			this.direction = direction;
		}else {//if an invalid direction is given, it will generate a random one.
			this.direction = (int)(Math.random()*4);
		}
	}
	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
	

	public boolean[] getOpenings() {
		return openings;
	}

	public int getTreasureNumber() {
		return treasureNumber;
	}

	public void setTreasureNumber(int treasureNumber) {
		this.treasureNumber = treasureNumber;
	}
	
	public String  getName() {
		return name;
	}

	public void setName(String str) {
		this.name = str;
	}
	
	//Generate the tile's name that match the PNGs images in the program folder""
	public void generateName() {
		if (treasureNumber != 0)
		{
			if (this.treasureNumber == 1)
			{
				this.name = "Bat" + Integer.toString(this.getDirection());
			}
			
			if (this.treasureNumber == 2)
			{
				this.name = "Beetle" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 3)
			{
				this.name = "Book" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 4)
			{
				this.name = "Candle" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 5)
			{
				this.name = "Diamond" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 6)
			{
				this.name = "Dragon" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 7)
			{
				this.name = "Emerld" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 8)
			{
				this.name = "Genie" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 9)
			{
				this.name = "Ghost" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 10)
			{
				this.name = "Horse" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 11)
			{
				this.name = "Keys" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 12)
			{
				this.name = "Knight" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 13)
			{
				this.name = "Lizard" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 14)
			{
				this.name = "Map" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 15)
			{
				this.name = "Money" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 16)
			{
				this.name = "Moth" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 17)
			{
				this.name = "Mouse" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 18)
			{
				this.name = "Owl" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 19)
			{
				this.name = "Pig" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 20)
			{
				this.name = "Ring" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 21)
			{
				this.name = "Skeleton" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 22)
			{
				this.name = "Sorceress" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 23)
			{
				this.name = "Spider" + Integer.toString(this.getDirection());
			}
			if (this.treasureNumber == 24)
			{
				this.name = "TreasureChest" + Integer.toString(this.getDirection());
			}
		}
		else if (treasureNumber == 0)
		{
			if (type == 0)
			{
				this.name = "T" + Integer.toString(this.getDirection());
			}
			if (type == 1)
			{
				this.name = "L" + Integer.toString(this.getDirection());
			}

			if (type == 2)
			{
				this.name = "I" + Integer.toString(this.getDirection());
			}

		}
	}
	
	
	//rotates tile orientation. 
	//0 is up, 1 is right, 2 is down, 3 is left
	public void rotateRight() {
		if (direction==3) {
			direction=0;
		}else {
			direction++;
		}
		 
		generateName();
		setOpenings();
		
	}
	
	public void rotateLeft() {
		if (direction==0) {
			direction=3;
		}else {
			direction--;
		}
		
		generateName();
		setOpenings();
	}
	
	//Parameter: int direction
	//Returns: boolean array
	//Given a direction, returns boolean array that shows which directions have openings.
	//setOpenings calls the other three checks.
	public void setOpenings(){
		if (this.type == 0)
		{
			this.openings=tBlockCheck(this.direction);
		}
		else if (this.type == 1)
		{
			this.openings=bentCheck(this.direction);
		}
		else 
		{
			this.openings=straightCheck(this.direction);
		}
		
	}
	

	public boolean[] tBlockCheck(int direction) {
		boolean[] opening=new boolean[]{true, true, true, true};
		
		
		opening[direction] = false;
		return opening;
	}
	
	
	public boolean[] straightCheck(int direction) {
		boolean[] opening=new boolean[]{false,false,false,false};
		opening[direction]=true;
		
		if (direction == 0 || direction == 2)
		{
			opening[0] = true;
			opening[2] = true;
		}
		else if (direction == 1 || direction == 3)
		{
			opening[1] = true;
			opening[3] = true;
		}
		

		return opening;
	}
	
	
	public boolean[] bentCheck(int direction) {
		boolean[] opening=new boolean[]{false,false,false,false};
		
		if (direction == 0)
		{
			opening[0] = true;
			opening[1] = true;
		}
		
		if (direction == 1)
		{
			opening[1] = true;
			opening[2] = true;
		}
		
		if (direction == 2)
		{
			opening[2] = true;
			opening[3] = true;
		}
		
		if (direction == 3)
		{
			opening[3] = true;
			opening[0] = true;
		}


	
		return opening;
	}
	
	
	
	@Override
	public String toString() {
		return "Tile [type=" + type + ", direction=" + direction + ", treasureNumber=" + treasureNumber+ "]";
	}
	
	//Creates save. 
	public String toSaveString() {
		return String.format("%s\n%s\n%s\n",getType(),getDirection(),getTreasureNumber());
	}
}