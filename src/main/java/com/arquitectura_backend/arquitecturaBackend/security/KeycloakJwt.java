package com.arquitectura_backend.arquitecturaBackend.security;

import jakarta.persistence.Convert;
import org.springframework.core.convert.converter.Converter;



import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeycloakJwt {


    private final TokenConvert properties;

    public KeycloakJwt(
            TokenConvert properties) {
        this.properties = properties;
    }

    public KeycloakJwt() {
        this.properties = new TokenConvert();
    }

    public KeycloakJwt(String resourceId, String principalAttribute) {
        this.properties = new TokenConvert();
        this.properties.setResourceId(resourceId);
        this.properties.setPrincipalAttribute(principalAttribute);
    }


    public String getResourceId() {
        return properties.getResourceId();
    }

    public String getPrincipalAttribute() {
        return properties.getPrincipalAttribute();
    }

    public String getPrincipalName() {
        return properties.getPrincipalAttribute();
    }

    public String getPrincipalName(String principalName) {
        return properties.getPrincipalAttribute();
    }

    public String getPrincipalName(String principalName, String principalName2) {
        return properties.getPrincipalAttribute();
    }
}


