package it.raffo.giocodellavita;

import processing.core.PApplet;

public class TestP3 extends PApplet
{
	public static void main(String[] args)
	{
		PApplet.main("it.raffo.giocodellavita.TestP3");
	}

	private int opacita = 255;

	@Override
	public void draw()
	{
		// this.background(0);
		this.pushMatrix();
		this.fill(0);
		this.rect(10, 10, 200, 200);
		this.fill(255, 255, 0, this.opacita -= 20);
		this.rect(10, 10, 200, 200);
		this.popMatrix();

	}

	@Override
	public void settings()
	{
		this.size(600, 600);
	}

	@Override
	public void setup()
	{
		this.background(0);

	}
}
