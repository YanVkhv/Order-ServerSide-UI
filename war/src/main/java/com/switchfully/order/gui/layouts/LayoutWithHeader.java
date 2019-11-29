package com.switchfully.order.gui.layouts;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
public class LayoutWithHeader extends Composite<VerticalLayout> implements RouterLayout {

    private final H2 header = new H2("Items");

    public LayoutWithHeader() {
        getContent().add(header);
    }
}
