package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemDto;
import com.switchfully.order.gui.components.ViewItemForm;
import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route(value = "items", layout = MainLayout.class)
public class DetailsPage extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    private ItemApplicationService itemApplicationService;
    private final H2 header = new H2();

    @Inject
    public DetailsPage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;
    }

    public void generatePage(ItemDto item) {
        header.setText("Item: " + item.getId());
        getContent().add(header, new ViewItemForm(itemApplicationService, item));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String itemId) {
        this.generatePage(itemApplicationService.getItem(itemId));
    }
}
