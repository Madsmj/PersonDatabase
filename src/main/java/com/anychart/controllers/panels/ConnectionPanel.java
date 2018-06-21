package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.utils.Converter;
import com.anychart.models.Person;
import com.anychart.models.dao.PersonDAO;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Panel for handling searching of values for checkings
 */
public class ConnectionPanel extends GridLayout {

    private TextArea me = new TextArea();
    private TextArea father = new TextArea();
    private TextArea mother = new TextArea();

    public ConnectionPanel() {
        super(3,2);
        Person person = (Person)VaadinSession.getCurrent().getAttribute(AuthService.SESSION_PERSONITEM);
        me.setValue(Converter.getPersonDescription(person));

        father.setEnabled(person.getMomUuid() != null);
        if(person.getMomUuid()!=null) {
            father.setValue(Converter.getPersonDescription(person.getMomUuid()));
        }

        father.setEnabled(person.getMomUuid() != null);
        if(person.getDadUuid()!=null) {
            mother.setValue(Converter.getPersonDescription(person.getDadUuid()));
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
