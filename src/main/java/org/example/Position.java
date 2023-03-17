package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private int posX;
    private int posY;

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
