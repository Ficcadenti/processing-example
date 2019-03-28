package it.raffo.giocodellavita.model;

public class Cellula
{
	public static final int	CELLULA_VIVA			= 1;
	public static final int	CELLULA_MORTA			= 0;
	public static final int	VIVRA					= 1;
	public static final int	MORIRA					= 2;
	public static final int	NASCERA					= 3;
	public static final int	DEFINITIVAMENTE_MORTO	= 0;
	public static final int	NEL_PIENO_DELLA_VITA	= 255;
	public static final int	STEP_OPACITA			= 10;

	private int				x;
	private int				y;
	private int				statoIniziale;
	private int				statoGenerazioneSuccessiva;
	private boolean			colonizzata;
	private int				opacita;

	public Cellula(int stato)
	{
		this.setStatoIniziale(this.statoIniziale);
	}

	public Cellula(int x, int y, int stato)
	{
		super();
		this.x = x;
		this.y = y;
		this.setStatoIniziale(stato);
		this.setOpacita(NEL_PIENO_DELLA_VITA);
		this.setColonizzata(true);
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
		Cellula other = (Cellula) obj;
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

	public int getOpacita()
	{
		return this.opacita;
	}

	public int getStatoGenerazioneSuccessiva()
	{
		return this.statoGenerazioneSuccessiva;
	}

	public int getStatoIniziale()
	{
		return this.statoIniziale;
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
		result = (prime * result) + this.x;
		result = (prime * result) + this.y;
		return result;
	}

	public boolean isColonizzata()
	{
		return this.colonizzata;
	}

	public void setColonizzata(boolean colonizzata)
	{
		this.colonizzata = colonizzata;
	}

	public void setOpacita(int opacita)
	{
		if (opacita <= 0)
		{
			opacita = NEL_PIENO_DELLA_VITA;
		}
		this.opacita = opacita;
	}

	public void setStatoGenerazioneSuccessiva(int statoGenerazioneSuccessiva)
	{
		this.statoGenerazioneSuccessiva = statoGenerazioneSuccessiva;
	}

	public void setStatoIniziale(int statoIniziale)
	{
		this.statoIniziale = statoIniziale;
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
		return "Verice [x=" + this.x + ", y=" + this.y + "]";
	}

}
