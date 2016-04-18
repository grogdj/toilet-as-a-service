/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.services;





import org.jboss.shrinkwrap.api.ShrinkWrap;
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

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.as(Secured.class);
// 
//        // Enable the swagger bits
//        SwaggerArchive archive = deployment.as(SwaggerArchive.class);
//        // Tell swagger where our resources are
//        archive.setResourcePackages("org.toilet.paper.endpoint");
//        archive.setTitle("Toilet Service");
        
        
        deployment.setContextRoot("/api");
        deployment.addAsLibrary(container.createDefaultDeployment());
        deployment.addAllDependencies();
        container.start();
        container.deploy(deployment);
        
    }
}
