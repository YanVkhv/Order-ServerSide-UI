package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataChangeEvent;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ItemResultList extends Composite<VerticalLayout> implements DataProviderListener<ItemOverviewDto> {

    private FlexLayout flexLayout = new FlexLayout();
    private ListDataProvider<ItemOverviewDto> listDataProvider;

    public ItemResultList(ListDataProvider<ItemOverviewDto> listDataProvider) {
        this.listDataProvider = listDataProvider;
        this.listDataProvider.addDataProviderListener(this);
        refreshList().stream()
                .forEach(itemResult -> flexLayout.add(itemResult));
        flexLayout.setWrapMode(FlexLayout.WrapMode.WRAP);
        getContent().add(flexLayout);
        getContent().setSizeFull();
    }

    private List<ItemResult> refreshList() {
        getContent().removeAll();
        return this.listDataProvider
                .fetch(new Query<>())
                .map(ItemResult::new)
                .collect(Collectors.toList());
    }

    @Override
    public void onDataChange(DataChangeEvent<ItemOverviewDto> dataChangeEvent) {
        refreshList();
    }
}
