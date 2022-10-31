package br.edu.unoesc.desafiofullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.desafiofullstack.models.Contato;
import br.edu.unoesc.desafiofullstack.models.Pessoa;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Long>{
	@Query("select c from Contato c where c.pessoa.codigo =?1")
	List<Contato> findContatosByPessoa(Long codigoPessoa) ;
}