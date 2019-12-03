package com.switchfully.order.gui.layouts;

import com.switchfully.order.gui.views.CreateItemPage;
import com.switchfully.order.gui.views.CustomersPage;
import com.switchfully.order.gui.views.ItemsPage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
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
    private final Button homepage = new Button("Items");
    private final Button create = new Button("Create Item");
    private final Button customers = new Button("Customers");
    private final Button nav4 = new Button("Nav #4");
    private final Image orderIcon = new Image("icons/order-icon.png", "Order icon");
    private final Image orderFooter = new Image("images/order-footer.png", "Order footer image");
    private final Div content = new Div();

    public MainLayout() {
        orderIcon.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));
        homepage.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));
        create.addClickListener(e -> UI.getCurrent().navigate(CreateItemPage.class));
        customers.addClickListener(e -> UI.getCurrent().navigate(CustomersPage.class));
        nav4.addClickListener(e -> UI.getCurrent().navigate(ItemsPage.class));

        navBar.getStyle().set("margin-left", "auto");
        navBar.setWrapMode(FlexLayout.WrapMode.WRAP);
        header.setSizeFull();
        orderFooter.setSizeFull();
        orderIcon.setSizeFull();
        orderIcon.setHeight("61px");
        orderIcon.setWidth("255px");
        navTop.add(homepage, create);
        navBottom.add(customers, nav4);
        navBar.add(navTop, navBottom);
        header.add(orderIcon, navBar);
        content.setSizeFull();

        getContent().add(header);
        getContent().add(content);
        getContent().add(orderFooter);
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

