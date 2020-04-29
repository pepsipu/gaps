package com.pepsipu.gaps;

import com.pepsipu.gaps.commands.Andromeda;
import com.pepsipu.gaps.commands.SafeWalk;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

@Mod(modid = Gaps.MODID, version = Gaps.VERSION)
public class Gaps
{
    public static final String MODID = "gaps";
    public static final String VERSION = "1.0";

    private NewMovement newMovement = new NewMovement(Minecraft.getMinecraft().gameSettings);
    private SafeWalk safeWalk = new SafeWalk(this.newMovement);
    //private Andromeda andromeda = new Andromeda(this.newMovement, this.safeWalk);


    public Gaps() throws AWTException {
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        System.out.println("gaps has initialized");
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(this.safeWalk);
        //ClientCommandHandler.instance.registerCommand(this.andromeda);
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.START || !e.side.isClient()) {
            return;
        }
        boolean onEdge = this.safeWalk.autoCrouch();
        if (onEdge) {
            //this.andromeda.scaffold();
        }
    }
}
