package com.anychart.controllers.panels;

import com.anychart.controllers.FamilymapUI;
import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.window.PopupWindow;
import com.anychart.models.dao.UserDAO;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class PublicComponent extends CustomComponent {

    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private UserDAO userDAO = new UserDAO();

    public PublicComponent() {

        userDAO.setSessionFactory(sessionFactory);
        setSizeFull();
        TextField username = new TextField("Username");
        username.focus();
        PasswordField password = new PasswordField("Password");
        CheckBox rememberMe = new CheckBox("Remember me");
        Button button = new Button("Login", e -> onLogin(username.getValue(), password.getValue(), rememberMe.getValue()));
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        Button buttonCreate = new Button("Create");
        buttonCreate.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final PopupWindow dialog = new PopupWindow("Create new user (only loginname)", true);

                UserCreatePanel rp = new UserCreatePanel();
                dialog.setDialogContent(rp);
                dialog.setModal(true);




                UI.getCurrent().addWindow(dialog);
                dialog.setListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI.getCurrent().removeWindow(dialog);
                        if ("OKBUTTON".equals(event.getButton().getId())) {
                            String username = rp.getUsername();
                            String password = rp.getPassword();

                            if(username.length() > 3 && password.length() > 7) {
                                String userUuid = userDAO.createUser(username, rp.getPassword());
                                Notification.show("The user has been created", Notification.Type.ASSISTIVE_NOTIFICATION);
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

        FormLayout formLayout = new FormLayout(username, password, button, rememberMe, buttonCreate);
        formLayout.setSizeUndefined();

        VerticalLayout layout = new VerticalLayout(formLayout);
        layout.setSizeFull();
        layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
        setSizeFull();
    }

    private void onLogin(String username, String password, boolean rememberMe) {
        if (AuthService.login(username, password, rememberMe)) {
            FamilymapUI ui = (FamilymapUI) UI.getCurrent();
            ui.showPrivateComponent();
        } else {
            Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
        }
    }
}