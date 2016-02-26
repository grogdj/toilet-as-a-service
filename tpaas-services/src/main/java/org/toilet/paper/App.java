/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper;





import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.toilet.paper.endpoint.api.HomesService;
import org.toilet.paper.endpoint.api.RollsService;
import org.toilet.paper.endpoint.config.AuthRESTResponseFilter;
import org.toilet.paper.endpoint.exception.BusinessException;
import org.toilet.paper.endpoint.exception.HttpStatusExceptionHandler;
import org.toilet.paper.endpoint.impl.HomesServiceImpl;
import org.toilet.paper.endpoint.impl.RollsServiceImpl;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.keycloak.Secured;

/**
 *
 * @author salaboy
 */
public class App {

    public static void main(String[] args) throws Exception {
        Container container = new Container();

        container.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.as(Secured.class);
        deployment.setContextRoot("/api");
        deployment.addPackage("org.toilet.paper.model");
        deployment.addPackages(true, "org.toilet.paper.endpoint");
        deployment.addResource(RollsService.class);
        deployment.addResource(RollsServiceImpl.class);
        deployment.addResource(HomesService.class);
        deployment.addResource(HomesServiceImpl.class);
        deployment.addClass(HttpStatusExceptionHandler.class);
        deployment.addClass(BusinessException.class);
        deployment.addClass(AuthRESTResponseFilter.class);
        deployment.addAllDependencies();
        container.deploy(deployment);
    }
}
