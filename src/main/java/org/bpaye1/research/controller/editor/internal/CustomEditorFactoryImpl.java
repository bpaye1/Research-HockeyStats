package org.bpaye1.research.controller.editor.internal;

import org.bpaye1.research.controller.editor.*;
import org.bpaye1.research.repository.PlayerRepository;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class CustomEditorFactoryImpl implements CustomEditorFactory {

    public LocalDateCustomEditor createLocalDateCustomEditor(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(Pattern.localDate());
        return new LocalDateCustomEditor(dateTimeFormatter);
    }

    public LocalTimeCustomEditor createLocalTimeCustomEditor(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(Pattern.localTime());
        return new LocalTimeCustomEditor(dateTimeFormatter);
    }

    public PlayerCustomEditor createPlayerCustomEditor(PlayerRepository repository) {
        return new PlayerCustomEditor(repository);
    }
}
