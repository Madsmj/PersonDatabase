package com.anychart.controllers.window;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Window for creating user
 */
public class PopupWindow extends Window {

    private final HorizontalLayout contentPanel = new HorizontalLayout();
    private final VerticalLayout vl = new VerticalLayout();
    private final HorizontalLayout hl = new HorizontalLayout();
    private com.vaadin.ui.Layout resultPanel;

    private final Button ok = new Button("Ok");
    private final Button cancel = new Button("Cancel");

    public PopupWindow(String caption, boolean showOkCancel) {
        super(caption);

        contentPanel.setMargin(true);

        ok.setId("OKBUTTON");
        cancel.setId("CANCELBUTTON");

        hl.addComponent(ok);
        hl.addComponent(cancel);
        vl.addComponent(contentPanel);
        if(showOkCancel) {
            vl.addComponent(hl);
        }
        super.setContent(vl);
    }

    /**
     * Set listeners to the buttons in the dialog
     * @param listener
     */
    public void setListener(Button.ClickListener listener) {
        ok.addClickListener(listener);
        cancel.addClickListener(listener);
    }

    /**
     * Set the dialog to be ready or not ready to perform a press on ok.
     * @param ready if the parameter is false the "ok" button is disabled and a comment about why it is disabled is shown
     */
    public void setReady(boolean ready) {
        ok.setEnabled(ready);
    }

    /**
     * Insert the content to view in the dialog
     * @param content
     */
    public void setDialogContent(com.vaadin.ui.Layout content) {
        resultPanel = content;
        contentPanel.addComponent(resultPanel);
    }
}
