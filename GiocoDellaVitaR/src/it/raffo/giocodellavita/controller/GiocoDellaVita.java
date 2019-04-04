package it.raffo.giocodellavita.controller;

import java.util.Random;

import it.raffo.giocodellavita.model.Cellula;
import it.raffo.giocodellavita.model.GiardinoCellulare;
import it.raffo.giocodellavita.model.Matrice;
import processing.core.PApplet;

public class GiocoDellaVita
{

	private static GiocoDellaVita istanza;

	public static GiocoDellaVita getInstance()
	{
		if (istanza == null)
		{
			istanza = new GiocoDellaVita();
		}

		return istanza;
	}

	private PApplet pa;

	private int     wM, hM;
	private int     w, h;
	private int     rapportoMatriceTavoloW;
	private int     rapportoMatriceTavoloH;

	private void calcolaStatoCellula(Cellula v)
	{
		int cellule = 0;
		Cellula m[][] = Matrice.getInstance().getMatrice();
		// creo giardino dell'eden :)
		GiardinoCellulare g = new GiardinoCellulare(v, this.wM, this.hM);
		for (int y = g.getgYT(); y <= g.getgYD(); y++)
		{
			for (int x = g.getgXS(); x <= g.getgXD(); x++)
			{
				cellule = cellule + m[x][y].getStatoIniziale();
			}
		}
		cellule = cellule - v.getStatoIniziale();
		// System.out.println("x=" + v.getX() + "; y=" + v.getY() + " cellule=" +
		// cellule);
		if ((v.getStatoIniziale() == Cellula.CELLULA_MORTA) && (cellule == 3))
		{
			// System.out.println("NASCERA'");
			v.setStatoGenerazioneSuccessiva(Cellula.NASCERA);
			v.setColonizzata(true);
		}
		if ((v.getStatoIniziale() == Cellula.CELLULA_VIVA) && ((cellule == 2) || (cellule == 3)))
		{
			// System.out.println("VIVRA'");
			v.setStatoGenerazioneSuccessiva(Cellula.VIVRA);
			v.setColonizzata(true);
		}
		if ((v.getStatoIniziale() == Cellula.CELLULA_VIVA) && ((cellule < 2) || (cellule > 3)))
		{
			// System.out.println("MORIRA' per sovraffolamento");
			v.setStatoGenerazioneSuccessiva(Cellula.MORIRA);
		}

	}

	public void clearCellula(Cellula cel)
	{
		this.pa.pushMatrix();
		this.pa.fill(0, 0, 0);
		this.pa.rect(cel.getX(), cel.getY(), this.getRapportoMatriceTavoloW(), this.getRapportoMatriceTavoloH());
		this.pa.popMatrix();
	}

	public int contaCellule()
	{
		Cellula[][] m = Matrice.getInstance().getMatrice();
		int cellule = 0;
		for (int y = 0; y < this.hM; y++)
		{
			for (int x = 0; x < this.wM; x++)
			{
				cellule = cellule + m[x][y].getStatoIniziale();
			}
		}
		return cellule;
	}

	public int contaCelluleColonizzate()
	{
		Cellula[][] m = Matrice.getInstance().getMatrice();
		int cellule = 0;
		for (int y = 0; y < this.hM; y++)
		{
			for (int x = 0; x < this.wM; x++)
			{
				if (m[x][y].isColonizzata())
				{
					cellule++;
				}
			}
		}
		return cellule;
	}

	public void drawCellula(Cellula cel)
	{

		int x = cel.getX() * this.getRapportoMatriceTavoloW();
		int y = cel.getY() * this.getRapportoMatriceTavoloH();

		this.pa.pushMatrix();

		if (cel.getStatoIniziale() == Cellula.CELLULA_VIVA)
		{
			this.pa.fill(255, 255, 0);
		} else if (cel.getStatoIniziale() == Cellula.CELLULA_MORTA)
		{
			{
				// if (cel.isColonizzata())
				// // if (cel.isColonizzata() && (cel.getOpacita() < 10)) // disegno le celle
				// // colonizzate da almeno una
				// // cellula
				// {
				// this.pa.fill(238, 252, 234, 70);
				// } else
				{
					int opacita = cel.getOpacita();
					// cel.setOpacita(opacita - Cellula.STEP_OPACITA);
					// this.pa.fill(255, 255, 0, cel.getOpacita());
					this.pa.fill(0, 0, 0);
				}
			}
		}
		this.pa.rect(x, y, this.getRapportoMatriceTavoloW(), this.getRapportoMatriceTavoloH());
		this.pa.popMatrix();

	}

	private void drawGriglia()
	{
		this.pa.pushMatrix();
		this.pa.stroke(100, 100, 100);
		for (int x = 0; x <= this.wM; x++)
		{
			this.pa.line(x * this.getRapportoMatriceTavoloW(), 0, x * this.getRapportoMatriceTavoloW(), this.h);
		}

		for (int y = 0; y <= this.hM; y++)
		{
			this.pa.line(0, y * this.getRapportoMatriceTavoloH(), this.w, y * this.getRapportoMatriceTavoloH());
		}
		this.pa.popMatrix();
	}

	public void generaAdamoEva()
	{
		int possibileCentroX = (int) ((Math.random() * this.wM) - 1);
		int possibileCentroY = (int) ((Math.random() * this.hM) - 1);
		Cellula v = new Cellula(possibileCentroX, possibileCentroY, Cellula.CELLULA_VIVA);
		Matrice.getInstance().inserisciCellula(v);
		this.drawCellula(v);

		v = new Cellula(possibileCentroX + 1, possibileCentroY, Cellula.CELLULA_VIVA);
		Matrice.getInstance().inserisciCellula(v);
		this.drawCellula(v);

		v = new Cellula(possibileCentroX + 1, possibileCentroY + 1, Cellula.CELLULA_VIVA);
		Matrice.getInstance().inserisciCellula(v);
		this.drawCellula(v);

		v = new Cellula(possibileCentroX + 2, possibileCentroY, Cellula.CELLULA_VIVA);
		Matrice.getInstance().inserisciCellula(v);
		this.drawCellula(v);

		v = new Cellula(possibileCentroX + 2, possibileCentroY - 2, Cellula.CELLULA_VIVA);
		Matrice.getInstance().inserisciCellula(v);

		v = new Cellula(possibileCentroX + 2, possibileCentroY - 1, Cellula.CELLULA_VIVA);
		Matrice.getInstance().inserisciCellula(v);
		this.drawCellula(v);
	}

	private void generaFamiglia(String famiglia, int dx, int dy)
	{
		int pos = 0;
		int lato = (int) Math.sqrt(famiglia.length());

		Cellula v = null;

		int possibileCentroX = this.getRandomNumberInRange(0, this.wM - lato);
		int possibileCentroY = this.getRandomNumberInRange(0, this.hM - lato);

		for (int y = 0; y < dy; y++)
		{
			for (int x = 0; x < dx; x++)
			{
				v = new Cellula(possibileCentroX + x, possibileCentroY + y,
				        Integer.parseInt("" + famiglia.charAt(pos++)));
				Matrice.getInstance().inserisciCellula(v);
				this.drawCellula(v);
			}
		}
	}

	public void generazioneSuccessiva(Cellula[][] m)
	{
		this.drawGriglia();
		int stato = 0;
		for (int y = 0; y < this.hM; y++)
		{
			for (int x = 0; x < this.wM; x++)
			{
				stato = m[x][y].getStatoGenerazioneSuccessiva();
				switch (stato)
				{
					case Cellula.MORIRA: {
						m[x][y].setStatoIniziale(Cellula.CELLULA_MORTA);

					}
						break;
					case Cellula.NASCERA: {
						m[x][y].setStatoIniziale(Cellula.CELLULA_VIVA);

					}
						break;
				}
				this.drawCellula(m[x][y]);
			}
		}

	}

	public int getH()
	{
		return this.h;
	}

	public int gethM()
	{
		return this.hM;
	}

	public PApplet getPa()
	{
		return this.pa;
	}

	private int getRandomNumberInRange(int min, int max)
	{

		if (min >= max)
		{
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public int getRapportoMatriceTavoloH()
	{
		return this.rapportoMatriceTavoloH;
	}

	public int getRapportoMatriceTavoloW()
	{
		return this.rapportoMatriceTavoloW;
	}

	public int getW()
	{
		return this.w;
	}

	public int getwM()
	{
		return this.wM;
	}

	public void initVita(String famiglia, int dx, int dy)
	{
		System.out.println("Gioco della vita start");

		// inizializzo matrice
		Matrice.getInstance().setW(this.wM);
		Matrice.getInstance().setH(this.hM);
		Matrice.getInstance().azzeraMatrice();

		// calcolo il rapporto
		this.setRapportoMatriceTavoloW(this.w / this.wM);
		this.setRapportoMatriceTavoloH(this.h / this.hM);

		// disegno la griglia
		this.drawGriglia();

		// genero famiglia iniziale
		// this.generaAdamoEva();
		// this.generaFamiglia(famiglia, dx, dy);

		// this.start(Matrice.getInstance().getMatrice());
		// this.generazioneSuccessiva(Matrice.getInstance().getMatrice());

		// System.out.println("MATRICE STEP 0");
		// Matrice.getInstance().stampaGenerazioneAttuale();
		// this.start(Matrice.getInstance().getMatrice());
		// this.generazioneSuccessiva(Matrice.getInstance().getMatrice());
		// this.start(Matrice.getInstance().getMatrice());
		// this.generazioneSuccessiva(Matrice.getInstance().getMatrice());
		// System.out.println("MATRICE STEP 2");
		// Matrice.getInstance().stampaGenerazioneAttuale();

	}

	public void setH(int h)
	{
		this.h = h;
	}

	public void sethM(int hM)
	{
		this.hM = hM;
	}

	public void setPa(PApplet pa)
	{
		this.pa = pa;
	}

	public void setRapportoMatriceTavoloH(int rapportoMatriceTavoloH)
	{
		this.rapportoMatriceTavoloH = rapportoMatriceTavoloH;
	}

	public void setRapportoMatriceTavoloW(int rapportoMatriceTavoloW)
	{
		this.rapportoMatriceTavoloW = rapportoMatriceTavoloW;
	}

	public void setW(int w)
	{
		this.w = w;
	}

	public void setwM(int wM)
	{
		this.wM = wM;
	}

	private void stampaGiardino(Cellula v, int m[][])
	{
		GiardinoCellulare g = new GiardinoCellulare(v, this.wM, this.hM);
		for (int y = g.getgYT(); y <= g.getgYD(); y++)
		{
			for (int x = g.getgXS(); x <= g.getgXD(); x++)
			{
				System.out.print(m[x][y]);
			}
			System.out.println();
		}
	}

	public void start(Cellula[][] m)
	{
		for (int y = 0; y < this.hM; y++)
		{
			for (int x = 0; x < this.wM; x++)
			{
				this.calcolaStatoCellula(m[x][y]);
			}
		}
	}

	private void test()
	{
		int possibileCentroX = 26;
		int possibileCentroY = 49;
		Cellula v = new Cellula(possibileCentroX, possibileCentroY, Cellula.CELLULA_VIVA);
		System.out.println(v);
		Matrice.getInstance().inserisciCellula(v);
		this.drawCellula(v);
		// Matrice.getInstance().stampaMatrice();

		this.calcolaStatoCellula(v);
	}

	public void testOpacita()
	{
		Cellula c = new Cellula(20, 20, Cellula.CELLULA_VIVA);
		c.setOpacita(Cellula.NEL_PIENO_DELLA_VITA);
		c.setStatoIniziale(Cellula.CELLULA_MORTA);

		this.pa.delay(1000);
		c.setOpacita(255);
		this.drawCellula(c);

		this.pa.delay(1000);
		c.setOpacita(0);
		this.drawCellula(c);
	}
}
