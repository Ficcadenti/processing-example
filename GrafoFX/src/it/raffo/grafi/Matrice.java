package it.raffo.grafi;

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

	private int		distanzaMinima;

	private int		h;

	private int[][]	matrice;

	private int		raggio;

	private int		w;

	private Matrice()
	{
		super();
		this.matrice = null;
	}

	public void azzeraMatrice()
	{
		this.matrice = new int[this.w][this.h];
		for (int x = 0; x < this.w; x++)
		{
			for (int y = 0; y < this.h; y++)
			{
				this.matrice[x][y] = 0;
			}
		}
	}

	public Centro calcolaCentro()
	{
		int cont = 1;
		int possibileCentroX = (int) ((Math.random() * this.w) - 1);
		int possibileCentroY = (int) ((Math.random() * this.h) - 1);
		Centro c = new Centro(possibileCentroX, possibileCentroY);
		while (!this.isVuotoNeiDintorni(c, this.matrice))
		{
			System.out.println("Collisione n°:" + cont++);
			possibileCentroX = (int) ((Math.random() * this.w) - 1);
			possibileCentroY = (int) ((Math.random() * this.h) - 1);
			c = new Centro(possibileCentroX, possibileCentroY);
		}
		cont = 1;
		System.out.println("trovato centro: " + c);
		this.stampaCentroSuMatrice(c);
		return c;
	}

	private boolean isVuotoNeiDintorni(Centro c, int[][] m)
	{
		boolean bRet = true;
		int recinto = this.raggio + this.distanzaMinima;

		// controllo se sono al margine della matrice tenendo conto del raggio
		if ((c.getX() - recinto) < 0)
		{
			return false;
		}
		if ((c.getX() + recinto) >= this.w)
		{
			return false;
		}
		if ((c.getY() - recinto) < 0)
		{
			return false;
		}
		if ((c.getY() + recinto) >= this.h)
		{
			return false;
		}

		for (int x = c.getX() - (recinto); x < (c.getX() + (recinto)); x++)
		{
			for (int y = c.getY() - (recinto); y < (c.getY() + (recinto)); y++)
			{
				if ((m[x][y] == 1) || (m[x][y] == 2))
				{
					bRet = false;
					break;
				}
			}
		}
		return bRet;

	}

	public void setDistanzaMinima(int distanzaMinima)
	{
		this.distanzaMinima = distanzaMinima;
	}

	public void setH(int h)
	{
		this.h = h;
	}

	public void setMatrice(int[][] matrice)
	{
		this.matrice = matrice;
	}

	public void setRaggio(int raggio)
	{
		this.raggio = raggio;
	}

	public void setW(int w)
	{
		this.w = w;
	}

	private void stampaCentroSuMatrice(Centro c)
	{
		int recinto = this.raggio + this.distanzaMinima;
		for (int x = c.getX() - (recinto); x < (c.getX() + (recinto)); x++)
		{
			for (int y = c.getY() - (recinto); y < (c.getY() + (recinto)); y++)
			{
				this.matrice[x][y] = 2;
			}
		}

		for (int x = c.getX() - (this.raggio); x < (c.getX() + (this.raggio)); x++)
		{
			for (int y = c.getY() - (this.raggio); y < (c.getY() + (this.raggio)); y++)
			{
				this.matrice[x][y] = 1;
			}
		}
	}

	public void stampaMatrice()
	{
		for (int y = 0; y < this.h; y++)
		{
			for (int x = 0; x < this.w; x++)
			{
				System.out.print(this.matrice[x][y]);
			}
			System.out.println();
		}

	}
}
