package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemOverviewDto;
import com.switchfully.order.gui.components.FilterResults;
import com.switchfully.order.gui.components.ItemResultList;
import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route(value = "homepage", layout = MainLayout.class)
public class HomePage extends Composite<VerticalLayout> {

    private ItemApplicationService itemApplicationService;
    private final H2 headerTitle = new H2("Items");

    @Inject
    public HomePage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;

        ListDataProvider<ItemOverviewDto> itemOverviewDtoListDataProvider = new ListDataProvider<>(itemApplicationService.getAllItems(null));

        ItemResultList results = new ItemResultList(itemOverviewDtoListDataProvider);

        HorizontalLayout header = new HorizontalLayout(headerTitle, new FilterResults(itemOverviewDtoListDataProvider));
        getContent().add(header, results);
    }
}
