package com.yog.transaction.ejbs.beans;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduleManager {
    private ScheduledExecutorService scheduler;
    private Logger logger = Logger.getLogger(getClass().getName());

    @PostConstruct
    public void init() {
	logger.info("Startingcheduler.");
	scheduler = Executors.newScheduledThreadPool(3);
    }

    @PreDestroy
    public void cleanup() {
	logger.info("Shutting scheduler.");
	scheduler.shutdown();
    }

    public void schedule(Runnable r, int secondsLater) {
	scheduler.schedule(r, secondsLater, TimeUnit.SECONDS);
    }
}