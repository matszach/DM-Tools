package com.appCore.dmtools.services.lootService.lootGeneator;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;


@Getter
@Component
public class LootBank {

    // fields
    /**
     *  K : String itemName
     *  V : int[]{minValue, maxValue}
     */
    private final HashMap<String, int[]> lootItems = new HashMap<>();
    private final Random random = new Random();
    private final ItemNameParts itemNameParts;

    // constructor
    @Autowired
    public LootBank(ItemNameParts itemNameParts) {
        this.itemNameParts = itemNameParts;
    }


    // init methods
    @PostConstruct
    private void init(){
        addGemsToLootTable();
        addWeaponsToTable();
        addArmorsToTable();
        addArtToTable();
    }




    /**
     * @return - all possible item names in a form of a set
     */
    public Set<String> getAllItems(){
        return lootItems.keySet();
    }

    /**
     * Returns min an amx value of an item as an array.
     * @param itemName - item name - key
     * @return - min and max value of an item
     */
    public int[] getItemValueRange(String itemName){
        return lootItems.get(itemName);
    }



    // private methods
    private void addGemsToLootTable() {
        for (String gemType : itemNameParts.getGemTypes()) {
            lootItems.put("Small "+gemType, new int[]{10,100});
            lootItems.put("Medium "+gemType, new int[]{100,250});
            lootItems.put("Large "+gemType, new int[]{250,800});
        }
    }
    private void addWeaponsToTable(){
        for (String weaponType : itemNameParts.getWeaponTypes()){
            lootItems.put("Masterwork "+weaponType, new int[]{50,200});
            lootItems.put("Decorated "+weaponType, new int[]{50,120});
            lootItems.put("Old "+weaponType, new int[]{10,30});
            lootItems.put("Enchanted "+weaponType, new int[]{400,1200});
        }
    }
    private void addArmorsToTable(){
        for (String armorType : itemNameParts.getArmorTypes()){
            lootItems.put("Masterwork "+armorType, new int[]{100,300});
            lootItems.put("Decorated "+armorType, new int[]{50,200});
            lootItems.put("Old "+armorType, new int[]{10,50});
            lootItems.put("Enchanted "+armorType, new int[]{700,1800});
        }
    }
    private void addArtToTable(){
        for (String artType : itemNameParts.getArtTypes()){
            for (String artSubject : itemNameParts.getArtSubject()){
                lootItems.put(artType+artSubject, new int[]{20,500});
            }
        }
    }











}
