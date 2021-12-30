package com.mycompany;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class DemoCustomSessionPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Session session= getSession();
        session.bind();// forces wicket to create permanent session
        add(new Label("counter",()->getCount()));
    }

    @Override
    public CustomSession getSession(){
        return (CustomSession)super.getSession();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        CustomSession session=getSession();
        int count= session.increment();
        System.out.println("inside onconfigure,"+"session temporary="+session.isTemporary()+ "session id="+session.getId());
        System.out.println("count value in session="+count);
    }

    public int getCount(){
        CustomSession session=getSession();
        return session.getCount();
    }
}
