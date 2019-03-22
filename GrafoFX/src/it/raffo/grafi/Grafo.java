package it.raffo.grafi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PFont;

public class Grafo
{
	private PFont						f;
	private HashMap<Nodo, Set<Arco>>	nodi;
	private int							numeroArchi;
	private PApplet						pa;
	private int							raggio;

	public Grafo(PApplet pa)
	{
		this.nodi = new HashMap<Nodo, Set<Arco>>();
		this.numeroArchi = 0;
		this.pa = pa;
		this.f = this.pa.createFont("Arial", 32, true); // Loading font
	}

	public boolean add(Arco a)
	{
		return this.add(a.getNodo1(), a.getNodo2(), a.getValore());
	}

	public void add(Nodo x)
	{
		if (!this.nodi.containsKey(x))
		{
			Set<Arco> lista = new HashSet<Arco>();
			this.nodi.put(x, lista);
		}
	}

	public boolean add(Nodo x, Nodo y, Integer value)
	{
		boolean flag = false, flag1 = false;
		if (!this.nodi.containsKey(x))
		{
			this.add(x);
		}
		if (!this.nodi.containsKey(y))
		{
			this.add(y);
		}
		Arco a = new Arco(x, y, value);
		flag = (this.nodi.get(x)).add(a);
		flag1 = (this.nodi.get(y)).add(a);
		flag = flag && flag1;
		if (flag)
		{
			this.numeroArchi++;
		}
		return flag;
	}

	private void drawArco(Arco a)
	{
		this.pa.pushMatrix();
		this.pa.noFill();
		this.pa.stroke(255, 255, 255);
		this.pa.line(a.getNodo1().getX(), a.getNodo1().getY(), a.getNodo2().getX(), a.getNodo2().getY());
		this.pa.popMatrix();

	}

	public void drawGrafo()
	{
		Nodo nodo;
		Arco a;
		Iterator<Arco> arcoI;
		Iterator<Nodo> nodoI = this.nodi.keySet().iterator();

		while (nodoI.hasNext())
		{
			nodo = nodoI.next();
			arcoI = this.nodi.get(nodo).iterator();
			this.drawNodo(nodo);
			while (arcoI.hasNext())
			{
				a = arcoI.next();
				this.drawArco(a);
			}
		}
	}

	private void drawNodo(Nodo nodo)
	{
		this.pa.pushMatrix();
		this.pa.noFill();
		this.pa.stroke(255, 255, 255);
		this.pa.textFont(this.f, 20);
		this.pa.ellipse(nodo.getX(), nodo.getY(), this.getRaggio() * 2, this.getRaggio() * 2);
		this.pa.fill(this.pa.random(255), this.pa.random(255), this.pa.random(255));
		this.pa.ellipse(nodo.getX(), nodo.getY(), (this.getRaggio() * 2) - 6, (this.getRaggio() * 2) - 6);
		this.pa.fill(0);
		this.pa.ellipse(nodo.getX(), nodo.getY(), 10, 10);
		this.pa.fill(255);
		this.pa.textAlign(this.pa.CENTER);
		this.pa.text(nodo.getTxt(), nodo.getX(), nodo.getY() - ((this.getRaggio()) + 5));
		this.pa.popMatrix();
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
		Grafo other = (Grafo) obj;
		if (this.nodi == null)
		{
			if (other.nodi != null)
			{
				return false;
			}
		}
		else if (!this.nodi.equals(other.nodi))
		{
			return false;
		}
		if (this.numeroArchi != other.numeroArchi)
		{
			return false;
		}
		return true;
	}

	public Set<Nodo> getInsiemeNodi()
	{
		return this.nodi.keySet();
	}

	public Set<Arco> getInsiemiArchi()
	{
		Set<Arco> setArchi = new HashSet<Arco>();
		Iterator<Set<Arco>> hashSetI = this.nodi.values().iterator();
		while (hashSetI.hasNext())
		{
			setArchi.addAll(hashSetI.next());
		}

		return setArchi;
	}

	public Set<Arco> getInsiemiArchi(Object nodo)
	{
		if (this.nodi.containsKey(nodo))
		{
			return this.nodi.get(nodo);
		}
		else
		{
			return null;
		}
	}

	public HashMap<Nodo, Set<Arco>> getNodi()
	{
		return this.nodi;
	}

	public int getNumeroArchi()
	{
		return this.numeroArchi;
	}

	public int getRaggio()
	{
		return this.raggio;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.nodi == null) ? 0 : this.nodi.hashCode());
		result = (prime * result) + this.numeroArchi;
		return result;
	}

	public int numeroNodi()
	{
		return this.nodi.size();
	}

	public boolean remove(Arco a)
	{
		boolean flag = false, flag1 = false;
		if (this.nodi.containsKey(a.getNodo1()) && this.nodi.containsKey(a.getNodo2()))
		{
			flag = ((HashSet<Arco>) this.nodi.get(a.getNodo1())).remove(a);
			flag1 = ((HashSet<Arco>) this.nodi.get(a.getNodo2())).remove(a);
		}
		return flag || flag1;
	}

	public boolean remove(Nodo x, Nodo y, Integer value)
	{
		Arco a = new Arco(x, y, value);
		return this.remove(a);
	}

	public void remove(Object x)
	{
		if (this.nodi.containsKey(x))
		{
			Iterator<Arco> arcoIncidenteI = ((HashSet<Arco>) this.nodi.get(x)).iterator();
			Arco a;
			Object y;
			while (arcoIncidenteI.hasNext())
			{
				a = arcoIncidenteI.next();
				y = (a.getNodo1().equals(x)) ? a.getNodo2() : a.getNodo1();
				if (((HashSet<Arco>) this.nodi.get(y)).remove(a))
				{
					this.numeroArchi--;
				}
			}
			this.nodi.remove(x);
		}
	}

	public void setNodi(HashMap<Nodo, Set<Arco>> nodi)
	{
		this.nodi = nodi;
	}

	public void setNumeroArchi(int numeroArchi)
	{
		this.numeroArchi = numeroArchi;
	}

	public void setRaggio(int raggio)
	{
		this.raggio = raggio;
	}

	private void stampaNodo(Nodo nodo)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String toString()
	{
		StringBuffer out = new StringBuffer();
		Nodo nodo;
		Arco a;
		Iterator<Arco> arcoI;
		Iterator<Nodo> nodoI = this.nodi.keySet().iterator();

		while (nodoI.hasNext())
		{
			nodo = nodoI.next();
			arcoI = this.nodi.get(nodo).iterator();
			out.append("Nodo " + nodo.toString() + ": ");
			while (arcoI.hasNext())
			{
				a = arcoI.next();
				out.append(a.toString() + ", ");
			}
			out.append("\n");
		}
		return out.toString();
	}
}
