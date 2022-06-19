package net.randomscientist.soundmod.scene;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientChunkManager;

import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.chunk.WorldChunk;
import net.randomscientist.soundmod.ExampleMod;
import net.randomscientist.soundmod.util.Scene;


public class SceneBuilder {
    public static void getScene() {
        Scene scene  = new Scene();

    }
    public static void test() {
        ClientChunkManager cm = MinecraftClient.getInstance().world.getChunkManager();
        WorldChunk chunk = (WorldChunk) cm.getChunk(1,1);
        if(!(chunk == null)) {
            BlockState blockState = chunk.getBlockState(new BlockPos(0, 0, 0));
            Block b;
            b = blockState.getBlock();
            MutableText r = b.getName();
            ExampleMod.LOGGER.info("block {} is at 0,0,0", r);
        }

    }
}
