package it.raffo.fs;

import java.util.ArrayList;

import processing.core.PApplet;

public class TestFSList extends PApplet
{

	public static void main(String[] args)
	{
		PApplet.main("it.raffo.fs.TestFSList");

		int diametro = 10;
		int x = 40;
		int y = 40;
		Centro c1 = new Centro(x, y);
		Giardino gc1 = new Giardino(c1, x - diametro, x + diametro, y - diametro, y + diametro);

		x = 40;
		y = 40;
		Centro c2 = new Centro(x, y);
		Giardino gc2 = new Giardino(c2, x - diametro, x + diametro, y - diametro, y + diametro);

		gc1.setNomeGiardino("this");
		gc2.setNomeGiardino("g2");
		System.out.println(gc1);
		System.out.println(gc2);
		System.out.println("Uguali       : " + gc1.equals(gc2));
		System.out.println("Interferenza : " + gc1.interferisce(gc2));

		ArrayList<Giardino> terra = new ArrayList<>();
		terra.add(gc1);

		System.out.println("Esiste un girdino nella terra : " + terra.contains(gc2));

		Terra.getInstance().setW(1900);
		Terra.getInstance().setH(1600);
		Centro c3 = Terra.getInstance().calcolaCentro(diametro / 2);
		System.out.println(c3);
		// Centro c3 = Terra.getInstance().calcolaCentro(5);
		// System.out.println(c3);
	}

}
