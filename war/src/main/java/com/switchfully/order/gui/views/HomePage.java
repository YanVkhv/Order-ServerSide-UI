package com.switchfully.order.gui.views;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemOverviewDto;
import com.switchfully.order.gui.components.FilterResults;
import com.switchfully.order.gui.components.ItemResultList;
import com.switchfully.order.gui.layouts.LayoutWithHeader;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route(value = "homepage", layout = LayoutWithHeader.class)
public class HomePage extends Composite<VerticalLayout> {

    private ItemApplicationService itemApplicationService;

    @Inject
    public HomePage(ItemApplicationService itemApplicationService) {
        this.itemApplicationService = itemApplicationService;

        ListDataProvider<ItemOverviewDto> itemOverviewDtoListDataProvider = new ListDataProvider<>(itemApplicationService.getAllItems(null));

        ItemResultList results = new ItemResultList(itemOverviewDtoListDataProvider);

        getContent().add(new FilterResults(itemOverviewDtoListDataProvider));
        getContent().add(results);
    }
}
