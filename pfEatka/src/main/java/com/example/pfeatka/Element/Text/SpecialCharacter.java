package com.example.pfeatka.Element.Text;

public enum SpecialCharacter {

    lineStart("&lineStart"),
    imageBlock("&imageBlock"),
    table("&table") ,
    tableRow("&tableRow"),
    tableColumn("&tableColumn"),
    tableHeadColumn("&tableHeadColumn"),
    tableHeadColumnEnd("&tableHeadColumnEnd"),

    tableEnd ("&tableEnd"),
    tableRowEnd("&tableRowEnd"),
    tableColumnEnd("&tableColumnEnd");

    private final String command ;

    public  String getCommand()
    {
        return this.command;
    }
    SpecialCharacter(String command) {
        this.command = command;
    }
}
