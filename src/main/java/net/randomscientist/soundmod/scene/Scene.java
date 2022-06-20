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
    private int size = 4;
    final Pos2i baseChunk = new Pos2i(0,0);
    private ArrayList<Pos2i> loadpool;
    private ArrayList<Pos2i> loaded;
    public Scene(ClientChunkManager cm, PlayerEntity player,int size) {
        this.cm = cm;
        this.player = player;
        this.size = size;
    }
    public void update() {
        BlockPos playerPos = player.getBlockPos();
        Pos2i playerChunk = new Pos2i(0,0);
        playerChunk.setX(playerPos.getX()>>4);
        playerChunk.setZ(playerPos.getZ()>>4);
        if(!(playerChunk.getZ() == baseChunk.getZ() && playerChunk.getX() == baseChunk.getX())) {
            for(int i =baseChunk.x-size; i<baseChunk.x+size; i++) {
                for(int j = baseChunk.y-size;j< baseChunk.y+size; j++) {
                    Pos2i chunk = new Pos2i(i,j);
                    if(!(loaded.contains(chunk))) {
                        loadpool.add(chunk);
                    }
                }
            }
        }
    }

}
