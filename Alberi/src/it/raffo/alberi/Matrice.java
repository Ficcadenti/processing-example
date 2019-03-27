package it.raffo.alberi;

public class Matrice
{
	private static Matrice istanza;

	public static Matrice getInstance()
	{
		if (istanza == null)
		{
			istanza = new Matrice();
			istanza.setCollisioniGlobali(0);
		}

		return istanza;
	}

	private int		centriTrovati;
	private int		distanzaMinima;
	private int		h;
	private int[][]	matrice;
	private int		raggio;
	private int		w;
	private int		collisioniGlobali;

	private Matrice()
	{
		super();
		this.matrice = null;
		this.centriTrovati = 0;
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

	public Centro calcolaCentro(int raggio)
	{
		this.collisioniGlobali++;
		int cont = 1;
		int possibileCentroX = (int) ((Math.random() * this.w) - 1);
		int possibileCentroY = (int) ((Math.random() * this.h) - 1);
		Centro c = new Centro(possibileCentroX, possibileCentroY);
		// System.out.println(c);
		while (!this.isVuotoNeiDintorni(c))
		{
			if ((cont % 1000) == 0)
			{
				// System.out.println("Collisione n°:" + cont++);
			}
			possibileCentroX = (int) ((Math.random() * this.w) - 1);
			possibileCentroY = (int) ((Math.random() * this.h) - 1);
			c = new Centro(possibileCentroX, possibileCentroY);
		}
		cont = 1;
		// System.out.println("trovato centro (" + (++this.centriTrovati) + ": " + c);
		this.stampaCentroSuMatrice(c);
		return c;
	}

	public int getCentriTrovati()
	{
		return this.centriTrovati;
	}

	public int getCollisioniGlobali()
	{
		return this.collisioniGlobali;
	}

	private boolean isVuotoNeiDintorni(Centro c)
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

				if ((this.matrice[x][y] == 1) || (this.matrice[x][y] == 2))
				{
					bRet = false;
					break;
				}

			}
		}
		return bRet;

	}

	public void setCentriTrovati(int centriTrovati)
	{
		this.centriTrovati = centriTrovati;
	}

	public void setCollisioniGlobali(int collisioniGlobali)
	{
		this.collisioniGlobali = collisioniGlobali;
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
