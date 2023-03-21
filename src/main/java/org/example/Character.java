package org.example;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character extends Targeteable{
    //private int health;
    private int level;
    private boolean isAlive;
    private int rango;

    private Set<Faction> factions;

    public Character() {
        super();
        this.level = 1;
        this.isAlive = true;
        this.setMeleeFighter();
        this.setPosition(new Position(0,0));
        factions = new HashSet<>();
    }

    public void attack(int damage, Targeteable enemy){
        if (isProp(enemy))
            attackProp(damage, enemy);

        if (isCharacter(enemy))
            attackCharacter(damage, enemy);
    }

    private int damageBasedOnLevelModifier(int damage, Character enemy){
        if (this.getLevel() >= enemy.getLevel()+5)
            return (int)Math.round(damage * 1.5);

        if (this.getLevel() <= enemy.getLevel()-5)
            return (int)Math.round(damage * 0.5);

        return damage;
    }
    public void heal(int quantity, Character objective){
        if (isValidTargetToHeal(objective))
            commitHealing(quantity, objective);
    }

    private void checkDead() {
        if (getHealth() <= 0){
            isAlive = false;
            setHealth(0);
        }
    }

    public void attackProp(int damage, Targeteable enemy){
        if (isInRange(enemy)){
            enemy.takeDamage(damage);
            ((Prop) enemy).checkDestroyed();
        }
    }

    public void attackCharacter(int damage, Targeteable enemy){
        if (!isValidTargetToAttack(enemy))
            return;

        damage = damageBasedOnLevelModifier(damage, (Character) enemy);

        enemy.takeDamage(damage);

        ((Character) enemy).checkDead();
    }

    public void setMeleeFighter(){
        this.setRango(2);
    }

    public void setRangedFighter(){
        this.setRango(20);
    }

    public void commitHealing(int quantity, Character objective){
        if (objective.getHealth()+quantity > 1000)
            objective.setHealth(1000);

        else objective.setHealth(objective.getHealth()+quantity);
    }

    public boolean isValidTargetToAttack(Targeteable target){
        if (target.equals(this))
            return false;

        if (bothAreFromTheSameFaction((Character) target))
            return false;

        if (!isInRange(target))
            return false;

        return true;
    }

    public boolean isValidTargetToHeal(Targeteable target){
        if (target.equals(this))
            return true;

        if (!bothAreFromTheSameFaction((Character) target))
            return false;

        if (!((Character)target).isAlive())
            return false;

        return true;
    }

    private boolean isInRange(Targeteable enemy){
        double distanceBetweenCharacters = Point2D.distance(getPosition().getPosX(), getPosition().getPosY(), enemy.getPosition().getPosX(), enemy.getPosition().getPosY());

        return this.getRango() >= distanceBetweenCharacters;
    }

    private boolean bothAreFromTheSameFaction(Character rival){
        for (Faction faction: factions) {
            if (rival.getFactions().contains(faction)){
                return true;
            }
        }
        return false;
    }

}
