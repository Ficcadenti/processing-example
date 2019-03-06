public class Nodo
{
  private Scheda  sc;
  private Nodo  dx;
  private Nodo  sx;
  private Nodo  padre;

  public Nodo()
  {
    this.sc = null;
    this.sx = null;
    this.dx = null;
    this.padre = null;
  }

  public Nodo(Scheda sc)
  {
    this.sc = sc;
    this.sx = null;
    this.dx = null;
    this.padre = null;
  }

  public Nodo(Scheda sc, Nodo sx, Nodo dx, Nodo padre)
  {
    this.sc = sc;
    this.sx = sx;
    this.dx = dx;
    this.padre = padre;
  }

  public Nodo getDx()
  {
    return this.dx;
  }

  public Nodo getPadre()
  {
    return this.padre;
  }

  public Scheda getSc()
  {
    return this.sc;
  }

  public Nodo getSx()
  {
    return this.sx;
  }

  public void setDx(Nodo dx)
  {
    this.dx = dx;
  }

  public void setPadre(Nodo padre)
  {
    this.padre = padre;
  }

  public void setSc(Scheda sc)
  {
    this.sc = sc;
  }

  public void setSx(Nodo sx)
  {
    this.sx = sx;
  }

  @Override
  public String toString()
  {
    return "Nodo [sc=" + this.sc + ", dx=" + this.dx + ", sx=" + this.sx + "]";
  }

}
