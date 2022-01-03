package com.mycompany;

import com.mycompany.web.SamplePage;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.time.Duration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class WicketApplication {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder()
                .sources(WicketApplication.class)
                .run(args);
        WebApplication app = context.getBean(WebApplication.class);
        app.getResourceSettings().getResourceFinders().add(new WebApplicationPath(app.getServletContext(), "markup"));
        app.getResourceSettings().setResourcePollFrequency(Duration.seconds(10));
       // app.setConfigurationType(RuntimeConfigurationType.DEPLOYMENT);
       // app.mountPage("/sample2",SamplePage.class);
    }

}
