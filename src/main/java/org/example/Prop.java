package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prop extends Targeteable{
    //private int health;
    private boolean destroyed;

    public Prop(int health) {
        this.setHealth(health);
        this.destroyed = false;
        setPosition(new Position(0,0));
    }

    public void checkDestroyed(){
        if (getHealth()<=0)
            setDestroyed(true);
    }
}
