package it.raffomafr.clientserver;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import processing.core.PApplet;
import processing.net.Client;
import processing.net.Server;

public class ServerP3 extends PApplet
{
	private static final Logger	log	= Logger.getLogger(ServerP3.class);
	private Server				s;
	private Client				c;

	public static void main(String[] args)
	{
		PropertyConfigurator.configure("log4j.properties");
		PApplet.main("it.raffomafr.clientserver.ServerP3");
	}

	@Override
	public void draw()
	{
		this.c = this.s.available();
		if (this.c != null)
		{
			String input = this.c.readString();
			log.info("Ricevuto dal client: " + input);
		}
	}

	@Override
	public void settings()
	{
		this.size(800, 800);
		log.info("Test Server");
	}

	@Override
	public void setup()
	{
		this.background(0);
		this.s = new Server(this, 12345);
		if (this.s != null)
		{
			log.info("Il sever è pronto !!!");
		}
		else
		{
			log.info("Errore init sever !!!");
		}
	}

}
