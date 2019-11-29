package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;

public class CreateItemForm extends Composite<FormLayout> {

    private ListDataProvider<ItemOverviewDto> itemDataProvider;
    private final TextField name = new TextField();
    private final TextArea description = new TextArea();
    private final NumberField price = new NumberField();
    private final NumberField stockAmount = new NumberField();
    private final HorizontalLayout priceAndStock = new HorizontalLayout();
    private final VerticalLayout completeForm = new VerticalLayout();

    public CreateItemForm(ListDataProvider<ItemOverviewDto> itemDataProvider) {
        this.itemDataProvider = itemDataProvider;

        name.setLabel("Name");
        description.setLabel("Description");
        price.setLabel("Price");
        stockAmount.setLabel("Amount in Stock");
        priceAndStock.add(price, stockAmount);
        completeForm.add(name, description, priceAndStock);

        description.setMaxLength(255);
        description.setPlaceholder("Max. length: " + description.getMaxLength() + " characters");
        description.setHeight("200px");
        description.setWidth("790px");
        name.setWidth("790px");

        getContent().add(completeForm);
    }
}
