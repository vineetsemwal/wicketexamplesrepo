package com.mycompany;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class DemoSessionMetaDataPage extends WebPage {

    private static final MetaDataKey<Integer>key=new MetaDataKey<Integer>(){};


    @Override
    protected void onInitialize() {
        super.onInitialize();
       Session session= getSession();
        session.bind();// forces wicket to create permanent session
       add(new Label("counter",()->getCount()));
    }


    @Override
    protected void onConfigure() {
        super.onConfigure();
       Session session=  getSession();

       Integer count= session.getMetaData(key);
       if(count==null){
           count=1;
       }else{
           count++;
       }
       session.setMetaData(key,count);


       System.out.println("inside onconfigure,"+"session temporary="+session.isTemporary()+ "session id="+session.getId());
        System.out.println("count value in session="+count);
    }

    public int getCount(){
        Integer count= getSession().getMetaData(key);
        if(count==null){
            count=1;
        }
        return count;
    }

}
