package com.roknauta.pilot.repository;

import com.roknauta.pilot.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa,Long> {

}
