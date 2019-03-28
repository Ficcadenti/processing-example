package it.raffo.giocodellavita.model;

public class Matrice
{
	private static Matrice istanza;

	public static Matrice getInstance()
	{
		if (istanza == null)
		{
			istanza = new Matrice();
		}

		return istanza;
	}

	private int			h;
	private Cellula[][]	matrice;
	private int			w;

	private Matrice()
	{
		super();
		this.matrice = null;
	}

	public void azzeraMatrice()
	{
		this.matrice = new Cellula[this.w][this.h];
		for (int x = 0; x < this.w; x++)
		{
			for (int y = 0; y < this.h; y++)
			{

				this.matrice[x][y] = new Cellula(x, y, Cellula.CELLULA_MORTA);
				this.matrice[x][y].setColonizzata(false);
			}
		}
	}

	public int getH()
	{
		return this.h;
	}

	public Cellula[][] getMatrice()
	{
		return this.matrice;
	}

	public int getW()
	{
		return this.w;
	}

	public void inserisciCellula(Cellula v)
	{
		this.matrice[v.getX()][v.getY()] = v;
	}

	public void setH(int h)
	{
		this.h = h;
	}

	public void setMatrice(Cellula[][] matrice)
	{
		this.matrice = matrice;
	}

	public void setW(int w)
	{
		this.w = w;
	}

	public void stampaGenerazioneAttuale()
	{
		for (int y = 0; y < this.h; y++)
		{
			for (int x = 0; x < this.w; x++)
			{

				System.out.print(this.matrice[x][y].getStatoIniziale());

			}
			System.out.println();
		}
	}

	public void stampaGenerazioneSuccessiva()
	{
		for (int y = 0; y < this.h; y++)
		{
			for (int x = 0; x < this.w; x++)
			{

				System.out.print(this.matrice[x][y].getStatoGenerazioneSuccessiva());

			}
			System.out.println();
		}
	}
}
