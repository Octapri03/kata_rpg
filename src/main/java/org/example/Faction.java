package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Faction {
    String name;
    ArrayList<Character> members;

    public Faction(String name) {
        this.name = name;
    }
}
