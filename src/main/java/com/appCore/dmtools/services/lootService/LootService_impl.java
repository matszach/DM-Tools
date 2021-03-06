package com.appCore.dmtools.services.lootService;

import com.appCore.dmtools.services.lootService.lootGeneator.LootGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class passes loot generated by LootGenerator to
 * the AppController upon it's request
 */
@Service
public class LootService_impl implements LootService {

    // constants
    private static final String DEFAULT_MESSAGE = "Please generate the loot first.";

    // fields
    private final LootGenerator generator;

    private String generatedLoot;


    // constructor
    @Autowired
    public LootService_impl(LootGenerator generator){
        this.generator = generator;
    }


    // public methods
    /**
     * Uses name generator to create an array of loot items,
     * then converts them to a single string, separated by commas.
     * @param inGold - total value of the loot hoard
     * @param percentInCoin - percent of the hoard that's made up of coin
     */
    @Override
    public void generateLoot(int inGold, int percentInCoin) {
        generatedLoot = lootListToSingleString(generator.generateLoot(inGold,percentInCoin));
    }

    /**
     * Returns generated loot items, or, if no loot was generated yet, returns default message.
     * @return - generated names or the default message
     */
    @Override
    public String getResults() {
        if(generatedLoot!=null && !generatedLoot.isEmpty()){
            return generatedLoot;
        } else {
            return DEFAULT_MESSAGE;
        }
    }

    /**
     * Clears result field
     */
    @Override
    public void reset() {
        generatedLoot = null;
    }

    // private methods
    /**
     * Converts array of loot to a single String,
     * while separating its elements by commas.
     * @param loot - to-be-converted array of loot
     * @return - list of loot separated by commas
     */
    private String lootListToSingleString(List<String> loot) {
        StringBuilder listBuilder = new StringBuilder();
        for (int i = 0; i < loot.size(); i++) {
            listBuilder.append(loot.get(i));
            if (i < loot.size() - 1) {
                listBuilder.append(",<br>");
            }
        }
        return listBuilder.toString();
    }


}
