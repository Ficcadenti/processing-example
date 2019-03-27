package it.raffo.giocodellavita.model;

public class GiardinoCellulare extends Cellula
{
	private String	nomeGiardino	= null;
	private int		gXS;
	private int		gXD;
	private int		gYT;
	private int		gYD;

	public GiardinoCellulare(Cellula v, int w, int h)
	{
		super(v.getX(), v.getY(), v.getStatoIniziale());
		this.gXS = v.getX() - 1;
		this.gXD = v.getX() + 1;
		this.gYT = v.getY() - 1;
		this.gYD = v.getY() + 1;

		if (this.gXS < 0)
		{
			this.gXS = 0;
		}
		if (this.gXD >= w)
		{
			this.gXD = w - 1;
		}
		if (this.gYT < 0)
		{
			this.gYT = 0;
		}
		if (this.gYD >= h)
		{
			this.gYD = h - 1;
		}

	}

	public GiardinoCellulare(Cellula v, int gXS, int gXD, int gYT, int gYD)
	{
		super(v.getX(), v.getY(), v.getStatoIniziale());
		this.gXS = gXS;
		this.gXD = gXD;
		this.gYT = gYT;
		this.gYD = gYD;
		this.setNomeGiardino("");
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (this.getClass() != obj.getClass())
		{
			return false;
		}
		GiardinoCellulare other = (GiardinoCellulare) obj;
		if (this.gXD != other.gXD)
		{
			return false;
		}
		if (this.gXS != other.gXS)
		{
			return false;
		}
		if (this.gYD != other.gYD)
		{
			return false;
		}
		if (this.gYT != other.gYT)
		{
			return false;
		}
		return true;
	}

	public Cellula getCellula()
	{
		return new Cellula(super.getX(), super.getY(), super.getStatoIniziale());
	}

	public int getgXD()
	{
		return this.gXD;
	}

	public int getgXS()
	{
		return this.gXS;
	}

	public int getgYD()
	{
		return this.gYD;
	}

	public int getgYT()
	{
		return this.gYT;
	}

	public String getNomeGiardino()
	{
		return this.nomeGiardino;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result) + this.gXD;
		result = (prime * result) + this.gXS;
		result = (prime * result) + this.gYD;
		result = (prime * result) + this.gYT;
		return result;
	}

	public void setgXD(int gXD)
	{
		this.gXD = gXD;
	}

	public void setgXS(int gXS)
	{
		this.gXS = gXS;
	}

	public void setgYD(int gYD)
	{
		this.gYD = gYD;
	}

	public void setgYT(int gYT)
	{
		this.gYT = gYT;
	}

	public void setNomeGiardino(String nomeGiardino)
	{
		this.nomeGiardino = nomeGiardino;
	}

	@Override
	public String toString()
	{
		return "Giardino [gXS=" + this.gXS + ", gXD=" + this.gXD + ", gYT=" + this.gYT + ", gYD=" + this.gYD + "] ";
	}

}
