package org.bpaye1.research.controller.editor;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;

/**
 * Custom Date Editor for Joda LocalDate objects
 */
public class LocalDateCustomEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    public LocalDateCustomEditor(DateTimeFormatter formatter){
        this.formatter = formatter;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(formatter.parseLocalDate(text));
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        LocalDate value = (LocalDate) getValue();
        return (value != null ? value.toString(Pattern.localDate()) : "");
    }
}
