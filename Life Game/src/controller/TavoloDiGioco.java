package controller;

import model.Cellula;
import processing.core.PApplet;

public class TavoloDiGioco extends PApplet
{
	static int			width					= 800;
	static int			height					= 800;
	static int			cellPerLato				= 10;
	static int			grandezzaLatoCellula	= width / cellPerLato;
	static Cellula[][]	table					= new Cellula[cellPerLato][cellPerLato];

	public static void main(String[] args)
	{
		PApplet.main("controller.TavoloDiGioco");
	}

	public void accendiCellula(int x, int y)
	{
		this.fill(0, 255, 255);
		this.rect(table[x][y].getPosizioneX(), table[x][y].getPosizioneY(), grandezzaLatoCellula, grandezzaLatoCellula);
	}

	private void disegnaGrigliaGioco()
	{
		this.stroke(255);
		for (int x = 0; x < width; x += grandezzaLatoCellula)
		{
			this.line(x, 0, x, height);
		}
		for (int y = 0; y < height; y += grandezzaLatoCellula)
		{
			this.line(0, y, width, y);
		}
	}

	@Override
	public void draw()
	{

	}

	@Override
	public void settings()
	{
		this.size(width, height);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.startMatrice();
		this.disegnaGrigliaGioco();
		this.accendiCellula(6, 1);
	}

	public void startMatrice()
	{
		for (int x = 0; x < (table.length - 1); x++)
		{
			for (int y = 0; y < (table.length - 1); y++)
			{
				table[x][y] = new Cellula(x * grandezzaLatoCellula, y * grandezzaLatoCellula, "SPENTO", "SPENTO");
			}
		}
	}
}