/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper.endpoint.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.toilet.paper.endpoint.exception.BusinessException;

/**
 *
 * @author salaboy
 */
public class AlertJobImpl implements Job {

    @Inject
    private HomesServiceImpl home;
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            System.out.println("Executing Job: "+home.getAllHomes());
        } catch (BusinessException ex) {
            Logger.getLogger(AlertJobImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
