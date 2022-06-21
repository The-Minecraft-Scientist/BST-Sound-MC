package net.randomscientist.soundmod.scene;

import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;
import net.randomscientist.soundmod.util.SceneChunk;
import net.randomscientist.soundmod.util.Pos2i;

import java.util.ArrayList;

public class Scene {
    private SceneChunk[] chunks;
    final ClientChunkManager cm;
    final PlayerEntity player;
    private int size = 4;
    final Pos2i baseChunk = new Pos2i(0,0);
    Pos2i playerChunk = new Pos2i(0,0);
    private ArrayList<Pos2i> loadpool;
    private ArrayList<Pos2i> loaded;
    public Scene(ClientChunkManager cm, PlayerEntity player,int size) {
        this.cm = cm;
        this.player = player;
        this.size = size;
    }
    public void update() {
        BlockPos playerPos = player.getBlockPos();
        playerChunk.x=playerPos.getX()>>4;
        playerChunk.y=playerPos.getZ()>>4;
        if(!(playerChunk.y == baseChunk.y && playerChunk.x == baseChunk.x)) {
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
    private int posToIndex(Pos2i p) {
        Pos2i rel=p.sub(playerChunk).add(new Pos2i(size,size));
        return rel.y<<3|rel.x;    }
    public void parse() {
        if(!(loadpool.size()==0)) {
            Pos2i task = loadpool.get(0);
            int ind = posToIndex(task);
            WorldChunk c = cm.getWorldChunk(task.x,task.y);
            chunks[ind].ingestChunk(c);
            loadpool.remove(0);
            loaded.add(task);
        }
    }

}
