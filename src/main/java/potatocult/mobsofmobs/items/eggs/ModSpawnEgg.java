package potatocult.mobsofmobs.items.eggs;

import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import potatocult.mobsofmobs.core.MobsOfMobs;

public class ModSpawnEgg extends Item {

    private final int primaryColor;
    private final int secondaryColor;

    public ModSpawnEgg(int primaryColorIn, int secondaryColorIn) {
        super(new Item.Properties().maxStackSize(1).group(MobsOfMobs.GROUP));
        this.primaryColor = primaryColorIn;
        this.secondaryColor = secondaryColorIn;
    }

    @OnlyIn(Dist.CLIENT)
    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

}