package com.anychart.controllers.panels;


import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;

import com.vaadin.ui.VerticalLayout;

import java.util.Collection;
import java.util.Iterator;

/**
 * Generic table for viewing a table in the table-bean form. This component contains a table and a checkbox which makes
 * it possible to make the table visible/invisible
 */
public class GenericListTable extends VerticalLayout {

    private String checkedColumnName;
    private String sortColumnName = null;
    private Object checkedColumnDefaultValue;


    /**
     * Construct the TableViewing-component with specific parameters about layout
     *
     * @param c
     * @param checkedColumn
     * @param checkedDefaultValue
     * @param visibleColumns
     * @param tableId
     * @param initialVisible
     */
    public GenericListTable(Class c, String checkedColumn, Object checkedDefaultValue, String[] visibleColumns, String tableId, boolean initialVisible) {

    }

    /**
     * Set a list of which columns in the table to make visible
     *
     * @param visibleColumns
     */
    public void setVisibleColumns(String[] visibleColumns) {

    }

    /**
     * Set the name of the column in the table to sort the rows with
     *
     * @param param
     */
    public void setSortParam(String param) {
        sortColumnName = param;
    }

    /**
     * Check the specified row in the table
     *
     * @param itemId
     * @param value
     * @return returns true if the item could be found
     */
    public boolean checkSpecific(Object itemId, Object value) {

        return false;
    }

    /**
     * Iterate through all rows and find out if all checkboxes is selected
     *
     * @return
     */
    public boolean isAllChecked() {

        return true;
    }

    /**
     * Set the component to be vieved as enabled in the UI
     *
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public void setCaption(String caption) {

    }

    public void cleanTable() {

    }

    /**
     * Set data to the embedded table
     *
     * @param dataCollection
     */
    public void setInfo(Collection dataCollection) {

    }

    public void setValToCheck(Object itemId, Object value) {

    }

}
