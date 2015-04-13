package com.advancedmods.advancedfoods.common.items.seeds;

import com.advancedmods.advancedfoods.AdvancedFoods;
import com.advancedmods.advancedfoods.common.generic.AFItemSeeds;
import com.advancedmods.advancedfoods.core.AFRegistry;
import net.minecraft.init.Blocks;

/**
 * Created by Dennisbonke on 13-2-2015.
 */
public class BananaSeed extends AFItemSeeds {

    public BananaSeed() {
        super(1, 0.3F, AFRegistry.bananaPlant, Blocks.farmland);
        this.setUnlocalizedName("bananaseed");
        this.setCreativeTab(AdvancedFoods.food);
    }

}
