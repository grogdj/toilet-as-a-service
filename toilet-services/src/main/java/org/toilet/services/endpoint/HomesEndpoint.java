package org.toilet.services.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.toilet.core.api.HomesService;
import org.toilet.core.exceptions.BusinessException;
import org.toilet.core.util.StringIdGenerator;

import org.toilet.model.Bathroom;
import org.toilet.model.Home;
import org.toilet.model.Person;

/**
 *
 * @author salaboy
 */
@Path("/homes")
@ApplicationScoped
public class HomesEndpoint {

    @Inject
    private HomesService homesService;

    @Context
    private SecurityContext context;

//    @Inject
//    private AlertServiceImpl alerts;
    @Context
    private UriInfo uriInfo;

    @Context
    private HttpServletResponse response;

    public HomesEndpoint() {

    }

    @PostConstruct
    public void init() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person("grogdj", 32, Person.Gender.MALE);
        String personId = StringIdGenerator.generateId();
        person.setId(personId);
        persons.add(person);

        Person person2 = new Person("grogdj2", 33, Person.Gender.MALE);
        String personId2 = StringIdGenerator.generateId();
        person2.setId(personId2);
        persons.add(person2);

        List<Bathroom> baths = new ArrayList<>();
        Bathroom bathroom = new Bathroom("my bath 1");
        String bathroomId = StringIdGenerator.generateId();
        bathroom.setId(bathroomId);
        baths.add(bathroom);

        Bathroom bathroom2 = new Bathroom("my bath 2");
        String bathroomId2 = StringIdGenerator.generateId();
        bathroom2.setId(bathroomId2);

        baths.add(bathroom2);

        Home home1 = new Home("my home", persons, baths);
        try {
            homesService.addHome(home1);
        } catch (BusinessException ex) {
            Logger.getLogger(HomesEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }

        Home home2 = new Home("my home 2", null, null);

        try {
            homesService.addHome(home2);
        } catch (BusinessException ex) {
            Logger.getLogger(HomesEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Produces("application/json")
    @Path("/")
    public List<Home> getAllHomes() throws BusinessException {
        return homesService.getAllHomes();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/")
    public String addHome(@NotNull Home home) throws BusinessException {
        return homesService.addHome(home);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public void deleteHome(@PathParam("id") String id) throws BusinessException {
        homesService.deleteHome(id);
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/")
    public void updateHome(@NotNull Home home) throws BusinessException {
        homesService.updateHome(home);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Home getHomeById(@PathParam("id") String id) throws BusinessException {
        Home home = homesService.getHomeById(id);
        home.setSelf(Link.fromUri(uriInfo.getAbsolutePath())
                .rel("self").type("GET").build());
        response.setHeader("Link", home.getSelf().toString() + ", " + 
                Link.fromUri(uriInfo.getBaseUriBuilder().path(HomesEndpoint.class).path(getClass(), "getAllBathrooms").build(id)).rel("bathrooms").type("GET").build() + ", " + 
                Link.fromUri(uriInfo.getBaseUriBuilder().path(HomesEndpoint.class).path(getClass(), "getAllPersons").build(id)).rel("persons").type("GET").build());
        return home;
        

    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{homeId}/baths")
    public String addBathroom(@PathParam("homeId") String homeId, @NotNull Bathroom bath) throws BusinessException {
        List<Bathroom> bathrooms = homesService.getHomeById(homeId).getBathrooms();
        String bathroomId = StringIdGenerator.generateId();
        bath.setId(bathroomId);
        bathrooms.add(bath);
        homesService.getHomeById(homeId).setBathrooms(bathrooms);
        return bathroomId;
    }

    @GET
    @Produces("application/json")
    @Path("/{homeId}/baths")
    public List<Bathroom> getAllBathrooms(@PathParam("homeId") String homeId) throws BusinessException {
        return homesService.getHomeById(homeId).getBathrooms();
    }

    @GET
    @Produces("application/json")
    @Path("/{homeId}/persons")
    public List<Person> getAllPersons(@PathParam("homeId") String homeId) throws BusinessException {
        return homesService.getHomeById(homeId).getPersons();
    }

    @GET
    @Produces("application/json")
    @Path("/{homeId}/baths/{bathId}")
    public Bathroom getBathroomById(@PathParam("homeId") String homeId, @PathParam("bathId") String bathId) throws BusinessException {
        List<Bathroom> bathrooms = homesService.getHomeById(homeId).getBathrooms();
        for (Bathroom b : bathrooms) {
            if (b.getId().equals(bathId)) {
                return b;
            }
        }
        return null;
    }

    @GET
    @Produces("application/json")
    @Path("/{homeId}/persons/{personId}")
    public Person getPersonById(@PathParam("homeId") String homeId, @PathParam("personId") String personId) throws BusinessException {
        List<Person> persons = homesService.getHomeById(homeId).getPersons();
        for (Person p : persons) {
            if (p.getId().equals(personId)) {
                return p;
            }
        }
        return null;
    }

}
