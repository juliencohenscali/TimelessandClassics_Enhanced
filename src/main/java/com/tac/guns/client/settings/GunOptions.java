package com.tac.guns.client.settings;

import com.tac.guns.Config;
import com.tac.guns.client.handler.CrosshairHandler;
import com.tac.guns.client.render.crosshair.Crosshair;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.DecimalFormat;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class GunOptions
{
    private static final DecimalFormat FORMAT = new DecimalFormat("0.0#");

    public static final SliderPercentageOption ADS_SENSITIVITY = new GunSliderPercentageOption("tac.options.adsSensitivity", 0.0, 1.0, 0.01F, gameSettings -> {
        return Config.CLIENT.controls.aimDownSightSensitivity.get();
    }, (gameSettings, value) -> {
        Config.CLIENT.controls.aimDownSightSensitivity.set(MathHelper.clamp(value, 0.0, 2.0));
        Config.saveClientConfig();
    }, (gameSettings, option) -> {
        double adsSensitivity = Config.CLIENT.controls.aimDownSightSensitivity.get();
        return new TranslationTextComponent("tac.options.adsSensitivity.format", FORMAT.format(adsSensitivity));
    });

    public static final BooleanOption TOGGLE_ADS = new BooleanOption("tac.options.toggleAim", (settings) -> {
        return Config.CLIENT.controls.toggleAim.get();
    }, (settings, value) -> {
        Config.CLIENT.controls.toggleAim.set(value);
        Config.saveClientConfig();
    });


    public static final AbstractOption CROSSHAIR = new GunListOption<>("tac.options.crosshair", () -> {
        return CrosshairHandler.get().getRegisteredCrosshairs();
    }, () -> {
        return ResourceLocation.tryCreate(Config.CLIENT.display.crosshair.get());
    }, (value) -> {
        Config.CLIENT.display.crosshair.set(value.toString());
        Config.saveClientConfig();
        CrosshairHandler.get().setCrosshair(value);
    }, (value) -> {
        ResourceLocation id = value.getLocation();
        return new TranslationTextComponent(id.getNamespace() + ".crosshair." + id.getPath());
    }).setRenderer((button, matrixStack, partialTicks) -> {
        matrixStack.push();
        matrixStack.translate(button.x, button.y, 0);
        matrixStack.translate(button.getWidth() + 2, 2, 0);
        Crosshair crosshair = CrosshairHandler.get().getCurrentCrosshair();
        if(crosshair != null)
        {
            if(crosshair.isDefault())
            {
                Minecraft mc = Minecraft.getInstance();
                mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
                AbstractGui.blit(matrixStack, (16 - 15) / 2, (16 - 15) / 2, 0, 0, 0, 15, 15, 256, 256);
            }
            else
            {
                crosshair.render(Minecraft.getInstance(), matrixStack, 16, 16, partialTicks);
            }
        }
        matrixStack.pop();
    });

    //Firemode positioning

    public static final SliderPercentageOption X_FIREMODE_POS = new GunSliderPercentageOption("tac.options.xFiremodePos", -1000, 1000, 0.01F,
    gameSettings ->
    {
        return Config.CLIENT.weaponGUI.weaponFireMode.x.get();
        //return Config.CLIENT.controls.aimDownSightSensitivity.get();
    },
    (gameSettings, value) ->
    {
        Config.CLIENT.weaponGUI.weaponFireMode.x.set(MathHelper.clamp(value, -1000, 1000));
        Config.saveClientConfig();
    },
    (gameSettings, option) -> {
        double adsSensitivity = Config.CLIENT.weaponGUI.weaponFireMode.x.get();
        return new TranslationTextComponent("tac.options.xFiremodePos.format", FORMAT.format(adsSensitivity));
    });
    public static final SliderPercentageOption Y_FIREMODE_POS = new GunSliderPercentageOption("tac.options.yFiremodePos", -1000, 1000, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponFireMode.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponFireMode.y.set(MathHelper.clamp(value, -1000, 1000));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponFireMode.y.get();
                return new TranslationTextComponent("tac.options.yFiremodePos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_FIREMODE_POS = new GunSliderPercentageOption("tac.options.sizeFiremodePos", 0.1, 4, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponFireMode.weaponFireModeSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponFireMode.weaponFireModeSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponFireMode.weaponFireModeSize.get();
                return new TranslationTextComponent("tac.options.sizeFiremodePos.format", FORMAT.format(adsSensitivity));
            });

    //AmmoCounter positioning

    public static final SliderPercentageOption X_AMMOCOUNTER_POS = new GunSliderPercentageOption("tac.options.xAmmoCounterPos", -1000, 1000, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponAmmoCounter.x.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponAmmoCounter.x.set(MathHelper.clamp(value, -1000, 1000));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.x.get();
                return new TranslationTextComponent("tac.options.xAmmoCounterPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption Y_AMMOCOUNTER_POS = new GunSliderPercentageOption("tac.options.yAmmoCounterPos", -1000, 1000, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponAmmoCounter.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponAmmoCounter.y.set(MathHelper.clamp(value, -1000, 1000));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.y.get();
                return new TranslationTextComponent("tac.options.yAmmoCounterPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_AMMOCOUNTER_POS = new GunSliderPercentageOption("tac.options.sizeAmmoCounterPos", 0.1, 4, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.get();
                return new TranslationTextComponent("tac.options.sizeAmmoCounterPos.format", FORMAT.format(adsSensitivity));
            });

    //WeaponIcon positioning

    public static final SliderPercentageOption X_Icon_POS = new GunSliderPercentageOption("tac.options.xIconPos", -1000, 1000, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponTypeIcon.x.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponTypeIcon.x.set(MathHelper.clamp(value, -1000, 1000));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponTypeIcon.x.get();
                return new TranslationTextComponent("tac.options.xIconPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption Y_Icon_POS = new GunSliderPercentageOption("tac.options.yIconPos", -1000, 1000, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponTypeIcon.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponTypeIcon.y.set(MathHelper.clamp(value, -1000, 1000));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.y.get();
                return new TranslationTextComponent("tac.options.yIconPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_Icon_POS = new GunSliderPercentageOption("tac.options.sizeIconPos", 0.1, 4, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponTypeIcon.weaponIconSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponTypeIcon.weaponIconSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponTypeIcon.weaponIconSize.get();
                return new TranslationTextComponent("tac.options.sizeIconPos.format", FORMAT.format(adsSensitivity));
            });
}