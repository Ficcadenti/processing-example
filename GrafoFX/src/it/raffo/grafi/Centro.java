package it.raffo.grafi;

public class Centro
{
	private int	x;
	private int	y;

	public Centro(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public String toString()
	{
		return "Centro [x=" + this.x + ", y=" + this.y + "]";
	}

}
