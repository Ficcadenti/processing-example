package it.mafr.model;

import it.mafr.settings.Settings;

public class GrigliaDiGioco
{
	public static GrigliaDiGioco	instance	= null;
	private int[][]					matrice		= new int[Settings.GrigliaDiGioco.CELL_PER_LATO_X][Settings.GrigliaDiGioco.CELL_PER_LATO_Y];

	public static GrigliaDiGioco getIstance()
	{
		if (instance == null)
		{
			return instance = new GrigliaDiGioco();
		}
		else
		{
			return instance;
		}
	}

	private GrigliaDiGioco()
	{
		for (int x = 0; x < Settings.GrigliaDiGioco.CELL_PER_LATO_X; x++)
		{
			for (int y = 0; y < Settings.GrigliaDiGioco.CELL_PER_LATO_Y; y++)
			{
				this.matrice[x][y] = 0;
			}
		}
		generaMuro();
	}

	private void generaMuro()
	{
		for (int x = 0; x < Settings.GrigliaDiGioco.CELL_PER_LATO_X; x++)
		{
			for (int y = 0; y < Settings.GrigliaDiGioco.CELL_PER_LATO_Y; y++)
			{
				this.matrice[0][y] = 1;
				this.matrice[Settings.GrigliaDiGioco.CELL_PER_LATO_X - 1][y] = 1;
				this.matrice[x][Settings.GrigliaDiGioco.CELL_PER_LATO_Y - 1] = 1;
			}
		}
	}

	public void stampaMatrice()
	{
		for (int y = 0; y < Settings.GrigliaDiGioco.CELL_PER_LATO_Y; y++)
		{
			for (int x = 0; x < Settings.GrigliaDiGioco.CELL_PER_LATO_X; x++)
			{
				System.out.print(this.matrice[x][y]);
			}
			System.out.println("");
		}
	}

	public int[][] getMatrice()
	{
		return matrice;
	}

	public void setMatrice(int[][] matrice)
	{
		this.matrice = matrice;
	}

}
