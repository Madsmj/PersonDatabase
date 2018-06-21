package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.controllers.window.PopupWindow;
import com.anychart.models.DataModel;
import com.anychart.models.User;
import com.anychart.models.dao.PersonDAO;
import com.anychart.models.Person;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

/**
 * Panel for handling searching of values for checkings
 */
public class SearchPanel extends VerticalLayout {

    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private PersonDAO personDAO = new PersonDAO();

    private HorizontalLayout hz = new HorizontalLayout();
    private HorizontalLayout hz2 = new HorizontalLayout();

    private Button searchButton = new Button("Search");

    private DateField startDf = new DateField("Lived", LocalDate.now());

    private TextField firstname = new TextField("Firstname");
    private TextField middlename = new TextField("MiddleName(s)");
    private TextField lastname = new TextField("Lastname");

    private Button meButton = new Button("Me");
    private Button fatherButton = new Button("Father");
    private Button motherButton = new Button("Mother");


    private List<Person> people;
    private Grid<Person> grid = new Grid<>();

    DataModel dm = new DataModel();


    public SearchPanel() {
        meButton.setWidth(100, Unit.PIXELS);
        fatherButton.setWidth(100, Unit.PIXELS);
        motherButton.setWidth(100, Unit.PIXELS);


        personDAO.setSessionFactory(sessionFactory);

        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                people = personDAO.findPerson(firstname.getValue(),middlename.getValue(),lastname.getValue(), null);
                grid.setItems(people);
            }
        });



        grid.addColumn(Person::getFirstname).setCaption("Firstname");
        grid.addColumn(Person::getMiddlename).setCaption("Middlename");
        grid.addColumn(Person::getLastname).setCaption("Lastname");
        grid.addSelectionListener(event -> {
            Person selected = event.getFirstSelectedItem().get();
            final PopupWindow dialog = new PopupWindow("Connect Person", false);
            PersonConnectPanel rp = new PersonConnectPanel();
            rp.setSelectedPerson(selected.getFirstname()+" "+selected.getMiddlename()+" "+selected.getLastname());

            rp.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                    switch(clickEvent.getButton().getId()) {

                        case "ME":
                            User user = (User) VaadinSession.getCurrent()
                                    .getAttribute(AuthService.SESSION_USERUITEM);

                            user.setPerson(selected);
                            dm.updateUser(user);

                            break;
                        case "FATHER":

                            break;
                        case "MOTHER":

                            break;

                    }
                }
            });

            dialog.setDialogContent(rp);
            dialog.setModal(true);
            UI.getCurrent().addWindow(dialog);
        });

        hz.addComponent(searchButton);
        hz.addComponent(firstname);
        hz.addComponent(middlename);
        hz.addComponent(lastname);
        hz.addComponent(startDf);

        hz2.addComponent(meButton);
        hz2.addComponent(fatherButton);
        hz2.addComponent(motherButton);

        this.addComponent(hz);
        this.addComponent(hz2);
        this.addComponent(grid);
    }


    /**
     * Add listeners to buttons in panel
     * @param listener
     */
    public void addClickListener(Button.ClickListener listener) {

    }
}
