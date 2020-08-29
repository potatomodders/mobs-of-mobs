package net.potatocult.mobsofmobs.entities.ai.goal;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.potatocult.mobsofmobs.entities.monster.WightEntity;

public class WightAttackGoal extends MeleeAttackGoal {
    private final WightEntity wight;
    private int raiseArmTicks;

    public WightAttackGoal(WightEntity wightIn, double speedIn, boolean longMemoryIn) {
        super(wightIn, speedIn, longMemoryIn);
        this.wight = wightIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.wight.setAggroed(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        this.wight.setAggroed(this.raiseArmTicks >= 5 && this.attackTick < 10);

    }
}