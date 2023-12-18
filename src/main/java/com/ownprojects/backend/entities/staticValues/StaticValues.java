package com.ownprojects.backend.entities.staticValues;

import com.ownprojects.backend.exceptions.NotFoundStaticValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StaticValues {
    private List<String> APPOINTMENT_STATES = Arrays.asList("Pendiente", "Atendido");
    private List<String> GENDERS = Arrays.asList("M", "F");
    private List<String> RELATIONSHIP_TYPES = Arrays.asList("Título", "Cónyugue");
    private List<String> ROLES = Arrays.asList("Administrador", "Digitador");
    private List<String> SERVICES = Arrays.asList("Cardiología", "Ginecología", "Medicina General", "Medicina Interna", "Obstetricia", "Oftalmología", "Otorrino", "Pediatría", "Geriatría", "Cirugía");

    public void validate(EStaticValues staticValueName, String value) {
        List<String> values = new ArrayList<>();
        switch (staticValueName){
            case APPOINTMENT_STATES: values=APPOINTMENT_STATES; break;
            case GENDERS: values = GENDERS; break;
            case RELATIONSHIP_TYPES: values = RELATIONSHIP_TYPES; break;
            case ROLES: values = ROLES; break;
            case SERVICES: values = SERVICES; break;
        }
        if(!belongs(value, values)){
            String errorMessage = "";
            switch (staticValueName){
                case APPOINTMENT_STATES: errorMessage = getAppointStateMessageError(); break;
                case GENDERS: errorMessage = getGendersMessageError(); break;
                case RELATIONSHIP_TYPES: errorMessage = getRelationshipTypesMessageError(); break;
                case ROLES: errorMessage = getRolesMessageError(); break;
                case SERVICES: errorMessage = getServicesMessageError(); break;
            }
            throw new NotFoundStaticValue(errorMessage);
        }
    }

    private boolean belongs(String value, List<String> array) {
        return array.stream().anyMatch(currentValue -> currentValue.equals(value));
    }
    private String getAppointStateMessageError(){
        String valuesAsString = getValuesAsString(APPOINTMENT_STATES);
        return "Los estados de las citas sólo son estos: " + valuesAsString;
    }

    private String getValuesAsString(List<String> array){
        final StringBuilder values = new StringBuilder();
        IntStream.range(0,array.size()).forEach(index->{
            String value = array.get(index);
            if((index+1)!=array.size()){
                value+=", ";
            }
            values.append(value);
        });
        return values.toString();
    }

    private String getGendersMessageError(){
        String valuesAsString = getValuesAsString(GENDERS);
        return "Los géneros sólo son estos: " + valuesAsString;
    }

    private String getRelationshipTypesMessageError(){
        String valuesAsString = getValuesAsString(RELATIONSHIP_TYPES);
        return "Los tipos de relaciones sólo son estos: " + valuesAsString;
    }

    private String getRolesMessageError(){
        String valuesAsString = getValuesAsString(ROLES);
        return "Los roles sólo son estos: " + valuesAsString;
    }

    private String getServicesMessageError(){
        String valuesAsString = getValuesAsString(SERVICES);
        return "Los servicios sólo son estos: " + valuesAsString;
    }
}
