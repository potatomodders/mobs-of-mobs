package potatocult.mobsofmobs.items.eggs;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import potatocult.mobsofmobs.core.MobsOfMobs;

public class MobSpawnEgg extends SpawnEggItem {
    private Supplier<? extends EntityType<?>> typeGetter;

    public MobSpawnEgg(Supplier<? extends EntityType<?>> typeIn, int primaryColorIn, int secondaryColorIn) {
        super(null, primaryColorIn, secondaryColorIn,
                new Item.Properties().group(MobsOfMobs.GROUP).maxStackSize(64).rarity(MobsOfMobs.getRarity()));
        typeGetter = typeIn;
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
        return typeGetter.get();
    }

}