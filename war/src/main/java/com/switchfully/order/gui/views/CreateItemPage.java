package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemOverviewDto;
import com.switchfully.order.gui.components.CreateItemForm;
import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route(value = "create", layout = MainLayout.class)
public class CreateItemPage extends Composite<VerticalLayout> {

    private ItemApplicationService itemApplicationService;
    private final H2 header = new H2("Create Item");

    public CreateItemPage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;

        ListDataProvider<ItemOverviewDto> itemOverviewDtoListDataProvider = new ListDataProvider<>(itemApplicationService.getAllItems(null));

        getContent().add(header, new CreateItemForm(itemOverviewDtoListDataProvider));
    }
}
