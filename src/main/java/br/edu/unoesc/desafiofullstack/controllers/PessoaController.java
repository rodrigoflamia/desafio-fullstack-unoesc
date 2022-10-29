package br.edu.unoesc.desafiofullstack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import br.edu.unoesc.desafiofullstack.repository.*;
import br.edu.unoesc.desafiofullstack.models.*;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pr;
	@Autowired
	private ContatoRepository cr;
	@Autowired
	private EnderecoRepository er;
	
	@RequestMapping("/pessoas")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Pessoa> pessoas = pr.findAll();
		mv.addObject("pessoas", pessoas);
		return mv;
	}
	
	//GET e POST  Cadastro de Pessoas
	@RequestMapping(value = "/cadastro-pessoa", method = RequestMethod.GET)
	public String form() {
		return "pessoa/formPessoa";
	}
	
	@RequestMapping(value = "/cadastro-pessoa", method = RequestMethod.POST)
	public String form(Pessoa pessoa) {
		pr.save(pessoa);
		
		return "redirect:/pessoas";
	}	
	
	//GET e POST  Cadastro de Contatos
	@RequestMapping(value = "/contatos/{codigo}", method = RequestMethod.GET)
	public ModelAndView contatosPessoa(@PathVariable("codigo") Long codigo) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("pessoa/contatos");
		mv.addObject("pessoa", pessoa);
		
		Iterable<Contato> contatos = cr.findByPessoa(pessoa);
		mv.addObject("contatos", contatos);
		return mv;
	}
	
	@RequestMapping(value = "/contatos/{codigo}", method = RequestMethod.POST)
	public String contatosPessoa(@PathVariable("codigo") Long codigo, Contato contato) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		contato.setPessoa(pessoa);
		cr.save(contato);
		
		return "redirect:/contatos/{codigo}";
	}
	
	//GET e POST  Cadastro de Endereços
	@RequestMapping(value = "/enderecos/{codigo}", method = RequestMethod.GET)
	public ModelAndView enderecosPessoa(@PathVariable("codigo") Long codigo) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("pessoa/enderecos");
		mv.addObject("pessoa", pessoa);
		
		Iterable<Endereco> enderecos = er.findByPessoa(pessoa);
		mv.addObject("enderecos", enderecos);
		return mv;
	}
	
	@RequestMapping(value = "/enderecos/{codigo}", method = RequestMethod.POST)
	public String enderecosPessoa(@PathVariable("codigo") Long codigo, Endereco endereco) {
		Pessoa pessoa = pr.findByCodigo(codigo);
		endereco.setPessoa(pessoa);
		er.save(endereco);
		
		return "redirect:/enderecos/{codigo}";
	}
}