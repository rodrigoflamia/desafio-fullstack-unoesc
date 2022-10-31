package br.edu.unoesc.desafiofullstack.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import org.springframework.validation.FieldError;

import br.edu.unoesc.desafiofullstack.repository.*;
import br.edu.unoesc.desafiofullstack.models.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pr;
	@Autowired
	private ContatoRepository cr;
	@Autowired
	private EnderecoRepository er;
	
	@GetMapping("/index")
    public String listPessoa(Model model) {
        model.addAttribute("pessoas", pr.findAll());
        return "index";
    }
	
	//Cadastro de Pessoas
	@GetMapping("/cadastrar-pessoa")
    public String formPessoa(Pessoa pessoa) {
        return "pessoa/formPessoa";
    }
	
    @PostMapping("/cadastrar-pessoa")
    public String addPessoa(@Valid Pessoa pessoa, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	return "pessoa/formPessoa";
        }
        
        pr.save(pessoa);
        return "redirect:/index";
    }
    
    @GetMapping("/editar-pessoa/{codigo}")
    public String formUpdatePessoa(@PathVariable("codigo") Long codigo, Model model) {
        Pessoa pessoa = pr.findById(codigo)
          .orElseThrow(() -> new IllegalArgumentException("Código inválido:" + codigo));
        
        model.addAttribute("pessoa", pessoa);
        return "pessoa/updatePessoa";
    }
    
    @PostMapping("/update-pessoa/{codigo}")
    public String updatePessoa(@PathVariable("codigo") Long codigo, @Valid Pessoa pessoa, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            pessoa.setCodigo(null);
            return "pessoa/updatePessoa";
        }
          
        pr.save(pessoa);
        return "redirect:/index";
    }
        
    @GetMapping("/excluir-pessoa/{codigo}")
    public String deleteUser(@PathVariable("codigo") Long codigo, Model model) {
        Pessoa pessoa = pr.findById(codigo)
          .orElseThrow(() -> new IllegalArgumentException("Código inválido:" + codigo));
        
        pr.delete(pessoa);
        return "redirect:/index";
    }
    
    //Cadastro de Contatos
    @GetMapping("/cadastrar-contato/{codigo}")
    public String formContato(@PathVariable("codigo") Long codigo, Model model) {
    	model.addAttribute("contatos", cr.findContatosByPessoa(codigo));
        return "pessoa/formContato";
    }
	
    @PostMapping("/cadastrar-contato/{codigo}")
    public String addContato(@PathVariable("codigo") Long codigo, @Valid Contato contato, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	return "pessoa/formContato";
        }
        
        Pessoa pessoa = pr.findById(codigo).get();
		contato.setPessoa(pessoa);        
		cr.save(contato);
        return "redirect:/index";
    }
    
    //
    @GetMapping("/editar-contato/{codigo}")
    public String formUpdateContato(@PathVariable("codigo") Long codigo, Model model) {
        Contato contato = cr.findById(codigo)
          .orElseThrow(() -> new IllegalArgumentException("Código inválido:" + codigo));
        
        model.addAttribute("contato", contato);
        return "pessoa/updateContato";
    }
    
    @PostMapping("/update-contato/{codigo}")
    public String updateContato(@PathVariable("codigo") Long codigo, @Valid Contato contato, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	contato.setCodigoContato(null);
            return "pessoa/updateContato";
        }
            
        cr.save(contato);
        return "redirect:/index";
    }
        
    @GetMapping("/excluir-contato/{codigo}")
    public String deleteContato(@PathVariable("codigo") Long codigo, Model model) {
    	Contato contato = cr.findById(codigo)
          .orElseThrow(() -> new IllegalArgumentException("Código inválido:" + codigo));
        cr.delete(contato);
        return "redirect:/index";
    }
    
    //Cadastro de Endereços
    @GetMapping("/cadastrar-endereco/{codigo}")
    public String formEndereco(@PathVariable("codigo") Long codigo, Endereco endereco, Model model) {
    	model.addAttribute("enderecos", er.findEnderecosByPessoa(codigo));
        return "pessoa/formEndereco";
    }
	
    @PostMapping("/cadastrar-endereco/{codigo}")
    public String addEndereco(@PathVariable("codigo") Long codigo, @Valid Endereco endereco, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
        	return "pessoa/formEndereco";
        }
        
        /*
        //API ViaCep
        URL url = new URL("https://viacep.com.br/ws/"+endereco.getCep()+"/json/");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        
        String cep = "";
        StringBuilder jsonCep = new StringBuilder();
        
        while((cep = br.readLine()) != null) {
        	jsonCep.append(cep);
        }
        
        Endereco auxEndereco = new Gson().fromJson(jsonCep.toString(), Endereco.class);
        endereco.setCep(auxEndereco.getCep());
        endereco.setLogradouro(auxEndereco.getLogradouro());
        endereco.setBairro(auxEndereco.getBairro());
        endereco.setMunicipio(auxEndereco.getMunicipio());
        endereco.setEstado(auxEndereco.getEstado());
        //API ViaCep
        */
        
        Pessoa pessoa = pr.findById(codigo).get();
		endereco.setPessoa(pessoa);
		er.save(endereco);
        return "redirect:/index";
    }
    
    //
    @GetMapping("/editar-endereco/{codigo}")
    public String formUpdateEndereco(@PathVariable("codigo") Long codigo, Model model) {
        Endereco endereco = er.findById(codigo)
          .orElseThrow(() -> new IllegalArgumentException("Código inválido:" + codigo));
        
        model.addAttribute("endereco", endereco);
        return "pessoa/updateEndereco";
    }
    
    @PostMapping("/update-endereco/{codigo}")
    public String updateContato(@PathVariable("codigo") Long codigo, @Valid Endereco endereco, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
        	endereco.setCodigoEndereco(null);
            return "pessoa/updateEndereco";
        }
            
        er.save(endereco);
        return "redirect:/index";
    }
        
    @GetMapping("/excluir-endereco/{codigo}")
    public String deleteEndereco(@PathVariable("codigo") Long codigo, Model model) {
    	Endereco endereco = er.findById(codigo)
          .orElseThrow(() -> new IllegalArgumentException("Código inválido:" + codigo));
        er.delete(endereco);
        return "redirect:/index";
    }

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}