package com.advancedmods.advancedfoods.common.blocks.crops;

import com.advancedmods.advancedfoods.AdvancedFoods;
import com.advancedmods.advancedfoods.common.generic.AFBlockCrop;
import com.advancedmods.advancedfoods.core.AFProps;
import com.advancedmods.advancedfoods.core.AFRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.util.Random;

/**
 * Created by Dennisbonke on 12-2-2015.
 */
public class LemonPlant extends AFBlockCrop {

	public LemonPlant() {
		// Basic block setup
		this.setBlockName("lemonplant");
		this.setBlockTextureName(AFProps.modid.toLowerCase() + ":lemon_0");
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(int parMetadata, int parFortune, Random parRand) {
		return (parMetadata / 2);
	}

	@Override
	public Item getItemDropped(int parMetadata, Random parRand, int parFortune) {
		return (AFRegistry.lemon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister parIIconRegister) {
		iIcon = new IIcon[maxGrowthStage + 1];
		iIcon[0] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_0");
		iIcon[1] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_0");
		iIcon[2] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_1");
		iIcon[3] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_1");
		iIcon[4] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_2");
		iIcon[5] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_2");
		iIcon[6] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_3");
		iIcon[7] = parIIconRegister.registerIcon(AFProps.modid.toLowerCase() + ":lemon_3");
	}

}
