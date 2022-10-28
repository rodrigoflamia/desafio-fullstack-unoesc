package br.edu.unoesc.desafiofullstack.models;

import javax.persistence.*;


@Entity
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa", referencedColumnName = "codigo")
	private Pessoa pessoa;
	private String telefone;
	private String email;
	
	//Getters and Setters
	public Long getCodigoContato() {
		return codigo;
	}
	public void setCodigoContato(Long codigo) {
		this.codigo = codigo;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}