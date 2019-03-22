package it.raffo.fs;

public class Giardino extends Centro
{
	private String	nomeGiardino	= null;
	private int		gXS;
	private int		gXD;
	private int		gYT;
	private int		gYD;

	public Giardino(Centro c, int gXS, int gXD, int gYT, int gYD)
	{
		super(c.getX(), c.getY());
		this.gXS = gXS;
		this.gXD = gXD;
		this.gYT = gYT;
		this.gYD = gYD;
		this.setNomeGiardino("");
	}

	public Giardino(int x, int y, int gXS, int gXD, int gYT, int gYD)
	{
		super(x, y);
		this.gXS = gXS;
		this.gXD = gXD;
		this.gYT = gYT;
		this.gYD = gYD;
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
		Giardino other = (Giardino) obj;
		if (!super.getCentro().equals(other.getCentro()))
		{
			return false;
		}
		return true;
	}

	private int getgXD()
	{
		return this.gXD;
	}

	private int getgXS()
	{
		return this.gXS;
	}

	private int getgYD()
	{
		return this.gYD;
	}

	private int getgYT()
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

	public boolean interferisce(Giardino g)
	{
		boolean bRet = false;
		if (super.getCentro().equals(g.getCentro()))
		{
			bRet = true;
		}
		return bRet;

	}

	private void setgXD(int gXD)
	{
		this.gXD = gXD;
	}

	private void setgXS(int gXS)
	{
		this.gXS = gXS;
	}

	private void setgYD(int gYD)
	{
		this.gYD = gYD;
	}

	private void setgYT(int gYT)
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
		return "Giardino [gXS=" + this.gXS + ", gXD=" + this.gXD + ", gYT=" + this.gYT + ", gYD=" + this.gYD + "] con "
				+ super.getCentro() + " (" + this.nomeGiardino + ")";
	}

}
