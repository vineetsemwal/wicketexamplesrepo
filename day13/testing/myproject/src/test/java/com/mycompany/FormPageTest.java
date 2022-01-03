package com.mycompany;

import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class FormPageTest {


    WicketTester tester;

    @Before
    public void setup(){
        tester=new WicketTester(new WicketApplication());
    }

   @Test
    public void testRender(){
        tester.startPage(FormPage.class);
        tester.assertComponent("form", Form.class);
        tester.assertComponent("form:name", TextField.class);
        tester.assertComponent("form:age", TextField.class);
        tester.assertComponent("form:btn", Button.class);
        tester.assertComponent("feedback", FeedbackPanel.class);
    }


    @Test
    public void testSubmit_Success(){
        tester.startPage(FormPage.class);
        FormTester formTester= tester.newFormTester("form");
        formTester.setValue("name","mohan");
        formTester.setValue("age","28");
        formTester.submit("btn");
        tester.assertFeedbackMessages(new ExactLevelFeedbackMessageFilter(FeedbackMessage.SUCCESS),"successful");
        tester.assertModelValue("form:age",28);
        tester.assertModelValue("form:name","mohan");
    }

    @Test
    public void testSubmit_Age_LessThanRequired(){
        tester.startPage(FormPage.class);
        FormTester formTester= tester.newFormTester("form");
        formTester.setValue("name","mohan");
        formTester.setValue("age","2");
        formTester.submit("btn");
        tester.assertFeedbackMessages(new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR),"The value of 'age' must be between 21 and 50.");
        tester.assertModelValue("form:age",0);
        tester.assertModelValue("form:name",null);
    }

}
