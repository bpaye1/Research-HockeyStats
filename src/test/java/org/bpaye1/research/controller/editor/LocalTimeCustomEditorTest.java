package org.bpaye1.research.controller.editor;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocalTimeCustomEditorTest {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(Pattern.localTime());

    @Test
    public void setAsText() throws Exception {
        LocalTimeCustomEditor customEditor = new LocalTimeCustomEditor(formatter);
        customEditor.setAsText("20:30");
        LocalTime customEditorValue = (LocalTime)customEditor.getValue();
        assertThat(customEditorValue.getHourOfDay(), is(20));
        assertThat(customEditorValue.getMinuteOfHour(), is(30));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAsText_shouldFailWhenParsingAnInValidTime() throws Exception {
        LocalTimeCustomEditor customEditor = new LocalTimeCustomEditor(formatter);
        customEditor.setAsText("2012:393A");
    }

    @Test
    public void getAsText() throws Exception {
        LocalTimeCustomEditor customEditor = new LocalTimeCustomEditor(formatter);
        String time = "09:45";
        customEditor.setAsText(time);
        assertThat(customEditor.getAsText(), is(time));
    }

    @Test
    public void getAsText_shouldReturnBlankTextWhenNoValueIsPassedIn() throws Exception {
        LocalTimeCustomEditor customEditor = new LocalTimeCustomEditor(formatter);
        assertThat(customEditor.getAsText(), is(StringUtils.EMPTY));
    }
}
