package com.switchfully.order.gui.views;

import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "create_customer", layout = MainLayout.class)
public class CreateCustomerPage extends Composite<VerticalLayout> {

    private final H2 headerTitle = new H2("Create Customer");

    public CreateCustomerPage() {
        headerTitle.getElement().getStyle().set("margin", "0px");
        headerTitle.getElement().getStyle().set("padding", "12px");
        headerTitle.setSizeFull();
        getContent().add(headerTitle);
    }
}
