package com.example.pfeatka.Utils;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class TextUtil {
    public static double getWidth(String data, SerializableFont newFont) {
        Text tmp = new Text(data);
        tmp.setFont(newFont.toFont());
        return tmp.getLayoutBounds().getWidth();
    }
    public static double getHeight(String data, SerializableFont newFont) {
        Text tmp = new Text(data);
        tmp.setFont(newFont.toFont());
        return tmp.getLayoutBounds().getHeight();
    }
    public static int lower_bound(Object[] array, int selectedCharacterIndex) {
        int i= Arrays.binarySearch(array  , selectedCharacterIndex);
        if(i> 0) return  i;
        else if(i == 0) return  1;
        else {
//            System.out.println("this had to be called " + i);
            return -i -1  ;
        }
    }
}
