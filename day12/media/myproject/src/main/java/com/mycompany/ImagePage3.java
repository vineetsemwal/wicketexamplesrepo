package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ImagePage3 extends WebPage {
    private static final long serialVersionUID = 1L;

    private static final Logger log= LoggerFactory.getLogger(ImagePage3.class);


    public ImagePage3(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        ResourceReference imgRef1=new MyFileSystemResourceReference("india.png");
        add(new Image("img1",imgRef1));

        ResourceReference imgRef2=new MyFileSystemResourceReference("japan.jpg");
        add(new Image("img2",imgRef2));

    }
}
