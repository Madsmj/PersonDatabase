package com.anychart.controllers.utils;



import com.anychart.models.Person;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class Converter {


    public static String getPersonDescription(Person p) {

        return Objects.toString(p.getFirstname(), "") + " " + Objects.toString(p.getMiddlename(), "") + " " + Objects.toString(p.getLastname(), "");

    }
}
