package it.raffo.grafi;

public class Arco implements Comparable<Arco>
{
	protected Nodo		nodo1, nodo2;
	protected Integer	peso;

	public Arco()
	{
		this.nodo1 = this.nodo2 = null;
		this.peso = null;
	}

	public Arco(Nodo n1, Nodo n2, Integer v)
	{
		this.nodo1 = n1;
		this.nodo2 = n2;
		this.peso = v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Arco o)
	{
		int i = ((Comparable<Integer>) this.peso).compareTo(o.peso);
		if (i == 0)
		{
			int j = ((Comparable<Nodo>) this.nodo1).compareTo(o.nodo1);
			if (j == 0)
			{
				return ((Comparable<Nodo>) this.nodo2).compareTo(o.nodo2);
			}
			else
			{
				return j;
			}
		}
		else
		{
			return i;
		}
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
		Arco other = (Arco) obj;
		if (this.nodo1 == null)
		{
			if (other.nodo1 != null)
			{
				return false;
			}
		}
		else if (!this.nodo1.equals(other.nodo1))
		{
			return false;
		}
		if (this.nodo2 == null)
		{
			if (other.nodo2 != null)
			{
				return false;
			}
		}
		else if (!this.nodo2.equals(other.nodo2))
		{
			return false;
		}
		if (this.peso == null)
		{
			if (other.peso != null)
			{
				return false;
			}
		}
		else if (!this.peso.equals(other.peso))
		{
			return false;
		}
		return true;
	}

	public Nodo getNodo1()
	{
		return this.nodo1;
	}

	public Nodo getNodo2()
	{
		return this.nodo2;
	}

	public Integer getValore()
	{
		return this.peso;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.nodo1 == null) ? 0 : this.nodo1.hashCode());
		result = (prime * result) + ((this.nodo2 == null) ? 0 : this.nodo2.hashCode());
		result = (prime * result) + ((this.peso == null) ? 0 : this.peso.hashCode());
		return result;
	}

	public void setNodo1(Nodo nodo1)
	{
		this.nodo1 = nodo1;
	}

	public void setNodo2(Nodo nodo2)
	{
		this.nodo2 = nodo2;
	}

	public void setValore(Integer peso)
	{
		this.peso = peso;
	}

	@Override
	public String toString()
	{
		return "Arco [" + this.nodo1.getTxt() + "," + this.nodo2.getTxt() + "]";
	}

}
