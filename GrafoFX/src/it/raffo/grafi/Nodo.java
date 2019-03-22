package it.raffo.grafi;

public class Nodo
{

	private String	txt;
	private int		x;
	private int		y;

	public Nodo(String txt)
	{
		super();
		Centro c = Matrice.getInstance().calcolaCentro();
		this.x = c.getX();
		this.y = c.getY();
		this.txt = txt;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (this.getClass() != obj.getClass())
		{
			return false;
		}
		Nodo other = (Nodo) obj;
		if (this.txt == null)
		{
			if (other.txt != null)
			{
				return false;
			}
		}
		else if (!this.txt.equals(other.txt))
		{
			return false;
		}
		if (this.x != other.x)
		{
			return false;
		}
		if (this.y != other.y)
		{
			return false;
		}
		return true;
	}

	public String getTxt()
	{
		return this.txt;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.txt == null) ? 0 : this.txt.hashCode());
		result = (prime * result) + this.x;
		result = (prime * result) + this.y;
		return result;
	}

	public void setTxt(String txt)
	{
		this.txt = txt;
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
		return "(" + this.txt + "," + this.x + "," + this.y + ")";
	}

}
