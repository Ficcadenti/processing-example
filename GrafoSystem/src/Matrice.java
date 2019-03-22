import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Matrice
{

	private static int		w				= 1200;
	private static int		h				= 1200;
	private static Matrice	matrice_istanza	= null;
	private static int[][]	matrice			= new int[w][h];
	private static int		diametro		= SystemGraphProcessing.diametro;

	public static Matrice getIstance()
	{
		if (matrice_istanza == null)
		{
			matrice_istanza = new Matrice();
		}
		return matrice_istanza;
	}

	public static int[][] getMatrice()
	{
		return matrice;
	}

	public static void resettaMatrice()
	{
		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				matrice[x][y] = 0;
			}
		}
	}

	private static void setMatrice(int[][] matrice)
	{
		Matrice.matrice = matrice;
	}

	private Matrice()
	{
		for (int x = 1; x < w; x++)
		{
			for (int y = 1; y < h; y++)
			{
				matrice[x][y] = 0;
			}
		}
	}

	public boolean controllaSeSicuro(Centro c)
	{
		boolean status = true;
		if (c.x < diametro)
		{
			status = false;
		}
		if (c.x > (w - diametro))
		{
			status = false;
		}
		if (c.y < diametro)
		{
			status = false;
		}
		if (c.y > (h - diametro))
		{
			status = false;
		}
		if (status)
		{
			float partenzaCortileX = c.x - (diametro / 2);
			float arrivoCortileX = c.x + (diametro / 2);
			float partenzaCortileY = c.y - (diametro / 2);
			float arrivoCortileY = c.y + (diametro / 2);
			giardino: for (int x = (int) partenzaCortileX; x < arrivoCortileX; x++)
			{
				for (int y = (int) partenzaCortileY; y < arrivoCortileY; y++)
				{
					if (matrice[x][y] == 1)
					{
						status = false;
						break giardino;
					}
				}
			}
		}
		return status;
	}

	public Centro generaCentro()
	{
		int ipotesiX = (int) ((Math.random() * w) - 1);
		int ipotesiY = (int) ((Math.random() * h) - 1);
		Centro centroIpotetico = new Centro(ipotesiX, ipotesiY);
		if (this.controllaSeSicuro(centroIpotetico))
		{
			this.scriviNodoMatrice(centroIpotetico);
		}
		else
		{
			this.generaCentro();
		}
		return centroIpotetico;
	}

	public void scriviNodoMatrice(Centro c)
	{
		float partenzaCortileX = c.x - (diametro / 2);
		System.out.println("partenzaCortileX ----->" + partenzaCortileX);
		float arrivoCortileX = c.x + (diametro / 2);
		System.out.println("arrivoCortileX ----->" + arrivoCortileX);
		float partenzaCortileY = c.y - (diametro / 2);
		System.out.println("partenzaCortileY ----->" + partenzaCortileY);
		float arrivoCortileY = c.y + (diametro / 2);
		System.out.println("arrivoCortileY ----->" + arrivoCortileY);
		System.out.println("____________");

		for (int x = (int) partenzaCortileX; x < arrivoCortileX; x++)
		{
			for (int y = (int) partenzaCortileY; y < arrivoCortileY; y++)
			{
				matrice[x][y] = 1;
			}
		}
	}

	public void stampaMatrice()
	{
		PrintWriter out;
		try
		{
			out = new PrintWriter(new BufferedWriter(new FileWriter("d:\\mioFile.txt")));
			for (int y = 0; y < h; y++)
			{
				// System.out.println("");
				for (int x = 0; x < w; x++)
				{
					// System.out.print(matrice[x][y]);
					out.print(matrice[x][y]);
				}
				out.println();
			}
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
