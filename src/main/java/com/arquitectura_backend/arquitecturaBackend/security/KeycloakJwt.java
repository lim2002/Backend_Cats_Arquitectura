package com.arquitectura_backend.arquitecturaBackend.security;

import jakarta.persistence.Convert;
import org.springframework.core.convert.converter.Converter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeycloakJwt implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final TokenConvert properties;

    public KeycloakJwt(
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter,
            TokenConvert properties) {
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat (
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect (Collectors.toSet ()) ;
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName (jwt));
    }

    private String getPrincipalClaimName (Jwt jwt) {
        String principalAttribute = properties.getPrincipalAttribute();
        return principalAttribute != null ? jwt.getClaimAsString(principalAttribute) : jwt.getClaimAsString(JwtClaimNames.SUB);
    }


    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        // Verificar si resourceAccess es nulo
        if (resourceAccess == null) {
            return Collections.emptyList(); // Devolver una lista vacía si no hay acceso a recursos
        }

        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId());

        // Verificar si resource es nulo
        if (resource == null) {
            return Collections.emptyList(); // Devolver una lista vacía si no hay recurso
        }

        Collection<String> roles = (Collection<String>) resource.get("roles");

        // Verificar si roles es nulo
        if (roles == null) {
            return Collections.emptyList(); // Devolver una lista vacía si no hay roles
        }

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}


