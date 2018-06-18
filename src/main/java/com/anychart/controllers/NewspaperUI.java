package com.anychart.controllers;

import com.anychart.controllers.views.MainView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class NewspaperUI extends UI {

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

        if (request.getUserPrincipal() != null) {
            String initials = request.getUserPrincipal().getName();
            //model.setInitials(initials);
        }

        //These parameters can be used to construct a link to information without performing the search in the UI
        String month = request.getParameter("month");
        String del = request.getParameter("del");
        String title = request.getParameter("title");
        boolean validated = "true".equals(request.getParameter("validated")); //This parameter is a backdoor to view information that is normally hidden from the user



        address = request.getRemoteAddr();
        getPage().setTitle("DPA");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        navigator.addView(MAINVIEW, new MainView());


    }

}
