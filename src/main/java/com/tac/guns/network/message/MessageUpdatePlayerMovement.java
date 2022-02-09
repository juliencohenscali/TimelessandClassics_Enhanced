package com.tac.guns.network.message;


import com.tac.guns.client.network.ClientPlayHandler;
import com.tac.guns.common.network.ServerPlayHandler;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */


public class MessageUpdatePlayerMovement implements IMessage
{
    @Override
    public void encode(PacketBuffer buffer) {}
    @Override
    public void decode(PacketBuffer buffer) {}

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() -> {ClientPlayHandler.handleMovementUpdate(supplier.get().getSender());});
        supplier.get().setPacketHandled(true);
    }
}


