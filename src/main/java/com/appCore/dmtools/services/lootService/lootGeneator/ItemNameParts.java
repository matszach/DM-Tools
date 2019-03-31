package com.appCore.dmtools.services.lootService.lootGeneator;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@Getter
final public class ItemNameParts {

    // fields
    private List<String> gemTypes = new ArrayList<>();
    private List<String> weaponTypes = new ArrayList<>();
    private List<String> armorTypes = new ArrayList<>();
    private List<String> artSubject = new ArrayList<>();
    private List<String> artTypes = new ArrayList<>();

    // constructor
    public ItemNameParts(){

    }

    // init methods
    @PostConstruct
    private void initArrays(){
        initGemTypes();
        initWeaponTypes();
        initArmorTypes();
        initArtSubjects();
        initArtTypes();
    }

    // private methods
    private void initGemTypes(){
        gemTypes.add("amethyst");
        gemTypes.add("emerald");
        gemTypes.add("pearl");
        gemTypes.add("ruby");
        gemTypes.add("sapphire");
        gemTypes.add("diamond");
    }
    private void initWeaponTypes(){
        weaponTypes.add("longsword");
        weaponTypes.add("dagger");
        weaponTypes.add("rapier");
        weaponTypes.add("scimitar");
        weaponTypes.add("mace");
        weaponTypes.add("war hammer");
        weaponTypes.add("war pick");
        weaponTypes.add("axe");
        weaponTypes.add("crossbow");
        weaponTypes.add("bow");
    }
    private void initArmorTypes(){
        armorTypes.add("chain mail");
        armorTypes.add("breastplate");
        armorTypes.add("helmet");
        armorTypes.add("gauntlets");
    }
    private void initArtSubjects(){
        artSubject.add("a hero");
        artSubject.add("a raven");
        artSubject.add("a deity");
    }
    private void initArtTypes(){
        artTypes.add("Sculpture of ");
        artTypes.add("Signet with a symbol of ");
        artTypes.add("Painting of ");
    }

}
