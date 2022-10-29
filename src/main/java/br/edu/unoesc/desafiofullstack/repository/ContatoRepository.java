package br.edu.unoesc.desafiofullstack.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.unoesc.desafiofullstack.models.Contato;
import br.edu.unoesc.desafiofullstack.models.Pessoa;

public interface ContatoRepository extends CrudRepository<Contato, String>{
	Iterable<Contato> findByPessoa(Pessoa pessoa);
}