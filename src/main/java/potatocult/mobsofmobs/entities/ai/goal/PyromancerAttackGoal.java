package potatocult.mobsofmobs.entities.ai.goal;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import potatocult.mobsofmobs.entities.boss.PyromancerEntity;

public class PyromancerAttackGoal extends MeleeAttackGoal {
    private final PyromancerEntity pyromancer;
    private int raiseArmTicks;

    public PyromancerAttackGoal(PyromancerEntity pyromancerIn, double speedIn, boolean longMemoryIn) {
        super(pyromancerIn, speedIn, longMemoryIn);
        this.pyromancer = pyromancerIn;
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
        this.pyromancer.setAggroed(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        this.pyromancer.setAggroed(this.raiseArmTicks >= 5 && this.attackTick < 10);

    }
}