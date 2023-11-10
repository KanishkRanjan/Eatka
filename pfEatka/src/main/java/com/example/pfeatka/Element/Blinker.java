package com.example.pfeatka.Element;

import com.example.pfeatka.Element.Text.SpecialCharacter;

import java.util.Objects;


public class Blinker {


    public int selectedCharacterIndex = 0;
    Membrane membrane;

    public Blinker(Membrane membrane){
        this.membrane = membrane;
    }

    public int getSelectedCharacterIndex(){
        return this.selectedCharacterIndex;
    }
    public void rerender()
    {
        setBlinkerPosition( this.selectedCharacterIndex);
    }
    public void setBlinkerPosition(int selectedCharacterIndex){

        this.selectedCharacterIndex = selectedCharacterIndex;
        double width = membrane.membraneData.data.get( this.selectedCharacterIndex).width;
        if(Objects.equals(membrane.membraneData.data.get(this.selectedCharacterIndex).getText(), SpecialCharacter.lineStart.getCommand())) width = 2;
        membrane.gc.fillRect(membrane.membraneData.data.get( this.selectedCharacterIndex).getxPosition()+width, membrane.membraneData.data.get( this.selectedCharacterIndex).getyPosition() - membrane.membraneData.data.get( this.selectedCharacterIndex).getHeight() , membrane.membraneData.data.get( this.selectedCharacterIndex).getFont().toFont().getSize()*0.09 , membrane.membraneData.data.get( this.selectedCharacterIndex).getHeight());
    }
}
