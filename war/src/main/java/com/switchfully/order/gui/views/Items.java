package com.switchfully.order.gui.views;

import com.switchfully.order.gui.layouts.LayoutWithHeaderAndContent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "items", layout = LayoutWithHeaderAndContent.class)
public class Items extends VerticalLayout {

    public Items() {
    }
}
