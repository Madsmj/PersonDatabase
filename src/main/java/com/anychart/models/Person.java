package com.anychart.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;


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

    @Column(name = "sameUuid")
    private String sameUuid;

    @Column(name = "loginKey")
    private String loginKey;

    @Column(name = "requestedBy")
    private String requestedBy;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column
    @CreationTimestamp
    private Date createDateTime;

    @Column
    @UpdateTimestamp
    private Date updateDateTime;

    @Column(name = "note")
    private String note;

    public Person() {
    }

    public Person(String uuid, String firstname, String lastname, String middlename) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
    }

    public String getUuid() {
        return uuid;
    }

    public void setuid(int id) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
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

    public String getDadUuid() {
        return dadUuid;
    }

    public void setDadUuid(String dadUuid) {
        this.dadUuid = dadUuid;
    }

    public String getMomUuid() {
        return momUuid;
    }

    public void setMomUuid(String momUuid) {
        this.momUuid = momUuid;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
