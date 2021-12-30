package com.mycompany;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLogListener implements IRequestCycleListener {

    private static final Logger log= LoggerFactory.getLogger(ExceptionLogListener.class);

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex) {
        log.error("exception logged",ex);
        return cycle.getActiveRequestHandler();
    }
}
