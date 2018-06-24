package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.utils.Converter;
import com.anychart.models.Person;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;


public class TreePanel extends GridLayout {

    private TextField me = new TextField();
    private TextArea father = new TextArea();
    private TextArea mother = new TextArea();

    public TreePanel() {
        super(6,31);
        Person person = (Person) VaadinSession.getCurrent().getAttribute(AuthService.SESSION_PERSONITEM);
        me.setValue(Converter.getPersonDescription(person));

        father.setEnabled(person.getMomUuid() != null);
        if(person.getMomUuid()!=null) {
            father.setValue(Converter.getPersonDescription(person.getMomUuid()));
        }

        father.setEnabled(person.getMomUuid() != null);
        if(person.getDadUuid()!=null) {
            mother.setValue(Converter.getPersonDescription(person.getDadUuid()));
        }

        this.addComponent(me,1,15);

        this.addComponent(new TextField(),2,7);
        this.addComponent(new TextField(),2,23);

        this.addComponent(new TextField(),3,3);
        this.addComponent(new TextField(),3,11);
        this.addComponent(new TextField(),3,19);
        this.addComponent(new TextField(),3,27);

        this.addComponent(new TextField(),4,1);
        this.addComponent(new TextField(),4,5);
        this.addComponent(new TextField(),4,9);
        this.addComponent(new TextField(),4,13);
        this.addComponent(new TextField(),4,17);
        this.addComponent(new TextField(),4,21);
        this.addComponent(new TextField(),4,25);
        this.addComponent(new TextField(),4,29);

        this.addComponent(new TextField(),5,0);
        this.addComponent(new TextField(),5,2);
        this.addComponent(new TextField(),5,4);
        this.addComponent(new TextField(),5,6);
        this.addComponent(new TextField(),5,8);
        this.addComponent(new TextField(),5,10);
        this.addComponent(new TextField(),5,12);
        this.addComponent(new TextField(),5,14);
        this.addComponent(new TextField(),5,16);
        this.addComponent(new TextField(),5,18);
        this.addComponent(new TextField(),5,20);
        this.addComponent(new TextField(),5,22);
        this.addComponent(new TextField(),5,24);
        this.addComponent(new TextField(),5,26);
        this.addComponent(new TextField(),5,28);
        this.addComponent(new TextField(),5,30);
    }




    /**
     * Add listeners to buttons in panel
     * @param listener
     */
    public void addClickListener(Button.ClickListener listener) {

    }
}
