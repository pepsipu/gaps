package com.pepsipu.gaps;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

public class NewMovement extends MovementInput {
    private GameSettings gameSettings;
    public float moveStrafe;
    public float moveForward;
    public boolean jump;
    public NewMovement(GameSettings settings) {
        this.gameSettings = settings;
    }

    public void updatePlayerMoveState() {
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
        if (this.sneak) {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3D);
            this.moveForward = (float)((double)this.moveForward * 0.3D);
        }
        System.out.println(this.moveForward);
    }
}
