package it.raffo.giocodellavita;

import it.raffo.giocodellavita.controller.GiocoDellaVita;
import it.raffo.giocodellavita.model.Cellula;
import it.raffo.giocodellavita.model.Matrice;
import processing.core.PApplet;
import processing.core.PImage;

public class LifeTest extends PApplet
{

	public static int     generazioni = 0;
	public static int     maxCellule  = 0;
	public static int     hM          = 100;
	public static int     wM          = 100;

	public static int     h           = 800;
	public static int     w           = 800;
	public static boolean start       = false;
	public static PImage  img;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.giocodellavita.LifeTest");
	}

	public void buttonOK()
	{
		this.pushMatrix();
		this.noFill();
		this.stroke(255, 0, 0);
		this.rect((w / 2) - 50, 870, 100, 50);
		this.fill(255);
		this.textAlign(this.CENTER);
		this.textFont(this.createFont("Arial", 32, true), 20);
		this.text("Start", (w / 2), 900);
		this.stroke(100, 100, 100);
		// this.image(img, (w / 2), 900, 50, 50);
		this.popMatrix();
	}

	@Override
	public void draw()
	{
		if ((this.mousePressed == true) && (this.mouseX > ((w / 2) - 50)) && (((w / 2) - 50) < ((w / 2) + 50)))
		{
			if ((this.mouseY > 870) && (this.mouseY < 920))
			{
				start = true;
			}
		}
		if (start)
		{
			this.background(0);
			GiocoDellaVita.getInstance().start(Matrice.getInstance().getMatrice());
			GiocoDellaVita.getInstance().generazioneSuccessiva(Matrice.getInstance().getMatrice());
			generazioni++;
			this.drawGenerazioni();
		}
		this.buttonOK();
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
	public void mouseDragged()
	{
		if (this.mouseY < 800)
		{
			System.out
			        .println("mouseDragged: " + (this.mouseX / GiocoDellaVita.getInstance().getRapportoMatriceTavoloW()) + ","
			                + (this.mouseY / GiocoDellaVita.getInstance().getRapportoMatriceTavoloH()));
			Cellula c;
			if (this.mouseButton == LEFT)
			{
				c = new Cellula(this.mouseX / GiocoDellaVita.getInstance().getRapportoMatriceTavoloW(), this.mouseY / GiocoDellaVita.getInstance().getRapportoMatriceTavoloH(),
				        Cellula.CELLULA_VIVA);
				c.setColonizzata(false);
			} else
			{
				c = new Cellula(this.mouseX / GiocoDellaVita.getInstance().getRapportoMatriceTavoloW(), this.mouseY / GiocoDellaVita.getInstance().getRapportoMatriceTavoloH(),
				        Cellula.CELLULA_MORTA);
				c.setColonizzata(false);
			}
			Matrice.getInstance().inserisciCellula(c);
			GiocoDellaVita.getInstance().drawCellula(c);
		}

	}

	// @Override
	// public void mousePressed()
	// {
	// System.out.println("Pressed: " + this.mouseX + "," + this.mouseY + ", " +
	// this.mouseButton);
	// if ((this.mousePressed == true) && (this.mouseX > ((w / 2) - 50)) && (((w /
	// 2) - 50) < ((w / 2) + 50)))
	// {
	// if ((this.mouseY > 870) && (this.mouseY < 920))
	// {
	// start = true;
	// }
	// }
	// }

	@Override
	public void settings()
	{
		this.size(this.w, this.h + 150);
	}

	@Override
	public void setup()
	{
		this.background(0);

		img = this.loadImage("start.png");

		GiocoDellaVita.getInstance().setW(w);
		GiocoDellaVita.getInstance().setH(h);
		GiocoDellaVita.getInstance().setwM(wM);
		GiocoDellaVita.getInstance().sethM(hM);

		GiocoDellaVita.getInstance().setPa(this);
		// GiocoDellaVita.getInstance().initVita("11101001100111101001", 4, 5); // R
		GiocoDellaVita.getInstance().initVita("11111000111010011111", 4, 5); // R
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
		// GiocoDellaVita.getInstance().start(Matrice.getInstance().getMatrice());
		// Matrice.getInstance().stampaGenerazioneAttuale();
		// Matrice.getInstance().stampaGenerazioneSuccessiva();
		//
		// GiocoDellaVita.getInstance().generazioneSuccessiva(Matrice.getInstance().getMatrice());
		// Matrice.getInstance().stampaGenerazioneAttuale();
		//
		// GiocoDellaVita.getInstance().start(Matrice.getInstance().getMatrice());
		// GiocoDellaVita.getInstance().generazioneSuccessiva(Matrice.getInstance().getMatrice());
		// Matrice.getInstance().stampaGenerazioneAttuale();
	}

}
