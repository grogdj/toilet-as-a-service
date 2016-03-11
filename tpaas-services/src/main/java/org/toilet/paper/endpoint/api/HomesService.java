/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper.endpoint.api;

import org.toilet.paper.endpoint.exception.BusinessException;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.toilet.paper.model.Bathroom;
import org.toilet.paper.model.Home;
import org.toilet.paper.model.Person;


/**
 *
 * @author salaboy
 */
@Path("homes")
public interface HomesService {

    @GET
    @Produces("application/json")
    @Path("")
    public List<Home> getAllHomes() throws BusinessException;
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("")
    public String addHome(@NotNull Home home) throws BusinessException;


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}/bath")
    public String addBathroom(@PathParam("id") String id, @NotNull Bathroom bath) throws BusinessException;
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}/person")
    public String addPerson(@PathParam("id") String id, @NotNull Person person) throws BusinessException;
    
    @POST
    @Path("alert")
    public void registerAlert() throws BusinessException;
    
}
