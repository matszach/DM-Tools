package com.appCore.dmtools.services.diceService;

import com.appCore.dmtools.services.diceService.diceRoller.DiceRoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class passes dice rolls generated by DiceRoller to
 * the AppController upon it's request
 */
@Service
public class DiceService_impl implements DiceService {

    // constants
    private static final String DEFAULT_MESSAGE = "Please roll some dice first.";

    // fields
    private final DiceRoller diceRoller;

    private String generatedRolls;


    // constructor
    @Autowired
    public DiceService_impl(DiceRoller diceRoller){
        this.diceRoller = diceRoller;
    }


    // public methods
    /**
     * Uses dice roller to create an array of dice roll results,
     * then converts them to a single string, separated by a comma,
     * and embeds any value equal or greater than targetValue
     * in html <strong></strong> tags, that are then passed through
     * Thymeleaf's th:utext parameter
     * @param maxDieResult - base results is generated from values from 0 to maxDieValue
     * @param number - expected number of rolls
     * @param bonus - number added to base result before it is added to result array
     * @param targetValue - minimum value for which the roll result will be embedded in <strong></strong> tags
     */
    @Override
    public void generateRolls(int maxDieResult, int number, int bonus, int targetValue) {
        generatedRolls = intArrayToSingleString(diceRoller.generateRolls(maxDieResult,number,bonus), targetValue);
    }

    /**
     * Returns generated rolls, or, if no rolls were generated yet, returns default message.
     * @return - generated results or the default message
     */
    @Override
    public String getResults() {
        if(generatedRolls!=null && !generatedRolls.isEmpty()){
            return generatedRolls;
        } else {
            return DEFAULT_MESSAGE;
        }
    }

    /**
     * Clears result field
     */
    @Override
    public void reset() {
        generatedRolls = null;
    }


    // private methods
    /**
     * Converts array of names to a single String,
     * while separating them by commas and applying <strong></strong> tags
     * results considered successful (equal or greater than targetValue).
     * @param results - to-be-converted array of die roll results
     * @param targetValue - minimum desired roll outcome
     * @return - list of results separated by commas, and equipped with
     *          html tags to make the successful rolls stand out
     */
    private String intArrayToSingleString(int[] results, int targetValue) {
        StringBuilder listBuilder = new StringBuilder();
        for (int i = 0; i < results.length; i++) {
            if (results[i]>=targetValue){
                listBuilder.append("<strong>");
            }
            listBuilder.append(results[i]);
            if (results[i]>=targetValue){
                listBuilder.append("</strong>");
            }
            if (i < results.length - 1) {
                listBuilder.append(", ");
            }
        }
        return listBuilder.toString();
    }










}