package com.anychart.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * DataModel has one initiated instance per browsersession. The datamodel contains cashed information about the
 * deliveries, which is currently beeing checked
 */
public class DataModel {
    protected Logger log = LoggerFactory.getLogger(getClass());

    public DataModel() {

    }

    public void cleanModel() {

    }

    /**
     * Set initials of the person currently using the application in this browserinstance
     *
     * @param initials
     */
    public void setInitials(String initials){
    }

    public void setIncludeValidatedDeliveries(boolean includeValidatedDeliveries) {

    }

    /**
     * Get initials of the person currently using the application in this browserinstance
     *
     * @return
     */
    public String getInitials() {
        return null;
    }

    /**
     * Get the name of the delivery which is currently in operation
     *
     * @return
     */
    public String getSelectedDelivery() {
        return null;
    }

    /**
     * Set the name of the delivery which is currently in operation
     *
     * @param selectedDelivery
     */
    public void setSelectedDelivery(String selectedDelivery) {

    }

    /**
     * Get the name of the newspapertitle which is currently in operation
     *
     * @return
     */
    public String getSelectedTitle() {
        return null;
    }

    /**
     * Get the name of the newspapersection which is currently in operation
     *
     * @return
     */
    public String getSelectedSection() {
        return null;
    }

    /**
     * Set the name of the newspapersection which is currently in operation
     *
     * @param selectedSection
     */
    public void setSelectedSection(String selectedSection) {

    }

    /**
     * Set the name of the delivery which is currently in operation
     *
     * @param selectedTitle
     */
    public void setSelectedTitle(String selectedTitle) {

    }

    public List<String> getTitlesFromFileSystem() throws Exception {
        initiateTitleHierachyFromFilesystem();
        return null;
    }

    /**
     * Select the TitleDeliveryItem for operation, selection is done with the local parameters of title and
     * deliveryname
     */
    public void selectTitleDelivery() {
    }


    /**
     * Initiate the list of deliveries which is currently beeing in operation
     */
    public void initiateDeliveries() {

    }

    /**
     * Write information to the defined DeliveryTitleInfo
     *
     * @param deliveryName
     * @param titleName
     * @param checked
     * @param initials
     * @param comment
     * @return
     */
    public boolean writeToCurrentItemCashed(String deliveryName, String titleName, boolean checked, String initials, String comment) {

        return false;
    }

    /**
     * Remove a specific cashed title in a delivery
     *
     * @throws Exception
     */
    public void removeCurrentSelectedTitleInDelivery() throws Exception {
    }

    /**
     * Initiate TitleDeliveryHierarchy from fedora, and cash it in the model
     *
     * @throws Exception
     */
    public void initiateTitleHierachyFromFedora() throws Exception {
    }

    /**
     * Initiate TitleDeliveryHierarchy from filesystem, and cash it in the model
     *
     * @throws Exception
     */
    public void initiateTitleHierachyFromFilesystem() throws Exception {
    }

    /**
     * Set the month to the model, the month is used as basis of which deliveries that can currently get validated
     *
     * @param selectedMonth
     */
    public void setSelectedMonth(Date selectedMonth) {

    }

    public void setSelectedMonth(String selectedMonth) {

    }



    /**
     * Get the currently selected month as a string
     *
     * @return
     */
    public String getSelectedMonthString() {
        return "";
    }



}
