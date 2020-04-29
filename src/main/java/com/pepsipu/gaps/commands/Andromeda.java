package com.pepsipu.gaps.commands;

import com.pepsipu.gaps.NewMovement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MouseHelper;

import java.awt.*;

public class Andromeda extends CommandBase {
    private Minecraft mc;
    private NewMovement newMovement;

    public Andromeda(NewMovement newMovement) {
        this.mc = Minecraft.getMinecraft();
        this.newMovement = newMovement;
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
        this.newMovement.autoMove = true;
        mc.thePlayer.setRotationYawHead(-1);
        BlockPos playerPos = iCommandSender.getPosition();
        System.out.println(mc.thePlayer.getCurrentEquippedItem().getDisplayName());
//        for (int i = 0; i < parseInt(strings[0]); ++i) {
//        }
//        KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
