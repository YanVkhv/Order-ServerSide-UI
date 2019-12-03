package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.data.provider.DataChangeEvent;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;

public class ItemResultList extends Composite<FlexLayout> implements DataProviderListener<ItemOverviewDto> {

    private ListDataProvider<ItemOverviewDto> listDataProvider;

    public ItemResultList(ListDataProvider<ItemOverviewDto> listDataProvider) {
        this.listDataProvider = listDataProvider;
        this.listDataProvider.addDataProviderListener(this);

        refreshList();

        getContent().setWrapMode(FlexLayout.WrapMode.WRAP);
        getContent().setSizeFull();
    }

    private void refreshList() {
        getContent().removeAll();
        this.listDataProvider
                .fetch(new Query<>())
                .map(ItemResult::new)
                .forEach(itemResult -> this.getContent().add(itemResult));
    }

    @Override
    public void onDataChange(DataChangeEvent<ItemOverviewDto> dataChangeEvent) {
        refreshList();
    }
}
