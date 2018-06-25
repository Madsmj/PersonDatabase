package com.anychart.controllers.panels;

import com.vaadin.ui.Button;

/**
 * Created by mmj on 6/24/18.
 */
public class PersonCreatePagePanel extends PersonCreatePanel {

    Button okButton = new Button("ok");
    Button cancelButton = new Button("cancel");

    public PersonCreatePagePanel() {
        super();
        this.addComponent(okButton);
        this.addComponent(cancelButton);
    }

}
