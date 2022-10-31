package br.edu.unoesc.desafiofullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.desafiofullstack.models.Contato;
import br.edu.unoesc.desafiofullstack.models.Endereco;
import br.edu.unoesc.desafiofullstack.models.Pessoa;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
	@Query("select e from Endereco e where e.pessoa.codigo =?1")
	List<Endereco> findEnderecosByPessoa(Long codigoPessoa) ;
}