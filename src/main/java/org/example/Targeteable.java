package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Targeteable {
    private int health;

    private Position position;

    public Targeteable() {
        this.health = 1000;
    }

    public void takeDamage(int damage){
        this.setHealth(getHealth()-damage);
    }

    public boolean isProp(Targeteable enemy){
        if (enemy.getClass().equals(Prop.class))
            return true;
        return false;
    }

    public boolean isCharacter(Targeteable enemy){
        if (enemy.getClass().equals(Character.class))
            return true;
        return false;
    }
}
