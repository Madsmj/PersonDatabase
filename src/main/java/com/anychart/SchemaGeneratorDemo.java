package com.anychart;

import com.anychart.models.dao.PersonDAO;
import com.anychart.models.dao.UserDAO;
import com.anychart.models.Person;
import com.anychart.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;
import java.util.List;

// Hibernate 5.
public class SchemaGeneratorDemo {

    public static final String SCRIPT_FILE = "exportScript.sql";

    private static SchemaExport getSchemaExport() {

        SchemaExport export = new SchemaExport();
        // Script file.
        File outputFile = new File(SCRIPT_FILE);
        String outputFilePath = outputFile.getAbsolutePath();

        System.out.println("Export file: " + outputFilePath);

        export.setDelimiter(";");
        export.setOutputFile(outputFilePath);

        // No Stop if Error
        export.setHaltOnError(false);
        //
        return export;
    }

    public static void dropDataBase(SchemaExport export, Metadata metadata) {
        // TargetType.DATABASE - Execute on Databse
        // TargetType.SCRIPT - Write Script file.
        // TargetType.STDOUT - Write log to Console.
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

        export.drop(targetTypes, metadata);
    }

    public static void createDataBase(SchemaExport export, Metadata metadata) {
        // TargetType.DATABASE - Execute on Databse
        // TargetType.SCRIPT - Write Script file.
        // TargetType.STDOUT - Write log to Console.

        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

        SchemaExport.Action action = SchemaExport.Action.CREATE;
        //
        export.execute(targetTypes, action, metadata);

        System.out.println("Export OK");

    }




    public static void deployData() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        PersonDAO personDAO = new PersonDAO();
        personDAO.setSessionFactory(sessionFactory);

        UserDAO userDAO = new UserDAO();
        userDAO.setSessionFactory(sessionFactory);

        personDAO.createPerson("Susanne","","Møller");
        personDAO.createPerson("Mads","Møller","Johansen");
        personDAO.createPerson("David","Kusk","Johansen");
        personDAO.createPerson("Thomas","Kusk","Johansen");
        personDAO.createPerson("Jonas","Kusk","Johansen");

        List<Person> sPerson = personDAO.findPerson("Susanne",null,null,null);
        List<Person> mPerson = personDAO.findPerson("Mads",null,null,null);

        List<Person> dPerson = personDAO.findPerson("David",null,null,null);
        dPerson.get(0).setMomUuid(sPerson.get(0).getUuid());
        dPerson.get(0).setDadUuid(mPerson.get(0).getUuid());
        personDAO.updatePerson(dPerson.get(0));

        List<Person> tPerson = personDAO.findPerson("Thomas",null,null,null);
        tPerson.get(0).setMomUuid(sPerson.get(0).getUuid());
        tPerson.get(0).setDadUuid(mPerson.get(0).getUuid());
        personDAO.updatePerson(tPerson.get(0));

        List<Person> jPerson = personDAO.findPerson("Jonas",null,null,null);
        jPerson.get(0).setMomUuid(sPerson.get(0).getUuid());
        jPerson.get(0).setDadUuid(mPerson.get(0).getUuid());
        personDAO.updatePerson(jPerson.get(0));

        //Add person to me
        Person adp = new Person();
        adp.setFirstname("Christian");
        adp.setLastname("Johansen");
        String duuid = personDAO.createPerson(adp);
        List<Person> added = personDAO.findPerson("Mads",null,null,null);
        added.get(0).setDadUuid(duuid);
        personDAO.updatePerson(added.get(0));

        //Add person to me
        Person amp = new Person();
        amp.setFirstname("Karen");
        amp.setMiddlename("Møller");
        amp.setLastname("Johansen");
        String muuid = personDAO.createPerson(amp);
        List<Person> added2 = personDAO.findPerson("Mads",null,null,null);
        added2.get(0).setMomUuid(muuid);
        personDAO.updatePerson(added2.get(0));

        //Add person to me
        Person ad1 = new Person();
        ad1.setFirstname("Ernst");
        ad1.setLastname("Johansen");
        String uuid = personDAO.createPerson(ad1);
        List<Person> person = personDAO.findPerson("Christian",null,"Johansen",null);
        person.get(0).setDadUuid(uuid);
        personDAO.updatePerson(person.get(0));

        //Add person to me
        ad1 = new Person();
        ad1.setFirstname("Jonna");
        ad1.setLastname("Johansen");
        uuid = personDAO.createPerson(ad1);
        person = personDAO.findPerson("Christian",null,"Johansen",null);
        person.get(0).setMomUuid(uuid);
        personDAO.updatePerson(person.get(0));


        //Add person to me
        ad1 = new Person();
        ad1.setFirstname("Helge");
        ad1.setLastname("Larsen");
        uuid = personDAO.createPerson(ad1);
        person = personDAO.findPerson("Karen",null,"Johansen",null);
        person.get(0).setDadUuid(uuid);
        personDAO.updatePerson(person.get(0));

        //Add person to me
        ad1 = new Person();
        ad1.setFirstname("Edel");
        ad1.setLastname("Larsen");
        uuid = personDAO.createPerson(ad1);
        person = personDAO.findPerson("Karen",null,"Johansen",null);
        person.get(0).setMomUuid(uuid);
        personDAO.updatePerson(person.get(0));


        ad1 = new Person();
        ad1.setFirstname("Jeppe");
        ad1.setMiddlename("Skafte");
        ad1.setLastname("Johansen");
        ad1.setMomUuid(muuid);
        ad1.setDadUuid(duuid);
        uuid = personDAO.createPerson(ad1);

        User fu = new User();
        fu.setUsername("mads");
        fu.setPassword("testtest");
        fu.setPerson(mPerson.get(0));
        uuid = userDAO.createUser(fu);



        System.out.println("Deploy OK");

    }

    public static void main(String[] args) {

        // Using Oracle Database.

        String configFileName = "hibernate.cfg.xml";

        // Create the ServiceRegistry from hibernate-xxx.cfg.xml
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                .configure(configFileName).build();

        // Create a metadata sources using the specified service registry.
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        SchemaExport export = getSchemaExport();

        System.out.println("Drop Database...");
        // Drop Database
        dropDataBase(export, metadata);

        System.out.println("Create Database...");
        // Create tables
        createDataBase(export, metadata);


        System.out.println("Deploy Data...");
        // Create tables
        deployData();

    }

}