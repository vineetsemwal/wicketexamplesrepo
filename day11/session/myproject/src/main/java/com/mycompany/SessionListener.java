package com.mycompany;

import org.apache.wicket.ISessionListener;
import org.apache.wicket.Session;

public class SessionListener implements ISessionListener {
    @Override
    public void onCreated(Session session) {
        System.out.println("session created");
    }

    @Override
    public void onUnbound(String sessionId) {
        System.out.println("session expired");
    }
}