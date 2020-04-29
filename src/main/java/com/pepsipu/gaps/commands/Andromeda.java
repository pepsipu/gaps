package com.pepsipu.gaps.commands;

import com.pepsipu.gaps.NewMovement;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.Vec3;

import java.awt.*;

public class Andromeda extends CommandBase {
    private final SafeWalk safeWalk;
    private Minecraft mc;
    private NewMovement newMovement;
    private boolean isActive;

    public Andromeda(NewMovement newMovement, SafeWalk safeWalk) {
        this.mc = Minecraft.getMinecraft();
        this.newMovement = newMovement;
        this.safeWalk = safeWalk;
    }

    @Override
    public String getCommandName() {
        return "ab";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "./ab <length>";
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) throws CommandException {
        this.mc = Minecraft.getMinecraft();
        this.mc.thePlayer.addChatMessage(new ChatComponentText(isActive ? "deactivating andromeda bridging": "activating andromeda bridging"));
        this.isActive = !this.isActive;
        safeWalk.processCommand(iCommandSender, null);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public void scaffold() {
        if (this.isActive) {
            Vec3 lookVec = this.mc.thePlayer.getLookVec();
            BlockPos blockPos = new BlockPos(this.mc.thePlayer.posX + lookVec.xCoord, this.mc.thePlayer.posY - 1, this.mc.thePlayer.posZ + lookVec.zCoord);
            System.out.println(blockPos);
            this.mc.thePlayer.rotationPitch = 90;
        }
    }
}
