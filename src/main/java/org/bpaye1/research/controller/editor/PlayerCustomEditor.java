package org.bpaye1.research.controller.editor;

import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;

import java.beans.PropertyEditorSupport;

public class PlayerCustomEditor extends PropertyEditorSupport {

    private PlayerRepository repository;

    public PlayerCustomEditor(PlayerRepository repository){
        this.repository = repository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(repository.find(Long.valueOf(text)));
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Could not parse player: " + ex.getMessage(), ex);
        }
    }

    @Override
    public String getAsText() {
        Player value = (Player) getValue();
        return (value != null ? String.valueOf(value.getId()) : "");
    }
}
