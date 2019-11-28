package com.switchfully.order.gui.layouts;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
public class LayoutWithHeaderAndContent extends Composite<VerticalLayout> implements RouterLayout {
    private final Span header = new Span("Enter header here");
    private final Span content = new Span("Enter content here");

    public LayoutWithHeaderAndContent() {
        getContent().add(header, content);
    }
}
