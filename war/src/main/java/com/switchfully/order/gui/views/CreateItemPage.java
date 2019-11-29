package com.switchfully.order.gui.views;

import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "create", layout = MainLayout.class)
public class CreateItemPage extends Composite<VerticalLayout> {

    private final H2 header = new H2("Create Item");

    public CreateItemPage() {
        getContent().add(header);
    }
}
