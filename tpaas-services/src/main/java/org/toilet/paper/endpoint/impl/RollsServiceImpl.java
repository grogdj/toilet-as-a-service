package org.toilet.paper.endpoint.impl;


import org.toilet.paper.endpoint.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


import org.keycloak.KeycloakPrincipal;
import org.toilet.paper.endpoint.api.RollsService;
import org.toilet.paper.model.Roll;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class RollsServiceImpl implements RollsService {

    private List<Roll> items = new ArrayList<Roll>();

    @Context
    SecurityContext context;

    public RollsServiceImpl() {
    }

    

    @Override
    public List<Roll> getAllRolls() throws BusinessException {
        KeycloakPrincipal principal = (KeycloakPrincipal) context.getUserPrincipal();
        if (principal != null && principal.getKeycloakSecurityContext() != null) {
            return items;
        } else {
            throw new BusinessException("You don't have the appropriate permession to access this service");
        }
    }

   
    

}
