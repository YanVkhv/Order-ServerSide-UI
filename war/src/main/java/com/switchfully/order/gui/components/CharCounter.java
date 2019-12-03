package com.switchfully.order.gui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;

public class CharCounter extends Composite<Span> {

    public static final int MAX_ALLOWED_CHARACTERS = 255;
    private final String charCounter;

    public CharCounter(int amountOfCharacters) {

        if (amountOfCharacters >= MAX_ALLOWED_CHARACTERS) {
            getContent().getStyle().set("color", "red");
            charCounter = 0 + "/" + MAX_ALLOWED_CHARACTERS;
            getContent().add(charCounter);
        } else {
            charCounter = MAX_ALLOWED_CHARACTERS - amountOfCharacters + "/" + MAX_ALLOWED_CHARACTERS;
            getContent().add(charCounter);
        }
    }
}
