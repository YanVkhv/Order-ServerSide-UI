package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemDetails extends Composite<VerticalLayout> {

    private ItemOverviewDto item;

    public ItemDetails(ItemOverviewDto item) {
        this.item = item;

        H3 name = new H3(item.getName());
        Div imagePlaceHolder = new Div();
        imagePlaceHolder.add("Image here");
        Div price = new Div();
        price.add(String.valueOf(item.getPrice()));
        Div stock = new Div();
        stock.add(item.getStockUrgency());
        HorizontalLayout priceAndStock = new HorizontalLayout(price, stock);

        VerticalLayout main = new VerticalLayout(name, imagePlaceHolder, priceAndStock);
        getContent().add(main);

    }
}
