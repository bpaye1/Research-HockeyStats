package org.bpaye1.research.controller.editor;

import org.bpaye1.research.repository.PlayerRepository;

public interface CustomEditorFactory {
    LocalDateCustomEditor createLocalDateCustomEditor();
    LocalTimeCustomEditor createLocalTimeCustomEditor();
    PlayerCustomEditor createPlayerCustomEditor(PlayerRepository repository);
}
