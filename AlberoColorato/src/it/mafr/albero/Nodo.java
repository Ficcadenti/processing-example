package it.mafr.albero;

class Nodo
{
	int		valore;
	Nodo	figlioSinistro;
	Nodo	figlioDestro;
	int		posX;
	int		posY;

	Nodo(int valore)
	{
		this.valore = valore;
	}

	Nodo getFiglioDestro()
	{
		return this.figlioDestro;
	}

	Nodo getFiglioSinistro()
	{
		return this.figlioSinistro;
	}

	public int getPosX()
	{
		return this.posX;
	}

	public int getPosY()
	{
		return this.posY;
	}

	void setFiglioDestro(Nodo figlioDestro)
	{
		this.figlioDestro = figlioDestro;
	}

	void setFiglioSinistro(Nodo figlioSinistro)
	{
		this.figlioSinistro = figlioSinistro;
	}

	void setPosX(int valore)
	{
		this.posX = valore;
	}

	public void setPosY(int valore)
	{
		this.posY = valore;
	}

}