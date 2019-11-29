package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemResult extends Composite<VerticalLayout> {

    private ItemOverviewDto item;

    public ItemResult(ItemOverviewDto item) {
        this.item = item;
        VerticalLayout main = new VerticalLayout();

        ItemDetails itemDetails = new ItemDetails(this.item);

        getContent().getElement().getStyle().set("margin-top", "0");
        getContent().getElement().getStyle().set("margin-bottom", "0");
        getContent().add(itemDetails);
    }
}
