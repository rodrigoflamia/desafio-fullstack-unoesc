package br.edu.unoesc.desafiofullstack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.desafiofullstack.models.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
}
