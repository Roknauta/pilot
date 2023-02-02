package com.roknauta.pilot.controller;

import com.roknauta.pilot.service.PessoaService;
import com.roknauta.pilot.util.JsfUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@Component
@RequestScope public class PessoaController implements Serializable {

    @Setter @Getter private String nome;

    @Autowired
    private PessoaService pessoaService;

    public void salvar() {
        pessoaService.salvar(nome);
        JsfUtils.addInfoMessage("entity.saved");
    }
}
