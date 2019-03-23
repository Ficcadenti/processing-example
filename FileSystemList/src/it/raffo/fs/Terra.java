package it.raffo.fs;

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

		}
		return istanza;
	}

	private int					h;
	private int					w;

	private ArrayList<Giardino>	terra	= new ArrayList<>();

	public Centro calcolaCentro(int diametro)
	{
		Centro cBuono = this.calcolaCentro(0, diametro);
		Giardino gBuono = new Giardino(cBuono, cBuono.getX() - diametro, cBuono.getX() + diametro,
				cBuono.getY() - diametro, cBuono.getY() + diametro);
		gBuono.setNomeGiardino("" + diametro);
		this.terra.add(gBuono);
		return gBuono;
	}

	private Centro calcolaCentro(int collisione, int diametro)
	{
		System.out.println("Collisione N°: " + collisione++);

		int cont = 1;
		int possibileCentroX = (int) ((Math.random() * this.w) - 1);
		int possibileCentroY = (int) ((Math.random() * this.h) - 1);
		Centro c = new Centro(possibileCentroX, possibileCentroY);
		Giardino gPossibile = new Giardino(c, possibileCentroX - diametro, possibileCentroX + diametro,
				possibileCentroY - diametro, possibileCentroY + diametro);

		if (!this.neiConfini(gPossibile))
		{
			this.calcolaCentro(collisione, diametro);
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

				this.calcolaCentro(collisione, diametro);
			}
		}
		return c;

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
		return false;

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
