package br.edu.unoesc.desafiofullstack.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private String CPF;
	private Date dataNascimento;
	private String sexo;
	
	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos;
	
	@OneToMany(mappedBy = "pessoa")
	private List<Contato> contatos;

	//Getters and Setters
	public Long getCodigoPessoa() {
		return codigo;
	}
	public void setCodigoPessoa(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}	
}