import java.io.File;

public class Nodo {
	File Nodo;
	String nome;
	File[]  figli;
	Centro centro;


	public Centro getCentro() {
		return this.centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	public File getNodo() {
		return this.Nodo;
	}
	public void setNodo(File nodo) {
		this.Nodo = nodo;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public File[] getFigli() {
		return this.figli;
	}
	public void setFigli(File[] figli) {
		this.figli = figli;
	}

}
