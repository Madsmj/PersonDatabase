package com.anychart.controllers;

import com.anychart.controllers.panels.PrivateComponent;
import com.anychart.controllers.panels.PublicComponent;
import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.views.MainView;
import com.anychart.controllers.views.StatisticsView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class FamilymapUI extends UI {

    protected Logger log = LoggerFactory.getLogger(getClass());

    public static String address = "localhost";
    private Navigator navigator;
    public static final String MAINVIEW = "";
    public static final String CONFIGPANEL = "CONFIGPANEL";
    public static final String DELIVERYPANEL = "DELIVERYPANEL";
    public static final String OVERVIEW = "OVERVIEW";
    public static final String TITLEVALIDATIONPANEL = "TITLEVALIDATIONPANEL";

    //private DataModel model = new DataModel();

    /**
     * Initiate the application
     *
     * @param request
     */
    @Override
    protected void init(VaadinRequest request) {
        if (!AuthService.isAuthenticated()) {
            showPublicComponent();
        } else {
            String uuid = request.getParameter("uuid");
            showPrivateComponent(uuid);
        }
    }

    public void showPublicComponent() {
        setContent(new PublicComponent());
    }

    public void showPrivateComponent(String uuid) {
        setContent(new PrivateComponent(uuid));
    }
}
