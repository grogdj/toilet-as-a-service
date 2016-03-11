/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper;





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
        deployment.setContextRoot("/api");
        deployment.addAsLibrary(container.createDefaultDeployment());
        deployment.addAllDependencies();
        container.start();
        container.deploy(deployment);
        
    }
}
