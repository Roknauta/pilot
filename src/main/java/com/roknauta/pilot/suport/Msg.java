package com.roknauta.pilot.suport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.text.MessageFormat;
import java.util.HashMap;

@ApplicationScope // qdo for multilíngua tem que rever
@Component
/*
 * Resolve referências internas de properties e cacheia todos os properties.
 */
public class Msg extends HashMap<String, String> {

    @Autowired
    @Lazy
    private MessageSource messageSource;

    private static final String START_REFERENCE = "${";
    private static final String END_REFERENCE = "}";

    /*
     * obtém o valor da chave, priorizando o properties da configuração.
     */
    private String _internalGet(final String key) {
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return key;
        }

    }

    private String _get(final String key) {

        return _get(key, "");
    }

    private String _get(final String key, String cacheKey) {

        if (super.containsKey(cacheKey)) {
            // se a chave já está cacheada, retorna
            return super.get(cacheKey);
        } else {
            // substitui as referências internas ${}
            String tmpKey = key;
            String message = _internalGet(tmpKey);
            // procura referência ${} e substitui.
            do {
                tmpKey = StringUtils.substringBetween(message, START_REFERENCE, END_REFERENCE);
                if (tmpKey == null) {
                    break;
                }
                message = message.replace(START_REFERENCE + tmpKey + END_REFERENCE, _internalGet(tmpKey));
            } while (true);

            // coloca a chave e valor no cache
            super.put(cacheKey, message);

            return message;
        }
    }

    @Override
    public String get(Object key) {
        return this._get((String) key);
    }

    public String get(String key, Object... params) {
        final MessageFormat format = new MessageFormat(this._get(key));
        return format.format(params);
    }
}
