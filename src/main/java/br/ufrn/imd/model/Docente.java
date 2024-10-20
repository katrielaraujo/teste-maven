package br.ufrn.imd.model;

public class Docente {
	private String nome;
	
	private Integer siape;

	

	public Docente(String nome, Integer siape) {
		this.nome = nome;
		this.siape = siape;
	}

	public Docente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSiape() {
		return siape;
	}

	public void setSiape(Integer siape) {
		this.siape = siape;
	} 
}
