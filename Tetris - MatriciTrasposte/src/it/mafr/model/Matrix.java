package it.mafr.model;

import org.apache.log4j.Logger;

import it.mafr.settings.Settings;
import it.mafr.utility.Rotazioni;

public class Matrix implements OperazioniMatrice
{
	public static Matrix	instance			= null;
	public int				larghezza			= Settings.L.WIDTH;
	public int				altezza				= Settings.L.HEIGHT;
	private int[][]			mattoncinoMatrix	= new int[larghezza][altezza];
	private String			nomeMatrice			= "Iniziale";
	private final Logger	log					= Logger.getLogger(Matrix.class);

	public void ruota(Rotazioni rotazione)
	{
		this.trasposta();
		if (rotazione == Rotazioni.DX)
		{
			this.inversioneColonne();
		}
		else if (rotazione == Rotazioni.SX)
		{
			this.inversioneRighe();
		}
	}

	@Override
	public void trasposta()
	{
		int[][] traspoMatrice = new int[altezza][larghezza];
		int scambio;

		for (int y = 0; y < altezza; y++)
		{
			for (int x = 0; x < larghezza; x++)
			{
				traspoMatrice[y][x] = mattoncinoMatrix[x][y];

			}
		}
		mattoncinoMatrix = traspoMatrice;
		scambio = larghezza;
		larghezza = altezza;
		altezza = scambio;
		nomeMatrice = "Trasposta";
	}

	@Override
	public void inversioneColonne()
	{
		int temp = 0;
		for (int y = 0; y < altezza; y++)
		{
			for (int x = 0; x < larghezza / 2; x++)
			{
				temp = mattoncinoMatrix[x][y];
				mattoncinoMatrix[x][y] = mattoncinoMatrix[(larghezza - 1) - x][y];
				mattoncinoMatrix[(larghezza - 1) - x][y] = temp;

			}
		}
		nomeMatrice = "Inversione Colonne";
	}

	@Override
	public void inversioneRighe()
	{
		int temp = 0;
		for (int y = 0; y < altezza / 2; y++)
		{
			for (int x = 0; x < larghezza; x++)
			{
				temp = mattoncinoMatrix[x][y];
				mattoncinoMatrix[x][y] = mattoncinoMatrix[x][(altezza - 1) - y];
				mattoncinoMatrix[x][(altezza - 1) - y] = temp;

			}
		}
		nomeMatrice = "Inversione Righe";
	}

	@Override
	public void riflessa()
	{
		int[][] rifleMatrice = new int[larghezza][altezza];

		for (int y = 0; y < altezza; y++)

		{
			for (int x = 0; x < larghezza; x++)
			{
				rifleMatrice[x][y] = mattoncinoMatrix[(larghezza - 1) - x][y];
			}
		}
		mattoncinoMatrix = rifleMatrice;
		nomeMatrice = "Riflessa";
	}

	@Override
	public void stampaMatrice()
	{
		log.info("*** Matrice " + nomeMatrice + ": M " + larghezza + "x" + altezza);
		for (int y = 0; y < altezza; y++)
		{
			for (int x = 0; x < larghezza; x++)
			{
				System.out.print(mattoncinoMatrix[x][y]);
			}
			System.out.println("");
		}

	}

	public int[][] getMattoncinoMatrix()
	{
		return mattoncinoMatrix;
	}

	public void setMattoncinoMatrix(int[][] matriceGenerica)
	{
		this.larghezza = matriceGenerica.length;
		this.altezza = matriceGenerica[0].length;
		mattoncinoMatrix = matriceGenerica;
	}

	public static Matrix getIstance()
	{
		if (instance == null)
		{
			return instance = new Matrix();
		}
		else
		{
			return instance;
		}
	}

}
