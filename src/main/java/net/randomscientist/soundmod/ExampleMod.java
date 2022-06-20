package net.randomscientist.soundmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.world.chunk.WorldChunk;
import net.randomscientist.soundmod.util.SceneChunk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("soundmod");
	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ClientTickEvents.END_WORLD_TICK.register((listener) -> {
			SceneChunk dat = new SceneChunk();
			ClientChunkManager cm = MinecraftClient.getInstance().world.getChunkManager();
			WorldChunk chunk = (WorldChunk) cm.getChunk(1,1);
			//dat.ingestChunk(chunk);
			// LOGGER.info("{}",dat.getData());
		});
	}

}
