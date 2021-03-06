package com.advancedmods.advancedfoods.common.generic;

import com.advancedmods.advancedfoods.core.AFProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Dennisbonke on 11-2-2015.
 */
public class AFItemSeeds extends ItemFood implements IPlantable {

	private final Block theBlockPlant;
	private final Block soilId;

	public AFItemSeeds(int parHealAmount, float parSaturationModifier,
	                   Block parBlockPlant, Block parSoilBlock) {
		super(parHealAmount, parSaturationModifier, false);
		theBlockPlant = parBlockPlant;
		soilId = parSoilBlock;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", AFProps.modid.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", AFProps.modid.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	@Override
	public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer,
	                         World parWorld, int parX, int parY, int parZ, int par7, float par8,
	                         float par9, float par10) {
		// not sure what this parameter does, copied it from potato
		if (par7 != 1) {
			return false;
		}
		// check if player has capability to edit
		else if (parPlayer.canPlayerEdit(parX, parY + 1, parZ, par7, parItemStack)) {
			// check that the soil block can sustain the plant
			// and that block above is air so there is room for plant to grow
			if (parWorld.getBlock(parX, parY, parZ).canSustainPlant(parWorld,
					parX, parY, parZ, ForgeDirection.UP, this) && parWorld
					.isAirBlock(parX, parY + 1, parZ)) {
				// place the plant block
				parWorld.setBlock(parX, parY + 1, parZ, theBlockPlant);
				// decrement the stack of seed items
				--parItemStack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return theBlockPlant;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}

	public Block getSoilId() {
		return soilId;
	}
}
