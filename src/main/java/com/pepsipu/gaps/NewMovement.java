package com.pepsipu.gaps;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;

public class NewMovement extends MovementInputFromOptions {
    private GameSettings gameSettings;
    public boolean autoMove;
    public float moveStrafe;
    public float moveForward;
    public boolean jump;
    public NewMovement(GameSettings settings) {
        super(settings);
        this.gameSettings = settings;
    }

    public void updatePlayerMoveState() {
        if (autoMove) {
            return;
        }
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;
        if (this.gameSettings.keyBindForward.isKeyDown()) {
            ++this.moveForward;
        }

        if (this.gameSettings.keyBindBack.isKeyDown()) {
            --this.moveForward;
        }

        if (this.gameSettings.keyBindLeft.isKeyDown()) {
            ++this.moveStrafe;
        }

        if (this.gameSettings.keyBindRight.isKeyDown()) {
            --this.moveStrafe;
        }

        this.jump = this.gameSettings.keyBindJump.isKeyDown();
        this.sneak |= this.gameSettings.keyBindSneak.isKeyDown();
        if (this.sneak) {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3D);
            this.moveForward = (float)((double)this.moveForward * 0.3D);
        }
    }
}
