package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.utils.Converter;
import com.anychart.models.Person;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * Panel for handling searching of values for checkings
 */
public class ConnectionPanel extends GridLayout {

    private PersonPanel me = new PersonPanel();
    private PersonPanel father = new PersonPanel();
    private PersonPanel mother = new PersonPanel();

    public ConnectionPanel() {
        super(3,2);
        Person person = (Person)VaadinSession.getCurrent().getAttribute(AuthService.SESSION_PERSONITEM);
        me.setValue(Converter.getPersonDescription(person));

        father.setEnabled(person.getDadUuid() != null);
        if(person.getDadUuid()!=null) {
            //father.setValue("Father:\n" + Converter.getPersonDescription(person.getDadUuid()));
            //father.setUuid(person.getDadUuid().getUuid());
            father.setPersonPanel(person.getDadUuid());
        }

        mother.setEnabled(person.getMomUuid() != null);
        if(person.getMomUuid()!=null) {
            //mother.setValue("Mother:\n" + Converter.getPersonDescription(person.getMomUuid()));
            //mother.setUuid(person.getMomUuid().getUuid());
            mother.setPersonPanel(person.getMomUuid());
        }

        this.addComponent(me,1,1);
        this.addComponent(mother,0,0);
        this.addComponent(father,2,0);
    }


    /**
     * Add listeners to buttons in panel
     * @param listener
     */
    public void addClickListener(Button.ClickListener listener) {

    }
}
