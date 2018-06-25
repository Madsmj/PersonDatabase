package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.utils.Converter;
import com.anychart.controllers.window.PopupWindow;
import com.anychart.models.DataModel;
import com.anychart.models.Person;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;


public class PersonPanel extends VerticalLayout {

    private DataModel dm = new DataModel();
    private TextArea personPanel = new TextArea();
    private Person person;
    private Button createPerson = new Button("create");
    private Button goToPerson = new Button("goto");
    private String uuid;

    public PersonPanel() {

        createPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                final PopupWindow dialog = new PopupWindow("Create new user (only loginname)", true);

                PersonCreatePanel rp = new PersonCreatePanel();
                dialog.setDialogContent(rp);
                dialog.setModal(true);

                UI.getCurrent().addWindow(dialog);
                dialog.setListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI.getCurrent().removeWindow(dialog);
                        if ("OKBUTTON".equals(event.getButton().getId())) {
                            Person createdPerson = rp.getPerson();

                            if(createdPerson != null) {
                                dm.createPerson(createdPerson);

                                //String userUuid = userDAO.createUser(username, rp.getPassword());
                                //Notification.show("The user has been created", Notification.Type.ASSISTIVE_NOTIFICATION);
                            } else {
                                Notification.show("Invalid username or password", Notification.Type.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                dialog.addCloseListener(new Window.CloseListener() {
                    //This event gets called when the dialog is closed
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        UI.getCurrent().removeWindow(dialog);
                    }
                });

            }
        });
        goToPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                VaadinSession.getCurrent().setAttribute(
                        AuthService.SESSION_PERSONITEM, person);
                Page.getCurrent().reload();
            }
        });

        this.addComponent(personPanel);
        //this.addComponent(createPerson);
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
