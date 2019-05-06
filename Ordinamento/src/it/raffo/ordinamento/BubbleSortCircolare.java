package it.raffo.ordinamento;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import processing.core.PApplet;

public class BubbleSortCircolare extends PApplet
{
	private static final Logger	log			= Logger.getLogger(BubbleSortCircolare.class);
	static List<Integer>		lista		= new LinkedList<>();
	static int					n			= 30;
	static int					LARGHEZZA	= 800;
	static int					ALTEZZA		= 400;

	public static void main(String[] args)
	{
		PropertyConfigurator.configure("log4j.properties");
		PApplet.main("it.raffo.ordinamento.BubbleSortCircolare");
		riempiListaDispari(n);
		stampaLista();
	}

	public static void riempiListaDispari(int numeroElementi)
	{
		Random random = new Random();
		int cont = 1;
		int dispari;
		while (cont <= numeroElementi)
		{
			dispari = (random.nextInt(numeroElementi) * 2) + 1;
			if (!lista.contains(dispari))
			{
				lista.add(dispari);
				cont++;
			}
		}
	}

	public static void swap(int i, int j)
	{
		int temp = lista.get(i);
		lista.set(i, lista.get(j));
		lista.set(j, temp);
	}

	public static void stampaLista()
	{
		log.info("Valori in lista: ");
		lista.stream().forEach(e -> log.info(e));
	}

	public void drawLista()
	{
		for (int i = 0; i < lista.size(); i++)
		{
			this.drawNumero(50 + (i * ((LARGHEZZA - 100) / lista.size())), 200);
		}
	}

	public void drawNumero(float x, float y)
	{
		this.pushMatrix();

		this.noFill();
		this.stroke(255);
		this.ellipse(x, y, 50, 50);

		this.popMatrix();
	}

	float	x1;
	float	x2;
	float	y1;
	float	y2;
	float	m	= 0;

	@Override
	public void draw()
	{
		this.translate(400, 200);

		if (this.m <= 180)
		{
			this.background(0);

			float angle = map(this.m, 0, 360, 0, TWO_PI) - HALF_PI;
			this.background(0);
			// float angle = radians(s);
			this.x1 = (50 * sin(angle));
			this.y1 = (50 * cos(angle));
			this.x2 = (50 * -sin(angle));
			this.y2 = (50 * -cos(angle));
			this.drawNumero(this.x1, this.y1);
			this.drawNumero(this.x2, this.y2);
			this.m = this.m + 3;
			// this.delay(300);
			log.info(this.m);
		}

	}

	private void ruotaNodo()
	{

	}

	@Override
	public void settings()
	{
		this.size(LARGHEZZA, ALTEZZA);
	}

	@Override
	public void setup()
	{
		this.background(0);
		// this.drawLista();
	}

}
