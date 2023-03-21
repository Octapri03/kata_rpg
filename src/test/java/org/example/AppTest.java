package org.example;


import junit.framework.TestCase;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @Test
    public void test_attackEnemyButDontKill()
    {
        Character mainCharacter = new Character();
        Character enemy = new Character();

        mainCharacter.attack(100, enemy);

        assertEquals(900, enemy.getHealth());
    }


    @Test
    public void test_attackEnemyAndKill()
    {
        Character mainCharacter = new Character();
        Character enemy = new Character();

        mainCharacter.attack(1100, enemy);

        assertEquals(0, enemy.getHealth());
    }

    @Test
    public void test_healingCharacterOtherThanPlayer()
    {
        Character mainCharacter = new Character();
        Character reciever = new Character();

        reciever.setHealth(500);
        mainCharacter.heal(100, reciever);

        assertEquals(500, reciever.getHealth());
    }

    @Test
    public void test_healingOver1000()
    {
        Character mainCharacter = new Character();

        mainCharacter.heal(100, mainCharacter);

        assertEquals(1000, mainCharacter.getHealth());
    }

    @Test
    public void test_healingHurtCharacter()
    {
        Character mainCharacter = new Character();

        mainCharacter.setHealth(500);
        mainCharacter.heal(100, mainCharacter);

        assertEquals(600, mainCharacter.getHealth());
    }

    @Test
    public void test_playerCantHurtItself()
    {
        Character mainCharacter = new Character();

        mainCharacter.attack(100, mainCharacter);

        assertEquals(1000, mainCharacter.getHealth());
    }

    @Test
    public void test_playerFiveLevelsAboveEnemy()
    {
        Character mainCharacter = new Character();
        mainCharacter.setLevel(6);

        Character enemy = new Character();

        mainCharacter.attack(100, enemy);

        assertEquals(850, enemy.getHealth());
    }

    @Test
    public void test_playerFiveLevelsBelowEnemy()
    {
        Character mainCharacter = new Character();

        Character enemy = new Character();
        enemy.setLevel(6);

        mainCharacter.attack(100, enemy);

        assertEquals(950, enemy.getHealth());
    }

    @Test
    public void test_playerMeleeHitsOutRange()
    {
        Character mainCharacter = new Character();

        Character enemy = new Character();
        enemy.setPosition(new Position(7, 5));

        mainCharacter.attack(100, enemy);

        assertEquals(1000, enemy.getHealth());
    }

    @Test
    public void test_playerRangedHitsOutRange()
    {
        Character mainCharacter = new Character();
        mainCharacter.setRangedFighter();

        Character enemy = new Character();
        enemy.setPosition(new Position(20, 5));

        mainCharacter.attack(100, enemy);

        assertEquals(1000, enemy.getHealth());
    }

    @Test
    public void test_healPlayerFromTheSameFaction(){
        Faction factionOne = new Faction("One");

        Character mainCharacter = new Character();
        mainCharacter.getFactions().add(factionOne);

        Character allie = new Character();
        allie.setHealth(500);
        allie.getFactions().add(factionOne);

        mainCharacter.heal(200, allie);


        assertEquals(700, allie.getHealth());
    }

    @Test
    public void test_player_can_hurt_props(){

        Character mainCharacter = new Character();

        Prop tree = new Prop(2000);

        mainCharacter.attack(200, tree);


        assertEquals(1800, tree.getHealth());
    }

    @Test
    public void test_player_can_destroy_props(){

        Character mainCharacter = new Character();

        Prop tree = new Prop(2000);

        mainCharacter.attack(2000, tree);

        assertEquals(true, tree.isDestroyed());
    }

}
