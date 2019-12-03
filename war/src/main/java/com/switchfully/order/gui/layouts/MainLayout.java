package com.switchfully.order.gui.layouts;

import com.switchfully.order.gui.views.CreateCustomerPage;
import com.switchfully.order.gui.views.CreateItemPage;
import com.switchfully.order.gui.views.CustomersPage;
import com.switchfully.order.gui.views.ItemsPage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

import java.util.Objects;

public class MainLayout extends Composite<VerticalLayout> implements RouterLayout {
    private final FlexLayout navBar = new FlexLayout();
    private final HorizontalLayout header = new HorizontalLayout();
    private final FlexLayout navTop = new FlexLayout();
    private final FlexLayout navBottom = new FlexLayout();
    private final Button items = new Button("Items");
    private final Button createItem = new Button("Create Item");
    private final Button customers = new Button("Customers");
    private final Button createCustomer = new Button("Create Customer");
    private final Image orderIcon = new Image("icons/order-icon.png", "Order icon");
    private final Image orderFooter = new Image("images/order-footer.png", "Order footer image");
    private final Div content = new Div();

    public MainLayout() {
        orderIcon.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));
        items.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));
        createItem.addClickListener(e -> UI.getCurrent().navigate(CreateItemPage.class));
        customers.addClickListener(e -> UI.getCurrent().navigate(CustomersPage.class));
        createCustomer.addClickListener(e -> UI.getCurrent().navigate(CreateCustomerPage.class));

        navBar.getStyle().set("margin-left", "auto");
        navBar.setWrapMode(FlexLayout.WrapMode.WRAP);
        header.setSizeFull();
        orderFooter.setSizeFull();
        orderIcon.setSizeFull();
        orderIcon.setHeight("61px");
        orderIcon.setWidth("255px");
        navTop.add(items, createItem);
        navBottom.add(customers, createCustomer);
        navBar.add(navTop, navBottom);
        header.add(orderIcon, navBar);
        content.setSizeFull();

        getContent().add(header);
        getContent().add(content);
        getContent().add(orderFooter);
        getContent().getStyle().set("margin", "auto");
        getContent().setMaxWidth("900px");
    }

    @Override
    public void showRouterLayoutContent(HasElement element) {
        Objects.requireNonNull(element);
        Objects.requireNonNull(element.getElement());
        content.removeAll();
        content.getElement().appendChild(element.getElement());
    }
}

