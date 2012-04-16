package org.bpaye1.research.controller.editor;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;

/**
 * Custom Date Editor for Joda LocalTime objects
 */
public class LocalTimeCustomEditor extends PropertyEditorSupport {
    private final DateTimeFormatter formatter;

    public LocalTimeCustomEditor(DateTimeFormatter formatter){
        this.formatter = formatter;
    }

    /**
     * Parse the Time from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(formatter.parseLocalTime(text));
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
        }
    }

    /**
     * Format the Time as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        LocalTime value = (LocalTime) getValue();
        return (value != null ? value.toString("hh:mm") : "");
    }
}
