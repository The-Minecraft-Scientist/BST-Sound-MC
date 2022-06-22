package net.randomscientist.soundmod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.ChunkData;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.BlendingData;
import net.randomscientist.soundmod.util.WorldChunkAccessor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.randomscientist.soundmod.ExampleMod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin extends Chunk implements WorldChunkAccessor {
    public WorldChunkMixin(ChunkPos pos, UpgradeData upgradeData, HeightLimitView heightLimitView, Registry<Biome> biome, long inhabitedTime, @Nullable ChunkSection[] sectionArrayInitializer, @Nullable BlendingData blendingData) {
        super(pos, upgradeData, heightLimitView, biome, inhabitedTime, sectionArrayInitializer, blendingData);
    }
    public ArrayList<Integer> soundScene = new ArrayList<>();
    public ArrayList<Integer> getSoundScene() {
        return this.soundScene;
    }
    public void buildSoundScene() {
        final ArrayList<Integer> fullSection = new ArrayList(Arrays.asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1));
        final ArrayList<Integer> emptySection = new ArrayList(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        ChunkSection[] sectionArray = super.sectionArray;
        for (int i = 0; i < (320/16); i++) {
            ChunkSection section = sectionArray[i];
            if (section.isEmpty()) {
                soundScene.addAll(emptySection);
                continue;
            }
            if (4096 == ((ChunkSectionAccessor) section).nonEmptyBlocks()) {
                soundScene.addAll(fullSection);
                continue;
            }
            PalettedContainerAccessor<BlockState> statesGettable = ((PalettedContainerAccessor<BlockState>) section.getBlockStateContainer());
            for (int j=0;j<16*8;j++) {
                int s=0;
                for (int k=0;k<32;k++) {
                    Material bl=statesGettable.invokeGet(j+k).getMaterial();
                    int ref=(bl.isSolid()||bl.blocksLight())?1:0;
                    s=s<<1|ref;
                }
                soundScene.add(s);
            }

        }
    }
    @Inject(method="loadFromPacket", at=@At("TAIL"))
    public void loadFromPacket(PacketByteBuf buf, NbtCompound nbt, Consumer<ChunkData.BlockEntityVisitor> consumer, CallbackInfo ci) {
        this.buildSoundScene();
    }
}
