package com.mycompany;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class DemoContainerTest {

    WicketTester tester;

    @Before
    public void setup(){
        tester=new WicketTester(new WicketApplication());
    }

    @Test
    public void testRender(){
        MockTestPage page=new MockTestPage();
        DemoContainer container=new DemoContainer("container");
        page.add(container);
        page=tester.startPage(page);
        tester.assertLabel("container:mylabel","hello");
    }

    class MockTestPage extends WebPage implements IMarkupResourceStreamProvider {
        @Override
        public IResourceStream getMarkupResourceStream(MarkupContainer container, Class<?> containerClass) {
            return new StringResourceStream("<html><div wicket:id='container'>  "
                    +"<span wicket:id='mylabel'> </span>"
                    +"</div>  </html>" );
        }
    }
}
