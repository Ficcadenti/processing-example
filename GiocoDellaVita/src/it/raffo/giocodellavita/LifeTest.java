package it.raffo.giocodellavita;

import it.raffo.giocodellavita.controller.GiocoDellaVita;
import it.raffo.giocodellavita.model.Matrice;
import processing.core.PApplet;

public class LifeTest extends PApplet
{

	public static int	generazioni	= 0;
	public static int	maxCellule	= 0;
	public static int	hM			= 200;
	public static int	wM			= 200;

	public static int	h			= 800;
	public static int	w			= 800;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.giocodellavita.LifeTest");
	}

	@Override
	public void draw()
	{
		this.background(0);
		GiocoDellaVita.getInstance().start(Matrice.getInstance().getMatrice());
		// this.delay(100);
		GiocoDellaVita.getInstance().generazioneSuccessiva(Matrice.getInstance().getMatrice());
		generazioni++;
		this.drawGenerazioni();
	}

	public void drawGenerazioni()
	{
		this.pushMatrix();
		this.fill(255);
		this.textAlign(this.LEFT);
		this.textFont(this.createFont("Arial", 32, true), 20);
		int numCellule = GiocoDellaVita.getInstance().contaCellule();
		int celleColonizzate = GiocoDellaVita.getInstance().contaCelluleColonizzate();
		if (numCellule > maxCellule)
		{
			maxCellule = numCellule;
		}
		this.text("Generazione : " + generazioni + ";   # cellule : " + numCellule + ";   MAX cellule : " + maxCellule
				+ ";   Celle colonizzate : " + celleColonizzate, 10, (this.h + (50 + (20 / 2))));
		this.popMatrix();
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
		this.size(this.w, this.h + 100);
	}

	@Override
	public void setup()
	{
		this.background(0);

		GiocoDellaVita.getInstance().setW(w);
		GiocoDellaVita.getInstance().setH(h);
		GiocoDellaVita.getInstance().setwM(wM);
		GiocoDellaVita.getInstance().sethM(hM);

		GiocoDellaVita.getInstance().setPa(this);
		GiocoDellaVita.getInstance().initVita("011110010", 3, 3); // R
		// GiocoDellaVita.getInstance().initVita("010001111",3,3); // Aliante
		// GiocoDellaVita.getInstance()
		// .initVita("00000000000000000000000000000000000000" +
		// "00000000000000000000000001000000000000"
		// + "00000000000000000000000101000000000000" +
		// "00000000000001100000011000000000000110"
		// + "00000000000010001000011000000000000110" +
		// "01100000000100000100011000000000000000"
		// + "01100000000100010110000101000000000000" +
		// "00000000000100000100000001000000000000"
		// + "00000000000010001000000000000000000000" +
		// "00000000000001100000000000000000000000"
		// + "00000000000000000000000000000000000000", 38, 11); // l cannone di
		// navicelle di Gosper
	}

}
