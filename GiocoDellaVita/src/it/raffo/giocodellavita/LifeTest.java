package it.raffo.giocodellavita;

import processing.core.PApplet;

public class LifeTest extends PApplet
{

	public static int	h	= 1900;
	public static int	w	= 1600;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.giocodellavita.LifeTest");
		Matrice.getInstance().setW(w);
		Matrice.getInstance().setH(h);
		Matrice.getInstance().azzeraMatrice();
	}

	@Override
	public void draw()
	{

	}

	public void generaPunti(int i)
	{
		for (int x = 0; x < i; x++)
		{
			this.stroke(255);
			this.strokeWeight(this.random(0, 2));
			this.point(this.random(0, this.w), this.random(0, this.h));
		}
	}

	@Override
	public void settings()
	{
		this.size(this.w, this.h);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.generaPunti(200);
	}

}
