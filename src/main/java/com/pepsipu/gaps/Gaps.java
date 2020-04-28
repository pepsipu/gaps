package com.pepsipu.gaps;

import com.google.common.eventbus.Subscribe;
import com.pepsipu.gaps.commands.SafeWalk;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import java.awt.event.InputMethodEvent;
import java.lang.reflect.Field;
import java.util.Map;

@Mod(modid = Gaps.MODID, version = Gaps.VERSION)
public class Gaps
{
    public static final String MODID = "gaps";
    public static final String VERSION = "1.0";

    private SafeWalk sw;
    private Minecraft mc;
    private int ticksElapsed;
    private MovementInput mi;

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        System.out.println("gaps has initialized");
        MinecraftForge.EVENT_BUS.register(this);
        this.mc = Minecraft.getMinecraft();
        this.sw = new SafeWalk();
        this.sw.mc = this.mc;
        ClientCommandHandler.instance.registerCommand(this.sw);
        this.mi = new MovementInput();
        this.mi.sneak = true;
    }
    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (e.side.isClient() && Minecraft.getMinecraft().thePlayer != null && this.sw.isActive) {
            boolean negativeXOffset = Math.random() > .3;
            boolean negativeZOffset = Math.random() > .2;
            IBlockState block = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer.posX + (negativeXOffset ? -(Math.random() % .07) : (Math.random() % .07)), this.mc.thePlayer.posY - 1, this.mc.thePlayer.posZ + (negativeZOffset ? -(Math.random() % .2) : (Math.random() % .2))));
            boolean atEdge = Math.ceil(this.mc.thePlayer.posY) == this.mc.thePlayer.posY && block.getBlock().getUnlocalizedName().equals("tile.air");
            if (atEdge && !this.sw.atEdge) {
                this.sw.atEdge = true;
                ticksElapsed = 0;
            } else if (!atEdge && this.sw.atEdge) {
                this.mc.thePlayer.movementInput = new MovementInputFromOptions(this.mc.gameSettings);
                this.sw.atEdge = false;
            }
            if (atEdge && ticksElapsed > (int) (Math.random() * 4) + 10) {
                ticksElapsed = -1;
                mi.moveForward = 0;
            } else if (ticksElapsed != -1){
                ticksElapsed++;
            }
            if (atEdge && ticksElapsed > (int) (Math.random() * 5) + 5) {
                mi.moveForward = -1;
                this.mc.thePlayer.movementInput = mi;
            }
        }
    }
}
