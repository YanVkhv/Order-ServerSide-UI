package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class ItemResultList extends Composite<VerticalLayout> {

    private List<ItemOverviewDto> items = new ArrayList<>();

    public void setItems(List<ItemOverviewDto> items) {
        this.items = items;
        refreshList();
    }

    private void refreshList() {
        getContent().removeAll();
        this.items
                .stream()
                .map(ItemResult::new)
                .forEach(itemResult -> this.getContent().add(itemResult));
    }

}
