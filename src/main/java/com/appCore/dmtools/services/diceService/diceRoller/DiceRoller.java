package com.appCore.dmtools.services.diceService.diceRoller;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * This class generates random dice rolls
 */
@Component
public class DiceRoller {

    // fields
    private final Random random = new Random();

    // public methods
    public int[] generateRolls(int maxDieResult, int number, int bonus){
        int[] rolls = new int[number];
        for(int i = 0; i < number; i++){
            rolls[i] = generateRoll(maxDieResult, bonus);
        }
        return rolls;
    }

    // private methods
    private int generateRoll(int maxDieValue, int bonus){
        return random.nextInt(maxDieValue) + 1 + bonus;
    }

}
