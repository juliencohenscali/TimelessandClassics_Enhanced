package com.tac.guns.util;

public class UTR
{
    public int getWeaponLevel() {return this.weaponLevel;}

    public double getWeaponCraftPercentage() {return this.weaponCraftPercentage;}
    private int weaponLevel;
    private double weaponCraftPercentage;
    /**
     * Creates an upgradeTableRequirement entry
     *
     * @param  wL  the required weapon level to apply
     * @param  wCP the "weapon cost", provide a percentage value (1d = 100%) towards how much of a weapons craft will be required to apply
     * @return     Self
     */
    public UTR(int wL, double wCP)
    {
        this.weaponCraftPercentage = wCP;
        this.weaponLevel = wL;
    }
}
