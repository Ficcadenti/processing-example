package it.raffo.alberi;

import java.util.ArrayList;
import java.util.Iterator;

public class Terra
{
	private static Terra istanza;

	public static Terra getInstance()
	{
		if (istanza == null)
		{
			istanza = new Terra();
			istanza.setCollisioniGlobali(0);

		}

		return istanza;
	}

	private int					h;
	private int					w;
	private int					collisioniGlobali;

	private ArrayList<Giardino>	terra	= new ArrayList<>();

	public Centro calcolaCentro(int raggio)
	{
		Centro cBuono = this.calcolaCentro(0, raggio);
		Giardino gBuono = new Giardino(cBuono, cBuono.getX() - raggio, cBuono.getX() + raggio, cBuono.getY() - raggio,
				cBuono.getY() + raggio);
		gBuono.setNomeGiardino("" + raggio);
		this.terra.add(gBuono);
		return gBuono;
	}

	private Centro calcolaCentro(int collisione, int raggio)
	{
		// System.out.println("Collisione N°: " + collisione++);

		int cont = 1;
		this.collisioniGlobali++;
		int possibileCentroX = (int) ((Math.random() * this.w) - 1);
		int possibileCentroY = (int) ((Math.random() * this.h) - 1);
		Centro c = new Centro(possibileCentroX, possibileCentroY);
		Giardino gPossibile = new Giardino(c, possibileCentroX - raggio, possibileCentroX + raggio,
				possibileCentroY - raggio, possibileCentroY + raggio);

		if (this.neiConfini(gPossibile) == false)
		{
			this.calcolaCentro(collisione, raggio);
		}

		Iterator<Giardino> iter = this.terra.iterator();

		if (!iter.hasNext())
		{
			System.out.println("Il giardino è buono :" + gPossibile);
			return c;
		}
		else
		{
			Giardino gEsistente = iter.next();
			if (gEsistente.interferisce(gPossibile))
			{

				this.calcolaCentro(collisione, raggio);
			}
		}
		return c;

	}

	public int getCollisioniGlobali()
	{
		return this.collisioniGlobali;
	}

	public int getH()
	{
		return this.h;
	}

	public ArrayList<Giardino> getTerra()
	{
		return this.terra;
	}

	public int getW()
	{
		return this.w;
	}

	public boolean neiConfini(Giardino g)
	{
		if (g.getgXS() < 0)
		{
			return false;
		}
		if (g.getgXD() >= this.w)
		{
			return false;
		}
		if (g.getgYT() < 0)
		{
			return false;
		}
		if (g.getgYD() >= this.h)
		{
			return false;
		}

		return true;
	}

	private void setCollisioniGlobali(int collisioniGlobali)
	{
		this.collisioniGlobali = collisioniGlobali;
	}

	public void setH(int h)
	{
		this.h = h;
	}

	public void setTerra(ArrayList<Giardino> terra)
	{
		this.terra = terra;
	}

	public void setW(int w)
	{
		this.w = w;
	}

}
