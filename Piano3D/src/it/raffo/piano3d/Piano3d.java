package it.raffo.piano3d;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Piano3d extends PApplet
{

	static final float NUMRO_AUREO = 1.618f;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.piano3d.Piano3d");
	}

	PShape	globe;
	PImage	img;
	float	t		= 0, o = 0, w, h;
	float	theta	= 0, omega = 0;

	@Override
	public void draw()
	{
		// this.noStroke();
		// this.lights();
		// this.translate(100, 100, 50);
		// this.sphere(28);
		// this.translate(200, 200, -350);
		// this.sphere(28);
		// this.rotate(PI);

		this.background(0);
		this.stroke(150);
		this.noFill();

		this.lights();
		this.translate(100, 100, 50);
		this.sphere(28);
		this.translate(200, 200, -350);
		this.sphere(28);

		// box(150); //Enable/disable these one by one

		this.beginCamera();
		this.camera();
		this.translate(this.width / 2, this.height / 2);
		this.endCamera();

		this.beginCamera();
		// this.rotateX(radians(this.mouseX));
		this.rotateY(radians(this.mouseX));
		this.endCamera();

	}

	@Override
	public void settings()
	{
		this.size(1000, 1000, P3D);
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.img = this.loadImage("earth.jpg");
		this.globe = this.createShape(SPHERE, 150);
		this.globe.setTexture(this.img);
		this.h = this.height;
		this.w = this.width;
	}
}
