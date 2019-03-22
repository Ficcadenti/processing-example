package it.raffo.fs;

import processing.core.PApplet;

public class TestFSList extends PApplet
{

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.fs.TestFSList");
		int sec = 1;
		float s = map(sec, 0, 60, 0, TWO_PI) - HALF_PI;
		float m = map(minute() + norm(second(), 0, 60), 0, 60, 0, TWO_PI) - HALF_PI;
		float h = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;

		float n = norm(sec, 0, 60);

		float s1 = map(sec, 0, 60, 0, TWO_PI);
		System.out.println("Secondi : " + sec);
		System.out.println("s       : " + s);
		System.out.println("s1      : " + s1);
		System.out.println("TWO_PI  : " + TWO_PI);
		System.out.println("HALF_PI : " + HALF_PI);
		System.out.println("n       : " + n);

		int diametro = 100;
		Centro c = new Centro(10, 10);
		Centro c1 = new Centro(10, 10);
		Giardino gc1 = new Giardino(c, diametro - 30, diametro + 100, diametro - 100, diametro + 100);
		Giardino gc2 = new Giardino(c1, diametro - 20, diametro + 100, diametro - 100, diametro + 100);
		gc1.setNomeGiardino("Raffo");
		gc2.setNomeGiardino("Di chi lo vuole");
		System.out.println(gc1);
		System.out.println(gc2);
		System.out.println(gc1.equals(gc2));
	}

}
