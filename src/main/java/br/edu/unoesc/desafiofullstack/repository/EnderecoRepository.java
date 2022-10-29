package br.edu.unoesc.desafiofullstack.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.unoesc.desafiofullstack.models.Endereco;
import br.edu.unoesc.desafiofullstack.models.Pessoa;

public interface EnderecoRepository extends CrudRepository<Endereco, String>{
	Iterable<Endereco> findByPessoa(Pessoa pessoa);
}