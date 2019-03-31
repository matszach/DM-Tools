package com.appCore.dmtools.services.lootService.lootGeneator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class generates random loot from LootBank's maps
 */
@Component
public class LootGenerator {

    //fields
    private final LootBank bank;
    private final Random random = new Random();

    // constructors
    @Autowired
    public LootGenerator(LootBank bank) {
        this.bank = bank;
    }

    // public methods
    /**
     * Builds the loot list.
     * @param value - total value of the generated hoard
     * @param percentInCoin - percent of the value of the hoard represented by coin
     * @return - array list of loot items and their values in String form
     */
    public ArrayList<String> generateLoot(int value, int percentInCoin){
        int currentValue = 0;
        int maxValueInItems = (int)((double)value*(100-percentInCoin)/100d);
        ArrayList<String> itemsWithValues = new ArrayList<>();
        while (currentValue<maxValueInItems){
            ArrayList<String> itemsList = new ArrayList<>(bank.getAllItems());
            String newItemName = itemsList.get(random.nextInt(itemsList.size()));
            int[] newItemValueRange = bank.getItemValueRange(newItemName);
            int newItemFinalValue = randomValueFrom(newItemValueRange[0], newItemValueRange[1]);
            itemsWithValues.add(newItemName + " (" + newItemFinalValue + ")");
            currentValue += newItemFinalValue;
        }
        int goldLeft = value - currentValue;
        itemsWithValues.add(goldLeft + "g");
        return itemsWithValues;
    }


    // private methods
    /**
     * Given min and max values, generates a random one
     * the result will, by design, always be a multiple of 10.
     * @param min - minimum value of the item
     * @param max - maximum value of the item
     * @return - generated value of the item
     */
    private int randomValueFrom(int min, int max){
        int bound = (max - min)/10;
        return random.nextInt(bound)*10 + min;
    }

}
