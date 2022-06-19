package net.randomscientist.soundmod.util;

import net.jpountz.lz4.LZ4Factory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.PalettedContainer;
import net.minecraft.world.chunk.WorldChunk;
import net.randomscientist.soundmod.mixin.ChunkSectionAccessor;
import net.randomscientist.soundmod.mixin.PalettedContainerAccessor;


import java.util.ArrayList;
import java.util.Arrays;

public class BitSetChunk {
    LZ4Factory factory = LZ4Factory.fastestInstance();
    private final ArrayList<Integer> data = new ArrayList<Integer>();
    private Vec3i indexToSectionPos(int i) {
        return new Vec3i(i&0xf,i&0xf00,i&0xf0 );
    }
    private int sectionPosToIndex(Vec3i p) {
        return (p.getY()<<4|p.getZ())<<4|p.getX();
    }
    public int chunkPosToIndex(Vec3i p){
        return (p.getY()<<4|p.getZ())<<4|p.getX();
    }
    public Vec3i indexToChunkPos(int i) {
        return new Vec3i(i&0xf,i>>>8,i&0xf0 );
    }
    public void ingestChunk(WorldChunk chunk) {
        final ArrayList<Integer> fullSection = new ArrayList(Arrays.asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1));
        final ArrayList<Integer> emptySection = new ArrayList(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));

        if(!(chunk ==null)) {
            ChunkSection[] sectionArray = chunk.getSectionArray();
            for (int i = 0; i < (320/16); i++) {
                ChunkSection section = sectionArray[i];
                if (section.isEmpty()) {
                    data.addAll(emptySection);
                    continue;
                }
                if (4096 == ((ChunkSectionAccessor) section).nonEmptyBlocks()) {
                    data.addAll(fullSection);
                    continue;
                }
                PalettedContainer<BlockState> states = section.getBlockStateContainer();
                PalettedContainerAccessor<BlockState> statesGettable = ((PalettedContainerAccessor<BlockState>) states);
                for (int j = 0; j < 16 * 8; j++) {
                    int s = 0;
                    for (int k = 0; k < 32; k++) {
                        Material bl=statesGettable.invokeGet(j+k).getMaterial();
                        int ref=(bl.isSolid()||bl.blocksLight())?1:0;
                        s = s << 1 | ref;
                    }
                    data.add(s);
                }

            }
        }
        else {
            for(int i =0; i<(320 / 16); i++){
                data.addAll(emptySection);
            }
        }
    }
    public ArrayList<Integer> getData() {
        return this.data;
    }
}
