package org.toilet.core.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.toilet.core.api.RollsService;
import org.toilet.core.exceptions.BusinessException;


import org.toilet.model.Roll;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class RollsServiceImpl implements RollsService {

    private List<Roll> items = new ArrayList<Roll>();

    public RollsServiceImpl() {
        items.add(new Roll("firstRoll", new Date(), Roll.Type.BIG));
    }

    

    @Override
    public List<Roll> getAllRolls() throws BusinessException {
//        KeycloakPrincipal principal = (KeycloakPrincipal) context.getUserPrincipal();
//        if (principal != null && principal.getKeycloakSecurityContext() != null) {
            return items;
//        } else {
//            throw new BusinessException("You don't have the appropriate permession to access this service");
//        }
    }

    @Override
    public void addRoll(Roll roll) throws BusinessException {
        items.add(roll);
    }
    
    

   
    

}
