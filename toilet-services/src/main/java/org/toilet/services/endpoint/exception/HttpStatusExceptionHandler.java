/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.services.endpoint.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.toilet.core.exceptions.BusinessException;

/**
 *
 * @author salaboy
 */
@Provider
public class HttpStatusExceptionHandler implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException exception)
    {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error: Business Exception "+exception.getMessage()).build(); 
    }
}
