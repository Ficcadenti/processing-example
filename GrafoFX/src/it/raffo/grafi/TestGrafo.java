package it.raffo.grafi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import processing.core.PApplet;

public class TestGrafo extends PApplet
{
	public static int					distanzaMinima	= 2;
	public static int					h				= 1000;
	public static int					numeriFibonacci	= 20;
	public static int					raggio			= 30;
	public static ArrayList<Integer>	sequenzaFibo	= new ArrayList();
	public static int					w				= 1000;

	public static ArrayList<Integer> cloneList(ArrayList<Integer> list)
	{
		ArrayList<Integer> clone = new ArrayList<Integer>(list.size());
		for (Integer item : list)
		{
			clone.add(item);
		}
		return clone;
	}

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.grafi.TestGrafo");
	}

	@Override
	public void draw()
	{
	}

	public void generaPunti(int i)
	{
		for (int x = 0; x < i; x++)
		{
			this.stroke(255);
			this.strokeWeight(this.random(0, 2));
			this.point(this.random(0, w), this.random(0, h));
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

	public ArrayList<Integer> riempiListaFibo(int cicli)
	{
		for (int x = 2; x < cicli; x++)
		{
			sequenzaFibo.add(this.generaSequenzaFibo(x));
		}
		return sequenzaFibo;
	}

	@Override
	public void settings()
	{
		this.size(w, h);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.generaPunti(200);
		sequenzaFibo = this.riempiListaFibo(numeriFibonacci);
		Collections.shuffle(sequenzaFibo);

		System.out.println("Test Grafo - 15-03/2019 - Raffo - raffaele.ficcadenti@gmail.com");
		Grafo g = new Grafo(this);
		g.setRaggio(raggio);
		Matrice.getInstance().setW(w);
		Matrice.getInstance().setH(h);
		Matrice.getInstance().setRaggio(raggio);
		Matrice.getInstance().setDistanzaMinima(distanzaMinima);
		Matrice.getInstance().azzeraMatrice();

		Integer peso = new Integer(1); // peso arco

		Iterator<Integer> iter = sequenzaFibo.iterator();
		Integer fiboFrom = null;
		Integer fiboTo = null;
		Integer fibo = null;
		Nodo nodoFrom = null;
		Nodo nodoTo = null;
		Nodo nodo = null;

		if (iter.hasNext())
		{
			fiboFrom = iter.next();
			nodoFrom = new Nodo(Integer.toString(fiboFrom));
		}
		while (iter.hasNext())
		{
			fiboTo = iter.next();
			nodoTo = new Nodo(Integer.toString(fiboTo));
			g.add(nodoFrom, nodoTo, 1);
			nodoFrom = nodoTo;
		}

		Matrice.getInstance().stampaMatrice();
		// Nodo A = new Nodo("A");
		// Nodo B = new Nodo("B");
		// Nodo C = new Nodo("C");
		// Nodo D = new Nodo("D");
		// Nodo E = new Nodo("E");
		// Nodo F = new Nodo("F");
		// Nodo G = new Nodo("G");
		// Nodo H = new Nodo("H");
		//
		// g.add(A, B, peso);
		// g.add(A, C, peso);
		// g.add(H, E, peso);
		// g.add(C, D, peso);
		// g.add(C, E, peso);
		// g.add(B, D, peso);
		// g.add(E, D, peso);
		// g.add(H, G, peso);
		// g.add(F, A, peso);
		// g.add(G, C, peso);

		System.out.println("Il grafo G e':\n" + g);
		g.drawGrafo();
		System.out.println("L'insieme di archi e': " + g.getInsiemiArchi());
		System.out.println("END");

	}

}
