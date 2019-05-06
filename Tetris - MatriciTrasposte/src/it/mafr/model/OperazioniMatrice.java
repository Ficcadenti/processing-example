package it.mafr.model;

import it.mafr.utility.Rotazioni;

public interface OperazioniMatrice
{
	public void stampaMatrice();

	public void riflessa();

	public void inversioneRighe();

	public void inversioneColonne();

	public void trasposta();

	public void ruota(Rotazioni rotazione);
}
