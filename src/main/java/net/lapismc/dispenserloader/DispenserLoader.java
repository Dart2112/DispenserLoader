package net.lapismc.dispenserloader;

import net.lapismc.dispenserloader.containers.DispenserGroup;
import net.lapismc.lapiscore.LapisCorePlugin;

import java.util.ArrayList;
import java.util.List;

public final class DispenserLoader extends LapisCorePlugin {

    private List<DispenserGroup> groups = new ArrayList<>();
    //Command to set block for leading from, chest or dispenser, and a command to force the load

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
