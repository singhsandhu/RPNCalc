package com.rpn.input;

public class UserInput {

    private String value;
    private int position;

    public UserInput(String value, int position) {
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

}
