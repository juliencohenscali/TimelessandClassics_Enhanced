package com.tac.guns.item.TransitionalTypes;


import com.tac.guns.GunMod;
import com.tac.guns.util.Process;


public class TimelessOldRifleGunItem extends TimelessGunItem {
    public TimelessOldRifleGunItem(Process<Properties> properties)
    {
        super(properties1 -> properties.process(new Properties().maxStackSize(1).group(GunMod.GROUP)));
    }

    public TimelessOldRifleGunItem() {
        this(properties -> properties);
    }
}