package it.raffo.ordinamento;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;

public class BubbleSort extends PApplet
{
	int						a;
	int						max;
	boolean					done			= false;
	static List<Integer>	lista			= new LinkedList<>();
	int						numeroElementi	= 30;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.ordinamento.BubbleSort");

	}

	public static void riempiListaDispari(int numeroElementi)
	{
		Random random = new Random();
		int cont = 1;
		int dispari;
		while (cont <= numeroElementi)
		{
			dispari = (random.nextInt(numeroElementi));
			if (!lista.contains(dispari))
			{
				lista.add(dispari);
				cont++;
			}
		}
	}

	public static void stampaLista()
	{
		lista.stream().forEach(e -> System.out.println(e));
	}

	public static void swap(int i, int j)
	{
		int temp = lista.get(i);
		lista.set(i, lista.get(j));
		lista.set(j, temp);
	}

	@Override
	public void draw()
	{
		this.delay(50);
		if (this.done)
		{
			return;
		}
		this.background(0);
		this.translate(1, 0);
		if (this.a >= this.max)
		{
			this.a = 0;
			this.max--;
		}

		if (lista.get(this.a) > (lista.get(this.a + 1)))
		{
			swap(this.a, this.a + 1);
		}
		this.a++;
		this.noStroke();// stroke(0);
		for (int i = 0; i < lista.size(); i++)
		{
			this.fill(196);
			if (i == this.a)
			{
				this.fill(255, 0, 0);
			}
			if (i > this.max)
			{
				this.fill(0, 255, 0);
			}
			this.rect(i * this.numeroElementi, this.height - (this.numeroElementi * lista.get(i)), this.numeroElementi - 2, (this.numeroElementi * lista.get(i)));
			this.fill(255, 255, 0);
			this.rect(i * this.numeroElementi, this.height - (this.numeroElementi * lista.get(i)), this.numeroElementi - 2, this.numeroElementi - 2);
		}
		if (this.max == -1)
		{
			this.done = true;
		}
	}

	@Override
	public void settings()
	{
		this.size((this.numeroElementi * this.numeroElementi) + 1, this.numeroElementi * this.numeroElementi);
	}

	@Override
	public void setup()
	{
		this.noStroke();
		riempiListaDispari(this.numeroElementi);
		this.max = lista.size() - 1;
		stampaLista();
	}

}
