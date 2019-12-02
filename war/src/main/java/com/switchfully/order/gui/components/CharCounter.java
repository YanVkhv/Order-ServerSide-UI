package com.switchfully.order.gui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;

public class CharCounter extends Composite<Span> {

    public static final int MAX_ALLOWED_CHARACTERS = 255;
    private int amountOfCharacters = 0;
    private String charCounter;

    public CharCounter() {
        charCounter = amountOfCharacters + "/" + MAX_ALLOWED_CHARACTERS;
        getContent().add(charCounter);
    }
}
