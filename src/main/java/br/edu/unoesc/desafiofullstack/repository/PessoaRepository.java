package br.edu.unoesc.desafiofullstack.repository;

import org.springframework.data.repository.CrudRepository;
import br.edu.unoesc.desafiofullstack.models.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String>{
	Pessoa findByCodigo(Long codigo);
}
