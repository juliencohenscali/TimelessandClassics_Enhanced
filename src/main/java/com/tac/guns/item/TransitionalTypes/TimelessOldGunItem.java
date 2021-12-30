package com.tac.guns.item.TransitionalTypes;


import com.tac.guns.Config;
import com.tac.guns.GunMod;
import com.tac.guns.common.Gun;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.IColored;
import com.tac.guns.util.GunEnchantmentHelper;
import com.tac.guns.util.GunModifierHelper;
import com.tac.guns.util.Process;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class TimelessOldGunItem extends TimelessGunItem {
    public TimelessOldGunItem(Process<Properties> properties)
    {
        super(properties1 -> properties.process(new Properties().maxStackSize(1).group(GunMod.GROUP)));
    }

    public TimelessOldGunItem() {
        this(properties -> properties);
    }
}