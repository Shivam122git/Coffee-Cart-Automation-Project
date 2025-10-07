package com.coffeecart.suites;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlClass;

import java.util.ArrayList;
import java.util.List;

public class TestNGRunner {

    public static void main(String[] args) {
        TestNG testng = new TestNG();

        XmlSuite suite = new XmlSuite();
        suite.setName("Coffee Cart Test Suite");
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(2);

        XmlTest chromeTest = new XmlTest(suite);
        chromeTest.setName("Chrome Tests");
        chromeTest.addParameter("browser", "chrome");

        List<XmlClass> chromeClasses = new ArrayList<>();
        chromeClasses.add(new XmlClass("com.coffeecart.tests.MenuTests"));
        chromeClasses.add(new XmlClass("com.coffeecart.tests.CartTests"));
        chromeClasses.add(new XmlClass("com.coffeecart.tests.CheckoutTests"));
        chromeTest.setXmlClasses(chromeClasses);

        XmlTest firefoxTest = new XmlTest(suite);
        firefoxTest.setName("Firefox Tests");
        firefoxTest.addParameter("browser", "firefox");

        List<XmlClass> firefoxClasses = new ArrayList<>();
        firefoxClasses.add(new XmlClass("com.coffeecart.tests.MenuTests"));
        firefoxClasses.add(new XmlClass("com.coffeecart.tests.CartTests"));
        firefoxTest.setXmlClasses(firefoxClasses);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testng.setXmlSuites(suites);
        testng.run();
    }
}