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
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ViewItemForm extends Composite<FormLayout> {

    private ItemApplicationService itemApplicationService;
    private TextField name = new TextField();
    private TextArea description = new TextArea();
    private NumberField price = new NumberField();
    private NumberField amountOfStock = new NumberField();
    private HorizontalLayout priceAndStock = new HorizontalLayout();
    private HorizontalLayout buttons = new HorizontalLayout();
    private VerticalLayout completeForm = new VerticalLayout();
    private Binder<ItemDto> binder = new Binder<>(ItemDto.class);
    private Button cancel = new Button("Cancel");
    private Button edit = new Button("Edit");

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

        edit.setWidth("660px");
        cancel.setWidth("120px");
        edit.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        edit.setIcon(VaadinIcon.EDIT.create());
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.setIcon(VaadinIcon.CLOSE_SMALL.create());
        description.setHeight("200px");
        description.setWidth("790px");
        name.setWidth("790px");

        binder.setBean(itemDto);

        getContent().add(completeForm);
    }

}
