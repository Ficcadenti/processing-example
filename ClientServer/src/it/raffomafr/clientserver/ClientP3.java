package it.raffomafr.clientserver;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import processing.core.PApplet;
import processing.net.Client;

public class ClientP3 extends PApplet
{
	private static final Logger	log	= Logger.getLogger(ClientP3.class);
	private Client				c;
	private String				input;

	public static void main(String[] args)
	{
		PropertyConfigurator.configure("log4j.properties");
		PApplet.main("it.raffomafr.clientserver.ClientP3");
	}

	@Override
	public void draw()
	{

	}

	@Override
	public void settings()
	{
		this.size(800, 800);
		log.info("Test Client");
	}

	@Override
	public void keyPressed()
	{

		if (this.keyCode == DOWN)
		{
			log.info("Press KEY DOWN");
			this.c.write("KEY DOWN");
		}

	}

	@Override
	public void setup()
	{
		this.background(0);
		this.c = new Client(this, "127.0.0.1", 12345); // Replace with your server’s IP and port
		if (this.c != null)
		{
			log.info("Il client è pronto !!!");
		}
	}

}
