package net.randomscientist.soundmod.scene;

import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.randomscientist.soundmod.util.SceneChunk;
import net.randomscientist.soundmod.util.Pos2i;

import java.util.ArrayList;

public class Scene {
    private SceneChunk[] chunks;
    final ClientChunkManager cm;
    final PlayerEntity player;
    final Pos2i size = new Pos2i(0,0);
    final Pos2i baseChunk = new Pos2i(0,0);
    private ArrayList<Pos2i> loadpool;
    public Scene(ClientChunkManager cm, PlayerEntity player,int sizeX, int sizeZ) {
        this.cm = cm;
        this.player = player;
        this.size.setX(sizeX);
        this.size.setY(sizeZ);
    }
    public void update() {
        BlockPos playerPos = player.getBlockPos();
        Pos2i playerChunk = new Pos2i(0,0);
        playerChunk.setX(playerPos.getX()>>4);
        playerChunk.setZ(playerPos.getZ()>>4);
        if(!(playerChunk.getZ() == baseChunk.getZ() && playerChunk.getX() == baseChunk.getX())) {
        }
    }

}
