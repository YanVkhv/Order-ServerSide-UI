package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.gui.components.ItemResultList;
import com.switchfully.order.gui.layouts.LayoutWithHeader;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route(value = "homepage", layout = LayoutWithHeader.class)
public class HomePage extends Composite<VerticalLayout> {

    private ItemApplicationService itemApplicationService;

    @Inject
    public HomePage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;

        ItemResultList results = new ItemResultList();

        results.setItems(itemApplicationService.getAllItems(null));

        getContent().add(results);
        getContent().add(new Span("test"));
    }
}
