package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemDto;
import com.switchfully.order.gui.components.converters.DoubleToIntegerConverter;
import com.switchfully.order.gui.components.converters.FloatToIntegerConverter;
import com.switchfully.order.gui.views.DetailsPage;
import com.switchfully.order.gui.views.HomePage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

public class UpdateItemForm extends Composite<FormLayout> {

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
    private Button update = new Button("Update");
    private CharCounter counter = new CharCounter(0);

    public UpdateItemForm(ItemApplicationService itemApplicationService, ItemDto itemDto) {
        this.itemApplicationService = itemApplicationService;

        binder.forField(name)
                .asRequired()
                .bind(ItemDto::getName, ItemDto::withName);

        binder.forField(description)
                .withValidator(description -> description.length() <= 255, "Description length exceeded...")
                .bind(ItemDto::getDescription, ItemDto::withDescription);

        binder.forField(price)
                .asRequired()
                .withConverter(new FloatToIntegerConverter())
                .withValidator(aDouble -> aDouble > 0.1, "Price cannot be lower than 0.1 €")
                .bind(ItemDto::getPrice, ItemDto::withPrice);

        binder.forField(amountOfStock)
                .withValidator(aDouble -> aDouble >= 0, "Stock cannot be lower than 0")
                .asRequired()
                .withConverter(new DoubleToIntegerConverter())
                .bind(ItemDto::getAmountOfStock, ItemDto::withAmountOfStock);

        name.setLabel("Name");
        description.setLabel("Description");
        price.setLabel("Price");
        amountOfStock.setLabel("Amount in Stock");
        priceAndStock.add(price, amountOfStock);
        buttons.add(update, cancel);

        completeForm.add(name, description, counter, priceAndStock, buttons);

        update.addClickListener(e -> updateItem(itemDto.getId()));
        cancel.addClickListener(e -> UI.getCurrent().navigate(HomePage.class));

        description.setValueChangeMode(ValueChangeMode.EAGER);
        description.addValueChangeListener(e -> {
            completeForm.remove(completeForm.getComponentAt(2));
            completeForm.addComponentAtIndex(2, new CharCounter(description.getValue().length()));
        });

        update.setWidth("660px");
        cancel.setWidth("120px");
        update.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        update.setIcon(VaadinIcon.CLIPBOARD_CHECK.create());
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.setIcon(VaadinIcon.CLOSE_SMALL.create());
        description.setPlaceholder("Max. length: 255 characters");
        description.setHeight("200px");
        description.setWidth("790px");
        name.setWidth("790px");

        binder.setBean(itemDto);

        getContent().add(completeForm);
    }

    private void updateItem(String itemId) {
        itemApplicationService.updateItem(itemId, binder.getBean());
        Notification.show(String.format("Item %s has been %s.", binder.getBean().getName(), " updated"));
        this.getContent().removeAll();
        UI.getCurrent().navigate(DetailsPage.class, itemId);
    }

}
