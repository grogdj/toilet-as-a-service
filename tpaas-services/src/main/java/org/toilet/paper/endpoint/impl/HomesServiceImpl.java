package org.toilet.paper.endpoint.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.toilet.paper.endpoint.api.HomesService;
import org.toilet.paper.endpoint.exception.BusinessException;
import org.toilet.paper.endpoint.util.StringIdGenerator;
import org.toilet.paper.model.Bathroom;
import org.toilet.paper.model.Home;
import org.toilet.paper.model.Person;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class HomesServiceImpl implements HomesService {

    private Map<String, Home> homes = new HashMap<String, Home>();

    @Context
    SecurityContext context;
    
    @Inject
    private AlertServiceImpl alerts;

    public HomesServiceImpl() {
        
        List<Person> persons = new ArrayList<>();
        Person person = new Person("grogdj", 32, Person.Gender.MALE);
        String personId = StringIdGenerator.generateId();
        person.setId(personId);
        persons.add(person);
        List<Bathroom> baths = new ArrayList<>();
        Bathroom bathroom = new Bathroom("my bath");
        String bathroomId = StringIdGenerator.generateId();
        bathroom.setId(bathroomId);
        baths.add(bathroom);
        String id = StringIdGenerator.generateId();
        homes.put(id, new Home(id, "my home", persons, baths));
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
    public String addBathroom(String homeId, Bathroom bath) throws BusinessException {
        String bathId = StringIdGenerator.generateId();
        bath.setId(bathId);
        homes.get(homeId).getBathrooms().add(bath);
        return bathId;
    }

    @Override
    public String addPerson(String homeId, Person person) throws BusinessException {
        String personId = StringIdGenerator.generateId();
        person.setId(personId);
        homes.get(homeId).getPersons().add(person);
        return personId;
    }

    @Override
    public void registerAlert() throws BusinessException {
        alerts.registerAlert();
    }
    
    

}
