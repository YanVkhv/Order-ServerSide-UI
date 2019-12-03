package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemDto;
import com.switchfully.order.gui.components.converters.DoubleToIntegerConverter;
import com.switchfully.order.gui.components.converters.FloatToDoubleConverter;
import com.switchfully.order.gui.views.ItemsPage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

public class CreateItemForm extends Composite<VerticalLayout> {

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
    private final Button create = new Button("Create");
    private final CharCounter counter = new CharCounter(0);

    public CreateItemForm(ItemApplicationService itemApplicationService, ItemDto itemDto) {
        this.itemApplicationService = itemApplicationService;

        binder.forField(name)
                .asRequired()
                .bind(ItemDto::getName, ItemDto::withName);

        binder.forField(description)
                .asRequired()
                .withValidator(description -> description.length() <= CharCounter.MAX_ALLOWED_CHARACTERS, "Description length exceeded...")
                .bind(ItemDto::getDescription, ItemDto::withDescription);

        binder.forField(price)
                .asRequired()
                .withValidator(price -> price >= 0.01, "Price cannot be lower than 0.01 €")
                .withConverter(new FloatToDoubleConverter())
                .bind(ItemDto::getPrice, ItemDto::withPrice);

        binder.forField(amountOfStock)
                .withValidator(stock -> stock >= 0, "Stock cannot be lower than 0")
                .asRequired()
                .withConverter(new DoubleToIntegerConverter())
                .bind(ItemDto::getAmountOfStock, ItemDto::withAmountOfStock);


        name.setLabel("Name");
        description.setLabel("Description");
        price.setLabel("Price");
        price.setPlaceholder("0");
        amountOfStock.setLabel("Amount in Stock");
        priceAndStock.add(price, amountOfStock);
        buttons.add(create, cancel);


        create.addClickListener(e -> createItem());
        cancel.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));

        description.setValueChangeMode(ValueChangeMode.EAGER);
        description.addValueChangeListener(e -> {
            completeForm.remove(completeForm.getComponentAt(2));
            completeForm.addComponentAtIndex(2, new CharCounter(description.getValue().length()));
        });

        name.setWidthFull();
        description.setWidthFull();
        create.setWidthFull();
        cancel.setWidth("200px");
        buttons.setWidthFull();
        priceAndStock.setWidthFull();
        create.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        create.setIcon(VaadinIcon.FILE_ADD.create());
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.setIcon(VaadinIcon.CLOSE_SMALL.create());
        description.setPlaceholder("Max. length: " + CharCounter.MAX_ALLOWED_CHARACTERS + " characters");

        binder.setBean(itemDto);

        completeForm.add(name, description, counter, priceAndStock, buttons);
        completeForm.setWidthFull();
        getContent().add(completeForm);
    }

    private void createItem() {
        itemApplicationService.createItem(binder.getBean());
        Notification.show(String.format("Item %s has been %s.", binder.getBean().getName(), " created"));
        UI.getCurrent().navigate(ItemsPage.class);
    }

}
