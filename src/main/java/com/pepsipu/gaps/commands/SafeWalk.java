package com.pepsipu.gaps.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovementInputFromOptions;

public class SafeWalk extends CommandBase {
    public boolean isActive = false;
    public boolean atEdge = false;
    public Minecraft mc;

    @Override
    public String getCommandName() {
        return "sw";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "enable safe walk";
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        this.mc.thePlayer.addChatMessage(new ChatComponentText(isActive ? "deactivating safewalk": "activating safewalk"));
        this.isActive = !this.isActive;
        if (!this.isActive) {
            this.mc.thePlayer.movementInput = new MovementInputFromOptions(this.mc.gameSettings);
        }
    }
}
