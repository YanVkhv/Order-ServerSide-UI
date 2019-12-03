package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.switchfully.order.gui.views.DetailsPage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemDetails extends Composite<FlexLayout> {

    private final ItemOverviewDto item;

    public ItemDetails(ItemOverviewDto item) {
        this.item = item;

        H3 name = new H3(item.getName());
        name.setHeight("55px");
        Image itemPlaceholder = new Image("images/itemPlaceholder.png", "Item placeholder image");
        Image stockLow = new Image("icons/stock_low.png", "Low stock icon");
        Image stockMedium = new Image("icons/stock_medium.png", "Medium stock icon");
        Image stockHigh = new Image("icons/stock_high.png", "High stock icon");
        Span price = new Span("Price: € " + item.getPrice());
        Span stock = new Span();

        if (item.getStockUrgency().equals("STOCK_LOW")) {
            stock.add("LOW  ");
            stock.add(stockLow);
        } else if (item.getStockUrgency().equals("STOCK_MEDIUM")) {
            stock.add("MEDIUM ");
            stock.add(stockMedium);
        } else {
            stock.add("HIGH  ");
            stock.add(stockHigh);
        }

        HorizontalLayout priceAndStock = new HorizontalLayout(price, stock);

        VerticalLayout main = new VerticalLayout(name, itemPlaceholder, priceAndStock);
        main.addClickListener(e -> main.getUI().ifPresent(ui -> ui.navigate(DetailsPage.class, item.getId())));
        getContent().add(main);
    }
}
