package br.ufrn.imd.model;

import java.math.BigDecimal;

public class Matricula {
	private final Discente discente;
	
	private final Turma turma;
	
	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private Integer frequencia;
	
	private StatusAprovacao status;

	public Matricula(Discente discente, Turma turma) {
		this.discente = discente;
		this.turma = turma;
	}

	public BigDecimal getNota1() {
		return nota1;
	}

	public void cadastrarNota1(BigDecimal nota1) {
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void cadastrarNota2(BigDecimal nota2) {
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void cadastrarNota3(BigDecimal nota3) {
		this.nota3 = nota3;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void cadastrarFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	public Discente getDiscente() {
		return discente;
	}

	public Turma getTurma() {
		return turma;
	}

	public boolean compareMedia(int n){
		BigDecimal media = calcularMediaParcial();
		return media.compareTo(BigDecimal.valueOf(n)) == 1 || media.compareTo(BigDecimal.valueOf(n)) == 0;
	}

	public boolean notasPorUnidade(int n){
		boolean unit01 = nota1.compareTo(BigDecimal.valueOf(n)) == 1 || nota1.compareTo(BigDecimal.valueOf(n)) == 0; 
		boolean unit02 = nota2.compareTo(BigDecimal.valueOf(n)) == 1 || nota2.compareTo(BigDecimal.valueOf(n)) == 0; 
		boolean unit03 = nota3.compareTo(BigDecimal.valueOf(n)) == 1 || nota3.compareTo(BigDecimal.valueOf(n)) == 0;

		return unit01 && unit02 && unit03;
	}

	public void consolidarParcialmente() {
		boolean mediaAprovacao =  compareMedia(6);
		boolean unidadeAprovacao = notasPorUnidade(4);
		boolean frenquenciaNecessaria = frequencia >= 75;

		boolean mediaReposicao = compareMedia(3);

		boolean mediaReprovacao = mediaAprovacao && mediaReposicao;

		if(mediaAprovacao && unidadeAprovacao && frenquenciaNecessaria){
			this.setStatus(StatusAprovacao.APR);
		}else if(mediaReposicao && frenquenciaNecessaria){
			this.setStatus(StatusAprovacao.REC);
		}else{
			if(!mediaReprovacao && frenquenciaNecessaria){
				this.setStatus(StatusAprovacao.REP);
			}else if(mediaAprovacao && !frenquenciaNecessaria){
				this.setStatus(StatusAprovacao.REPF);
			}else{
				this.setStatus(StatusAprovacao.REPMF);
			}
		}
	}

	public StatusAprovacao getStatus() {
		return status;
	}

	private void setStatus(StatusAprovacao status) {
		this.status = status;
	}

	public BigDecimal calcularMediaParcial(){
		BigDecimal mediaParcial = nota1.add(nota2).add(nota3);
		return mediaParcial.divide(BigDecimal.valueOf(3L));
	}
}