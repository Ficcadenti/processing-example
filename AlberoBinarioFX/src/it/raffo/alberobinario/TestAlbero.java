package it.raffo.alberobinario;

import processing.core.PApplet;

public class TestAlbero extends PApplet
{
	private static final int	ITERATIVO	= 1;
	private static final int	RICORSIVO	= 2;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.alberobinario.TestAlbero");
	}

	@Override
	public void draw()
	{
	}

	@Override
	public void settings()
	{
		this.size(1000, 600);
	}

	@Override
	public void setup()
	{
		this.background(0);

		Scheda sc1 = new Scheda("Raffaele", "Ficcadenti", 42);
		Scheda sc2 = new Scheda("Edo", "Galizia", 50);
		Scheda sc3 = new Scheda("Yuri", "Quaglia", 31);
		Scheda sc4 = new Scheda("Samuele", "Parentato", 18);
		Scheda sc5 = new Scheda("Alberto", "Ruggeri", 28);
		Scheda sc6 = new Scheda("Francesca", "Maffia", 16);
		Scheda sc7 = new Scheda("Enzo", "Cianfarani", 70);
		Scheda sc8 = new Scheda("Pluto", "Pippo", 23);

		AlberoBinario albero = new AlberoBinario(RICORSIVO, this);

		albero.inserisciValore(sc1);
		albero.inserisciValore(sc2);
		albero.inserisciValore(sc3);
		albero.inserisciValore(sc4);
		albero.inserisciValore(sc5);
		albero.inserisciValore(sc6);
		albero.inserisciValore(sc7);
		albero.inserisciValore(sc8);

		albero.visitaPreOrder();

	}

	public void test()
	{
		this.smooth();
		this.translate(this.width / 2, this.height / 2);
		this.strokeWeight(2);
		this.beginShape();
		for (int i = 0; i < 350; i++)
		{
			this.curveVertex((i * 2) * sin((float) (i / 5.0)), (i * 2) * cos((float) (i / 5.0)));
		}
		this.endShape();
	}

	public void test1()
	{

	}

}
