package com.appCore.dmtools.controller;

import com.appCore.dmtools.services.diceService.DiceService;
import com.appCore.dmtools.services.lootService.LootService;
import com.appCore.dmtools.services.nameService.NameService;
import com.appCore.dmtools.util.AttributeNames;
import com.appCore.dmtools.util.Mappings;
import com.appCore.dmtools.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Manages requests (Mappings constants)
 * and returns views (ViewNames constants -> thymeleaf templates)
 */
@Controller
public class AppController {

    // services
    private final NameService nameService;
    private final DiceService diceService;
    private final LootService lootService;

    // constructor
    @Autowired
    public AppController(NameService nameService, DiceService diceService, LootService lootService){
        this.nameService = nameService;
        this.diceService = diceService;
        this.lootService = lootService;
    }


    // Side panel request methods
    @GetMapping(Mappings.HOME)
    public String home(Model model) {
        return ViewNames.HOME;
    }

    @GetMapping(Mappings.LOOT)
    public String loot(Model model) {
        model.addAttribute(AttributeNames.MESSAGE, lootService.getResults());
        lootService.reset();
        return ViewNames.LOOT;
    }

    @GetMapping(Mappings.NAMES)
    public String names(Model model) {
        model.addAttribute(AttributeNames.MESSAGE, nameService.getResults());
        nameService.reset(); // the app, after passing the generated names further, no longer stores them
        return ViewNames.NAMES;
    }

    @GetMapping(Mappings.DICE)
    public String dice(Model model) {
        model.addAttribute(AttributeNames.MESSAGE, diceService.getResults());
        diceService.reset(); // the app, after passing the generated roll results further, no longer stores them
        return ViewNames.DICE;
    }



    // Redirects on form-input request
    @PostMapping(Mappings.LOOT)
    public String lootGenerateCalled(@RequestParam int value, @RequestParam int percentInCoin){
        lootService.generateLoot(value, percentInCoin);
        return Mappings.REDIR_LOOT;
    }

    @PostMapping(Mappings.NAMES)
    public String namesGenerateCalled(@RequestParam int number){
        nameService.generateNames(number);
        return Mappings.REDIR_NAMES;
    }

    @PostMapping(Mappings.DICE)
    public String diceRollCalled(@RequestParam int maxDieResult, @RequestParam int number,
                                 @RequestParam int bonus, @RequestParam int targetValue){
        diceService.generateRolls(maxDieResult,number,bonus,targetValue);
        return Mappings.REDIR_DICE;
    }



}
