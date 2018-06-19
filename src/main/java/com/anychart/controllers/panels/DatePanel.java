package com.anychart.controllers.panels;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
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

    private String[] columns = {"Sun", "Mon","Tue", "Wed", "Thu", "Fri", "Sat"};
    private CheckBox checkbox;
    private BeanItemContainer beans;
    private Table table;
    private TextArea unmappable = new TextArea("");

    public DatePanel() {
        checkbox = new CheckBox("Visible", true);
        checkbox.setEnabled(false);
        beans = new BeanItemContainer(DayOfWeek.class);

        // Bind a table to it
        table = new Table("", null);

        DayOfWeek[] ds = DayOfWeek.values();
        //columns = new String[ds.length];
        int i = 0;
        table.addContainerProperty("Weekno", String.class, null);

        for (DayOfWeek d : ds) {
            //columns[i] = d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            table.addContainerProperty(columns[i], String.class, null);
            table.addGeneratedColumn(columns[i], new DatePanel.FieldGenerator());
            i++;
        }
        table.setSortContainerPropertyId("Weekno");

        unmappable.setEnabled(false);

        table.setWidth("100%");
        table.setHeight("100%");
        table.setSelectable(true);
        table.setImmediate(true);
        this.addComponent(checkbox);
        this.addComponent(table);
        this.addComponent(unmappable);
    }

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
        beans.removeAllItems();
        super.setEnabled(enabled);
    }

    /**
     * Set a caption of the embedded Table
     *
     * @param caption
     */
    @Override
    public void setCaption(String caption) {
        table.setCaption(caption);
    }

    /**
     * Generate textareas as cells in the table
     */
    static class FieldGenerator implements Table.ColumnGenerator {

        @Override
        public Component generateCell(Table source, Object itemId, Object columnId) {
            Property prop = source.getItem(itemId).getItemProperty(columnId);
            TextArea area = new TextArea(null, prop);
            if (prop.getValue() == null) {
                area.setValue("");
            }
            area.setReadOnly(true);
            return area;
        }
    }
}
