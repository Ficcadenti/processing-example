package it.mafr.albero;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.PFont;

public class TestAlbero extends PApplet
{

	public static void main(String[] args)
	{
		PApplet.main("it.mafr.albero.TestAlbero");
	}

	Nodo				root;
	int					spostamentoVerticale	= 100;
	int					diametro				= 50;
	PFont				font;
	ArrayList<Integer>	sequenzaFibo			= new ArrayList();

	public void disegnaLinea(Nodo nodoCorrente, Nodo figlio)
	{
		this.line(nodoCorrente.posX, nodoCorrente.posY, figlio.posX, figlio.posY);
	}

	public void disegnaNodo(Nodo nodo)
	{
		this.noFill();
		this.stroke(255);
		this.ellipse(nodo.posX, nodo.posY, this.diametro, this.diametro);
		this.fill(this.random(255), this.random(255), this.random(255));
		this.ellipse(nodo.posX, nodo.posY, this.diametro - 10, this.diametro - 10);
		this.fill(255);
		this.textAlign(CENTER);
		this.textSize(10);
		this.font = this.loadFont("AlegreyaSans-Thin-20.vlw");
		this.textFont(this.font);
		this.text(nodo.valore, nodo.posX, nodo.posY - 40);
		this.ellipse(nodo.posX, nodo.posY, 10, 10);
	}

	@Override
	public void draw()
	{

	}

	public void generaNodiFibo()
	{
		Collections.shuffle(this.sequenzaFibo);
		for (Integer elemento : this.sequenzaFibo)
		{
			this.inserisciNodo(elemento);
		}
	}

	public void generaPunti(int i)
	{
		for (int x = 0; x < i; x++)
		{
			this.stroke(255);
			this.strokeWeight(this.random(0, 2));
			this.point(this.random(0, this.width), this.random(0, this.height));
		}
	}

	public int generaSequenzaFibo(int cicli)
	{
		if (cicli == 0)
		{
			return 0;
		}
		else if (cicli == 1)
		{
			return 1;
		}
		return this.generaSequenzaFibo(cicli - 1) + this.generaSequenzaFibo(cicli - 2);
	}

	public void inserisciNodo(int valore)
	{
		if (this.root == null)
		{
			this.root = new Nodo(valore);
			this.root.setPosX(this.width / 2);
			this.root.setPosY(100);
			this.disegnaNodo(this.root);
		}
		else
		{
			this.insert(valore, this.root, 2);
		}
	}

	public void insert(int valore, Nodo nodoCorrente, int i)
	{
		Nodo figlio = new Nodo(valore);
		if (figlio.valore < nodoCorrente.valore)
		{
			if (nodoCorrente.getFiglioSinistro() == null)
			{
				nodoCorrente.setFiglioSinistro(figlio);
				figlio.setPosX(nodoCorrente.posX - ((this.width / 2) / i));
				figlio.setPosY(nodoCorrente.posY + this.spostamentoVerticale);
				this.disegnaNodo(figlio);
				this.disegnaLinea(nodoCorrente, figlio);
			}
			else
			{
				this.insert(valore, nodoCorrente.getFiglioSinistro(), i + 2);
			}
		}
		else if (figlio.valore > nodoCorrente.valore)
		{
			if (nodoCorrente.getFiglioDestro() == null)
			{
				nodoCorrente.setFiglioDestro(figlio);
				figlio.setPosX(nodoCorrente.posX + ((this.width / 2) / i));
				figlio.setPosY(nodoCorrente.posY + this.spostamentoVerticale);
				this.disegnaNodo(figlio);
				this.disegnaLinea(nodoCorrente, figlio);
			}
			else
			{
				this.insert(valore, nodoCorrente.getFiglioDestro(), i + 2);
			}
		}
	}

	public ArrayList<Integer> riempiListaFibo(int cicli)
	{
		for (int x = 2; x < cicli; x++)
		{
			this.sequenzaFibo.add(this.generaSequenzaFibo(x));
		}
		return this.sequenzaFibo;
	}

	@Override
	public void settings()
	{
		this.size(1280, 1280);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.sequenzaFibo = this.riempiListaFibo(19);
		this.generaPunti(200);
		this.generaNodiFibo();
	}

}
