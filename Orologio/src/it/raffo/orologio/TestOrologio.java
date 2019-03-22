package it.raffo.orologio;

import processing.core.PApplet;

public class TestOrologio extends PApplet
{
	public static void main(String[] args)
	{
		PApplet.main("it.raffo.orologio.TestOrologio");
	}

	int		h		= 1000;
	int		raggioX	= 300;
	int		raggioY	= 200;
	int		w		= 1000;
	float	x		= this.w / 2;
	float	y		= this.h / 2;
	float	xc;
	float	yc;
	float	secondsRadius;

	@Override
	public void draw()
	{

		float xs, ys, xm, ym, xh, yh;
		float s = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
		float m = map(minute() + norm(second(), 0, 60), 0, 60, 0, TWO_PI) - HALF_PI;
		float h = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;

		this.background(0);
		this.generaPunti(40);
		this.drawDisplay();
		xs = (this.raggioX - 50) * cos(s);
		ys = (this.raggioY - 50) * sin(s);
		xm = (this.raggioX - 30) * cos(m);
		ym = (this.raggioY - 30) * sin(m);
		xh = (this.raggioX - 15) * cos(h);
		yh = (this.raggioY - 15) * sin(h);
		this.translate(this.w / 2, this.h / 2);
		this.strokeWeight(1);
		this.stroke(0, 255, 0);
		this.line(0, 0, xs, ys);
		this.strokeWeight(2);
		this.stroke(255, 255, 0);
		this.line(0, 0, xm, ym);
		this.strokeWeight(3);
		this.stroke(0, 255, 255);
		this.line(0, 0, xh, yh);
		h = this.height;
	}

	private void drawDisplay()
	{

		this.pushMatrix();
		this.stroke(255);
		this.strokeWeight(3);

		this.translate(this.w / 2, this.h / 2);

		this.fill(0, 255, 0);
		for (int a = 0; a < 360; a++)
		{
			float angle = radians(a);
			System.out.println(angle);
			this.x = (this.raggioX - 50) * sin(angle);
			this.y = (this.raggioY - 50) * cos(angle);
			System.out.println(this.x + "," + this.y);
			this.point(this.x, this.y);

		}
		// disegno hh
		for (int i = 0; i < 12; i++)
		{
			float p = ((2 * PI) / 12) * i;
			this.xc = (this.raggioX - 30) * sin(p);
			this.yc = (this.raggioY - 30) * cos(p);
			this.x = this.raggioX * sin(p);
			this.y = this.raggioY * cos(p);
			this.line(this.xc, this.yc, this.x, this.y);
		}

		// disegno hh
		this.strokeWeight(1);
		for (int i = 0; i < 60; i++)
		{
			float p = ((2 * PI) / 60) * i;
			this.xc = (this.raggioX - 10) * sin(p);
			this.yc = (this.raggioY - 10) * cos(p);
			this.x = this.raggioX * sin(p);
			this.y = this.raggioY * cos(p);
			this.line(this.xc, this.yc, this.x, this.y);
		}
		this.fill(255, 0, 0);
		this.ellipse(0, 0, 20, 20);

		this.popMatrix();

	}

	public void generaPunti(int i)
	{
		for (int x = 0; x < i; x++)
		{
			this.stroke(this.random(255), this.random(255), this.random(255));
			this.strokeWeight(this.random(0, 5));
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

	}
}
