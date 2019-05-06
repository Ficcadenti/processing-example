package it.mafr.start;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.mafr.model.GrigliaDiGioco;
import it.mafr.model.Matrix;
import it.mafr.model.MattoncinoL;
import it.mafr.settings.Settings;
import it.mafr.utility.Rotazioni;
import processing.core.PApplet;

public class Avvio extends processing.core.PApplet
{
	MattoncinoL						ML			= new MattoncinoL();

	private static GrigliaDiGioco	gameGrid	= GrigliaDiGioco.getIstance();

	private static final Logger		log			= Logger.getLogger(Avvio.class);

	public static void main(String[] args)
	{
		PApplet.main("it.mafr.start.Avvio");
		PropertyConfigurator.configure("log4j.properties");
		log.info("Mattoncini Mafr");
		Matrix.getIstance().setMattoncinoMatrix(MattoncinoL.getMattoncinoL());
		Matrix.getIstance().ruota(Rotazioni.DX);
		Matrix.getIstance().stampaMatrice();
	}

	@Override
	public void settings()
	{
		this.size(Settings.GrigliaDiGioco.WIDTH, Settings.GrigliaDiGioco.HEIGHT);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.drawGameGrid();
		gameGrid.stampaMatrice();
		this.drawWall();
		Matrix.getIstance().setMattoncinoMatrix(MattoncinoL.getMatrice());
		Matrix.getIstance().ruota(Rotazioni.DX);
		Matrix.getIstance().stampaMatrice();
	}

	@Override
	public void draw()
	{
	}

	private void drawGameGrid()
	{
		this.stroke(255, 150);
		for (int x = 0; x < Settings.GrigliaDiGioco.WIDTH; x += Settings.GrigliaDiGioco.LATO_CELL_X)
		{
			this.line(x, 0, x, this.height);
		}
		for (int y = 0; y < Settings.GrigliaDiGioco.HEIGHT; y += Settings.GrigliaDiGioco.LATO_CELL_Y)
		{
			this.line(0, y, this.width, y);
		}
	}

	private void drawWall()
	{
		int[][] m = GrigliaDiGioco.getIstance().getMatrice();

	}
}
