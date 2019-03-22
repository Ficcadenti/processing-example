package it.raffo.spirale;

import processing.core.PApplet;
import processing.core.PFont;

public class Spirale extends PApplet
{

	static final float NUMRO_AUREO = 1.618f;

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.spirale.Spirale");
	}

	PFont	font;
	float	t		= 0, w, h;
	float	theta	= 0;
	float	verso	= +1;
	float	a		= 30;
	float	k		= 0;

	@Override
	public void draw()
	{
		this.textFont(this.font);
		this.textSize(15);
		this.fill(0);
		this.noStroke();
		this.rect(0, 0, 300, 200);
		this.fill(0, 255, 0);

		this.text("a     : " + this.a, 100, 60);
		this.text("k     : " + this.k, 100, 80);
		this.text("Theta : " + this.theta, 100, 100);

		// posizionati al centro
		this.translate(this.w / 2, this.h / 2);

		// angolo polare ruotato
		this.theta = radians(this.t);

		// ruota di theta
		this.rotate(this.theta);

		// la coloro
		this.stroke(0, 255, 0);
		// la disegno

		this.point(this.equazioneLibera(this.theta), 0);
		// this.point(-this.spiraleAurea(this.theta), 0);
		// incremento l'angolo
		this.t += 0.15;
	}

	public float equazioneLibera(float theta)
	{
		// r=a*(1+cos(theta));
		//
		// r=a*theta;
		// r=pow(a,2)*cos(2*theta);
		//
		this.a = 200;
		this.k = 2;
		// float r = this.a * (sin(this.k * this.theta) * cos(this.k * this.theta));
		// float r = this.a * (sin(theta) + pow(sin((5 * theta) / 2), 3));
		// float r = this.a * (sin(theta) * pow(sin((5 * theta) / 2), 3));
		// float r = this.a * pow(cos((this.k * theta) / 3), 3);
		// float r = pow(this.a, 1) * cos(this.k * theta);
		float r = this.a * sin(theta);
		// float r = this.a * (1 + cos(theta));
		return r;

	}

	@Override
	public void settings()
	{
		this.size(1000, 1000);
	}

	@Override
	public void setup()
	{
		this.frameRate(400);
		this.background(0);

		this.h = this.height;
		this.w = this.width;
		this.font = this.loadFont("AlegreyaSans-Thin-20.vlw");

		this.strokeWeight(1);
		this.stroke(255);
		this.line(0, this.h / 2, this.w, this.h / 2);
		this.line(this.w / 2, 0, this.w / 2, this.h);
		this.strokeWeight(3);
	}

	public float spiraleAurea(float theta)
	{
		// equazione Spirale aurea
		this.a = 30;
		this.k = 1.618f;
		float r = this.a * exp((this.k * theta * log(1 + (sqrt(5) / 2))) / PI);
		return r;
	}

}
