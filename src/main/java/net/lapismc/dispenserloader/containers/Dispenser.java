package net.lapismc.dispenserloader.containers;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class to represent the dispensers used by this plugin
 * It facilitates quickly getting or setting the contents of the dispenser with checks in place to stop errors on block changes
 */
public class Dispenser {

    private Location loc;

    /**
     * Initialize the dispenser representation class
     *
     * @param loc The location that the dispenser is at
     * @throws IllegalArgumentException Thrown if the location given is not a dispenser
     */
    public Dispenser(Location loc) throws IllegalArgumentException {
        this.loc = loc;
        if (!isDispenser()) {
            throw new IllegalArgumentException("The location provided isn't a dispenser");
        }
    }

    /**
     * Check if the location is a dispenser
     *
     * @return True if the block at loc is a dispenser, otherwise false
     */
    public boolean isDispenser() {
        return getBlock().getState() instanceof org.bukkit.block.Dispenser;
    }

    /**
     * Get the contents of the inventory of the dispenser
     *
     * @return An array list containing the contents of the dispenser
     */
    public List<ItemStack> getContents() {
        List<ItemStack> contents = new ArrayList<>();
        if (isDispenser()) {
            ItemStack[] blockInvContents = ((org.bukkit.block.Dispenser) getBlock().getState()).getInventory().getContents();
            contents.addAll(Arrays.asList(blockInvContents));
        }
        return contents;
    }

    /**
     * Set the contents of the dispenser to the list given
     *
     * @param contents The items you wish to be contained within the dispenser
     */
    public void setContents(List<ItemStack> contents) {
        if (isDispenser()) {
            ((org.bukkit.block.Dispenser) getBlock().getState()).getInventory().setContents(contents.toArray(new ItemStack[0]));
        }
    }

    private Block getBlock() {
        return loc.getBlock();
    }

}
