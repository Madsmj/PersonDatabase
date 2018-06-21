package com.anychart.controllers.utils;



import com.anychart.models.User;
import com.anychart.models.dao.UserDAO;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

public class AuthService {

    private static final String COOKIE_NAME = "remember-me";
    public static final String SESSION_USERNAME = "username";
    public static final String SESSION_USERUUID = "useruuid";
    public static final String SESSION_USERITEM = "useritem";
    public static final String SESSION_PERSONITEM = "personitem";







    public static boolean isAuthenticated() {
        return VaadinSession.getCurrent()
                .getAttribute(SESSION_USERNAME) != null
                || loginRememberedUser();
    }

    public static boolean login(String username, String password,
                                boolean rememberMe) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        UserDAO userDAO = new UserDAO();
        userDAO.setSessionFactory(sessionFactory);
        User user = userDAO.validateUser(username, password);


        if (user != null) {
            VaadinSession.getCurrent().setAttribute(
                    SESSION_USERNAME, user.getUsername());

            VaadinSession.getCurrent().setAttribute(
                    SESSION_USERUUID, user.getUuid());

            VaadinSession.getCurrent().setAttribute(
                    SESSION_USERITEM, user);

            VaadinSession.getCurrent().setAttribute(
                    SESSION_PERSONITEM, user.getPerson());

            if (rememberMe) {
                rememberUser(username);
            }
            return true;
        }

        return false;
    }

    public static void logOut() {
        Optional<Cookie> cookie = getRememberMeCookie();
        if (cookie.isPresent()) {
            String id = cookie.get().getValue();
            UserService.removeRememberedUser(id);
            deleteRememberMeCookie();
        }

        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation("");
    }

    private static Optional<Cookie> getRememberMeCookie() {
        Cookie[] cookies =
                VaadinService.getCurrentRequest().getCookies();
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(COOKIE_NAME))
                .findFirst();
    }

    private static boolean loginRememberedUser() {
        Optional<Cookie> rememberMeCookie = getRememberMeCookie();

        if (rememberMeCookie.isPresent()) {
            String id = rememberMeCookie.get().getValue();
            String username = UserService.getRememberedUser(id);

            if (username != null) {
                VaadinSession.getCurrent()
                        .setAttribute(SESSION_USERNAME, username);
                return true;
            }
        }

        return false;
    }

    private static void rememberUser(String username) {
        String id = UserService.rememberUser(username);

        Cookie cookie = new Cookie(COOKIE_NAME, id);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30); // valid for 30 days
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    private static void deleteRememberMeCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }
}