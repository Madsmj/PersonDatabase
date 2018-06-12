package com.anychart.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "firstname")
    private String firstname;


    @Column(name = "lastname")
    private String lastname;

    @Column(name = "middlename")
    private String middlename;


    @Column(name = "dadUuid")
    private String dadUuid;

    @Column(name = "momUuid")
    private String momUuid;



    @Column(name = "value")
    private int value;

    public Person() {
    }

    public Person(String uuid, String firstname, String lastname, String middlename, int value) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.value = value;
    }

    public String getUuid() {
        return uuid;
    }

    public void setuid(int id) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }



    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
