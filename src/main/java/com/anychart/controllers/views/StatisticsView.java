package com.anychart.controllers.views;


import com.anychart.controllers.NewspaperUI;
import com.anychart.controllers.datamodel.DataModel;
import com.anychart.controllers.panels.DatePanel;
import com.anychart.controllers.panels.GenericListTable;
import com.anychart.controllers.panels.SearchPanel;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The full panel for showing deliveries and titles.
 * The panel contains a search panel at the top, a information panel in the middle, and a detail view at the buttom.
 * The information panel can be changed with different panel depending on what is in focus in the view
 */
public class StatisticsView extends VerticalLayout implements View {

    protected Logger log = LoggerFactory.getLogger(getClass());

    private SearchPanel searchPanel = new SearchPanel();
    private DataModel model;
    private Link metadatalink = new Link("Metadatalink", null);
    private DatePanel tabelsLayout;

    public StatisticsView(DataModel model, String type) {

        this.model = model;
        MenuBar header = new MenuBar();
        header.setWidth("100%");
        searchPanel.setWidth("100%");
        Layout mainhlayout;
        final VerticalLayout layout = new VerticalLayout();
        metadatalink.setTargetName("_blank");

        MenuBar.Command configCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo(NewspaperUI.CONFIGPANEL);
            }
        };

        MenuBar.Command otherCommand1 = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo(NewspaperUI.DELIVERYPANEL);
            }
        };

        MenuBar.Command otherCommand2 = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo(NewspaperUI.TITLEVALIDATIONPANEL);
            }
        };

        MenuBar.Command otherCommand3 = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo(NewspaperUI.OVERVIEW);
            }
        };

        header.addItem("config", configCommand);
        header.addItem("Delivery validation", otherCommand1);
        header.addItem("TitleValidation", otherCommand2);
        header.addItem("Overview", otherCommand3);

        switch (type) {
            case NewspaperUI.DELIVERYPANEL:
                tabelsLayout = new DatePanel();
                break;

            default:
                tabelsLayout = new DatePanel();
        }

        int browserWidth = UI.getCurrent().getPage().getBrowserWindowWidth();





        tabelsLayout.setVisible(false);

        final VerticalLayout viewLayout = new VerticalLayout();
        final HorizontalLayout viewControlLayout = new HorizontalLayout();


        tabelsLayout.setWidth("100%");
        tabelsLayout.setHeight("100%");
        layout.setMargin(true);
        addComponent(layout);


        searchPanel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

            }
        });



        Button confirmViewButton = new Button("Confirmed");
        confirmViewButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {


            }
        });

        Button rejectViewButton = new Button("Reject");
        rejectViewButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {


            }
        });

        layout.addComponent(header);
        layout.addComponent(searchPanel);
        viewControlLayout.addComponent(confirmViewButton);
        viewControlLayout.addComponent(rejectViewButton);
        viewControlLayout.addComponent(metadatalink);
        viewLayout.addComponent(viewControlLayout);

        panelPrepare(false);
    }

    /**
     * Set panes to being prepared for viewing details
     * @param prepare
     */
    private void panelPrepare(boolean prepare) {
        metadatalink.setVisible(prepare);
        tabelsLayout.setVisible(true);
    }

    /**
     * Show a welcome message when entering the page
     * If the model has been initiated with search-selections these wil be shown
     * @param event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Notification.show("DPA Delivery validation");
    }
}
