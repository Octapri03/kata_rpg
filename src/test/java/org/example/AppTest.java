package org.example;


import junit.framework.TestCase;
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

}
