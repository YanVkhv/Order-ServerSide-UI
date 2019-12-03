package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemDto;
import com.switchfully.order.gui.components.CreateItemForm;
import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route(value = "create", layout = MainLayout.class)
public class CreateItemPage extends Composite<VerticalLayout> {

    private ItemApplicationService itemApplicationService;
    private final H2 header = new H2("Create Item");

    @Inject
    public CreateItemPage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;

        getContent().add(header, new CreateItemForm(itemApplicationService, new ItemDto()));
    }
}
