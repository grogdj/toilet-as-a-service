package org.toilet.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.toilet.core.api.HomesService;
import org.toilet.core.exceptions.BusinessException;
import org.toilet.core.util.StringIdGenerator;

import org.toilet.model.Home;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class HomesServiceImpl implements HomesService {

    private Map<String, Home> homes = new HashMap<String, Home>();

    @Inject
    private AlertServiceImpl alerts;

    public HomesServiceImpl() {

    }

    @Override
    public List<Home> getAllHomes() throws BusinessException {
        return new ArrayList<>(homes.values());
    }

    @Override
    public String addHome(Home home) throws BusinessException {
        String id = StringIdGenerator.generateId();
        home.setId(id);
        homes.put(id, home);

        return id;
    }

    @Override
    public void deleteHome(String id) throws BusinessException {
        homes.remove(id);
    }

    @Override
    public void updateHome(Home home) throws BusinessException {
        homes.remove(home.getId());
        homes.put(home.getId(), home);
    }

//    @Override
//    public void registerAlert() throws BusinessException {
//        alerts.registerAlert();
//    }
    @Override
    public Home getHomeById(String id) throws BusinessException {
        return homes.get(id);
    }

}
