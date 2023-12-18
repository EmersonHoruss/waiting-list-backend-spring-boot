package com.ownprojects.backend.utils.translator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Translator {
    private List<Idiom> values = Arrays.asList(
            new Idiom("appointment","cita"),
            new Idiom("service","servicio"),
            new Idiom("state","estado"),
            new Idiom("insurance","asegurado"),
            new Idiom("code","código"),
            new Idiom("kind","tipo"),
            new Idiom("patient","paciente"),
            new Idiom("document","documento"),
            new Idiom("name","nombre"),
            new Idiom("birthdate","fecha de nacimiento"),
            new Idiom("gender","género"),
            new Idiom("phone","teléfono"),
            new Idiom("cell","celular"),
            new Idiom("relationshipType","tipo de relación"),
            new Idiom("relationshipType","tipo de relación"),
            new Idiom("casAffiliation","asociación del CAS"),
            new Idiom("ubigeo","ubigeo"),
            new Idiom("user","usuario"),
            new Idiom("role","rol"),
            new Idiom("password","contraseña")
    );
    private String notFound = "Not found";
    public String getEs(String en){
        String returnedEs = this.notFound;
        Optional<Idiom> found = this.values
                .stream()
                .filter(value->value.getEn().equals(en))
                .findFirst();
        if(found.isPresent()){
            returnedEs = found.get().getEs();
        }
        return returnedEs;
    }
}
