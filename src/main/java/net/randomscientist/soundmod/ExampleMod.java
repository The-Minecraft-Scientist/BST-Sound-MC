package net.randomscientist.soundmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.randomscientist.soundmod.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

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
			ClientChunkManager cm = MinecraftClient.getInstance().world.getChunkManager();
			PlayerEntity player = MinecraftClient.getInstance().player;
			Scene scene = new Scene(cm,player,2);
			ArrayList<Integer> data = scene.fullScene();

		});
	}

}
