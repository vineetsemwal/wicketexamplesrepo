package com.mycompany;

import org.apache.wicket.request.resource.PackageResourceReference;

public class Style2Reference extends PackageResourceReference{
    private Style2Reference(){
        //class and file should be in same package, wicket will register ss file in this class scope
        super(Style2Reference.class,"style2.css");
    }

    private static final Style2Reference instance=new Style2Reference();

    public static Style2Reference get(){
        return instance;
    }



}
