package it.raffo.alberi;

import java.io.File;

import processing.core.PApplet;

public class FileSystem
{
	private int		distanzaMinima;
	private int		h;
	private PApplet	pa;
	private int		raggio;
	private Nodo	root;
	private int		w;
	private int		max;
	private String	nomeMax;

	public FileSystem(PApplet pa, int w, int h)
	{
		super();
		this.pa = pa;
		this.root = null;
		this.w = w;
		this.h = h;
		this.raggio = 5;
		this.distanzaMinima = 0;
		this.max = 0;
		this.nomeMax = "----";
		Matrice.getInstance().setW(this.w);
		Matrice.getInstance().setH(this.h);
		Matrice.getInstance().setRaggio(this.raggio);
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

	public int getMax()
	{
		return this.max;
	}

	public String getNomeMax()
	{
		return this.nomeMax;
	}

	public PApplet getPa()
	{
		return this.pa;
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
			}
			else
			{
				type = "F";
				size = file.length();
			}

			this.root = new Nodo(new Elemento(file.getName(), type, size, file));
			this.root.setC(Matrice.getInstance().calcolaCentro(this.raggio));
			this.ls(this.root);
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

	public void setMax(int max)
	{
		this.max = max;
	}

	public void setNomeMax(String nomeMax)
	{
		this.nomeMax = nomeMax;
	}

	public void setPa(PApplet pa)
	{
		this.pa = pa;
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
				if (n.getNumeroFigli() > this.max)
				{
					this.max = n.getNumeroFigli();
					this.nomeMax = n.getElem().getFile().getAbsolutePath();
				}
				this.drawArco(nodoCorrente, n);
				nodoCorrente.getC().getX();
				this.stampaTxtFx(n);

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
		this.pa.noFill();
		this.pa.stroke(255);
		this.pa.ellipse(n.getC().getX(), n.getC().getY(), this.raggio * 2, this.raggio * 2);
		this.pa.fill(this.pa.random(255), this.pa.random(255), this.pa.random(255));
		this.pa.ellipse(n.getC().getX(), n.getC().getY(), (this.raggio * 2) - 6, (this.raggio * 2) - 6);
		this.pa.fill(this.pa.random(255), this.pa.random(255), this.pa.random(255));
		this.pa.color(this.pa.random(255), this.pa.random(255), this.pa.random(255));
		this.pa.text(str, n.getC().getX(), n.getC().getY() - ((this.raggio) + 5));
		this.pa.popMatrix();
	}

}
