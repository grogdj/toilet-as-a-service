/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.core.impl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.scheduler.spi.Scheduler;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class AlertServiceImpl {

    @Inject
    private Scheduler<Job> jobScheduler;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        registerAlert();
    }

    public AlertServiceImpl() {
    }

    public Scheduler<Job> getJobScheduler() {
        return jobScheduler;
    }

    public void setJobScheduler(Scheduler<Job> jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    public void registerAlert() {
        //jobScheduler.registerNewJob(AlertJobImpl.class);
        JobDetail jobDetail = JobBuilder
                .newJob(AlertJobImpl.class)
                .build();
        org.quartz.Scheduler s = jobScheduler.unwrap(org.quartz.Scheduler.class);
        SimpleTrigger trigger = TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .startAt(new Date(System.currentTimeMillis() + 5000))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                .build();
        try {
            s.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(AlertServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
