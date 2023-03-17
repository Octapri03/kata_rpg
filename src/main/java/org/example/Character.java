package org.example;

import java.awt.geom.Point2D;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character {
    private int health;
    private int level;
    private boolean isAlive;
    private int rango;

    private Position position;

    public Character() {
        this.health = 1000;
        this.level = 1;
        this.isAlive = true;
        this.setMeleeFighter();
        this.position = new Position(0,0);
    }

    public void attack(int damage, Character enemy){
        if (enemy.equals(this))
            return;

        if (!isInRange(enemy))
            return;

        damage = damageBasedOnLevelModifier(damage, enemy);

        if (damageGreaterThanHealth(damage, enemy)){
            enemy.setHealth(enemy.getHealth()-damage);
        }
        else
            enemy.die();
    }


    private boolean damageGreaterThanHealth(int damage, Character enemy){
        return enemy.getHealth() > damage;
    }

    private int damageBasedOnLevelModifier(int damage, Character enemy){
        if (this.getLevel() >= enemy.getLevel()+5)
            return (int)Math.round(damage * 1.5);

        if (this.getLevel() <= enemy.getLevel()-5)
            return (int)Math.round(damage * 0.5);

        return damage;
    }
    public void heal(int quantity, Character objective){
        if (!objective.equals(this))
            return;

        if (!objective.isAlive())
            return;

        if (objective.getHealth()+quantity > 1000)
            objective.setHealth(1000);

        else objective.setHealth(objective.getHealth()+quantity);
    }

    public void die() {
        isAlive = false;
        health = 0;
    }

    public void setMeleeFighter(){
        this.setRango(2);
    }

    public void setRangedFighter(){
        this.setRango(20);
    }

    public boolean isInRange(Character enemy){
        double distanceBetweenCharacters = Point2D.distance(getPosition().getPosX(), getPosition().getPosY(), enemy.getPosition().getPosX(), enemy.getPosition().getPosY());

        return this.getRango() >= distanceBetweenCharacters;
    }

}
