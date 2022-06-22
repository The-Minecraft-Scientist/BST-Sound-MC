package net.randomscientist.soundmod.util;

import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.ArrayList;

public interface WorldChunkAccessor {
    ArrayList<Integer> getSoundScene();
}
