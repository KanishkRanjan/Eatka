package com.example.pfeatka.Utils;

import com.example.pfeatka.Controllers.Page;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;

public class KeywordManager {

    Page selectedPage ;
    boolean isCapsOn = true;
    ArrayList<KeyCode> keysPressed = new ArrayList<>();
    public KeywordManager(Page page){
        selectedPage = page;
    }
    public void resisterKeyPress(KeyEvent event, SerializableFont currFont , boolean isUnderline , boolean isStrikeThrough){
//            selectedPage.getMembrane().addCharacter(keyCode.getChar() , currFont);
//        System.out.println("pussy "+currFont.toFont().getSize());
//        System.out.println(keyCode.);
//        if(KeyCode)
//        keyPressed[0]
        if(event.getCode() == KeyCode.CAPS) isCapsOn = !isCapsOn;
        keysPressed.add(event.getCode());
        if(event.getCode() == KeyCode.SHIFT) return;
        String keyPressed = event.getCode().getChar();
        if(event.isShiftDown() ^ isCapsOn){
            keyPressed =  keyPressed.toLowerCase();
        }
        if(event.isControlDown()){
            if(event.getCode() == KeyCode.S && event.isShiftDown()){
//                selectedPage.getMembrane()
                System.out.println("Save command");
            }
            else if(event.getCode() == KeyCode.S){
                System.out.println("sdf");
            }
        }
        else if(event.getCode().isLetterKey()) selectedPage.getMembrane().addCharacter(keyPressed, currFont);
        else if(event.getCode().isDigitKey()) {
            if(event.getCode() == KeyCode.DIGIT0 || event.getCode() == KeyCode.NUMPAD0 ){
                keyPressed = event.isShiftDown() ?  ")" : "0" ;
            }
            else if(event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.NUMPAD1){
                keyPressed = event.isShiftDown() ?   "!": "1";
            }
            else if(event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.NUMPAD2){
                keyPressed = event.isShiftDown() ?  "@": "2";
            }
            else if(event.getCode() == KeyCode.DIGIT3 || event.getCode() == KeyCode.NUMPAD3){
                keyPressed = event.isShiftDown() ? "#":"3";
            }
            else if(event.getCode() == KeyCode.DIGIT4 || event.getCode() == KeyCode.NUMPAD4){
                keyPressed = event.isShiftDown() ? "$":"4";
            }
            else if(event.getCode() == KeyCode.DIGIT5 || event.getCode() == KeyCode.NUMPAD5){
                keyPressed = event.isShiftDown() ? "%": "5";
            }
            else if(event.getCode() == KeyCode.DIGIT6 || event.getCode() == KeyCode.NUMPAD6){
                keyPressed = event.isShiftDown() ? "^": "6" ;
            }
            else if(event.getCode() == KeyCode.DIGIT7 || event.getCode() == KeyCode.NUMPAD7){
                keyPressed = event.isShiftDown() ? "&": "7";
            }
            else if(event.getCode() == KeyCode.DIGIT8 || event.getCode() == KeyCode.NUMPAD8){
                keyPressed = event.isShiftDown() ? "*": "8"  ;
            }
            else if(event.getCode() == KeyCode.DIGIT9 || event.getCode() == KeyCode.NUMPAD9){
                keyPressed = event.isShiftDown() ? "(":"9";
            }
//            event.getCode().
            selectedPage.getMembrane().addCharacter(keyPressed, currFont);
            System.out.println((KeyCode.BACK_QUOTE));
//        } else if(event.getCode().)
//        System.out.println((event.getCode().getClass()) == KeyCode.Num);
//        else if(event)
//        System.out.println(keyPressed   );
        }
        else if (event.getCode() == KeyCode.BACK_SPACE){
            selectedPage.getMembrane().removeCharFront();
        }
        else if(event.getCode() == KeyCode.DELETE){
            selectedPage.getMembrane().removeCharBack();
        }
        else if(event.getCode().isArrowKey()){
            if(event.getCode() == KeyCode.UP){
                selectedPage.getMembrane().moveTheBlinkerVertically(+1);
            }
            else if(event.getCode() == KeyCode.DOWN){
                selectedPage.getMembrane().moveTheBlinkerVertically(-1);
            }
            else if(event.getCode() == KeyCode.LEFT){
                selectedPage.getMembrane().moveTheBlinkerHorizontally(-1);
            }
            else if(event.getCode() == KeyCode.RIGHT){
                selectedPage.getMembrane().moveTheBlinkerHorizontally(+1);
            }
        }
        else if(event.getCode() == KeyCode.ENTER){
            selectedPage.getMembrane().createNewLine();
        }
        else if(event.getCode() == KeyCode.TAB){
            for(int i = 0 ; i <=  4 ; i++) selectedPage.getMembrane().addCharacter(" ", currFont);
        }
        else if(event.getCode() == KeyCode.SPACE){
            selectedPage.getMembrane().addCharacter(" " , currFont);
        }
        else{
            if(event.getCode() == KeyCode.COMMA){
                keyPressed = event.isShiftDown() ? "<":",";
            }
            else if(event.getCode() == KeyCode.SLASH){
                keyPressed = event.isShiftDown() ? "?":"/";

            }
            else if(event.getCode() == KeyCode.COLON){
                keyPressed = event.isShiftDown() ? ":":";";
            }
            else if(event.getCode() == KeyCode.QUOTE){
                keyPressed = event.isShiftDown() ? "\"":"'";
            }
            else if(event.getCode() == KeyCode.OPEN_BRACKET){
                keyPressed = event.isShiftDown() ? "{":"[";
            }
            else if(event.getCode() == KeyCode.CLOSE_BRACKET){
                keyPressed = event.isShiftDown() ? "}":"]";
            }
            else if(event.getCode() == KeyCode.BACK_SLASH){
                keyPressed = event.isShiftDown() ? "|":"\\";
            }
            else if(event.getCode() == KeyCode.EQUALS){
                keyPressed = event.isShiftDown() ? "+":"=";
            }
            else if(event.getCode() == KeyCode.MINUS){
                keyPressed = event.isShiftDown() ? "_":"-";
            }
//            else if(event.getCode() == KeyCode.)
//            else if(event.getCode() == KeyCode.)
//            System.out.println("this: "+(event.getCode() == KeyCode.));
            selectedPage.getMembrane().addCharacter(keyPressed, currFont);

        }

    }
    public void registerKeyRelease(KeyEvent event) {
        keysPressed = new ArrayList<>();
    }
}
