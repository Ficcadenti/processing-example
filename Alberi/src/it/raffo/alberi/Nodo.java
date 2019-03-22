package it.raffo.alberi;

import java.util.ArrayList;
import java.util.List;

public class Nodo
{
	private Centro		c;
	private Elemento	elem;
	private List<Nodo>	figli;
	private Nodo		padre;

	public Nodo()
	{
		this.elem = null;
		this.figli = null;
		this.padre = null;
	}

	public Nodo(Elemento elem)
	{
		this.elem = elem;
		this.figli = null;
		this.padre = null;
	}

	public void addFiglio(Elemento elem)
	{
		if (this.figli == null)
		{
			this.figli = new ArrayList<>();
		}
		this.figli.add(new Nodo(elem));
	}

	public void addFiglio(Nodo f)
	{
		if (this.figli == null)
		{
			this.figli = new ArrayList<>();
		}
		this.figli.add(f);
	}

	public Centro getC()
	{
		return this.c;
	}

	public Elemento getElem()
	{
		return this.elem;
	}

	public List<Nodo> getFigli()
	{
		return this.figli;
	}

	public Nodo getPadre()
	{
		return this.padre;
	}

	public void setC(Centro c)
	{
		this.c = c;
	}

	public void setElem(Elemento elem)
	{
		this.elem = elem;
	}

	public void setFigli(List<Nodo> figli)
	{
		this.figli = figli;
	}

	public void setPadre(Nodo padre)
	{
		this.padre = padre;
	}

	@Override
	public String toString()
	{
		return "Nodo [elem=" + this.elem + ", figli=" + this.figli + ", padre=" + this.padre + "]";
	}

}
