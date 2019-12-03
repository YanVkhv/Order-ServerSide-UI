package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemOverviewDto;
import com.switchfully.order.gui.components.FilterResults;
import com.switchfully.order.gui.components.ItemResultList;
import com.switchfully.order.gui.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route(value = "items", layout = MainLayout.class)
public class ItemsPage extends Composite<VerticalLayout> {

    private ItemApplicationService itemApplicationService;
    private final H2 headerTitle = new H2("Items");

    @Inject
    public ItemsPage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;

        ListDataProvider<ItemOverviewDto> itemOverviewDtoListDataProvider = new ListDataProvider<>(itemApplicationService.getAllItems(null));

        ItemResultList results = new ItemResultList(itemOverviewDtoListDataProvider);

        HorizontalLayout header = new HorizontalLayout(headerTitle, new FilterResults(itemOverviewDtoListDataProvider));

        headerTitle.getElement().getStyle().set("margin", "0px");
        headerTitle.getElement().getStyle().set("padding", "12px");
        headerTitle.setSizeFull();
        header.setSizeFull();

        getContent().add(header, results);
    }
}
