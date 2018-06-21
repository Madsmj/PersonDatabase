package com.anychart.controllers.panels;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * This panel
 */
public class PersonConnectPanel extends VerticalLayout {

    private Label selectedPerson = new Label("-");
    private Button meButton = new Button("me");
    private Button fatherButton = new Button("father");
    private Button motherButton = new Button("mother");

    public PersonConnectPanel() {
        meButton.setWidth(100, Unit.PIXELS);
        meButton.setId("ME");
        fatherButton.setWidth(100, Unit.PIXELS);
        fatherButton.setId("FATHER");
        motherButton.setWidth(100, Unit.PIXELS);
        motherButton.setId("MOTHER");

        this.setSpacing(true);

        this.addComponent(selectedPerson);
        this.addComponent(meButton);
        this.addComponent(fatherButton);
        this.addComponent(motherButton);
    }

    public void addClickListener(Button.ClickListener listener) {
        meButton.addClickListener(listener);
        fatherButton.addClickListener(listener);
        motherButton.addClickListener(listener);
    }

    @Override
    public void setEnabled(boolean enabled) {

        super.setEnabled(enabled);
    }
}
