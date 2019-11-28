package com.switchfully.order.gui.layouts;

import com.switchfully.order.gui.views.HomePage;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import java.util.Objects;

public class MainLayout extends Composite<VerticalLayout> implements RouterLayout {
    private HorizontalLayout navBar = new HorizontalLayout();
    private RouterLink toItems = new RouterLink("Items", HomePage.class);
    private RouterLink nav2 = new RouterLink("Nav #2", HomePage.class);
    private RouterLink nav3 = new RouterLink("Nav #3", HomePage.class);
    private RouterLink nav4 = new RouterLink("Nav #4", HomePage.class);
    private Image orderIcon = new Image("icons/order-icon.png", "Order icon");
    private Image orderFooter = new Image("icons/order-footer.png", "Order footer image");
    private Div content = new Div();

    public MainLayout() {
        navBar.add(orderIcon, toItems, nav2, nav3, nav4);
        getContent().add(navBar);
        getContent().add(content);
        getContent().add(orderFooter);
        getContent().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

    @Override
    public void showRouterLayoutContent(HasElement element) {
        Objects.requireNonNull(element);
        Objects.requireNonNull(element.getElement());
        content.removeAll();
        content.getElement().appendChild(element.getElement());
    }
}

