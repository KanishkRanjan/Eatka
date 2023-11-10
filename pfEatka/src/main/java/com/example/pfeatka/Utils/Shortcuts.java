package com.example.pfeatka.Utils;

import javafx.scene.input.KeyCode;

public enum Shortcuts {


    save(new KeyCode[]{KeyCode.CONTROL ,KeyCode.S}),
    saveAs(new KeyCode[]{KeyCode.CONTROL , KeyCode.SHIFT, KeyCode.S}),
    open(new KeyCode[]{KeyCode.CONTROL , KeyCode.O});
    private final KeyCode[] keySequence ;

    public  KeyCode[] getCommand()
    {
        return this.keySequence;
    }
    Shortcuts(KeyCode[] keySequence) {
        this.keySequence = keySequence;
    }
}
