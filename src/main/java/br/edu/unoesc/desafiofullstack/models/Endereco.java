package br.edu.unoesc.desafiofullstack.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa", referencedColumnName = "codigo")
	private Pessoa pessoa;
	
	@NotBlank(message = "CEP não informado!")
	private String cep;
	
	@NotBlank(message = "Logradouro não informado!")
	private String logradouro;
	
	@NotBlank(message = "Numero não informado!")
	private String numero;
	
	@NotBlank(message = "Bairro não informado!")
	private String bairro;
	
	@NotBlank(message = "Municipio não informado!")
	private String municipio;
	
	@NotBlank(message = "Estado não informado!")
	private String estado;
	
	//Getters and Setters
	public Long getCodigoEndereco() {
		return codigo;
	}
	public void setCodigoEndereco(Long codigo) {
		this.codigo = codigo;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
}