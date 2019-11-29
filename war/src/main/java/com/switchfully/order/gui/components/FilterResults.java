package com.switchfully.order.gui.components;

import com.switchfully.order.api.items.ItemApplicationService;
import com.switchfully.order.api.items.ItemOverviewDto;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import javax.inject.Inject;

public class FilterResults extends Composite<HorizontalLayout> {

    private final TextField filter = new TextField();
    private final Button searchTextBtn = new Button(new Icon(VaadinIcon.SEARCH));
    private final Div filtering = new Div();
    private ListDataProvider<ItemOverviewDto> itemDataProvider;

    @Inject
    public FilterResults(ListDataProvider<ItemOverviewDto> itemDataProvider) {
        this.itemDataProvider = itemDataProvider;

        filter.setPlaceholder("Filter by name...");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> itemDataProvider.setFilter(
                ItemOverviewDto::getName, name -> name.toLowerCase().startsWith(e.getValue().toLowerCase())));

        searchTextBtn.getElement().setProperty("title", "Search");

        filtering.add(filter, searchTextBtn);

        getContent().add(filtering);
    }
}
