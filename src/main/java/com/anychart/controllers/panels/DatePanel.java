package com.anychart.controllers.panels;


import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.DayOfWeek;
import java.util.Date;

/**
 * DatePanel contains a table which can be used for viewing deliveries plotted into a month-layout
 */
public class DatePanel extends VerticalLayout {
    private Logger log = LoggerFactory.getLogger(getClass());




    Date month;

    public void setMonth(Date month) {
        this.month = month;
    }



    /**
     * Set the component to be vieved as enabled in the UI
     *
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {

    }

    /**
     * Set a caption of the embedded Table
     *
     * @param caption
     */
    @Override
    public void setCaption(String caption) {

    }

}
