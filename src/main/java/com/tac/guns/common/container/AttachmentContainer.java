package com.tac.guns.common.container;

import com.tac.guns.common.Gun;
import com.tac.guns.common.container.slot.AttachmentSlot;
import com.tac.guns.init.ModContainers;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.ScopeItem;
import com.tac.guns.item.TransitionalTypes.TimelessOldRifleGunItem;
import com.tac.guns.item.TransitionalTypes.TimelessPistolGunItem;
import com.tac.guns.item.attachment.IAttachment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class AttachmentContainer extends Container
{
    private ItemStack weapon;
    private IInventory playerInventory;
    private IInventory weaponInventory = new Inventory(IAttachment.Type.values().length)
    {
        @Override
        public void markDirty()
        {
            super.markDirty();
            AttachmentContainer.this.onCraftMatrixChanged(this);
        }
    };

    private boolean loaded = false;

    public AttachmentContainer(int windowId, PlayerInventory playerInventory, ItemStack stack)
    {
        this(windowId, playerInventory);
        ItemStack[] attachments = new ItemStack[IAttachment.Type.values().length];
        if(stack.getItem() instanceof ScopeItem)
        {
            for (int i = 6; i < attachments.length; i++) {
                attachments[i] = Gun.getAttachment(IAttachment.Type.values()[i], stack);
            }
            for (int i = 6; i < attachments.length; i++) {
                this.weaponInventory.setInventorySlotContents(i, attachments[i]);
            }
        }
        else if(this.weapon.getItem() instanceof TimelessOldRifleGunItem)
        {
            for (int i = 0; i < attachments.length-5; i++)
            {
                if(IAttachment.Type.values()[i] == IAttachment.Type.SCOPE)
                    attachments[i] = Gun.getAttachment(IAttachment.Type.OLD_SCOPE, stack);
                else
                    attachments[i] = Gun.getAttachment(IAttachment.Type.values()[i], stack);
            }
            for (int i = 0; i < attachments.length-5; i++)
            {
                this.weaponInventory.setInventorySlotContents(i, attachments[i]);
            }
        }
        else if(this.weapon.getItem() instanceof TimelessPistolGunItem)
        {
            for (int i = 0; i < attachments.length-5; i++)
            {
                if(IAttachment.Type.values()[i] == IAttachment.Type.SCOPE)
                    attachments[i] = Gun.getAttachment(IAttachment.Type.PISTOL_SCOPE, stack);
                else if(IAttachment.Type.values()[i] == IAttachment.Type.BARREL)
                    attachments[i] = Gun.getAttachment(IAttachment.Type.PISTOL_BARREL, stack);
                else
                    attachments[i] = Gun.getAttachment(IAttachment.Type.values()[i], stack);
            }
            for (int i = 0; i < attachments.length-5; i++)
            {
                this.weaponInventory.setInventorySlotContents(i, attachments[i]);
            }
        }
        else
        {
            for (int i = 0; i < attachments.length-5; i++)
            {
                attachments[i] = Gun.getAttachment(IAttachment.Type.values()[i], stack);
            }
            for (int i = 0; i < attachments.length-5; i++) {
                this.weaponInventory.setInventorySlotContents(i, attachments[i]);
            }
        }
        this.loaded = true;
    }

    public AttachmentContainer(int windowId, PlayerInventory playerInventory)
    {
        super(ModContainers.ATTACHMENTS.get(), windowId);
        this.weapon = playerInventory.getCurrentItem();
        this.playerInventory = playerInventory;

        if(this.weapon.getItem() instanceof ScopeItem)
        {
            for (int i = 5; i < IAttachment.Type.values().length; i++)
            {
                int itorationAdjustment = i;
                if(i==5)
                {
                    itorationAdjustment = i-3;
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.values()[i], playerInventory.player, i, 70, 32 + (itorationAdjustment) * 18));
                }
                if(i==6)
                {
                    itorationAdjustment = i-5;
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.values()[i], playerInventory.player, i, 40, -1 + (itorationAdjustment) * 18));
                }
                if(i==7)
                {
                    itorationAdjustment = i-5;
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.values()[i], playerInventory.player, i, 10, 32 + (itorationAdjustment) * 18));
                }
            }
        }
        else if(this.weapon.getItem() instanceof TimelessOldRifleGunItem)
        {
            for (int i = 0; i < IAttachment.Type.values().length-5; i++)
            {
                if(IAttachment.Type.values()[i] == IAttachment.Type.SCOPE)
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.OLD_SCOPE, playerInventory.player, 0, 5, 17 + 0 * 18));
                else if(!(IAttachment.Type.values()[i] == IAttachment.Type.OLD_SCOPE))
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.values()[i], playerInventory.player, i, 5, 17 + i * 18));
            }
        }
        else if(this.weapon.getItem() instanceof TimelessPistolGunItem)
        {
            for (int i = 0; i < IAttachment.Type.values().length-5; i++)
            {
                if(IAttachment.Type.values()[i] == IAttachment.Type.SCOPE)
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.PISTOL_SCOPE, playerInventory.player, 0, 5, 17 + 0 * 18));
                else if(IAttachment.Type.values()[i] == IAttachment.Type.BARREL)
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.PISTOL_BARREL, playerInventory.player, 1, 5, 17 + 1 * 18));
                else
                    this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.values()[i], playerInventory.player, i, 5, 17 + i * 18));

            }
        }
        else if(this.weapon.getItem() instanceof GunItem)// && !(this.weapon.getItem() instanceof TimelessOldRifleGunItem))
        {
            for (int i = 0; i < IAttachment.Type.values().length-5; i++) {
                this.addSlot(new AttachmentSlot(this, this.weaponInventory, this.weapon, IAttachment.Type.values()[i], playerInventory.player, i, 5, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 102 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            if(i == playerInventory.currentItem)
            {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 160)
                {
                    @Override
                    public boolean canTakeStack(PlayerEntity playerIn)
                    {
                        return false;
                    }
                });
            }
            else
            {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 160));
            }
        }
    }

    public boolean isLoaded()
    {
        return this.loaded;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        CompoundNBT attachments = new CompoundNBT();

        /*if(this.weapon.getItem() instanceof ScopeItem)
        {
            for (int i = 0; i < this.getWeaponInventory().getSizeInventory()-4; i++) {
                ItemStack attachment = this.getSlot(i).getStack();
                if (attachment.getItem() instanceof DyeItem) {
                    attachments.put(currentStuff[i], attachment.write(new CompoundNBT()));
                }
            }

            *//*if (scopeReticleAttachment.getItem() instanceof DyeItem) {
                attachments.put(IAttachment.Type.SCOPE_RETICLE_COLOR.getTagKey(), scopeReticleAttachment.write(new CompoundNBT()));
            }
            if (scopeBodyAttachment.getItem() instanceof DyeItem) {
                attachments.put(IAttachment.Type.SCOPE_BODY_COLOR.getTagKey(), scopeBodyAttachment.write(new CompoundNBT()));
            }
            if (scopeGlassAttachment.getItem() instanceof DyeItem) {
                attachments.put(IAttachment.Type.SCOPE_GLASS_COLOR.getTagKey(), scopeGlassAttachment.write(new CompoundNBT()));
            }*//*
        }
        else*/
            for (int i = 0; i < this.getWeaponInventory().getSizeInventory(); i++) {
                ItemStack attachment = this.getSlot(i).getStack();
                if (attachment.getItem() instanceof IAttachment) {
                    attachments.put(((IAttachment) attachment.getItem()).getType().getTagKey(), attachment.write(new CompoundNBT()));
                }
                else if(attachment.getItem() instanceof DyeItem)
                {
                    if(i == 0)
                        attachments.put(IAttachment.Type.SCOPE_RETICLE_COLOR.getTagKey(), attachment.write(new CompoundNBT()));
                    if(i == 1)
                        attachments.put(IAttachment.Type.SCOPE_BODY_COLOR.getTagKey(), attachment.write(new CompoundNBT()));
                    if(i == 2)
                        attachments.put(IAttachment.Type.SCOPE_GLASS_COLOR.getTagKey(), attachment.write(new CompoundNBT()));
                }
            }

        CompoundNBT tag = this.weapon.getOrCreateTag();
        tag.put("Attachments", attachments);
        super.detectAndSendChanges();
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack copyStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

/*        if (this.weapon.getItem() instanceof ScopeItem)
        {
            if (slot != null && slot.getHasStack()) {
                ItemStack slotStack = slot.getStack();
                copyStack = slotStack.copy();

                if (index == 0) {
                    if (!this.mergeItemStack(slotStack, 0, 36, true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (slotStack.getItem() instanceof DyeItem) {
                        if (!this.mergeItemStack(slotStack, 0, 3, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 28) {
                        if (!this.mergeItemStack(slotStack, 28, 36, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index <= 36 && !this.mergeItemStack(slotStack, 0, 28, false)) {
                        return ItemStack.EMPTY;
                    }
                }

                if (slotStack.isEmpty()) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }

                if (slotStack.getCount() == copyStack.getCount()) {
                    return ItemStack.EMPTY;
                }

                slot.onTake(playerIn, slotStack);
            }
        }
        else {*/
            if (slot != null && slot.getHasStack()) {
                ItemStack slotStack = slot.getStack();
                copyStack = slotStack.copy();
                if (index < this.weaponInventory.getSizeInventory()) {
                    if (!this.mergeItemStack(slotStack, this.weaponInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.mergeItemStack(slotStack, 0, this.weaponInventory.getSizeInventory(), false)) {
                    return ItemStack.EMPTY;
                }
                if (slotStack.isEmpty()) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }
            }//}

        return copyStack;
    }

    public IInventory getPlayerInventory()
    {
        return this.playerInventory;
    }

    public IInventory getWeaponInventory()
    {
        return this.weaponInventory;
    }
}
