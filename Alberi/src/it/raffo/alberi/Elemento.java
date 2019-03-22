package it.raffo.alberi;

import java.io.File;

public class Elemento implements Comparable<Elemento>
{
	private File	file;
	private String	nome;
	private String	type;
	private long	size;

	public Elemento(String nome, String type, long size, File file)
	{
		super();
		this.nome = nome;
		this.type = type;
		this.size = size;
		this.file = file;
	}

	@Override
	public int compareTo(Elemento o)
	{
		// TODO Auto-generated method stub
		return 0;
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
		Elemento other = (Elemento) obj;
		if (this.file == null)
		{
			if (other.file != null)
			{
				return false;
			}
		}
		else if (!this.file.equals(other.file))
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
		if (this.size != other.size)
		{
			return false;
		}
		if (this.type == null)
		{
			if (other.type != null)
			{
				return false;
			}
		}
		else if (!this.type.equals(other.type))
		{
			return false;
		}
		return true;
	}

	public File getFile()
	{
		return this.file;
	}

	public String getNome()
	{
		return this.nome;
	}

	public long getSize()
	{
		return this.size;
	}

	public String getType()
	{
		return this.type;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.file == null) ? 0 : this.file.hashCode());
		result = (prime * result) + ((this.nome == null) ? 0 : this.nome.hashCode());
		result = (prime * result) + (int) (this.size ^ (this.size >>> 32));
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Elemento [nome=" + this.nome + ", type=" + this.type + ", size=" + this.size + "]";
	}

}
