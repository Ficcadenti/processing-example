package it.raffo.alberi;

import java.util.ArrayList;
import java.util.List;

public class NodoFloat
{

	private CentroFloat     c;
	private Elemento        elem;
	private List<NodoFloat> figli;
	private NodoFloat       padre;
	private int             numeroFigli;

	public NodoFloat() {
		this.elem = null;
		this.figli = null;
		this.padre = null;
	}

	public NodoFloat(Elemento elem) {
		this.elem = elem;
		this.numeroFigli = 0;
		this.figli = null;
		this.padre = null;
	}

	public void addFiglio(Elemento elem)
	{
		if (this.figli == null)
		{
			this.figli = new ArrayList<>();
		}
		this.figli.add(new NodoFloat(elem));
	}

	public void addFiglio(NodoFloat f)
	{
		if (this.figli == null)
		{
			this.figli = new ArrayList<>();
		}
		this.figli.add(f);
	}

	public CentroFloat getC()
	{
		return this.c;
	}

	public Elemento getElem()
	{
		return this.elem;
	}

	public List<NodoFloat> getFigli()
	{
		return this.figli;
	}

	public int getNumeroFigli()
	{
		return this.numeroFigli;
	}

	public NodoFloat getPadre()
	{
		return this.padre;
	}

	public void setC(CentroFloat c)
	{
		this.c = c;
	}

	public void setElem(Elemento elem)
	{
		this.elem = elem;
	}

	public void setFigli(List<NodoFloat> figli)
	{
		this.figli = figli;
	}

	public void setNumeroFigli(int numeroFigli)
	{
		this.numeroFigli = numeroFigli;
	}

	public void setPadre(NodoFloat padre)
	{
		this.padre = padre;
	}

	@Override
	public String toString()
	{
		return "Nodo [ Elem=" + this.elem.getNome() + ", figli=" + this.figli + ", padre=" + this.padre + ", numeroFigli=" + this.numeroFigli + "]";
	}

}
