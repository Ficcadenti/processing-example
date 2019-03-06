class Scheda implements Comparable<Scheda>
{
  private String  nome;
  private String  cognome;
  private Integer  eta;

  public Scheda(String n, String c, Integer e)
  {
    this.nome = n;
    this.cognome = c;
    this.eta = e;
  }

  @Override
  public int compareTo(Scheda sc)
  {
    int ret = 0;
    if (this.eta < sc.getEta())
    {
      ret = -1;
    }
    else if (this.eta > sc.getEta())
    {
      ret = 1;
    }
    return ret;
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
    Scheda other = (Scheda) obj;
    if (this.cognome == null)
    {
      if (other.cognome != null)
      {
        return false;
      }
    }
    else if (!this.cognome.equals(other.cognome))
    {
      return false;
    }
    if (this.eta == null)
    {
      if (other.eta != null)
      {
        return false;
      }
    }
    else if (!this.eta.equals(other.eta))
    {
      return false;
    }
    if (this.nome == null)
    {
      if (other.nome != null)
      {
        return false;
      }
    }
    else if (!this.nome.equals(other.nome))
    {
      return false;
    }
    return true;
  }

  public String getCognome()
  {
    return this.cognome;
  }

  public Integer getEta()
  {
    return this.eta;
  }

  public String getNome()
  {
    return this.nome;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.cognome == null) ? 0 : this.cognome.hashCode());
    result = (prime * result) + ((this.eta == null) ? 0 : this.eta.hashCode());
    result = (prime * result) + ((this.nome == null) ? 0 : this.nome.hashCode());
    return result;
  }

  public void setCognome(String cognome)
  {
    this.cognome = cognome;
  }

  public void setEta(Integer eta)
  {
    this.eta = eta;
  }

  public void setNome(String nome)
  {
    this.nome = nome;
  }

  @Override
  public String toString()
  {
    return "Scheda [nome=" + this.nome + ", cognome=" + this.cognome + ", eta=" + this.eta + "]";
  }

}
