package com.mycompany;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(HomePage.class);

    private static final MetaDataKey<Integer>pageKey=new MetaDataKey<Integer>(){};


    public HomePage(final PageParameters parameters) {
        super(parameters);
        System.out.println("inside HomePge ctr");
        //setStatelessHint(false);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        System.out.println("inside homepage onconfigure");
      getOrSetPageRequestCount();

    }

    public int getOrSetPageRequestCount(){
        Integer pageRequestCount= getMetaData(pageKey);
        if(pageRequestCount==null){
            pageRequestCount=1;
        }else{
            pageRequestCount++;
        }
        setMetaData(pageKey,pageRequestCount);
        return pageRequestCount;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();


        add(new Label("appRequestsCount",()->{
            int val=CountListener.getRequestsCount();
            return val;
        }));


        add(new Label("pageRequestsCount",()->{
            int val=getOrSetPageRequestCount();
            return val;
        }));

        add(new Label("cid" ,()->{
           Customer customer=LoginTracker.getCustomer();
            return customer.getId();
        }));

        add(new Label("cname" ,()->{
            Customer customer=LoginTracker.getCustomer();
            return customer.getName();
        }));

    }

}
