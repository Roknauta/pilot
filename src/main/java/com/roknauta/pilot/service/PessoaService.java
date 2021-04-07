package com.roknauta.pilot.service;

import com.roknauta.pilot.model.Pessoa;
import com.roknauta.pilot.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void salvar(String nome){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoaRepository.save(pessoa);
    }

}
