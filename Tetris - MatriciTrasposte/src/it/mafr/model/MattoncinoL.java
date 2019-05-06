package it.mafr.model;

import it.mafr.utility.MattonciniLifeKey;

public class MattoncinoL
{
	private static int		WIDTH			= 2;
	private static int		HEIGHT			= 3;
	private static int[][]	MATTONCINO_L	= new int[WIDTH][HEIGHT];

	public MattoncinoL()
	{

		key_life(MattonciniLifeKey.ELLE);
	}

	public void key_life(MattonciniLifeKey elle)
	{

		int i = 0;
		for (int y = 0; y < HEIGHT; y++)
		{
			for (int x = 0; x < WIDTH; x++)
			{
				char lettera = elle.getLifeKey().charAt(i);
				int dna = Character.getNumericValue(lettera);
				{
					MATTONCINO_L[x][y] = dna;
				}
				i++;
			}
		}
	}

	public static int getWidth()
	{
		return WIDTH;
	}

	public static int getHeight()
	{
		return HEIGHT;
	}

	public static int[][] getMattoncinoL()
	{
		return MATTONCINO_L;
	}

	public static int[][] getMatrice()
	{
		return null;
	}

}
