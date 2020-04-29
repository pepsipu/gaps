package com.pepsipu.gaps.commands;

import com.pepsipu.gaps.NewMovement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovementInputFromOptions;

public class SafeWalk extends CommandBase {
    public boolean isActive = false;
    public Minecraft mc;
    private NewMovement newMovement;

    public SafeWalk(NewMovement newMovement) {
        this.newMovement = newMovement;
    }

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
        this.mc = Minecraft.getMinecraft();
        this.mc.thePlayer.addChatMessage(new ChatComponentText(isActive ? "deactivating safewalk": "activating safewalk"));
        this.isActive = !this.isActive;
        if (this.isActive) {
            this.mc.thePlayer.movementInput = this.newMovement;
        } else {
            this.mc.thePlayer.movementInput = new MovementInputFromOptions(this.mc.gameSettings);
        }
    }

    public boolean autoCrouch() {
        if (!this.isActive) return false;
        Minecraft.getMinecraft().thePlayer.movementInput = this.newMovement;
        if (this.isActive) return false;
        IBlockState block = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer.posX + ((Math.random() * 2) - 1) * .07, this.mc.thePlayer.posY - 1, this.mc.thePlayer.posZ + ((Math.random() * 2) - 1) * .07));
        boolean onEdge = this.mc.thePlayer.posY % 1 == 0 && block.getBlock().getUnlocalizedName().equals("tile.air");
        this.newMovement.sneak = onEdge;
        System.out.println("bruh");
        return onEdge;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
