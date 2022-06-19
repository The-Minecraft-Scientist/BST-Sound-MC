package net.randomscientist.soundmod.util;

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
    private final int sides = 16;
    private final int height = 320;
    private final int SectionHeight = 16;
    private final int volume = sides^2*height;
    private final ArrayList<Integer> fullSection = (ArrayList<Integer>) Arrays.asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1);
    private final ArrayList<Integer> emptySection = (ArrayList<Integer>) Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    private ArrayList<Integer> data = new ArrayList<Integer>();
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
        ChunkSection[] sectionArray = chunk.getSectionArray();
        for(int i = 0; i<(height/SectionHeight); i++) {
            ChunkSection section = sectionArray[i];
            if(section.isEmpty() || section == null){
                data.addAll(emptySection);
                continue;
            }
            if(4096==((ChunkSectionAccessor) section).nonEmptyBlocks()) {
                data.addAll(fullSection);
                continue;
            }
            PalettedContainer<BlockState> states = section.getBlockStateContainer();
            PalettedContainerAccessor<BlockState> statesGettable = ((PalettedContainerAccessor<BlockState>) states);
            for(int j = 0; j< 16*16*16;j++) {
                Material bl = statesGettable.invokeGet(j).getMaterial();
                // data.add(i<<8+j,(bl.isSolid()||bl.blocksLight()));
            }

        }

    }
}
