package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemDto;
import com.switchfully.order.gui.components.converters.DoubleToIntegerConverter;
import com.switchfully.order.gui.components.converters.FloatToIntegerConverter;
import com.switchfully.order.gui.views.ItemsPage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ViewItemForm extends Composite<VerticalLayout> {

    private final ItemApplicationService itemApplicationService;
    private final TextField name = new TextField();
    private final TextArea description = new TextArea();
    private final NumberField price = new NumberField();
    private final NumberField amountOfStock = new NumberField();
    private final HorizontalLayout priceAndStock = new HorizontalLayout();
    private final HorizontalLayout buttons = new HorizontalLayout();
    private final VerticalLayout completeForm = new VerticalLayout();
    private final Binder<ItemDto> binder = new Binder<>(ItemDto.class);
    private final Button cancel = new Button("Cancel");
    private final Button edit = new Button("Edit");

    public ViewItemForm(ItemApplicationService itemApplicationService, ItemDto itemDto) {
        this.itemApplicationService = itemApplicationService;

        binder.forField(name)
                .bind(ItemDto::getName, null);

        binder.forField(description)
                .bind(ItemDto::getDescription, null);

        binder.forField(price)
                .withConverter(new FloatToIntegerConverter())
                .bind(ItemDto::getPrice, null);

        binder.forField(amountOfStock)
                .withConverter(new DoubleToIntegerConverter())
                .bind(ItemDto::getAmountOfStock, null);

        name.setLabel("Name");
        description.setLabel("Description");
        price.setLabel("Price");
        amountOfStock.setLabel("Amount in Stock");
        priceAndStock.add(price, amountOfStock);
        buttons.add(edit, cancel);

        completeForm.add(name, description, priceAndStock, buttons);

        edit.addClickListener(e -> {
            getContent().removeAll();
            getContent().add(new UpdateItemForm(itemApplicationService, itemDto));
        });
        cancel.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));

        name.setWidthFull();
        description.setWidthFull();
        edit.setWidthFull();
        cancel.setWidth("200px");
        buttons.setWidthFull();
        priceAndStock.setWidthFull();
        edit.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        edit.setIcon(VaadinIcon.EDIT.create());
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.setIcon(VaadinIcon.CLOSE_SMALL.create());
        price.setMin(0.1);

        binder.setBean(itemDto);

        getContent().add(completeForm);
    }

}
