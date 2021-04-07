package com.roknauta.pilot.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

public class JsfUtils {

    private static final ResourceBundle rb = ResourceBundle.getBundle("messages", getFacesContext().getViewRoot().getLocale());

    static public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    static public void addMessage(final String clientId, final FacesMessage.Severity severity, final String message) {
        getFacesContext().addMessage(clientId, new FacesMessage(severity, message, null));
    }

    static public void addMessage(final FacesMessage.Severity severity, final String message) {
        addMessage(null, severity, message);
    }

    static public void addInfoMessage(final String key) {
        addMessage(FacesMessage.SEVERITY_INFO, rb.getString(key));
    }

}
