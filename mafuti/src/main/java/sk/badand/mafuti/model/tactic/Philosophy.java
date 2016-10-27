/*
 * Copyright 2016 Andrej Badinka
 */
package sk.badand.mafuti.model.tactic;

/**
 *
 * @author abadinka
 */
public enum Philosophy {
    ATTACK("philosophy.attack"),
    NORMAL("philosophy.normal"),
    DEFEND("philosophy.defend");
    
    private String key;

    private Philosophy(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}