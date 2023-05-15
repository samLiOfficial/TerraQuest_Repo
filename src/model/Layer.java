package model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class Layer {

	//Every Layer have a grid of 7 x 7 JLabel and a Panel
	private JLabel[][] grid = new JLabel[7][7];
	private JPanel currPanel;

	//Constructor
	public Layer() {
		super();
	}

	//Setter and Getters
	public JLabel[][] getGrid() {
		return grid;
	}

	public void setLayer(JLabel[][] grid) {
		this.grid = grid;
	}
	
	//Clear the image of every JLabel on the grid
	public void clearGrid()
	{
		for (int i = 0; i<7; i++)
		{
			for (int j = 0; j< 7; j++)
			{
				grid[i][j].setIcon(new ImageIcon(""));
			}
		}
	}
	
	//Reload the player's image
	public void reloadPlayer(Labyrinth lab)
	{
		grid[lab.player0.getrow()][lab.player0.getcolumn()].setIcon(new ImageIcon("player1.png"));
		grid[lab.player1.getrow()][lab.player1.getcolumn()].setIcon(new ImageIcon("player2.png"));
		grid[lab.player2.getrow()][lab.player2.getcolumn()].setIcon(new ImageIcon("player3.png"));
		grid[lab.player3.getrow()][lab.player3.getcolumn()].setIcon(new ImageIcon("player4.png"));
		
	
	}
	
	
	public void reloadHighLight(Labyrinth lab)
	{
		for (int i = 0; i<7; i++)
		{
			for (int j = 0; j< 7; j++)
			{
				if (lab.getLabyrinth()[i][j].isHighlighted())
				{
					grid[i][j].setIcon(new ImageIcon("highlight.png"));
				}
				
			}
		}
	}


	//Reload the tile image 
	public void reloadboard(Labyrinth lab)
	{
		for (int i = 0; i<7; i++)
		{
			if (i == 0 || i%2 == 0)
			{
				for (int j = 1; j < 7; j=j+2)
				{
					grid[i][j].setIcon(new ImageIcon(lab.getLabyrinth()[i][j].getName() + ".png"));
				}
			}
			else
			{
				for (int j = 0; j < 7; j++)
				{
					grid[i][j].setIcon(new ImageIcon(lab.getLabyrinth()[i][j].getName() + ".png"));
				}
			}
		}
		
	
	}
	
	
	
	
}
