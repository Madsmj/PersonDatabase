package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.utils.Converter;
import com.anychart.models.Person;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;


public class PersonPanel extends VerticalLayout {

    private TextArea personPanel = new TextArea();
    private Person person;
    private Button createPerson = new Button("create");
    private Button goToPerson = new Button("goto");
    private String uuid;

    public PersonPanel() {

        createPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });
        goToPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                VaadinSession.getCurrent().setAttribute(
                        AuthService.SESSION_PERSONITEM, person);
            }
        });

        this.addComponent(personPanel);
        this.addComponent(createPerson);
        this.addComponent(goToPerson);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setValue(String value) {
        personPanel.setValue(value);
    }

    public void setPersonPanel(Person person) {
        this.person = person;
        this.personPanel.setValue(Converter.getPersonDescription(person));
    }
}
