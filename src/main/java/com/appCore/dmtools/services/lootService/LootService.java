package com.appCore.dmtools.services.lootService;

public interface LootService {
    void generateLoot(int inGold, int percentInCoin);
    String getResults();
    void reset();
}
