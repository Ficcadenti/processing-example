package it.raffo.alberi;

public class CentroFloat
{

	private float x;
	private float y;

	public CentroFloat(float x, float y) {
		super();
		this.x = x;
		this.y = y;
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
		CentroFloat other = (CentroFloat) obj;
		if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x))
		{
			return false;
		}
		if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y))
		{
			return false;
		}
		return true;
	}

	public CentroFloat getCentro()
	{
		return new CentroFloat(this.x, this.y);
	}

	public float getX()
	{
		return this.x;
	}

	public float getY()
	{
		return this.y;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + Float.floatToIntBits(this.x);
		result = (prime * result) + Float.floatToIntBits(this.y);
		return result;
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
