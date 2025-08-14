package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Identificador único do animal
	private String nome;
	private String tipo;
	private String raca;
	private double peso;
	private Tutor tutor;
	

	// ✅ Construtor vazio necessário para o Hibernate
	public Animal() {
	}

	// Construtor com todos os dados
	public Animal(String nome, String cpf, String telefone, String t_email) {
		this.nome = nome;
		this.tipo = tipo;
		this.raca = raca;
		this.tutor = tutor;
		this.peso = peso;
	}

	// Construtor da classe com todos os atributos
	public Animal(String nome, String tipo, String raca, Tutor tutor, Double peso) {

		this.nome = nome;
		this.tipo = tipo;
		this.raca = raca;
		this.tutor = tutor;
		this.peso = peso;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", raca=" + raca + ", peso=" + peso
				+ ", tutor=" + tutor + "]";
	}
	
	
	

}
