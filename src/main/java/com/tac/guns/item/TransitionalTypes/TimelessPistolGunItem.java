package com.tac.guns.item.TransitionalTypes;


import com.tac.guns.GunMod;
import com.tac.guns.util.Process;


public class TimelessPistolGunItem extends TimelessGunItem {
    public TimelessPistolGunItem(Process<Properties> properties)
    {
        super(properties1 -> properties.process(new Properties().maxStackSize(1).group(GunMod.GROUP)));
    }

    public TimelessPistolGunItem() {
        this(properties -> properties);
    }
}