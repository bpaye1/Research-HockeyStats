package org.bpaye1.research.controller.editor;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocalDateCustomEditorTest {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern(Pattern.localDate());

    @Test
    public void setAsText() throws Exception {
        LocalDateCustomEditor customEditor = new LocalDateCustomEditor(formatter);
        customEditor.setAsText("11/12/2012");
        LocalDate customEditorValue = (LocalDate)customEditor.getValue();
        assertThat(customEditorValue.getYear(), is(2012));
        assertThat(customEditorValue.getMonthOfYear(), is(11));
        assertThat(customEditorValue.dayOfMonth().get(), is(12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAsText_shouldFailWhenParsingAnInValidDate() throws Exception {
        LocalDateCustomEditor customEditor = new LocalDateCustomEditor(formatter);
        customEditor.setAsText("11-12-2012AA");
    }

    @Test
    public void getAsText() throws Exception {
        LocalDateCustomEditor customEditor = new LocalDateCustomEditor(formatter);
        String date = "11/01/2012";
        customEditor.setAsText(date);
        assertThat(customEditor.getAsText(), is(date));
    }

    @Test
    public void getAsText_shouldReturnBlankTextWhenNoValueIsPassedIn() throws Exception {
        LocalDateCustomEditor customEditor = new LocalDateCustomEditor(formatter);
        assertThat(customEditor.getAsText(), is(StringUtils.EMPTY));
    }
}
