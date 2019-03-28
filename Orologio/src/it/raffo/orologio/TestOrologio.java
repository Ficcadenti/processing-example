package it.raffo.orologio;

import processing.core.PApplet;

public class TestOrologio extends PApplet
{

	static String numeriRomani[] = new String[12];

	// da romani ad arabi (base 10)
	public static String converti(int value)
	{
		if (value == 1000)
		{
			return "M";
		}

		String str = "";

		int unita;
		int decine;
		int centinaia;

		centinaia = value / 100;
		decine = (value - (centinaia * 100)) / 10;
		unita = (value - (centinaia * 100) - (decine * 10));
		switch (centinaia)
		{
			case 1:
				str += "C";
				break;
			case 2:
				str += "CC";
				break;
			case 3:
				str += "CCC";
				break;
			case 4:
				str += "CD";
				break;
			case 5:
				str += "D";
				break;
			case 6:
				str += "DC";
				break;
			case 7:
				str += "DCC";
				break;
			case 8:
				str += "DCCC";
				break;
			case 9:
				str += "CM";
				break;
		}

		switch (decine)
		{
			case 1:
				str += "X";
				break;
			case 2:
				str += "XX";
				break;
			case 3:
				str += "XXX";
				break;
			case 4:
				str += "XL";
				break;
			case 5:
				str += "L";
				break;
			case 6:
				str += "LX";
				break;
			case 7:
				str += "LXX";
				break;
			case 8:
				str += "LXXX";
				break;
			case 9:
				str += "XC";
				break;
		}

		switch (unita)
		{
			case 1:
				str += "I";
				break;
			case 2:
				str += "II";
				break;
			case 3:
				str += "III";
				break;
			case 4:
				str += "IV";
				break;
			case 5:
				str += "V";
				break;
			case 6:
				str += "VI";
				break;
			case 7:
				str += "VII";
				break;
			case 8:
				str += "VIII";
				break;
			case 9:
				str += "IX";
				break;
		}
		return str;
	}

	public static int converti(String str)
	{
		int value = 0;
		byte x = 0;

		for (int i = str.length() - 1; i >= 0; i--)
		{
			switch (str.charAt(i))
			{
				case 'I':
					if (x <= 0)
					{
						value += 1;
					} else
					{
						value -= 1;
					}
					x = 0;
					break;
				case 'V':
					if (x <= 1)
					{
						value += 5;
					} else
					{
						value -= 5;
					}
					x = 1;
					break;
				case 'X':
					if (x <= 2)
					{
						value += 10;
					} else
					{
						value -= 10;
					}
					x = 2;
					break;
				case 'L':
					if (x <= 3)
					{
						value += 50;
					} else
					{
						value -= 50;
					}
					x = 3;
					break;
				case 'C':
					if (x <= 4)
					{
						value += 100;
					} else
					{
						value -= 100;
					}
					x = 4;
					break;
				case 'D':
					if (x <= 5)
					{
						value += 500;
					} else
					{
						value -= 500;
					}
					x = 5;
					break;
				case 'M':
					if (x <= 6)
					{
						value += 1000;
					} else
					{
						value -= 1000;
					}
					x = 6;
					break;
			}
		}
		return value;
	}

	public static void generaNumeriRomani()
	{
		numeriRomani[0] = converti(12);
		for (int i = 1; i < 12; i++)
		{
			numeriRomani[i] = converti(i);
		}
	}

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.orologio.TestOrologio");
		generaNumeriRomani();
	}

	int     h            = 1000;
	int     raggioX      = 300;
	int     raggioY      = 300;
	int     raggioCronoX = 40;
	int     raggioCronoY = 40;
	int     w            = 1000;
	float   x            = this.w / 2;
	float   y            = this.h / 2;
	float   xc;
	float   yc;
	float   secondsRadius;
	boolean sincro       = false;

	int     startM       = 0;

	int     startS       = 0;

	float   millisecondi = 0;
	float   secondi      = 0;

	@Override
	public void draw()
	{

		float xs, ys, xm, ym, xh, yh, xms, yms;

		this.startS = second();

		while (((this.startS + 1) != second()) && (this.sincro == false))
		{
			System.out.println("Sincronizzazione (" + this.startS + ")....");
			this.startM = this.millis();
		}
		this.sincro = true;

		this.millisecondi = (this.millis() - this.startM) % 1000;
		System.out.println(this.millisecondi);
		float ms = map(this.millisecondi, 0, 1000, 0, TWO_PI) - HALF_PI;

		float s = map(second() + norm(this.millisecondi, 0, 1000), 0, 60, 0, TWO_PI) - HALF_PI;
		float m = map(minute() + norm(second(), 0, 60), 0, 60, 0, TWO_PI) - HALF_PI;
		float h = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;

		this.background(0);
		this.generaPunti(40);
		this.drawDisplay();

		this.fill(255);
		this.textAlign(this.CENTER);
		this.textFont(this.createFont("Arial", 32, true), 32);
		this.text(
		        String.format("%02d", hour()) + ":" + String.format("%02d", minute()) + ":"
		                + String.format("%02d", second()) + " - " + String.format("%03d", (int) this.millisecondi),
		        this.width / 2, 130);

		xms = (this.raggioCronoX) * cos(ms);
		yms = (this.raggioCronoY) * sin(ms);
		xs = (this.raggioX - 50) * cos(s);
		ys = (this.raggioY - 50) * sin(s);
		xm = (this.raggioX - 30) * cos(m);
		ym = (this.raggioY - 30) * sin(m);
		xh = (this.raggioX - 15) * cos(h);
		yh = (this.raggioY - 15) * sin(h);

		this.translate(this.w / 2, this.h / 2);
		this.strokeWeight(1);
		this.stroke(0, 255, 0);
		this.line(-130, -110, -130 + xms, -110 + yms);
		this.strokeWeight(3);
		this.stroke(0, 255, 0);
		this.line(0, 0, xs, ys);
		this.strokeWeight(5);
		this.stroke(0, 255, 0);
		this.line(0, 0, xm, ym);
		this.strokeWeight(7);
		this.stroke(0, 255, 0);
		this.line(0, 0, xh, yh);
		// h = this.height;
	}

	private void drawDisplay()
	{

		this.pushMatrix();
		this.stroke(255);
		this.strokeWeight(3);

		this.translate(this.w / 2, this.h / 2);

		this.fill(0, 255, 0);
		for (float a = 0; a < 360; a += 0.2)
		{
			float angle = radians(a);
			this.x = (this.raggioX - 65) * sin(angle);
			this.y = (this.raggioY - 65) * cos(angle);
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

		// disegno numeri
		for (int i = 0; i < 12; i++)
		{
			float p = (((2 * PI) / 12) * i);

			this.x = (this.raggioX + 30) * cos(p - HALF_PI);
			this.y = (this.raggioY + 30) * sin(p - HALF_PI);

			this.textAlign(this.CENTER);
			this.textFont(this.createFont("Arial", 12, true), 18);
			this.text(numeriRomani[i], this.x, this.y);
		}

		// disegno mm
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
		// dot centrale
		this.fill(255, 255, 0);
		this.ellipse(0, 0, 30, 30);

		this.translate(-130, -110);
		for (float a = 0; a < 360; a += 0.2)
		{
			float angle = radians(a);
			this.x = (this.raggioCronoX) * sin(angle);
			this.y = (this.raggioCronoX) * cos(angle);
			this.point(this.x, this.y);

		}

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
