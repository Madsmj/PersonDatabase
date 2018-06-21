package com;

import com.anychart.SchemaGeneratorDemo;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Runner for starting the application during development at http://localhost:8080/dpa-manualcontrol/
 */
public class JettyRunner {

    public static void main(String[] args) throws Exception {

        // Locate custom cacerts with LDAP server certificate - https://stackoverflow.com/a/38431439/53897

        //Path cacertPath = MavenProjectsHelper.getRequiredPathTowardsRoot(FamilymapUI.class, "cacerts");
        //System.setProperty ("javax.net.ssl.trustStore", cacertPath.toFile().getAbsolutePath());
        //System.setProperty ("javax.net.ssl.trustStorePassword", "changeit");

        // Create Jetty Server
        Server server = new Server(8082);

        //Path warPath = MavenProjectsHelper.getRequiredPathTowardsRoot(SchemaGeneratorDemo.class, "HibernateGenerateTables-0.0.1-SNAPSHOT.war");
        //Path xmlPath = MavenProjectsHelper.getRequiredPathTowardsRoot(FamilymapUI.class, "dpa-manualcontrol_jetty.xml");

        // Read parameter name-value pairs from XML file and set them as init parameters.
        //InputStream in = new FileInputStream(xmlPath.toFile());
        //InputSource inputSource = new InputSource(new InputStreamReader(in, StandardCharsets.UTF_8));

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/MapMyFamily");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //org.w3c.dom.Document doc = builder.parse(inputSource);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Parameter");
        //NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        //for (int i = 0; i < nl.getLength(); i++) {
        //    String paramName = nl.item(i).getAttributes().getNamedItem("name").getTextContent();
        //    String paramValue = nl.item(i).getAttributes().getNamedItem("value").getTextContent();
        //    webapp.setInitParameter(paramName, paramValue);
        //}

        // Ready

        //webapp.setWar("/home/mmj/projects/PersonDatabase/target/HibernateGenerateTables.war");


        webapp.setDescriptor("/home/mmj/projects/PersonDatabase/src/main/webapp/WEB-INF/web.xml");
        webapp.setResourceBase("file:/home/mmj/projects/PersonDatabase/src/main/webapp/");
        webapp.setParentLoaderPriority(true);

        server.setHandler(webapp);

        server.start();
        server.join();

        server.dumpStdErr();
    }
}
