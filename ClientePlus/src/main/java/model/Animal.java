package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String Sexo;
	private String raca;
	private String idade;
	private String observacoes;
	
	public Animal() {}
	
	public Animal(String nome, String sexo, String raca, String idade, String observacoes) {
		this.idade = idade;
		this.nome = nome;
		this.observacoes = observacoes;
		this.Sexo = sexo;
		this.raca = raca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nome=" + nome + ", Sexo=" + Sexo + ", raca=" + raca + ", idade=" + idade
				+ ", observacoes=" + observacoes + "]";
	}
	
	
}
