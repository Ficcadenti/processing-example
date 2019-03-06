public void settings()
{
  size(1000,600);
}

public void setup()
{
    background(0);
    testAlbero();
}

public void testAlbero()
{
  int  RICORSIVO  = 2;

  
  Scheda sc1 = new Scheda("Raffaele", "Ficcadenti", 42);
  Scheda sc2 = new Scheda("Edo", "Galizia", 50);
  Scheda sc3 = new Scheda("Yuri", "Quaglia", 31);
  Scheda sc4 = new Scheda("Samuele", "Parentato", 18);
  Scheda sc5 = new Scheda("Alberto", "Ruggeri", 28);
  Scheda sc6 = new Scheda("Francesca", "Maffia", 16);
  Scheda sc7 = new Scheda("Enzo", "Cianfarani", 70);
  Scheda sc8 = new Scheda("Pluto", "Pippo", 23);
  
  AlberoBinario albero = new AlberoBinario(RICORSIVO);
  
  albero.inserisciValore(sc1);
  albero.inserisciValore(sc2);
  albero.inserisciValore(sc3);
  albero.inserisciValore(sc4);
  albero.inserisciValore(sc5);
  albero.inserisciValore(sc6);
  albero.inserisciValore(sc7);
  albero.inserisciValore(sc8);
  
  //albero.cancella(sc4);
  
  
  albero.visitaPreOrder();
}
