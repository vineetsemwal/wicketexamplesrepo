package com.mycompany;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class DemoPanelTest {

    WicketTester tester;

    @Before
    public void setup(){
        tester=new WicketTester(new WicketApplication());
    }

    @Test
    public void testRender(){
        DemoPanel panel=new DemoPanel("panel");
        panel=tester.startComponentInPage(panel);

        String expectedMsg1=panel.getString("msg1");
        String expectedMsg2=panel.getString("msg2");
        System.out.println("msg1="+expectedMsg1);
        tester.assertLabel("panel:msg1",expectedMsg1);
        tester.assertLabel("panel:msg2",expectedMsg2);
        tester.assertLabel("panel:counter","0");
        tester.assertComponent("panel:increment", AjaxLink.class);

    }

    @Test
    public  void testIncrement_Click_1(){
        DemoPanel panel=new DemoPanel("panel");
        panel=tester.startComponentInPage(panel);
        tester.clickLink("panel:increment");
        tester.assertLabel("panel:counter","1");
        tester.assertComponentOnAjaxResponse("panel:counter");
    }


}
