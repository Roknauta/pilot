package com.roknauta.pilot.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component @RequestScope
public class InicioController implements Serializable {
    @Getter
    @Setter
    private String language;
    @Getter
    @Setter
    private List<Locale> locales = Arrays.asList(new Locale("pt","BR"),Locale.US);

    @PostConstruct
    public void init(){
        this.language = "pt_BR";
    }

    public void changeLanguage(){
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.language));
    }

}
