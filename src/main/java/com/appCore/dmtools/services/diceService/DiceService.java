package com.appCore.dmtools.services.diceService;

/**
 * Defines responsibilities of DiceRoller_impl class
 */
public interface DiceService {
    void generateRolls(int maxDieResult, int number, int bonus, int targetValue);
    String getResults();
    void reset();
}
