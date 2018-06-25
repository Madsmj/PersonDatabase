package com.anychart.controllers.panels;

import com.anychart.models.DataModel;
import com.anychart.models.Person;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * This panel contains results of validations in a titleDelivery, it gives the possiblity of viewing what has been validated
 * and store it if it is approved.
 */
public class PersonCreatePanel extends VerticalLayout {

    private TextField firstname = new TextField("firstname");
    private TextField middlename = new TextField("Middlename");
    private TextField lastname = new TextField("lastname");

    public PersonCreatePanel() {
        this.setSpacing(true);

        this.addComponent(firstname);
        this.addComponent(middlename);
        this.addComponent(lastname);
    }



    /**
     * Set values to be viewed in the panel
     * @param item
     */
    public void setValues(Object item) {

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    public Person getPerson() {
        Person p = new Person();
        p.setFirstname(firstname.getValue());
        p.setMiddlename(middlename.getValue());
        p.setLastname(lastname.getValue());
        return p;
    }
}
