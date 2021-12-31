package com.mycompany;

import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

public class Javascript1Reference  extends JavaScriptResourceReference {

    public Javascript1Reference(){
        super(HomePage.class,"javascript1.js");
    }

}
