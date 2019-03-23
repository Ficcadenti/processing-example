package it.raffo.fs;

import java.util.ArrayList;

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

		int diametro = 10;
		int x = 40;
		int y = 40;
		Centro c = new Centro(x, y);
		Giardino gc1 = new Giardino(c, x - diametro, x + diametro, y - diametro, y + diametro);

		x = 40;
		y = 40;
		Centro c1 = new Centro(x, y);
		Giardino gc2 = new Giardino(c1, x - diametro, x + diametro, y - diametro, y + diametro);

		gc1.setNomeGiardino("this");
		gc2.setNomeGiardino("g2");
		System.out.println(gc1);
		System.out.println(gc2);
		System.out.println("Uguali       : " + gc1.equals(gc2));
		System.out.println("Interferenza : " + gc1.interferisce(gc2));

		ArrayList<Giardino> terra = new ArrayList<>();
		terra.add(gc1);

		System.out.println("Esiste un girdino nella terra : " + terra.contains(gc2));

		Terra.getInstance().setW(20);
		Terra.getInstance().setH(20);
		Centro c2 = Terra.getInstance().calcolaCentro(5);
		System.out.println(c2);
		Centro c3 = Terra.getInstance().calcolaCentro(5);
		System.out.println(c3);
	}

}
