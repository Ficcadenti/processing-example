package it.raffo.alberi;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import processing.core.PApplet;

public class FileSystem
{

	private int     distanzaMinima;
	private int     h;
	private PApplet pa;
	private int     raggioDirectory;
	private int     raggioFile;
	private int     raggio;
	private Nodo    root;
	private int     w;
	private int     maxFigli;
	private String  nomeDirectoryConFigliMax;
	private int     profonditaFs;

	public FileSystem(PApplet pa, int w, int h) {
		super();
		this.pa = pa;
		this.root = null;
		this.w = w;
		this.h = h;
		this.raggioDirectory = 20;
		this.raggioFile = 5;
		this.raggio = 5;
		this.distanzaMinima = 0;
		this.maxFigli = 0;
		this.profonditaFs = 0;
		this.nomeDirectoryConFigliMax = "----";
		Matrice.getInstance().setW(this.w);
		Matrice.getInstance().setH(this.h);
		Matrice.getInstance().setRaggio(this.raggioDirectory);
		Matrice.getInstance().setDistanzaMinima(this.distanzaMinima);
		Matrice.getInstance().azzeraMatrice();
		Terra.getInstance().setW(1900);
		Terra.getInstance().setH(1600);

	}

	private void drawArco(Nodo nFrom, Nodo nTo)
	{
		this.pa.pushMatrix();
		this.pa.noFill();
		this.pa.stroke(255, 255, 255);
		this.pa.line(nFrom.getC().getX(), nFrom.getC().getY(), nTo.getC().getX(), nTo.getC().getY());
		this.pa.popMatrix();

	}

	void drawArrow(int cx, int cy, int len, float angle)
	{
		this.pa.pushMatrix();
		this.pa.translate(cx, cy);
		this.pa.line(0, 0, len, 0);
		this.pa.line(len, 0, len - 8, -8);
		this.pa.line(len, 0, len - 8, 8);
		this.pa.popMatrix();
	}

	public int getH()
	{
		return this.h;
	}

	public int getMaxFigli()
	{
		return this.maxFigli;
	}

	public String getNomeDirectoryConFigliMax()
	{
		return this.nomeDirectoryConFigliMax;
	}

	public PApplet getPa()
	{
		return this.pa;
	}

	public int getProfonditaFs()
	{
		return this.profonditaFs;
	}

	public int getRaggioFile()
	{
		return this.raggioFile;
	}

	public Nodo getRoot()
	{
		return this.root;
	}

	public int getW()
	{
		return this.w;
	}

	public void ls(File file)
	{
		if (this.root == null)
		{
			String type;
			long size = 0;
			if (file.isDirectory())
			{
				type = "D";
			} else
			{
				type = "F";
				size = file.length();
			}

			this.setProfonditaFs(1);
			this.root = new Nodo(new Elemento(file.getName(), type, size, file));
			Centro c = new Centro(this.w / 2, this.h / 2);
			// this.root.setC(Matrice.getInstance().calcolaCentro(this.raggio)); casuale
			this.root.setC(c);
			this.ls(this.root);
			if (this.profonditaFs > this.splitPath(file.getPath()).size())
			{
				this.profonditaFs = this.profonditaFs - (this.splitPath(file.getPath()).size());
			}
		}
	}

	private void ls(Nodo nodoCorrente)
	{
		File[] filesList = nodoCorrente.getElem().getFile().listFiles();
		nodoCorrente.setNumeroFigli(filesList.length);

		for (File f : filesList)
		{

			if (f.isDirectory() && !f.isHidden())
			{
				if (this.splitPath(f.getPath()).size() > this.profonditaFs)
				{
					this.profonditaFs = this.splitPath(f.getPath()).size();
				}

				Nodo n = new Nodo(new Elemento(f.getName(), "D", f.length(), f));
				n.setC(Matrice.getInstance().calcolaCentro(this.raggio));
				nodoCorrente.addFiglio(n);
				this.ls(n);
			}
			if (f.isFile())
			{
				Nodo n = new Nodo(new Elemento(f.getName(), "F", f.length(), f));
				n.setC(Matrice.getInstance().calcolaCentro(this.raggio));
				nodoCorrente.addFiglio(n);
			}
		}
	}

	public void setH(int h)
	{
		this.h = h;
	}

	public void setMaxFigli(int max)
	{
		this.maxFigli = max;
	}

	public void setNomeDirectoryConFigliMax(String nomeMax)
	{
		this.nomeDirectoryConFigliMax = nomeMax;
	}

	public void setPa(PApplet pa)
	{
		this.pa = pa;
	}

	public void setProfonditaFs(int profonditaFs)
	{
		this.profonditaFs = profonditaFs;
	}

	public void setRaggioFile(int raggioFile)
	{
		this.raggioFile = raggioFile;
	}

	public void setRoot(Nodo root)
	{
		this.root = root;
	}

	public void setW(int w)
	{
		this.w = w;
	}

	public String space(int n)
	{
		String str = "";
		for (int i = 0; i < n; i++)
		{
			str = str + " ";
		}
		return str;
	}

	public List<String> splitPath(String pathString)
	{
		Path path = Paths.get(pathString);
		return Arrays.asList(StreamSupport.stream(path.spliterator(), false).map(Path::toString)
		        .toArray(String[]::new));
	}

	public void stampaAlbero()
	{
		if (this.root != null)
		{
			String str = this.root.getElem().getNome() + " (" + this.root.getElem().getType() + ")";
			System.out.println(" directory di partenza: " + str);
			this.stampaTxtFx(this.root);
			this.stampaAlbero(this.root);
		}

	}

	public void stampaAlbero(Nodo nodoCorrente)
	{

		if (nodoCorrente.getFigli() != null)
		{

			for (Nodo n : nodoCorrente.getFigli())
			{
				if (n.getNumeroFigli() > this.maxFigli)
				{
					this.maxFigli = n.getNumeroFigli();
					this.nomeDirectoryConFigliMax = n.getElem().getFile().getAbsolutePath();
				}
				this.drawArco(nodoCorrente, n);
				nodoCorrente.getC().getX();
				this.stampaTxtFx(n);
				System.out.println(n);

				if (n.getElem().getType().equalsIgnoreCase("D"))
				{
					this.stampaAlbero(n);
				}
			}
		}
	}

	public void stampaTxtFx(Nodo n)
	{
		this.pa.pushMatrix();
		String str = n.getElem().getNome() + " (" + n.getElem().getType() + ")";

		if (n.getElem().getType().equalsIgnoreCase("D")) // directory
		{
			this.pa.noFill();
			this.pa.stroke(255);
			this.pa.ellipse(n.getC().getX(), n.getC().getY(), this.raggioDirectory * 2, this.raggioDirectory * 2);
			this.pa.fill(this.pa.random(255), this.pa.random(255), this.pa.random(255));
			this.pa.ellipse(n.getC().getX(), n.getC().getY(), (this.raggioDirectory * 2) - 6, (this.raggioDirectory * 2) - 6);
		} else // file
		{
			this.pa.fill(this.pa.random(255), this.pa.random(255), this.pa.random(255));
			this.pa.ellipse(n.getC().getX(), n.getC().getY(), (this.raggioFile * 2), (this.raggioFile * 2));
		}
		this.pa.fill(this.pa.random(255), this.pa.random(255), this.pa.random(255));
		this.pa.color(this.pa.random(255), this.pa.random(255), this.pa.random(255));
		this.pa.text(str, n.getC().getX(), n.getC().getY() - ((this.raggioDirectory) + 5));
		this.pa.popMatrix();
	}

}
