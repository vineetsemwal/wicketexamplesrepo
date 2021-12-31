package com.mycompany;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.css.ICssCompressor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.resource.CompositeCssCompressor;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see com.mycompany.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
       mountPage("image2",ImagePage2.class);
       mountPage("image3",ImagePage3.class);

    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return RuntimeConfigurationType.DEVELOPMENT;
    }
}
