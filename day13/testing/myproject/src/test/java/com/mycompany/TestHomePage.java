package com.mycompany;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testRender()
	{
		//start and render the test page
		tester.startPage(HomePage.class);
         tester.assertLabel(HomePage.CONTAINER_ID+":"+HomePage.LABEL_ID,"0");
         tester.assertVisible("container:counter");
         tester.assertComponent(HomePage.LINK_ID, Link.class);
		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void testLink_1(){
		tester.startPage(HomePage.class);
         tester.clickLink("increment");
		tester.assertLabel(HomePage.CONTAINER_ID+":"+HomePage.LABEL_ID,"1");
		tester.assertComponent("increment", Link.class);

	}

}
