package it.raffo.alberi;

import java.io.File;

import processing.core.PApplet;

public class TestFS extends PApplet
{

	static FileSystemStella fs;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.alberi.TestFS");

	}

	int h = 1000;
	int w = 1400;

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
			this.point(this.random(0, this.w), this.random(0, this.h));
		}
	}

	@Override
	public void settings()
	{
		this.size(this.w, this.h);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.generaPunti(200);
		fs = new FileSystemStella(this, this.h, this.w);
		fs.ls(new File("d:\\doganeprj"));
		long timeSinceStart = System.currentTimeMillis();
		fs.stampaAlbero();
		long timeSinceStop = System.currentTimeMillis();

		System.out.println("Time start                   : " + timeSinceStart + " (nanosecondi)");
		System.out.println("Time stop                    : " + timeSinceStop + " (nanosecondi)");
		System.out.println("Delta time                   : " + ((timeSinceStop - timeSinceStart)) + " (millisecondi)");
		System.out.println("Collisioni                   : " + Matrice.getInstance().getCollisioniGlobali());
		System.out.println("Directory con max figli      : " + fs.getNomeDirectoryConFigliMax());
		System.out.println("Numero figli                 : " + fs.getMaxFigli());
		System.out.println("Profondita fs                : " + fs.getProfonditaFs());

	}

}
