package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemResult extends Composite<VerticalLayout> {

    private ItemOverviewDto item;

    public ItemResult(ItemOverviewDto item) {
        this.item = item;

        ItemDetails itemDetails = new ItemDetails(this.item);

        getContent().setWidth("268px");
        getContent().setPadding(false);
        getContent().add(itemDetails);
    }
}
