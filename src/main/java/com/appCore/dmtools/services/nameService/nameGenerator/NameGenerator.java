package com.appCore.dmtools.services.nameService.nameGenerator;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * This class generates random names from NameParts' string arrays
 */
@Component
public class NameGenerator {

    // constants
    private final double NO_END_PART_CHANCE = 0.1;

    // fields
    private final Random random = new Random();

    // public methods
    public String[] generateNames(int number){
        String[] names = new String[number];
        for(int i = 0; i < number; i++){
            names[i] = generateName();
        }
        return names;
    }


    /**
     * Generates a single name from NameParts' String arrays.
     * @return - generated name constructed from random parts
     */
    // private methods
    private String generateName(){
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(getRandomNamePart(NameParts.BEGINNING_PARTS));
        nameBuilder.append(getRandomNamePart(NameParts.MIDDLE_PARTS));
        nameBuilder.append(getRandomNamePart(NameParts.END_PARTS, NO_END_PART_CHANCE));
        return nameBuilder.toString();
    }

    /**
     * Returns random name part form passed name part array.
     * @param nameParts - one of the arrays from NameParts class
     * @return - the chosen name part
     */
    private String getRandomNamePart(String[] nameParts){
        return nameParts[random.nextInt(nameParts.length)];
    }

    /**
     * Returns random name part form passed name part array, but allows
     * a chance for an empty string to be passed, this is used with NameParts.END_PARTS.
     * @param nameParts - one of the arrays from NameParts class
     * @param noneChance - defines a chance from 0.0 - impossible, to 1.0 - certain
     *                   that the method will return an empty name part
     * @return - the chosen name part OR and empty string
     */
    private String getRandomNamePart(String[] nameParts, double noneChance){
        if(Math.random()>noneChance){
            return getRandomNamePart(nameParts);
        } else {
            return "";
        }
    }
}
