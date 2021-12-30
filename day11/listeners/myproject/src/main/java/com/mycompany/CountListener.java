package com.mycompany;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class CountListener implements IRequestCycleListener {

    public  static final MetaDataKey<Integer>key=new MetaDataKey<Integer>(){};

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        System.out.println("inside on begin request");
       Application app= Application.get();
       Integer val=app.getMetaData(key);
       if(val==null){
           val=1;
       }else{
            val++;
          }
        app.setMetaData(key, val);
    }

    @Override
    public void onEndRequest(RequestCycle cycle) {
        System.out.println("inside on end request");
    }

    public static  int getRequestsCount(){
        Application app= Application.get();
        Integer val=app.getMetaData(key);
        if(val==null){
            val=1;
        }
        return val;
    }



}
