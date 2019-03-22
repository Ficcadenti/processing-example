import java.io.File;

import processing.core.PApplet;

public class SystemGraphProcessing extends PApplet
{

	public static int	diametro	= 20;
	public static int	m[][];

	public static void main(String[] args)
	{
		PApplet.main("SystemGraphProcessing");
		Matrice.getIstance().stampaMatrice();

	}

	private String	path		= "D:\\TAGGATI";

	private File	rootFile	= new File(this.path);
	private Nodo	cartellaPartenza;

	public void disegnaDirectory(Nodo nodo)
	{
		this.noFill();
		this.stroke(255);
		this.ellipse(nodo.centro.x, nodo.centro.y, diametro, diametro);
		this.fill(255, 0, 0);
		this.textAlign(CENTER);
		this.textSize(13);
		this.text("Cartella" + nodo.nome, nodo.centro.x, nodo.centro.y - 40);
	}

	void disegnaFigli(File[] listaFigli, Nodo cartellaPartenza)
	{
		for (int x = 0; x < listaFigli.length; x++)
		{
			Nodo cartellaFiglio = new Nodo();
			cartellaFiglio.setNome(listaFigli[x].getName());
			cartellaFiglio.setFigli(listaFigli[x].listFiles());
			cartellaFiglio.setCentro(Matrice.getIstance().generaCentro());
			Matrice.getIstance().stampaMatrice();
			this.disegnaLinea(cartellaPartenza, cartellaFiglio);
			if (listaFigli[x].isDirectory())
			{
				this.disegnaDirectory(cartellaFiglio);
				this.disegnaLinea(cartellaPartenza, cartellaFiglio);
				this.disegnaFigli(cartellaFiglio.figli, cartellaFiglio);
			}
			else
			{
				this.disegnaFile(cartellaFiglio);
			}
		}
	}

	public void disegnaFile(Nodo nodo)
	{
		this.noFill();
		this.stroke(255, 0, 0);
		this.ellipse(nodo.centro.x, nodo.centro.y, diametro, diametro);
		this.fill(0);
		this.ellipse(nodo.centro.x, nodo.centro.y, diametro - 10, diametro - 10);
		this.fill(255, 0, 0);
		this.textAlign(CENTER);
		this.textSize(13);
		this.text("File" + nodo.nome, nodo.centro.x, nodo.centro.y - 40);
		this.ellipse(nodo.centro.x, nodo.centro.y, 10, 10);
	}

	public void disegnaLinea(Nodo cartellaPadre, Nodo cartellaFiglio)
	{
		this.stroke(255);
		this.line(cartellaPadre.centro.x, cartellaPadre.centro.y, cartellaFiglio.centro.x, cartellaFiglio.centro.y);
	}

	public void disegnaMatrice()
	{
		int[][] m = Matrice.getIstance().getMatrice();
		for (int y = 0; y < 1200; y++)
		{
			for (int x = 0; x < 1200; x++)
			{
				if (m[x][y] == 1)
				{
					this.stroke(255, 255, 0);

				}
				else
				{
					this.stroke(0, 255, 0);
				}
				this.point(x, y);
			}
		}
	}

	public void disegnaRoot(Nodo nodo)
	{
		this.noFill();
		this.stroke(255);
		this.ellipse(nodo.centro.x, nodo.centro.y, diametro + 20, diametro + 20);
		this.fill(255, 0, 0);
		this.ellipse(nodo.centro.x, nodo.centro.y, diametro - 10, diametro - 10);
		this.fill(255, 0, 0);
		this.textAlign(CENTER);
		this.textSize(13);
		this.text("ROOT " + nodo.nome, nodo.centro.x, nodo.centro.y - 40);
	}

	@Override
	public void draw()
	{
	}

	public void leggiCartella(File filePartenza)
	{
		this.cartellaPartenza = new Nodo();
		this.cartellaPartenza.setNome(this.rootFile.getName());
		File[] listaFigli = filePartenza.listFiles();
		this.cartellaPartenza.setFigli(listaFigli);
		Centro CentroRoot = new Centro(this.random(this.width), this.random(this.height));
		this.cartellaPartenza.setCentro(CentroRoot);
		this.disegnaRoot(this.cartellaPartenza);
		this.disegnaFigli(listaFigli, this.cartellaPartenza);
	}

	@Override
	public void settings()
	{
		this.size(1200, 1200);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.leggiCartella(this.rootFile);
		this.save("d:\\francesca.jpg");
		this.background(0);

		this.disegnaMatrice();
		this.save("d:\\giardino.jpg");

	}
}
